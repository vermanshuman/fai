/**
 * DownloadListinoInputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino;

public class DownloadListinoInputBean  implements java.io.Serializable {
    private java.lang.String IDSessione;

    private java.lang.String codiceFornitore;

    private java.lang.String formatoOutput;

    private java.lang.String tipoRichiesta;

    public DownloadListinoInputBean() {
    }

    public DownloadListinoInputBean(
           java.lang.String IDSessione,
           java.lang.String codiceFornitore,
           java.lang.String formatoOutput,
           java.lang.String tipoRichiesta) {
           this.IDSessione = IDSessione;
           this.codiceFornitore = codiceFornitore;
           this.formatoOutput = formatoOutput;
           this.tipoRichiesta = tipoRichiesta;
    }


    /**
     * Gets the IDSessione value for this DownloadListinoInputBean.
     * 
     * @return IDSessione
     */
    public java.lang.String getIDSessione() {
        return IDSessione;
    }


    /**
     * Sets the IDSessione value for this DownloadListinoInputBean.
     * 
     * @param IDSessione
     */
    public void setIDSessione(java.lang.String IDSessione) {
        this.IDSessione = IDSessione;
    }


    /**
     * Gets the codiceFornitore value for this DownloadListinoInputBean.
     * 
     * @return codiceFornitore
     */
    public java.lang.String getCodiceFornitore() {
        return codiceFornitore;
    }


    /**
     * Sets the codiceFornitore value for this DownloadListinoInputBean.
     * 
     * @param codiceFornitore
     */
    public void setCodiceFornitore(java.lang.String codiceFornitore) {
        this.codiceFornitore = codiceFornitore;
    }


    /**
     * Gets the formatoOutput value for this DownloadListinoInputBean.
     * 
     * @return formatoOutput
     */
    public java.lang.String getFormatoOutput() {
        return formatoOutput;
    }


    /**
     * Sets the formatoOutput value for this DownloadListinoInputBean.
     * 
     * @param formatoOutput
     */
    public void setFormatoOutput(java.lang.String formatoOutput) {
        this.formatoOutput = formatoOutput;
    }


    /**
     * Gets the tipoRichiesta value for this DownloadListinoInputBean.
     * 
     * @return tipoRichiesta
     */
    public java.lang.String getTipoRichiesta() {
        return tipoRichiesta;
    }


    /**
     * Sets the tipoRichiesta value for this DownloadListinoInputBean.
     * 
     * @param tipoRichiesta
     */
    public void setTipoRichiesta(java.lang.String tipoRichiesta) {
        this.tipoRichiesta = tipoRichiesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DownloadListinoInputBean)) return false;
        DownloadListinoInputBean other = (DownloadListinoInputBean) obj;
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
            ((this.codiceFornitore==null && other.getCodiceFornitore()==null) || 
             (this.codiceFornitore!=null &&
              this.codiceFornitore.equals(other.getCodiceFornitore()))) &&
            ((this.formatoOutput==null && other.getFormatoOutput()==null) || 
             (this.formatoOutput!=null &&
              this.formatoOutput.equals(other.getFormatoOutput()))) &&
            ((this.tipoRichiesta==null && other.getTipoRichiesta()==null) || 
             (this.tipoRichiesta!=null &&
              this.tipoRichiesta.equals(other.getTipoRichiesta())));
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
        if (getCodiceFornitore() != null) {
            _hashCode += getCodiceFornitore().hashCode();
        }
        if (getFormatoOutput() != null) {
            _hashCode += getFormatoOutput().hashCode();
        }
        if (getTipoRichiesta() != null) {
            _hashCode += getTipoRichiesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DownloadListinoInputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "DownloadListinoInputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDSessione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDSessione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFornitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFornitore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatoOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formatoOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRichiesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoRichiesta"));
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
