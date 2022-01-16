package fai.broker.models;

import it.swdes.models.AbstractModel;
import it.swdes.models.Attribute;

import java.util.Hashtable;

public class UploadCSVStatus extends AbstractModel {


  public static int OID;
  public static int ACRONYM;
  public static int DESCR;

  public static final Attribute[] attributes = buildAttributes(UploadCSVStatus.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("acronym", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("descr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  public static UploadCSVStatus VALUE_TO_PROCESS = new UploadCSVStatus(new Long(1), "TO PROCESS", "TO PROCESS");
  public static UploadCSVStatus VALUE_PROCESSING = new UploadCSVStatus(new Long(2), "PROCESSING", "PROCESSING");
  public static UploadCSVStatus VALUE_PROCESSED = new UploadCSVStatus(new Long(3), "PROCESSED", "PROCESSED");
  public static UploadCSVStatus VALUE_ERROR = new UploadCSVStatus(new Long(4), "ERROR", "ERROR");

  private static Hashtable<Long, UploadCSVStatus> statusByOid = new Hashtable<Long, UploadCSVStatus>();
  private static Hashtable<String, UploadCSVStatus> statusByAcronym = new Hashtable<String, UploadCSVStatus>();
  static {
    UploadCSVStatus[] status = new UploadCSVStatus[] { VALUE_TO_PROCESS, VALUE_PROCESSING, VALUE_PROCESSED, VALUE_ERROR };
    for (int i = 0; i < status.length; i++) {
      statusByOid.put(status[i].getOid(), status[i]);
      statusByAcronym.put(status[i].getAcronym(), status[i]);
    }
  }

  public static UploadCSVStatus getByOid(Long oid) {
    UploadCSVStatus is = statusByOid.get(oid);
    if (is == null) throw new IllegalStateException("nessun "+ UploadCSVStatus.class.getName()+" trovato per OID "+oid);
    return is;
  }

  public static UploadCSVStatus getByAcronym(String acronym) {
    UploadCSVStatus is = statusByAcronym.get(acronym);
    if (is == null) throw new IllegalStateException("nessun "+ UploadCSVStatus.class.getName()+" trovato per ACRONYM "+acronym);
    return is;
  }


  /** CONSTRUCTOR **/

  public UploadCSVStatus() {
  }

  public UploadCSVStatus(Long oid, String acronym, String descr) {
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


}
