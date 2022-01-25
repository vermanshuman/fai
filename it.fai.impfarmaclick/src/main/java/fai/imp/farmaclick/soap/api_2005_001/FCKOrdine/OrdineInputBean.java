/**
 * OrdineInputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKOrdine;

public class OrdineInputBean  extends fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.DettaglioArticoliInputBean  implements java.io.Serializable {
    private java.lang.String codiceFatturazione;

    private java.lang.String codiceTipoOrdine;

    private boolean indicazioneDatiConsegna;

    private java.lang.String noteOrdine;

    private java.lang.String riferimentoOrdineFarmacia;

    private int trattamentoRisposta;

    public OrdineInputBean() {
    }

    public OrdineInputBean(
           java.lang.String IDSessione,
           java.lang.String codiceFornitore,
           fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloInputBean[] arrayArticoliInput,
           boolean descrizioneArticoli,
           boolean descrizioneMotivazioneMancanza,
           boolean indicazioneDepositoAllestimento,
           java.lang.String codiceFatturazione,
           java.lang.String codiceTipoOrdine,
           boolean indicazioneDatiConsegna,
           java.lang.String noteOrdine,
           java.lang.String riferimentoOrdineFarmacia,
           int trattamentoRisposta) {
        super(
            IDSessione,
            codiceFornitore,
            arrayArticoliInput,
            descrizioneArticoli,
            descrizioneMotivazioneMancanza,
            indicazioneDepositoAllestimento);
        this.codiceFatturazione = codiceFatturazione;
        this.codiceTipoOrdine = codiceTipoOrdine;
        this.indicazioneDatiConsegna = indicazioneDatiConsegna;
        this.noteOrdine = noteOrdine;
        this.riferimentoOrdineFarmacia = riferimentoOrdineFarmacia;
        this.trattamentoRisposta = trattamentoRisposta;
    }


    /**
     * Gets the codiceFatturazione value for this OrdineInputBean.
     * 
     * @return codiceFatturazione
     */
    public java.lang.String getCodiceFatturazione() {
        return codiceFatturazione;
    }


    /**
     * Sets the codiceFatturazione value for this OrdineInputBean.
     * 
     * @param codiceFatturazione
     */
    public void setCodiceFatturazione(java.lang.String codiceFatturazione) {
        this.codiceFatturazione = codiceFatturazione;
    }


    /**
     * Gets the codiceTipoOrdine value for this OrdineInputBean.
     * 
     * @return codiceTipoOrdine
     */
    public java.lang.String getCodiceTipoOrdine() {
        return codiceTipoOrdine;
    }


    /**
     * Sets the codiceTipoOrdine value for this OrdineInputBean.
     * 
     * @param codiceTipoOrdine
     */
    public void setCodiceTipoOrdine(java.lang.String codiceTipoOrdine) {
        this.codiceTipoOrdine = codiceTipoOrdine;
    }


    /**
     * Gets the indicazioneDatiConsegna value for this OrdineInputBean.
     * 
     * @return indicazioneDatiConsegna
     */
    public boolean isIndicazioneDatiConsegna() {
        return indicazioneDatiConsegna;
    }


    /**
     * Sets the indicazioneDatiConsegna value for this OrdineInputBean.
     * 
     * @param indicazioneDatiConsegna
     */
    public void setIndicazioneDatiConsegna(boolean indicazioneDatiConsegna) {
        this.indicazioneDatiConsegna = indicazioneDatiConsegna;
    }


    /**
     * Gets the noteOrdine value for this OrdineInputBean.
     * 
     * @return noteOrdine
     */
    public java.lang.String getNoteOrdine() {
        return noteOrdine;
    }


    /**
     * Sets the noteOrdine value for this OrdineInputBean.
     * 
     * @param noteOrdine
     */
    public void setNoteOrdine(java.lang.String noteOrdine) {
        this.noteOrdine = noteOrdine;
    }


    /**
     * Gets the riferimentoOrdineFarmacia value for this OrdineInputBean.
     * 
     * @return riferimentoOrdineFarmacia
     */
    public java.lang.String getRiferimentoOrdineFarmacia() {
        return riferimentoOrdineFarmacia;
    }


    /**
     * Sets the riferimentoOrdineFarmacia value for this OrdineInputBean.
     * 
     * @param riferimentoOrdineFarmacia
     */
    public void setRiferimentoOrdineFarmacia(java.lang.String riferimentoOrdineFarmacia) {
        this.riferimentoOrdineFarmacia = riferimentoOrdineFarmacia;
    }


    /**
     * Gets the trattamentoRisposta value for this OrdineInputBean.
     * 
     * @return trattamentoRisposta
     */
    public int getTrattamentoRisposta() {
        return trattamentoRisposta;
    }


    /**
     * Sets the trattamentoRisposta value for this OrdineInputBean.
     * 
     * @param trattamentoRisposta
     */
    public void setTrattamentoRisposta(int trattamentoRisposta) {
        this.trattamentoRisposta = trattamentoRisposta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrdineInputBean)) return false;
        OrdineInputBean other = (OrdineInputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codiceFatturazione==null && other.getCodiceFatturazione()==null) || 
             (this.codiceFatturazione!=null &&
              this.codiceFatturazione.equals(other.getCodiceFatturazione()))) &&
            ((this.codiceTipoOrdine==null && other.getCodiceTipoOrdine()==null) || 
             (this.codiceTipoOrdine!=null &&
              this.codiceTipoOrdine.equals(other.getCodiceTipoOrdine()))) &&
            this.indicazioneDatiConsegna == other.isIndicazioneDatiConsegna() &&
            ((this.noteOrdine==null && other.getNoteOrdine()==null) || 
             (this.noteOrdine!=null &&
              this.noteOrdine.equals(other.getNoteOrdine()))) &&
            ((this.riferimentoOrdineFarmacia==null && other.getRiferimentoOrdineFarmacia()==null) || 
             (this.riferimentoOrdineFarmacia!=null &&
              this.riferimentoOrdineFarmacia.equals(other.getRiferimentoOrdineFarmacia()))) &&
            this.trattamentoRisposta == other.getTrattamentoRisposta();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getCodiceFatturazione() != null) {
            _hashCode += getCodiceFatturazione().hashCode();
        }
        if (getCodiceTipoOrdine() != null) {
            _hashCode += getCodiceTipoOrdine().hashCode();
        }
        _hashCode += (isIndicazioneDatiConsegna() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNoteOrdine() != null) {
            _hashCode += getNoteOrdine().hashCode();
        }
        if (getRiferimentoOrdineFarmacia() != null) {
            _hashCode += getRiferimentoOrdineFarmacia().hashCode();
        }
        _hashCode += getTrattamentoRisposta();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrdineInputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "OrdineInputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFatturazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFatturazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceTipoOrdine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceTipoOrdine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicazioneDatiConsegna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicazioneDatiConsegna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteOrdine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noteOrdine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("riferimentoOrdineFarmacia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "riferimentoOrdineFarmacia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trattamentoRisposta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trattamentoRisposta"));
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
