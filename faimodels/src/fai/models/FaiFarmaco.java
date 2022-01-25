package fai.models;

import it.swdes.models.*;

public class FaiFarmaco extends AbstractModel {

public static int  OID  ;
public static int  IS_DELETED  ;
public static int  COD_MINSAN ;
public static int  COD_EAN  ;
public static int  DESCR_PROD  ;
public static int  ANAGRAFICA  ;
public static int  DESCR_TIPO_PROD ;
public static int  DESCR_ESTESA_TIPO_PROD  ;

  public static final Attribute[] attributes = buildAttributes(FaiFarmaco.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("isDeleted", null, Boolean.class, Boolean.FALSE, true, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("codMinsan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("codEan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("descrProd", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("descrTipoPRod", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("anagrafica", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("descrEstesaTipoProd", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
 
  }); //@formatter:on

  /** CONSTRUCTOR **/
  public FaiFarmaco() {
  }

  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }
  
 
  /** IS_DELETED **/
  public boolean setIsDeleted(Boolean isDeleted) {
    return setBoolean(IS_DELETED, isDeleted);
  }

  public Boolean getIsDeleted() {
    return getBoolean(IS_DELETED);
  }

  public boolean isDeleted() {
    return isBoolean(IS_DELETED);
  }
  
  /** DESCR_PROD **/
  public boolean setDescrProd(String descrProd) {
    return setString(DESCR_PROD, descrProd);
  }

  public String getDescrProd() {
    return getString(DESCR_PROD);
  }
  
  /** DESCR_TIPO_PROD **/
  public boolean setDescrTipoProd(String descrTipoProd) {
    return setString(DESCR_TIPO_PROD, descrTipoProd);
  }

  public String getDescrTipoProd() {
    return getString(DESCR_TIPO_PROD);
  }
  
  /** DESCR_ESTESA_TIPO_PROD **/
  public boolean setDescrEstesoTipoProd(String descrEstesaTipoProd) {
    return setString(DESCR_ESTESA_TIPO_PROD, descrEstesaTipoProd);
  }

  public String getDescrEstesaTipoProd() {
    return getString(DESCR_ESTESA_TIPO_PROD);
  }

  
  /** ANAGRAFICA **/
  public boolean setAnagrafica(String anag) {
    return setString(ANAGRAFICA, anag);
  }

  public String getAnagrafica() {
    return getString(ANAGRAFICA);
  }
  
  
  /** COD_MINSAN **/
  public boolean setCodMinsan(String codMinsan) {
    return setString(COD_MINSAN, codMinsan);
  }

  public String getCodMinsan() {
    return getString(COD_MINSAN);
  }  
  
  /** COD_EAN **/
  public boolean setCodEan(String codEan) {
    return setString(COD_EAN, codEan);
  }

  public String getCodEan() {
    return getString(COD_EAN);
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



}
