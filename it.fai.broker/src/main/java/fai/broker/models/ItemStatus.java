package fai.broker.models;

import java.util.Hashtable;

import it.swdes.models.*;

public class ItemStatus extends AbstractModel {


  public static int OID;
  public static int ACRONYM;
  public static int DESCR;

  public static final Attribute[] attributes = buildAttributes(ItemStatus.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("acronym", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("descr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  public static ItemStatus VALUE_TO_PROCESS = new ItemStatus(new Long(1), "TO PROCESS", "TO PROCESS");
  public static ItemStatus VALUE_PROCESSING = new ItemStatus(new Long(2), "PROCESSING", "PROCESSING");
  public static ItemStatus VALUE_PROCESSED = new ItemStatus(new Long(3), "PROCESSED", "PROCESSED");
  public static ItemStatus VALUE_ERROR = new ItemStatus(new Long(4), "ERROR", "ERROR");
  public static ItemStatus VALUE_SUSPENDED = new ItemStatus(new Long(5), "SUSPENDED", "SUSPENDED");
  public static ItemStatus VALUE_TO_APPROVE = new ItemStatus(new Long(6), "TO APPROVE", "TO APPROVE");
  public static ItemStatus VALUE_APPROVED = new ItemStatus(new Long(7), "APPROVED", "APPROVED");
  public static ItemStatus VALUE_VOIDED = new ItemStatus(new Long(8), "VOIDED", "VOIDED");
  
  private static Hashtable<Long, ItemStatus> statusByOid = new Hashtable<Long, ItemStatus>(); 
  private static Hashtable<String, ItemStatus> statusByAcronym = new Hashtable<String, ItemStatus>();
  static {
    ItemStatus [] status = new ItemStatus[] { VALUE_TO_PROCESS, VALUE_PROCESSING, VALUE_PROCESSED, VALUE_ERROR, VALUE_SUSPENDED };
    for (int i = 0; i < status.length; i++) {
      statusByOid.put(status[i].getOid(), status[i]);
      statusByAcronym.put(status[i].getAcronym(), status[i]);
    }
  }
  
  public static ItemStatus getByOid(Long oid) {
    ItemStatus is = statusByOid.get(oid);
    if (is == null) throw new IllegalStateException("nessun "+ItemStatus.class.getName()+" trovato per OID "+oid);
    return is;
  }
  
  public static ItemStatus getByAcronym(String acronym) {
    ItemStatus is = statusByAcronym.get(acronym);
    if (is == null) throw new IllegalStateException("nessun "+ItemStatus.class.getName()+" trovato per ACRONYM "+acronym);
    return is;
  }
  
  
  /** CONSTRUCTOR **/
  
  public ItemStatus() {
  }

  public ItemStatus(Long oid, String acronym, String descr) {
    this.setOid(oid);
    this.setAcronym(acronym);
    this.setDescr(descr);
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

 //@formatter:on


 //@formatter:on


 //@formatter:on



}
