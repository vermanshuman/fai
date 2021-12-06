package fai.common.util;

import java.io.StringWriter;
import java.lang.reflect.Method;

import javax.xml.namespace.QName;

import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.ser.BeanSerializer;
import org.apache.axis.server.AxisServer;
import org.xml.sax.helpers.AttributesImpl;

public class AxisSerializationUtil {

  public static String serializeAxisObject(Object obj) throws Exception {
    if (obj == null) return null;
    //
    StringWriter outStr = new StringWriter();
    TypeDesc typeDesc = getAxisTypeDesc(obj);
    QName qname = typeDesc.getXmlType();
    String lname = qname.getLocalPart();
    if (lname.startsWith(">") && lname.length() > 1)
      lname = lname.substring(1);

    qname = new QName(qname.getNamespaceURI(), lname);
    AxisServer server = new AxisServer();
    BeanSerializer ser = new BeanSerializer(obj.getClass(), qname, typeDesc);
    SerializationContext ctx = new SerializationContext(outStr, new MessageContext(server));
    ctx.setSendDecl(false);
    ctx.setDoMultiRefs(false);
    ctx.setPretty(true);
    try {
      ser.serialize(qname, new AttributesImpl(), obj, ctx);
    }
    catch (final Exception e) {
      throw new Exception("Unable to serialize object " + obj.getClass().getName(), e);
    }

    String xml = outStr.toString();
    return xml;
  }

  public static Object deserializeAxisObject(Class<?> cls, String xml) throws Exception {
    if (xml == null) return null;
    //
    final String SOAP_START = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header /><soapenv:Body>";
    final String SOAP_START_XSI = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header /><soapenv:Body>";
    final String SOAP_END = "</soapenv:Body></soapenv:Envelope>";

    Object result = null;
    try {
      Message message = new Message(SOAP_START + xml + SOAP_END);
      result = message.getSOAPEnvelope().getFirstBody().getObjectValue(cls);
    }
    catch (Exception e) {
      try {
        Message message = new Message(SOAP_START_XSI + xml + SOAP_END);
        result = message.getSOAPEnvelope().getFirstBody().getObjectValue(cls);
      }
      catch (Exception e1) {
        throw new Exception(e1);
      }
    }
    return result;
  }

  public static TypeDesc getAxisTypeDesc(Object obj) throws Exception {
    final Class<? extends Object> objClass = obj.getClass();
    try {
      final Method methodGetTypeDesc = objClass.getMethod("getTypeDesc", new Class[] {});
      final TypeDesc typeDesc = (TypeDesc)methodGetTypeDesc.invoke(obj, new Object[] {});
      return (typeDesc);
    }
    catch (final Exception e) {
      throw new Exception("Unable to get Axis TypeDesc for " + objClass.getName(), e);
    }
  }

}
