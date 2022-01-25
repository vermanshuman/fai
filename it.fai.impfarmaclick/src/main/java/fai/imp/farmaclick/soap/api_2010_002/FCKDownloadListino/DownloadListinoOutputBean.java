/**
 * DownloadListinoOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino;

public class DownloadListinoOutputBean  implements java.io.Serializable {
    private java.util.Calendar dataOraGenerazioneListino;

    private int delayProsRichiesta;

    private int esitoServizio;

    private java.lang.String tipoRisposta;

    private java.lang.String urlConfermaDownload;

    private java.lang.String urlDownload;

    public DownloadListinoOutputBean() {
    }

    public DownloadListinoOutputBean(
           java.util.Calendar dataOraGenerazioneListino,
           int delayProsRichiesta,
           int esitoServizio,
           java.lang.String tipoRisposta,
           java.lang.String urlConfermaDownload,
           java.lang.String urlDownload) {
           this.dataOraGenerazioneListino = dataOraGenerazioneListino;
           this.delayProsRichiesta = delayProsRichiesta;
           this.esitoServizio = esitoServizio;
           this.tipoRisposta = tipoRisposta;
           this.urlConfermaDownload = urlConfermaDownload;
           this.urlDownload = urlDownload;
    }


    /**
     * Gets the dataOraGenerazioneListino value for this DownloadListinoOutputBean.
     * 
     * @return dataOraGenerazioneListino
     */
    public java.util.Calendar getDataOraGenerazioneListino() {
        return dataOraGenerazioneListino;
    }


    /**
     * Sets the dataOraGenerazioneListino value for this DownloadListinoOutputBean.
     * 
     * @param dataOraGenerazioneListino
     */
    public void setDataOraGenerazioneListino(java.util.Calendar dataOraGenerazioneListino) {
        this.dataOraGenerazioneListino = dataOraGenerazioneListino;
    }


    /**
     * Gets the delayProsRichiesta value for this DownloadListinoOutputBean.
     * 
     * @return delayProsRichiesta
     */
    public int getDelayProsRichiesta() {
        return delayProsRichiesta;
    }


    /**
     * Sets the delayProsRichiesta value for this DownloadListinoOutputBean.
     * 
     * @param delayProsRichiesta
     */
    public void setDelayProsRichiesta(int delayProsRichiesta) {
        this.delayProsRichiesta = delayProsRichiesta;
    }


    /**
     * Gets the esitoServizio value for this DownloadListinoOutputBean.
     * 
     * @return esitoServizio
     */
    public int getEsitoServizio() {
        return esitoServizio;
    }


    /**
     * Sets the esitoServizio value for this DownloadListinoOutputBean.
     * 
     * @param esitoServizio
     */
    public void setEsitoServizio(int esitoServizio) {
        this.esitoServizio = esitoServizio;
    }


    /**
     * Gets the tipoRisposta value for this DownloadListinoOutputBean.
     * 
     * @return tipoRisposta
     */
    public java.lang.String getTipoRisposta() {
        return tipoRisposta;
    }


    /**
     * Sets the tipoRisposta value for this DownloadListinoOutputBean.
     * 
     * @param tipoRisposta
     */
    public void setTipoRisposta(java.lang.String tipoRisposta) {
        this.tipoRisposta = tipoRisposta;
    }


    /**
     * Gets the urlConfermaDownload value for this DownloadListinoOutputBean.
     * 
     * @return urlConfermaDownload
     */
    public java.lang.String getUrlConfermaDownload() {
        return urlConfermaDownload;
    }


    /**
     * Sets the urlConfermaDownload value for this DownloadListinoOutputBean.
     * 
     * @param urlConfermaDownload
     */
    public void setUrlConfermaDownload(java.lang.String urlConfermaDownload) {
        this.urlConfermaDownload = urlConfermaDownload;
    }


    /**
     * Gets the urlDownload value for this DownloadListinoOutputBean.
     * 
     * @return urlDownload
     */
    public java.lang.String getUrlDownload() {
        return urlDownload;
    }


    /**
     * Sets the urlDownload value for this DownloadListinoOutputBean.
     * 
     * @param urlDownload
     */
    public void setUrlDownload(java.lang.String urlDownload) {
        this.urlDownload = urlDownload;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DownloadListinoOutputBean)) return false;
        DownloadListinoOutputBean other = (DownloadListinoOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataOraGenerazioneListino==null && other.getDataOraGenerazioneListino()==null) || 
             (this.dataOraGenerazioneListino!=null &&
              this.dataOraGenerazioneListino.equals(other.getDataOraGenerazioneListino()))) &&
            this.delayProsRichiesta == other.getDelayProsRichiesta() &&
            this.esitoServizio == other.getEsitoServizio() &&
            ((this.tipoRisposta==null && other.getTipoRisposta()==null) || 
             (this.tipoRisposta!=null &&
              this.tipoRisposta.equals(other.getTipoRisposta()))) &&
            ((this.urlConfermaDownload==null && other.getUrlConfermaDownload()==null) || 
             (this.urlConfermaDownload!=null &&
              this.urlConfermaDownload.equals(other.getUrlConfermaDownload()))) &&
            ((this.urlDownload==null && other.getUrlDownload()==null) || 
             (this.urlDownload!=null &&
              this.urlDownload.equals(other.getUrlDownload())));
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
        if (getDataOraGenerazioneListino() != null) {
            _hashCode += getDataOraGenerazioneListino().hashCode();
        }
        _hashCode += getDelayProsRichiesta();
        _hashCode += getEsitoServizio();
        if (getTipoRisposta() != null) {
            _hashCode += getTipoRisposta().hashCode();
        }
        if (getUrlConfermaDownload() != null) {
            _hashCode += getUrlConfermaDownload().hashCode();
        }
        if (getUrlDownload() != null) {
            _hashCode += getUrlDownload().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DownloadListinoOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "DownloadListinoOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataOraGenerazioneListino");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataOraGenerazioneListino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("tipoRisposta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoRisposta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlConfermaDownload");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlConfermaDownload"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlDownload");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlDownload"));
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
