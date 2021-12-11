/**
 * ArticoloValorizzatoOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKOrdine;

public class ArticoloValorizzatoOutputBean  extends fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloOutputBean  implements java.io.Serializable {
    private fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.FatturazioneOutputBean[] arrayFatturazione;

    private java.lang.String codiceProdottoOmaggioAlternativo;

    private java.util.Calendar dataPrevistaConsegnaPrenotazione;

    private java.util.Calendar dataPrevistaDisponibilita;

    private java.lang.String descrizioneProdottoOmaggioAlternativo;

    private double importoNettoRiga;

    private int quantitaOmaggio;

    private int quantitaOmaggioAlternativo;

    private int quantitaPrenotata;

    public ArticoloValorizzatoOutputBean() {
    }

    public ArticoloValorizzatoOutputBean(
           fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.AllestimentoOutputBean[] arrayAllestimento,
           java.lang.String codiceMancanza,
           java.lang.String codiceProdotto,
           java.lang.String codiceProdottoSostituito,
           java.lang.String descrizioneMancanza,
           java.lang.String descrizioneProdotto,
           java.lang.String descrizioneProdottoSostituito,
           boolean prenotabile,
           int quantitaConsegnata,
           int quantitaMancante,
           int quantitaRichiesta,
           fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.FatturazioneOutputBean[] arrayFatturazione,
           java.lang.String codiceProdottoOmaggioAlternativo,
           java.util.Calendar dataPrevistaConsegnaPrenotazione,
           java.util.Calendar dataPrevistaDisponibilita,
           java.lang.String descrizioneProdottoOmaggioAlternativo,
           double importoNettoRiga,
           int quantitaOmaggio,
           int quantitaOmaggioAlternativo,
           int quantitaPrenotata) {
        super(
            arrayAllestimento,
            codiceMancanza,
            codiceProdotto,
            codiceProdottoSostituito,
            descrizioneMancanza,
            descrizioneProdotto,
            descrizioneProdottoSostituito,
            prenotabile,
            quantitaConsegnata,
            quantitaMancante,
            quantitaRichiesta);
        this.arrayFatturazione = arrayFatturazione;
        this.codiceProdottoOmaggioAlternativo = codiceProdottoOmaggioAlternativo;
        this.dataPrevistaConsegnaPrenotazione = dataPrevistaConsegnaPrenotazione;
        this.dataPrevistaDisponibilita = dataPrevistaDisponibilita;
        this.descrizioneProdottoOmaggioAlternativo = descrizioneProdottoOmaggioAlternativo;
        this.importoNettoRiga = importoNettoRiga;
        this.quantitaOmaggio = quantitaOmaggio;
        this.quantitaOmaggioAlternativo = quantitaOmaggioAlternativo;
        this.quantitaPrenotata = quantitaPrenotata;
    }


    /**
     * Gets the arrayFatturazione value for this ArticoloValorizzatoOutputBean.
     * 
     * @return arrayFatturazione
     */
    public fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.FatturazioneOutputBean[] getArrayFatturazione() {
        return arrayFatturazione;
    }


    /**
     * Sets the arrayFatturazione value for this ArticoloValorizzatoOutputBean.
     * 
     * @param arrayFatturazione
     */
    public void setArrayFatturazione(fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.FatturazioneOutputBean[] arrayFatturazione) {
        this.arrayFatturazione = arrayFatturazione;
    }


    /**
     * Gets the codiceProdottoOmaggioAlternativo value for this ArticoloValorizzatoOutputBean.
     * 
     * @return codiceProdottoOmaggioAlternativo
     */
    public java.lang.String getCodiceProdottoOmaggioAlternativo() {
        return codiceProdottoOmaggioAlternativo;
    }


    /**
     * Sets the codiceProdottoOmaggioAlternativo value for this ArticoloValorizzatoOutputBean.
     * 
     * @param codiceProdottoOmaggioAlternativo
     */
    public void setCodiceProdottoOmaggioAlternativo(java.lang.String codiceProdottoOmaggioAlternativo) {
        this.codiceProdottoOmaggioAlternativo = codiceProdottoOmaggioAlternativo;
    }


    /**
     * Gets the dataPrevistaConsegnaPrenotazione value for this ArticoloValorizzatoOutputBean.
     * 
     * @return dataPrevistaConsegnaPrenotazione
     */
    public java.util.Calendar getDataPrevistaConsegnaPrenotazione() {
        return dataPrevistaConsegnaPrenotazione;
    }


    /**
     * Sets the dataPrevistaConsegnaPrenotazione value for this ArticoloValorizzatoOutputBean.
     * 
     * @param dataPrevistaConsegnaPrenotazione
     */
    public void setDataPrevistaConsegnaPrenotazione(java.util.Calendar dataPrevistaConsegnaPrenotazione) {
        this.dataPrevistaConsegnaPrenotazione = dataPrevistaConsegnaPrenotazione;
    }


    /**
     * Gets the dataPrevistaDisponibilita value for this ArticoloValorizzatoOutputBean.
     * 
     * @return dataPrevistaDisponibilita
     */
    public java.util.Calendar getDataPrevistaDisponibilita() {
        return dataPrevistaDisponibilita;
    }


    /**
     * Sets the dataPrevistaDisponibilita value for this ArticoloValorizzatoOutputBean.
     * 
     * @param dataPrevistaDisponibilita
     */
    public void setDataPrevistaDisponibilita(java.util.Calendar dataPrevistaDisponibilita) {
        this.dataPrevistaDisponibilita = dataPrevistaDisponibilita;
    }


    /**
     * Gets the descrizioneProdottoOmaggioAlternativo value for this ArticoloValorizzatoOutputBean.
     * 
     * @return descrizioneProdottoOmaggioAlternativo
     */
    public java.lang.String getDescrizioneProdottoOmaggioAlternativo() {
        return descrizioneProdottoOmaggioAlternativo;
    }


    /**
     * Sets the descrizioneProdottoOmaggioAlternativo value for this ArticoloValorizzatoOutputBean.
     * 
     * @param descrizioneProdottoOmaggioAlternativo
     */
    public void setDescrizioneProdottoOmaggioAlternativo(java.lang.String descrizioneProdottoOmaggioAlternativo) {
        this.descrizioneProdottoOmaggioAlternativo = descrizioneProdottoOmaggioAlternativo;
    }


    /**
     * Gets the importoNettoRiga value for this ArticoloValorizzatoOutputBean.
     * 
     * @return importoNettoRiga
     */
    public double getImportoNettoRiga() {
        return importoNettoRiga;
    }


    /**
     * Sets the importoNettoRiga value for this ArticoloValorizzatoOutputBean.
     * 
     * @param importoNettoRiga
     */
    public void setImportoNettoRiga(double importoNettoRiga) {
        this.importoNettoRiga = importoNettoRiga;
    }


    /**
     * Gets the quantitaOmaggio value for this ArticoloValorizzatoOutputBean.
     * 
     * @return quantitaOmaggio
     */
    public int getQuantitaOmaggio() {
        return quantitaOmaggio;
    }


    /**
     * Sets the quantitaOmaggio value for this ArticoloValorizzatoOutputBean.
     * 
     * @param quantitaOmaggio
     */
    public void setQuantitaOmaggio(int quantitaOmaggio) {
        this.quantitaOmaggio = quantitaOmaggio;
    }


    /**
     * Gets the quantitaOmaggioAlternativo value for this ArticoloValorizzatoOutputBean.
     * 
     * @return quantitaOmaggioAlternativo
     */
    public int getQuantitaOmaggioAlternativo() {
        return quantitaOmaggioAlternativo;
    }


    /**
     * Sets the quantitaOmaggioAlternativo value for this ArticoloValorizzatoOutputBean.
     * 
     * @param quantitaOmaggioAlternativo
     */
    public void setQuantitaOmaggioAlternativo(int quantitaOmaggioAlternativo) {
        this.quantitaOmaggioAlternativo = quantitaOmaggioAlternativo;
    }


    /**
     * Gets the quantitaPrenotata value for this ArticoloValorizzatoOutputBean.
     * 
     * @return quantitaPrenotata
     */
    public int getQuantitaPrenotata() {
        return quantitaPrenotata;
    }


    /**
     * Sets the quantitaPrenotata value for this ArticoloValorizzatoOutputBean.
     * 
     * @param quantitaPrenotata
     */
    public void setQuantitaPrenotata(int quantitaPrenotata) {
        this.quantitaPrenotata = quantitaPrenotata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArticoloValorizzatoOutputBean)) return false;
        ArticoloValorizzatoOutputBean other = (ArticoloValorizzatoOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrayFatturazione==null && other.getArrayFatturazione()==null) || 
             (this.arrayFatturazione!=null &&
              java.util.Arrays.equals(this.arrayFatturazione, other.getArrayFatturazione()))) &&
            ((this.codiceProdottoOmaggioAlternativo==null && other.getCodiceProdottoOmaggioAlternativo()==null) || 
             (this.codiceProdottoOmaggioAlternativo!=null &&
              this.codiceProdottoOmaggioAlternativo.equals(other.getCodiceProdottoOmaggioAlternativo()))) &&
            ((this.dataPrevistaConsegnaPrenotazione==null && other.getDataPrevistaConsegnaPrenotazione()==null) || 
             (this.dataPrevistaConsegnaPrenotazione!=null &&
              this.dataPrevistaConsegnaPrenotazione.equals(other.getDataPrevistaConsegnaPrenotazione()))) &&
            ((this.dataPrevistaDisponibilita==null && other.getDataPrevistaDisponibilita()==null) || 
             (this.dataPrevistaDisponibilita!=null &&
              this.dataPrevistaDisponibilita.equals(other.getDataPrevistaDisponibilita()))) &&
            ((this.descrizioneProdottoOmaggioAlternativo==null && other.getDescrizioneProdottoOmaggioAlternativo()==null) || 
             (this.descrizioneProdottoOmaggioAlternativo!=null &&
              this.descrizioneProdottoOmaggioAlternativo.equals(other.getDescrizioneProdottoOmaggioAlternativo()))) &&
            this.importoNettoRiga == other.getImportoNettoRiga() &&
            this.quantitaOmaggio == other.getQuantitaOmaggio() &&
            this.quantitaOmaggioAlternativo == other.getQuantitaOmaggioAlternativo() &&
            this.quantitaPrenotata == other.getQuantitaPrenotata();
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
        if (getArrayFatturazione() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayFatturazione());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayFatturazione(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodiceProdottoOmaggioAlternativo() != null) {
            _hashCode += getCodiceProdottoOmaggioAlternativo().hashCode();
        }
        if (getDataPrevistaConsegnaPrenotazione() != null) {
            _hashCode += getDataPrevistaConsegnaPrenotazione().hashCode();
        }
        if (getDataPrevistaDisponibilita() != null) {
            _hashCode += getDataPrevistaDisponibilita().hashCode();
        }
        if (getDescrizioneProdottoOmaggioAlternativo() != null) {
            _hashCode += getDescrizioneProdottoOmaggioAlternativo().hashCode();
        }
        _hashCode += new Double(getImportoNettoRiga()).hashCode();
        _hashCode += getQuantitaOmaggio();
        _hashCode += getQuantitaOmaggioAlternativo();
        _hashCode += getQuantitaPrenotata();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArticoloValorizzatoOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ArticoloValorizzatoOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayFatturazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayFatturazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "FatturazioneOutputBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceProdottoOmaggioAlternativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceProdottoOmaggioAlternativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPrevistaConsegnaPrenotazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPrevistaConsegnaPrenotazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPrevistaDisponibilita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPrevistaDisponibilita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneProdottoOmaggioAlternativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneProdottoOmaggioAlternativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoNettoRiga");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importoNettoRiga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaOmaggio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaOmaggio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaOmaggioAlternativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaOmaggioAlternativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaPrenotata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaPrenotata"));
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
