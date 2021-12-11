INSERT INTO FAI_PO_GA_ORG_ASSIGN_INTERFACE
(
   INTERFACE_BU_ASSIGNMENT_KEY
  ,INTERFACE_HEADER_KEY
  ,REQ_BU_NAME
  ,ORDERED_LOCALLY_FLAG
  ,VENDOR_SITE_CODE
  ,SHIP_TO_LOCATION
  ,BILL_TO_BU_NAME
  ,BILL_TO_LOCATION
  ,ENABLED
)
SELECT
   'intf_bu_1'          INTERFACE_BU_ASSIGNMENT_KEY  -- Interface BU Assignment Key *** required ***
  ,'intf_hdr_1'         INTERFACE_HEADER_KEY         -- Interface Header Key *** required ***
  ,'Vision Operations'  REQ_BU_NAME                  -- Requisitioning BU
  ,'N'                  ORDERED_LOCALLY_FLAG         -- Order Locally
  ,NULL                 VENDOR_SITE_CODE             -- Purchasing Site
  ,NULL                 SHIP_TO_LOCATION             -- Ship-to Location
  ,'Vision Operations'  BILL_TO_BU_NAME              -- Bill-to BU
  ,NULL                 BILL_TO_LOCATION             -- Bill-to Location
  ,'Y'                  ENABLED                      -- Enabled
FROM DUAL


