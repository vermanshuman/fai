SELECT 
   APPROV.OID
  ,APPROV.OID_MAGAZZINO
  ,APPROV.OID_FORNITORE
  ,APPROV.CODICE_MINSAN
  ,APPROV.CODICE_EAN
  ,APPROV.QUANTITA
  ,APPROV.DISPONIBILITA_RICHIESTA_REQ
  ,APPROV.DISPONIBILITA_RICHIESTA_RES
  ,APPROV.DISPONIBILITA_CONFERMATA_REQ
  ,APPROV.DISPONIBILITA_CONFERMATA_RES
  ,APPROV.ID_ORDINE
  ,APPROV.ID_RICEVUTA
  ,APPROV.OID_STATUS
  ,APPROV.STATUS_DESCR
  ,APPROV.STATUS_TECH_DESCR
  ,APPROV.STATUS_UPDATED_TS
  ,APPROV.PREZZO_UNITARIO
  ,APPROV.PREZZO_TOTALE
  ,APPROV.ALIQUOTA_IVA
  ,APPROV.ALIQUOTA_IVA_INCLUSA
  ,APPROV.OID_ORDINEOUT
  ,APPROV.CREATION_TS
  ,APPROV2RIGA.OID			        APPROV2RIGA_OID     
  ,APPROV2RIGA.QUANTITA             APPROV2RIGA_QUANTITA
  ,RIGA.OID							RIGA_OID          
  ,RIGA.CODICE_MINSAN               RIGA_CODICE_MINSAN
  ,RIGA.CODICE_EAN                  RIGA_CODICE_EAN   
  ,RIGA.QUANTITA                    RIGA_QUANTITA     
FROM 
   FAI_APPROVVIGIONAMENTO_FARMACO	APPROV
  ,FAI_APPROVVIGFARMACO_ORDINRIGA	APPROV2RIGA
  ,FAI_ORDINE_IN_RIGA_DETTAGLIO		RIGA
WHERE  
   APPROV.OID_STATUS = ?OID_STATUS?
   AND
   APPROV2RIGA.OID_APPROVFARMACO = APPROV.OID
   AND
   APPROV2RIGA.OID_ORDINEINRIGA  = RIGA.OID
ORDER BY
   APPROV.OID
  ,RIGA.OID -- ordinamento per OID: priorit� alle Righe (e quindi Ordini) in attesa da pi� tempo e secondo l'ordine con cui sono ricevute nel "web_order.csv"
  

