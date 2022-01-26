package it.fai.be.controller.mapping;

public class MappingConstants {

    public static final String FAI_URL = "/fai";

    public static final String CONFIG_MAPPING = "/api/config";

    public static final String FIND_BY_ACRONYM = "/byAcronym";
    public static final String UPLOAD_GENERIC_TASK_CSV = "/uploadGenericTaskCSV";

    public static final String UPLOAD_MAPPING = "/api/upload";
    public static final String EXECUTE_IMPORT_TASK = "/importCSV/{taskOID}";
    public static final String EXECUTE_CALCULATOR_TASK = "/fabbisognoCalculator/{taskOID}";
    public static final String PROCUREMENT_MANAGER_TASK = "/approvvigionamentoManager/{taskOID}";

    public static final String ORDER_MAPPING = "/api/orders";
    public static final String TASK_ORDER_MAPPING = "/byTask/{taskOID}";

    public static final String WAREHOUSE_MAPPING = "/api/warehouse";

    public static final String SUPPLIER_MAPPING = "/api/supplier";

    public static final String PRODUCT_MAPPING = "/api/products";
    public static final String PRODUCT_ORDER_MAPPING = "/byOrder/{orderId}";

    public static final String SUPPLIER_CALENDAR_MAPPING = "/api/supplier/calendar";


}
