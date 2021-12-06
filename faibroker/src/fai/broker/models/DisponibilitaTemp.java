package fai.broker.models;

import it.swdes.models.*;

public class DisponibilitaTemp extends AbstractModel {


  public static int OID;
  public static int FORNITORE;
  public static int MAGAZZINO;
  public static int REQUEST_TS;
  public static int REQUEST_RAW_DATA;
  public static int RESPONSE_TS;
  public static int RESPONSE_RAW_DATA;
  public static int STATUS;
  public static int REQ;

  public static final Attribute[] attributes = buildAttributes(DisponibilitaTemp.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("fornitore", null, Fornitore.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
    new Attribute("magazzino", null, Magazzino.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
    new Attribute("requestTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("requestRawData", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("responseTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("responseRawData", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("status", null, StatusInfo.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
    new Attribute("req", null, DisponibilitaReqTemp[].class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public DisponibilitaTemp() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** FORNITORE **/
  public boolean setFornitore(Fornitore fornitore) {
    return setObject(FORNITORE, fornitore);
  }

  public Fornitore getFornitore() {
    return (Fornitore)getObject(FORNITORE);
  }


  /** MAGAZZINO **/
  public boolean setMagazzino(Magazzino magazzino) {
    return setObject(MAGAZZINO, magazzino);
  }

  public Magazzino getMagazzino() {
    return (Magazzino)getObject(MAGAZZINO);
  }


  /** REQUEST_TS **/
  public boolean setRequestTs(java.util.Calendar requestTs) {
    return setCalendar(REQUEST_TS, requestTs);
  }

  public java.util.Calendar getRequestTs() {
    return getCalendar(REQUEST_TS);
  }


  /** REQUEST_RAW_DATA **/
  public boolean setRequestRawData(String requestRawData) {
    return setString(REQUEST_RAW_DATA, requestRawData);
  }

  public String getRequestRawData() {
    return getString(REQUEST_RAW_DATA);
  }


  /** RESPONSE_TS **/
  public boolean setResponseTs(java.util.Calendar responseTs) {
    return setCalendar(RESPONSE_TS, responseTs);
  }

  public java.util.Calendar getResponseTs() {
    return getCalendar(RESPONSE_TS);
  }


  /** RESPONSE_RAW_DATA **/
  public boolean setResponseRawData(String responseRawData) {
    return setString(RESPONSE_RAW_DATA, responseRawData);
  }

  public String getResponseRawData() {
    return getString(RESPONSE_RAW_DATA);
  }


  /** STATUS **/
  public boolean setStatus(StatusInfo status) {
    return setObject(STATUS, status);
  }

  public StatusInfo getStatus() {
    return (StatusInfo)getObject(STATUS);
  }


  /** REQ **/
  public boolean setReq(AbstractModel req) {
    return setObject(REQ, req);
  }

  public AbstractModel getReq() {
    return getObject(REQ);
  }

  public void setAllReq(DisponibilitaReqTemp[] req) {
    setArray(REQ, req);
  }

  public DisponibilitaReqTemp[] getAllReq() {
    return (DisponibilitaReqTemp[])getArray(REQ);
  }

  public void addReq(DisponibilitaReqTemp req) {
    addArrayElement(REQ, req);
  }

  public void addReq(int index, DisponibilitaReqTemp req) {
    addArrayElement(REQ, index, req);
  }

  public boolean removeReq(DisponibilitaReqTemp req) {
    return removeArrayElement(REQ, req);
  }

  public DisponibilitaReqTemp removeReq(int index) {
    return (DisponibilitaReqTemp)removeArrayElement(REQ, index);
  }

  public DisponibilitaReqTemp getReqAt(int index) {
    return (DisponibilitaReqTemp)getArrayElement(REQ, index);
  }

  public void replaceReqAt(int index, DisponibilitaReqTemp req) {
    copyAt(REQ, req, index);
  }

  public int getReqCount() {
    return getArrayCount(REQ);
  }

 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on



}
