/**
 * CommonOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKOrdine;

public class CommonOutputBean  implements java.io.Serializable {
    private int esitoServizio;

    public CommonOutputBean() {
    }

    public CommonOutputBean(
           int esitoServizio) {
           this.esitoServizio = esitoServizio;
    }


    /**
     * Gets the esitoServizio value for this CommonOutputBean.
     * 
     * @return esitoServizio
     */
    public int getEsitoServizio() {
        return esitoServizio;
    }


    /**
     * Sets the esitoServizio value for this CommonOutputBean.
     * 
     * @param esitoServizio
     */
    public void setEsitoServizio(int esitoServizio) {
        this.esitoServizio = esitoServizio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CommonOutputBean)) return false;
        CommonOutputBean other = (CommonOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.esitoServizio == other.getEsitoServizio();
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
        _hashCode += getEsitoServizio();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CommonOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bean.farmaclick.infarma.it", "CommonOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esitoServizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esitoServizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
