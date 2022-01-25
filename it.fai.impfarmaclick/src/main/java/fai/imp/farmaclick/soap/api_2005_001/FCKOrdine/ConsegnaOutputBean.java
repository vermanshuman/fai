/**
 * ConsegnaOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKOrdine;

public class ConsegnaOutputBean  implements java.io.Serializable {
    private java.lang.String codiceFornitore;

    private java.util.Calendar dataConsegna;

    private java.lang.String descrizioneCorriere;

    private java.lang.String telefonoCorriere;

    public ConsegnaOutputBean() {
    }

    public ConsegnaOutputBean(
           java.lang.String codiceFornitore,
           java.util.Calendar dataConsegna,
           java.lang.String descrizioneCorriere,
           java.lang.String telefonoCorriere) {
           this.codiceFornitore = codiceFornitore;
           this.dataConsegna = dataConsegna;
           this.descrizioneCorriere = descrizioneCorriere;
           this.telefonoCorriere = telefonoCorriere;
    }


    /**
     * Gets the codiceFornitore value for this ConsegnaOutputBean.
     * 
     * @return codiceFornitore
     */
    public java.lang.String getCodiceFornitore() {
        return codiceFornitore;
    }


    /**
     * Sets the codiceFornitore value for this ConsegnaOutputBean.
     * 
     * @param codiceFornitore
     */
    public void setCodiceFornitore(java.lang.String codiceFornitore) {
        this.codiceFornitore = codiceFornitore;
    }


    /**
     * Gets the dataConsegna value for this ConsegnaOutputBean.
     * 
     * @return dataConsegna
     */
    public java.util.Calendar getDataConsegna() {
        return dataConsegna;
    }


    /**
     * Sets the dataConsegna value for this ConsegnaOutputBean.
     * 
     * @param dataConsegna
     */
    public void setDataConsegna(java.util.Calendar dataConsegna) {
        this.dataConsegna = dataConsegna;
    }


    /**
     * Gets the descrizioneCorriere value for this ConsegnaOutputBean.
     * 
     * @return descrizioneCorriere
     */
    public java.lang.String getDescrizioneCorriere() {
        return descrizioneCorriere;
    }


    /**
     * Sets the descrizioneCorriere value for this ConsegnaOutputBean.
     * 
     * @param descrizioneCorriere
     */
    public void setDescrizioneCorriere(java.lang.String descrizioneCorriere) {
        this.descrizioneCorriere = descrizioneCorriere;
    }


    /**
     * Gets the telefonoCorriere value for this ConsegnaOutputBean.
     * 
     * @return telefonoCorriere
     */
    public java.lang.String getTelefonoCorriere() {
        return telefonoCorriere;
    }


    /**
     * Sets the telefonoCorriere value for this ConsegnaOutputBean.
     * 
     * @param telefonoCorriere
     */
    public void setTelefonoCorriere(java.lang.String telefonoCorriere) {
        this.telefonoCorriere = telefonoCorriere;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsegnaOutputBean)) return false;
        ConsegnaOutputBean other = (ConsegnaOutputBean) obj;
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
            ((this.dataConsegna==null && other.getDataConsegna()==null) || 
             (this.dataConsegna!=null &&
              this.dataConsegna.equals(other.getDataConsegna()))) &&
            ((this.descrizioneCorriere==null && other.getDescrizioneCorriere()==null) || 
             (this.descrizioneCorriere!=null &&
              this.descrizioneCorriere.equals(other.getDescrizioneCorriere()))) &&
            ((this.telefonoCorriere==null && other.getTelefonoCorriere()==null) || 
             (this.telefonoCorriere!=null &&
              this.telefonoCorriere.equals(other.getTelefonoCorriere())));
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
        if (getDataConsegna() != null) {
            _hashCode += getDataConsegna().hashCode();
        }
        if (getDescrizioneCorriere() != null) {
            _hashCode += getDescrizioneCorriere().hashCode();
        }
        if (getTelefonoCorriere() != null) {
            _hashCode += getTelefonoCorriere().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsegnaOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ConsegnaOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFornitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFornitore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataConsegna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataConsegna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneCorriere");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneCorriere"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoCorriere");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefonoCorriere"));
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
