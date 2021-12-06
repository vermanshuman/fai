INSERT INTO FAI_PO_HEADERS_INTERFACE
(
   INTERFACE_HEADER_KEY
  ,ACTION
  ,BATCH_ID
  ,INTERFACE_SOURCE_CODE
  ,APPROVAL_ACTION
  ,DOCUMENT_NUM
  ,DOCUMENT_TYPE_CODE
  ,STYLE_DISPLAY_NAME
  ,PRC_BU_NAME
  ,AGENT_NAME
  ,CURRENCY_CODE
  ,COMMENTS
  ,VENDOR_NAME
  ,VENDOR_NUM
  ,VENDOR_SITE_CODE
  ,VENDOR_CONTACT
  ,VENDOR_DOC_NUM
  ,FOB
  ,FREIGHT_CARRIER
  ,FREIGHT_TERMS
  ,PAY_ON_CODE
  ,PAYMENT_TERMS
  ,ORIGINATOR_ROLE
  ,CHANGE_ORDER_DESC
  ,ACCEPTANCE_REQUIRED_FLAG
  ,ACCEPTANCE_WITHIN_DAYS
  ,SUPPLIER_NOTIF_METHOD
  ,FAX
  ,EMAIL_ADDRESS
  ,CONFIRMING_ORDER_FLAG
  ,AMOUNT_AGREED
  ,AMOUNT_LIMIT
  ,MIN_RELEASE_AMOUNT
  ,EFFECTIVE_DATE
  ,EXPIRATION_DATE
  ,NOTE_TO_VENDOR
  ,NOTE_TO_RECEIVER
  ,GENERATE_ORDERS_AUTOMATIC
  ,SUBMIT_APPROVAL_AUTOMATIC
  ,GROUP_REQUISITIONS
  ,GROUP_REQUISITION_LINES
  ,USE_SHIP_TO
  ,USE_NEED_BY_DATE
  ,CAT_ADMIN_AUTH_ENABLED_FLAG
  ,RETRO_PRICE_APPLY_UPDATES_FLAG
  ,RETRO_PRICE_COMM_UPDATES_FLAG
  ,ATTRIBUTE_CATEGORY
  ,ATTRIBUTE1
  ,ATTRIBUTE2
  ,ATTRIBUTE3
  ,ATTRIBUTE4
  ,ATTRIBUTE5
  ,ATTRIBUTE6
  ,ATTRIBUTE7
  ,ATTRIBUTE8
  ,ATTRIBUTE9
  ,ATTRIBUTE10
  ,ATTRIBUTE11
  ,ATTRIBUTE12
  ,ATTRIBUTE13
  ,ATTRIBUTE14
  ,ATTRIBUTE15
  ,ATTRIBUTE16
  ,ATTRIBUTE17
  ,ATTRIBUTE18
  ,ATTRIBUTE19
  ,ATTRIBUTE20
  ,ATTRIBUTE_DATE1
  ,ATTRIBUTE_DATE2
  ,ATTRIBUTE_DATE3
  ,ATTRIBUTE_DATE4
  ,ATTRIBUTE_DATE5
  ,ATTRIBUTE_DATE6
  ,ATTRIBUTE_DATE7
  ,ATTRIBUTE_DATE8
  ,ATTRIBUTE_DATE9
  ,ATTRIBUTE_DATE10
  ,ATTRIBUTE_NUMBER1
  ,ATTRIBUTE_NUMBER2
  ,ATTRIBUTE_NUMBER3
  ,ATTRIBUTE_NUMBER4
  ,ATTRIBUTE_NUMBER5
  ,ATTRIBUTE_NUMBER6
  ,ATTRIBUTE_NUMBER7
  ,ATTRIBUTE_NUMBER8
  ,ATTRIBUTE_NUMBER9
  ,ATTRIBUTE_NUMBER10
  ,ATTRIBUTE_TIMESTAMP1
  ,ATTRIBUTE_TIMESTAMP2
  ,ATTRIBUTE_TIMESTAMP3
  ,ATTRIBUTE_TIMESTAMP4
  ,ATTRIBUTE_TIMESTAMP5
  ,ATTRIBUTE_TIMESTAMP6
  ,ATTRIBUTE_TIMESTAMP7
  ,ATTRIBUTE_TIMESTAMP8
  ,ATTRIBUTE_TIMESTAMP9
  ,ATTRIBUTE_TIMESTAMP10
  ,AGENT_EMAIL_ADDRESS
  ,MODE_OF_TRANSPORT
  ,SERVICE_LEVEL
  ,AGING_PERIOD_DAYS
  ,AGING_ONSET_POINT
  ,CONSUMPTION_ADVICE_FREQUENCY
  ,CONSUMPTION_ADVICE_SUMMARY
  ,DEFAULT_CONSIGNMENT_LINE_FLAG
  ,PAY_ON_USE_FLAG
  ,BILLING_CYCLE_CLOSING_DATE
  ,CONFIGURED_ITEM_FLAG
  ,USE_SALES_ORDER_NUMBER_FLAG
  ,BUYER_MANAGED_TRANSPORT_FLAG
  ,ALLOW_ORDER_FROM_UNASS_SITES
  ,OUTSIDE_PROCESS_ENABLED_FLAG
  ,MASTER_CONTRACT_NUMBER
  ,MASTER_CONTRACT_TYPE
)
SELECT
   'intf_hdr_1'                         INTERFACE_HEADER_KEY            -- Interface Header Key *** required ***
  ,'UPDATE'                             ACTION                          -- Action *** required ***
  ,1245                                 BATCH_ID                        -- Batch ID
  ,NULL                                 INTERFACE_SOURCE_CODE           -- Import Source
  ,NULL                                 APPROVAL_ACTION                 -- Approval Action
  ,NULL                                 DOCUMENT_NUM                    -- Agreement
  ,'BLANKET'                            DOCUMENT_TYPE_CODE              -- Document Type Code
  ,NULL                                 STYLE_DISPLAY_NAME              -- Style
  ,'Farmacia Loreto Gallo (Sezionale)'  PRC_BU_NAME                     -- Procurement BU
  ,'RAFFAELE ARPAIA'                    AGENT_NAME                      -- Buyer
  ,'EUR'                                CURRENCY_CODE                   -- Currency Code
  ,NULL                                 COMMENTS                        -- Description
  ,'GROSSISTA COMIFAR'                  VENDOR_NAME                     -- Supplier
  ,NULL                                 VENDOR_NUM                      -- Supplier Number
  ,'LEGALE'                             VENDOR_SITE_CODE                -- Supplier Site
  ,NULL                                 VENDOR_CONTACT                  -- Supplier Contact
  ,NULL                                 VENDOR_DOC_NUM                  -- Supplier Order
  ,NULL                                 FOB                             -- Fob
  ,NULL                                 FREIGHT_CARRIER                 -- Carrier
  ,NULL                                 FREIGHT_TERMS                   -- Freight Terms
  ,NULL                                 PAY_ON_CODE                     -- Pay On Code
  ,'Immediate'                          PAYMENT_TERMS                   -- Payment Terms
  ,'BUYER'                              ORIGINATOR_ROLE                 -- Initiating Party
  ,NULL                                 CHANGE_ORDER_DESC               -- Change Order Description
  ,'N'                                  ACCEPTANCE_REQUIRED_FLAG        -- Required Acknowledgment
  ,NULL                                 ACCEPTANCE_WITHIN_DAYS          -- Acknowledge Within (Days)
  ,NULL                                 SUPPLIER_NOTIF_METHOD           -- Communication Method
  ,NULL                                 FAX                             -- Fax
  ,NULL                                 EMAIL_ADDRESS                   -- E-mail
  ,NULL                                 CONFIRMING_ORDER_FLAG           -- Confirming order
  ,NULL                                 AMOUNT_AGREED                   -- Agreement Amount
  ,NULL                                 AMOUNT_LIMIT                    -- Amount Limit
  ,NULL                                 MIN_RELEASE_AMOUNT              -- Minimum Release Amount
  ,NULL                                 EFFECTIVE_DATE                  -- Start Date
  ,NULL                                 EXPIRATION_DATE                 -- End Date
  ,'Note to Vendor'                     NOTE_TO_VENDOR                  -- Note to Supplier
  ,'Note to Receiver'                   NOTE_TO_RECEIVER                -- Note to Receiver
  ,NULL                                 GENERATE_ORDERS_AUTOMATIC       -- Automatically generate orders
  ,NULL                                 SUBMIT_APPROVAL_AUTOMATIC       -- Automatically submit for approval
  ,NULL                                 GROUP_REQUISITIONS              -- Group requisitions
  ,NULL                                 GROUP_REQUISITION_LINES         -- Group requisition lines
  ,NULL                                 USE_SHIP_TO                     -- Use ship-to organization and location
  ,NULL                                 USE_NEED_BY_DATE                -- Use need-by date
  ,NULL                                 CAT_ADMIN_AUTH_ENABLED_FLAG     -- Catalog Administrator Authoring
  ,NULL                                 RETRO_PRICE_APPLY_UPDATES_FLAG  -- Apply price updates to existing orders
  ,NULL                                 RETRO_PRICE_COMM_UPDATES_FLAG   -- Communicate price updates
  ,NULL                                 ATTRIBUTE_CATEGORY              -- ATTRIBUTE_CATEGORY
  ,'Attribute1'                         ATTRIBUTE1                      -- ATTRIBUTE1
  ,NULL                                 ATTRIBUTE2                      -- ATTRIBUTE2
  ,NULL                                 ATTRIBUTE3                      -- ATTRIBUTE3
  ,NULL                                 ATTRIBUTE4                      -- ATTRIBUTE4
  ,NULL                                 ATTRIBUTE5                      -- ATTRIBUTE5
  ,NULL                                 ATTRIBUTE6                      -- ATTRIBUTE6
  ,NULL                                 ATTRIBUTE7                      -- ATTRIBUTE7
  ,NULL                                 ATTRIBUTE8                      -- ATTRIBUTE8
  ,NULL                                 ATTRIBUTE9                      -- ATTRIBUTE9
  ,NULL                                 ATTRIBUTE10                     -- ATTRIBUTE10
  ,NULL                                 ATTRIBUTE11                     -- ATTRIBUTE11
  ,NULL                                 ATTRIBUTE12                     -- ATTRIBUTE12
  ,NULL                                 ATTRIBUTE13                     -- ATTRIBUTE13
  ,NULL                                 ATTRIBUTE14                     -- ATTRIBUTE14
  ,NULL                                 ATTRIBUTE15                     -- ATTRIBUTE15
  ,NULL                                 ATTRIBUTE16                     -- ATTRIBUTE16
  ,NULL                                 ATTRIBUTE17                     -- ATTRIBUTE17
  ,NULL                                 ATTRIBUTE18                     -- ATTRIBUTE18
  ,NULL                                 ATTRIBUTE19                     -- ATTRIBUTE19
  ,'Attribute20'                        ATTRIBUTE20                     -- ATTRIBUTE20
  ,NULL                                 ATTRIBUTE_DATE1                 -- ATTRIBUTE_DATE1
  ,NULL                                 ATTRIBUTE_DATE2                 -- ATTRIBUTE_DATE2
  ,NULL                                 ATTRIBUTE_DATE3                 -- ATTRIBUTE_DATE3
  ,NULL                                 ATTRIBUTE_DATE4                 -- ATTRIBUTE_DATE4
  ,NULL                                 ATTRIBUTE_DATE5                 -- ATTRIBUTE_DATE5
  ,NULL                                 ATTRIBUTE_DATE6                 -- ATTRIBUTE_DATE6
  ,NULL                                 ATTRIBUTE_DATE7                 -- ATTRIBUTE_DATE7
  ,NULL                                 ATTRIBUTE_DATE8                 -- ATTRIBUTE_DATE8
  ,NULL                                 ATTRIBUTE_DATE9                 -- ATTRIBUTE_DATE9
  ,NULL                                 ATTRIBUTE_DATE10                -- ATTRIBUTE_DATE10
  ,1                                    ATTRIBUTE_NUMBER1               -- ATTRIBUTE_NUMBER1
  ,NULL                                 ATTRIBUTE_NUMBER2               -- ATTRIBUTE_NUMBER2
  ,NULL                                 ATTRIBUTE_NUMBER3               -- ATTRIBUTE_NUMBER3
  ,NULL                                 ATTRIBUTE_NUMBER4               -- ATTRIBUTE_NUMBER4
  ,NULL                                 ATTRIBUTE_NUMBER5               -- ATTRIBUTE_NUMBER5
  ,NULL                                 ATTRIBUTE_NUMBER6               -- ATTRIBUTE_NUMBER6
  ,NULL                                 ATTRIBUTE_NUMBER7               -- ATTRIBUTE_NUMBER7
  ,NULL                                 ATTRIBUTE_NUMBER8               -- ATTRIBUTE_NUMBER8
  ,NULL                                 ATTRIBUTE_NUMBER9               -- ATTRIBUTE_NUMBER9
  ,10                                   ATTRIBUTE_NUMBER10              -- ATTRIBUTE_NUMBER10
  ,NULL                                 ATTRIBUTE_TIMESTAMP1            -- ATTRIBUTE_TIMESTAMP1
  ,NULL                                 ATTRIBUTE_TIMESTAMP2            -- ATTRIBUTE_TIMESTAMP2
  ,NULL                                 ATTRIBUTE_TIMESTAMP3            -- ATTRIBUTE_TIMESTAMP3
  ,NULL                                 ATTRIBUTE_TIMESTAMP4            -- ATTRIBUTE_TIMESTAMP4
  ,NULL                                 ATTRIBUTE_TIMESTAMP5            -- ATTRIBUTE_TIMESTAMP5
  ,NULL                                 ATTRIBUTE_TIMESTAMP6            -- ATTRIBUTE_TIMESTAMP6
  ,NULL                                 ATTRIBUTE_TIMESTAMP7            -- ATTRIBUTE_TIMESTAMP7
  ,NULL                                 ATTRIBUTE_TIMESTAMP8            -- ATTRIBUTE_TIMESTAMP8
  ,NULL                                 ATTRIBUTE_TIMESTAMP9            -- ATTRIBUTE_TIMESTAMP9
  ,NULL                                 ATTRIBUTE_TIMESTAMP10           -- ATTRIBUTE_TIMESTAMP10
  ,NULL                                 AGENT_EMAIL_ADDRESS             -- Buyer E-mail
  ,NULL                                 MODE_OF_TRANSPORT               -- Mode of Transport
  ,NULL                                 SERVICE_LEVEL                   -- Service Level
  ,NULL                                 AGING_PERIOD_DAYS               -- Aging Period Days
  ,NULL                                 AGING_ONSET_POINT               -- Aging Onset Point
  ,NULL                                 CONSUMPTION_ADVICE_FREQUENCY    -- Consumption Advice Frequency
  ,NULL                                 CONSUMPTION_ADVICE_SUMMARY      -- Consumption Advice Summary
  ,NULL                                 DEFAULT_CONSIGNMENT_LINE_FLAG   -- Default line as consignment line
  ,NULL                                 PAY_ON_USE_FLAG                 -- Pay on use
  ,NULL                                 BILLING_CYCLE_CLOSING_DATE      -- Billing Cycle Closing Date
  ,NULL                                 CONFIGURED_ITEM_FLAG            -- Configuration Ordering Enabled
  ,NULL                                 USE_SALES_ORDER_NUMBER_FLAG     -- Use Customer Sales Order
  ,NULL                                 BUYER_MANAGED_TRANSPORT_FLAG    -- Buyer Managed Transportation
  ,'N'                                  ALLOW_ORDER_FROM_UNASS_SITES    -- Allow ordering from unassigned sites
  ,'N'                                  OUTSIDE_PROCESS_ENABLED_FLAG    -- Outside Processing Enabled
  ,NULL                                 MASTER_CONTRACT_NUMBER          -- Master Contract Number
  ,NULL                                 MASTER_CONTRACT_TYPE            -- Master Contract Type
FROM DUAL


