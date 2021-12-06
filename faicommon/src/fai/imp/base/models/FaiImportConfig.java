package fai.imp.base.models;

import it.swdes.models.*;

public class FaiImportConfig extends AbstractModel {


  public static int OID;
  public static int ACRONYM;
  public static int DESCR;
  public static int IMPL_CLASS_NAME;
  public static int SERVICE_LOGIN;
  public static int SERVICE_PASS;
  public static int SERVICE_PASS_ENCR;
  public static int SERVICE_QUERY_TIME;
  public static int SERVICE_QUERY_SMTWTFS;
  public static int SERVICE_QUERY_URL;
  public static int SERVICE_QUREY_ZIPPED_CONTENT;
  public static int SERVICE_RESUME_PREV_SESSION;
  public static int LAST_RUN_START_TS;
  public static int LAST_RUN_END_TS;
  public static int LAST_RUN_TYPE;
  public static int LAST_RUN_DONE;
  public static int LAST_RUN_DESCR;

  public static final Attribute[] attributes = buildAttributes(FaiImportConfig.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("acronym", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("descr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("implClassName", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("serviceLogin", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("servicePass", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("servicePassEncr", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("serviceQueryTime", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("serviceQuerySMTWTFS", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("serviceQueryUrl", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("serviceQureyZippedContent", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("serviceResumePrevSession", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunStartTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunEndTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunType", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunDone", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public FaiImportConfig() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** ACRONYM **/
  public boolean setAcronym(String acronym) {
    return setString(ACRONYM, acronym);
  }

  public String getAcronym() {
    return getString(ACRONYM);
  }


  /** DESCR **/
  public boolean setDescr(String descr) {
    return setString(DESCR, descr);
  }

  public String getDescr() {
    return getString(DESCR);
  }


  /** IMPL_CLASS_NAME **/
  public boolean setImplClassName(String implClassName) {
    return setString(IMPL_CLASS_NAME, implClassName);
  }

  public String getImplClassName() {
    return getString(IMPL_CLASS_NAME);
  }


  /** SERVICE_LOGIN **/
  public boolean setServiceLogin(String serviceLogin) {
    return setString(SERVICE_LOGIN, serviceLogin);
  }

  public String getServiceLogin() {
    return getString(SERVICE_LOGIN);
  }


  /** SERVICE_PASS **/
  public boolean setServicePass(String servicePass) {
    return setString(SERVICE_PASS, servicePass);
  }

  public String getServicePass() {
    return getString(SERVICE_PASS);
  }


  /** SERVICE_PASS_ENCR **/
  public boolean setServicePassEncr(Boolean servicePassEncr) {
    return setBoolean(SERVICE_PASS_ENCR, servicePassEncr);
  }

  public Boolean getServicePassEncr() {
    return getBoolean(SERVICE_PASS_ENCR);
  }

  public boolean isServicePassEncr() {
    return isBoolean(SERVICE_PASS_ENCR);
  }


  /** SERVICE_QUERY_TIME **/
  public boolean setServiceQueryTime(java.util.Calendar serviceQueryTime) {
    return setCalendar(SERVICE_QUERY_TIME, serviceQueryTime);
  }

  public java.util.Calendar getServiceQueryTime() {
    return getCalendar(SERVICE_QUERY_TIME);
  }


  /** SERVICE_QUERY_SMTWTFS **/
  public boolean setServiceQuerySMTWTFS(String serviceQuerySMTWTFS) {
    return setString(SERVICE_QUERY_SMTWTFS, serviceQuerySMTWTFS);
  }

  public String getServiceQuerySMTWTFS() {
    return getString(SERVICE_QUERY_SMTWTFS);
  }


  /** SERVICE_QUERY_URL **/
  public boolean setServiceQueryUrl(String serviceQueryUrl) {
    return setString(SERVICE_QUERY_URL, serviceQueryUrl);
  }

  public String getServiceQueryUrl() {
    return getString(SERVICE_QUERY_URL);
  }


  /** SERVICE_QUREY_ZIPPED_CONTENT **/
  public boolean setServiceQureyZippedContent(Boolean serviceQureyZippedContent) {
    return setBoolean(SERVICE_QUREY_ZIPPED_CONTENT, serviceQureyZippedContent);
  }

  public Boolean getServiceQureyZippedContent() {
    return getBoolean(SERVICE_QUREY_ZIPPED_CONTENT);
  }

  public boolean isServiceQureyZippedContent() {
    return isBoolean(SERVICE_QUREY_ZIPPED_CONTENT);
  }


  /** SERVICE_RESUME_PREV_SESSION **/
  public boolean setServiceResumePrevSession(String serviceResumePrevSession) {
    return setString(SERVICE_RESUME_PREV_SESSION, serviceResumePrevSession);
  }

  public String getServiceResumePrevSession() {
    return getString(SERVICE_RESUME_PREV_SESSION);
  }


  /** LAST_RUN_START_TS **/
  public boolean setLastRunStartTs(java.util.Calendar lastRunStartTs) {
    return setCalendar(LAST_RUN_START_TS, lastRunStartTs);
  }

  public java.util.Calendar getLastRunStartTs() {
    return getCalendar(LAST_RUN_START_TS);
  }


  /** LAST_RUN_END_TS **/
  public boolean setLastRunEndTs(java.util.Calendar lastRunEndTs) {
    return setCalendar(LAST_RUN_END_TS, lastRunEndTs);
  }

  public java.util.Calendar getLastRunEndTs() {
    return getCalendar(LAST_RUN_END_TS);
  }


  /** LAST_RUN_TYPE **/
  public boolean setLastRunType(String lastRunType) {
    return setString(LAST_RUN_TYPE, lastRunType);
  }

  public String getLastRunType() {
    return getString(LAST_RUN_TYPE);
  }


  /** LAST_RUN_DONE **/
  public boolean setLastRunDone(Boolean lastRunDone) {
    return setBoolean(LAST_RUN_DONE, lastRunDone);
  }

  public Boolean getLastRunDone() {
    return getBoolean(LAST_RUN_DONE);
  }

  public boolean isLastRunDone() {
    return isBoolean(LAST_RUN_DONE);
  }


  /** LAST_RUN_DESCR **/
  public boolean setLastRunDescr(String lastRunDescr) {
    return setString(LAST_RUN_DESCR, lastRunDescr);
  }

  public String getLastRunDescr() {
    return getString(LAST_RUN_DESCR);
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


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on



}
