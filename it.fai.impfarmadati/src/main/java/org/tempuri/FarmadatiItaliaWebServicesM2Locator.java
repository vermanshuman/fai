/**
 * FarmadatiItaliaWebServicesM2Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class FarmadatiItaliaWebServicesM2Locator extends org.apache.axis.client.Service implements org.tempuri.FarmadatiItaliaWebServicesM2 {

    public FarmadatiItaliaWebServicesM2Locator() {
    }


    public FarmadatiItaliaWebServicesM2Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FarmadatiItaliaWebServicesM2Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_FarmadatiItaliaWebServicesM2
    private java.lang.String BasicHttpBinding_FarmadatiItaliaWebServicesM2_address = "https://webservices.farmadati.it/WS2S/FarmadatiItaliaWebServicesM2.svc";

    public java.lang.String getBasicHttpBinding_FarmadatiItaliaWebServicesM2Address() {
        return BasicHttpBinding_FarmadatiItaliaWebServicesM2_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_FarmadatiItaliaWebServicesM2WSDDServiceName = "BasicHttpBinding_FarmadatiItaliaWebServicesM2";

    public java.lang.String getBasicHttpBinding_FarmadatiItaliaWebServicesM2WSDDServiceName() {
        return BasicHttpBinding_FarmadatiItaliaWebServicesM2WSDDServiceName;
    }

    public void setBasicHttpBinding_FarmadatiItaliaWebServicesM2WSDDServiceName(java.lang.String name) {
        BasicHttpBinding_FarmadatiItaliaWebServicesM2WSDDServiceName = name;
    }

    public it.farmadati.webservices.FarmadatiItaliaWebServicesM2 getBasicHttpBinding_FarmadatiItaliaWebServicesM2() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_FarmadatiItaliaWebServicesM2_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_FarmadatiItaliaWebServicesM2(endpoint);
    }

    public it.farmadati.webservices.FarmadatiItaliaWebServicesM2 getBasicHttpBinding_FarmadatiItaliaWebServicesM2(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub _stub = new org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_FarmadatiItaliaWebServicesM2WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_FarmadatiItaliaWebServicesM2EndpointAddress(java.lang.String address) {
        BasicHttpBinding_FarmadatiItaliaWebServicesM2_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.farmadati.webservices.FarmadatiItaliaWebServicesM2.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub _stub = new org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub(new java.net.URL(BasicHttpBinding_FarmadatiItaliaWebServicesM2_address), this);
                _stub.setPortName(getBasicHttpBinding_FarmadatiItaliaWebServicesM2WSDDServiceName());
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
        if ("BasicHttpBinding_FarmadatiItaliaWebServicesM2".equals(inputPortName)) {
            return getBasicHttpBinding_FarmadatiItaliaWebServicesM2();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "FarmadatiItaliaWebServicesM2");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_FarmadatiItaliaWebServicesM2"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_FarmadatiItaliaWebServicesM2".equals(portName)) {
            setBasicHttpBinding_FarmadatiItaliaWebServicesM2EndpointAddress(address);
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
