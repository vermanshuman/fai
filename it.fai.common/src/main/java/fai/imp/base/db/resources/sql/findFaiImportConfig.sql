SELECT 
  OID
 ,ACRONYM
 ,CLASS_NAME
 ,DESCR
 ,LAST_RUN_DESCR
 ,LAST_RUN_DONE
 ,LAST_RUN_END_TS
 ,LAST_RUN_START_TS
 ,LAST_RUN_TYPE
 ,SERVICE_LOGIN
 ,SERVICE_PASS
 ,SERVICE_PASS_ENCR
 ,SERVICE_QUERY_SMTWTFS
 ,SERVICE_QUERY_TIME
 ,SERVICE_QUERY_URL
 ,SERVICE_QUERY_ZIPPED_CONTENT
 ,SERVICE_RESUME_PREV_SESS
 ,SERVICE_PASS_SECOND_LEVEL
 ,SERVICE_API_LEVEL
FROM 
 FAI_IMPORT_CONFIG
?whereCondition?
ORDER BY 
 ACRONYM   
