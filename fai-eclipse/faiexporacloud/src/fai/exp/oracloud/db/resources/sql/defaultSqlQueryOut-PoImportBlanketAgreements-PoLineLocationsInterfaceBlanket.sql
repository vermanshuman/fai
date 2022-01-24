SELECT
   INTERFACE_LINE_LOCATION_KEY  -- Interface Line Location Key (es. intf_line_loc_1) *** required ***  
  ,INTERFACE_LINE_KEY           -- Interface Line Key (es. intf_line_1) *** required ***  
  ,SHIPMENT_NUM                 -- Price Break  
  ,SHIP_TO_LOCATION             -- Ship-to Location  
  ,SHIP_TO_ORGANIZATION_CODE    -- Ship-to Organization  
  ,QUANTITY                     -- Quantity (es. 1)  
  ,PRICE_OVERRIDE               -- Price (es. 12)  
  ,PRICE_DISCOUNT               -- Discount (%) (es. 20)  
  ,START_DATE                   -- Start Date  
  ,END_DATE                     -- End Date  
  ,ATTRIBUTE_CATEGORY           -- ATTRIBUTE_CATEGORY  
  ,ATTRIBUTE1                   -- ATTRIBUTE1  
  ,ATTRIBUTE2                   -- ATTRIBUTE2  
  ,ATTRIBUTE3                   -- ATTRIBUTE3  
  ,ATTRIBUTE4                   -- ATTRIBUTE4  
  ,ATTRIBUTE5                   -- ATTRIBUTE5  
  ,ATTRIBUTE6                   -- ATTRIBUTE6  
  ,ATTRIBUTE7                   -- ATTRIBUTE7  
  ,ATTRIBUTE8                   -- ATTRIBUTE8  
  ,ATTRIBUTE9                   -- ATTRIBUTE9  
  ,ATTRIBUTE10                  -- ATTRIBUTE10  
  ,ATTRIBUTE11                  -- ATTRIBUTE11  
  ,ATTRIBUTE12                  -- ATTRIBUTE12  
  ,ATTRIBUTE13                  -- ATTRIBUTE13  
  ,ATTRIBUTE14                  -- ATTRIBUTE14  
  ,ATTRIBUTE15                  -- ATTRIBUTE15  
  ,ATTRIBUTE16                  -- ATTRIBUTE16  
  ,ATTRIBUTE17                  -- ATTRIBUTE17  
  ,ATTRIBUTE18                  -- ATTRIBUTE18  
  ,ATTRIBUTE19                  -- ATTRIBUTE19  
  ,ATTRIBUTE20                  -- ATTRIBUTE20  
  ,ATTRIBUTE_DATE1              -- ATTRIBUTE_DATE1  
  ,ATTRIBUTE_DATE2              -- ATTRIBUTE_DATE2  
  ,ATTRIBUTE_DATE3              -- ATTRIBUTE_DATE3  
  ,ATTRIBUTE_DATE4              -- ATTRIBUTE_DATE4  
  ,ATTRIBUTE_DATE5              -- ATTRIBUTE_DATE5  
  ,ATTRIBUTE_DATE6              -- ATTRIBUTE_DATE6  
  ,ATTRIBUTE_DATE7              -- ATTRIBUTE_DATE7  
  ,ATTRIBUTE_DATE8              -- ATTRIBUTE_DATE8  
  ,ATTRIBUTE_DATE9              -- ATTRIBUTE_DATE9  
  ,ATTRIBUTE_DATE10             -- ATTRIBUTE_DATE10  
  ,ATTRIBUTE_NUMBER1            -- ATTRIBUTE_NUMBER1  
  ,ATTRIBUTE_NUMBER2            -- ATTRIBUTE_NUMBER2  
  ,ATTRIBUTE_NUMBER3            -- ATTRIBUTE_NUMBER3  
  ,ATTRIBUTE_NUMBER4            -- ATTRIBUTE_NUMBER4  
  ,ATTRIBUTE_NUMBER5            -- ATTRIBUTE_NUMBER5  
  ,ATTRIBUTE_NUMBER6            -- ATTRIBUTE_NUMBER6  
  ,ATTRIBUTE_NUMBER7            -- ATTRIBUTE_NUMBER7  
  ,ATTRIBUTE_NUMBER8            -- ATTRIBUTE_NUMBER8  
  ,ATTRIBUTE_NUMBER9            -- ATTRIBUTE_NUMBER9  
  ,ATTRIBUTE_NUMBER10           -- ATTRIBUTE_NUMBER10  
  ,ATTRIBUTE_TIMESTAMP1         -- ATTRIBUTE_TIMESTAMP1  
  ,ATTRIBUTE_TIMESTAMP2         -- ATTRIBUTE_TIMESTAMP2  
  ,ATTRIBUTE_TIMESTAMP3         -- ATTRIBUTE_TIMESTAMP3  
  ,ATTRIBUTE_TIMESTAMP4         -- ATTRIBUTE_TIMESTAMP4  
  ,ATTRIBUTE_TIMESTAMP5         -- ATTRIBUTE_TIMESTAMP5  
  ,ATTRIBUTE_TIMESTAMP6         -- ATTRIBUTE_TIMESTAMP6  
  ,ATTRIBUTE_TIMESTAMP7         -- ATTRIBUTE_TIMESTAMP7  
  ,ATTRIBUTE_TIMESTAMP8         -- ATTRIBUTE_TIMESTAMP8  
  ,ATTRIBUTE_TIMESTAMP9         -- ATTRIBUTE_TIMESTAMP9  
  ,ATTRIBUTE_TIMESTAMP10        -- ATTRIBUTE_TIMESTAMP10  
FROM
  FAI_LINE_LOCATIONS_INTERFACE -- PoLineLocationsInterfaceBlanket
WHERE
  VERSION_NUMBER = ?LAST_VERSION?


