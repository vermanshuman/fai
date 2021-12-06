INSERT INTO FAI_FARMACO( OID, IS_DELETED,COD_MINSAN, COD_EAN, DESCR_PROD, ANAGRAFICA, DESCR_TIPO_PROD,  DESCR_ESTESA_TIPO_PROD)
select 
?oid?, 
'?isDeleted?', 
'?codMinsan?', 
'?codEan?',
'?descrProd?',
'?anagrafica?',
'?descrTipoProd?',
'?descrEstesaTipoProd?'
from 
dual
where 
 (SELECT COUNT(*) codMinsan FROM V_FAI_FARMACO WHERE COD_MINSAN like '?codMinsan?') = 0
 and
 (SELECT COUNT(*) codEan FROM V_FAI_FARMACO WHERE COD_EAN like '?codEan?') = 0
 
 
 