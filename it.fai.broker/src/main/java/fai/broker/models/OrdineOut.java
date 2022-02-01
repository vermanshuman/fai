package fai.broker.models;

import it.swdes.models.*;

public class OrdineOut extends AbstractModel {


    public static int OID;
    public static int FORNITORE;
    public static int ID_ORDINE;
    public static int ID_RICEVUTA;
    public static int PREZZO_TOTALE;
    public static int ALIQUOTA_IVA;
    public static int ALIQUOTA_IVA_INCLUSA;
    public static int STATUS;
    public static int REQUEST_XML;
    public static int RESPONSE_XMl;

    public static final Attribute[] attributes = buildAttributes(OrdineOut.class, new Attribute[]{ //@formatter:off
            new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("fornitore", null, Fornitore.class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
            new Attribute("idOrdine", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("idRicevuta", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("prezzoTotale", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("aliquotaIva", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("aliquotaIvaInclusa", null, Boolean.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("status", null, ItemStatus.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
            new Attribute("requestXml", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("responseXml", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    }); //@formatter:on


    /**
     * CONSTRUCTOR
     **/
    public OrdineOut() {
    }


    /**
     * OID
     **/
    public boolean setOid(Long oid) {
        return setLong(OID, oid);
    }

    public Long getOid() {
        return getLong(OID);
    }


    /**
     * FORNITORE
     **/
    public boolean setFornitore(Fornitore fornitore) {
        return setObject(FORNITORE, fornitore);
    }

    public Fornitore getFornitore() {
        return (Fornitore) getObject(FORNITORE);
    }


    /**
     * ID_ORDINE
     **/
    public boolean setIdOrdine(String idOrdine) {
        return setString(ID_ORDINE, idOrdine);
    }

    public String getIdOrdine() {
        return getString(ID_ORDINE);
    }


    /**
     * ID_RICEVUTA
     **/
    public boolean setIdRicevuta(String idRicevuta) {
        return setString(ID_RICEVUTA, idRicevuta);
    }

    public String getIdRicevuta() {
        return getString(ID_RICEVUTA);
    }


    /**
     * PREZZO_TOTALE
     **/
    public boolean setPrezzoTotale(Double prezzoTotale) {
        return setDouble(PREZZO_TOTALE, prezzoTotale);
    }

    public Double getPrezzoTotale() {
        return getDouble(PREZZO_TOTALE);
    }


    /**
     * ALIQUOTA_IVA
     **/
    public boolean setAliquotaIva(Double aliquotaIva) {
        return setDouble(ALIQUOTA_IVA, aliquotaIva);
    }

    public Double getAliquotaIva() {
        return getDouble(ALIQUOTA_IVA);
    }


    /**
     * ALIQUOTA_IVA_INCLUSA
     **/
    public boolean setAliquotaIvaInclusa(Boolean aliquotaIvaInclusa) {
        return setBoolean(ALIQUOTA_IVA_INCLUSA, aliquotaIvaInclusa);
    }

    public Boolean getAliquotaIvaInclusa() {
        return getBoolean(ALIQUOTA_IVA_INCLUSA);
    }

    public boolean isAliquotaIvaInclusa() {
        return isBoolean(ALIQUOTA_IVA_INCLUSA);
    }


    /**
     * STATUS
     **/
    public boolean setStatus(ItemStatus status) {
        return setObject(STATUS, status);
    }

    public ItemStatus getStatus() {
        return (ItemStatus) getObject(STATUS);
    }

  /** REQUEST_XML **/
  public boolean setRequestXml(String xml) {
    return setString(REQUEST_XML, xml);
  }

  public String getRequestXml() {
    return getString(REQUEST_XML);
  }


  /** RESPONSE_XMl **/
  public boolean setResponseXml(String xml) {
    return setString(RESPONSE_XMl, xml);
  }

  public String getResponseXml() {
    return getString(RESPONSE_XMl);
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
