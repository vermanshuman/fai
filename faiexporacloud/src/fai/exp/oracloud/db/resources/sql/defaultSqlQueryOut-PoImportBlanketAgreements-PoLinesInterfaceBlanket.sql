SELECT
   INTERFACE_LINE_KEY             -- Interface Line Key (es. intf_line_1) *** required ***  
  ,INTERFACE_HEADER_KEY           -- Interface Header Key (es. intf_hdr_1) *** required ***  
  ,ACTION                         -- Action (es. SYNC) *** required ***  
  ,LINE_NUM                       -- Line (es. 1)  
  ,LINE_TYPE                      -- Line Type  
  ,ITEM                           -- Item (es. 1578018)  
  ,ITEM_DESCRIPTION               -- Item Description (es. EURAX*CREMA DERM 20G 10%)  
  ,ITEM_REVISION                  -- Item Revision  
  ,CATEGORY                       -- Category Name  
  ,COMMITTED_AMOUNT               -- Agreement Amount  
  ,UNIT_OF_MEASURE                -- UOM  
  ,UNIT_PRICE                     -- Price (es. 0)  
  ,ALLOW_PRICE_OVERRIDE_FLAG      -- Allow Price Override  
  ,NOT_TO_EXCEED_PRICE            -- Not to exceed price  
  ,VENDOR_PRODUCT_NUM             -- Supplier Item  
  ,NEGOTIATED_BY_PREPARER_FLAG    -- Negotiated  
  ,NOTE_TO_VENDOR                 -- Note to Supplier  
  ,NOTE_TO_RECEIVER               -- Note to Receiver  
  ,MIN_RELEASE_AMOUNT             -- Minimum Release Amount  
  ,EXPIRATION_DATE                -- Expiration Date  
  ,SUPPLIER_PART_AUXID            -- Supplier Part Auxiliary ID  
  ,SUPPLIER_REF_NUMBER            -- Supplier Ref Number  
  ,LINE_ATTRIBUTE_CATEGORY_LINES  -- ATTRIBUTE_CATEGORY  
  ,LINE_ATTRIBUTE1                -- ATTRIBUTE1  
  ,LINE_ATTRIBUTE2                -- ATTRIBUTE2  
  ,LINE_ATTRIBUTE3                -- ATTRIBUTE3  
  ,LINE_ATTRIBUTE4                -- ATTRIBUTE4  
  ,LINE_ATTRIBUTE5                -- ATTRIBUTE5  
  ,LINE_ATTRIBUTE6                -- ATTRIBUTE6  
  ,LINE_ATTRIBUTE7                -- ATTRIBUTE7  
  ,LINE_ATTRIBUTE8                -- ATTRIBUTE8  
  ,LINE_ATTRIBUTE9                -- ATTRIBUTE9  
  ,LINE_ATTRIBUTE10               -- ATTRIBUTE10  
  ,LINE_ATTRIBUTE11               -- ATTRIBUTE11  
  ,LINE_ATTRIBUTE12               -- ATTRIBUTE12  
  ,LINE_ATTRIBUTE13               -- ATTRIBUTE13  
  ,LINE_ATTRIBUTE14               -- ATTRIBUTE14  
  ,LINE_ATTRIBUTE15               -- ATTRIBUTE15  
  ,ATTRIBUTE16                    -- ATTRIBUTE16  
  ,ATTRIBUTE17                    -- ATTRIBUTE17  
  ,ATTRIBUTE18                    -- ATTRIBUTE18  
  ,ATTRIBUTE19                    -- ATTRIBUTE19  
  ,ATTRIBUTE20                    -- ATTRIBUTE20  
  ,ATTRIBUTE_DATE1                -- ATTRIBUTE_DATE1  
  ,ATTRIBUTE_DATE2                -- ATTRIBUTE_DATE2  
  ,ATTRIBUTE_DATE3                -- ATTRIBUTE_DATE3  
  ,ATTRIBUTE_DATE4                -- ATTRIBUTE_DATE4  
  ,ATTRIBUTE_DATE5                -- ATTRIBUTE_DATE5  
  ,ATTRIBUTE_DATE6                -- ATTRIBUTE_DATE6  
  ,ATTRIBUTE_DATE7                -- ATTRIBUTE_DATE7  
  ,ATTRIBUTE_DATE8                -- ATTRIBUTE_DATE8  
  ,ATTRIBUTE_DATE9                -- ATTRIBUTE_DATE9  
  ,ATTRIBUTE_DATE10               -- ATTRIBUTE_DATE10  
  ,ATTRIBUTE_NUMBER1              -- ATTRIBUTE_NUMBER1  
  ,ATTRIBUTE_NUMBER2              -- ATTRIBUTE_NUMBER2  
  ,ATTRIBUTE_NUMBER3              -- ATTRIBUTE_NUMBER3  
  ,ATTRIBUTE_NUMBER4              -- ATTRIBUTE_NUMBER4  
  ,ATTRIBUTE_NUMBER5              -- ATTRIBUTE_NUMBER5  
  ,ATTRIBUTE_NUMBER6              -- ATTRIBUTE_NUMBER6  
  ,ATTRIBUTE_NUMBER7              -- ATTRIBUTE_NUMBER7  
  ,ATTRIBUTE_NUMBER8              -- ATTRIBUTE_NUMBER8  
  ,ATTRIBUTE_NUMBER9              -- ATTRIBUTE_NUMBER9  
  ,ATTRIBUTE_NUMBER10             -- ATTRIBUTE_NUMBER10  
  ,ATTRIBUTE_TIMESTAMP1           -- ATTRIBUTE_TIMESTAMP1  
  ,ATTRIBUTE_TIMESTAMP2           -- ATTRIBUTE_TIMESTAMP2  
  ,ATTRIBUTE_TIMESTAMP3           -- ATTRIBUTE_TIMESTAMP3  
  ,ATTRIBUTE_TIMESTAMP4           -- ATTRIBUTE_TIMESTAMP4  
  ,ATTRIBUTE_TIMESTAMP5           -- ATTRIBUTE_TIMESTAMP5  
  ,ATTRIBUTE_TIMESTAMP6           -- ATTRIBUTE_TIMESTAMP6  
  ,ATTRIBUTE_TIMESTAMP7           -- ATTRIBUTE_TIMESTAMP7  
  ,ATTRIBUTE_TIMESTAMP8           -- ATTRIBUTE_TIMESTAMP8  
  ,ATTRIBUTE_TIMESTAMP9           -- ATTRIBUTE_TIMESTAMP9  
  ,ATTRIBUTE_TIMESTAMP10          -- ATTRIBUTE_TIMESTAMP10  
  ,AGING_PERIOD_DAYS              -- Aging Period Days  
  ,CONSIGNMENT_LINE_FLAG          -- Consignment Line  
  ,UNIT_WEIGHT                    -- Unit Weight  
  ,WEIGHT_UOM_CODE                -- Weight UOM Code  
  ,WEIGHT_UNIT_OF_MEASURE         -- Weight UOM Name  
  ,UNIT_VOLUME                    -- Unit Volumn  
  ,VOLUME_UOM_CODE                -- Volume UOM Code  
  ,VOLUME_UNIT_OF_MEASURE         -- Volume UOM Name  
  ,TEMPLATE_NAME                  -- Template Name  
  ,ITEM_ATTRIBUTE_CATEGORY        -- ITEM_ATTRIBUTE_CATEGORY  
  ,ITEM_ATTRIBUTE1                -- ITEM_ATTRIBUTE1  
  ,ITEM_ATTRIBUTE2                -- ITEM_ATTRIBUTE2  
  ,ITEM_ATTRIBUTE3                -- ITEM_ATTRIBUTE3  
  ,ITEM_ATTRIBUTE4                -- ITEM_ATTRIBUTE4  
  ,ITEM_ATTRIBUTE5                -- ITEM_ATTRIBUTE5  
  ,ITEM_ATTRIBUTE6                -- ITEM_ATTRIBUTE6  
  ,ITEM_ATTRIBUTE7                -- ITEM_ATTRIBUTE7  
  ,ITEM_ATTRIBUTE8                -- ITEM_ATTRIBUTE8  
  ,ITEM_ATTRIBUTE9                -- ITEM_ATTRIBUTE9  
  ,ITEM_ATTRIBUTE10               -- ITEM_ATTRIBUTE10  
  ,ITEM_ATTRIBUTE11               -- ITEM_ATTRIBUTE11  
  ,ITEM_ATTRIBUTE12               -- ITEM_ATTRIBUTE12  
  ,ITEM_ATTRIBUTE13               -- ITEM_ATTRIBUTE13  
  ,ITEM_ATTRIBUTE14               -- ITEM_ATTRIBUTE14  
  ,ITEM_ATTRIBUTE15               -- ITEM_ATTRIBUTE15  
  ,PARENT_ITEM                    -- Parent Item  
  ,TOP_MODEL                      -- Top Model  
  ,SUPPLIER_PARENT_ITEM           -- Supplier Parent Item  
  ,SUPPLIER_TOP_MODEL             -- Supplier Top Model  
  ,AMOUNT                         -- Amount  
  ,PRICE_BREAK_LOOKUP_CODE        -- Price Break Type  
  ,QUANTITY_COMMITTED             -- Agreement Quantity  
  ,ALLOW_DESCRIPTION_UPDATE_FLAG  -- Allow Description Update  
FROM
  FAI_PO_LINES_INTERFACE -- PoLinesInterfaceBlanket
WHERE
  VERSION_NUMBER = ?LAST_VERSION?


