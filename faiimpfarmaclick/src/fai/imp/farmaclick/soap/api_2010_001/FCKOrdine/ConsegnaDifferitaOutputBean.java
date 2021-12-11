/**
 * ConsegnaDifferitaOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKOrdine;

public class ConsegnaDifferitaOutputBean  implements java.io.Serializable {
    private java.lang.String codiceConsegna;

    private java.lang.String codiceFornitore;

    private java.util.Calendar dataConsegna;

    private java.util.Calendar dataFineValidita;

    private int disponibilitaProdotto;

    private java.lang.String note;

    private int quantita;

    public ConsegnaDifferitaOutputBean() {
    }

    public ConsegnaDifferitaOutputBean(
           java.lang.String codiceConsegna,
           java.lang.String codiceFornitore,
           java.util.Calendar dataConsegna,
           java.util.Calendar dataFineValidita,
           int disponibilitaProdotto,
           java.lang.String note,
           int quantita) {
           this.codiceConsegna = codiceConsegna;
           this.codiceFornitore = codiceFornitore;
           this.dataConsegna = dataConsegna;
           this.dataFineValidita = dataFineValidita;
           this.disponibilitaProdotto = disponibilitaProdotto;
           this.note = note;
           this.quantita = quantita;
    }


    /**
     * Gets the codiceConsegna value for this ConsegnaDifferitaOutputBean.
     * 
     * @return codiceConsegna
     */
    public java.lang.String getCodiceConsegna() {
        return codiceConsegna;
    }


    /**
     * Sets the codiceConsegna value for this ConsegnaDifferitaOutputBean.
     * 
     * @param codiceConsegna
     */
    public void setCodiceConsegna(java.lang.String codiceConsegna) {
        this.codiceConsegna = codiceConsegna;
    }


    /**
     * Gets the codiceFornitore value for this ConsegnaDifferitaOutputBean.
     * 
     * @return codiceFornitore
     */
    public java.lang.String getCodiceFornitore() {
        return codiceFornitore;
    }


    /**
     * Sets the codiceFornitore value for this ConsegnaDifferitaOutputBean.
     * 
     * @param codiceFornitore
     */
    public void setCodiceFornitore(java.lang.String codiceFornitore) {
        this.codiceFornitore = codiceFornitore;
    }


    /**
     * Gets the dataConsegna value for this ConsegnaDifferitaOutputBean.
     * 
     * @return dataConsegna
     */
    public java.util.Calendar getDataConsegna() {
        return dataConsegna;
    }


    /**
     * Sets the dataConsegna value for this ConsegnaDifferitaOutputBean.
     * 
     * @param dataConsegna
     */
    public void setDataConsegna(java.util.Calendar dataConsegna) {
        this.dataConsegna = dataConsegna;
    }


    /**
     * Gets the dataFineValidita value for this ConsegnaDifferitaOutputBean.
     * 
     * @return dataFineValidita
     */
    public java.util.Calendar getDataFineValidita() {
        return dataFineValidita;
    }


    /**
     * Sets the dataFineValidita value for this ConsegnaDifferitaOutputBean.
     * 
     * @param dataFineValidita
     */
    public void setDataFineValidita(java.util.Calendar dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }


    /**
     * Gets the disponibilitaProdotto value for this ConsegnaDifferitaOutputBean.
     * 
     * @return disponibilitaProdotto
     */
    public int getDisponibilitaProdotto() {
        return disponibilitaProdotto;
    }


    /**
     * Sets the disponibilitaProdotto value for this ConsegnaDifferitaOutputBean.
     * 
     * @param disponibilitaProdotto
     */
    public void setDisponibilitaProdotto(int disponibilitaProdotto) {
        this.disponibilitaProdotto = disponibilitaProdotto;
    }


    /**
     * Gets the note value for this ConsegnaDifferitaOutputBean.
     * 
     * @return note
     */
    public java.lang.String getNote() {
        return note;
    }


    /**
     * Sets the note value for this ConsegnaDifferitaOutputBean.
     * 
     * @param note
     */
    public void setNote(java.lang.String note) {
        this.note = note;
    }


    /**
     * Gets the quantita value for this ConsegnaDifferitaOutputBean.
     * 
     * @return quantita
     */
    public int getQuantita() {
        return quantita;
    }


    /**
     * Sets the quantita value for this ConsegnaDifferitaOutputBean.
     * 
     * @param quantita
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsegnaDifferitaOutputBean)) return false;
        ConsegnaDifferitaOutputBean other = (ConsegnaDifferitaOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceConsegna==null && other.getCodiceConsegna()==null) || 
             (this.codiceConsegna!=null &&
              this.codiceConsegna.equals(other.getCodiceConsegna()))) &&
            ((this.codiceFornitore==null && other.getCodiceFornitore()==null) || 
             (this.codiceFornitore!=null &&
              this.codiceFornitore.equals(other.getCodiceFornitore()))) &&
            ((this.dataConsegna==null && other.getDataConsegna()==null) || 
             (this.dataConsegna!=null &&
              this.dataConsegna.equals(other.getDataConsegna()))) &&
            ((this.dataFineValidita==null && other.getDataFineValidita()==null) || 
             (this.dataFineValidita!=null &&
              this.dataFineValidita.equals(other.getDataFineValidita()))) &&
            this.disponibilitaProdotto == other.getDisponibilitaProdotto() &&
            ((this.note==null && other.getNote()==null) || 
             (this.note!=null &&
              this.note.equals(other.getNote()))) &&
            this.quantita == other.getQuantita();
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
        if (getCodiceConsegna() != null) {
            _hashCode += getCodiceConsegna().hashCode();
        }
        if (getCodiceFornitore() != null) {
            _hashCode += getCodiceFornitore().hashCode();
        }
        if (getDataConsegna() != null) {
            _hashCode += getDataConsegna().hashCode();
        }
        if (getDataFineValidita() != null) {
            _hashCode += getDataFineValidita().hashCode();
        }
        _hashCode += getDisponibilitaProdotto();
        if (getNote() != null) {
            _hashCode += getNote().hashCode();
        }
        _hashCode += getQuantita();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsegnaDifferitaOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ConsegnaDifferitaOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceConsegna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceConsegna"));
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
        elemField.setFieldName("dataFineValidita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataFineValidita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disponibilitaProdotto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disponibilitaProdotto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("note");
        elemField.setXmlName(new javax.xml.namespace.QName("", "note"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantita"));
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
