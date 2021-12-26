/**
 * GetEnabledDataSet_Output.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.FDIWebServices;

public class GetEnabledDataSet_Output  implements java.io.Serializable {
    private java.lang.String codEsito;

    private java.lang.String descEsito;

    private org.datacontract.schemas._2004._07.FDIWebServices.SetDati[] setDatiAbilitati;

    public GetEnabledDataSet_Output() {
    }

    public GetEnabledDataSet_Output(
           java.lang.String codEsito,
           java.lang.String descEsito,
           org.datacontract.schemas._2004._07.FDIWebServices.SetDati[] setDatiAbilitati) {
           this.codEsito = codEsito;
           this.descEsito = descEsito;
           this.setDatiAbilitati = setDatiAbilitati;
    }


    /**
     * Gets the codEsito value for this GetEnabledDataSet_Output.
     * 
     * @return codEsito
     */
    public java.lang.String getCodEsito() {
        return codEsito;
    }


    /**
     * Sets the codEsito value for this GetEnabledDataSet_Output.
     * 
     * @param codEsito
     */
    public void setCodEsito(java.lang.String codEsito) {
        this.codEsito = codEsito;
    }


    /**
     * Gets the descEsito value for this GetEnabledDataSet_Output.
     * 
     * @return descEsito
     */
    public java.lang.String getDescEsito() {
        return descEsito;
    }


    /**
     * Sets the descEsito value for this GetEnabledDataSet_Output.
     * 
     * @param descEsito
     */
    public void setDescEsito(java.lang.String descEsito) {
        this.descEsito = descEsito;
    }


    /**
     * Gets the setDatiAbilitati value for this GetEnabledDataSet_Output.
     * 
     * @return setDatiAbilitati
     */
    public org.datacontract.schemas._2004._07.FDIWebServices.SetDati[] getSetDatiAbilitati() {
        return setDatiAbilitati;
    }


    /**
     * Sets the setDatiAbilitati value for this GetEnabledDataSet_Output.
     * 
     * @param setDatiAbilitati
     */
    public void setSetDatiAbilitati(org.datacontract.schemas._2004._07.FDIWebServices.SetDati[] setDatiAbilitati) {
        this.setDatiAbilitati = setDatiAbilitati;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetEnabledDataSet_Output)) return false;
        GetEnabledDataSet_Output other = (GetEnabledDataSet_Output) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codEsito==null && other.getCodEsito()==null) || 
             (this.codEsito!=null &&
              this.codEsito.equals(other.getCodEsito()))) &&
            ((this.descEsito==null && other.getDescEsito()==null) || 
             (this.descEsito!=null &&
              this.descEsito.equals(other.getDescEsito()))) &&
            ((this.setDatiAbilitati==null && other.getSetDatiAbilitati()==null) || 
             (this.setDatiAbilitati!=null &&
              java.util.Arrays.equals(this.setDatiAbilitati, other.getSetDatiAbilitati())));
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
        if (getCodEsito() != null) {
            _hashCode += getCodEsito().hashCode();
        }
        if (getDescEsito() != null) {
            _hashCode += getDescEsito().hashCode();
        }
        if (getSetDatiAbilitati() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSetDatiAbilitati());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSetDatiAbilitati(), i);
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
        new org.apache.axis.description.TypeDesc(GetEnabledDataSet_Output.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "GetEnabledDataSet_Output"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("setDatiAbilitati");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "SetDatiAbilitati"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "SetDati"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FDIWebServices", "SetDati"));
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
