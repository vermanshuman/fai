/**
 * ArticoloOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKOrdine;

public class ArticoloOutputBean  implements java.io.Serializable {
    private fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.AllestimentoOutputBean[] arrayAllestimento;

    private java.lang.String codiceMancanza;

    private java.lang.String codiceProdotto;

    private java.lang.String codiceProdottoSostituito;

    private java.lang.String descrizioneMancanza;

    private java.lang.String descrizioneProdotto;

    private java.lang.String descrizioneProdottoSostituito;

    private boolean prenotabile;

    private int quantitaConsegnata;

    private int quantitaMancante;

    private int quantitaRichiesta;

    public ArticoloOutputBean() {
    }

    public ArticoloOutputBean(
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
           int quantitaRichiesta) {
           this.arrayAllestimento = arrayAllestimento;
           this.codiceMancanza = codiceMancanza;
           this.codiceProdotto = codiceProdotto;
           this.codiceProdottoSostituito = codiceProdottoSostituito;
           this.descrizioneMancanza = descrizioneMancanza;
           this.descrizioneProdotto = descrizioneProdotto;
           this.descrizioneProdottoSostituito = descrizioneProdottoSostituito;
           this.prenotabile = prenotabile;
           this.quantitaConsegnata = quantitaConsegnata;
           this.quantitaMancante = quantitaMancante;
           this.quantitaRichiesta = quantitaRichiesta;
    }


    /**
     * Gets the arrayAllestimento value for this ArticoloOutputBean.
     * 
     * @return arrayAllestimento
     */
    public fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.AllestimentoOutputBean[] getArrayAllestimento() {
        return arrayAllestimento;
    }


    /**
     * Sets the arrayAllestimento value for this ArticoloOutputBean.
     * 
     * @param arrayAllestimento
     */
    public void setArrayAllestimento(fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.AllestimentoOutputBean[] arrayAllestimento) {
        this.arrayAllestimento = arrayAllestimento;
    }


    /**
     * Gets the codiceMancanza value for this ArticoloOutputBean.
     * 
     * @return codiceMancanza
     */
    public java.lang.String getCodiceMancanza() {
        return codiceMancanza;
    }


    /**
     * Sets the codiceMancanza value for this ArticoloOutputBean.
     * 
     * @param codiceMancanza
     */
    public void setCodiceMancanza(java.lang.String codiceMancanza) {
        this.codiceMancanza = codiceMancanza;
    }


    /**
     * Gets the codiceProdotto value for this ArticoloOutputBean.
     * 
     * @return codiceProdotto
     */
    public java.lang.String getCodiceProdotto() {
        return codiceProdotto;
    }


    /**
     * Sets the codiceProdotto value for this ArticoloOutputBean.
     * 
     * @param codiceProdotto
     */
    public void setCodiceProdotto(java.lang.String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }


    /**
     * Gets the codiceProdottoSostituito value for this ArticoloOutputBean.
     * 
     * @return codiceProdottoSostituito
     */
    public java.lang.String getCodiceProdottoSostituito() {
        return codiceProdottoSostituito;
    }


    /**
     * Sets the codiceProdottoSostituito value for this ArticoloOutputBean.
     * 
     * @param codiceProdottoSostituito
     */
    public void setCodiceProdottoSostituito(java.lang.String codiceProdottoSostituito) {
        this.codiceProdottoSostituito = codiceProdottoSostituito;
    }


    /**
     * Gets the descrizioneMancanza value for this ArticoloOutputBean.
     * 
     * @return descrizioneMancanza
     */
    public java.lang.String getDescrizioneMancanza() {
        return descrizioneMancanza;
    }


    /**
     * Sets the descrizioneMancanza value for this ArticoloOutputBean.
     * 
     * @param descrizioneMancanza
     */
    public void setDescrizioneMancanza(java.lang.String descrizioneMancanza) {
        this.descrizioneMancanza = descrizioneMancanza;
    }


    /**
     * Gets the descrizioneProdotto value for this ArticoloOutputBean.
     * 
     * @return descrizioneProdotto
     */
    public java.lang.String getDescrizioneProdotto() {
        return descrizioneProdotto;
    }


    /**
     * Sets the descrizioneProdotto value for this ArticoloOutputBean.
     * 
     * @param descrizioneProdotto
     */
    public void setDescrizioneProdotto(java.lang.String descrizioneProdotto) {
        this.descrizioneProdotto = descrizioneProdotto;
    }


    /**
     * Gets the descrizioneProdottoSostituito value for this ArticoloOutputBean.
     * 
     * @return descrizioneProdottoSostituito
     */
    public java.lang.String getDescrizioneProdottoSostituito() {
        return descrizioneProdottoSostituito;
    }


    /**
     * Sets the descrizioneProdottoSostituito value for this ArticoloOutputBean.
     * 
     * @param descrizioneProdottoSostituito
     */
    public void setDescrizioneProdottoSostituito(java.lang.String descrizioneProdottoSostituito) {
        this.descrizioneProdottoSostituito = descrizioneProdottoSostituito;
    }


    /**
     * Gets the prenotabile value for this ArticoloOutputBean.
     * 
     * @return prenotabile
     */
    public boolean isPrenotabile() {
        return prenotabile;
    }


    /**
     * Sets the prenotabile value for this ArticoloOutputBean.
     * 
     * @param prenotabile
     */
    public void setPrenotabile(boolean prenotabile) {
        this.prenotabile = prenotabile;
    }


    /**
     * Gets the quantitaConsegnata value for this ArticoloOutputBean.
     * 
     * @return quantitaConsegnata
     */
    public int getQuantitaConsegnata() {
        return quantitaConsegnata;
    }


    /**
     * Sets the quantitaConsegnata value for this ArticoloOutputBean.
     * 
     * @param quantitaConsegnata
     */
    public void setQuantitaConsegnata(int quantitaConsegnata) {
        this.quantitaConsegnata = quantitaConsegnata;
    }


    /**
     * Gets the quantitaMancante value for this ArticoloOutputBean.
     * 
     * @return quantitaMancante
     */
    public int getQuantitaMancante() {
        return quantitaMancante;
    }


    /**
     * Sets the quantitaMancante value for this ArticoloOutputBean.
     * 
     * @param quantitaMancante
     */
    public void setQuantitaMancante(int quantitaMancante) {
        this.quantitaMancante = quantitaMancante;
    }


    /**
     * Gets the quantitaRichiesta value for this ArticoloOutputBean.
     * 
     * @return quantitaRichiesta
     */
    public int getQuantitaRichiesta() {
        return quantitaRichiesta;
    }


    /**
     * Sets the quantitaRichiesta value for this ArticoloOutputBean.
     * 
     * @param quantitaRichiesta
     */
    public void setQuantitaRichiesta(int quantitaRichiesta) {
        this.quantitaRichiesta = quantitaRichiesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArticoloOutputBean)) return false;
        ArticoloOutputBean other = (ArticoloOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arrayAllestimento==null && other.getArrayAllestimento()==null) || 
             (this.arrayAllestimento!=null &&
              java.util.Arrays.equals(this.arrayAllestimento, other.getArrayAllestimento()))) &&
            ((this.codiceMancanza==null && other.getCodiceMancanza()==null) || 
             (this.codiceMancanza!=null &&
              this.codiceMancanza.equals(other.getCodiceMancanza()))) &&
            ((this.codiceProdotto==null && other.getCodiceProdotto()==null) || 
             (this.codiceProdotto!=null &&
              this.codiceProdotto.equals(other.getCodiceProdotto()))) &&
            ((this.codiceProdottoSostituito==null && other.getCodiceProdottoSostituito()==null) || 
             (this.codiceProdottoSostituito!=null &&
              this.codiceProdottoSostituito.equals(other.getCodiceProdottoSostituito()))) &&
            ((this.descrizioneMancanza==null && other.getDescrizioneMancanza()==null) || 
             (this.descrizioneMancanza!=null &&
              this.descrizioneMancanza.equals(other.getDescrizioneMancanza()))) &&
            ((this.descrizioneProdotto==null && other.getDescrizioneProdotto()==null) || 
             (this.descrizioneProdotto!=null &&
              this.descrizioneProdotto.equals(other.getDescrizioneProdotto()))) &&
            ((this.descrizioneProdottoSostituito==null && other.getDescrizioneProdottoSostituito()==null) || 
             (this.descrizioneProdottoSostituito!=null &&
              this.descrizioneProdottoSostituito.equals(other.getDescrizioneProdottoSostituito()))) &&
            this.prenotabile == other.isPrenotabile() &&
            this.quantitaConsegnata == other.getQuantitaConsegnata() &&
            this.quantitaMancante == other.getQuantitaMancante() &&
            this.quantitaRichiesta == other.getQuantitaRichiesta();
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
        if (getArrayAllestimento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayAllestimento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayAllestimento(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodiceMancanza() != null) {
            _hashCode += getCodiceMancanza().hashCode();
        }
        if (getCodiceProdotto() != null) {
            _hashCode += getCodiceProdotto().hashCode();
        }
        if (getCodiceProdottoSostituito() != null) {
            _hashCode += getCodiceProdottoSostituito().hashCode();
        }
        if (getDescrizioneMancanza() != null) {
            _hashCode += getDescrizioneMancanza().hashCode();
        }
        if (getDescrizioneProdotto() != null) {
            _hashCode += getDescrizioneProdotto().hashCode();
        }
        if (getDescrizioneProdottoSostituito() != null) {
            _hashCode += getDescrizioneProdottoSostituito().hashCode();
        }
        _hashCode += (isPrenotabile() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getQuantitaConsegnata();
        _hashCode += getQuantitaMancante();
        _hashCode += getQuantitaRichiesta();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ArticoloOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bean.farmaclick.infarma.it", "ArticoloOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayAllestimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayAllestimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "AllestimentoOutputBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceMancanza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceMancanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceProdotto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceProdotto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceProdottoSostituito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceProdottoSostituito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneMancanza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneMancanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneProdotto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneProdotto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneProdottoSostituito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneProdottoSostituito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prenotabile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prenotabile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaConsegnata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaConsegnata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaMancante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaMancante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantitaRichiesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantitaRichiesta"));
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
