/**
 * Farmaclick2010001ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino;

public class Farmaclick2010001ServiceLocator extends org.apache.axis.client.Service implements fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001Service {

    public Farmaclick2010001ServiceLocator() {
    }


    public Farmaclick2010001ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Farmaclick2010001ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Farmaclick2010001FCKDownloadListino
    private java.lang.String Farmaclick2010001FCKDownloadListino_address = "http://farmaclick.infarma.it/Farmaclick2010001FCKDownloadListino";

    public java.lang.String getFarmaclick2010001FCKDownloadListinoAddress() {
        return Farmaclick2010001FCKDownloadListino_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Farmaclick2010001FCKDownloadListinoWSDDServiceName = "Farmaclick2010001FCKDownloadListino";

    public java.lang.String getFarmaclick2010001FCKDownloadListinoWSDDServiceName() {
        return Farmaclick2010001FCKDownloadListinoWSDDServiceName;
    }

    public void setFarmaclick2010001FCKDownloadListinoWSDDServiceName(java.lang.String name) {
        Farmaclick2010001FCKDownloadListinoWSDDServiceName = name;
    }

    public fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001 getFarmaclick2010001FCKDownloadListino() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Farmaclick2010001FCKDownloadListino_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFarmaclick2010001FCKDownloadListino(endpoint);
    }

    public fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001 getFarmaclick2010001FCKDownloadListino(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001FCKDownloadListinoSoapBindingStub _stub = new fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001FCKDownloadListinoSoapBindingStub(portAddress, this);
            _stub.setPortName(getFarmaclick2010001FCKDownloadListinoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFarmaclick2010001FCKDownloadListinoEndpointAddress(java.lang.String address) {
        Farmaclick2010001FCKDownloadListino_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001.class.isAssignableFrom(serviceEndpointInterface)) {
                fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001FCKDownloadListinoSoapBindingStub _stub = new fai.imp.farmaclick.soap.api_2010_001.FCKDownloadListino.Farmaclick2010001FCKDownloadListinoSoapBindingStub(new java.net.URL(Farmaclick2010001FCKDownloadListino_address), this);
                _stub.setPortName(getFarmaclick2010001FCKDownloadListinoWSDDServiceName());
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
        if ("Farmaclick2010001FCKDownloadListino".equals(inputPortName)) {
            return getFarmaclick2010001FCKDownloadListino();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://farmaclick.infarma.it/Farmaclick2010001FCKDownloadListino", "farmaclick2010001Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://farmaclick.infarma.it/Farmaclick2010001FCKDownloadListino", "Farmaclick2010001FCKDownloadListino"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Farmaclick2010001FCKDownloadListino".equals(portName)) {
            setFarmaclick2010001FCKDownloadListinoEndpointAddress(address);
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
