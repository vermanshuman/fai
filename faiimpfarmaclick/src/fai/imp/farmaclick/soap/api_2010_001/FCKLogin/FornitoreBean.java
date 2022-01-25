/**
 * FornitoreBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKLogin;

public class FornitoreBean  implements java.io.Serializable {
    private java.lang.String IDSessione;

    private fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ServiziBean[] arrayServizi;

    private java.lang.String codice;

    private java.lang.String codiceSitoLogistico;

    private java.lang.String descrizioneBreve;

    private java.util.Calendar fineFermoMacchina;

    private java.util.Calendar inizioFermoMacchina;

    private java.lang.String livelloAPI;

    private java.util.Calendar scadenzaSessione;

    public FornitoreBean() {
    }

    public FornitoreBean(
           java.lang.String IDSessione,
           fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ServiziBean[] arrayServizi,
           java.lang.String codice,
           java.lang.String codiceSitoLogistico,
           java.lang.String descrizioneBreve,
           java.util.Calendar fineFermoMacchina,
           java.util.Calendar inizioFermoMacchina,
           java.lang.String livelloAPI,
           java.util.Calendar scadenzaSessione) {
           this.IDSessione = IDSessione;
           this.arrayServizi = arrayServizi;
           this.codice = codice;
           this.codiceSitoLogistico = codiceSitoLogistico;
           this.descrizioneBreve = descrizioneBreve;
           this.fineFermoMacchina = fineFermoMacchina;
           this.inizioFermoMacchina = inizioFermoMacchina;
           this.livelloAPI = livelloAPI;
           this.scadenzaSessione = scadenzaSessione;
    }


    /**
     * Gets the IDSessione value for this FornitoreBean.
     * 
     * @return IDSessione
     */
    public java.lang.String getIDSessione() {
        return IDSessione;
    }


    /**
     * Sets the IDSessione value for this FornitoreBean.
     * 
     * @param IDSessione
     */
    public void setIDSessione(java.lang.String IDSessione) {
        this.IDSessione = IDSessione;
    }


    /**
     * Gets the arrayServizi value for this FornitoreBean.
     * 
     * @return arrayServizi
     */
    public fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ServiziBean[] getArrayServizi() {
        return arrayServizi;
    }


    /**
     * Sets the arrayServizi value for this FornitoreBean.
     * 
     * @param arrayServizi
     */
    public void setArrayServizi(fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ServiziBean[] arrayServizi) {
        this.arrayServizi = arrayServizi;
    }


    /**
     * Gets the codice value for this FornitoreBean.
     * 
     * @return codice
     */
    public java.lang.String getCodice() {
        return codice;
    }


    /**
     * Sets the codice value for this FornitoreBean.
     * 
     * @param codice
     */
    public void setCodice(java.lang.String codice) {
        this.codice = codice;
    }


    /**
     * Gets the codiceSitoLogistico value for this FornitoreBean.
     * 
     * @return codiceSitoLogistico
     */
    public java.lang.String getCodiceSitoLogistico() {
        return codiceSitoLogistico;
    }


    /**
     * Sets the codiceSitoLogistico value for this FornitoreBean.
     * 
     * @param codiceSitoLogistico
     */
    public void setCodiceSitoLogistico(java.lang.String codiceSitoLogistico) {
        this.codiceSitoLogistico = codiceSitoLogistico;
    }


    /**
     * Gets the descrizioneBreve value for this FornitoreBean.
     * 
     * @return descrizioneBreve
     */
    public java.lang.String getDescrizioneBreve() {
        return descrizioneBreve;
    }


    /**
     * Sets the descrizioneBreve value for this FornitoreBean.
     * 
     * @param descrizioneBreve
     */
    public void setDescrizioneBreve(java.lang.String descrizioneBreve) {
        this.descrizioneBreve = descrizioneBreve;
    }


    /**
     * Gets the fineFermoMacchina value for this FornitoreBean.
     * 
     * @return fineFermoMacchina
     */
    public java.util.Calendar getFineFermoMacchina() {
        return fineFermoMacchina;
    }


    /**
     * Sets the fineFermoMacchina value for this FornitoreBean.
     * 
     * @param fineFermoMacchina
     */
    public void setFineFermoMacchina(java.util.Calendar fineFermoMacchina) {
        this.fineFermoMacchina = fineFermoMacchina;
    }


    /**
     * Gets the inizioFermoMacchina value for this FornitoreBean.
     * 
     * @return inizioFermoMacchina
     */
    public java.util.Calendar getInizioFermoMacchina() {
        return inizioFermoMacchina;
    }


    /**
     * Sets the inizioFermoMacchina value for this FornitoreBean.
     * 
     * @param inizioFermoMacchina
     */
    public void setInizioFermoMacchina(java.util.Calendar inizioFermoMacchina) {
        this.inizioFermoMacchina = inizioFermoMacchina;
    }


    /**
     * Gets the livelloAPI value for this FornitoreBean.
     * 
     * @return livelloAPI
     */
    public java.lang.String getLivelloAPI() {
        return livelloAPI;
    }


    /**
     * Sets the livelloAPI value for this FornitoreBean.
     * 
     * @param livelloAPI
     */
    public void setLivelloAPI(java.lang.String livelloAPI) {
        this.livelloAPI = livelloAPI;
    }


    /**
     * Gets the scadenzaSessione value for this FornitoreBean.
     * 
     * @return scadenzaSessione
     */
    public java.util.Calendar getScadenzaSessione() {
        return scadenzaSessione;
    }


    /**
     * Sets the scadenzaSessione value for this FornitoreBean.
     * 
     * @param scadenzaSessione
     */
    public void setScadenzaSessione(java.util.Calendar scadenzaSessione) {
        this.scadenzaSessione = scadenzaSessione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FornitoreBean)) return false;
        FornitoreBean other = (FornitoreBean) obj;
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
            ((this.arrayServizi==null && other.getArrayServizi()==null) || 
             (this.arrayServizi!=null &&
              java.util.Arrays.equals(this.arrayServizi, other.getArrayServizi()))) &&
            ((this.codice==null && other.getCodice()==null) || 
             (this.codice!=null &&
              this.codice.equals(other.getCodice()))) &&
            ((this.codiceSitoLogistico==null && other.getCodiceSitoLogistico()==null) || 
             (this.codiceSitoLogistico!=null &&
              this.codiceSitoLogistico.equals(other.getCodiceSitoLogistico()))) &&
            ((this.descrizioneBreve==null && other.getDescrizioneBreve()==null) || 
             (this.descrizioneBreve!=null &&
              this.descrizioneBreve.equals(other.getDescrizioneBreve()))) &&
            ((this.fineFermoMacchina==null && other.getFineFermoMacchina()==null) || 
             (this.fineFermoMacchina!=null &&
              this.fineFermoMacchina.equals(other.getFineFermoMacchina()))) &&
            ((this.inizioFermoMacchina==null && other.getInizioFermoMacchina()==null) || 
             (this.inizioFermoMacchina!=null &&
              this.inizioFermoMacchina.equals(other.getInizioFermoMacchina()))) &&
            ((this.livelloAPI==null && other.getLivelloAPI()==null) || 
             (this.livelloAPI!=null &&
              this.livelloAPI.equals(other.getLivelloAPI()))) &&
            ((this.scadenzaSessione==null && other.getScadenzaSessione()==null) || 
             (this.scadenzaSessione!=null &&
              this.scadenzaSessione.equals(other.getScadenzaSessione())));
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
        if (getArrayServizi() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArrayServizi());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArrayServizi(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodice() != null) {
            _hashCode += getCodice().hashCode();
        }
        if (getCodiceSitoLogistico() != null) {
            _hashCode += getCodiceSitoLogistico().hashCode();
        }
        if (getDescrizioneBreve() != null) {
            _hashCode += getDescrizioneBreve().hashCode();
        }
        if (getFineFermoMacchina() != null) {
            _hashCode += getFineFermoMacchina().hashCode();
        }
        if (getInizioFermoMacchina() != null) {
            _hashCode += getInizioFermoMacchina().hashCode();
        }
        if (getLivelloAPI() != null) {
            _hashCode += getLivelloAPI().hashCode();
        }
        if (getScadenzaSessione() != null) {
            _hashCode += getScadenzaSessione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FornitoreBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "FornitoreBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDSessione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDSessione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayServizi");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayServizi"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:BeanService", "ServiziBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceSitoLogistico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceSitoLogistico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneBreve");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneBreve"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fineFermoMacchina");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fineFermoMacchina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inizioFermoMacchina");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inizioFermoMacchina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("livelloAPI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "livelloAPI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scadenzaSessione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "scadenzaSessione"));
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
