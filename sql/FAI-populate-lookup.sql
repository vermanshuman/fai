--
-- FAI_ITEM_STATUS
--


INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 1 ,'TO PROCESS','TO PROCESS'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'TO PROCESS')
      T WHERE CNT = 0;
      
INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 2 ,'PROCESSING','PROCESSING'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'PROCESSING')
      T WHERE CNT = 0;

INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 3 ,'PROCESSED','PROCESSED'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'PROCESSED')
      T WHERE CNT = 0;
      
INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 4 ,'ERROR','ERROR'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'ERROR')
      T WHERE CNT = 0;

INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 5 ,'SUSPENDED','SUSPENDED'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'SUSPENDED')
      T WHERE CNT = 0;

INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 6 ,'TO APPROVE','TO APPROVE'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'TO APPROVE')
      T WHERE CNT = 0;

INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 7 ,'APPROVED','APPROVED'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'APPROVED')
      T WHERE CNT = 0;

INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 8 ,'VOIDED','VOIDED'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'VOIDED')
      T WHERE CNT = 0;
	  
	  
INSERT INTO FAI_ITEM_STATUS (OID, ACRONYM, DESCR)
      SELECT 9 ,'FULLFILLED','FULLFILLED'
      FROM (SELECT COUNT(*) CNT FROM FAI_ITEM_STATUS WHERE ACRONYM = 'FULLFILLED')
      T WHERE CNT = 0;


--
-- FAI_TIPO_FARMACO
--      
      
INSERT INTO FAI_TIPO_FARMACO (OID, ACRONYM, DESCR)
      SELECT 1 ,'FARMACO', 'FARMACO'
      FROM (SELECT COUNT(*) CNT FROM FAI_TIPO_FARMACO WHERE ACRONYM = 'FARMACO')
      T WHERE CNT = 0;

INSERT INTO FAI_TIPO_FARMACO (OID, ACRONYM, DESCR)
      SELECT 2 ,'PARAFARMACO', 'PARAFARMACO'
      FROM (SELECT COUNT(*) CNT FROM FAI_TIPO_FARMACO WHERE ACRONYM = 'PARAFARMACO')
      T WHERE CNT = 0;



--
-- FAI_UPLOAD_CSV_STATUS
--


INSERT INTO FAI_UPLOAD_CSV_STATUS (OID, ACRONYM, DESCR)
      SELECT 1 ,'TO PROCESS','ESEGUIRE'
      FROM (SELECT COUNT(*) CNT FROM FAI_UPLOAD_CSV_STATUS WHERE ACRONYM = 'TO PROCESS')
      T WHERE CNT = 0;
      
INSERT INTO FAI_UPLOAD_CSV_STATUS (OID, ACRONYM, DESCR)
      SELECT 2 ,'PROCESSING','IN CORSO'
      FROM (SELECT COUNT(*) CNT FROM FAI_UPLOAD_CSV_STATUS WHERE ACRONYM = 'PROCESSING')
      T WHERE CNT = 0;

INSERT INTO FAI_UPLOAD_CSV_STATUS (OID, ACRONYM, DESCR)
      SELECT 3 ,'PROCESSED','COMPLETATO'
      FROM (SELECT COUNT(*) CNT FROM FAI_UPLOAD_CSV_STATUS WHERE ACRONYM = 'PROCESSED')
      T WHERE CNT = 0;
      
INSERT INTO FAI_UPLOAD_CSV_STATUS (OID, ACRONYM, DESCR)
      SELECT 4 ,'ERROR','ANNULATO'
      FROM (SELECT COUNT(*) CNT FROM FAI_UPLOAD_CSV_STATUS WHERE ACRONYM = 'ERROR')
      T WHERE CNT = 0;
	  
	  
	  
   INSERT INTO FAI.FAI_UPLOAD_TASK_PROPERTY
("OID", "KEY", VALUE)
 SELECT 1, 'csvUploadRootDir', TO_CLOB('G:\FAI\docs')
      FROM (SELECT COUNT(*) CNT FROM FAI.FAI_UPLOAD_TASK_PROPERTY WHERE KEY = 'csvUploadRootDir')
	    T WHERE CNT = 0;




INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (1,9,1,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (2,11,1,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (3,9,2,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (4,11,2,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (5,9,3,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (6,11,3,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (7,9,4,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (8,11,4,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (9,9,5,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (10,11,5,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (11,9,6,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (12,11,6,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (13,9,7,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');
INSERT INTO FAI.FAI_FORNITORE_CALENDAR ("OID",OID_FORNITORE,DATE_OF_WEEK,HOUR_START,HOUR_END) VALUES (14,11,7,TIMESTAMP '2021-11-24 00:00:00.000000',TIMESTAMP '2021-11-24 23:59:59.000000');

--
-- commit
--      
commit;
              
			  
			  