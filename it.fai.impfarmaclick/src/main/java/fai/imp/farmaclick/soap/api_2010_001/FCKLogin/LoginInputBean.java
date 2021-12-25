/**
 * LoginInputBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKLogin;

public class LoginInputBean  implements java.io.Serializable {
    private java.lang.String nomeTerminale;

    private java.lang.String password;

    private java.lang.String passwordSH;

    private java.lang.String userName;

    public LoginInputBean() {
    }

    public LoginInputBean(
           java.lang.String nomeTerminale,
           java.lang.String password,
           java.lang.String passwordSH,
           java.lang.String userName) {
           this.nomeTerminale = nomeTerminale;
           this.password = password;
           this.passwordSH = passwordSH;
           this.userName = userName;
    }


    /**
     * Gets the nomeTerminale value for this LoginInputBean.
     * 
     * @return nomeTerminale
     */
    public java.lang.String getNomeTerminale() {
        return nomeTerminale;
    }


    /**
     * Sets the nomeTerminale value for this LoginInputBean.
     * 
     * @param nomeTerminale
     */
    public void setNomeTerminale(java.lang.String nomeTerminale) {
        this.nomeTerminale = nomeTerminale;
    }


    /**
     * Gets the password value for this LoginInputBean.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this LoginInputBean.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the passwordSH value for this LoginInputBean.
     * 
     * @return passwordSH
     */
    public java.lang.String getPasswordSH() {
        return passwordSH;
    }


    /**
     * Sets the passwordSH value for this LoginInputBean.
     * 
     * @param passwordSH
     */
    public void setPasswordSH(java.lang.String passwordSH) {
        this.passwordSH = passwordSH;
    }


    /**
     * Gets the userName value for this LoginInputBean.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this LoginInputBean.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LoginInputBean)) return false;
        LoginInputBean other = (LoginInputBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeTerminale==null && other.getNomeTerminale()==null) || 
             (this.nomeTerminale!=null &&
              this.nomeTerminale.equals(other.getNomeTerminale()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.passwordSH==null && other.getPasswordSH()==null) || 
             (this.passwordSH!=null &&
              this.passwordSH.equals(other.getPasswordSH()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName())));
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
        if (getNomeTerminale() != null) {
            _hashCode += getNomeTerminale().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getPasswordSH() != null) {
            _hashCode += getPasswordSH().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoginInputBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "LoginInputBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeTerminale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeTerminale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passwordSH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "passwordSH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userName"));
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
