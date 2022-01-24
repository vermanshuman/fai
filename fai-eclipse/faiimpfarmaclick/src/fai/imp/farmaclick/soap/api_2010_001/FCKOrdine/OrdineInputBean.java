/**
 * OrdineInputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKOrdine;

public class OrdineInputBean  implements java.io.Serializable {
    private java.lang.String IDSessione;

    private fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean[] arrayArticoliInput;

    private java.lang.String codiceFornitore;

    private java.lang.String codiceTipoOrdine;

    private boolean descrizioneArticoli;

    private boolean descrizioneMotivazioneMancanza;

    private java.lang.String noteCorriere;

    private java.lang.String noteOrdine;

    private java.lang.String riferimentoOrdineFarmacia;

    private int trattamentoRisposta;

    public OrdineInputBean() {
    }

    public OrdineInputBean(
           java.lang.String IDSessione,
           fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean[] arrayArticoliInput,
           java.lang.String codiceFornitore,
           java.lang.String codiceTipoOrdine,
           boolean descrizioneArticoli,
           boolean descrizioneMotivazioneMancanza,
           java.lang.String noteCorriere,
           java.lang.String noteOrdine,
           java.lang.String riferimentoOrdineFarmacia,
           int trattamentoRisposta) {
           this.IDSessione = IDSessione;
           this.arrayArticoliInput = arrayArticoliInput;
           this.codiceFornitore = codiceFornitore;
           this.codiceTipoOrdine = codiceTipoOrdine;
           this.descrizioneArticoli = descrizioneArticoli;
           this.descrizioneMotivazioneMancanza = descrizioneMotivazioneMancanza;
           this.noteCorriere = noteCorriere;
           this.noteOrdine = noteOrdine;
           this.riferimentoOrdineFarmacia = riferimentoOrdineFarmacia;
           this.trattamentoRisposta = trattamentoRisposta;
    }


    /**
     * Gets the IDSessione value for this OrdineInputBean.
     * 
     * @return IDSessione
     */
    public java.lang.String getIDSessione() {
        return IDSessione;
    }


    /**
     * Sets the IDSessione value for this OrdineInputBean.
     * 
     * @param IDSessione
     */
    public void setIDSessione(java.lang.String IDSessione) {
        this.IDSessione = IDSessione;
    }


    /**
     * Gets the arrayArticoliInput value for this OrdineInputBean.
     * 
     * @return arrayArticoliInput
     */
    public fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean[] getArrayArticoliInput() {
        return arrayArticoliInput;
    }


    /**
     * Sets the arrayArticoliInput value for this OrdineInputBean.
     * 
     * @param arrayArticoliInput
     */
    public void setArrayArticoliInput(fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloInputBean[] arrayArticoliInput) {
        this.arrayArticoliInput = arrayArticoliInput;
    }


    /**
     * Gets the codiceFornitore value for this OrdineInputBean.
     * 
     * @return codiceFornitore
     */
    public java.lang.String getCodiceFornitore() {
        return codiceFornitore;
    }


    /**
     * Sets the codiceFornitore value for this OrdineInputBean.
     * 
     * @param codiceFornitore
     */
    public void setCodiceFornitore(java.lang.String codiceFornitore) {
        this.codiceFornitore = codiceFornitore;
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
     * Gets the descrizioneArticoli value for this OrdineInputBean.
     * 
     * @return descrizioneArticoli
     */
    public boolean isDescrizioneArticoli() {
        return descrizioneArticoli;
    }


    /**
     * Sets the descrizioneArticoli value for this OrdineInputBean.
     * 
     * @param descrizioneArticoli
     */
    public void setDescrizioneArticoli(boolean descrizioneArticoli) {
        this.descrizioneArticoli = descrizioneArticoli;
    }


    /**
     * Gets the descrizioneMotivazioneMancanza value for this OrdineInputBean.
     * 
     * @return descrizioneMotivazioneMancanza
     */
    public boolean isDescrizioneMotivazioneMancanza() {
        return descrizioneMotivazioneMancanza;
    }


    /**
     * Sets the descrizioneMotivazioneMancanza value for this OrdineInputBean.
     * 
     * @param descrizioneMotivazioneMancanza
     */
    public void setDescrizioneMotivazioneMancanza(boolean descrizioneMotivazioneMancanza) {
        this.descrizioneMotivazioneMancanza = descrizioneMotivazioneMancanza;
    }


    /**
     * Gets the noteCorriere value for this OrdineInputBean.
     * 
     * @return noteCorriere
     */
    public java.lang.String getNoteCorriere() {
        return noteCorriere;
    }


    /**
     * Sets the noteCorriere value for this OrdineInputBean.
     * 
     * @param noteCorriere
     */
    public void setNoteCorriere(java.lang.String noteCorriere) {
        this.noteCorriere = noteCorriere;
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
        _equals = true && 
            ((this.IDSessione==null && other.getIDSessione()==null) || 
             (this.IDSessione!=null &&
              this.IDSessione.equals(other.getIDSessione()))) &&
            ((this.arrayArticoliInput==null && other.getArrayArticoliInput()==null) || 
             (this.arrayArticoliInput!=null &&
              java.util.Arrays.equals(this.arrayArticoliInput, other.getArrayArticoliInput()))) &&
            ((this.codiceFornitore==null && other.getCodiceFornitore()==null) || 
             (this.codiceFornitore!=null &&
              this.codiceFornitore.equals(other.getCodiceFornitore()))) &&
            ((this.codiceTipoOrdine==null && other.getCodiceTipoOrdine()==null) || 
             (this.codiceTipoOrdine!=null &&
              this.codiceTipoOrdine.equals(other.getCodiceTipoOrdine()))) &&
            this.descrizioneArticoli == other.isDescrizioneArticoli() &&
            this.descrizioneMotivazioneMancanza == other.isDescrizioneMotivazioneMancanza() &&
            ((this.noteCorriere==null && other.getNoteCorriere()==null) || 
             (this.noteCorriere!=null &&
              this.noteCorriere.equals(other.getNoteCorriere()))) &&
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
        int _hashCode = 1;
        if (getIDSessione() != null) {
            _hashCode += getIDSessione().hashCode();
        }
        if (getArrayArticoliInput() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayArticoliInput());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayArticoliInput(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodiceFornitore() != null) {
            _hashCode += getCodiceFornitore().hashCode();
        }
        if (getCodiceTipoOrdine() != null) {
            _hashCode += getCodiceTipoOrdine().hashCode();
        }
        _hashCode += (isDescrizioneArticoli() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isDescrizioneMotivazioneMancanza() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNoteCorriere() != null) {
            _hashCode += getNoteCorriere().hashCode();
        }
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
        elemField.setFieldName("IDSessione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDSessione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayArticoliInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayArticoliInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ArticoloInputBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFornitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFornitore"));
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
        elemField.setFieldName("descrizioneArticoli");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneArticoli"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneMotivazioneMancanza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneMotivazioneMancanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteCorriere");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noteCorriere"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
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
