package fai.broker.models;

import java.util.Calendar;

import it.swdes.models.*;

public class StatusInfo extends AbstractModel {


  public static int STATUS;
  public static int STATUS_DESCR;
  public static int STATUS_TECH_DESCR;
  public static int STATUS_UPDATED_TS;

  public static final Attribute[] attributes = buildAttributes(StatusInfo.class, new Attribute[] { //@formatter:off
    new Attribute("status", null, ItemStatus.class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
    new Attribute("statusDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("statusTechDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("statusUpdatedTs", null, Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on

  
  public static StatusInfo newInstance(ItemStatus itemStatus, String statusDecr, String statusTechDescr) {
    StatusInfo si = new StatusInfo();
    si.setStatus(itemStatus);
    si.setStatusDescr(statusDecr);
    si.setStatusTechDescr(statusTechDescr);
    si.setStatusUpdatedTs(Calendar.getInstance());
    return si;
  }
  
  public static StatusInfo newToProcessInstance(String statusDecr, String statusTechDescr) {
    return newInstance(ItemStatus.VALUE_TO_PROCESS, statusDecr, statusTechDescr);
  }
  
  public static StatusInfo newProcessingInstance(String statusDecr, String statusTechDescr) {
    return newInstance(ItemStatus.VALUE_PROCESSING, statusDecr, statusTechDescr);
  }
  
  public static StatusInfo newProcessedInstance(String statusDecr, String statusTechDescr) {
    return newInstance(ItemStatus.VALUE_PROCESSED, statusDecr, statusTechDescr);
  }
  
  public static StatusInfo newErrorInstance(String statusDecr, String statusTechDescr) {
    return newInstance(ItemStatus.VALUE_ERROR, statusDecr, statusTechDescr);
  }
  
  public static StatusInfo newSuspendedInstance(String statusDecr, String statusTechDescr) {
    return newInstance(ItemStatus.VALUE_SUSPENDED, statusDecr, statusTechDescr);
  }
  

  /** CONSTRUCTOR **/
  public StatusInfo() {
  }


  /** STATUS **/
  public boolean setStatus(ItemStatus status) {
    return setObject(STATUS, status);
  }

  public ItemStatus getStatus() {
    return (ItemStatus)getObject(STATUS);
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
