INSERT INTO FAI_PO_LINES_INTERFACE
(
   INTERFACE_LINE_KEY
  ,INTERFACE_HEADER_KEY
  ,ACTION
  ,LINE_NUM
  ,LINE_TYPE
  ,ITEM
  ,ITEM_DESCRIPTION
  ,ITEM_REVISION
  ,CATEGORY
  ,COMMITTED_AMOUNT
  ,UNIT_OF_MEASURE
  ,UNIT_PRICE
  ,ALLOW_PRICE_OVERRIDE_FLAG
  ,NOT_TO_EXCEED_PRICE
  ,VENDOR_PRODUCT_NUM
  ,NEGOTIATED_BY_PREPARER_FLAG
  ,NOTE_TO_VENDOR
  ,NOTE_TO_RECEIVER
  ,MIN_RELEASE_AMOUNT
  ,EXPIRATION_DATE
  ,SUPPLIER_PART_AUXID
  ,SUPPLIER_REF_NUMBER
  ,LINE_ATTRIBUTE_CATEGORY_LINES
  ,LINE_ATTRIBUTE1
  ,LINE_ATTRIBUTE2
  ,LINE_ATTRIBUTE3
  ,LINE_ATTRIBUTE4
  ,LINE_ATTRIBUTE5
  ,LINE_ATTRIBUTE6
  ,LINE_ATTRIBUTE7
  ,LINE_ATTRIBUTE8
  ,LINE_ATTRIBUTE9
  ,LINE_ATTRIBUTE10
  ,LINE_ATTRIBUTE11
  ,LINE_ATTRIBUTE12
  ,LINE_ATTRIBUTE13
  ,LINE_ATTRIBUTE14
  ,LINE_ATTRIBUTE15
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
  ,AGING_PERIOD_DAYS
  ,CONSIGNMENT_LINE_FLAG
  ,UNIT_WEIGHT
  ,WEIGHT_UOM_CODE
  ,WEIGHT_UNIT_OF_MEASURE
  ,UNIT_VOLUME
  ,VOLUME_UOM_CODE
  ,VOLUME_UNIT_OF_MEASURE
  ,TEMPLATE_NAME
  ,ITEM_ATTRIBUTE_CATEGORY
  ,ITEM_ATTRIBUTE1
  ,ITEM_ATTRIBUTE2
  ,ITEM_ATTRIBUTE3
  ,ITEM_ATTRIBUTE4
  ,ITEM_ATTRIBUTE5
  ,ITEM_ATTRIBUTE6
  ,ITEM_ATTRIBUTE7
  ,ITEM_ATTRIBUTE8
  ,ITEM_ATTRIBUTE9
  ,ITEM_ATTRIBUTE10
  ,ITEM_ATTRIBUTE11
  ,ITEM_ATTRIBUTE12
  ,ITEM_ATTRIBUTE13
  ,ITEM_ATTRIBUTE14
  ,ITEM_ATTRIBUTE15
  ,PARENT_ITEM
  ,TOP_MODEL
  ,SUPPLIER_PARENT_ITEM
  ,SUPPLIER_TOP_MODEL
  ,AMOUNT
  ,PRICE_BREAK_LOOKUP_CODE
  ,QUANTITY_COMMITTED
  ,ALLOW_DESCRIPTION_UPDATE_FLAG
)
SELECT
   'intf_line_1'               INTERFACE_LINE_KEY             -- Interface Line Key *** required ***
  ,'intf_hdr_1'                INTERFACE_HEADER_KEY           -- Interface Header Key *** required ***
  ,'SYNC'                      ACTION                         -- Action *** required ***
  ,1                           LINE_NUM                       -- Line
  ,NULL                        LINE_TYPE                      -- Line Type
  ,'1578018'                   ITEM                           -- Item
  ,'EURAX*CREMA DERM 20G 10%'  ITEM_DESCRIPTION               -- Item Description
  ,NULL                        ITEM_REVISION                  -- Item Revision
  ,NULL                        CATEGORY                       -- Category Name
  ,NULL                        COMMITTED_AMOUNT               -- Agreement Amount
  ,NULL                        UNIT_OF_MEASURE                -- UOM
  ,0                           UNIT_PRICE                     -- Price
  ,NULL                        ALLOW_PRICE_OVERRIDE_FLAG      -- Allow Price Override
  ,NULL                        NOT_TO_EXCEED_PRICE            -- Not to exceed price
  ,NULL                        VENDOR_PRODUCT_NUM             -- Supplier Item
  ,NULL                        NEGOTIATED_BY_PREPARER_FLAG    -- Negotiated
  ,NULL                        NOTE_TO_VENDOR                 -- Note to Supplier
  ,NULL                        NOTE_TO_RECEIVER               -- Note to Receiver
  ,NULL                        MIN_RELEASE_AMOUNT             -- Minimum Release Amount
  ,NULL                        EXPIRATION_DATE                -- Expiration Date
  ,NULL                        SUPPLIER_PART_AUXID            -- Supplier Part Auxiliary ID
  ,NULL                        SUPPLIER_REF_NUMBER            -- Supplier Ref Number
  ,NULL                        LINE_ATTRIBUTE_CATEGORY_LINES  -- ATTRIBUTE_CATEGORY
  ,NULL                        LINE_ATTRIBUTE1                -- ATTRIBUTE1
  ,NULL                        LINE_ATTRIBUTE2                -- ATTRIBUTE2
  ,NULL                        LINE_ATTRIBUTE3                -- ATTRIBUTE3
  ,NULL                        LINE_ATTRIBUTE4                -- ATTRIBUTE4
  ,NULL                        LINE_ATTRIBUTE5                -- ATTRIBUTE5
  ,NULL                        LINE_ATTRIBUTE6                -- ATTRIBUTE6
  ,NULL                        LINE_ATTRIBUTE7                -- ATTRIBUTE7
  ,NULL                        LINE_ATTRIBUTE8                -- ATTRIBUTE8
  ,NULL                        LINE_ATTRIBUTE9                -- ATTRIBUTE9
  ,NULL                        LINE_ATTRIBUTE10               -- ATTRIBUTE10
  ,NULL                        LINE_ATTRIBUTE11               -- ATTRIBUTE11
  ,NULL                        LINE_ATTRIBUTE12               -- ATTRIBUTE12
  ,NULL                        LINE_ATTRIBUTE13               -- ATTRIBUTE13
  ,NULL                        LINE_ATTRIBUTE14               -- ATTRIBUTE14
  ,NULL                        LINE_ATTRIBUTE15               -- ATTRIBUTE15
  ,NULL                        ATTRIBUTE16                    -- ATTRIBUTE16
  ,NULL                        ATTRIBUTE17                    -- ATTRIBUTE17
  ,NULL                        ATTRIBUTE18                    -- ATTRIBUTE18
  ,NULL                        ATTRIBUTE19                    -- ATTRIBUTE19
  ,NULL                        ATTRIBUTE20                    -- ATTRIBUTE20
  ,NULL                        ATTRIBUTE_DATE1                -- ATTRIBUTE_DATE1
  ,NULL                        ATTRIBUTE_DATE2                -- ATTRIBUTE_DATE2
  ,NULL                        ATTRIBUTE_DATE3                -- ATTRIBUTE_DATE3
  ,NULL                        ATTRIBUTE_DATE4                -- ATTRIBUTE_DATE4
  ,NULL                        ATTRIBUTE_DATE5                -- ATTRIBUTE_DATE5
  ,NULL                        ATTRIBUTE_DATE6                -- ATTRIBUTE_DATE6
  ,NULL                        ATTRIBUTE_DATE7                -- ATTRIBUTE_DATE7
  ,NULL                        ATTRIBUTE_DATE8                -- ATTRIBUTE_DATE8
  ,NULL                        ATTRIBUTE_DATE9                -- ATTRIBUTE_DATE9
  ,NULL                        ATTRIBUTE_DATE10               -- ATTRIBUTE_DATE10
  ,NULL                        ATTRIBUTE_NUMBER1              -- ATTRIBUTE_NUMBER1
  ,NULL                        ATTRIBUTE_NUMBER2              -- ATTRIBUTE_NUMBER2
  ,NULL                        ATTRIBUTE_NUMBER3              -- ATTRIBUTE_NUMBER3
  ,NULL                        ATTRIBUTE_NUMBER4              -- ATTRIBUTE_NUMBER4
  ,NULL                        ATTRIBUTE_NUMBER5              -- ATTRIBUTE_NUMBER5
  ,NULL                        ATTRIBUTE_NUMBER6              -- ATTRIBUTE_NUMBER6
  ,NULL                        ATTRIBUTE_NUMBER7              -- ATTRIBUTE_NUMBER7
  ,NULL                        ATTRIBUTE_NUMBER8              -- ATTRIBUTE_NUMBER8
  ,NULL                        ATTRIBUTE_NUMBER9              -- ATTRIBUTE_NUMBER9
  ,NULL                        ATTRIBUTE_NUMBER10             -- ATTRIBUTE_NUMBER10
  ,NULL                        ATTRIBUTE_TIMESTAMP1           -- ATTRIBUTE_TIMESTAMP1
  ,NULL                        ATTRIBUTE_TIMESTAMP2           -- ATTRIBUTE_TIMESTAMP2
  ,NULL                        ATTRIBUTE_TIMESTAMP3           -- ATTRIBUTE_TIMESTAMP3
  ,NULL                        ATTRIBUTE_TIMESTAMP4           -- ATTRIBUTE_TIMESTAMP4
  ,NULL                        ATTRIBUTE_TIMESTAMP5           -- ATTRIBUTE_TIMESTAMP5
  ,NULL                        ATTRIBUTE_TIMESTAMP6           -- ATTRIBUTE_TIMESTAMP6
  ,NULL                        ATTRIBUTE_TIMESTAMP7           -- ATTRIBUTE_TIMESTAMP7
  ,NULL                        ATTRIBUTE_TIMESTAMP8           -- ATTRIBUTE_TIMESTAMP8
  ,NULL                        ATTRIBUTE_TIMESTAMP9           -- ATTRIBUTE_TIMESTAMP9
  ,NULL                        ATTRIBUTE_TIMESTAMP10          -- ATTRIBUTE_TIMESTAMP10
  ,NULL                        AGING_PERIOD_DAYS              -- Aging Period Days
  ,NULL                        CONSIGNMENT_LINE_FLAG          -- Consignment Line
  ,NULL                        UNIT_WEIGHT                    -- Unit Weight
  ,NULL                        WEIGHT_UOM_CODE                -- Weight UOM Code
  ,NULL                        WEIGHT_UNIT_OF_MEASURE         -- Weight UOM Name
  ,NULL                        UNIT_VOLUME                    -- Unit Volumn
  ,NULL                        VOLUME_UOM_CODE                -- Volume UOM Code
  ,NULL                        VOLUME_UNIT_OF_MEASURE         -- Volume UOM Name
  ,NULL                        TEMPLATE_NAME                  -- Template Name
  ,NULL                        ITEM_ATTRIBUTE_CATEGORY        -- ITEM_ATTRIBUTE_CATEGORY
  ,NULL                        ITEM_ATTRIBUTE1                -- ITEM_ATTRIBUTE1
  ,NULL                        ITEM_ATTRIBUTE2                -- ITEM_ATTRIBUTE2
  ,NULL                        ITEM_ATTRIBUTE3                -- ITEM_ATTRIBUTE3
  ,NULL                        ITEM_ATTRIBUTE4                -- ITEM_ATTRIBUTE4
  ,NULL                        ITEM_ATTRIBUTE5                -- ITEM_ATTRIBUTE5
  ,NULL                        ITEM_ATTRIBUTE6                -- ITEM_ATTRIBUTE6
  ,NULL                        ITEM_ATTRIBUTE7                -- ITEM_ATTRIBUTE7
  ,NULL                        ITEM_ATTRIBUTE8                -- ITEM_ATTRIBUTE8
  ,NULL                        ITEM_ATTRIBUTE9                -- ITEM_ATTRIBUTE9
  ,NULL                        ITEM_ATTRIBUTE10               -- ITEM_ATTRIBUTE10
  ,NULL                        ITEM_ATTRIBUTE11               -- ITEM_ATTRIBUTE11
  ,NULL                        ITEM_ATTRIBUTE12               -- ITEM_ATTRIBUTE12
  ,NULL                        ITEM_ATTRIBUTE13               -- ITEM_ATTRIBUTE13
  ,NULL                        ITEM_ATTRIBUTE14               -- ITEM_ATTRIBUTE14
  ,NULL                        ITEM_ATTRIBUTE15               -- ITEM_ATTRIBUTE15
  ,NULL                        PARENT_ITEM                    -- Parent Item
  ,NULL                        TOP_MODEL                      -- Top Model
  ,NULL                        SUPPLIER_PARENT_ITEM           -- Supplier Parent Item
  ,NULL                        SUPPLIER_TOP_MODEL             -- Supplier Top Model
  ,NULL                        AMOUNT                         -- Amount
  ,NULL                        PRICE_BREAK_LOOKUP_CODE        -- Price Break Type
  ,NULL                        QUANTITY_COMMITTED             -- Agreement Quantity
  ,NULL                        ALLOW_DESCRIPTION_UPDATE_FLAG  -- Allow Description Update
FROM DUAL


