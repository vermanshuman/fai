/**
 * LoginOutputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKLogin;

public class LoginOutputBean  implements java.io.Serializable {
    private fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean[] arrayFornitori;

    private int esitoServizio;

    private java.lang.String newPassword;

    private java.util.Calendar serverTime;

    public LoginOutputBean() {
    }

    public LoginOutputBean(
           fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean[] arrayFornitori,
           int esitoServizio,
           java.lang.String newPassword,
           java.util.Calendar serverTime) {
           this.arrayFornitori = arrayFornitori;
           this.esitoServizio = esitoServizio;
           this.newPassword = newPassword;
           this.serverTime = serverTime;
    }


    /**
     * Gets the arrayFornitori value for this LoginOutputBean.
     * 
     * @return arrayFornitori
     */
    public fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean[] getArrayFornitori() {
        return arrayFornitori;
    }


    /**
     * Sets the arrayFornitori value for this LoginOutputBean.
     * 
     * @param arrayFornitori
     */
    public void setArrayFornitori(fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean[] arrayFornitori) {
        this.arrayFornitori = arrayFornitori;
    }


    /**
     * Gets the esitoServizio value for this LoginOutputBean.
     * 
     * @return esitoServizio
     */
    public int getEsitoServizio() {
        return esitoServizio;
    }


    /**
     * Sets the esitoServizio value for this LoginOutputBean.
     * 
     * @param esitoServizio
     */
    public void setEsitoServizio(int esitoServizio) {
        this.esitoServizio = esitoServizio;
    }


    /**
     * Gets the newPassword value for this LoginOutputBean.
     * 
     * @return newPassword
     */
    public java.lang.String getNewPassword() {
        return newPassword;
    }


    /**
     * Sets the newPassword value for this LoginOutputBean.
     * 
     * @param newPassword
     */
    public void setNewPassword(java.lang.String newPassword) {
        this.newPassword = newPassword;
    }


    /**
     * Gets the serverTime value for this LoginOutputBean.
     * 
     * @return serverTime
     */
    public java.util.Calendar getServerTime() {
        return serverTime;
    }


    /**
     * Sets the serverTime value for this LoginOutputBean.
     * 
     * @param serverTime
     */
    public void setServerTime(java.util.Calendar serverTime) {
        this.serverTime = serverTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LoginOutputBean)) return false;
        LoginOutputBean other = (LoginOutputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.arrayFornitori==null && other.getArrayFornitori()==null) || 
             (this.arrayFornitori!=null &&
              java.util.Arrays.equals(this.arrayFornitori, other.getArrayFornitori()))) &&
            this.esitoServizio == other.getEsitoServizio() &&
            ((this.newPassword==null && other.getNewPassword()==null) || 
             (this.newPassword!=null &&
              this.newPassword.equals(other.getNewPassword()))) &&
            ((this.serverTime==null && other.getServerTime()==null) || 
             (this.serverTime!=null &&
              this.serverTime.equals(other.getServerTime())));
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
        if (getArrayFornitori() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayFornitori());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayFornitori(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getEsitoServizio();
        if (getNewPassword() != null) {
            _hashCode += getNewPassword().hashCode();
        }
        if (getServerTime() != null) {
            _hashCode += getServerTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoginOutputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "LoginOutputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayFornitori");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayFornitori"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "FornitoreBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esitoServizio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esitoServizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serverTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
