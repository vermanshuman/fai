/**
 * DettaglioArticoliOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita;

public class DettaglioArticoliOutputBean  extends fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.CommonOutputBean  implements java.io.Serializable {
    private fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloOutputBean[] arrayArticoli;

    public DettaglioArticoliOutputBean() {
    }

    public DettaglioArticoliOutputBean(
           int esitoServizio,
           fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloOutputBean[] arrayArticoli) {
        super(
            esitoServizio);
        this.arrayArticoli = arrayArticoli;
    }


    /**
     * Gets the arrayArticoli value for this DettaglioArticoliOutputBean.
     * 
     * @return arrayArticoli
     */
    public fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloOutputBean[] getArrayArticoli() {
        return arrayArticoli;
    }


    /**
     * Sets the arrayArticoli value for this DettaglioArticoliOutputBean.
     * 
     * @param arrayArticoli
     */
    public void setArrayArticoli(fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloOutputBean[] arrayArticoli) {
        this.arrayArticoli = arrayArticoli;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DettaglioArticoliOutputBean)) return false;
        DettaglioArticoliOutputBean other = (DettaglioArticoliOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.arrayArticoli==null && other.getArrayArticoli()==null) || 
             (this.arrayArticoli!=null &&
              java.util.Arrays.equals(this.arrayArticoli, other.getArrayArticoli())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DettaglioArticoliOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "DettaglioArticoliOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayArticoli");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayArticoli"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ArticoloOutputBean"));
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
