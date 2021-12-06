INSERT INTO FAI_LINE_LOCATIONS_INTERFACE
(
   INTERFACE_LINE_LOCATION_KEY
  ,INTERFACE_LINE_KEY
  ,SHIPMENT_NUM
  ,SHIP_TO_LOCATION
  ,SHIP_TO_ORGANIZATION_CODE
  ,QUANTITY
  ,PRICE_OVERRIDE
  ,PRICE_DISCOUNT
  ,START_DATE
  ,END_DATE
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
)
SELECT
   'intf_line_loc_1'  INTERFACE_LINE_LOCATION_KEY  -- Interface Line Location Key *** required ***
  ,'intf_line_1'      INTERFACE_LINE_KEY           -- Interface Line Key *** required ***
  ,NULL               SHIPMENT_NUM                 -- Price Break
  ,NULL               SHIP_TO_LOCATION             -- Ship-to Location
  ,NULL               SHIP_TO_ORGANIZATION_CODE    -- Ship-to Organization
  ,1                  QUANTITY                     -- Quantity
  ,12                 PRICE_OVERRIDE               -- Price
  ,20                 PRICE_DISCOUNT               -- Discount (%)
  ,NULL               START_DATE                   -- Start Date
  ,NULL               END_DATE                     -- End Date
  ,NULL               ATTRIBUTE_CATEGORY           -- ATTRIBUTE_CATEGORY
  ,NULL               ATTRIBUTE1                   -- ATTRIBUTE1
  ,NULL               ATTRIBUTE2                   -- ATTRIBUTE2
  ,NULL               ATTRIBUTE3                   -- ATTRIBUTE3
  ,NULL               ATTRIBUTE4                   -- ATTRIBUTE4
  ,NULL               ATTRIBUTE5                   -- ATTRIBUTE5
  ,NULL               ATTRIBUTE6                   -- ATTRIBUTE6
  ,NULL               ATTRIBUTE7                   -- ATTRIBUTE7
  ,NULL               ATTRIBUTE8                   -- ATTRIBUTE8
  ,NULL               ATTRIBUTE9                   -- ATTRIBUTE9
  ,NULL               ATTRIBUTE10                  -- ATTRIBUTE10
  ,NULL               ATTRIBUTE11                  -- ATTRIBUTE11
  ,NULL               ATTRIBUTE12                  -- ATTRIBUTE12
  ,NULL               ATTRIBUTE13                  -- ATTRIBUTE13
  ,NULL               ATTRIBUTE14                  -- ATTRIBUTE14
  ,NULL               ATTRIBUTE15                  -- ATTRIBUTE15
  ,NULL               ATTRIBUTE16                  -- ATTRIBUTE16
  ,NULL               ATTRIBUTE17                  -- ATTRIBUTE17
  ,NULL               ATTRIBUTE18                  -- ATTRIBUTE18
  ,NULL               ATTRIBUTE19                  -- ATTRIBUTE19
  ,NULL               ATTRIBUTE20                  -- ATTRIBUTE20
  ,NULL               ATTRIBUTE_DATE1              -- ATTRIBUTE_DATE1
  ,NULL               ATTRIBUTE_DATE2              -- ATTRIBUTE_DATE2
  ,NULL               ATTRIBUTE_DATE3              -- ATTRIBUTE_DATE3
  ,NULL               ATTRIBUTE_DATE4              -- ATTRIBUTE_DATE4
  ,NULL               ATTRIBUTE_DATE5              -- ATTRIBUTE_DATE5
  ,NULL               ATTRIBUTE_DATE6              -- ATTRIBUTE_DATE6
  ,NULL               ATTRIBUTE_DATE7              -- ATTRIBUTE_DATE7
  ,NULL               ATTRIBUTE_DATE8              -- ATTRIBUTE_DATE8
  ,NULL               ATTRIBUTE_DATE9              -- ATTRIBUTE_DATE9
  ,NULL               ATTRIBUTE_DATE10             -- ATTRIBUTE_DATE10
  ,NULL               ATTRIBUTE_NUMBER1            -- ATTRIBUTE_NUMBER1
  ,NULL               ATTRIBUTE_NUMBER2            -- ATTRIBUTE_NUMBER2
  ,NULL               ATTRIBUTE_NUMBER3            -- ATTRIBUTE_NUMBER3
  ,NULL               ATTRIBUTE_NUMBER4            -- ATTRIBUTE_NUMBER4
  ,NULL               ATTRIBUTE_NUMBER5            -- ATTRIBUTE_NUMBER5
  ,NULL               ATTRIBUTE_NUMBER6            -- ATTRIBUTE_NUMBER6
  ,NULL               ATTRIBUTE_NUMBER7            -- ATTRIBUTE_NUMBER7
  ,NULL               ATTRIBUTE_NUMBER8            -- ATTRIBUTE_NUMBER8
  ,NULL               ATTRIBUTE_NUMBER9            -- ATTRIBUTE_NUMBER9
  ,NULL               ATTRIBUTE_NUMBER10           -- ATTRIBUTE_NUMBER10
  ,NULL               ATTRIBUTE_TIMESTAMP1         -- ATTRIBUTE_TIMESTAMP1
  ,NULL               ATTRIBUTE_TIMESTAMP2         -- ATTRIBUTE_TIMESTAMP2
  ,NULL               ATTRIBUTE_TIMESTAMP3         -- ATTRIBUTE_TIMESTAMP3
  ,NULL               ATTRIBUTE_TIMESTAMP4         -- ATTRIBUTE_TIMESTAMP4
  ,NULL               ATTRIBUTE_TIMESTAMP5         -- ATTRIBUTE_TIMESTAMP5
  ,NULL               ATTRIBUTE_TIMESTAMP6         -- ATTRIBUTE_TIMESTAMP6
  ,NULL               ATTRIBUTE_TIMESTAMP7         -- ATTRIBUTE_TIMESTAMP7
  ,NULL               ATTRIBUTE_TIMESTAMP8         -- ATTRIBUTE_TIMESTAMP8
  ,NULL               ATTRIBUTE_TIMESTAMP9         -- ATTRIBUTE_TIMESTAMP9
  ,NULL               ATTRIBUTE_TIMESTAMP10        -- ATTRIBUTE_TIMESTAMP10
FROM DUAL


