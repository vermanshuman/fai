SELECT
     ordine.oid                     ordine_oid
    ,ordine.OID_STATUS              ordine_OID_STATUS
    ,ordine.STATUS_DESCR            ordine_STATUS_DESCR
    ,ordine.STATUS_TECH_DESCR       ordine_STATUS_TECH_DESCR
    ,ordine.STATUS_UPDATED_TS       ordine_STATUS_UPDATED_TS
    ,riga.oid                       riga_oid
    ,riga.CODICE_MINSAN             riga_CODICE_MINSAN
    ,riga.CODICE_EAN                riga_CODICE_EAN
    ,riga.QUANTITA                  riga_QUANTITA
    ,a2r.OID                        a2r_OID
    ,a2r.QUANTITA                   a2r_QUANTITA
    ,approv.OID                     approv_OID
    ,approv.CODICE_MINSAN           approv_CODICE_MINSAN
    ,approv.CODICE_EAN              approv_CODICE_EAN
    ,approv.QUANTITA                approv_QUANTITA
    ,approv.OID_STATUS              approv_OID_STATUS
    ,approv.STATUS_DESCR            approv_STATUS_DESCR
    ,approv.STATUS_TECH_DESCR       approv_STATUS_TECH_DESCR
    ,approv.STATUS_UPDATED_TS       approv_STATUS_UPDATED_TS
FROM
     FAI_APPROVVIGIONAMENTO_FARMACO approv
    ,FAI_APPROVVIGFARMACO_ORDINRIGA a2r
    ,FAI_ORDINE_IN_RIGA_DETTAGLIO   riga
    ,FAI_ORDINE_IN                  ordine
    ,FAI_ITEM_STATUS                approv_status
    ,FAI_ITEM_STATUS                ordine_status
WHERE
    approv_status.ACRONYM = 'TO PROCESS'
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
    ordine.OID_STATUS = ordine_status.OID
    AND
    ordine_status.ACRONYM = 'PROCESSED'
ORDER BY     
     approv_OID
    ,riga_oid
    ,ordine_oid
    