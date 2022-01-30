package fai.imp.farmaclick.models;

import it.swdes.models.*;

public class Fornitore extends AbstractModel {


    public static int OID;
    public static int CODICE;
    public static int CODICE_SITO_LOGISTICO;
    public static int DESCRIZIONE_BREVE;
    public static int LAST_CSV_ZIPPED;
    public static int LAST_CSV_FILENAME;
    public static int LAST_CSV_DOWNLOAD_URL;
    public static int LAST_CSV_DOWNLOAD_AT_TS;
    public static int LAST_CSV_CONFIRM_URL;
    public static int LAST_CSV_CONFIRMED_AT_TS;
    public static int NO_LONGER_EXISTS_TS;
    public static int ALL_DATA_STORED_TS;
    public static int OID_COMFIG;

    public static final Attribute[] attributes = buildAttributes(Fornitore.class, new Attribute[]{ //@formatter:off
            new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("codice", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("codiceSitoLogistico", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("descrizioneBreve", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("lastCsvZipped", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("lastCsvFilename", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("lastCsvDownloadUrl", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("lastCsvDownloadAtTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("lastCsvConfirmUrl", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("lastCsvConfirmedAtTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("noLongerExistsTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("allDataStoredTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("oidConfig", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),

    }); //@formatter:on


    /**
     * CONSTRUCTOR
     **/
    public Fornitore() {
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
     * CODICE
     **/
    public boolean setCodice(String codice) {
        return setString(CODICE, codice);
    }

    public String getCodice() {
        return getString(CODICE);
    }


    /**
     * CODICE_SITO_LOGISTICO
     **/
    public boolean setCodiceSitoLogistico(String codiceSitoLogistico) {
        return setString(CODICE_SITO_LOGISTICO, codiceSitoLogistico);
    }

    public String getCodiceSitoLogistico() {
        return getString(CODICE_SITO_LOGISTICO);
    }


    /**
     * DESCRIZIONE_BREVE
     **/
    public boolean setDescrizioneBreve(String descrizioneBreve) {
        return setString(DESCRIZIONE_BREVE, descrizioneBreve);
    }

    public String getDescrizioneBreve() {
        return getString(DESCRIZIONE_BREVE);
    }


    /**
     * LAST_CSV_ZIPPED
     **/
    public boolean setLastCsvZipped(Boolean lastCsvZipped) {
        return setBoolean(LAST_CSV_ZIPPED, lastCsvZipped);
    }

    public Boolean getLastCsvZipped() {
        return getBoolean(LAST_CSV_ZIPPED);
    }

    public boolean isLastCsvZipped() {
        return isBoolean(LAST_CSV_ZIPPED);
    }


    /**
     * LAST_CSV_FILENAME
     **/
    public boolean setLastCsvFilename(String lastCsvFilename) {
        return setString(LAST_CSV_FILENAME, lastCsvFilename);
    }

    public String getLastCsvFilename() {
        return getString(LAST_CSV_FILENAME);
    }


    /**
     * LAST_CSV_DOWNLOAD_URL
     **/
    public boolean setLastCsvDownloadUrl(String lastCsvDownloadUrl) {
        return setString(LAST_CSV_DOWNLOAD_URL, lastCsvDownloadUrl);
    }

    public String getLastCsvDownloadUrl() {
        return getString(LAST_CSV_DOWNLOAD_URL);
    }


    /**
     * LAST_CSV_DOWNLOAD_AT_TS
     **/
    public boolean setLastCsvDownloadAtTs(java.util.Calendar lastCsvDownloadAtTs) {
        return setCalendar(LAST_CSV_DOWNLOAD_AT_TS, lastCsvDownloadAtTs);
    }

    public java.util.Calendar getLastCsvDownloadAtTs() {
        return getCalendar(LAST_CSV_DOWNLOAD_AT_TS);
    }


    /**
     * LAST_CSV_CONFIRM_URL
     **/
    public boolean setLastCsvConfirmUrl(String lastCsvConfirmUrl) {
        return setString(LAST_CSV_CONFIRM_URL, lastCsvConfirmUrl);
    }

    public String getLastCsvConfirmUrl() {
        return getString(LAST_CSV_CONFIRM_URL);
    }


    /**
     * LAST_CSV_CONFIRMED_AT_TS
     **/
    public boolean setLastCsvConfirmedAtTs(java.util.Calendar lastCsvConfirmedAtTs) {
        return setCalendar(LAST_CSV_CONFIRMED_AT_TS, lastCsvConfirmedAtTs);
    }

    public java.util.Calendar getLastCsvConfirmedAtTs() {
        return getCalendar(LAST_CSV_CONFIRMED_AT_TS);
    }


    /**
     * NO_LONGER_EXISTS_TS
     **/
    public boolean setNoLongerExistsTs(java.util.Calendar noLongerExistsTs) {
        return setCalendar(NO_LONGER_EXISTS_TS, noLongerExistsTs);
    }

    public java.util.Calendar getNoLongerExistsTs() {
        return getCalendar(NO_LONGER_EXISTS_TS);
    }


    /**
     * ALL_DATA_STORED_TS
     **/
    public boolean setAllDataStoredTs(java.util.Calendar allDataStoredTs) {
        return setCalendar(ALL_DATA_STORED_TS, allDataStoredTs);
    }

    public java.util.Calendar getAllDataStoredTs() {
        return getCalendar(ALL_DATA_STORED_TS);
    }


  /**
   * OID_CONFIG
   **/
  public boolean setOidConfig(Long oid) {
    return setLong(OID_COMFIG, oid);
  }

  public Long getOidConfig() {
    return getLong(OID_COMFIG);
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
