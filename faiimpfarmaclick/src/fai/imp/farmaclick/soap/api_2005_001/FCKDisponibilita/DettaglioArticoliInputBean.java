/**
 * DettaglioArticoliInputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita;

public class DettaglioArticoliInputBean  extends fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.CommonInputBean  implements java.io.Serializable {
    private fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloInputBean[] arrayArticoliInput;

    private boolean descrizioneArticoli;

    private boolean descrizioneMotivazioneMancanza;

    private boolean indicazioneDepositoAllestimento;

    public DettaglioArticoliInputBean() {
    }

    public DettaglioArticoliInputBean(
           java.lang.String IDSessione,
           java.lang.String codiceFornitore,
           fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloInputBean[] arrayArticoliInput,
           boolean descrizioneArticoli,
           boolean descrizioneMotivazioneMancanza,
           boolean indicazioneDepositoAllestimento) {
        super(
            IDSessione,
            codiceFornitore);
        this.arrayArticoliInput = arrayArticoliInput;
        this.descrizioneArticoli = descrizioneArticoli;
        this.descrizioneMotivazioneMancanza = descrizioneMotivazioneMancanza;
        this.indicazioneDepositoAllestimento = indicazioneDepositoAllestimento;
    }


    /**
     * Gets the arrayArticoliInput value for this DettaglioArticoliInputBean.
     * 
     * @return arrayArticoliInput
     */
    public fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloInputBean[] getArrayArticoliInput() {
        return arrayArticoliInput;
    }


    /**
     * Sets the arrayArticoliInput value for this DettaglioArticoliInputBean.
     * 
     * @param arrayArticoliInput
     */
    public void setArrayArticoliInput(fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloInputBean[] arrayArticoliInput) {
        this.arrayArticoliInput = arrayArticoliInput;
    }


    /**
     * Gets the descrizioneArticoli value for this DettaglioArticoliInputBean.
     * 
     * @return descrizioneArticoli
     */
    public boolean isDescrizioneArticoli() {
        return descrizioneArticoli;
    }


    /**
     * Sets the descrizioneArticoli value for this DettaglioArticoliInputBean.
     * 
     * @param descrizioneArticoli
     */
    public void setDescrizioneArticoli(boolean descrizioneArticoli) {
        this.descrizioneArticoli = descrizioneArticoli;
    }


    /**
     * Gets the descrizioneMotivazioneMancanza value for this DettaglioArticoliInputBean.
     * 
     * @return descrizioneMotivazioneMancanza
     */
    public boolean isDescrizioneMotivazioneMancanza() {
        return descrizioneMotivazioneMancanza;
    }


    /**
     * Sets the descrizioneMotivazioneMancanza value for this DettaglioArticoliInputBean.
     * 
     * @param descrizioneMotivazioneMancanza
     */
    public void setDescrizioneMotivazioneMancanza(boolean descrizioneMotivazioneMancanza) {
        this.descrizioneMotivazioneMancanza = descrizioneMotivazioneMancanza;
    }


    /**
     * Gets the indicazioneDepositoAllestimento value for this DettaglioArticoliInputBean.
     * 
     * @return indicazioneDepositoAllestimento
     */
    public boolean isIndicazioneDepositoAllestimento() {
        return indicazioneDepositoAllestimento;
    }


    /**
     * Sets the indicazioneDepositoAllestimento value for this DettaglioArticoliInputBean.
     * 
     * @param indicazioneDepositoAllestimento
     */
    public void setIndicazioneDepositoAllestimento(boolean indicazioneDepositoAllestimento) {
        this.indicazioneDepositoAllestimento = indicazioneDepositoAllestimento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DettaglioArticoliInputBean)) return false;
        DettaglioArticoliInputBean other = (DettaglioArticoliInputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrayArticoliInput==null && other.getArrayArticoliInput()==null) || 
             (this.arrayArticoliInput!=null &&
              java.util.Arrays.equals(this.arrayArticoliInput, other.getArrayArticoliInput()))) &&
            this.descrizioneArticoli == other.isDescrizioneArticoli() &&
            this.descrizioneMotivazioneMancanza == other.isDescrizioneMotivazioneMancanza() &&
            this.indicazioneDepositoAllestimento == other.isIndicazioneDepositoAllestimento();
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
        _hashCode += (isDescrizioneArticoli() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isDescrizioneMotivazioneMancanza() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIndicazioneDepositoAllestimento() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DettaglioArticoliInputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "DettaglioArticoliInputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayArticoliInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayArticoliInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ArticoloInputBean"));
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
        elemField.setFieldName("indicazioneDepositoAllestimento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicazioneDepositoAllestimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
