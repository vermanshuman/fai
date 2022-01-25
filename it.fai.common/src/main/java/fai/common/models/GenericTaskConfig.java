package fai.common.models;

import it.swdes.models.*;
import fai.common.util.RichProperties;

public class GenericTaskConfig extends AbstractModel {


  public static int OID;
  public static int ACRONYM;
  public static int DESCR;
  public static int IMPL_CLASS_NAME;
  public static int SCHEDULED_TIMES;
  public static int SCHEDULED_SMTWTFS;
  public static int LAST_RUN_START_TS;
  public static int LAST_RUN_END_TS;
  public static int LAST_RUN_DONE;
  public static int LAST_RUN_DESCR;
  public static int ENABLED;
  public static int PROPERTIES;

  public static final Attribute[] attributes = buildAttributes(GenericTaskConfig.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("acronym", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("descr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("implClassName", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("scheduledTimes", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("scheduledSmtwtfs", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunStartTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunEndTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunDone", null, Boolean.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lastRunDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("enabled", null, Boolean.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("properties", null, Property[].class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public GenericTaskConfig() {
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


  /** SCHEDULED_TIMES **/
  public boolean setScheduledTimes(String scheduledTimes) {
    return setString(SCHEDULED_TIMES, scheduledTimes);
  }

  public String getScheduledTimes() {
    return getString(SCHEDULED_TIMES);
  }


  /** SCHEDULED_SMTWTFS **/
  public boolean setScheduledSmtwtfs(String scheduledSmtwtfs) {
    return setString(SCHEDULED_SMTWTFS, scheduledSmtwtfs);
  }

  public String getScheduledSmtwtfs() {
    return getString(SCHEDULED_SMTWTFS);
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


  /** ENABLED **/
  public boolean setEnabled(Boolean enabled) {
    return setBoolean(ENABLED, enabled);
  }

  public Boolean getEnabled() {
    return getBoolean(ENABLED);
  }

  public boolean isEnabled() {
    return isBoolean(ENABLED);
  }


  /** PROPERTIES **/
  public boolean setProperties(AbstractModel properties) {
    return setObject(PROPERTIES, properties);
  }

  public AbstractModel getProperties() {
    return getObject(PROPERTIES);
  }

  public void setAllProperty(Property[] properties) {
    setArray(PROPERTIES, properties);
  }

  public Property[] getAllProperty() {
    return (Property[])getArray(PROPERTIES);
  }

  public void addProperty(Property property) {
    addArrayElement(PROPERTIES, property);
  }

  public void addProperty(int index, Property property) {
    addArrayElement(PROPERTIES, index, property);
  }

  public boolean removeProperty(Property property) {
    return removeArrayElement(PROPERTIES, property);
  }

  public Property removeProperty(int index) {
    return (Property)removeArrayElement(PROPERTIES, index);
  }

  public Property getPropertyAt(int index) {
    return (Property)getArrayElement(PROPERTIES, index);
  }

  public void replacePropertyAt(int index, Property property) {
    copyAt(PROPERTIES, property, index);
  }

  public int getPropertyCount() {
    return getArrayCount(PROPERTIES);
  }

 //@formatter:on


  public RichProperties getRichProperties() {
  	RichProperties rp = new RichProperties();
  	Property[] p = getAllProperty();
  	for (int i = 0; i < p.length; i++) {
      rp.setProperty(p[i].getKey(), p[i].getValue());
    }
  	return rp;
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



}
