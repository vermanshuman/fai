package fai.broker.models;

import it.swdes.models.*;

public class OrdineInCollection extends AbstractModel {


  public static int OID;
  public static int BATCH_ID;
  public static int INPUT_RESOURCE;
  public static int INPUT_RESOURCE_FULL_PATH;
  public static int STATUS;
  public static int ORDINE;
  public static int UNIQUE_ID;
  public static int MAGAZZINO_ACRONYM;

  public static final Attribute[] attributes = buildAttributes(OrdineInCollection.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("batchId", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("inputResource", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("inputResourceFullPath", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("status", null, StatusInfo.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
    new Attribute("ordine", null, OrdineIn[].class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
    new Attribute("uniqueID", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("magazzinoAcronym", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public OrdineInCollection() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** BATCH_ID **/
  public boolean setBatchId(Long batchId) {
    return setLong(BATCH_ID, batchId);
  }

  public Long getBatchId() {
    return getLong(BATCH_ID);
  }


  /** INPUT_RESOURCE **/
  public boolean setInputResource(String inputResource) {
    return setString(INPUT_RESOURCE, inputResource);
  }

  public String getInputResource() {
    return getString(INPUT_RESOURCE);
  }


  /** INPUT_RESOURCE_FULL_PATH **/
  public boolean setInputResourceFullPath(String inputResourceFullPath) {
    return setString(INPUT_RESOURCE_FULL_PATH, inputResourceFullPath);
  }

  public String getInputResourceFullPath() {
    return getString(INPUT_RESOURCE_FULL_PATH);
  }


  /** STATUS **/
  public boolean setStatus(StatusInfo status) {
    return setObject(STATUS, status);
  }

  public StatusInfo getStatus() {
    return (StatusInfo)getObject(STATUS);
  }


  /** ORDINE **/
  public boolean setOrdine(AbstractModel ordine) {
    return setObject(ORDINE, ordine);
  }

  public AbstractModel getOrdine() {
    return getObject(ORDINE);
  }

  public void setAllOrdine(OrdineIn[] ordine) {
    setArray(ORDINE, ordine);
  }

  public OrdineIn[] getAllOrdine() {
    return (OrdineIn[])getArray(ORDINE);
  }

  public void addOrdine(OrdineIn ordine) {
    addArrayElement(ORDINE, ordine);
  }

  public void addOrdine(int index, OrdineIn ordine) {
    addArrayElement(ORDINE, index, ordine);
  }

  public boolean removeOrdine(OrdineIn ordine) {
    return removeArrayElement(ORDINE, ordine);
  }

  public OrdineIn removeOrdine(int index) {
    return (OrdineIn)removeArrayElement(ORDINE, index);
  }

  public OrdineIn getOrdineAt(int index) {
    return (OrdineIn)getArrayElement(ORDINE, index);
  }

  public void replaceOrdineAt(int index, OrdineIn ordine) {
    copyAt(ORDINE, ordine, index);
  }

  public int getOrdineCount() {
    return getArrayCount(ORDINE);
  }



  /** UNIQUE_ID **/
  public boolean setUniqueID(String uniqueID) {
    return setString(UNIQUE_ID, uniqueID);
  }

  public String getUniqueID() {
    return getString(UNIQUE_ID);
  }
  
  /** MAGAZZINO_ACRONYM **/
  public boolean setMagazzinoAcronym(String magazzinoAcronym) {
    return setString(MAGAZZINO_ACRONYM, magazzinoAcronym);
  }

  public String getMagazzinoAcronym() {
	  return getString(MAGAZZINO_ACRONYM);
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



}
