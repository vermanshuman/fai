/**
 * SoapcomifarPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.soapcomifar;

public interface SoapcomifarPortType extends java.rmi.Remote {

    /**
     * Service definition of function ns__disponibilita
     */
    public java.lang.String disponibilita(java.lang.String myCLIENTE, java.lang.String myMINSAN, java.lang.String myARGS, java.lang.String myPWD, java.lang.String myPWD2) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__ordine
     */
    public java.lang.String ordine(java.lang.String myXML, java.lang.String myPWD, java.lang.String myPWD2) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__ddtweb
     */
    public java.lang.String ddtweb(java.lang.String myCLIENTE, java.lang.String myRIFINTERNO, java.lang.String myNUMERO, java.lang.String myARGS, java.lang.String myPWD, java.lang.String myPWD2) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__ddtlist
     */
    public java.lang.String ddtlist(java.lang.String myCLIENTE, java.lang.String myRIFINTERNO, java.lang.String myARGS, java.lang.String myDATA1, java.lang.String myDATA2, java.lang.String myPWD, java.lang.String myPWD2) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__listino
     */
    public java.lang.String listino(java.lang.String myCLIENTE, java.lang.String myPWD, java.lang.String myPWD2, java.lang.String myARGS, java.lang.String myTIPO) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__test
     */
    public java.lang.String test() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__fileget
     */
    public java.lang.String fileget(java.lang.String filename, java.lang.String myCLIENTE, java.lang.String myPWD, java.lang.String myPWD2) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__getfcond
     */
    public java.lang.String getfcond(java.lang.String myCLIENTE, java.lang.String myNUMERO, java.lang.String myARGS, java.lang.String myPWD, java.lang.String myPWD2) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__getfcond
     */
    public java.lang.String listfcond(java.lang.String myCLIENTE, java.lang.String myARGS, java.lang.String myPWD, java.lang.String myPWD2) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__setfilestatus
     */
    public java.lang.String setfcondstatus(java.lang.String myCLIENTE, java.lang.String myNUMERO, java.lang.String myCODESITO, java.lang.String myDESCRESITO, java.lang.String myARGS, java.lang.String myPWD, java.lang.String myPWD2) throws java.rmi.RemoteException;
}
