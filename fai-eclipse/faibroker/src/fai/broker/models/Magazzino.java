package fai.broker.models;

import fai.common.util.RichProperties;
import it.swdes.models.*;

public class Magazzino extends AbstractModel {


  public static int OID;
  public static int ACRONYM;
  public static int DESCR;
  public static int COMUNE;
  public static int PROVINCIA;
  public static int REGIONE;
  public static int CAP;
  public static int INDIRIZZO;
  public static int TIPO_FARMACO;
  public static int SUPPLIER_SERVICE_CLASS_NAME;
  public static int PROPERTIES;
  public static int ORGANIZATION_CODE;

  public static final Attribute[] attributes = buildAttributes(Magazzino.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("acronym", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("descr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("comune", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("provincia", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("regione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("cap", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("indirizzo", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("tipoFarmaco", null, TipoFarmaco[].class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
    new Attribute("supplierServiceClassName", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("properties", null, Property[].class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
    new Attribute("organizationCode", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public Magazzino() {
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


  /** COMUNE **/
  public boolean setComune(String comune) {
    return setString(COMUNE, comune);
  }

  public String getComune() {
    return getString(COMUNE);
  }


  /** PROVINCIA **/
  public boolean setProvincia(String provincia) {
    return setString(PROVINCIA, provincia);
  }

  public String getProvincia() {
    return getString(PROVINCIA);
  }


  /** REGIONE **/
  public boolean setRegione(String regione) {
    return setString(REGIONE, regione);
  }

  public String getRegione() {
    return getString(REGIONE);
  }


  /** CAP **/
  public boolean setCap(String cap) {
    return setString(CAP, cap);
  }

  public String getCap() {
    return getString(CAP);
  }


  /** INDIRIZZO **/
  public boolean setIndirizzo(String indirizzo) {
    return setString(INDIRIZZO, indirizzo);
  }

  public String getIndirizzo() {
    return getString(INDIRIZZO);
  }


  /** TIPO_FARMACO **/
  public boolean setTipoFarmaco(AbstractModel tipoFarmaco) {
    return setObject(TIPO_FARMACO, tipoFarmaco);
  }

  public AbstractModel getTipoFarmaco() {
    return getObject(TIPO_FARMACO);
  }

  public void setAllTipoFarmaco(TipoFarmaco[] tipoFarmaco) {
    setArray(TIPO_FARMACO, tipoFarmaco);
  }

  public TipoFarmaco[] getAllTipoFarmaco() {
    return (TipoFarmaco[])getArray(TIPO_FARMACO);
  }

  public void addTipoFarmaco(TipoFarmaco tipoFarmaco) {
    addArrayElement(TIPO_FARMACO, tipoFarmaco);
  }

  public void addTipoFarmaco(int index, TipoFarmaco tipoFarmaco) {
    addArrayElement(TIPO_FARMACO, index, tipoFarmaco);
  }

  public boolean removeTipoFarmaco(TipoFarmaco tipoFarmaco) {
    return removeArrayElement(TIPO_FARMACO, tipoFarmaco);
  }

  public TipoFarmaco removeTipoFarmaco(int index) {
    return (TipoFarmaco)removeArrayElement(TIPO_FARMACO, index);
  }

  public TipoFarmaco getTipoFarmacoAt(int index) {
    return (TipoFarmaco)getArrayElement(TIPO_FARMACO, index);
  }

  public void replaceTipoFarmacoAt(int index, TipoFarmaco tipoFarmaco) {
    copyAt(TIPO_FARMACO, tipoFarmaco, index);
  }

  public int getTipoFarmacoCount() {
    return getArrayCount(TIPO_FARMACO);
  }


  /** SUPPLIER_SERVICE_CLASS_NAME **/
  public boolean setSupplierServiceClassName(String supplierServiceClassName) {
    return setString(SUPPLIER_SERVICE_CLASS_NAME, supplierServiceClassName);
  }

  public String getSupplierServiceClassName() {
    return getString(SUPPLIER_SERVICE_CLASS_NAME);
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

  public RichProperties getRichProperties() {
    RichProperties rp = new RichProperties();
    Property[] p = getAllProperty();
    for (int i = 0; i < p.length; i++) {
      rp.setProperty(p[i].getKey(), p[i].getValue());
    }
    return rp;
  }

  /** ORGANIZATION_CODE **/
  public boolean setOrganizationCode(String organizationCode) {
    return setString(ORGANIZATION_CODE, organizationCode);
  }

  public String getOrganizationCode() {
    return getString(ORGANIZATION_CODE);
  }
  
 //@formatter:on


 //@formatter:on


 //@formatter:on


  public boolean isTipoFarmacoSuppported(TipoFarmaco tipoFarmaco) {
    if (tipoFarmaco == null) return false;
    //
    TipoFarmaco [] tf = getAllTipoFarmaco();
    for (int i = 0; i < tf.length; i++) {
      if (tf[i].getAcronym().equals(tipoFarmaco.getAcronym())) {
        return true;
      }
    }
    return false;
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



}
