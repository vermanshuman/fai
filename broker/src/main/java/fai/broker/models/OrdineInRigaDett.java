package fai.broker.models;

import it.swdes.models.*;

public class OrdineInRigaDett extends AbstractModel {


  public static int OID;
  public static int COUPON;
  public static int NUMERO_OGGETTO;
  public static int CODICE_MIN_SAN;
  public static int CODICE_EAN;
  public static int TITOLO_INSERZIONE;
  public static int ETICHETTA_PERSONALIZZATA;
  public static int QUANTITA;
  public static int PREZZO_FINALE;
  public static int ALIQUOTA_IVA_INCLUSA;
  public static int ID_PRODOTTO_UNIVOCO;
  public static int PRODUCTIDTYPE;
  public static int PRODUCTIDVALUE2;
  public static int PRODUCTIDVALUE;
  public static int DETTAGLI_VARIAZIONE;
  public static int MAGAZZINO_ACRONYM;
  public static int ORDINE_IN;

  public static final Attribute[] attributes = buildAttributes(OrdineInRigaDett.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("coupon", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("numeroOggetto", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("codiceMinSan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("codiceEan", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("titoloInserzione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("etichettaPersonalizzata", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("quantita", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("prezzoFinale", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("aliquotaIvaInclusa", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("idProdottoUnivoco", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("productidtype", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("productidvalue2", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("productidvalue", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("dettagliVariazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("magazzinoAcronym", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("ordineIn", null, OrdineIn.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public OrdineInRigaDett() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** COUPON **/
  public boolean setCoupon(Boolean coupon) {
    return setBoolean(COUPON, coupon);
  }

  public Boolean getCoupon() {
    return getBoolean(COUPON);
  }

  public boolean isCoupon() {
    return isBoolean(COUPON);
  }


  /** NUMERO_OGGETTO **/
  public boolean setNumeroOggetto(String numeroOggetto) {
    return setString(NUMERO_OGGETTO, numeroOggetto);
  }

  public String getNumeroOggetto() {
    return getString(NUMERO_OGGETTO);
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


  /** TITOLO_INSERZIONE **/
  public boolean setTitoloInserzione(String titoloInserzione) {
    return setString(TITOLO_INSERZIONE, titoloInserzione);
  }

  public String getTitoloInserzione() {
    return getString(TITOLO_INSERZIONE);
  }


  /** ETICHETTA_PERSONALIZZATA **/
  public boolean setEtichettaPersonalizzata(String etichettaPersonalizzata) {
    return setString(ETICHETTA_PERSONALIZZATA, etichettaPersonalizzata);
  }

  public String getEtichettaPersonalizzata() {
    return getString(ETICHETTA_PERSONALIZZATA);
  }


  /** QUANTITA **/
  public boolean setQuantita(Integer quantita) {
    return setInt(QUANTITA, quantita);
  }

  public Integer getQuantita() {
    return getInt(QUANTITA);
  }


  /** PREZZO_FINALE **/
  public boolean setPrezzoFinale(Double prezzoFinale) {
    return setDouble(PREZZO_FINALE, prezzoFinale);
  }

  public Double getPrezzoFinale() {
    return getDouble(PREZZO_FINALE);
  }


  /** ALIQUOTA_IVA_INCLUSA **/
  public boolean setAliquotaIvaInclusa(Integer aliquotaIvaInclusa) {
    return setInt(ALIQUOTA_IVA_INCLUSA, aliquotaIvaInclusa);
  }

  public Integer getAliquotaIvaInclusa() {
    return getInt(ALIQUOTA_IVA_INCLUSA);
  }


  /** ID_PRODOTTO_UNIVOCO **/
  public boolean setIdProdottoUnivoco(String idProdottoUnivoco) {
    return setString(ID_PRODOTTO_UNIVOCO, idProdottoUnivoco);
  }

  public String getIdProdottoUnivoco() {
    return getString(ID_PRODOTTO_UNIVOCO);
  }


  /** PRODUCTIDTYPE **/
  public boolean setProductidtype(String productidtype) {
    return setString(PRODUCTIDTYPE, productidtype);
  }

  public String getProductidtype() {
    return getString(PRODUCTIDTYPE);
  }


  /** PRODUCTIDVALUE2 **/
  public boolean setProductidvalue2(Double productidvalue2) {
    return setDouble(PRODUCTIDVALUE2, productidvalue2);
  }

  public Double getProductidvalue2() {
    return getDouble(PRODUCTIDVALUE2);
  }


  /** PRODUCTIDVALUE **/
  public boolean setProductidvalue(Double productidvalue) {
    return setDouble(PRODUCTIDVALUE, productidvalue);
  }

  public Double getProductidvalue() {
    return getDouble(PRODUCTIDVALUE);
  }


  /** DETTAGLI_VARIAZIONE **/
  public boolean setDettagliVariazione(String dettagliVariazione) {
    return setString(DETTAGLI_VARIAZIONE, dettagliVariazione);
  }

  public String getDettagliVariazione() {
    return getString(DETTAGLI_VARIAZIONE);
  }


  /** MAGAZZINO_ACRONYM **/
  public boolean setMagazzinoAcronym(String magazzinoAcronym) {
    return setString(MAGAZZINO_ACRONYM, magazzinoAcronym);
  }

  public String getMagazzinoAcronym() {
	  return getString(MAGAZZINO_ACRONYM);
  }
  


  /** ORDINE_IN **/
  public boolean setOrdineIn(OrdineIn ordineIn) {
    return setObject(ORDINE_IN, ordineIn);
  }

  public OrdineIn getOrdineIn() {
    return (OrdineIn)getObject(ORDINE_IN);
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
