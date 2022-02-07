package fai.broker.models;

import it.swdes.models.AbstractModel;
import it.swdes.models.Attribute;

import java.util.Calendar;

public class ExecutionStatusInfo extends AbstractModel {


    public static int STATUS;
    public static int STATUS_DESCR;
    public static int STATUS_TECH_DESCR;
    public static int STATUS_UPDATED_TS;

    public static final Attribute[] attributes = buildAttributes(ExecutionStatusInfo.class, new Attribute[]{ //@formatter:off
            new Attribute("status", null, ExecutionStatus.class, null, false, Attribute.DETAIL, true, Attribute.NO_OPTION),
            new Attribute("statusDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("statusTechDescr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
            new Attribute("statusUpdatedTs", null, Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    }); //@formatter:on


    public static ExecutionStatusInfo newInstance(ExecutionStatus executionStatus, String statusDecr, String statusTechDescr) {
        ExecutionStatusInfo si = new ExecutionStatusInfo();
        si.setStatus(executionStatus);
        si.setStatusDescr(statusDecr);
        si.setStatusTechDescr(statusTechDescr);
        si.setStatusUpdatedTs(Calendar.getInstance());
        return si;
    }

    public static ExecutionStatusInfo importCSVInstance(String statusDecr, String statusTechDescr) {
        return newInstance(ExecutionStatus.IMPORT_CSV, statusDecr, statusTechDescr);
    }

    public static ExecutionStatusInfo parsingCSVInstance(String statusDecr, String statusTechDescr) {
        return newInstance(ExecutionStatus.PARSE_CSV, statusDecr, statusTechDescr);
    }

    public static ExecutionStatusInfo getInstance(ExecutionStatus status, String statusDecr, String statusTechDescr) {
        return newInstance(status, statusDecr, statusTechDescr);
    }

    public static ExecutionStatusInfo failedInstance(String statusDecr, String statusTechDescr) {
        return newInstance(ExecutionStatus.EXECUTION_FAILED, statusDecr, statusTechDescr);
    }

    /**
     * CONSTRUCTOR
     **/
    public ExecutionStatusInfo() {
    }


    /**
     * STATUS
     **/
    public boolean setStatus(ExecutionStatus status) {
        return setObject(STATUS, status);
    }

    public ExecutionStatus getStatus() {
        return (ExecutionStatus) getObject(STATUS);
    }


    /**
     * STATUS_DESCR
     **/
    public boolean setStatusDescr(String statusDescr) {
        return setString(STATUS_DESCR, statusDescr);
    }

    public String getStatusDescr() {
        return getString(STATUS_DESCR);
    }


    /**
     * STATUS_TECH_DESCR
     **/
    public boolean setStatusTechDescr(String statusTechDescr) {
        return setString(STATUS_TECH_DESCR, statusTechDescr);
    }

    public String getStatusTechDescr() {
        return getString(STATUS_TECH_DESCR);
    }


    /**
     * STATUS_UPDATED_TS
     **/
    public boolean setStatusUpdatedTs(Calendar statusUpdatedTs) {
        return setCalendar(STATUS_UPDATED_TS, statusUpdatedTs);
    }

    public Calendar getStatusUpdatedTs() {
        return getCalendar(STATUS_UPDATED_TS);
    }

}
