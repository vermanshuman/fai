UPDATE
FAI_RECORD_R_RAGGR_COND
SET
   AZIONE                 = ?AZIONE?
  ,CODICE_REGGRUPP_CONDIZ = ?CODICE_REGGRUPP_CONDIZ?
  ,DESCR_REGGRUPP_CONDIZ  = ?DESCR_REGGRUPP_CONDIZ?
  ,RIGA                   = ?RIGA?
  ,UPDATE_TS              = SYSDATE
WHERE
OID_FORNITORE=?OID_FORNITORE? AND CODICE_CLIENTE=?CODICE_CLIENTE?