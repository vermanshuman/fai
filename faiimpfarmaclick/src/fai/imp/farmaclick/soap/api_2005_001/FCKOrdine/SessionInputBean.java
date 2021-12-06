/**
 * SessionInputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKOrdine;

public class SessionInputBean  implements java.io.Serializable {
    private java.lang.String IDSessione;

    public SessionInputBean() {
    }

    public SessionInputBean(
           java.lang.String IDSessione) {
           this.IDSessione = IDSessione;
    }


    /**
     * Gets the IDSessione value for this SessionInputBean.
     * 
     * @return IDSessione
     */
    public java.lang.String getIDSessione() {
        return IDSessione;
    }


    /**
     * Sets the IDSessione value for this SessionInputBean.
     * 
     * @param IDSessione
     */
    public void setIDSessione(java.lang.String IDSessione) {
        this.IDSessione = IDSessione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SessionInputBean)) return false;
        SessionInputBean other = (SessionInputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IDSessione==null && other.getIDSessione()==null) || 
             (this.IDSessione!=null &&
              this.IDSessione.equals(other.getIDSessione())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIDSessione() != null) {
            _hashCode += getIDSessione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SessionInputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bean.farmaclick.infarma.it", "SessionInputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDSessione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDSessione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
