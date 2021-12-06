package fai.broker.models;

import it.swdes.models.*;

public class DisponibilitaResTemp extends AbstractModel {


  public static int OID;
  public static int QUANTITA_DISPONIBILE;

  public static final Attribute[] attributes = buildAttributes(DisponibilitaResTemp.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("quantitaDisponibile", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public DisponibilitaResTemp() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** QUANTITA_DISPONIBILE **/
  public boolean setQuantitaDisponibile(Integer quantitaDisponibile) {
    return setInt(QUANTITA_DISPONIBILE, quantitaDisponibile);
  }

  public Integer getQuantitaDisponibile() {
    return getInt(QUANTITA_DISPONIBILE);
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



}
