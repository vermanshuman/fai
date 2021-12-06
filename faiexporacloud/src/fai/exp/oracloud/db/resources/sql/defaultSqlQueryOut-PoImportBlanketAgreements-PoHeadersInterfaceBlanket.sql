SELECT
   INTERFACE_HEADER_KEY            -- Interface Header Key (es. intf_hdr_1) *** required ***  
  ,ACTION                          -- Action (es. UPDATE) *** required ***  
  ,BATCH_ID                        -- Batch ID (es. 1245)  
  ,INTERFACE_SOURCE_CODE           -- Import Source  
  ,APPROVAL_ACTION                 -- Approval Action  
  ,DOCUMENT_NUM                    -- Agreement  
  ,DOCUMENT_TYPE_CODE              -- Document Type Code (es. BLANKET)  
  ,STYLE_DISPLAY_NAME              -- Style  
  ,PRC_BU_NAME                     -- Procurement BU (es. Farmacia Loreto Gallo (Sezionale))  
  ,AGENT_NAME                      -- Buyer (es. RAFFAELE ARPAIA)  
  ,CURRENCY_CODE                   -- Currency Code (es. EUR)  
  ,COMMENTS                        -- Description  
  ,VENDOR_NAME                     -- Supplier (es. GROSSISTA COMIFAR)  
  ,VENDOR_NUM                      -- Supplier Number  
  ,VENDOR_SITE_CODE                -- Supplier Site (es. LEGALE)  
  ,VENDOR_CONTACT                  -- Supplier Contact  
  ,VENDOR_DOC_NUM                  -- Supplier Order  
  ,FOB                             -- Fob  
  ,FREIGHT_CARRIER                 -- Carrier  
  ,FREIGHT_TERMS                   -- Freight Terms  
  ,PAY_ON_CODE                     -- Pay On Code  
  ,PAYMENT_TERMS                   -- Payment Terms (es. Immediate)  
  ,ORIGINATOR_ROLE                 -- Initiating Party (es. BUYER)  
  ,CHANGE_ORDER_DESC               -- Change Order Description  
  ,ACCEPTANCE_REQUIRED_FLAG        -- Required Acknowledgment (es. N)  
  ,ACCEPTANCE_WITHIN_DAYS          -- Acknowledge Within (Days)  
  ,SUPPLIER_NOTIF_METHOD           -- Communication Method  
  ,FAX                             -- Fax  
  ,EMAIL_ADDRESS                   -- E-mail  
  ,CONFIRMING_ORDER_FLAG           -- Confirming order  
  ,AMOUNT_AGREED                   -- Agreement Amount  
  ,AMOUNT_LIMIT                    -- Amount Limit  
  ,MIN_RELEASE_AMOUNT              -- Minimum Release Amount  
  ,EFFECTIVE_DATE                  -- Start Date  
  ,EXPIRATION_DATE                 -- End Date  
  ,NOTE_TO_VENDOR                  -- Note to Supplier (es. Note to Vendor)  
  ,NOTE_TO_RECEIVER                -- Note to Receiver (es. Note to Receiver)  
  ,GENERATE_ORDERS_AUTOMATIC       -- Automatically generate orders  
  ,SUBMIT_APPROVAL_AUTOMATIC       -- Automatically submit for approval  
  ,GROUP_REQUISITIONS              -- Group requisitions  
  ,GROUP_REQUISITION_LINES         -- Group requisition lines  
  ,USE_SHIP_TO                     -- Use ship-to organization and location  
  ,USE_NEED_BY_DATE                -- Use need-by date  
  ,CAT_ADMIN_AUTH_ENABLED_FLAG     -- Catalog Administrator Authoring  
  ,RETRO_PRICE_APPLY_UPDATES_FLAG  -- Apply price updates to existing orders  
  ,RETRO_PRICE_COMM_UPDATES_FLAG   -- Communicate price updates  
  ,ATTRIBUTE_CATEGORY              -- ATTRIBUTE_CATEGORY  
  ,ATTRIBUTE1                      -- ATTRIBUTE1 (es. Attribute1)  
  ,ATTRIBUTE2                      -- ATTRIBUTE2  
  ,ATTRIBUTE3                      -- ATTRIBUTE3  
  ,ATTRIBUTE4                      -- ATTRIBUTE4  
  ,ATTRIBUTE5                      -- ATTRIBUTE5  
  ,ATTRIBUTE6                      -- ATTRIBUTE6  
  ,ATTRIBUTE7                      -- ATTRIBUTE7  
  ,ATTRIBUTE8                      -- ATTRIBUTE8  
  ,ATTRIBUTE9                      -- ATTRIBUTE9  
  ,ATTRIBUTE10                     -- ATTRIBUTE10  
  ,ATTRIBUTE11                     -- ATTRIBUTE11  
  ,ATTRIBUTE12                     -- ATTRIBUTE12  
  ,ATTRIBUTE13                     -- ATTRIBUTE13  
  ,ATTRIBUTE14                     -- ATTRIBUTE14  
  ,ATTRIBUTE15                     -- ATTRIBUTE15  
  ,ATTRIBUTE16                     -- ATTRIBUTE16  
  ,ATTRIBUTE17                     -- ATTRIBUTE17  
  ,ATTRIBUTE18                     -- ATTRIBUTE18  
  ,ATTRIBUTE19                     -- ATTRIBUTE19  
  ,ATTRIBUTE20                     -- ATTRIBUTE20 (es. Attribute20)  
  ,ATTRIBUTE_DATE1                 -- ATTRIBUTE_DATE1  
  ,ATTRIBUTE_DATE2                 -- ATTRIBUTE_DATE2  
  ,ATTRIBUTE_DATE3                 -- ATTRIBUTE_DATE3  
  ,ATTRIBUTE_DATE4                 -- ATTRIBUTE_DATE4  
  ,ATTRIBUTE_DATE5                 -- ATTRIBUTE_DATE5  
  ,ATTRIBUTE_DATE6                 -- ATTRIBUTE_DATE6  
  ,ATTRIBUTE_DATE7                 -- ATTRIBUTE_DATE7  
  ,ATTRIBUTE_DATE8                 -- ATTRIBUTE_DATE8  
  ,ATTRIBUTE_DATE9                 -- ATTRIBUTE_DATE9  
  ,ATTRIBUTE_DATE10                -- ATTRIBUTE_DATE10  
  ,ATTRIBUTE_NUMBER1               -- ATTRIBUTE_NUMBER1 (es. 1)  
  ,ATTRIBUTE_NUMBER2               -- ATTRIBUTE_NUMBER2  
  ,ATTRIBUTE_NUMBER3               -- ATTRIBUTE_NUMBER3  
  ,ATTRIBUTE_NUMBER4               -- ATTRIBUTE_NUMBER4  
  ,ATTRIBUTE_NUMBER5               -- ATTRIBUTE_NUMBER5  
  ,ATTRIBUTE_NUMBER6               -- ATTRIBUTE_NUMBER6  
  ,ATTRIBUTE_NUMBER7               -- ATTRIBUTE_NUMBER7  
  ,ATTRIBUTE_NUMBER8               -- ATTRIBUTE_NUMBER8  
  ,ATTRIBUTE_NUMBER9               -- ATTRIBUTE_NUMBER9  
  ,ATTRIBUTE_NUMBER10              -- ATTRIBUTE_NUMBER10 (es. 10)  
  ,ATTRIBUTE_TIMESTAMP1            -- ATTRIBUTE_TIMESTAMP1  
  ,ATTRIBUTE_TIMESTAMP2            -- ATTRIBUTE_TIMESTAMP2  
  ,ATTRIBUTE_TIMESTAMP3            -- ATTRIBUTE_TIMESTAMP3  
  ,ATTRIBUTE_TIMESTAMP4            -- ATTRIBUTE_TIMESTAMP4  
  ,ATTRIBUTE_TIMESTAMP5            -- ATTRIBUTE_TIMESTAMP5  
  ,ATTRIBUTE_TIMESTAMP6            -- ATTRIBUTE_TIMESTAMP6  
  ,ATTRIBUTE_TIMESTAMP7            -- ATTRIBUTE_TIMESTAMP7  
  ,ATTRIBUTE_TIMESTAMP8            -- ATTRIBUTE_TIMESTAMP8  
  ,ATTRIBUTE_TIMESTAMP9            -- ATTRIBUTE_TIMESTAMP9  
  ,ATTRIBUTE_TIMESTAMP10           -- ATTRIBUTE_TIMESTAMP10  
  ,AGENT_EMAIL_ADDRESS             -- Buyer E-mail  
  ,MODE_OF_TRANSPORT               -- Mode of Transport  
  ,SERVICE_LEVEL                   -- Service Level  
  ,AGING_PERIOD_DAYS               -- Aging Period Days  
  ,AGING_ONSET_POINT               -- Aging Onset Point  
  ,CONSUMPTION_ADVICE_FREQUENCY    -- Consumption Advice Frequency  
  ,CONSUMPTION_ADVICE_SUMMARY      -- Consumption Advice Summary  
  ,DEFAULT_CONSIGNMENT_LINE_FLAG   -- Default line as consignment line  
  ,PAY_ON_USE_FLAG                 -- Pay on use  
  ,BILLING_CYCLE_CLOSING_DATE      -- Billing Cycle Closing Date  
  ,CONFIGURED_ITEM_FLAG            -- Configuration Ordering Enabled  
  ,USE_SALES_ORDER_NUMBER_FLAG     -- Use Customer Sales Order  
  ,BUYER_MANAGED_TRANSPORT_FLAG    -- Buyer Managed Transportation  
  ,ALLOW_ORDER_FROM_UNASS_SITES    -- Allow ordering from unassigned sites (es. N)  
  ,OUTSIDE_PROCESS_ENABLED_FLAG    -- Outside Processing Enabled (es. N)  
  ,MASTER_CONTRACT_NUMBER          -- Master Contract Number  
  ,MASTER_CONTRACT_TYPE            -- Master Contract Type  
FROM
  FAI_PO_HEADERS_INTERFACE -- PoHeadersInterfaceBlanket
WHERE
  VERSION_NUMBER = ?LAST_VERSION?


