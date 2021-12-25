package fai.imp.farmadati.task;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import fai.common.util.Timeout;
import fai.imp.farmadati.db.FarmadatiTable;
import fai.imp.farmadati.db.FarmadatiTable.Record;

public class FarmadatiContentHandler implements ContentHandler {
  
  
  static Logger logger = Logger.getLogger(FarmadatiContentHandler.class);


  private FarmadatiTable table = null;
  private BufferedWriter writer = null;

  private FarmadatiTable.Record currRecord = null;
  private String currentFieldName = null;
  private StringBuffer currentFieldValue = null;
  private int recordCount = 0;
  
  
  private Long ignoreInsertAferMillis = null;
  private Long startAtMillis = null;
  private boolean doNotInsertOtherRecords = false;
  

  /**
   * SOLO A SCOPO DI DEBUG!!!
   * 
   * @param ignoreInsertAferMillis
   */

  public void setIgnoreInsertAferMillis(Long ignoreInsertAferMillis) {
    this.ignoreInsertAferMillis = ignoreInsertAferMillis;
  }

  
  
  private List<String> stack = new ArrayList<String>();
  
  private Timeout timeout = new Timeout(10000, false);

  public FarmadatiContentHandler() {
    super();
  }

  public FarmadatiContentHandler(FarmadatiTable table) {
    super();
    this.table = table;
  }
  
  public FarmadatiContentHandler(OutputStream os) {
    super();
    writer = new BufferedWriter(new OutputStreamWriter(os));
  }

  @Override
  public void setDocumentLocator(Locator locator) {
    
  }

  @Override
  public void startDocument() throws SAXException {
    startAtMillis = System.currentTimeMillis();
  }

  @Override
  public void endDocument() throws SAXException {
    try {
      if (table != null) {
        logger.info(recordCount+" record complessivamente inseriti nella tabella "+table.getTableName());
      }
      else {
        logger.info(recordCount+" record complessivamente processati");
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»";
      throw new IllegalStateException(msg, th);
    }
  }

  @Override
  public void startPrefixMapping(String prefix, String uri) throws SAXException {
    
  }

  @Override
  public void endPrefixMapping(String prefix) throws SAXException {
    
  }
  
  @Override
  public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    //
    if (doNotInsertOtherRecords == true) return;
    //
    stack.add(localName);
    if ("RECORD".equals(localName)) {
      if (currRecord != null) throw new IllegalStateException("inammissibile, elemento non null non atteso in questo punto");
      currRecord = new FarmadatiTable.Record();
    }
    else if (stack.size() >= 2 && "RECORD".equals(stack.get(stack.size()-2))) {
      if (currentFieldName != null) throw new IllegalStateException("inammissibile, nome campo non null non atteso in questo punto");
      currentFieldName = localName;
    }
    
      
  }

  String _DEBUG_AS_STRING(Record record) {
    String s = "";
    for (int i = 0; i < record.size(); i++) {
      s+=record.getFieldNameAt(i)+"="+record.getFieldValueAt(i);
      if (i < record.size()-1) s+=", ";
    }
    return s;
  }
  
  
  
  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
      if (doNotInsertOtherRecords == false) {
          endElement_Actual(uri, localName, qName);
      }
      else {
          endElement_Fake(uri, localName, qName);
      }
  }
  
  private void endElement_Fake(String uri, String localName, String qName) throws SAXException {
        recordCount++;
        if (timeout.isExpired()) {
          if (table != null) {
            logger.info(recordCount+" record inseriti nella tabella "+table.getTableName()+" ...");
          }
          else {
            logger.info(recordCount+" record processati ...");
          }
        }
  }

  
  private void endElement_Actual(String uri, String localName, String qName) throws SAXException {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    try {
      String removedLocalName = stack.remove(stack.size()-1);
      if (localName.equals(removedLocalName) == false) throw new IllegalStateException("inammissibile, "+METH_NAME+" per "+localName+", ma l'elemento rimosso dallo stack risulta essere "+removedLocalName);
      //
      if ("RECORD".equals(localName)) {
        if (currRecord == null) throw new IllegalStateException("inammissibile, elemento null non atteso in questo punto");
        if (table == null && writer == null) {
          // ... stampo il contenuto sullo standard output
          System.out.println(_DEBUG_AS_STRING(currRecord));
        }
        if (table != null) {
          table.insertRecord(currRecord);
        }
        if (writer != null) {
          writer.write(_DEBUG_AS_STRING(currRecord)+"\r\n");
        }
        //
        recordCount++;
        if (timeout.isExpired()) {
          if (table != null) {
            logger.info(recordCount+" record inseriti nella tabella "+table.getTableName()+" ...");
          }
          else {
            logger.info(recordCount+" record processati ...");
          }
        }
        //
        currRecord = null;
        //
        if (ignoreInsertAferMillis != null) {
          if (System.currentTimeMillis() - startAtMillis >= ignoreInsertAferMillis){
            if (doNotInsertOtherRecords == false) {
              logger.warn("*** ATTENZIONE! RAGGIUNTA SOGLIA DI "+ignoreInsertAferMillis+" MILLISECONDI CON "+recordCount+" RECORD INSERITI, NESSUN ALTRO RECORD SARA' EFFETTIVAMENTE INSERITO IN BANCA DATI!! QUESTO MESSAGGIO NON DEVE COMPARIRE NEI LOG DI PRODUZIONE!!! ***");
              doNotInsertOtherRecords = true;
            }
          }
        }
      }
      else if (stack.size() >= 1 && "RECORD".equals(stack.get(stack.size()-1))) {
        if (currentFieldName == null) throw new IllegalStateException("inammissibile, nome campo null non atteso in questo punto");
        currRecord.set(currentFieldName, currentFieldValue == null ? null : currentFieldValue.toString());
        currentFieldName = null;
        currentFieldValue = null;
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      throw new IllegalStateException(msg, th);
    }
    
  }
  

  

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    //
    if (doNotInsertOtherRecords == true) return;
    //
    if ((stack.size() >= 2 && "RECORD".equals(stack.get(stack.size()-2))) == false) return;
    //
    char [] c = new char[length];
    System.arraycopy(ch, start, c, 0, length);
    String s = new String(c);
    if (currentFieldValue == null) 
      currentFieldValue = new StringBuffer(s);
    else
      currentFieldValue.append(s);
    
  }

  @Override
  public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    
  }

  @Override
  public void processingInstruction(String target, String data) throws SAXException {
    
  }

  @Override
  public void skippedEntity(String name) throws SAXException {
    
  }
  
}
