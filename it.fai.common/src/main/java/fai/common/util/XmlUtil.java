package fai.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import org.xml.sax.ContentHandler;

public class XmlUtil {
  
  public static Document cloneDocument(Document originalDocument) throws
      Exception {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();

    Node originalRoot = originalDocument.getDocumentElement();

    Document copiedDocument = db.newDocument();
    Node copiedRoot = copiedDocument.importNode(originalRoot, true);
    copiedDocument.appendChild(copiedRoot);

    return copiedDocument;

  }
  
  
  public static Date asDate(XMLGregorianCalendar value) throws Exception {
    if (value == null) return null;
    if (value.toGregorianCalendar() == null) return null;
    if (value.toGregorianCalendar().getTime() == null) return null;
    return new Date(value.toGregorianCalendar().getTime().getTime());
  }

  public static XMLGregorianCalendar asXMLGregorianCalendar(Date date) throws Exception {
    GregorianCalendar gCalendar = new GregorianCalendar();
    gCalendar.setTime(date);
    XMLGregorianCalendar xmlCalendar = null;

    xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);

    return xmlCalendar;
  
  }
  

  public static org.w3c.dom.Document asDocument(String xml) throws Exception {

    java.io.InputStream is = new java.io.ByteArrayInputStream(xml.getBytes());

    javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.
        DocumentBuilderFactory.newInstance();
    //  factory.setNamespaceAware(true);

    javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();

    org.w3c.dom.Document doc = builder.parse(is);
    is.close();
    return doc;
  }

  public static String asString(org.w3c.dom.Document node) throws
      TransformerFactoryConfigurationError, TransformerException, SAXException,
      IOException {
    StringWriter writer = new StringWriter();
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                                  "2");
    SchemaFactory factory = SchemaFactory.newInstance(/* Workaround: Deliverables non vede la costante XMLConstants.W3C_XML_SCHEMA_NS_URI --> copio direttamente la costante corrispondente "http://www.w3.org/2001/XMLSchema" */ "http://www.w3.org/2001/XMLSchema");
    

      transformer.transform(new DOMSource(node), new StreamResult(writer));
    String xml = writer.toString();
    return xml;
  }

  public static String asBeautifiedString(String xml) throws TransformerFactoryConfigurationError, TransformerException, SAXException, IOException, Exception {
    if (xml == null) return null;
    return asString(asDocument(xml));
  }

  public static void doParse(InputStream is, ContentHandler handler) throws Exception {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    spf.setNamespaceAware(true);
    SAXParser saxParser = spf.newSAXParser();
    XMLReader xmlReader = saxParser.getXMLReader();
    xmlReader.setContentHandler(handler);
    InputSource inputSource = new InputSource(is);
    xmlReader.parse(inputSource);
  }

}
