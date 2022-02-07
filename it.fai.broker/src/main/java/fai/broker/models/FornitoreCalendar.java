package fai.broker.models;

import java.util.Calendar;

import it.swdes.models.AbstractModel;
import it.swdes.models.Attribute;

public class FornitoreCalendar extends AbstractModel {


  public static int OID;
  public static int OID_FORNITORE;
  public static int DATE_OF_WEEK;
  public static int HOUR_START;
  public static int HOUR_END;
  public static int ATTEMPT_NUMBER;

  public static final Attribute[] attributes = buildAttributes(FornitoreCalendar.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("oidFornitore", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("dateOfWeek", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("hourStart", null, Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("hourEnd", null, Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("attemptNumber", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); 


  /** CONSTRUCTOR **/
  public FornitoreCalendar() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }
  
  /** OID_FORNITORE **/
  public boolean setOidFornitore(Long oidFornitore) {
    return setLong(OID_FORNITORE, oidFornitore);
  }

  public Long getOidFornitore() {
    return getLong(OID_FORNITORE);
  }
  
  /** DATE_OF_WEEK **/
  public boolean setDateOfWeek(Integer dateOfWeek) {
    return setInt(DATE_OF_WEEK, dateOfWeek);
  }

  public Integer getDateOfWeek() {
    return getInt(DATE_OF_WEEK);
  }
  
  /** HOUR_START **/
  public boolean setHourStart(Calendar hourStart) {
    return setCalendar(HOUR_START, hourStart);
  }

  public Calendar getHourStart() {
    return getCalendar(HOUR_START);
  }
  
  /** HOUR_END **/
  public boolean setHourEnd(Calendar hourEnd) {
    return setCalendar(HOUR_END, hourEnd);
  }

  public Calendar getHourEnd() {
    return getCalendar(HOUR_END);
  }
  
  /** ATTEMPT_NUMBER **/
  public boolean setAttemptNumber(Integer attemptNumber) {
    return setInt(ATTEMPT_NUMBER, attemptNumber);
  }

  public Integer getAttemptNumber() {
    return getInt(ATTEMPT_NUMBER);
  }
}
