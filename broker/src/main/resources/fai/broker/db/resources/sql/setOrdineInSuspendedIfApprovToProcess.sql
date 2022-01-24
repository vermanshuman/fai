UPDATE   FAI_ORDINE_IN
SET      OID_STATUS = (SELECT OID FROM FAI_ITEM_STATUS WHERE ACRONYM = 'SUSPENDED')
        ,STATUS_DESCR = 'sospeso perché referenziato da FAI_APPROVVIGIONAMENTO_FARMACO in stato "TO PROCESS"'
        ,STATUS_TECH_DESCR = NULL
        ,STATUS_UPDATED_TS = SYSDATE
WHERE   OID_STATUS = (SELECT OID FROM FAI_ITEM_STATUS WHERE ACRONYM = 'PROCESSED')
		AND
        OID IN (   SELECT
                        ordine.oid
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
                        approv.oid = a2r.OID_APPROVFARMACO
                        AND
                        a2r.OID_ORDINEINRIGA = riga.OID
                        AND
                        riga.OID_ORDINEIN = ordine.OID
                 )
            