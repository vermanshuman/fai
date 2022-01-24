SELECT
    count(*) RECORDS_COUNT
FROM
    FAI_APPROVVIGIONAMENTO_FARMACO approv
   ,FAI_APPROVVIGFARMACO_ORDINRIGA a2r
   ,FAI_ORDINE_IN_RIGA_DETTAGLIO   riga
   ,FAI_ORDINE_IN                  ordine
   ,FAI_ITEM_STATUS                approv_status
WHERE
        approv_status.ACRONYM = 'TO PROCESS'
  AND
        approv.OID_STATUS = approv_status.OID
  AND
    approv.OID_STATUS = approv_status.OID
  AND
    approv.OID_MAGAZZINO ?oidMagazzino?
    AND
    approv.OID_FORNITORE ?oidFornitore?
  AND
        approv.oid = a2r.OID_APPROVFARMACO
  AND
        a2r.OID_ORDINEINRIGA = riga.OID
  AND
        riga.OID_ORDINEIN = ordine.OID
  AND
    ordine.OID = ?oidOrdine?
ORDER BY
    approv.OID
       ,riga.oid
       , ordine.oid

