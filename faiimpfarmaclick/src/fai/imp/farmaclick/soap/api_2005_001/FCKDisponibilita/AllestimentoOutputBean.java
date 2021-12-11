/**
 * AllestimentoOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita;

public class AllestimentoOutputBean  implements java.io.Serializable {
    private java.lang.String codiceFornitore;

    private int quantitaAllestita;

    public AllestimentoOutputBean() {
    }

    public AllestimentoOutputBean(
           java.lang.String codiceFornitore,
           int quantitaAllestita) {
           this.codiceFornitore = codiceFornitore;
           this.quantitaAllestita = quantitaAllestita;
    }


    /**
     * Gets the codiceFornitore value for this AllestimentoOutputBean.
     * 
     * @return codiceFornitore
     */
    public java.lang.String getCodiceFornitore() {
        return codiceFornitore;
    }


    /**
     * Sets the codiceFornitore value for this AllestimentoOutputBean.
     * 
     * @param codiceFornitore
     */
    public void setCodiceFornitore(java.lang.String codiceFornitore) {
        this.codiceFornitore = codiceFornitore;
    }


    /**
     * Gets the quantitaAllestita value for this AllestimentoOutputBean.
     * 
     * @return quantitaAllestita
     */
    public int getQuantitaAllestita() {
        return quantitaAllestita;
    }


    /**
     * Sets the quantitaAllestita value for this AllestimentoOutputBean.
     * 
     * @param quantitaAllestita
     */
    public void setQuantitaAllestita(int quantitaAllestita) {
        this.quantitaAllestita = quantitaAllestita;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AllestimentoOutputBean)) return false;
        AllestimentoOutputBean other = (AllestimentoOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceFornitore==null && other.getCodiceFornitore()==null) || 
             (this.codiceFornitore!=null &&
              this.codiceFornitore.equals(other.getCodiceFornitore()))) &&
            this.quantitaAllestita == other.getQuantitaAllestita();
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
        if (getCodiceFornitore() != null) {
            _hashCode += getCodiceFornitore().hashCode();
        }
        _hashCode += getQuantitaAllestita();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AllestimentoOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "AllestimentoOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFornitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFornitore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaAllestita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaAllestita"));
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
