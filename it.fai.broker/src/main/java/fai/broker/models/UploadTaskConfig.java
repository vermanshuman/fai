package fai.broker.models;

import it.swdes.models.AbstractModel;
import it.swdes.models.Attribute;

public class UploadTaskConfig extends AbstractModel {

    public static int OID;
    public static int MAGAZZINO_ACRONYM;
    public static int DESCR;
    public static int CSV_FILE_NAME;
    public static int STATUS;
    public static int CREATION_TS;
    public static int RUN_START_TS;
    public static int RUN_END_TS;
    public static int RUN_DONE;
    public static int RUN_DESCR;
    public static int ORDER_COUNT;
    public static int ORDER_STATUS;

    public static final Attribute[] attributes = buildAttributes(UploadTaskConfig.class, new Attribute[]{ //@formatter:off
            new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("magazzinoAcronym", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("descr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("csvFileName", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("status", null, UploadStatusInfo.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
            new Attribute("creationTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("runStartTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("runEndTs", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("runDone", null, Boolean.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("runDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("orderCount", null, Integer.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("orderStatus", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    }); //@formatter:on


    /**
     * CONSTRUCTOR
     **/
    public UploadTaskConfig() {
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
     * MAGAZZINO_ACRONYM
     **/
    public boolean setMagazzinoAcronym(String acronym) {
        return setString(MAGAZZINO_ACRONYM, acronym);
    }

    public String getMagazzinoAcronym() {
        return getString(MAGAZZINO_ACRONYM);
    }


    /**
     * DESCR
     **/
    public boolean setDescr(String descr) {
        return setString(DESCR, descr);
    }

    public String getDescr() {
        return getString(DESCR);
    }


    /**
     * CSV_FILE_NAME
     **/
    public boolean setCsvFileName(String csvFileName) {
        return setString(CSV_FILE_NAME, csvFileName);
    }

    public String getCsvFileName() {
        return getString(CSV_FILE_NAME);
    }

    /**
     * STATUS
     **/
    public boolean setStatus(UploadStatusInfo status) {
        return setObject(STATUS, status);
    }

    public UploadStatusInfo getStatus() {
        return (UploadStatusInfo) getObject(STATUS);
    }


    /**
     * CREATION_TS
     **/
    public boolean setCreationTs(java.util.Calendar creationTs) {
        return setCalendar(CREATION_TS, creationTs);
    }

    public java.util.Calendar getCreationTs() {
        return getCalendar(CREATION_TS);
    }


    /**
     * RUN_START_TS
     **/
    public boolean setRunStartTs(java.util.Calendar runStartTs) {
        return setCalendar(RUN_START_TS, runStartTs);
    }

    public java.util.Calendar getRunStartTs() {
        return getCalendar(RUN_START_TS);
    }


    /**
     * RUN_END_TS
     **/
    public boolean setLastRunEndTs(java.util.Calendar runEndTs) {
        return setCalendar(RUN_END_TS, runEndTs);
    }

    public java.util.Calendar getRunEndTs() {
        return getCalendar(RUN_END_TS);
    }


    /**
     * RUN_DONE
     **/
    public boolean setRunDone(Boolean runDone) {
        return setBoolean(RUN_DONE, runDone);
    }

    public Boolean getRunDone() {
        return getBoolean(RUN_DONE);
    }

    public boolean isRunDone() {
        return isBoolean(RUN_DONE);
    }


    /**
     * RUN_DESCR
     **/
    public boolean setRunDescr(String runDescr) {
        return setString(RUN_DESCR, runDescr);
    }

    public String getRunDescr() {
        return getString(RUN_DESCR);
    }

    /**
     * ORDER_COUNT
     **/
    public boolean setOrderCount(Integer orderCount) {
        return setInt(ORDER_COUNT, orderCount);
    }

    public Integer getOrderCount() {
        return getInt(ORDER_COUNT);
    }


    /**
     * ORDER_COUNT
     **/
    public boolean setOrderStatus(String orderStatus) {
        return setString(ORDER_STATUS, orderStatus);
    }

    public String getOrderStatus() {
        return getString(ORDER_STATUS);
    }
}
