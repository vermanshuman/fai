/**
 * Farmaclick2010001ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fai.imp.farmaclick.soap.api_2010_001.FCKOrdine;

public class Farmaclick2010001ServiceLocator extends org.apache.axis.client.Service implements fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001Service {

/**
 * (C) Infarma Srl - Informatica Farmaceutica
 * 	           Via Canova 30 - Bologna
 * 	           Tel. 199.128.900
 * 	           email: farmaclick@infarma.it
 * 	           web: http://www.infarma.it
 */

    public Farmaclick2010001ServiceLocator() {
    }


    public Farmaclick2010001ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Farmaclick2010001ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Farmaclick2010001FCKOrdine
    private java.lang.String Farmaclick2010001FCKOrdine_address = "http://farmaclick.infarma.it/Farmaclick2010001FCKOrdine";

    public java.lang.String getFarmaclick2010001FCKOrdineAddress() {
        return Farmaclick2010001FCKOrdine_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Farmaclick2010001FCKOrdineWSDDServiceName = "Farmaclick2010001FCKOrdine";

    public java.lang.String getFarmaclick2010001FCKOrdineWSDDServiceName() {
        return Farmaclick2010001FCKOrdineWSDDServiceName;
    }

    public void setFarmaclick2010001FCKOrdineWSDDServiceName(java.lang.String name) {
        Farmaclick2010001FCKOrdineWSDDServiceName = name;
    }

    public fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001 getFarmaclick2010001FCKOrdine() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Farmaclick2010001FCKOrdine_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFarmaclick2010001FCKOrdine(endpoint);
    }

    public fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001 getFarmaclick2010001FCKOrdine(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001FCKOrdineSoapBindingStub _stub = new fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001FCKOrdineSoapBindingStub(portAddress, this);
            _stub.setPortName(getFarmaclick2010001FCKOrdineWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFarmaclick2010001FCKOrdineEndpointAddress(java.lang.String address) {
        Farmaclick2010001FCKOrdine_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001.class.isAssignableFrom(serviceEndpointInterface)) {
                fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001FCKOrdineSoapBindingStub _stub = new fai.imp.farmaclick.soap.api_2010_001.FCKOrdine.Farmaclick2010001FCKOrdineSoapBindingStub(new java.net.URL(Farmaclick2010001FCKOrdine_address), this);
                _stub.setPortName(getFarmaclick2010001FCKOrdineWSDDServiceName());
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
        if ("Farmaclick2010001FCKOrdine".equals(inputPortName)) {
            return getFarmaclick2010001FCKOrdine();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://farmaclick.infarma.it/Farmaclick2010001FCKOrdine", "farmaclick2010001Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://farmaclick.infarma.it/Farmaclick2010001FCKOrdine", "Farmaclick2010001FCKOrdine"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Farmaclick2010001FCKOrdine".equals(portName)) {
            setFarmaclick2010001FCKOrdineEndpointAddress(address);
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
