package fai.imp.farmadati.db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.datacontract.schemas._2004._07.FDIWebServices.GetSchemaDataSet_Output;
import org.datacontract.schemas._2004._07.FDIWebServices.SetDati;
import org.w3c.dom.NodeList;

import fai.common.db.SqlUtilities;
import fai.common.util.Filesystem;
import fai.common.util.XmlUtil;
import fai.imp.farmadati.db.FarmadatiTable.Record;

import org.w3c.dom.Node;
import org.apache.log4j.Logger;
import org.datacontract.schemas._2004._07.FDIWebServices.Field;

public class FarmadatiTable {
  
  
  static Logger logger = Logger.getLogger(FarmadatiTable.class);

  private static final String FARMADATI_DATE_FORMAT = "yyyy-MM-dd";
  private static final SimpleDateFormat sdfFarmadati = new SimpleDateFormat(FARMADATI_DATE_FORMAT);
  

  private Connection conn = null;
  private String tableName = null;
  private Hashtable<String, Field> fieldByName = null;
  private Field [] sortedField = null; 
  private int recordCount = 0;
  private boolean useBatchInsert = false;
  private PreparedStatement pstmt = null; 
  private StringBuffer sql = null; 

  public static class Record {
    private List<String> fieldNames = new ArrayList<String>();
    private List<String> fieldValues = new ArrayList<String>();
    private Hashtable<String, Field> fields = null;
    private Hashtable<String, String> values = new Hashtable<String, String>();
    
    public Record set(String fieldName, String fieldValue) {
      fieldNames.add(fieldName);
      fieldValues.add(fieldValue);
      values.put(fieldName, fieldValue);
      return this;
    }
    
    public int size() {
      return fieldNames.size();
    }
    
    public Record setFieldByName(Hashtable<String, Field> fieldByName) {
      fields = fieldByName;
      return this;
    }
    
    public Field getField(String fieldName) throws Exception {
      Field f = fields.get(fieldName);
      if (f == null) throw new IllegalArgumentException("inammissibile, nessun "+Field.class.getName()+" per l'identificativo "+fieldName);
      return f;
    }
    
    public String getValue(String fieldName) throws Exception {
      return values.get(fieldName);
    }
    
    public String getFieldNameAt(int idx)  {
      return fieldNames.get(idx);
    }
    
    public String getFieldValueAt(int idx)  {
      return fieldValues.get(idx);
    }
  }
  
  private FarmadatiTable() {
    
  }
  
  public FarmadatiTable(SetDati setDati, GetSchemaDataSet_Output schemaDataSet, Connection conn) {
    super();
    this.tableName = setDati.getKey();
    sortedField = schemaDataSet.getFields();
    Arrays.sort(sortedField, new Comparator<Field>() {
      @Override
      public int compare(Field o1, Field o2) {
        if (o1.getIdNum() < o2.getIdNum())
          return -1;
        else if (o1.getIdNum() > o2.getIdNum())
          return 1;
        else
          return 0;
      }
    });
    fieldByName = new Hashtable<String, Field>();
    for (int i = 0; i < sortedField.length; i++) {
      String key = sortedField[i].getKey();
      fieldByName.put(key, sortedField[i]);
    }
    this.conn = conn;
  }
  
  public String getTableName() {
    return tableName;
  }

  
  
  public boolean isUseBatchInsert() {
    return useBatchInsert;
  }

  public void setUseBatchInsert(boolean useBatchInsert) {
    this.useBatchInsert = useBatchInsert;
  }
  public FarmadatiTable deleteAllRecords() throws Exception {
    SqlQueries.executeUpdate("DELETE FROM "+tableName, conn);
    return this;
  }
  
  /**
   * Inserisce il singolo record.<br/>
   * V. anche {@link #insertRecordsPrepare()} ed
   * {@link #insertRecordsCompleted()}
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public FarmadatiTable insertRecord(Record record) throws Exception {
    record.setFieldByName(fieldByName);
    //
    insertRecordExecute(getTableName(), record);
    recordCount++;
    return this;
  }

  /**
   * "Alloca le risorse", in pratica crea il {@link PreparedStatement}.<br/>
   * Va <u>obbligatoriamente</u> invocata una sola volta, prima della prima
   * call a {@link #insertRecord(Record)}.<br/>
   * <br/>
   * Quando tutti i record sono stati inseriti, va <u>obbligatoriamente</u>
   * chiamata la {@link #insertRecordsCompleted()} 
   * 
   * @return
   * @throws Exception
   */
  public FarmadatiTable insertRecordsPrepare() throws Exception {
    //
    // --- creazione della struttura del prepared statement ---
    // 
    if (sql == null) {
      StringBuffer fieldNames = new StringBuffer("");
      StringBuffer fieldValues = new StringBuffer("");
      for (int i = 0; i < sortedField.length; i++) {
        fieldNames.append(sortedField[i].getKey());
        fieldValues.append("?");
        if (i < sortedField.length - 1) {
          fieldNames.append(", ");
          fieldValues.append(", ");
        }
      }
      sql = new StringBuffer("INSERT INTO ");
      sql.append(tableName);
      sql.append(" (");
      sql.append(fieldNames.toString());
      sql.append(") VALUES (");
      sql.append(fieldValues.toString());
      sql.append(")");
      logger.info("Statement SQL:" + System.getProperty("line.separator") + sql);
      pstmt = conn.prepareStatement(sql.toString());
    }
    //
    return this;
  }

  /**
   * Complementare alla {@link #insertRecordsPrepare()} (cui
   * si rimanda), "dealloca le risorse" da questa allocate.<br/>
   * Se attiva la moddalità "batch insert", allora esegue la
   * {@link PreparedStatement#executeBatch()}
   * 
   * @return
   * @throws Exception
   */
  public FarmadatiTable insertRecordsCompleted() throws Exception {
    if (pstmt == null) return this;
    if (useBatchInsert) {
      logger.info("conferma batch insert ("+recordCount+" record) ...");
      pstmt.executeBatch();
      logger.info("conferma batch confermata");
    }
    SqlUtilities.closeWithNoException(pstmt);
    sql = null;
    pstmt = null;
    return this;
  }
  
  
  
  

  public int getRecordCount() {
    return recordCount;
  }


  private  java.util.Date parseFarmadatiDate(String s) throws Exception {
    if (s == null || (s != null && "".equals(s.trim()))) return null;
    try {
      return sdfFarmadati.parse(s);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»; \""+s+"\" non è una data nel formato atteso \""+FARMADATI_DATE_FORMAT+"\"";
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
  }
  

  private void insertRecordExecute(String tableName, Record record) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    List<String> debugPurposeFieldsInfo = new ArrayList<String>();
    try {
      //
      // --- binding dei parametri ---
      // 
      for (int i = 0; i < sortedField.length; i++) {
        String fieldName = sortedField[i].getKey();
        Field field = record.getField(fieldName);
        String valueAsString = record.getValue(fieldName);
        //
        debugPurposeFieldsInfo.add("#"+(i+1)+"/"+sortedField.length+" "+fieldName+":"+field.getType()+"("+field.getLength()+") = "+valueAsString);
        //
        if ("VARCHAR".equals(field.getType())) {
          SqlUtilities.setString(pstmt, i+1, valueAsString);
        }
        else if ("TEXT".equals(field.getType())) {
          SqlUtilities.setStringAsClob(pstmt, i+1, valueAsString);
        }
        else if ("DATE".equals(field.getType())) {
          SqlUtilities.setDate(pstmt, i+1, parseFarmadatiDate(valueAsString));
        }
        else if ("NUMERIC".equals(field.getType())) {
          if (field.getLength().indexOf(",") >=0 ) {
            // la lunghezza è del tipo "10,3", quindi un "double"
            Double value = valueAsString == null ? null : new Double(valueAsString.trim());
            SqlUtilities.setDouble(pstmt, i+1, value);
          }
          else {
            Integer value = valueAsString == null ? null : new Integer(valueAsString.trim());
            SqlUtilities.setInteger(pstmt, i+1, value);
          }
        }
        else {
          throw new IllegalStateException("tipo non gestito: "+field.getType());
        }
      }
      //
      // --- add batch ---
      // 
      if (useBatchInsert) {
        pstmt.addBatch();
      }
      else {
        pstmt.executeUpdate();
      }
    }
    catch (Throwable th) {
      logger.error("Insert NON riuscita causa eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»; segue elenco parametri:");
      for (int i = 0; i < debugPurposeFieldsInfo.size(); i++) {
        logger.error(debugPurposeFieldsInfo.get(i));
      }
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }

    
  }
  

}
