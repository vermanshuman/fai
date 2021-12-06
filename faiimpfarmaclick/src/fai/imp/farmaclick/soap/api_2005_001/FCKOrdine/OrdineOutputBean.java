/**
 * OrdineOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKOrdine;

public class OrdineOutputBean  extends fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.DettaglioArticoliValorizzatiOutputBean  implements java.io.Serializable {
    private fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ConsegnaOutputBean[] arrayConsegna;

    private java.lang.String noteOrdine;

    private java.lang.String numeroOrdineFornitore;

    public OrdineOutputBean() {
    }

    public OrdineOutputBean(
           int esitoServizio,
           fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ArticoloValorizzatoOutputBean[] arrayArticoli,
           fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ConsegnaOutputBean[] arrayConsegna,
           java.lang.String noteOrdine,
           java.lang.String numeroOrdineFornitore) {
        super(
            esitoServizio,
            arrayArticoli);
        this.arrayConsegna = arrayConsegna;
        this.noteOrdine = noteOrdine;
        this.numeroOrdineFornitore = numeroOrdineFornitore;
    }


    /**
     * Gets the arrayConsegna value for this OrdineOutputBean.
     * 
     * @return arrayConsegna
     */
    public fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ConsegnaOutputBean[] getArrayConsegna() {
        return arrayConsegna;
    }


    /**
     * Sets the arrayConsegna value for this OrdineOutputBean.
     * 
     * @param arrayConsegna
     */
    public void setArrayConsegna(fai.imp.farmaclick.soap.api_2005_001.FCKOrdine.ConsegnaOutputBean[] arrayConsegna) {
        this.arrayConsegna = arrayConsegna;
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


    /**
     * Gets the numeroOrdineFornitore value for this OrdineOutputBean.
     * 
     * @return numeroOrdineFornitore
     */
    public java.lang.String getNumeroOrdineFornitore() {
        return numeroOrdineFornitore;
    }


    /**
     * Sets the numeroOrdineFornitore value for this OrdineOutputBean.
     * 
     * @param numeroOrdineFornitore
     */
    public void setNumeroOrdineFornitore(java.lang.String numeroOrdineFornitore) {
        this.numeroOrdineFornitore = numeroOrdineFornitore;
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
        _equals = super.equals(obj) && 
            ((this.arrayConsegna==null && other.getArrayConsegna()==null) || 
             (this.arrayConsegna!=null &&
              java.util.Arrays.equals(this.arrayConsegna, other.getArrayConsegna()))) &&
            ((this.noteOrdine==null && other.getNoteOrdine()==null) || 
             (this.noteOrdine!=null &&
              this.noteOrdine.equals(other.getNoteOrdine()))) &&
            ((this.numeroOrdineFornitore==null && other.getNumeroOrdineFornitore()==null) || 
             (this.numeroOrdineFornitore!=null &&
              this.numeroOrdineFornitore.equals(other.getNumeroOrdineFornitore())));
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
        if (getArrayConsegna() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayConsegna());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayConsegna(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNoteOrdine() != null) {
            _hashCode += getNoteOrdine().hashCode();
        }
        if (getNumeroOrdineFornitore() != null) {
            _hashCode += getNumeroOrdineFornitore().hashCode();
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
        elemField.setFieldName("arrayConsegna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayConsegna"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ConsegnaOutputBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteOrdine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noteOrdine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOrdineFornitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroOrdineFornitore"));
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
