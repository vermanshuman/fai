/**
 * ConsegnaOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKOrdine;

public class ConsegnaOutputBean  implements java.io.Serializable {
    private java.lang.String codiceCorriere;

    private java.lang.String codiceFornitore;

    private java.util.Calendar dataConsegna;

    private java.lang.String numeroOrdineFornitore;

    private int quantitaConsegnata;

    public ConsegnaOutputBean() {
    }

    public ConsegnaOutputBean(
           java.lang.String codiceCorriere,
           java.lang.String codiceFornitore,
           java.util.Calendar dataConsegna,
           java.lang.String numeroOrdineFornitore,
           int quantitaConsegnata) {
           this.codiceCorriere = codiceCorriere;
           this.codiceFornitore = codiceFornitore;
           this.dataConsegna = dataConsegna;
           this.numeroOrdineFornitore = numeroOrdineFornitore;
           this.quantitaConsegnata = quantitaConsegnata;
    }


    /**
     * Gets the codiceCorriere value for this ConsegnaOutputBean.
     * 
     * @return codiceCorriere
     */
    public java.lang.String getCodiceCorriere() {
        return codiceCorriere;
    }


    /**
     * Sets the codiceCorriere value for this ConsegnaOutputBean.
     * 
     * @param codiceCorriere
     */
    public void setCodiceCorriere(java.lang.String codiceCorriere) {
        this.codiceCorriere = codiceCorriere;
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
     * Gets the numeroOrdineFornitore value for this ConsegnaOutputBean.
     * 
     * @return numeroOrdineFornitore
     */
    public java.lang.String getNumeroOrdineFornitore() {
        return numeroOrdineFornitore;
    }


    /**
     * Sets the numeroOrdineFornitore value for this ConsegnaOutputBean.
     * 
     * @param numeroOrdineFornitore
     */
    public void setNumeroOrdineFornitore(java.lang.String numeroOrdineFornitore) {
        this.numeroOrdineFornitore = numeroOrdineFornitore;
    }


    /**
     * Gets the quantitaConsegnata value for this ConsegnaOutputBean.
     * 
     * @return quantitaConsegnata
     */
    public int getQuantitaConsegnata() {
        return quantitaConsegnata;
    }


    /**
     * Sets the quantitaConsegnata value for this ConsegnaOutputBean.
     * 
     * @param quantitaConsegnata
     */
    public void setQuantitaConsegnata(int quantitaConsegnata) {
        this.quantitaConsegnata = quantitaConsegnata;
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
            ((this.codiceCorriere==null && other.getCodiceCorriere()==null) || 
             (this.codiceCorriere!=null &&
              this.codiceCorriere.equals(other.getCodiceCorriere()))) &&
            ((this.codiceFornitore==null && other.getCodiceFornitore()==null) || 
             (this.codiceFornitore!=null &&
              this.codiceFornitore.equals(other.getCodiceFornitore()))) &&
            ((this.dataConsegna==null && other.getDataConsegna()==null) || 
             (this.dataConsegna!=null &&
              this.dataConsegna.equals(other.getDataConsegna()))) &&
            ((this.numeroOrdineFornitore==null && other.getNumeroOrdineFornitore()==null) || 
             (this.numeroOrdineFornitore!=null &&
              this.numeroOrdineFornitore.equals(other.getNumeroOrdineFornitore()))) &&
            this.quantitaConsegnata == other.getQuantitaConsegnata();
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
        if (getCodiceCorriere() != null) {
            _hashCode += getCodiceCorriere().hashCode();
        }
        if (getCodiceFornitore() != null) {
            _hashCode += getCodiceFornitore().hashCode();
        }
        if (getDataConsegna() != null) {
            _hashCode += getDataConsegna().hashCode();
        }
        if (getNumeroOrdineFornitore() != null) {
            _hashCode += getNumeroOrdineFornitore().hashCode();
        }
        _hashCode += getQuantitaConsegnata();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsegnaOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ConsegnaOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceCorriere");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceCorriere"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("numeroOrdineFornitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroOrdineFornitore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaConsegnata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaConsegnata"));
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
