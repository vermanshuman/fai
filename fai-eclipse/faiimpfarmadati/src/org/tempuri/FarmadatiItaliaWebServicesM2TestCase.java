/**
 * FarmadatiItaliaWebServicesM2TestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class FarmadatiItaliaWebServicesM2TestCase extends junit.framework.TestCase {
  
  
    public FarmadatiItaliaWebServicesM2TestCase(java.lang.String name) {
        super(name);
    }

    public void testBasicHttpBinding_FarmadatiItaliaWebServicesM2WSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new org.tempuri.FarmadatiItaliaWebServicesM2Locator().getBasicHttpBinding_FarmadatiItaliaWebServicesM2Address() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new org.tempuri.FarmadatiItaliaWebServicesM2Locator().getServiceName());
        assertTrue(service != null);
    }

    public void test1BasicHttpBinding_FarmadatiItaliaWebServicesM2GetEnabledDataSet() throws Exception {
        org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub binding;
        try {
            binding = (org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub)
                          new org.tempuri.FarmadatiItaliaWebServicesM2Locator().getBasicHttpBinding_FarmadatiItaliaWebServicesM2();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        org.datacontract.schemas._2004._07.FDIWebServices.GetEnabledDataSet_Output value = null;
        value = binding.getEnabledDataSet(new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test2BasicHttpBinding_FarmadatiItaliaWebServicesM2GetSchemaDataSet() throws Exception {
        org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub binding;
        try {
            binding = (org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub)
                          new org.tempuri.FarmadatiItaliaWebServicesM2Locator().getBasicHttpBinding_FarmadatiItaliaWebServicesM2();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        org.datacontract.schemas._2004._07.FDIWebServices.GetSchemaDataSet_Output value = null;
        value = binding.getSchemaDataSet(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.Boolean(false));
        // TBD - validate results
    }

    public void test3BasicHttpBinding_FarmadatiItaliaWebServicesM2GetDataSet() throws Exception {
        org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub binding;
        try {
            binding = (org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub)
                          new org.tempuri.FarmadatiItaliaWebServicesM2Locator().getBasicHttpBinding_FarmadatiItaliaWebServicesM2();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        org.datacontract.schemas._2004._07.FDIWebServices.GetDataSet_Output value = null;
        value = binding.getDataSet(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.Integer(0));
        // TBD - validate results
    }

    public void test4BasicHttpBinding_FarmadatiItaliaWebServicesM2GetDataSetChanges() throws Exception {
        org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub binding;
        try {
            binding = (org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub)
                          new org.tempuri.FarmadatiItaliaWebServicesM2Locator().getBasicHttpBinding_FarmadatiItaliaWebServicesM2();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        org.datacontract.schemas._2004._07.FDIWebServices.GetDataSetChanges_Output value = null;
        value = binding.getDataSetChanges(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.Integer(0), new java.lang.Integer(0));
        // TBD - validate results
    }

}
