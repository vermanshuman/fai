INSERT INTO FAI_UPLOAD_TASK
( OID
, DESCR
, CSV_FILE_NAME
, MAGAZZINO_ACRONYM
, OID_STATUS
,STATUS_DESCR
,STATUS_TECH_DESCR
,STATUS_UPDATED_TS
, CREATION_TS
, RUN_START_TS
, RUN_END_TS
, RUN_DONE
, RUN_DESCR)
VALUES ( ? -- OID
       , ? -- DESCR
       , ? -- ID_VENDITA
       , ? -- CSV_FILE_NAME
       , ? -- OID_STATUS
       , ? -- STATUS_DESCR
       , ? -- STATUS_TECH_DESCR
       , ? -- STATUS_UPDATED_TS
       , ? -- CREATION_TS
       , ? -- RUN_START_TS
       , ? -- RUN_END_TS
       , ? -- RUN_DONE
       , ? -- RUN_DESCR
       )
