package fai.broker.models;

import it.swdes.models.*;

public class ApprovToRiga extends AbstractModel {


  public static int OID;
  public static int RIGA_DETT;
  public static int QUANTITA;

  public static final Attribute[] attributes = buildAttributes(ApprovToRiga.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("rigaDett", null, OrdineInRigaDett.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
    new Attribute("quantita", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public ApprovToRiga() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** RIGA_DETT **/
  public boolean setRigaDett(OrdineInRigaDett rigaDett) {
    return setObject(RIGA_DETT, rigaDett);
  }

  public OrdineInRigaDett getRigaDett() {
    return (OrdineInRigaDett)getObject(RIGA_DETT);
  }


  /** QUANTITA **/
  public boolean setQuantita(Integer quantita) {
    return setInt(QUANTITA, quantita);
  }

  public Integer getQuantita() {
    return getInt(QUANTITA);
  }

 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on



}
