package fai.broker.models;

import it.swdes.models.*;

public class FornitoreFarmaco extends AbstractModel {


  public static int OID;
  public static int CODICE_MIN_SAN;
  public static int LINE_ID;

  public static final Attribute[] attributes = buildAttributes(FornitoreFarmaco.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("codiceMinSan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("lineId", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public FornitoreFarmaco() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** CODICE_MIN_SAN **/
  public boolean setCodiceMinSan(String codiceMinSan) {
    return setString(CODICE_MIN_SAN, codiceMinSan);
  }

  public String getCodiceMinSan() {
    return getString(CODICE_MIN_SAN);
  }


  /** LINE_ID **/
  public boolean setLineId(Integer lineId) {
    return setInt(LINE_ID, lineId);
  }

  public Integer getLineId() {
    return getInt(LINE_ID);
  }

 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on



}
