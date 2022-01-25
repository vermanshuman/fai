/**
 * Farmaclick2010002ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino;

public class Farmaclick2010002ServiceLocator extends org.apache.axis.client.Service implements fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002Service {

/**
 * (C) Infarma Srl - Informatica Farmaceutica
 * 	           Via Canova 30 - Bologna
 * 	           Tel. 199.128.900
 * 	           email: farmaclick@infarma.it
 * 	           web: http://www.infarma.it
 */

    public Farmaclick2010002ServiceLocator() {
    }


    public Farmaclick2010002ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Farmaclick2010002ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Farmaclick2010002FCKDownloadListino
    private java.lang.String Farmaclick2010002FCKDownloadListino_address = "http://farmaclick.infarma.it/Farmaclick2010002FCKDownloadListino";

    public java.lang.String getFarmaclick2010002FCKDownloadListinoAddress() {
        return Farmaclick2010002FCKDownloadListino_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Farmaclick2010002FCKDownloadListinoWSDDServiceName = "Farmaclick2010002FCKDownloadListino";

    public java.lang.String getFarmaclick2010002FCKDownloadListinoWSDDServiceName() {
        return Farmaclick2010002FCKDownloadListinoWSDDServiceName;
    }

    public void setFarmaclick2010002FCKDownloadListinoWSDDServiceName(java.lang.String name) {
        Farmaclick2010002FCKDownloadListinoWSDDServiceName = name;
    }

    public fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002 getFarmaclick2010002FCKDownloadListino() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Farmaclick2010002FCKDownloadListino_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFarmaclick2010002FCKDownloadListino(endpoint);
    }

    public fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002 getFarmaclick2010002FCKDownloadListino(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002FCKDownloadListinoSoapBindingStub _stub = new fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002FCKDownloadListinoSoapBindingStub(portAddress, this);
            _stub.setPortName(getFarmaclick2010002FCKDownloadListinoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFarmaclick2010002FCKDownloadListinoEndpointAddress(java.lang.String address) {
        Farmaclick2010002FCKDownloadListino_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002.class.isAssignableFrom(serviceEndpointInterface)) {
                fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002FCKDownloadListinoSoapBindingStub _stub = new fai.imp.farmaclick.soap.api_2010_002.FCKDownloadListino.Farmaclick2010002FCKDownloadListinoSoapBindingStub(new java.net.URL(Farmaclick2010002FCKDownloadListino_address), this);
                _stub.setPortName(getFarmaclick2010002FCKDownloadListinoWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Farmaclick2010002FCKDownloadListino".equals(inputPortName)) {
            return getFarmaclick2010002FCKDownloadListino();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://farmaclick.infarma.it/Farmaclick2010002FCKDownloadListino", "farmaclick2010002Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://farmaclick.infarma.it/Farmaclick2010002FCKDownloadListino", "Farmaclick2010002FCKDownloadListino"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Farmaclick2010002FCKDownloadListino".equals(portName)) {
            setFarmaclick2010002FCKDownloadListinoEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
