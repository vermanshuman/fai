package fai.broker.models;

import it.swdes.models.AbstractModel;
import it.swdes.models.Attribute;

import java.util.Hashtable;

public class ExecutionStatus extends AbstractModel {


    public static int OID;
    public static int ACRONYM;
    public static int DESCR;

    public static final Attribute[] attributes = buildAttributes(ExecutionStatus.class, new Attribute[] { //@formatter:off
      new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
      new Attribute("acronym", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
      new Attribute("descr", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    }); //@formatter:on


    public static ExecutionStatus IMPORT_CSV = new ExecutionStatus(new Long(1), "IMPORT_CSV", "Importing CSV");
    public static ExecutionStatus PARSE_CSV = new ExecutionStatus(new Long(2), "PARSE_CSV", "Parsing CSV");
    public static ExecutionStatus ORDERS_LOADED = new ExecutionStatus(new Long(3), "ORDERS_LOADED", "Orders Loaded");

    public static ExecutionStatus CALCULATION_START = new ExecutionStatus(new Long(4), "CALCULATION_START", "Retrieving Rows to approve");
    public static ExecutionStatus CALCULATION_COMPLETED = new ExecutionStatus(new Long(5), "CALCULATION_COMPLETED", "Retrieved Rows to approve");

    public static ExecutionStatus APPROVAL_START = new ExecutionStatus(new Long(6), "APPROVAL_START", "Approvvigionamento Manager");
    public static ExecutionStatus APPROVAL_TEMP = new ExecutionStatus(new Long(7), "APPROVAL_TEMP", "Approvvigionamento Manager Temp");
    public static ExecutionStatus APPROVAL_WAREHOUSE_AVAILIBILITY = new ExecutionStatus(new Long(8), "APPROVAL_WAREHOUSE_AVAILIBILITY", "Checking Warehouse Availability");
    public static ExecutionStatus APPROVAL_SUPPLIER_AVAILIBILITY = new ExecutionStatus(new Long(9), "APPROVAL_SUPPLIER_AVAILIBILITY", "Checking Supplier Availability");

    public static ExecutionStatus APPROVAL_SUPPLIER_ORDER = new ExecutionStatus(new Long(9), "APPROVAL_SUPPLIER_ORDER", "Confirming Order");


  public static ExecutionStatus EXECUTION_SUCCESS = new ExecutionStatus(new Long(9), "EXECUTION_SUCCESS", "Execution Completed");
    public static ExecutionStatus EXECUTION_FAILED = new ExecutionStatus(new Long(10), "EXECUTION_FAILED", "Execution Failed");


    private static Hashtable<Long, ExecutionStatus> statusByOid = new Hashtable<>();
    private static Hashtable<String, ExecutionStatus> statusByAcronym = new Hashtable<>();
    static {
      ExecutionStatus[] status = new ExecutionStatus[] { IMPORT_CSV, PARSE_CSV};
      for (int i = 0; i < status.length; i++) {
        statusByOid.put(status[i].getOid(), status[i]);
        statusByAcronym.put(status[i].getAcronym(), status[i]);
      }
    }

    public static ExecutionStatus getByOid(Long oid) {
      ExecutionStatus is = statusByOid.get(oid);
      if (is == null) throw new IllegalStateException("nessun "+ ExecutionStatus.class.getName()+" trovato per OID "+oid);
      return is;
    }

    public static ExecutionStatus getByAcronym(String acronym) {
      ExecutionStatus is = statusByAcronym.get(acronym);
      if (is == null) throw new IllegalStateException("nessun "+ ExecutionStatus.class.getName()+" trovato per ACRONYM "+acronym);
      return is;
    }


    /** CONSTRUCTOR **/

    public ExecutionStatus() {
    }

    public ExecutionStatus(Long oid, String acronym, String descr) {
      this.setOid(oid);
      this.setAcronym(acronym);
      this.setDescr(descr);
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
}
