/**
 * Farmaclick2008001ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino;

public class Farmaclick2008001ServiceLocator extends org.apache.axis.client.Service implements fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001Service {

/**
 * (C) Infarma Srl - Informatica Farmaceutica
 * 	           Via P. Canova 30 - 40138 Bologna
 * 	           Tel. 199.128.900
 * 	           email: a.bottaioli@infarma.it
 * 	           web: http://www.infarma.it
 */

    public Farmaclick2008001ServiceLocator() {
    }


    public Farmaclick2008001ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Farmaclick2008001ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Farmaclick2008001FCKDownloadListino
    private java.lang.String Farmaclick2008001FCKDownloadListino_address = "http://deploy.infarmaclick.com/infarmaclick/farmaclick/Farmaclick2008001FCKDownloadListino";

    public java.lang.String getFarmaclick2008001FCKDownloadListinoAddress() {
        return Farmaclick2008001FCKDownloadListino_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Farmaclick2008001FCKDownloadListinoWSDDServiceName = "Farmaclick2008001FCKDownloadListino";

    public java.lang.String getFarmaclick2008001FCKDownloadListinoWSDDServiceName() {
        return Farmaclick2008001FCKDownloadListinoWSDDServiceName;
    }

    public void setFarmaclick2008001FCKDownloadListinoWSDDServiceName(java.lang.String name) {
        Farmaclick2008001FCKDownloadListinoWSDDServiceName = name;
    }

    public fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001 getFarmaclick2008001FCKDownloadListino() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Farmaclick2008001FCKDownloadListino_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFarmaclick2008001FCKDownloadListino(endpoint);
    }

    public fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001 getFarmaclick2008001FCKDownloadListino(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001FCKDownloadListinoSoapBindingStub _stub = new fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001FCKDownloadListinoSoapBindingStub(portAddress, this);
            _stub.setPortName(getFarmaclick2008001FCKDownloadListinoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFarmaclick2008001FCKDownloadListinoEndpointAddress(java.lang.String address) {
        Farmaclick2008001FCKDownloadListino_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001.class.isAssignableFrom(serviceEndpointInterface)) {
                fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001FCKDownloadListinoSoapBindingStub _stub = new fai.imp.farmaclick.soap.api_2008_001.FCKDownloadListino.Farmaclick2008001FCKDownloadListinoSoapBindingStub(new java.net.URL(Farmaclick2008001FCKDownloadListino_address), this);
                _stub.setPortName(getFarmaclick2008001FCKDownloadListinoWSDDServiceName());
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
        if ("Farmaclick2008001FCKDownloadListino".equals(inputPortName)) {
            return getFarmaclick2008001FCKDownloadListino();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://deploy.infarmaclick.com/infarmaclick/farmaclick/Farmaclick2008001FCKDownloadListino", "farmaclick2008001Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://deploy.infarmaclick.com/infarmaclick/farmaclick/Farmaclick2008001FCKDownloadListino", "Farmaclick2008001FCKDownloadListino"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Farmaclick2008001FCKDownloadListino".equals(portName)) {
            setFarmaclick2008001FCKDownloadListinoEndpointAddress(address);
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
