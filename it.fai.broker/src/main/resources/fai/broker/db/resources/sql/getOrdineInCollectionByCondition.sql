SELECT
    OID
     ,INPUT_RESOURCE
     ,INPUT_RESOURCE_FULL_PATH
     ,BATCH_ID
     ,OID_STATUS
     ,STATUS_DESCR
     ,STATUS_TECH_DESCR
     ,STATUS_UPDATED_TS
     ,UNIQUE_ID
     ,MAGAZZINO_ACRONYM
FROM
    FAI_ORDINE_IN_COLLECTION
        ?whereCondition?
ORDER BY
    MAGAZZINO_ACRONYM
