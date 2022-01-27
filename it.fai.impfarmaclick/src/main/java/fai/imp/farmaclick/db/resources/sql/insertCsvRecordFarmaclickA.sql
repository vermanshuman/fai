INSERT 
INTO FAI_RECORD_A_ADDEBITO_FINANZ
 (
   OID
  ,OID_FORNITORE
  ,AZIONE
  ,CODICE_CLIENTE
  ,CODICE_ADDEBITO_FINANZ
  ,DESCR_ADDEBITO_FINANZ
  ,PERC_ADDEBITO_FINANZ
  ,NUM_GIORNI_ANNO_COMM
  ,CREATION_TS
  ,UPDATE_TS
)
VALUES (
   OID.NEXTVAL
  ,?OID_FORNITORE?           
  ,?AZIONE?                  
  ,?CODICE_CLIENTE?          
  ,?CODICE_ADDEBITO_FINANZ?  
  ,?DESCR_ADDEBITO_FINANZ?   
  ,?PERC_ADDEBITO_FINANZ?    
  ,?NUM_GIORNI_ANNO_COMM?    
  ,SYSDATE                   
  ,SYSDATE       
)
