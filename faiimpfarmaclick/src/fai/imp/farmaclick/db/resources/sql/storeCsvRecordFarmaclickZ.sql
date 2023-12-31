MERGE INTO FAI_RECORD_Z_CHIUSURA tgt
USING
(
SELECT
   ?OID_FORNITORE?     OID_FORNITORE
  ,?AZIONE?            AZIONE
  ,?CODICE_CLIENTE?    CODICE_CLIENTE
  ,SYSDATE             CREATION_TS
  ,?DATA_ORA_GENERAZ?  DATA_ORA_GENERAZ
  ,SYSDATE             UPDATE_TS
FROM DUAL
) src
ON (tgt.OID_FORNITORE=src.OID_FORNITORE AND tgt.CODICE_CLIENTE=src.CODICE_CLIENTE)
WHEN MATCHED
THEN UPDATE SET
   tgt.AZIONE           = src.AZIONE
  ,tgt.DATA_ORA_GENERAZ = src.DATA_ORA_GENERAZ
  ,tgt.UPDATE_TS        = src.UPDATE_TS
WHEN NOT MATCHED
THEN INSERT
(
   OID
  ,OID_FORNITORE
  ,AZIONE
  ,CODICE_CLIENTE
  ,CREATION_TS
  ,DATA_ORA_GENERAZ
  ,UPDATE_TS
)
VALUES
(
   OID.NEXTVAL
  ,src.OID_FORNITORE
  ,src.AZIONE
  ,src.CODICE_CLIENTE
  ,src.CREATION_TS
  ,src.DATA_ORA_GENERAZ
  ,src.UPDATE_TS
)
