/**
 * FarmadatiItaliaWebServicesM2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.farmadati.webservices;

public interface FarmadatiItaliaWebServicesM2 extends java.rmi.Remote {
    public org.datacontract.schemas._2004._07.FDIWebServices.GetEnabledDataSet_Output getEnabledDataSet(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.FDIWebServices.GetSchemaDataSet_Output getSchemaDataSet(java.lang.String username, java.lang.String password, java.lang.String codiceSetDati, java.lang.Boolean tracciatoVar) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.FDIWebServices.GetDataSet_Output getDataSet(java.lang.String username, java.lang.String password, java.lang.String codiceSetDati, java.lang.String modalita, java.lang.Integer pageN) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.FDIWebServices.GetDataSetChanges_Output getDataSetChanges(java.lang.String username, java.lang.String password, java.lang.String codiceSetDati, java.lang.String dataIstanza, java.lang.String modalita, java.lang.Integer pageN, java.lang.Integer pagingN) throws java.rmi.RemoteException;
}
