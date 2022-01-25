package fai.broker.models;

import fai.common.util.RichProperties;
import it.swdes.models.*;

public class Fornitore extends AbstractModel {


  public static int OID;
  public static int CODICE;
  public static int CODICE_FARMACLICK;
  public static int FARMACO;
  public static int SUPPLIER_SERVICE_CLASS_NAME;
  public static int PROPERTIES;

  public static final Attribute[] attributes = buildAttributes(Fornitore.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("codice", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("codiceFarmaclick", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("farmaco", null, FornitoreFarmaco[].class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
    new Attribute("supplierServiceClassName", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("properties", null, Property[].class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public Fornitore() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** CODICE **/
  public boolean setCodice(String codice) {
    return setString(CODICE, codice);
  }

  public String getCodice() {
    return getString(CODICE);
  }


  /** CODICE_FARMACLICK **/
  public boolean setCodiceFarmaclick(String codiceFarmaclick) {
    return setString(CODICE_FARMACLICK, codiceFarmaclick);
  }

  public String getCodiceFarmaclick() {
    return getString(CODICE_FARMACLICK);
  }


  /** FARMACO **/
  public boolean setFarmaco(AbstractModel farmaco) {
    return setObject(FARMACO, farmaco);
  }

  public AbstractModel getFarmaco() {
    return getObject(FARMACO);
  }

  public void setAllFarmaco(FornitoreFarmaco[] farmaco) {
    setArray(FARMACO, farmaco);
  }

  public FornitoreFarmaco[] getAllFarmaco() {
    return (FornitoreFarmaco[])getArray(FARMACO);
  }

  public void addFarmaco(FornitoreFarmaco farmaco) {
    addArrayElement(FARMACO, farmaco);
  }

  public void addFarmaco(int index, FornitoreFarmaco farmaco) {
    addArrayElement(FARMACO, index, farmaco);
  }

  public boolean removeFarmaco(FornitoreFarmaco farmaco) {
    return removeArrayElement(FARMACO, farmaco);
  }

  public FornitoreFarmaco removeFarmaco(int index) {
    return (FornitoreFarmaco)removeArrayElement(FARMACO, index);
  }

  public FornitoreFarmaco getFarmacoAt(int index) {
    return (FornitoreFarmaco)getArrayElement(FARMACO, index);
  }

  public void replaceFarmacoAt(int index, FornitoreFarmaco farmaco) {
    copyAt(FARMACO, farmaco, index);
  }

  public int getFarmacoCount() {
    return getArrayCount(FARMACO);
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
