/**
 * OrdineOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKOrdine;

public class OrdineOutputBean  implements java.io.Serializable {
    private fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloOutputBean[] arrayArticoli;

    private int delayProsRichiesta;

    private int esitoServizio;

    private java.lang.String noteOrdine;

    public OrdineOutputBean() {
    }

    public OrdineOutputBean(
           fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloOutputBean[] arrayArticoli,
           int delayProsRichiesta,
           int esitoServizio,
           java.lang.String noteOrdine) {
           this.arrayArticoli = arrayArticoli;
           this.delayProsRichiesta = delayProsRichiesta;
           this.esitoServizio = esitoServizio;
           this.noteOrdine = noteOrdine;
    }


    /**
     * Gets the arrayArticoli value for this OrdineOutputBean.
     * 
     * @return arrayArticoli
     */
    public fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloOutputBean[] getArrayArticoli() {
        return arrayArticoli;
    }


    /**
     * Sets the arrayArticoli value for this OrdineOutputBean.
     * 
     * @param arrayArticoli
     */
    public void setArrayArticoli(fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.ArticoloOutputBean[] arrayArticoli) {
        this.arrayArticoli = arrayArticoli;
    }


    /**
     * Gets the delayProsRichiesta value for this OrdineOutputBean.
     * 
     * @return delayProsRichiesta
     */
    public int getDelayProsRichiesta() {
        return delayProsRichiesta;
    }


    /**
     * Sets the delayProsRichiesta value for this OrdineOutputBean.
     * 
     * @param delayProsRichiesta
     */
    public void setDelayProsRichiesta(int delayProsRichiesta) {
        this.delayProsRichiesta = delayProsRichiesta;
    }


    /**
     * Gets the esitoServizio value for this OrdineOutputBean.
     * 
     * @return esitoServizio
     */
    public int getEsitoServizio() {
        return esitoServizio;
    }


    /**
     * Sets the esitoServizio value for this OrdineOutputBean.
     * 
     * @param esitoServizio
     */
    public void setEsitoServizio(int esitoServizio) {
        this.esitoServizio = esitoServizio;
    }


    /**
     * Gets the noteOrdine value for this OrdineOutputBean.
     * 
     * @return noteOrdine
     */
    public java.lang.String getNoteOrdine() {
        return noteOrdine;
    }


    /**
     * Sets the noteOrdine value for this OrdineOutputBean.
     * 
     * @param noteOrdine
     */
    public void setNoteOrdine(java.lang.String noteOrdine) {
        this.noteOrdine = noteOrdine;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrdineOutputBean)) return false;
        OrdineOutputBean other = (OrdineOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arrayArticoli==null && other.getArrayArticoli()==null) || 
             (this.arrayArticoli!=null &&
              java.util.Arrays.equals(this.arrayArticoli, other.getArrayArticoli()))) &&
            this.delayProsRichiesta == other.getDelayProsRichiesta() &&
            this.esitoServizio == other.getEsitoServizio() &&
            ((this.noteOrdine==null && other.getNoteOrdine()==null) || 
             (this.noteOrdine!=null &&
              this.noteOrdine.equals(other.getNoteOrdine())));
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
        if (getArrayArticoli() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayArticoli());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayArticoli(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getDelayProsRichiesta();
        _hashCode += getEsitoServizio();
        if (getNoteOrdine() != null) {
            _hashCode += getNoteOrdine().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrdineOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "OrdineOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayArticoli");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayArticoli"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ArticoloOutputBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delayProsRichiesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delayProsRichiesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esitoServizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esitoServizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteOrdine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noteOrdine"));
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
