/**
 * ArticoloInputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKOrdine;

public class ArticoloInputBean  implements java.io.Serializable {
    private boolean auto;

    private java.lang.String codiceProdotto;

    private java.lang.String noteRiga;

    private boolean prenotazione;

    private int quantitaRichiesta;

    private java.lang.String rifRiga;

    public ArticoloInputBean() {
    }

    public ArticoloInputBean(
           boolean auto,
           java.lang.String codiceProdotto,
           java.lang.String noteRiga,
           boolean prenotazione,
           int quantitaRichiesta,
           java.lang.String rifRiga) {
           this.auto = auto;
           this.codiceProdotto = codiceProdotto;
           this.noteRiga = noteRiga;
           this.prenotazione = prenotazione;
           this.quantitaRichiesta = quantitaRichiesta;
           this.rifRiga = rifRiga;
    }


    /**
     * Gets the auto value for this ArticoloInputBean.
     * 
     * @return auto
     */
    public boolean isAuto() {
        return auto;
    }


    /**
     * Sets the auto value for this ArticoloInputBean.
     * 
     * @param auto
     */
    public void setAuto(boolean auto) {
        this.auto = auto;
    }


    /**
     * Gets the codiceProdotto value for this ArticoloInputBean.
     * 
     * @return codiceProdotto
     */
    public java.lang.String getCodiceProdotto() {
        return codiceProdotto;
    }


    /**
     * Sets the codiceProdotto value for this ArticoloInputBean.
     * 
     * @param codiceProdotto
     */
    public void setCodiceProdotto(java.lang.String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }


    /**
     * Gets the noteRiga value for this ArticoloInputBean.
     * 
     * @return noteRiga
     */
    public java.lang.String getNoteRiga() {
        return noteRiga;
    }


    /**
     * Sets the noteRiga value for this ArticoloInputBean.
     * 
     * @param noteRiga
     */
    public void setNoteRiga(java.lang.String noteRiga) {
        this.noteRiga = noteRiga;
    }


    /**
     * Gets the prenotazione value for this ArticoloInputBean.
     * 
     * @return prenotazione
     */
    public boolean isPrenotazione() {
        return prenotazione;
    }


    /**
     * Sets the prenotazione value for this ArticoloInputBean.
     * 
     * @param prenotazione
     */
    public void setPrenotazione(boolean prenotazione) {
        this.prenotazione = prenotazione;
    }


    /**
     * Gets the quantitaRichiesta value for this ArticoloInputBean.
     * 
     * @return quantitaRichiesta
     */
    public int getQuantitaRichiesta() {
        return quantitaRichiesta;
    }


    /**
     * Sets the quantitaRichiesta value for this ArticoloInputBean.
     * 
     * @param quantitaRichiesta
     */
    public void setQuantitaRichiesta(int quantitaRichiesta) {
        this.quantitaRichiesta = quantitaRichiesta;
    }


    /**
     * Gets the rifRiga value for this ArticoloInputBean.
     * 
     * @return rifRiga
     */
    public java.lang.String getRifRiga() {
        return rifRiga;
    }


    /**
     * Sets the rifRiga value for this ArticoloInputBean.
     * 
     * @param rifRiga
     */
    public void setRifRiga(java.lang.String rifRiga) {
        this.rifRiga = rifRiga;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArticoloInputBean)) return false;
        ArticoloInputBean other = (ArticoloInputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.auto == other.isAuto() &&
            ((this.codiceProdotto==null && other.getCodiceProdotto()==null) || 
             (this.codiceProdotto!=null &&
              this.codiceProdotto.equals(other.getCodiceProdotto()))) &&
            ((this.noteRiga==null && other.getNoteRiga()==null) || 
             (this.noteRiga!=null &&
              this.noteRiga.equals(other.getNoteRiga()))) &&
            this.prenotazione == other.isPrenotazione() &&
            this.quantitaRichiesta == other.getQuantitaRichiesta() &&
            ((this.rifRiga==null && other.getRifRiga()==null) || 
             (this.rifRiga!=null &&
              this.rifRiga.equals(other.getRifRiga())));
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
        _hashCode += (isAuto() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCodiceProdotto() != null) {
            _hashCode += getCodiceProdotto().hashCode();
        }
        if (getNoteRiga() != null) {
            _hashCode += getNoteRiga().hashCode();
        }
        _hashCode += (isPrenotazione() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getQuantitaRichiesta();
        if (getRifRiga() != null) {
            _hashCode += getRifRiga().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArticoloInputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ArticoloInputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceProdotto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceProdotto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteRiga");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noteRiga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prenotazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prenotazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaRichiesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaRichiesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rifRiga");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rifRiga"));
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
