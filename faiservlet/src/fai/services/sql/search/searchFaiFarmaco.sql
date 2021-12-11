SELECT
   OID, 
   IS_DELETED,
   COD_MINSAN, 
   COD_EAN, 
   DESCR_PROD, 
   ANAGRAFICA, 
   DESCR_TIPO_PROD,  
   DESCR_ESTESA_TIPO_PROD
FROM 
  ?tableName? vff
  WHERE 1 = 1 
  ?wherecondition?