UPDATE 
FAI_RECORD_Z_CHIUSURA
SET
   AZIONE           = ?AZIONE?
  ,DATA_ORA_GENERAZ = ?DATA_ORA_GENERAZ?
  ,UPDATE_TS        = SYSDATE
WHERE
OID_FORNITORE=?OID_FORNITORE? AND CODICE_CLIENTE=?CODICE_CLIENTE?

