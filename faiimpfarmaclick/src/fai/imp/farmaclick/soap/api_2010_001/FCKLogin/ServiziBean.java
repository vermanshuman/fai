/**
 * ServiziBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKLogin;

public class ServiziBean  implements java.io.Serializable {
    private int abilitato;

    private fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ErogazioneServiziBean[] arrayErogazioneServizi;

    private java.lang.String codiceServizio;

    private java.lang.String livelloAPI;

    private java.lang.String url1;

    private java.lang.String url2;

    public ServiziBean() {
    }

    public ServiziBean(
           int abilitato,
           fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ErogazioneServiziBean[] arrayErogazioneServizi,
           java.lang.String codiceServizio,
           java.lang.String livelloAPI,
           java.lang.String url1,
           java.lang.String url2) {
           this.abilitato = abilitato;
           this.arrayErogazioneServizi = arrayErogazioneServizi;
           this.codiceServizio = codiceServizio;
           this.livelloAPI = livelloAPI;
           this.url1 = url1;
           this.url2 = url2;
    }


    /**
     * Gets the abilitato value for this ServiziBean.
     * 
     * @return abilitato
     */
    public int getAbilitato() {
        return abilitato;
    }


    /**
     * Sets the abilitato value for this ServiziBean.
     * 
     * @param abilitato
     */
    public void setAbilitato(int abilitato) {
        this.abilitato = abilitato;
    }


    /**
     * Gets the arrayErogazioneServizi value for this ServiziBean.
     * 
     * @return arrayErogazioneServizi
     */
    public fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ErogazioneServiziBean[] getArrayErogazioneServizi() {
        return arrayErogazioneServizi;
    }


    /**
     * Sets the arrayErogazioneServizi value for this ServiziBean.
     * 
     * @param arrayErogazioneServizi
     */
    public void setArrayErogazioneServizi(fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ErogazioneServiziBean[] arrayErogazioneServizi) {
        this.arrayErogazioneServizi = arrayErogazioneServizi;
    }


    /**
     * Gets the codiceServizio value for this ServiziBean.
     * 
     * @return codiceServizio
     */
    public java.lang.String getCodiceServizio() {
        return codiceServizio;
    }


    /**
     * Sets the codiceServizio value for this ServiziBean.
     * 
     * @param codiceServizio
     */
    public void setCodiceServizio(java.lang.String codiceServizio) {
        this.codiceServizio = codiceServizio;
    }


    /**
     * Gets the livelloAPI value for this ServiziBean.
     * 
     * @return livelloAPI
     */
    public java.lang.String getLivelloAPI() {
        return livelloAPI;
    }


    /**
     * Sets the livelloAPI value for this ServiziBean.
     * 
     * @param livelloAPI
     */
    public void setLivelloAPI(java.lang.String livelloAPI) {
        this.livelloAPI = livelloAPI;
    }


    /**
     * Gets the url1 value for this ServiziBean.
     * 
     * @return url1
     */
    public java.lang.String getUrl1() {
        return url1;
    }


    /**
     * Sets the url1 value for this ServiziBean.
     * 
     * @param url1
     */
    public void setUrl1(java.lang.String url1) {
        this.url1 = url1;
    }


    /**
     * Gets the url2 value for this ServiziBean.
     * 
     * @return url2
     */
    public java.lang.String getUrl2() {
        return url2;
    }


    /**
     * Sets the url2 value for this ServiziBean.
     * 
     * @param url2
     */
    public void setUrl2(java.lang.String url2) {
        this.url2 = url2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiziBean)) return false;
        ServiziBean other = (ServiziBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.abilitato == other.getAbilitato() &&
            ((this.arrayErogazioneServizi==null && other.getArrayErogazioneServizi()==null) || 
             (this.arrayErogazioneServizi!=null &&
              java.util.Arrays.equals(this.arrayErogazioneServizi, other.getArrayErogazioneServizi()))) &&
            ((this.codiceServizio==null && other.getCodiceServizio()==null) || 
             (this.codiceServizio!=null &&
              this.codiceServizio.equals(other.getCodiceServizio()))) &&
            ((this.livelloAPI==null && other.getLivelloAPI()==null) || 
             (this.livelloAPI!=null &&
              this.livelloAPI.equals(other.getLivelloAPI()))) &&
            ((this.url1==null && other.getUrl1()==null) || 
             (this.url1!=null &&
              this.url1.equals(other.getUrl1()))) &&
            ((this.url2==null && other.getUrl2()==null) || 
             (this.url2!=null &&
              this.url2.equals(other.getUrl2())));
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
        _hashCode += getAbilitato();
        if (getArrayErogazioneServizi() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayErogazioneServizi());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayErogazioneServizi(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodiceServizio() != null) {
            _hashCode += getCodiceServizio().hashCode();
        }
        if (getLivelloAPI() != null) {
            _hashCode += getLivelloAPI().hashCode();
        }
        if (getUrl1() != null) {
            _hashCode += getUrl1().hashCode();
        }
        if (getUrl2() != null) {
            _hashCode += getUrl2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiziBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ServiziBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abilitato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abilitato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayErogazioneServizi");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayErogazioneServizi"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ErogazioneServiziBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceServizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceServizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("livelloAPI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "livelloAPI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url2"));
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
