package fai.broker.models;

import it.swdes.models.*;

public class DisponibilitaReqTemp extends AbstractModel {


  public static int OID;
  public static int CODICE_MIN_SAN;
  public static int CODICE_EAN;
  public static int QUANTITA_RICHIESTA;
  public static int RESP;

  public static final Attribute[] attributes = buildAttributes(DisponibilitaReqTemp.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("codiceMinSan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("codiceEan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("quantitaRichiesta", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("resp", null, DisponibilitaResTemp[].class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public DisponibilitaReqTemp() {
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


  /** CODICE_EAN **/
  public boolean setCodiceEan(String codiceEan) {
    return setString(CODICE_EAN, codiceEan);
  }

  public String getCodiceEan() {
    return getString(CODICE_EAN);
  }


  /** QUANTITA_RICHIESTA **/
  public boolean setQuantitaRichiesta(Integer quantitaRichiesta) {
    return setInt(QUANTITA_RICHIESTA, quantitaRichiesta);
  }

  public Integer getQuantitaRichiesta() {
    return getInt(QUANTITA_RICHIESTA);
  }


  /** RESP **/
  public boolean setResp(AbstractModel resp) {
    return setObject(RESP, resp);
  }

  public AbstractModel getResp() {
    return getObject(RESP);
  }

  public void setAllResp(DisponibilitaResTemp[] resp) {
    setArray(RESP, resp);
  }

  public DisponibilitaResTemp[] getAllResp() {
    return (DisponibilitaResTemp[])getArray(RESP);
  }

  public void addResp(DisponibilitaResTemp resp) {
    addArrayElement(RESP, resp);
  }

  public void addResp(int index, DisponibilitaResTemp resp) {
    addArrayElement(RESP, index, resp);
  }

  public boolean removeResp(DisponibilitaResTemp resp) {
    return removeArrayElement(RESP, resp);
  }

  public DisponibilitaResTemp removeResp(int index) {
    return (DisponibilitaResTemp)removeArrayElement(RESP, index);
  }

  public DisponibilitaResTemp getRespAt(int index) {
    return (DisponibilitaResTemp)getArrayElement(RESP, index);
  }

  public void replaceRespAt(int index, DisponibilitaResTemp resp) {
    copyAt(RESP, resp, index);
  }

  public int getRespCount() {
    return getArrayCount(RESP);
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


 //@formatter:on


 //@formatter:on



}
