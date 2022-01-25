/**
 * ErogazioneServiziBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKLogin;

public class ErogazioneServiziBean  implements java.io.Serializable {
    private int giorno;

    private java.util.Calendar oraFine;

    private java.util.Calendar oraInizio;

    public ErogazioneServiziBean() {
    }

    public ErogazioneServiziBean(
           int giorno,
           java.util.Calendar oraFine,
           java.util.Calendar oraInizio) {
           this.giorno = giorno;
           this.oraFine = oraFine;
           this.oraInizio = oraInizio;
    }


    /**
     * Gets the giorno value for this ErogazioneServiziBean.
     * 
     * @return giorno
     */
    public int getGiorno() {
        return giorno;
    }


    /**
     * Sets the giorno value for this ErogazioneServiziBean.
     * 
     * @param giorno
     */
    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }


    /**
     * Gets the oraFine value for this ErogazioneServiziBean.
     * 
     * @return oraFine
     */
    public java.util.Calendar getOraFine() {
        return oraFine;
    }


    /**
     * Sets the oraFine value for this ErogazioneServiziBean.
     * 
     * @param oraFine
     */
    public void setOraFine(java.util.Calendar oraFine) {
        this.oraFine = oraFine;
    }


    /**
     * Gets the oraInizio value for this ErogazioneServiziBean.
     * 
     * @return oraInizio
     */
    public java.util.Calendar getOraInizio() {
        return oraInizio;
    }


    /**
     * Sets the oraInizio value for this ErogazioneServiziBean.
     * 
     * @param oraInizio
     */
    public void setOraInizio(java.util.Calendar oraInizio) {
        this.oraInizio = oraInizio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErogazioneServiziBean)) return false;
        ErogazioneServiziBean other = (ErogazioneServiziBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.giorno == other.getGiorno() &&
            ((this.oraFine==null && other.getOraFine()==null) || 
             (this.oraFine!=null &&
              this.oraFine.equals(other.getOraFine()))) &&
            ((this.oraInizio==null && other.getOraInizio()==null) || 
             (this.oraInizio!=null &&
              this.oraInizio.equals(other.getOraInizio())));
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
        _hashCode += getGiorno();
        if (getOraFine() != null) {
            _hashCode += getOraFine().hashCode();
        }
        if (getOraInizio() != null) {
            _hashCode += getOraInizio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErogazioneServiziBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ErogazioneServiziBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("giorno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "giorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oraFine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oraFine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oraInizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oraInizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
