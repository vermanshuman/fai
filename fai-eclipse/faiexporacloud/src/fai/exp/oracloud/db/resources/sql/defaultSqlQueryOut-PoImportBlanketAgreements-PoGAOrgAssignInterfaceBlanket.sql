SELECT
   INTERFACE_BU_ASSIGNMENT_KEY  -- Interface BU Assignment Key (es. intf_bu_1) *** required ***  
  ,INTERFACE_HEADER_KEY         -- Interface Header Key (es. intf_hdr_1) *** required ***  
  ,REQ_BU_NAME                  -- Requisitioning BU (es. Vision Operations)  
  ,ORDERED_LOCALLY_FLAG         -- Order Locally (es. N)  
  ,VENDOR_SITE_CODE             -- Purchasing Site  
  ,SHIP_TO_LOCATION             -- Ship-to Location  
  ,BILL_TO_BU_NAME              -- Bill-to BU (es. Vision Operations)  
  ,BILL_TO_LOCATION             -- Bill-to Location  
  ,ENABLED                      -- Enabled (es. Y)  
FROM
  FAI_PO_GA_ORG_ASSIGN_INTERFACE -- PoGAOrgAssignInterfaceBlanket
WHERE
  VERSION_NUMBER = ?LAST_VERSION?


