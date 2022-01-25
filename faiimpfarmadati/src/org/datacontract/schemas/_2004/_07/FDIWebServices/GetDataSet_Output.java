/**
 * GetDataSet_Output.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.FDIWebServices;

public class GetDataSet_Output  implements java.io.Serializable {
    private byte[] byteListFile;

    private java.lang.String codEsito;

    private java.lang.String descEsito;

    private java.lang.String outputValue;

    public GetDataSet_Output() {
    }

    public GetDataSet_Output(
           byte[] byteListFile,
           java.lang.String codEsito,
           java.lang.String descEsito,
           java.lang.String outputValue) {
           this.byteListFile = byteListFile;
           this.codEsito = codEsito;
           this.descEsito = descEsito;
           this.outputValue = outputValue;
    }


    /**
     * Gets the byteListFile value for this GetDataSet_Output.
     * 
     * @return byteListFile
     */
    public byte[] getByteListFile() {
        return byteListFile;
    }


    /**
     * Sets the byteListFile value for this GetDataSet_Output.
     * 
     * @param byteListFile
     */
    public void setByteListFile(byte[] byteListFile) {
        this.byteListFile = byteListFile;
    }


    /**
     * Gets the codEsito value for this GetDataSet_Output.
     * 
     * @return codEsito
     */
    public java.lang.String getCodEsito() {
        return codEsito;
    }


    /**
     * Sets the codEsito value for this GetDataSet_Output.
     * 
     * @param codEsito
     */
    public void setCodEsito(java.lang.String codEsito) {
        this.codEsito = codEsito;
    }


    /**
     * Gets the descEsito value for this GetDataSet_Output.
     * 
     * @return descEsito
     */
    public java.lang.String getDescEsito() {
        return descEsito;
    }


    /**
     * Sets the descEsito value for this GetDataSet_Output.
     * 
     * @param descEsito
     */
    public void setDescEsito(java.lang.String descEsito) {
        this.descEsito = descEsito;
    }


    /**
     * Gets the outputValue value for this GetDataSet_Output.
     * 
     * @return outputValue
     */
    public java.lang.String getOutputValue() {
        return outputValue;
    }


    /**
     * Sets the outputValue value for this GetDataSet_Output.
     * 
     * @param outputValue
     */
    public void setOutputValue(java.lang.String outputValue) {
        this.outputValue = outputValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDataSet_Output)) return false;
        GetDataSet_Output other = (GetDataSet_Output) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.byteListFile==null && other.getByteListFile()==null) || 
             (this.byteListFile!=null &&
              java.util.Arrays.equals(this.byteListFile, other.getByteListFile()))) &&
            ((this.codEsito==null && other.getCodEsito()==null) || 
             (this.codEsito!=null &&
              this.codEsito.equals(other.getCodEsito()))) &&
            ((this.descEsito==null && other.getDescEsito()==null) || 
             (this.descEsito!=null &&
              this.descEsito.equals(other.getDescEsito()))) &&
            ((this.outputValue==null && other.getOutputValue()==null) || 
             (this.outputValue!=null &&
              this.outputValue.equals(other.getOutputValue())));
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
        if (getByteListFile() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getByteListFile());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getByteListFile(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodEsito() != null) {
            _hashCode += getCodEsito().hashCode();
        }
        if (getDescEsito() != null) {
            _hashCode += getDescEsito().hashCode();
        }
        if (getOutputValue() != null) {
            _hashCode += getOutputValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDataSet_Output.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "GetDataSet_Output"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("byteListFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "ByteListFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codEsito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "CodEsito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descEsito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "DescEsito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outputValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "OutputValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
