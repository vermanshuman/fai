SELECT
    COLL.OID
     ,COLL.INPUT_RESOURCE
FROM
    FAI_ORDINE_IN  ORDINE
   ,FAI_ORDINE_IN_COLLECTION COLL
        ?whereCondition?
ORDER BY
    MAGAZZINO_ACRONYM