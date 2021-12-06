package fai.broker.models;

import it.swdes.models.*;

public class TipoFarmaco extends AbstractModel {


  public static int OID;
  public static int ACRONYM;
  public static int DESCR;

  public static final Attribute[] attributes = buildAttributes(TipoFarmaco.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("acronym", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("descr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on

  
  public static TipoFarmaco VALUE_FARMACO = new TipoFarmaco(new Long(1), "FARMACO", "FARMACO");
  public static TipoFarmaco VALUE_PARAFARMACO = new TipoFarmaco(new Long(2), "PARAFARMACO", "PARAFARMACO");


  /** CONSTRUCTOR **/
  public TipoFarmaco() {
  }

  
  public TipoFarmaco(Long oid, String acronym, String descr) {
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
