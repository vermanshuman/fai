package fai.broker.models;

import it.swdes.models.AbstractModel;
import it.swdes.models.Attribute;

import java.util.Calendar;

public class UploadStatusInfo extends AbstractModel {


  public static int STATUS;
  public static int STATUS_DESCR;
  public static int STATUS_TECH_DESCR;
  public static int STATUS_UPDATED_TS;

  public static final Attribute[] attributes = buildAttributes(UploadStatusInfo.class, new Attribute[] { //@formatter:off
    new Attribute("status", null, UploadCSVStatus.class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
    new Attribute("statusDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("statusTechDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("statusUpdatedTs", null, Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  public static UploadStatusInfo newInstance(UploadCSVStatus uploadCSVStatus, String statusDecr, String statusTechDescr) {
    UploadStatusInfo si = new UploadStatusInfo();
    si.setStatus(uploadCSVStatus);
    si.setStatusDescr(statusDecr);
    si.setStatusTechDescr(statusTechDescr);
    si.setStatusUpdatedTs(Calendar.getInstance());
    return si;
  }

  public static UploadStatusInfo newToProcessInstance(String statusDecr, String statusTechDescr) {
    return newInstance(UploadCSVStatus.VALUE_TO_PROCESS, statusDecr, statusTechDescr);
  }

  public static UploadStatusInfo newProcessingInstance(String statusDecr, String statusTechDescr) {
    return newInstance(UploadCSVStatus.VALUE_PROCESSING, statusDecr, statusTechDescr);
  }

  public static UploadStatusInfo newProcessedInstance(String statusDecr, String statusTechDescr) {
    return newInstance(UploadCSVStatus.VALUE_PROCESSED, statusDecr, statusTechDescr);
  }

  public static UploadStatusInfo newErrorInstance(String statusDecr, String statusTechDescr) {
    return newInstance(UploadCSVStatus.VALUE_ERROR, statusDecr, statusTechDescr);
  }

  /** CONSTRUCTOR **/
  public UploadStatusInfo() {
  }


  /** STATUS **/
  public boolean setStatus(UploadCSVStatus status) {
    return setObject(STATUS, status);
  }

  public UploadCSVStatus getStatus() {
    return (UploadCSVStatus)getObject(STATUS);
  }


  /** STATUS_DESCR **/
  public boolean setStatusDescr(String statusDescr) {
    return setString(STATUS_DESCR, statusDescr);
  }

  public String getStatusDescr() {
    return getString(STATUS_DESCR);
  }


  /** STATUS_TECH_DESCR **/
  public boolean setStatusTechDescr(String statusTechDescr) {
    return setString(STATUS_TECH_DESCR, statusTechDescr);
  }

  public String getStatusTechDescr() {
    return getString(STATUS_TECH_DESCR);
  }


  /** STATUS_UPDATED_TS **/
  public boolean setStatusUpdatedTs(Calendar statusUpdatedTs) {
    return setCalendar(STATUS_UPDATED_TS, statusUpdatedTs);
  }

  public Calendar getStatusUpdatedTs() {
    return getCalendar(STATUS_UPDATED_TS);
  }

 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on



}
