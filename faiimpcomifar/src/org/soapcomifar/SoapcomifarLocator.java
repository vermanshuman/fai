/**
 * SoapcomifarLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.soapcomifar;

public class SoapcomifarLocator extends org.apache.axis.client.Service implements Soapcomifar {

/**
 * gSOAP 2.7.6d generated service definition
 */

    public SoapcomifarLocator() {
    }


    public SoapcomifarLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SoapcomifarLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for soapcomifar
    private java.lang.String soapcomifar_address = "https://ordiniwebtest.comifar.it";

    public java.lang.String getsoapcomifarAddress() {
        return soapcomifar_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String soapcomifarWSDDServiceName = "soapcomifar";

    public java.lang.String getsoapcomifarWSDDServiceName() {
        return soapcomifarWSDDServiceName;
    }

    public void setsoapcomifarWSDDServiceName(java.lang.String name) {
        soapcomifarWSDDServiceName = name;
    }

    public SoapcomifarPortType getsoapcomifar() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(soapcomifar_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsoapcomifar(endpoint);
    }

    public SoapcomifarPortType getsoapcomifar(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SoapcomifarStub _stub = new SoapcomifarStub(portAddress, this);
            _stub.setPortName(getsoapcomifarWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsoapcomifarEndpointAddress(java.lang.String address) {
        soapcomifar_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SoapcomifarPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                SoapcomifarStub _stub = new SoapcomifarStub(new java.net.URL(soapcomifar_address), this);
                _stub.setPortName(getsoapcomifarWSDDServiceName());
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
        if ("soapcomifar".equals(inputPortName)) {
            return getsoapcomifar();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:soapcomifar", "soapcomifar");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:soapcomifar", "soapcomifar"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("soapcomifar".equals(portName)) {
            setsoapcomifarEndpointAddress(address);
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
