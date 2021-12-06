--------------------------------------------------------
--  File created - Monday-November-08-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence OID
--------------------------------------------------------

   CREATE SEQUENCE  "FAI"."OID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1041 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table FAI_APPROVVIGFARMACO_ORDINRIGA
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" 
   (	"OID" NUMBER(*,0), 
	"OID_APPROVFARMACO" NUMBER(*,0), 
	"OID_ORDINEINRIGA" NUMBER(*,0), 
	"QUANTITA" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA"."OID_APPROVFARMACO" IS 'Identificativo univoco della tupla (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA"."OID_ORDINEINRIGA" IS 'identificativo univoco della tupla (2021.06.10)';
   COMMENT ON TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA"  IS 'Consente di associare un recod FAI_APPROVVIGIONAMENTO_FARMACO a tutte le FAI_ORDINE_IN_RIGA_DETTAGLIO che detrerminano il record di approvvigionamento. Ovviamente risulterà sempre FAI_APPROVVIGIONAMENTO_FARMACO.QUANTITA = SUM(FAI_APPROVVIGFARMACO_ORDINRIGA.QUANTITA). Si osservi che il record di approvvigionamento può far riferimento, non solo a righe di diversi FAI_ORDINE_IN, ma anche ad FAI_ORDINE_IN relativi a FAI_ORDINE_IN_COLLECTION differenti  (2021.06.30)';
--------------------------------------------------------
--  DDL for Table FAI_APPROVVIGIONAMENTO_FARMACO
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" 
   (	"OID" NUMBER(*,0), 
	"OID_MAGAZZINO" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"CODICE_MINSAN" VARCHAR2(9 BYTE), 
	"QUANTITA" NUMBER(*,0), 
	"DISPONIBILITA_RICHIESTA_REQ" DATE, 
	"DISPONIBILITA_CONFERMATA_REQ" DATE, 
	"ID_ORDINE" VARCHAR2(30 BYTE), 
	"ID_RICEVUTA" VARCHAR2(30 BYTE), 
	"DISPONIBILITA_RICHIESTA_RES" DATE, 
	"DISPONIBILITA_CONFERMATA_RES" DATE, 
	"OID_STATUS" NUMBER(*,0) DEFAULT 1, 
	"STATUS_DESCR" CLOB, 
	"STATUS_TECH_DESCR" CLOB, 
	"PREZZO_UNITARIO" NUMBER(22,4), 
	"PREZZO_TOTALE" NUMBER(22,4), 
	"ALIQUOTA_IVA" NUMBER(22,4), 
	"ALIQUOTA_IVA_INCLUSA" CHAR(1 BYTE), 
	"OID_ORDINEOUT" NUMBER(*,0), 
	"STATUS_UPDATED_TS" DATE DEFAULT SYSDATE, 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"CODICE_EAN" VARCHAR2(13 BYTE), 
	"MAGAZZINO_ACRONYM" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("STATUS_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("STATUS_TECH_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."OID" IS 'Identificativo univoco della tupla (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."OID_MAGAZZINO" IS 'foreign key verso FAI_MAGAZZINO. NULL se la QUANTITA indicata viene soddisfatta per mezzo di un FAI_FORNITORE, oppure se il recordo è stato appena creato. In tal caso QUANTITA coinciderà con QUANTITA di FAI_FABBISOGNO_FARMACO e anche OID_FORNITORE sarà NULL (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."OID_FORNITORE" IS 'foreign key verso FAI_FORNITORE. NULL se la QUANTITA indicata viene soddisfatta per mezzo di un FAI_MAGAZZINO, oppure se il recordo è stato appena creato. In tal caso QUANTITA coinciderà con QUANTITA di FAI_FABBISOGNO_FARMACO e anche OID_MAGAZZINO sarà NULL (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."CODICE_MINSAN" IS 'codice MinSan identificativo del farmaco. Almeno uno dei due - CODICE_MINSAN o CODICE_EAN - deve essere valorizzato (2021.06.28)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."QUANTITA" IS 'quantità soddisfatta dal presente record (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."DISPONIBILITA_RICHIESTA_REQ" IS 'Se NOT NULL indica la data/ora alla quale la richiesta di disponibilità (verso il Magazzino o il Fornitore) è stata inoltrata. (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."DISPONIBILITA_CONFERMATA_REQ" IS 'Se NOT NULL indica la data/ora alla quale richiesta di confermare la quantità (precedentemente data come disponibile) è inviata (al Magazzino o al Fornitore).  (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."ID_ORDINE" IS 'Identificativo dell''Ordine verso il Magazzino o il Fornitore, se applicabile (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."ID_RICEVUTA" IS 'Identificativo della Ricevutap per l''Ordine verso il Magazzino o il Fornitore, se applicabile (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."DISPONIBILITA_RICHIESTA_RES" IS 'Se NOT NULL indica la data/ora alla quale è stata ricevuta la risposta alla richiesta di disponibilità (verso il Magazzino o il Fornitore), qualunque essa sia (es., anche "farmaco non disponibile") (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."DISPONIBILITA_CONFERMATA_RES" IS 'Se NOT NULL indica la data/ora alla quale è stata ricevuta la risposta alla richiesta di conferma della qualità (verso il Magazzino o il Fornitore). (2021.06.17)

';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."OID_STATUS" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."STATUS_DESCR" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."STATUS_TECH_DESCR" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."PREZZO_UNITARIO" IS 'prezzo unitario del farmaco (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."PREZZO_TOTALE" IS 'prezzo unitario del farmaco. In genere dovrebbe coincidere con PREZZO_UNITARIO * QUANTITA, a meno di particolari gestioni da parte del Fornitore (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."ALIQUOTA_IVA" IS 'aliquota IVA espresa in percentuale. 20.5 rappresenta il "20.5%" (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."ALIQUOTA_IVA_INCLUSA" IS '''S'' se l''aliquota IVA è inclusa, altrimenti ''N''.  (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."OID_ORDINEOUT" IS 'foreign key verso FAI_ORDINE_OUT (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."STATUS_UPDATED_TS" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."CREATION_TS" IS 'data/ora di creazione del record (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."CODICE_EAN" IS 'codice EAN identificativo del farmaco. Almeno uno dei due - CODICE_MINSAN o CODICE_EAN - deve essere valorizzato (2021.06.28)';
   COMMENT ON COLUMN "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"."MAGAZZINO_ACRONYM" IS 'Ha la stessa funzione dell''omonimo campo della tabella FAI_ORDINE_IN_RIGA_DETTAGLIO, cui si rimanda e di cui rappresenta una copia. Va da se che tutte le FAI_ORDINE_IN_RIGA_DETTAGLIO referenziate per mezzo dell''associativa FAI_APPROVVIGFARMACO_ORDINRIGA saranno caratterizzate dallo stesso valore per il campo MAGAZZINO_ACRONYM. Al momento (2021.09.21) si ipotizza che non possano esistere due FAI_ORDINE_IN_RIGA_DETTAGLIO che, a parità di Farmaco, presentano MAGAZZINO_ACRONYM diversi (2021.09.21)';
   COMMENT ON TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO"  IS 'un record di questa tabella nasce, in stato "TO_PROCESS", sempre contestualmente ("commit") al record FAI_FABBISOGNO_FARMACO e reca la stessa quantità del record referenziato, oltre che lo stesso CODICE_MINSAN, con entrambe le fk verso Magazzino e Fornitore NULL. Man mano che il processo reperisce il Farmaco presso i Magazzini o i Fornitori, vengono creati nuovi record, referenzianti la fonte individuata, con la QUANTITA che la fonte riesce a soddisfare. La SUM(QUANTITA) sarà sempre pari alla QUANTITA che record referenziano nel Fabbisogno. Quando la QUANTITA richiesta è stata reperita, il record con le due fk NULL viene rimosso (2021.06.17)';
--------------------------------------------------------
--  DDL for Table FAI_COLUMN_EXPORT
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_COLUMN_EXPORT" 
   (	"OID" NUMBER(*,0), 
	"COLUMN_IDX" NUMBER(*,0), 
	"COLUMN_NAME_ORACLE" VARCHAR2(30 BYTE), 
	"COLUMN_NAME_EXPORT" VARCHAR2(100 BYTE), 
	"REQUIRED" CHAR(1 BYTE), 
	"SQL_TYPE" VARCHAR2(30 BYTE), 
	"SQL_TYPE_LEN" NUMBER(*,0), 
	"SQL_TYPE_SCALE" NUMBER(*,0), 
	"DATE_FORMAT_PATTERN" VARCHAR2(100 BYTE), 
	"NUMB_FORMAT_PATTERN" VARCHAR2(100 BYTE), 
	"NUMB_FORMAT_GRPCH" CHAR(1 BYTE), 
	"NUMB_FORMAT_DECCH" CHAR(1 BYTE), 
	"OID_TABLEEXPORT" NUMBER(*,0), 
	"TEXT_FORMAT_TYPE" CHAR(2 BYTE), 
	"TEXT_FORMAT_SEARCH" VARCHAR2(50 BYTE), 
	"TEXT_FORMAT_REPLACE" VARCHAR2(50 BYTE), 
	"INDEX_NAME_ORACLE" VARCHAR2(30 BYTE), 
	"INDEX_NAME_TABLESPACE" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."OID" IS 'identificativo univoco della tupla (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."COLUMN_IDX" IS 'analogamente a GROUP_IDX e TABLE_IDX consente di definire l''ordinamento delle colonne  (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."COLUMN_NAME_ORACLE" IS 'nome della colonna conforme ai vincoli Oracle per le tabelle  (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."COLUMN_NAME_EXPORT" IS 'nome della colonna nel sistema di destinazione. Al momento (2021.05.18) tale nome non sarà esportato verso il prodotto Oracle, in quanto il foramto CSV previsto non include la riga di intestazione (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."REQUIRED" IS 'Se ''S'' indica che il campo è obbligatorio, altrimenti ''N'' (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."SQL_TYPE" IS 'insieme a SQL_TYPE_LEN ed SQL_TYPE_SCALE definisce il tipo SQL. Ad esempio per "VARCHAR2(10)" si avrebbe SQL_TYPE "VARCHAR2", SQL_TYPE_LEN 10 ed SQL_TYPE_SCALE NULL. Per NUMBER(20,4), invece, rispettivamente, "NUMBER", 20 e 4. Al momento (2021.05.18), dato il formato CSV e l''esigenza di formattare i valori data e numerici, l''unico tipo supportato inizialmente sarà "VARCHAR2" (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."SQL_TYPE_LEN" IS 'v. quanto documentato per SQL_TYPE (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."SQL_TYPE_SCALE" IS 'v. quanto documentato per SQL_TYPE (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."DATE_FORMAT_PATTERN" IS 'se SQL_TYPE è "DATE" definisce, se NOT NULL, il pattern di formattazione da adottare per convertire la data in testo (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."NUMB_FORMAT_PATTERN" IS 'se SQL_TYPE è "NUMBER", definisce, se NOT NULL, il pattern di formattazione da adottare per convertire il dato numerico in testo. V. anche NUB_FORMAT_GRPCH e NUB_FORMAT_DECCH (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."NUMB_FORMAT_GRPCH" IS 'Se NOT NULL indica il carattere di raggruppamento da adottare nel pattern NUMB_FORMAT_PATTERN (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."NUMB_FORMAT_DECCH" IS 'Se NOT NULL indica il carattere di separazione dei decimali da adottare nel pattern NUMB_FORMAT_PATTERN (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."OID_TABLEEXPORT" IS 'identificativo univoco della tupla (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."TEXT_FORMAT_TYPE" IS 'Se NOT NULL, ed SQL_TYPE è "VARCHAR2" o "CHAR", indica come interpretare i parametri TEXT_FORMAT_SEARCH e TEXT_FORMAT_REPLACE (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."TEXT_FORMAT_SEARCH" IS 'Se TEXT_FORMAT_TYPE è "RE" ("Regular Expression"), allora questo parametro sarà interprato come espressione regolare e, concretamente, costituirà il primo parametro del metodo replaceAll della classe java.lang.String. Se, invece, TEXT_FORMAT_TYPE è "SR" ("Search and Replace"), allora il testo da cercare, ossia il primo parametro del metodo replace della classe java.lang.String (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."TEXT_FORMAT_REPLACE" IS 'Se TEXT_FORMAT_TYPE (cui si rimanda) è NOT NULL, rappresenta il secondo parametro dell''operazione di formattazione del testo, ossia, concretamente, il secondo parametro del metodo replaceAll o replace di java.lang.String(2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."INDEX_NAME_ORACLE" IS 'Se NOT NULL indica il nome dell''INDEX non-unique per questa colonna. L''indice viene creato solo contestualmente alla tabella: modificare INDEX_NAME_ORACLE o INDEX_NAME_TABLESPACE successivamente alla creazione della tabella non ha alcun effetto (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_COLUMN_EXPORT"."INDEX_NAME_TABLESPACE" IS 'Se NOT NULL indica il TABLESPACE nel quale crare l''indice INDEX_NAME_ORACLE (2021.05.28)';
   COMMENT ON TABLE "FAI"."FAI_COLUMN_EXPORT"  IS 'fare riferimento a quanto documentato per FAI_TABLE_EXPORT ed alla documentazione ai singoli campi della presente (2021.05.18)';
--------------------------------------------------------
--  DDL for Table FAI_DISPONIBILITA_REQ_TEMP
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_DISPONIBILITA_REQ_TEMP" 
   (	"OID" NUMBER(*,0), 
	"OID_DISPONIBILITATEMP" NUMBER(*,0), 
	"CODICE_MINSAN" VARCHAR2(9 BYTE), 
	"CODICE_EAN" VARCHAR2(13 BYTE), 
	"QUANTITA_RICHIESTA" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_REQ_TEMP"."OID" IS 'Identificativo univoco della tupla (2021.07.26)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_REQ_TEMP"."OID_DISPONIBILITATEMP" IS 'foreign key verso FAI_DISPONIBILITA_TEMP (2021.07.26)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_REQ_TEMP"."CODICE_MINSAN" IS 'fare riferimento alla documentazione della tabella (2021.07.26)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_REQ_TEMP"."CODICE_EAN" IS 'fare riferimento alla documentazione della tabella (2021.07.26)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_REQ_TEMP"."QUANTITA_RICHIESTA" IS 'fare riferimento alla documentazione della tabella (2021.07.26)';
   COMMENT ON TABLE "FAI"."FAI_DISPONIBILITA_REQ_TEMP"  IS 'fare riferimento alla documentazione della tabella FAI_DISPONIBILITA_TEMP (2021.07.26)';
--------------------------------------------------------
--  DDL for Table FAI_DISPONIBILITA_RES_TEMP
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_DISPONIBILITA_RES_TEMP" 
   (	"OID" NUMBER(*,0), 
	"QUANTITA_DISPONIBILE" NUMBER(*,0), 
	"OID_DISPONIBILITAREQTEMP" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_RES_TEMP"."OID" IS 'Identificativo univoco della tupla (2021.07.26)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_RES_TEMP"."QUANTITA_DISPONIBILE" IS 'fare riferimento alla documentazione della tabella (2021.07.26)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_RES_TEMP"."OID_DISPONIBILITAREQTEMP" IS 'foreign key verso FAI_DISPONIBILITA_REQ_TEMP (2021.07.26)';
   COMMENT ON TABLE "FAI"."FAI_DISPONIBILITA_RES_TEMP"  IS 'fare riferimento alla documentazione della tabella FAI_DISPONIBILITA_TEMP (2021.07.26)';
--------------------------------------------------------
--  DDL for Table FAI_DISPONIBILITA_TEMP
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_DISPONIBILITA_TEMP" 
   (	"OID" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"OID_MAGAZZINO" NUMBER(*,0), 
	"REQUEST_TS" DATE DEFAULT SYSDATE, 
	"REQUEST_RAW_DATA" CLOB, 
	"RESPONSE_TS" DATE, 
	"RESPONSE_RAW_DATA" CLOB, 
	"STATUS_DESCR" CLOB, 
	"STATUS_TECH_DESCR" CLOB, 
	"STATUS_UPDATED_TS" DATE, 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"OID_STATUS" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("REQUEST_RAW_DATA") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("RESPONSE_RAW_DATA") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("STATUS_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("STATUS_TECH_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."OID" IS 'Identificativo univoco della tupla (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."OID_FORNITORE" IS 'fare riferimento alla documentazione della tabella (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."OID_MAGAZZINO" IS 'fare riferimento alla documentazione della tabella (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."REQUEST_TS" IS 'fare riferimento alla documentazione della tabella (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."REQUEST_RAW_DATA" IS 'fare riferimento alla documentazione della tabella (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."RESPONSE_TS" IS 'fare riferimento alla documentazione della tabella (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."RESPONSE_RAW_DATA" IS 'fare riferimento alla documentazione della tabella (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."STATUS_DESCR" IS 'v. quanto documentato per il campo OID_STATUS (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."STATUS_TECH_DESCR" IS 'v. quanto documentato per il campo OID_STATUS (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."STATUS_UPDATED_TS" IS 'v. quanto documentato per il campo OID_STATUS (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."CREATION_TS" IS 'data/ora di creazione del record, anche se di fatto superflua, da momento che il record DEVE essere creato contestualmente alla richiesta: CREATION_TS è sensibilmente diverso da REQUEST_TS? Qualcosa non va (2021.07.16)';
   COMMENT ON COLUMN "FAI"."FAI_DISPONIBILITA_TEMP"."OID_STATUS" IS 'Come per gli altri campi STATUS_*, vale quanto documentato per gli omonimi campi di FAI_ORDINE_IN. Tuttavia, in questo caso, OID_STATUS e STATUS_* sono stati aggiunti principalmente per tener traccia delle condizioni d''errore. Oltre lo Status "ERROR", pertanto, gli unici altri Status previsti al momento (2021.07.16) saranno "PROCESSING" (stato con ci si aspetta il record venga creato) e "PROCESSED" alla ricezione ed elaborazione, senza errori (sennò è "ERROR") della risposta (2021.07.16)';
   COMMENT ON TABLE "FAI"."FAI_DISPONIBILITA_TEMP"  IS 'Tiene traccia di una "Richiesta di Disponibilità" verso un FAI_MAGAZZINO o un FAI_FORNITORE, registrando sia la data/ora della richiesta (REQUEST_TS) che quella della risposta (RESPONSE_TS), nonché (sempre che sia possibile) il contenuto raw (XML, JSON, ...) della richiesta inolrata (REQUEST_RAW_DATA) e della risposta ottenuta (RESPONSE_RAW_DATA). Poiché, con la stessa richiesta, è possibile interrogare il Magazzino/Fornitore per più Prodotti/Farmaci, i relativi CODICE_MINSAN/CODICE_EAN e QUANTITA_RICHIESTA trovano posto nella FAI_DISPONIBILITA_REQ_TEMP. E poiché, per ogni QUANTITA_RICHIESTA (es., "7 aspririna"), il Magazzino/Fornitore può rispondere con più QUANTITA_DISPONIBILE (es., "7 non disponibile, ma disponibili 5 e 10"), ecco tali quantità sono registrate nella FAI_DISPONIBILITA_REQ_TEMP (2021.07.26)';
--------------------------------------------------------
--  DDL for Table FAI_EXPORT_CONFIG
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_EXPORT_CONFIG" 
   (	"FTP_PROTOCOL" VARCHAR2(5 BYTE), 
	"FTP_HOST" VARCHAR2(200 BYTE), 
	"FTP_PORT" NUMBER(*,0), 
	"FTP_DIR" VARCHAR2(200 BYTE), 
	"FTP_LOGIN" VARCHAR2(200 BYTE), 
	"FTP_PASSWORD" VARCHAR2(200 BYTE), 
	"FTP_PASSWORD_ENCR" CHAR(1 BYTE), 
	"EXPORT_TIME" DATE, 
	"CSV_FIELD_SEP" CHAR(1 BYTE), 
	"CSV_FIELD_SEP_REPLACEMENT" VARCHAR2(10 BYTE), 
	"CSV_HEADER_EXPORT_ENABLED" CHAR(1 BYTE), 
	"CSV_LINE_SEP" VARCHAR2(2 BYTE), 
	"LAST_RUN_START_TS" DATE, 
	"LAST_RUN_END_TS" DATE, 
	"LAST_RUN_DONE" CHAR(1 BYTE), 
	"LAST_RUN_DESCR" VARCHAR2(2048 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."FTP_PROTOCOL" IS 'Protocollo FTP da utilizzare. Insieme ad FTP_HOST ed FTP_PORT definisce il Server ftp destinatario del file CSV di ouput. I valori ammessi sono ''FTP'', ''FTPS'' ("FTP over SSL") ed ''SFTP'' (noto anche come "SSH File Transfer Protocol" oppure "Secure File Transfer Protocol"). E'' ammesso anche ''LOCAL '': in tal caso solo FTP_DIR ed FTP_FILENAME sono presi in considerazione e rappresenteranno un path locale (es., ''C:\DEBUG'' e ''testftp.csv'')  (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."FTP_HOST" IS 'Es: FTP_HOST = ''192.168.21.1''. V. anche  documentazione al campo FTP_PROTOCOL (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."FTP_PORT" IS 'Es: FTP_HOST = 21. V. anche  documentazione al campo FTP_PROTOCOL (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."FTP_DIR" IS 'Directory di destinazione del file di output(FTP_FILENAME); es.: FTP_DIR = ''/farmadati/out'' (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."FTP_LOGIN" IS 'Login per l''accesso al Server FTP di output(2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."FTP_PASSWORD" IS 'Password per l''accesso al Server FTP di output. Tale password sarà da considerarsi cifrata con gli strumenti it.swdes se FTP_PASSWORD_ENCR = ''S'', altrimenti (se FTP_PASSWORD_ENCR = ''N'') sarà da considerarsi in chiaro (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."FTP_PASSWORD_ENCR" IS 'v. quanto documentato per il campo FTP_PASSWORD (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."EXPORT_TIME" IS 'Orario di export.La data è da considerarsi meaningless e convenzionalmente fissata al 1 gennario 1970 (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."CSV_FIELD_SEP" IS 'Separatore di campo per il CSV esportato (2021.05.30)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."CSV_FIELD_SEP_REPLACEMENT" IS 'Se NOT NULL, indica che l''occorrenza del CSV_FIELD_SEP in uno qualsiasi dei campi esportati va sostituita col carattere così indicato. Per esempio, se CSV_FIELD_SEP="," e CSV_FIELD_SEP_REPLACEMENT="*", "aspirina, 20 compresse" viene sostituito nell''export con "aspirina* 20 compresse" (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."CSV_HEADER_EXPORT_ENABLED" IS 'Se ''S'' indica che la prima riga esportata sarà rappresentata dalle intestazioni, ossia i nomi di colonna (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."CSV_LINE_SEP" IS 'Separatore di riga. Tipicamente, CHR(13)||CHR(10) oppure CHR(13) oppure CHR(10)  (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."LAST_RUN_START_TS" IS 'data/ora di avvio dell''ultima operazione periodica di export dei dati. NULL se il processo non è mai stato eseguito (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."LAST_RUN_END_TS" IS 'data/ora di completamento dell''ultima operazione periodica di export dei datii. NULL se il processo non è mai stato eseguito o se l''export è in corso al momento della lettura del campo (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."LAST_RUN_DONE" IS 'Se ''S'', l''ultimo export dei dati si è concluso con successo, altrimetni ''N'' (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_EXPORT_CONFIG"."LAST_RUN_DESCR" IS 'Fornisce informazioni circa l''ultima esecuzione (2021.05.28)';
   COMMENT ON TABLE "FAI"."FAI_EXPORT_CONFIG"  IS 'Configurazione. Prevede un solo record (2021.05.18)';
--------------------------------------------------------
--  DDL for Table FAI_FARMACLICK_FORNITORE
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FARMACLICK_FORNITORE" 
   (	"OID" NUMBER(*,0), 
	"CODICE" VARCHAR2(100 BYTE), 
	"CODICE_SITO_LOGISTICO" VARCHAR2(100 BYTE), 
	"DESCRIZIONE_BREVE" VARCHAR2(200 BYTE), 
	"LAST_CSV" BLOB, 
	"LAST_CSV_FILENAME" VARCHAR2(100 BYTE), 
	"LAST_CSV_DOWNLOAD_URL" VARCHAR2(500 BYTE), 
	"LAST_CSV_CONFIRM_URL" VARCHAR2(500 BYTE), 
	"LAST_CSV_ZIPPED" CHAR(1 BYTE), 
	"NO_LONGER_EXISTS_TS" DATE, 
	"ALL_DATA_STORED_TS" DATE, 
	"LAST_CSV_CONFIRMED_AT_TS" DATE, 
	"LAST_CSV_DOWNLOAD_AT_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("LAST_CSV") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."OID" IS 'Identificativo univoco della tupla (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."CODICE" IS 'Corrisponde all''omonimo campo di "/LoginOutputBean/arrayFornitori/arrayFornitori" della risposta a "FCKLogin" (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."CODICE_SITO_LOGISTICO" IS 'Corrisponde all''omonimo campo di "/LoginOutputBean/arrayFornitori/arrayFornitori" della risposta a "FCKLogin" (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."DESCRIZIONE_BREVE" IS 'Corrisponde all''omonimo campo di "/LoginOutputBean/arrayFornitori/arrayFornitori" della risposta a "FCKLogin" (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."LAST_CSV" IS 'Se valorizzato, contenuto binario del CSV scaricato (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."LAST_CSV_FILENAME" IS 'Nome del CSV, se disponibile (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."LAST_CSV_DOWNLOAD_URL" IS 'url dal quale il CSV è stato scaricato (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."LAST_CSV_CONFIRM_URL" IS 'url di conferma del download (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."LAST_CSV_ZIPPED" IS 'Se ''S'', il contenuto di LAST_CSV è da conisderarsi compresso, altrimenti ''N'' (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."NO_LONGER_EXISTS_TS" IS 'Se NOT NULL indica la data/ora a partire dalla quale non si ha più riscontro del FORNITORE. Potrebbe risultare NOT NULL quando l''operazione di interrogazione dei Web Service è in corso (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."ALL_DATA_STORED_TS" IS 'Se NOT NULL indica la data/ora di completamento della registrazione in banca dati dei dati scaricati (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."LAST_CSV_CONFIRMED_AT_TS" IS 'Se NOT NULL, indica la data/ora alla quale il download è stato confermato per mezzo dell''apposito link (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_FARMACLICK_FORNITORE"."LAST_CSV_DOWNLOAD_AT_TS" IS 'Se NOT NULL indica la data/ora di inizio del download dall''url dedicato (2021.05.11)';
   COMMENT ON TABLE "FAI"."FAI_FARMACLICK_FORNITORE"  IS 'Ogni singolo record corrisponde ad un elemento "/LoginOutputBean/arrayFornitori/arrayFornitori" della risposta al servizio "FCKLogin" (2021.05.12)';
--------------------------------------------------------
--  DDL for Table FAI_FARMACO
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FARMACO" 
   (	"OID" NUMBER(*,0), 
	"IS_DELETED" CHAR(1 BYTE), 
	"CODICE_MINSAN" VARCHAR2(9 BYTE), 
	"CODICE_EAN" VARCHAR2(13 BYTE), 
	"DESCR_PROD" VARCHAR2(30 BYTE), 
	"ANAGRAFICA" VARCHAR2(58 BYTE), 
	"DESCR_TIPO_PROD" VARCHAR2(30 BYTE), 
	"DESCR_ESTESA_TIPO_PROD" VARCHAR2(120 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_FARMACO"."IS_DELETED" IS 'normalmente ''N'', se ''S'' indica che la riga è cancellata (2021.09.29)';
--------------------------------------------------------
--  DDL for Table FAI_FARMADATI_DDL
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FARMADATI_DDL" 
   (	"OID" NUMBER(*,0), 
	"TABLE_NAME" VARCHAR2(30 BYTE), 
	"SQL_CREATE" CLOB, 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"NO_LONGER_EXISTS_TS" DATE, 
	"ALL_DATA_LOADED_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("SQL_CREATE") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_DDL"."CREATION_TS" IS 'data/ora di creazione del record (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_DDL"."NO_LONGER_EXISTS_TS" IS 'Se NOT NULL indica la data/ora alla quale è stato determinato che la tabella non è più esistente. In fase di progetto, si ipotizza di impostare a SYSDATE questo campo ad ogni sessione di acquisizione, per poi impostarlo a NULL se si ha ancora riscontro della tabella (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_DDL"."ALL_DATA_LOADED_TS" IS 'Impostato a NULL all''avvio della sessione di acquisizione (o alla creazione del record), se NOT NULL indica la data/ora in corrispondenza della quale la tabella è stata completamente popolata (2021.04.29)';
   COMMENT ON TABLE "FAI"."FAI_FARMADATI_DDL"  IS 'Serve a mantenere copia del DDL dinamicamente generato per la creazione delle tabelle ospitanti i dati restituiti da Farmadati. Ciò consente di dedurre rapidamente se una tabella, magari esistente, è nel frattempo cambiata (semplicemente confrontando l''SQL qui conservato con l''SQL che sarebbe necessario eseguire per ri-crearla)  imponendo la DROP della stessa prima del ri-popolamento, anziché la semplice DELETE (2021.04.27)';
--------------------------------------------------------
--  DDL for Table FAI_FARMADATI_TABLE_TO_IGNORE
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FARMADATI_TABLE_TO_IGNORE" 
   (	"TABLE_NAME" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_TABLE_TO_IGNORE"."TABLE_NAME" IS 'Ie tabelle elencate nella presente, NON saranno mai scaricate.Il campo TABLE_NAME coincide con l''omonimo campo di FARMADATI_DDL (2021.05.03)';
--------------------------------------------------------
--  DDL for Table FAI_FARMADATI_WS
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FARMADATI_WS" 
   (	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"RESULT_XML" CLOB, 
	"SERVICE_NAME" VARCHAR2(20 BYTE), 
	"SERVICE_PARAMS" VARCHAR2(200 BYTE), 
	"OID" NUMBER(*,0), 
	"RESULT_SUCCESS" CHAR(1 BYTE), 
	"RESULT_DESCR" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("RESULT_XML") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS"."CREATION_TS" IS 'data/ora di creazione del record (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS"."RESULT_XML" IS 'XML restituto dal WebService (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS"."SERVICE_NAME" IS 'nome del servizio invocato, es. "GetEnabledDataSet", "GetSchemaDataSet", ecc... (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS"."SERVICE_PARAMS" IS 'Normalmente NULL, può contenere informazioni sulla valoriazzazione dei parametri utilizzati per quei servizi che li prevedono (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS"."OID" IS 'Identificativo univoco della tupla (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS"."RESULT_SUCCESS" IS 'Se ''S'' l''interrgoazione ha avuto successo, altrimenti ''N''. (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS"."RESULT_DESCR" IS 'ulteriori informazioni circa l''esito dell''interrogazione. Utile soprattutto se RESULT_SUCCESS=''N''. (2021.04.27)';
   COMMENT ON TABLE "FAI"."FAI_FARMADATI_WS"  IS 'Mantiene, per ogni servizio invocato, copia dell''XML ricevuto come risposta (2021.04.27)';
--------------------------------------------------------
--  DDL for Table FAI_FARMADATI_WS_CONFIG
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" 
   (	"WS_URL" VARCHAR2(500 BYTE), 
	"WS_LOGIN" VARCHAR2(200 BYTE), 
	"WS_PASSWORD" VARCHAR2(200 BYTE), 
	"WS_QUERY_INTERVAL" NUMBER(*,0), 
	"WS_QUERY_TIME" DATE, 
	"WS_PASSWORD_ENCR" CHAR(1 BYTE) DEFAULT 'N', 
	"DEBUG_MODE" VARCHAR2(20 BYTE), 
	"RESUME_PREV_SESSION_MODE" CHAR(1 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_CONFIG"."WS_URL" IS 'Url del WebSevice Farmadati. Al 2021.04.26, tale url è https://webservices.farmadati.it/WS2S/FarmadatiItaliaWebServicesM2.svc (2021.04.26)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_CONFIG"."WS_LOGIN" IS 'Login per l''accesso al WebService Farmadati(2021.04.26)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_CONFIG"."WS_PASSWORD" IS 'Password per l''accesso al WebService Farmadati. Tale password sarà da considerarsi cifrata con gli strumenti it.swdes se WS_PASSWORD_ENCR = ''S'', altrimenti (se WS_PASSWORD_ENCR = ''N'') sarà da considerarsi in chiaro (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_CONFIG"."WS_QUERY_INTERVAL" IS 'Espresso in minuti, rappresenta l''intervallo che deve intercorrere tra una esecuzione del processo (interrogazione WebService Farmadati, ecc..) e la successiva. La prima esecuzione avverrà all''ora specificata in WS_QUERY_TIME che, all''avvio della pianificazione, sarà sempre considerata ricadente nel futuro (2021.04.30)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_CONFIG"."WS_QUERY_TIME" IS 'Fare riferimento a quanto documentato per WS_QUERY_INTERVAL (2021.04.30)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_CONFIG"."WS_PASSWORD_ENCR" IS 'v. quanto documentato per il campo WS_PASSWORD (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_CONFIG"."DEBUG_MODE" IS 'DEVE normalmente essere impostata ad ''N''. La modalità debug è concepita per ridurre il tempo impegnato dalle sessioni di test, ad esempio caricando solo un ridotto numero di record per tabella (anziché centinaia di migliaia o milioni). La modalità debug va attivata impostando il campo ad ''S'' e TASSATIVAMENTE NON VA UTILIZZATA IN PRODUZIONE (2021.04.29)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_CONFIG"."RESUME_PREV_SESSION_MODE" IS 'DEVE normalmente essere impostata ad ''N''. Se impostato ad ''S'', tutti i dati già disponibili in banca dati, non saranno nuovamente scaricati. Più precisamente, non saranno invocati i WebService per i quali sono già state registrate le risposte in FARMADATI_WS e non saranno ripopolate le tabelle dinamicamente generate, per le quali, nella FARMADATI_DDL, esiste il corrispondente record ed il suo campo ALL_DATA_LOADED risulta essere NOT NULL (2021.04.30)';
   COMMENT ON TABLE "FAI"."FAI_FARMADATI_WS_CONFIG"  IS 'Raccoglie tutti i parametri di configurazione del sistema (2021.04.26)';
--------------------------------------------------------
--  DDL for Table FAI_FARMADATI_WS_STATUS
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FARMADATI_WS_STATUS" 
   (	"LAST_RUN_START_TS" DATE, 
	"LAST_RUN_END_TS" DATE, 
	"LAST_RUN_DONE" CHAR(1 BYTE), 
	"LAST_RUN_DESCR" VARCHAR2(2000 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_STATUS"."LAST_RUN_START_TS" IS 'data/ora di avvio dell''ultima operazione periodica di import da FarmaDati. NULL se il processo non è mai stato eseguito (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_STATUS"."LAST_RUN_END_TS" IS 'data/ora di completamento dell''ultima operazione periodica di import da FarmaDati. NULL se il processo non è mai stato eseguito o se l''import è in corso al momento della lettura del campo  (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_STATUS"."LAST_RUN_DONE" IS 'Se ''S'', l''ultimo import dei dati si è concluso con successo, altrimetni ''N'' (2021.04.27)';
   COMMENT ON COLUMN "FAI"."FAI_FARMADATI_WS_STATUS"."LAST_RUN_DESCR" IS 'Fornisce informazioni circa l''ultima esecuzione (2021.04.28)';
   COMMENT ON TABLE "FAI"."FAI_FARMADATI_WS_STATUS"  IS 'Mantiene lo stato di acquisizione dei dati dal Servivizio Farmadati  (2021.04.27)';
--------------------------------------------------------
--  DDL for Table FAI_FORNITORE
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FORNITORE" 
   (	"OID" NUMBER(*,0), 
	"CODICE" VARCHAR2(100 BYTE), 
	"CODICE_FARMACLICK" VARCHAR2(20 BYTE), 
	"SUPPLIERSERVICE_CLASS_NAME" VARCHAR2(500 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_FORNITORE"."OID" IS 'Identificativo univoco della tupla (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE"."CODICE" IS 'Codice identificativo del Fornitore  (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE"."CODICE_FARMACLICK" IS 'Codice identificativo del fornitore in Farmaclick, se presente in tale fonte. Questo campo, che forse troverebbe miglior collocazione in una tabella di transcodifica, viene invece posto qui per brevità (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE"."SUPPLIERSERVICE_CLASS_NAME" IS 'nome della classe implementante l''interfaccia fai.broker.supplier.SupplierService  che consente di interagire coi sistemi del Fornitore. Il campo è NULLable al solo scopo di consentire il popolamento della banca dati per non impedire tutte le operazioni che è possibile effettuare sulla stessa senza interagire coi sistemi del Fornitore. Ovviamente, se questo campo è NULL, qualsiasi tentativo di interazione coi sistemi del Fornitore, risulterà impossibile (2021.07.27)';
--------------------------------------------------------
--  DDL for Table FAI_FORNITORE_FARMACO
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FORNITORE_FARMACO" 
   (	"OID" NUMBER(*,0), 
	"CODICE_MINSAN" VARCHAR2(9 BYTE), 
	"LINE_ID" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"CREATION_TS" DATE DEFAULT SYSDATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_FORNITORE_FARMACO"."OID" IS 'Identificativo univoco della tupla (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE_FARMACO"."CODICE_MINSAN" IS 'Codice MinSan Identificativo del Farmaco (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE_FARMACO"."LINE_ID" IS 'E'' una "sequence senza gap", 1-based, che parte dal valore 1 per ogni singolo fornitore (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE_FARMACO"."OID_FORNITORE" IS 'foreign key verso FAI_FORNITORE (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE_FARMACO"."CREATION_TS" IS 'data/ora di creazione del record (2021.06.18)';
   COMMENT ON TABLE "FAI"."FAI_FORNITORE_FARMACO"  IS 'defnisce il LINE_ID che identifica il farmaco individuato dal codice MINSAN per il FAI_FORNITORE referenziato (2021.06.10)';
--------------------------------------------------------
--  DDL for Table FAI_FORNITORE_PROPERTY
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_FORNITORE_PROPERTY" 
   (	"OID" NUMBER(*,0), 
	"KEY" VARCHAR2(100 BYTE), 
	"VALUE" CLOB, 
	"OID_FORNITORE" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("VALUE") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_FORNITORE_PROPERTY"."KEY" IS 'v. documentazione alla tabella (2021.07.27)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE_PROPERTY"."VALUE" IS 'v. documentazione alla tabella (2021.07.27)';
   COMMENT ON COLUMN "FAI"."FAI_FORNITORE_PROPERTY"."OID_FORNITORE" IS 'foreign key verso FAI_FORNITORE (2021.07.27)';
   COMMENT ON TABLE "FAI"."FAI_FORNITORE_PROPERTY"  IS 'rappresenta una proprieta'' del FAI_FORNITORE referenziato espressa come coppia chiave/valore, dove la chiave è costitutita dal campo KEY ed il valore dal campo VALUE. Tali proprietà saranno essenzialmente utilizzate dalla classe specificata dal campo  SUPPLIERSERVICE_CLASS_NAME del FAI_FORNITORE (2021.07.27)';
--------------------------------------------------------
--  DDL for Table FAI_GENERIC_TASK
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_GENERIC_TASK" 
   (	"OID" NUMBER(*,0), 
	"ACRONYM" VARCHAR2(50 BYTE), 
	"DESCR" VARCHAR2(200 BYTE), 
	"CLASS_NAME" VARCHAR2(500 BYTE), 
	"SCHEDULED_TIMES" VARCHAR2(100 BYTE), 
	"SCHEDULED_SMTWTFS" CHAR(7 BYTE), 
	"LAST_RUN_START_TS" DATE, 
	"LAST_RUN_END_TS" DATE, 
	"LAST_RUN_DONE" CHAR(1 BYTE), 
	"LAST_RUN_DESCR" CLOB, 
	"ENABLED" CHAR(1 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("LAST_RUN_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."OID" IS 'identificativo univoco della tupla (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."ACRONYM" IS 'mnemonico identificativo univoco della tupla (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."DESCR" IS 'descrizione facoltativa (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."CLASS_NAME" IS 'classe implementante il task (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."SCHEDULED_TIMES" IS 'elenco di orari di attivazione del task - formato HH:mm con virgola come separatore - , a patto che venga soddisfatta anche la condizione configurata in SCHEDULED_SMTWTFS. Può essere NULL (v. documentazione alla tabella)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."SCHEDULED_SMTWTFS" IS 'pattern di attivazione settimanale (Sunday, Monday, ...). Il task verrà eseguito a patto che per il corrispondente giorno della settimana sia specificato "X" invece che "-". Può essere NULL (v. documentazione alla tabella) (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."LAST_RUN_START_TS" IS 'ultima data/ora di avvio del taski. NULL se il task non è mai stato eseguito (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."LAST_RUN_END_TS" IS 'ultima data/ora di completamento del taski. NULL se il task non è mai stato eseguito o se la sua esecuzione è in corso al momento della lettura del campo (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."LAST_RUN_DONE" IS '''S'', se l''ultima attività del task si è conclusa con sucesso, altrimetni ''N''  (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."LAST_RUN_DESCR" IS 'fornisce informazioni circa l''ultima esecuzione del task (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK"."ENABLED" IS 'normalmente ''S'', se ''N'' consente di disabilitare il task';
   COMMENT ON TABLE "FAI"."FAI_GENERIC_TASK"  IS 'Rappresenta un generico "task". I campi SCHEDULED_TIMES e SCHEDULED_SMTWTFS risulteranno entrambi valorizzati, se il task va schedulato, o entrambi NULL se il task è invocato da un task principale. Ad esempio, esisterà plausibilmente un task che importa "web_order.csv", un''altro che si occupa di reperire i farmaci dai magazzini oppure ordinarli dai fornitori e così via. Esisterà un solo task con SCHEDULED_TIMES e SCHEDULED_SMTWTFS valorizzati che esegue in sequenza quelli citati i quali, necessariamente, avranno SCHEDULED_TIMES e SCHEDULED_SMTWTFS  posti a NULL (2021.06.18)';
--------------------------------------------------------
--  DDL for Table FAI_GENERIC_TASK_PROPERTY
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY" 
   (	"OID" NUMBER(*,0), 
	"KEY" VARCHAR2(100 BYTE), 
	"VALUE" CLOB, 
	"OID_GENERICTASK" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("VALUE") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK_PROPERTY"."OID" IS 'Identificativo univoco della tupla (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK_PROPERTY"."KEY" IS 'v. documentazione alla tabella (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK_PROPERTY"."VALUE" IS 'v. documentazione alla tabella (2021.06.18)';
   COMMENT ON COLUMN "FAI"."FAI_GENERIC_TASK_PROPERTY"."OID_GENERICTASK" IS 'foreign key verso FAI_GENERIC_TASK  (2021.06.18)';
   COMMENT ON TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY"  IS 'rappresenta una proprieta'' del FAI_GENERIC_TASK referenziato espressa come coppia chiave/valore, dove la chiave è costitutita dal campo KEY ed il valore dal campo VALUE (2021.06.18)';
--------------------------------------------------------
--  DDL for Table FAI_GROUP_EXPORT
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_GROUP_EXPORT" 
   (	"OID" NUMBER(*,0), 
	"GROUP_IDX" NUMBER(*,0), 
	"GROUP_NAME_EXPORT" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_GROUP_EXPORT"."OID" IS 'Identificativo univoco della tupla (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_GROUP_EXPORT"."GROUP_IDX" IS 'inseme a TABLE_EXPORT.TABLE_IDX  consente di statabilire l''ordine con cui i dati delle tabelle sono importati in questo schema ed esportarti verso il sistema target (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_GROUP_EXPORT"."GROUP_NAME_EXPORT" IS 'v. quanto documentato per la tabella (2021.05.18)';
   COMMENT ON TABLE "FAI"."FAI_GROUP_EXPORT"  IS 'Rappresenta un raggruppamento. FAI_TABLE_EXPORT con lo stesso FAI_GROUP_EXPORT saranno considerate parte dello stesso raggruppamento. Al momento (2021.05.18) il valore attribuito a questo campo GROUP_NAME_EXPORT (es., "PoImportBlanketAgreements") corrisponderà al nome dello zip (es., "PoImportBlanketAgreements.zip") che conterrà tutte le FAI_TABLE_EXPORT dello stesso gruppo (2021.05.18)';
--------------------------------------------------------
--  DDL for Table FAI_IMPORT_CONFIG
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_IMPORT_CONFIG" 
   (	"OID" NUMBER(*,0), 
	"ACRONYM" VARCHAR2(50 BYTE), 
	"DESCR" VARCHAR2(200 BYTE), 
	"SERVICE_LOGIN" VARCHAR2(200 BYTE), 
	"SERVICE_PASS" VARCHAR2(200 BYTE), 
	"SERVICE_PASS_ENCR" CHAR(1 BYTE), 
	"SERVICE_QUERY_TIME" DATE, 
	"SERVICE_QUERY_SMTWTFS" CHAR(7 BYTE) DEFAULT 'CCCCCCC', 
	"SERVICE_QUERY_URL" VARCHAR2(2048 BYTE), 
	"SERVICE_RESUME_PREV_SESS" CHAR(1 BYTE) DEFAULT 'N', 
	"LAST_RUN_START_TS" DATE, 
	"LAST_RUN_END_TS" DATE, 
	"LAST_RUN_TYPE" CHAR(1 BYTE), 
	"LAST_RUN_DONE" CHAR(1 BYTE), 
	"LAST_RUN_DESCR" VARCHAR2(2048 BYTE), 
	"CLASS_NAME" VARCHAR2(500 BYTE), 
	"SERVICE_QUERY_ZIPPED_CONTENT" CHAR(1 BYTE) DEFAULT 'N'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."OID" IS 'identificativo univoco della tupla (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."ACRONYM" IS 'mnemonico identificativo del provider. Es., "FARMADATI", "FARMACLICK" (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."DESCR" IS 'descrizione facoltativa (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."SERVICE_LOGIN" IS 'login, se prevista dal provider (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."SERVICE_PASS" IS 'password, se prevista dal provider. Tale password sara'' da considerarsi cifrata con gli strumenti it.swdes se SERVICE_PASS_ENCR = ''S'', altrimenti (se SERVICE_PASS_ENCR = ''N'') sara'' da considerarsi in chiaro  (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."SERVICE_PASS_ENCR" IS 'v. quanto documentato per il campo SERVICE_PASS  (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."SERVICE_QUERY_TIME" IS 'Orario di interrogazione del servizio e, quindi, di scaricamento/import dei dati ed aggiornamento della banca dati. Più precisamente, ogni giorno, all''orario specificato da questo parametro, avrà luogo l''operazione specificata dal pattern settimanale SERVICE_QUERY_SMTWTFS cui si rimanda. La data è da considerarsi meaningless e convenzionalmente fissata al 1 gennario 1970  (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."SERVICE_QUERY_SMTWTFS" IS 'Pattern settimanale costituito da una stringa di 7 caratteri ove ognuno di essi può essere "C" per il download integerale dei dati, "V" per le sole variazioni (se supportato), "-" per nessuna operazione (in questo mdoo è possibile avere uno più giorni della settimana in corripondenza dei quali all''orario SERVICE_QUERY_TIME non accade assolutamente nulla). Come richiamato dal nome del campo, il primo giorno della settimana è "Sunday" (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."SERVICE_QUERY_URL" IS 'url principale del servizio, se previsto dal provider (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."SERVICE_RESUME_PREV_SESS" IS 'Se ''S'' ("Si", "true") le interrogazioni già completate con successo non saranno di nuovo ripetute. Di norma si utilizzerà questo valore solo per scopi di debug in quanto, una volta scaricati/importati i dati, questi non sarebbero mai più scaricati di nuovo. Se ''A'' ("Auto") si otterrà esattametne lo stesso comportamento di ''S'' se e solo se il download precedente non è terminato con successo e si tratta dello stesso  tipo di download ("C" o "V" ). Se ''N'', il download riprenderà da capo ad ogni sessione (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."LAST_RUN_START_TS" IS 'data/ora di avvio dell''ultima operazione periodica di import. NULL se il processo non e'' mai stato eseguito (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."LAST_RUN_END_TS" IS 'data/ora di avvio dell''ultima operazione periodica di import. NULL se il processo non e'' mai stato eseguito (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."LAST_RUN_TYPE" IS 'Tipologia dell''ultimo import, determinata dal pattern settimanale SERVICE_QUERY_SMTWTFS. Può dunque essere "C" opprue "V" o NULL se il processo non è mai stato avviato prima (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."LAST_RUN_DONE" IS ' ''S'' se l''ultimo import dei dati si e'' concluso con successo, altrimetni ''N'' (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."LAST_RUN_DESCR" IS 'Fornisce informazioni circa l''ultima esecuzione  (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."CLASS_NAME" IS 'classe implementante lo specifico provider(2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_IMPORT_CONFIG"."SERVICE_QUERY_ZIPPED_CONTENT" IS 'se ''S'', e se la funzionalità è supportata dal servizio, i dati saranno scaricato in formato compresso (tipicamente, zip) (2021.05.18)';
   COMMENT ON TABLE "FAI"."FAI_IMPORT_CONFIG"  IS 'configurazione e stato del generico Provider di Import dei dati. Al momento (2021.05.18) sono già stati implementati i Provider "FARMADATI" e  "FARMACLICK", ma ne sono previsti altri. Sia ''ACRONYM che la CLASS_NAME sono unique. I parametri (SERVICE_*) coprono l''esigenza della maggior parte (forse tutti) i sistemi terzi a cui attingere. Qualora dovesse rendersi necessaria la configurazione di parametri peculiarissimi, sarà sufficiente introdurre una tabella FAI_IMPORT_PROVIDER_PROPERTY con KEY/VALUE referenziante la presente (2021.05.18)';
--------------------------------------------------------
--  DDL for Table FAI_ITEM_STATUS
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_ITEM_STATUS" 
   (	"OID" NUMBER(*,0), 
	"ACRONYM" VARCHAR2(20 BYTE), 
	"DESCR" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_ITEM_STATUS"."OID" IS 'idenrtificativo univoco della tupla (2016.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_ITEM_STATUS"."ACRONYM" IS 'Acronimo univoco della tupla (2016.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_ITEM_STATUS"."DESCR" IS 'Descrizione (2016.06.16)';
   COMMENT ON TABLE "FAI"."FAI_ITEM_STATUS"  IS 'Consente di contrassegnare lo stato di un oggetto. Ad esempio, un ORDINE_IN appena creato sarà in stato "TO PROCESS". Gli stati previsti, al momento, sono "TO PROCESS", "PROCESSING", "PROCESSED",  "ERROR", "SUSPENDED", a seconda che il record sia, rispettivamente, in attesa di essere elaborato, in corso di elaborazione, l''elaborazione sia stata completata con successo, in errore (ciò determina la sospensione dell''intero processo finché il problema non viene risolto da un operatore), sospeso da parte di un operatore (il processo potrà continuare operando sugli elementi che non sono impattati dall''elemento sospeso) (2021.06.18) Sono poi stati aggiunti gli stati "TO APPROVE", ad indicare un record che richiede l''approvazione da parte di un Utente, "APPROVED", dall''ovvio significato, e "VOIDED", simile a "SUSPENDED", ma assegnato da un Utente (2021.06.30)';
--------------------------------------------------------
--  DDL for Table FAI_LISTINI_DISPONIBILITA_TEMP
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_LISTINI_DISPONIBILITA_TEMP" 
   (	"OID_FORNITORE" NUMBER(*,0), 
	"CODICE_MINSAN" VARCHAR2(9 BYTE), 
	"CODICE_EAN" VARCHAR2(13 BYTE), 
	"PREZZO" NUMBER(22,4)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_LISTINI_DISPONIBILITA_TEMP"."OID_FORNITORE" IS 'foreign key verso FAI_FORNITORE. Dato il carattere di temporaneità del contenuto della tabella, non necessariamente risulteranno abilitati i vincoli di integrità referenziale.';
   COMMENT ON TABLE "FAI"."FAI_LISTINI_DISPONIBILITA_TEMP"  IS 'Viene popolata "al volo" nell''ambito del task di determinazione della disponibilità dei farmaci. Più precisamente è di ausilio all''individuazione dei Fornitori in grado di offrire l''offerta migliore per ognuno dei farmaci che è necessario reperire ed all''aggregazione delle offerte migliori per singolo Fornitore (in modo da poter poi interrogare ogni fornitore per più farmaci contemporaneamente, almeno dove l''API supporta tale possibilità). Mantenendo sempre un subset ridotto di tutti i Listini di tutti i Fornitori, e dotata di indici ad-hoc, garantisce, necessariamente, velocità di accesso in lettura e scrittura necessariamente superiori alle tabelle di origine (al momento, 2021.07.14, omogeneizzate dalla V_FAI_LISTINI_FORNITORI) (2021.07.14)';
--------------------------------------------------------
--  DDL for Table FAI_LOG
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_LOG" 
   (	"OID" NUMBER(*,0), 
	"LOG_LEVEL" CHAR(1 BYTE), 
	"TS" DATE DEFAULT SYSDATE, 
	"THREAD" VARCHAR2(20 BYTE), 
	"MESSAGE" CLOB, 
	"TECH_MESSAGE" CLOB, 
	"STACK_TRACE" CLOB
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("MESSAGE") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("TECH_MESSAGE") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("STACK_TRACE") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_LOG"."OID" IS 'Identificativo univoco della tupla (2021.07.07)';
   COMMENT ON COLUMN "FAI"."FAI_LOG"."LOG_LEVEL" IS 'Ammessi: "T" (Trace), "D" (Debug), "I" (Info), "W" (Warning), "E" (Error), "F" (Fatal). Di fatto, questa tabella conserverà, rispetto ad un log tradizionale, solo un sottinsieme ridotto di righe dal contenuo fruibile (almeno in gran parte) anche dall''utente finale del sistema (2021.07.07)';
   COMMENT ON COLUMN "FAI"."FAI_LOG"."TS" IS 'Data/ora di creazione del record. Per scorrere la tabella in modo cronologico, è opportuno che l''ordinamento (ORDER BY) sia per TS, OID, in quanto i) nulla vieta di avere più record allo stesso secondo e ii) l''OID è legato ad una sequence che si assume sempre crescente. Quand''anche la sequence venisse azzerata, l''ordinamento potrebbe fallire solo nel punto di azzeramento della sequence, ammesso che proprio in quel punto due record consecutivi siano attribuiti allo stesso secondo (2021.07.07)';
   COMMENT ON COLUMN "FAI"."FAI_LOG"."THREAD" IS 'E'' il thread che ha generato la riga di log (2021.07.07)';
   COMMENT ON COLUMN "FAI"."FAI_LOG"."MESSAGE" IS 'testo della "riga" di log (2021.07.07)';
   COMMENT ON COLUMN "FAI"."FAI_LOG"."TECH_MESSAGE" IS 'eventuali ulteriori informazioni di carattere tecnico (2021.07.07)';
   COMMENT ON COLUMN "FAI"."FAI_LOG"."STACK_TRACE" IS 'campo disponibile per l''eventuale stacktrace (2021.07.07)';
--------------------------------------------------------
--  DDL for Table FAI_MAGAZZINO
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_MAGAZZINO" 
   (	"OID" NUMBER(*,0), 
	"ACRONYM" VARCHAR2(50 BYTE), 
	"DESCR" VARCHAR2(100 BYTE), 
	"COMUNE" VARCHAR2(20 BYTE), 
	"PROVINCIA" VARCHAR2(2 BYTE), 
	"REGIONE" VARCHAR2(20 BYTE), 
	"CAP" VARCHAR2(10 BYTE), 
	"INDIRIZZO" VARCHAR2(100 BYTE), 
	"SUPPLIERSERVICE_CLASS_NAME" VARCHAR2(500 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."OID" IS 'identificativo univoco della tupla (2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."ACRONYM" IS 'acronimo univoco della tupla (2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."DESCR" IS 'descrizione (2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."COMUNE" IS '(2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."PROVINCIA" IS '(2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."REGIONE" IS '(2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."CAP" IS '(2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."INDIRIZZO" IS '(2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO"."SUPPLIERSERVICE_CLASS_NAME" IS 'nome della classe implementante l''interfaccia fai.broker.supplier.SupplierService  che consente di interagire coi sistemi del Magazzino. Il campo è NULLable al solo scopo di consentire il popolamento della banca dati per non impedire tutte le operazioni che è possibile effettuare sulla stessa senza interagire coi sistemi del Magazzino. Ovviamente, se questo campo è NULL, qualsiasi tentativo di interazione coi sistemi del Magazzino, risulterà impossibile (2021.07.27)';
   COMMENT ON TABLE "FAI"."FAI_MAGAZZINO"  IS 'Rappresenta uno dei magazzini anagrafati e gestiti, come Loreto o UPS (2021.06.16)';
--------------------------------------------------------
--  DDL for Table FAI_MAGAZZINO_PROPERTY
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_MAGAZZINO_PROPERTY" 
   (	"OID" NUMBER(*,0), 
	"KEY" VARCHAR2(100 BYTE), 
	"VALUE" CLOB, 
	"OID_MAGAZZINO" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("VALUE") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO_PROPERTY"."OID" IS 'identificativo univoco della tupla (2021.07.27)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO_PROPERTY"."KEY" IS 'v. documentazione alla tabella (2021.07.27)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO_PROPERTY"."VALUE" IS 'v. documentazione alla tabella (2021.07.27)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO_PROPERTY"."OID_MAGAZZINO" IS 'foreign key verso FAI_MAGAZZINO (2021.07.27)';
   COMMENT ON TABLE "FAI"."FAI_MAGAZZINO_PROPERTY"  IS 'rappresenta una proprieta'' del FAI_MAGAZZINO referenziato espressa come coppia chiave/valore, dove la chiave è costitutita dal campo KEY ed il valore dal campo VALUE. Tali proprietà saranno essenzialmente utilizzate dalla classe specificata dal campo  SUPPLIERSERVICE_CLASS_NAME del FAI_MAGAZZINO (2021.07.27)';
--------------------------------------------------------
--  DDL for Table FAI_MAGAZZINO_TIPOFARMACO
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_MAGAZZINO_TIPOFARMACO" 
   (	"OID" NUMBER(*,0), 
	"OID_MAGAZZINO" NUMBER(*,0), 
	"OID_TIPOFARMACO" NUMBER(*,0), 
	"CONTEXT" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO_TIPOFARMACO"."OID" IS 'Identificativo univoco della tupla (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO_TIPOFARMACO"."OID_MAGAZZINO" IS 'foreign key verso FAI_MAGAZZINO (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO_TIPOFARMACO"."OID_TIPOFARMACO" IS 'foreign key verso FAI_TIPO_FARMACO (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_MAGAZZINO_TIPOFARMACO"."CONTEXT" IS 'Contesto nel quale l''associazione è valida. Si è reso necessario in quanto - per esempio - UPS Casoria va considerato come Magazzino cui attingere solo ed esclusivamente Parafarmaci nel _contesto_ dell''evasione dei  FAI_ORDINE_IN*, mentre nel _cotesto_ del "Flusso Trade" e "Flusso Retail" del §5 dell''allegato "nota_fai_v6.docx" può essere usato sia per i Farmaci che per i Parafarmaci. Essendo impossible al momento determinare quanti e quali "contesti" possono esistere, il presente non può che essere un campo VARCHAR2 che recherà un testo identificativo del contesto man mano che nuovi contesti di utilizzo andranno a palesarsi (21.07.05)';
   COMMENT ON TABLE "FAI"."FAI_MAGAZZINO_TIPOFARMACO"  IS 'Associativa che consente di specificare, per ogni FAI_MAGAZZINO, quali FAI_TIPO_FARMACO sono supportati. Al momento (2016.06.16) sono previsti i soli due magazzini "Loreto", di Casoria, per i Farmaci, e "UPS", di Formello (Roma), per i Parafarmaci (2016.06.16)';
--------------------------------------------------------
--  DDL for Table FAI_NO_GAP_SEQUENCE
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_NO_GAP_SEQUENCE" 
   (	"OID" NUMBER(*,0), 
	"ACRONYM" VARCHAR2(30 BYTE), 
	"LAST_VALUE" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_NO_GAP_SEQUENCE"."OID" IS 'identificativo univoco della tupla (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_NO_GAP_SEQUENCE"."ACRONYM" IS 'v. documentazione alla tabella (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_NO_GAP_SEQUENCE"."LAST_VALUE" IS 'v. documentazione alla tabella (2021.06.10)';
   COMMENT ON TABLE "FAI"."FAI_NO_GAP_SEQUENCE"  IS 'Tabella di servizio per la generazione di "sequences senza gap". La funzione (procedure o metodo/classe Java) che opera sulla tabella, per un qualsiasi ACRONYM, incrementa il valore LAST_VALUE di una unità. Se il record per l''ACRONYM indicato non esiste, viene creato con LAST_VALUE = 1. Il valore di LAST_VALUE viene quindi restituito. Se tutte le operazioni nella transazione terminano con successo, la transazione viene confermata - commit - e LAST_VALUE  diventa consolidato. Se la transazione viene abortita - rollback - LAST_VALUE si troverà automaticamente impostato al valore precedente per efffetto della rollback (2021.06.10)';
--------------------------------------------------------
--  DDL for Table FAI_ORDINE_IN
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_ORDINE_IN" 
   (	"OID" NUMBER(*,0), 
	"ID_VENDITA" NUMBER(*,0), 
	"ID_UTENTE" NUMBER(*,0), 
	"NOME_COMPLETO_ACQUIRENTE" VARCHAR2(100 BYTE), 
	"NUMERO_TELEFONO_ACQUIRENTE" VARCHAR2(30 BYTE), 
	"EMAIL_ACQUIRENTE" VARCHAR2(50 BYTE), 
	"INDIRIZZO_ACQUIRENTE_1" VARCHAR2(100 BYTE), 
	"INDIRIZZO_ACQUIRENTE_2" VARCHAR2(100 BYTE), 
	"CITTA_ACQUIRENTE" VARCHAR2(50 BYTE), 
	"STATO_ACQUIRENTE" VARCHAR2(50 BYTE), 
	"CAP_ACQUIRENTE" VARCHAR2(10 BYTE), 
	"PAESE_ACQUIRENTE" VARCHAR2(50 BYTE), 
	"QUANTITA" NUMBER(*,0), 
	"SPEDIZIONE_E_IMBALLAGGIO" NUMBER(22,4), 
	"ASSICURAZIONE" NUMBER(22,4), 
	"TARIFFA_PER_CONTRASSEGNO" NUMBER(22,4), 
	"PREZZO_TOTALE" NUMBER(22,4), 
	"METODO_DI_PAGAMENTO" NUMBER(*,0), 
	"DATA_DI_VENDITA" DATE, 
	"DATA_DEL_MODULO_DI_PAGAMENTO" DATE, 
	"DATA_PAGAMENTO" DATE, 
	"DATA_SPEDIZIONE" DATE, 
	"DATA_FATTURA" DATE, 
	"NUMERO_FATTURA" VARCHAR2(30 BYTE), 
	"FEEDBACK_LASCIATO" CHAR(1 BYTE), 
	"FEEDBACK_RICEVUTO" CHAR(1 BYTE), 
	"MEMO_PER_USO_PERSONALE" VARCHAR2(50 BYTE), 
	"CAMPO_PRIVATO" NUMBER(*,0), 
	"ID_TRANSAZIONE_PAYPAL" VARCHAR2(30 BYTE), 
	"SERVIZIO_DI_SPEDIZIONE" NUMBER(*,0), 
	"OPZIONE_CONTRASSEGNO" CHAR(1 BYTE), 
	"ID_TRANSAZIONE" NUMBER(*,0), 
	"ID_ORDINE" NUMBER(*,0), 
	"ALIQUOTA_IVA_INCLUSA" NUMBER(*,0), 
	"DETTAGLI_VARIAZIONE" VARCHAR2(60 BYTE), 
	"AZIENDA_ACQUIRENTE" VARCHAR2(50 BYTE), 
	"NOME_COMPLETO_FATTURAZIONE" VARCHAR2(50 BYTE), 
	"AZIENDA_FATTURAZIONE" VARCHAR2(50 BYTE), 
	"CF_FATTURAZIONE" VARCHAR2(20 BYTE), 
	"PIVA_FATTURAZIONE" VARCHAR2(30 BYTE), 
	"INDIRIZZO_FATTURAZIONE" VARCHAR2(100 BYTE), 
	"CITTA_FATTURAZIONE" VARCHAR2(50 BYTE), 
	"STATO_FATTURAZIONE" VARCHAR2(50 BYTE), 
	"CAP_FATTURAZIONE" VARCHAR2(10 BYTE), 
	"PAESE_FATTURAZIONE" VARCHAR2(50 BYTE), 
	"CODICEFE" VARCHAR2(30 BYTE), 
	"PEC" VARCHAR2(30 BYTE), 
	"NOTE" CLOB, 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"OID_STATUS" NUMBER(*,0) DEFAULT 1, 
	"STATUS_DESCR" CLOB, 
	"STATUS_TECH_DESCR" CLOB, 
	"OID_ORDINEINCOLLECTION" NUMBER(*,0), 
	"STATUS_UPDATED_TS" DATE DEFAULT SYSDATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("NOTE") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("STATUS_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("STATUS_TECH_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."OID" IS 'Identificativo univoco della tupla (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."ID_VENDITA" IS 'ID vendita (numero ordine) = 34257 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."NOME_COMPLETO_ACQUIRENTE" IS 'Nome completo dell''acquirente = Maria Rossi (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."NUMERO_TELEFONO_ACQUIRENTE" IS 'Numero di telefono dell''acquirente = 3447989467 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."EMAIL_ACQUIRENTE" IS 'Email dell''acquirente = mrossi@gmail.com (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."INDIRIZZO_ACQUIRENTE_1" IS 'Indirizzo dell''acquirente 1 = Via Nazionale, 190 scala C (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."CITTA_ACQUIRENTE" IS 'Città dell''acquirente (città) = Sorrento (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."STATO_ACQUIRENTE" IS 'Stato dell''acquirente (provincia) = Na (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."CAP_ACQUIRENTE" IS 'CAP dell''acquirente (cap) = 80067 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."PAESE_ACQUIRENTE" IS 'Paese dell''acquirente (nazione) = Italia (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."QUANTITA" IS 'Quantità (pezzi totali) = 4 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."SPEDIZIONE_E_IMBALLAGGIO" IS 'Spedizione e imballaggio (spese di spedizione, è un di cui del prezzo totale) = EUR 6,90 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."PREZZO_TOTALE" IS 'Prezzo totale (prezzo di vendita totale) = EUR 61,98 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."METODO_DI_PAGAMENTO" IS 'Metodo di pagamento (Paypal = 1, Contrassegno = 2, CartaDiCredito = 3, BonificoBancario = 4) = 2 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."DATA_DI_VENDITA" IS 'Data di vendita (data dell''ordine) = 23-jul-14 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."FEEDBACK_LASCIATO" IS '''S'' oppure ''N'' (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."CAMPO_PRIVATO" IS 'Campo privato (1 se pagato, 0 se non pagato) = 1 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."SERVIZIO_DI_SPEDIZIONE" IS 'Servizio di spedizione (0 se Ritiro in farmacia, 1 se Spedito) = 1 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."ALIQUOTA_IVA_INCLUSA" IS 'Aliquota IVA inclusa = 0 se i valori indicati sono deivati (clienti esteri che non pagano iva), 1 oppure vuoto se i valori indicati sono ivati (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."NOME_COMPLETO_FATTURAZIONE" IS 'Nome completo fatturazione = ROSSI snc di Maria Rossi (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."CF_FATTURAZIONE" IS 'CF fatturazione (Codice Fiscale acquirente) = XXXXXX77E09XXXXY (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."PIVA_FATTURAZIONE" IS 'PIVA fatturazione (Partita iva acquirente) = (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."INDIRIZZO_FATTURAZIONE" IS 'Indirizzo fatturazione = Via Pertini, 90 Città (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."CITTA_FATTURAZIONE" IS 'Città fatturazione (città) = Sorrento (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."STATO_FATTURAZIONE" IS 'Stato fatturazione (provincia) = Napoli (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."CAP_FATTURAZIONE" IS 'CAP fatturazione (cap) = 80067 (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."PAESE_FATTURAZIONE" IS 'Paese fatturazione (nazione, 2 caratteri) = IT (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."CODICEFE" IS 'CodiceFE (Codice Destinatario Fattura Elettronica) (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."PEC" IS 'PEC (PEC destinatario Fattura Elettronica) (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."NOTE" IS 'Note (testo libero, ATTENZIONE non usare il carattere " all''interno del testo) (2021.06.09)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."CREATION_TS" IS 'data/ora di creazione del record (2021.06.14)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."OID_STATUS" IS 'foreign key verso FAI_ITEM_STATUS (2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."STATUS_DESCR" IS 'fornisce informazioni aggiuntive circa il FAI_ITEM_STATUS referenziato. Essenzialmente concepito per poter fornire ulteriori informazioni - fruibili all''utente finale - nel caso in cui lo scenario in cui lo stato rerenziato è "ERROR" (2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."STATUS_TECH_DESCR" IS 'analogo a STATUS_DESCR, ma, rispetto a tale campo, potrà contenere anche informazioni tecniche di più basso livello tecnico (codici d''errore HTTP, Stack Trace, ecc...), non necessariamente fruibili all''utente finale  (2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."OID_ORDINEINCOLLECTION" IS 'foreign key verso FAI_ORDINE_IN_COLLECTION (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN"."STATUS_UPDATED_TS" IS 'data/ora di aggiornamento dello status  (2021.06.16)';
   COMMENT ON TABLE "FAI"."FAI_ORDINE_IN"  IS 'Rappresenta le "Riga Testata" del file di Ordini documentato nel "ordercsv.pdf" reso disponibile al 2021.05.06 e corredato del file "web_order.csv" di esempio. La documentazione ai singoli campi della presente tabella è attinta (copia-incolla) dal "ordercsv.pdf". I campi non documentati, sono presenti in "web_order.csv", ma non citati in "ordercsv.pdf". Di questi, quelli che non è chiara l''appartenenza alla "Testata" o alla "Riga", sono riportati su entrambe le tabelle. "ordercsv.pdf"  prescrive che «Tutti i campi devono essere racchiusi nelle doppie virgolette " (anche quelli vuoti)», ma ciò non trova riscontro in "web_order.csv" (2021.06.10)';
--------------------------------------------------------
--  DDL for Table FAI_ORDINE_IN_COLLECTION
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" 
   (	"OID" NUMBER(*,0), 
	"INPUT_RESOURCE" VARCHAR2(100 BYTE), 
	"INPUT_RESOURCE_FULL_PATH" VARCHAR2(500 BYTE), 
	"BATCH_ID" NUMBER(*,0), 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"OID_STATUS" NUMBER(*,0) DEFAULT 1, 
	"STATUS_DESCR" CLOB, 
	"STATUS_TECH_DESCR" CLOB, 
	"STATUS_UPDATED_TS" DATE DEFAULT SYSDATE, 
	"UNIQUE_ID" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("STATUS_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("STATUS_TECH_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."OID" IS 'Identificativo univoco della tupla (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."INPUT_RESOURCE" IS 'se valorizzato, mantiene traccia del nome della risorsa dalla quale sono stati estratti i FAI_ORDINE_IN (e FAI_ORDINE_IN_RIGA_DETTAGLIO) referenzianti questo record. Al momento, l''unica copia resa disponibile di tale risorsa, in forma di file, reca il nome "web_order.csv" (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."INPUT_RESOURCE_FULL_PATH" IS 'Vale quanto scritto per INPUT_RESOURCE, ma, se valorizzata, include anche altri informazioni circa la fonte alla quale è stata attinta la risorsa. Es. "ftp://localhost:21/test/web_order.csv". La funzione del campo è puramente documetnativa, per cui potrebbero esser presenti URI che URI non sono, es. "[soap]http://localhost/serviceTest/getCsv-->web_order.csv" (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."BATCH_ID" IS 'Identificativo dell''intero processo originante da dal csv in ingresso ("web_order.csv"). E'' pertanto unique e, per convenzione, coincide con la data/ora di ricezione del csv espressa in formato yyyyMMddHHmmss. Ad esempio, tutti gli ordini (FAI_ORDINE_IN) estratti dal csv ricevuto alla 15:27:01 del 16 giugno 2021 avranno BATCH_ID 20210616152701 (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."CREATION_TS" IS 'data/ora di creazione del record (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."OID_STATUS" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."STATUS_DESCR" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."STATUS_TECH_DESCR" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."STATUS_UPDATED_TS" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_COLLECTION"."UNIQUE_ID" IS 'Al momento non è chiaro se il file sarà univocamente identificato dal suo nome, o dal suo contenuto, o da altro. A prescindere da quella che sarà la decisione finale, il presente campo risulterà sempre valorizzato, per l''appunto, con tale identificativo (2021.06.21)';
   COMMENT ON TABLE "FAI"."FAI_ORDINE_IN_COLLECTION"  IS 'Rappresenta una collezione di ordini in ingresso acquisita in blocco, in modo atomico, dal processo. Facendo riferimento agli elementi noti al 2021.06.17, FAI_ORDINE_IN_COLLECTION, FAI_ORDINE_IN e FAI_ORDINE_IN_RIGA_DETTAGLIO sono gli equivalenti E-R, rispettivamente, del file in ingresso "web_order.csv" e (con terminologia attinta a "ordercsv.pdf") "Riga Testata" e "Riga Dettaglio" (2021.06.17)';
--------------------------------------------------------
--  DDL for Table FAI_ORDINE_IN_RIGA_DETTAGLIO
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" 
   (	"OID" NUMBER(*,0), 
	"NUMERO_OGGETTO" VARCHAR2(30 BYTE), 
	"TITOLO_INSERZIONE" VARCHAR2(100 BYTE), 
	"ETICHETTA_PERSONALIZZATA" VARCHAR2(100 BYTE), 
	"QUANTITA" NUMBER(*,0), 
	"PREZZO_FINALE" NUMBER(22,4), 
	"ALIQUOTA_IVA_INCLUSA" NUMBER(*,0), 
	"ID_PRODOTTO_UNIVOCO" VARCHAR2(50 BYTE), 
	"PRODUCTIDTYPE" VARCHAR2(20 BYTE), 
	"PRODUCTIDVALUE_2" NUMBER(22,4), 
	"PRODUCTIDVALUE" NUMBER(22,4), 
	"DETTAGLI_VARIAZIONE" VARCHAR2(60 BYTE), 
	"OID_ORDINEIN" NUMBER(*,0), 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"CODICE_MINSAN" VARCHAR2(9 BYTE), 
	"CODICE_EAN" VARCHAR2(13 BYTE), 
	"IS_COUPON" CHAR(1 BYTE), 
	"MAGAZZINO_ACRONYM" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."OID" IS 'identificativo univoco della tupla (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."TITOLO_INSERZIONE" IS 'Titolo inserzione (nome prodotto), es. "Phyto 7 Crema Idratante Da Giorno Capelli Secchi 50m" oppure "Coupon" (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."ETICHETTA_PERSONALIZZATA" IS 'Etichetta personalizzata (codice aic del prodotto), es. "911116806", "P99999998" (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."QUANTITA" IS 'Quantità (pezzi del prodotto), es., 2, 1 (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."PREZZO_FINALE" IS 'Prezzo finale (prezzo di vendita unitario), es EUR 10,53 o EUR -5,00 (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."ALIQUOTA_IVA_INCLUSA" IS 'Attenzione: per i clienti esteri che non pagano iva bisogna indicare nel campo "Aliquota IVA inclusa" il valore 0 e tutti i valori di PREZZO devono essere già deivati. (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."OID_ORDINEIN" IS 'foreign key verso FAI_ORDINE_IN, ossia l''Ordine in Ingresso che contiene questa riga (2021.06.10)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."CREATION_TS" IS 'data/ora di creazione del record (2021.06.14)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."IS_COUPON" IS 'normalmente ''N'', se ''S'' indica che la riga è un Coupon invece che un Prodotto (2021.07.23)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"."MAGAZZINO_ACRONYM" IS 'Se NOT NULL, indica il FAI_MAGAZZINO presso il quale va reperito il prodotto. Alcune precisazioni. Primo, nell''ambito del processo di elaborazione degli Ordini, questo campo sarà plausibilmente obblgiatorio, pertanto i controlli di obbligatorietà andranno fatti lato codice. Secondo, al momento (2021.09.21) NON è certo (neppure è disponibile il CSV) che la stringa coinciderà con l''ACRONYM di FAI_MAGAZZINO: potrebbe tranquillamente essere un testo differente che richiederà o un nuovo campo su FAI_MAGAZZINO (es. FAI_ORDINE_MAGAZZINO_ACRONYM) o un''intera tabella per la trascodifica (2021.09.21).';
   COMMENT ON TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO"  IS 'Rappresenta le "Riga Dettaglio" del file di Ordini documentato nel "ordercsv.pdf" reso disponibile al 2021.05.06 e corredato del file "web_order.csv" di esempio. La documentazione ai singoli campi della presente tabella è attinta dal  "ordercsv.pdf". I campi non documentati, sono presenti in "web_order.csv", ma non citati in "ordercsv.pdf". Di questi, quelli che non è chiara l''appartenenza alla "Testata" o alla "Riga", sono riportati su entrambe le tabelle. "ordercsv.pdf"  prescrive che «Tutti i campi devono essere racchiusi nelle doppie virgolette " (anche quelli vuoti)», ma ciò non trova riscontro in "web_order.csv". (2021.06.10)';
--------------------------------------------------------
--  DDL for Table FAI_ORDINE_OUT
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_ORDINE_OUT" 
   (	"OID" NUMBER(*,0), 
	"ID_ORDINE" VARCHAR2(20 BYTE), 
	"ID_RICEVUTA" VARCHAR2(20 BYTE), 
	"PREZZO_TOTALE" NUMBER(22,4), 
	"ALIQUOTA_IVA" NUMBER(22,4), 
	"ALIQUOTA_IVA_INCLUSA" NUMBER(22,4), 
	"OID_FORNITORE" NUMBER(*,0), 
	"OID_STATUS" NUMBER(*,0) DEFAULT 1, 
	"STATUS_DESCR" CLOB, 
	"STATUS_TECH_DESCR" CLOB, 
	"STATUS_UPDATED_TS" DATE DEFAULT SYSDATE, 
	"CREATION_TS" DATE DEFAULT SYSDATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("STATUS_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("STATUS_TECH_DESCR") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."OID" IS 'Identificativo univoco della tupla (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."ID_ORDINE" IS 'Identificativo dell''Ordine, se applicabile (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."ID_RICEVUTA" IS 'Identificativo della Ricevuta dell''Ordine, se applicabile (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."PREZZO_TOTALE" IS 'importo totale dell''ordine (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."ALIQUOTA_IVA" IS 'aliquota IVA espresa in percentuale. 20.5 rappresenta il "20.5%" (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."ALIQUOTA_IVA_INCLUSA" IS '''S'' se l''aliquota IVA è inclusa, altrimenti ''N''.  (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."OID_FORNITORE" IS 'foreign key verso FAI_FORNITORE (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."OID_STATUS" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."STATUS_DESCR" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."STATUS_TECH_DESCR" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."STATUS_UPDATED_TS" IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';
   COMMENT ON COLUMN "FAI"."FAI_ORDINE_OUT"."CREATION_TS" IS 'data/ora di creazione del record (2021.06.18)';
   COMMENT ON TABLE "FAI"."FAI_ORDINE_OUT"  IS 'Aggrega i FAI_APPROVVIGGIONAMENTO_FARMACO relativi allo stesso FAI_FORNITORE. Rispetto a FAI_ORDINE_OUT, quindi, FAI_APPROVVIGGIONAMENTO_FARMACO sono i "FAI_ORDINE_OUT_RIGA_DETTAGLIO" (2021.06.17)';
--------------------------------------------------------
--  DDL for Table FAI_RECORD_A_ADDEBITO_FINANZ
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" 
   (	"OID" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"AZIONE" CHAR(1 BYTE), 
	"CODICE_CLIENTE" VARCHAR2(7 BYTE), 
	"CODICE_ADDEBITO_FINANZ" VARCHAR2(3 BYTE), 
	"DESCR_ADDEBITO_FINANZ" VARCHAR2(25 BYTE), 
	"PERC_ADDEBITO_FINANZ" NUMBER(4,2), 
	"NUM_GIORNI_ANNO_COMM" NUMBER(*,0), 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"UPDATE_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ"."OID" IS 'Identificativo univoco della tupla (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ"."OID_FORNITORE" IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ"."AZIONE" IS 'I Inserimento, V Variazione, C Cancellazione (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ"."CREATION_TS" IS 'data/ora di creazione del record (2021.05.12)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ"."UPDATE_TS" IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';
   COMMENT ON TABLE "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ"  IS 'Record "A": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';
--------------------------------------------------------
--  DDL for Table FAI_RECORD_C_CONDIZIONI
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_RECORD_C_CONDIZIONI" 
   (	"OID" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"AZIONE" CHAR(1 BYTE), 
	"CODICE_CLIENTE" VARCHAR2(7 BYTE), 
	"CODICE_PRODOTTO" VARCHAR2(7 BYTE), 
	"CODICE_MINSAN" VARCHAR2(9 BYTE), 
	"CODICE_EAN" VARCHAR2(13 BYTE), 
	"DESCR_PRODOTTO" VARCHAR2(40 BYTE), 
	"SEQUENZA" CHAR(1 BYTE), 
	"SOTTOSEQUENZA" NUMBER(*,0), 
	"QUANTITA_MASSIMA" NUMBER(*,0), 
	"QUANTITA_MINIMA" NUMBER(*,0), 
	"QUANTITA_ASSEGNATA" NUMBER(*,0), 
	"QUANTITA_OMAGGIO" NUMBER(*,0), 
	"DATA_INIZIO_VALIDITA" DATE, 
	"DATA_FINE_VALIDITA" DATE, 
	"PREZZO_VEND_LORDO_SCONTI" NUMBER(11,4), 
	"CODICE_PRODOTTO_OMAGGIO" VARCHAR2(13 BYTE), 
	"DESCR_PRODOTTO_OMAGGIO" VARCHAR2(40 BYTE), 
	"QUANTITA_PRODOTTO_OMAGGIO" NUMBER(*,0), 
	"DILAZIONE_PAGAM_GIORNI" NUMBER(*,0), 
	"GIORNI_ABBU_CALC_ADD" NUMBER(*,0), 
	"CODICE_ADDEBITO" VARCHAR2(3 BYTE), 
	"PERC_ADDEBITO_AGG" NUMBER(4,2), 
	"SCONTO_CASSA" NUMBER(4,2), 
	"ALLINEAM_SCADENZA" CHAR(1 BYTE), 
	"PERIODICITA_FATTURAZ" CHAR(1 BYTE), 
	"PREZZO_NETTO_UNITARIO" NUMBER(13,6), 
	"PERC_SC_PRZPUB_PRZNETUN" NUMBER(8,2), 
	"PERC_SC_PRZPUBDR_PRZNETUN" NUMBER(8,2), 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"UPDATE_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_RECORD_C_CONDIZIONI"."OID" IS 'Identificativo univoco della tupla (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_C_CONDIZIONI"."OID_FORNITORE" IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_C_CONDIZIONI"."AZIONE" IS 'Azione: I Inserimento, V Variazione, C Cancellazione (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_C_CONDIZIONI"."PERC_SC_PRZPUB_PRZNETUN" IS 'Percentuale sconto tra prezzo al pubblico e il prezzo netto unitario (Il campo viene valorizzato solo se è presente un prezzo al pubblico del prodotto.) (2021.05.11);';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_C_CONDIZIONI"."PERC_SC_PRZPUBDR_PRZNETUN" IS 'Percentuale sconto tra prezzo al pubblico derivato e il prezzo netto unitario ( Il campo viene valorizzato solo se è presente un prezzo al pubblico del prodotto.) (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_C_CONDIZIONI"."CREATION_TS" IS 'data/ora di creazione del record (2021.05.12)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_C_CONDIZIONI"."UPDATE_TS" IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';
   COMMENT ON TABLE "FAI"."FAI_RECORD_C_CONDIZIONI"  IS 'Record "C": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';
--------------------------------------------------------
--  DDL for Table FAI_RECORD_D_RESET
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_RECORD_D_RESET" 
   (	"OID" NUMBER(*,0), 
	"CODICE_CLIENTE" VARCHAR2(7 BYTE), 
	"OID_FORNITORE" NUMBER(*,0), 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"UPDATE_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_RECORD_D_RESET"."OID" IS 'Identificativo univoco della tupla (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_D_RESET"."OID_FORNITORE" IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_D_RESET"."CREATION_TS" IS 'data/ora di creazione del record (2021.05.12)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_D_RESET"."UPDATE_TS" IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';
   COMMENT ON TABLE "FAI"."FAI_RECORD_D_RESET"  IS 'Record "D": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';
--------------------------------------------------------
--  DDL for Table FAI_RECORD_L_LISTINO
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_RECORD_L_LISTINO" 
   (	"OID" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"AZIONE" CHAR(1 BYTE), 
	"CODICE_CLIENTE" VARCHAR2(7 BYTE), 
	"CODICE_PRODOTTO" VARCHAR2(7 BYTE), 
	"CODICE_MINSAN" VARCHAR2(9 BYTE), 
	"CODICE_EAN" VARCHAR2(13 BYTE), 
	"DESCR_PRODOTTO" VARCHAR2(40 BYTE), 
	"CATEGORIA_TIPO_PRODOTTO" VARCHAR2(2 BYTE), 
	"PREZZO_AL_PUBBLICO" NUMBER(11,4), 
	"ALIQUOTA_IVA" NUMBER(3,1), 
	"PREZ_VEND_LIST_LORDO_SC" NUMBER(11,4), 
	"SCONTO_1" NUMBER(4,2), 
	"SCONTO_2" NUMBER(4,2), 
	"DILAZ_PAGAM_GIORNI" NUMBER(*,0), 
	"GIORNI_ABBU_CALC_ADD" NUMBER(*,0), 
	"CODICE_ADDEBITO" VARCHAR2(3 BYTE), 
	"PERC_ADDEBITO_AGG" NUMBER(4,2), 
	"SCONTO_CASSA" NUMBER(4,2), 
	"ALLINEAM_SCADENZA" CHAR(1 BYTE), 
	"PERIODICITA_FATTURAZ" CHAR(1 BYTE), 
	"QUANT_PER_COLLO_VEND" NUMBER(*,0), 
	"PREZZO_VEND_CONSIG" NUMBER(11,4), 
	"PREZZO_PUBB_DERIVATO" NUMBER(11,4), 
	"PREZZO_NETTO_UNITARIO" NUMBER(13,6), 
	"PERC_SC_PRZPUB_PRZNETUN" NUMBER(8,2), 
	"PERC_SC_PRZPUBDR_PRZNETUN" NUMBER(8,2), 
	"SOMMA_SCONTO_1_2" NUMBER(4,2), 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"UPDATE_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."OID" IS 'Identificativo univoco della tupla (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."OID_FORNITORE" IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."AZIONE" IS 'Azione: I Inserimento, V Variazione, C Cancellazione (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."PREZ_VEND_LIST_LORDO_SC" IS 'Prezzo di vendita a listino lordo sconti (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."DILAZ_PAGAM_GIORNI" IS 'Dilazione pagamento in giorni (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."GIORNI_ABBU_CALC_ADD" IS 'Giorni abbuono per il calcolo dell''addebito (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."PERC_ADDEBITO_AGG" IS 'Percentuale addebito aggiuntiva (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."ALLINEAM_SCADENZA" IS 'Allineamento scadenza: "D": Data fattura, "F": Fine mese (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."PERIODICITA_FATTURAZ" IS 'Periodicità di fatturazione. "A":  Accompagnatoria, "S": Settimanale, "Q": Quindicinale, "M": Mensile (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."PERC_SC_PRZPUB_PRZNETUN" IS 'Percentuale sconto tra prezzo al pubblico e il prezzo netto unitario (Il campo viene valorizzato solo se è presente un prezzo al pubblico del prodotto.) (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."PERC_SC_PRZPUBDR_PRZNETUN" IS 'Percentuale sconto tra prezzo al pubblico derivato e il prezzo netto unitario ( Il campo viene valorizzato solo se è presente un prezzo al pubblico del prodotto.) (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."SOMMA_SCONTO_1_2" IS 'Somma sconto 1 e sconto 2 (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."CREATION_TS" IS 'data/ora di creazione del record (2021.05.12)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_L_LISTINO"."UPDATE_TS" IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';
   COMMENT ON TABLE "FAI"."FAI_RECORD_L_LISTINO"  IS 'Record "L": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';
--------------------------------------------------------
--  DDL for Table FAI_RECORD_R_RAGGR_COND
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_RECORD_R_RAGGR_COND" 
   (	"OID" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"AZIONE" CHAR(1 BYTE), 
	"CODICE_CLIENTE" VARCHAR2(7 BYTE), 
	"CODICE_REGGRUPP_CONDIZ" VARCHAR2(3 BYTE), 
	"RIGA" CHAR(1 BYTE), 
	"DESCR_REGGRUPP_CONDIZ" VARCHAR2(78 BYTE), 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"UPDATE_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_RECORD_R_RAGGR_COND"."OID" IS 'Identificativo univoco della tupla (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_R_RAGGR_COND"."OID_FORNITORE" IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_R_RAGGR_COND"."AZIONE" IS 'I Inserimento, V Variazione, C Cancellazione (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_R_RAGGR_COND"."CODICE_REGGRUPP_CONDIZ" IS 'Codice raggruppamento condizione (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_R_RAGGR_COND"."CREATION_TS" IS 'data/ora di creazione del record (2021.05.12)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_R_RAGGR_COND"."UPDATE_TS" IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';
   COMMENT ON TABLE "FAI"."FAI_RECORD_R_RAGGR_COND"  IS 'Record "R": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';
--------------------------------------------------------
--  DDL for Table FAI_RECORD_V_RAGGR_COND
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_RECORD_V_RAGGR_COND" 
   (	"OID" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"AZIONE" CHAR(1 BYTE), 
	"CODICE_CLIENTE" VARCHAR2(7 BYTE), 
	"CODICE_ADDEBITO" VARCHAR2(2 BYTE), 
	"DESCR_ADDEBITO" VARCHAR2(30 BYTE), 
	"DESTINAZIONE_ADDEBITO" CHAR(1 BYTE), 
	"PERIODICITA_FATTURAZ" CHAR(1 BYTE), 
	"IMORTO_MASSIMO_DA_ADDEB" NUMBER(10,4), 
	"NUMERO_MASSIMO_ADDEB" NUMBER(*,0), 
	"IMPORTO_DA_ADDEB" NUMBER(11,4), 
	"PERC_ADDEB_SU_IMPONIBILE" NUMBER(4,2), 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"UPDATE_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."OID" IS 'Identificativo univoco della tupla (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."OID_FORNITORE" IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."AZIONE" IS 'I Inserimento, V Variazione, C Cancellazione (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."DESTINAZIONE_ADDEBITO" IS 'Destinazione addebito. ''B'': DDT o fattura accompagnatoria, ''F'': Fattura';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."PERIODICITA_FATTURAZ" IS 'Periodicità di fatturazione. "A": Accompagnatoria, "S": Settimanale, "Q": Quindicinale, "M": Mensile';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."IMORTO_MASSIMO_DA_ADDEB" IS 'Oltre tale importo non viene più applicato l''addebito (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."NUMERO_MASSIMO_ADDEB" IS 'Numero massimi addebiti. (Solo per destinazione addebito "F") (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."IMPORTO_DA_ADDEB" IS 'Importo da addebitare. (Deve essere addebitato ad ogni documento) (2011.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."PERC_ADDEB_SU_IMPONIBILE" IS 'Percentuale di addebito su imponibile (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."CREATION_TS" IS 'data/ora di creazione del record (2021.05.12)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_V_RAGGR_COND"."UPDATE_TS" IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';
   COMMENT ON TABLE "FAI"."FAI_RECORD_V_RAGGR_COND"  IS 'Record "V": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';
--------------------------------------------------------
--  DDL for Table FAI_RECORD_Z_CHIUSURA
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_RECORD_Z_CHIUSURA" 
   (	"OID" NUMBER(*,0), 
	"OID_FORNITORE" NUMBER(*,0), 
	"AZIONE" CHAR(1 BYTE), 
	"CODICE_CLIENTE" VARCHAR2(7 BYTE), 
	"DATA_ORA_GENERAZ" DATE, 
	"CREATION_TS" DATE DEFAULT SYSDATE, 
	"UPDATE_TS" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_RECORD_Z_CHIUSURA"."OID" IS 'Identificativo univoco della tupla (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_Z_CHIUSURA"."OID_FORNITORE" IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_Z_CHIUSURA"."AZIONE" IS 'I Inserimento, V Variazione, C Cancellazione (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_Z_CHIUSURA"."DATA_ORA_GENERAZ" IS 'E'' l''unione dei campi "Data di generazione" e "Orario di generazione" (2021.05.11)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_Z_CHIUSURA"."CREATION_TS" IS 'data/ora di creazione del record (2021.05.12)';
   COMMENT ON COLUMN "FAI"."FAI_RECORD_Z_CHIUSURA"."UPDATE_TS" IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';
   COMMENT ON TABLE "FAI"."FAI_RECORD_Z_CHIUSURA"  IS 'Record "Z": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';
--------------------------------------------------------
--  DDL for Table FAI_TABLE_EXPORT
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_TABLE_EXPORT" 
   (	"OID" NUMBER(*,0), 
	"SQL_QUERY_IN" CLOB, 
	"SQL_QUERY_IN_FILE" VARCHAR2(500 BYTE), 
	"SMTWTFS_EXPORT_PATTERN" CHAR(7 BYTE), 
	"TABLE_NAME_ORACLE" VARCHAR2(30 BYTE), 
	"TABLE_IDX" NUMBER(*,0), 
	"TABLE_NAME_EXPORT" VARCHAR2(100 BYTE), 
	"SQL_DDL" CLOB, 
	"OID_GROUPEXPORT" NUMBER(*,0), 
	"SQL_QUERY_OUT" CLOB, 
	"SQL_QUERY_OUT_FILE" VARCHAR2(500 BYTE), 
	"LAST_EXPORTED_VERSION_NUMBER" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("SQL_QUERY_IN") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("SQL_DDL") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
 LOB ("SQL_QUERY_OUT") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."OID" IS 'identificativo univoco della tupla (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."SQL_QUERY_IN" IS 'Se NOT NULL è la query per mezzo della quale viene vengono estratti i dati che saranno importati nella TABLE_NAME_ORACLE. La query deve presentare i campi oracle indicati nelle COLUMN_EXPORT.COLUMN_NAME_ORACLE referenzianti questo record e si presenterà nel formato INSERT INTO ... SELECT ...(2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."SQL_QUERY_IN_FILE" IS 'Se NOT NULL indica il percorso completo sul filesystem della query avente le medesime caratteristiche documetnate per SQL_QUERY_IN, cui si rimanda (2021.05.25)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."SMTWTFS_EXPORT_PATTERN" IS 'Pattern di esportazione settimanale (Sunday, Monday, ...). I dati saranno esportati verso il sistema target (previa importazione da altri sistemi sorgente) a patto che per il corrispondente giorno della settimana sia specificato "X" invece che "-"  (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."TABLE_NAME_ORACLE" IS 'nome della tabella Oracle. Per ogni record di questa tabella, infatti, sarà creata una tabella Oracle il cui nome è rappresetato da questo campo (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."TABLE_IDX" IS 'inseme a GROUP_EXPORT.GROUP_IDX  consente di statabilire l''ordine con cui i dati delle tabelle sono importati in questo schema ed esportarti verso il sistema target. (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."TABLE_NAME_EXPORT" IS 'nome della tabella nel sistema di destinazione (es. "PoHeadersInterfaceBlanket"). Al momento (2021.05.18) tale nome corrisponderà al nome del CSV esportato verso il prodotto Oracle (es. "PoHeadersInterfaceBlanket.csv") (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."SQL_DDL" IS 'utilizzato dal sistema contiene lo statement - o gli stamtent - SQL utilizzati per creare la tabella Oracle la cui struttura è defnita per mezzo di questo record e dei record tabella COLUMN_EXPORT che lo referenziano. Il sistema utilizza questo campo per capire se la struttura della tabella è cambiata e, in caso affermativo, la cancella (DROP) e ricrea (2021.05.28)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."OID_GROUPEXPORT" IS 'Identificativo univoco della tupla (2021.05.18)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."SQL_QUERY_OUT" IS 'Analogo a SQL_QUERY_IN, ma è la SELECT per mezzo della quale i dati importati nella TABLE_NAME_ORACLE vengono recuperati e formattati per l''export. Obbligatorio rispettare i nomi dei campi che seguono la keyword SELECT e che dovranno coincidere coi nomi dei campi FAI_COLUMN_EXPORT.COLUMN_NAME_ORACLE. In base a questa corrispondenza, infatti, viene determinato il formatter da utilzizare (DATE_FORMAT_PATTERN, NUMB_FORMAT_PATTERN) e quant''altro necessario all''operazione di trascodifica (2021.05.25)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."SQL_QUERY_OUT_FILE" IS 'E'' per SQL_QUERY_OUT ciò che SQL_QUERY_IN_FILE è per SQL_QUERY_IN (2021.05.21)';
   COMMENT ON COLUMN "FAI"."FAI_TABLE_EXPORT"."LAST_EXPORTED_VERSION_NUMBER" IS 'Riporta l''ultimo numero di versione della TABLE_NAME_ORACLE esportata con successo. Se nessuna esportazione ha avuto ancora luogo per la tabella, questo campo risulterà valorizzato con NULL (2021.05.28)';
   COMMENT ON TABLE "FAI"."FAI_TABLE_EXPORT"  IS 'Rappresenta una tabella di dati da esportare verso un sistema esterno. Al momento (2021.05.18) il formato di export è CSV e il sistema esterno è un prodotto Oracle. La struttura della tabella è definita da FAI_TABLE_EXPORT e FAI_COLUMN_EXPORT. Ulteriori campi forniscono informazioni circa il recupero dei dati che la popolano, la periodicità dell''export, ecc... I dati da esportare passano sempre per una tabella Oracle fisica, il cui nome è specificato da TABLE_NAME_ORACLE e le cui colonne sono FAI_COLUMN_EXPORT.COLUMN_NAME_ORACLE, generata automaticamente dal sistema (2021.05.18)';
--------------------------------------------------------
--  DDL for Table FAI_TIPO_FARMACO
--------------------------------------------------------

  CREATE TABLE "FAI"."FAI_TIPO_FARMACO" 
   (	"OID" NUMBER(*,0), 
	"ACRONYM" VARCHAR2(20 BYTE), 
	"DESCR" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "FAI"."FAI_TIPO_FARMACO"."OID" IS 'identificativo univoco della tupla (2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_TIPO_FARMACO"."ACRONYM" IS 'acronimo univoco della tupla (2021.06.16)';
   COMMENT ON COLUMN "FAI"."FAI_TIPO_FARMACO"."DESCR" IS 'descrizione (2021.06.16)';
   COMMENT ON TABLE "FAI"."FAI_TIPO_FARMACO"  IS 'Tipologia di Farmaco, quali "FARMACO" o "PARAFARMACO" (2021.06.16)';
--------------------------------------------------------
--  DDL for Table SD_SCHEMA_VERSION
--------------------------------------------------------

  CREATE TABLE "FAI"."SD_SCHEMA_VERSION" 
   (	"VERSION" VARCHAR2(64 BYTE), 
	"LAST_MODIFY" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON TABLE "FAI"."SD_SCHEMA_VERSION"  IS 'Tabella di versioning dello schema (2021.05.12)';
--------------------------------------------------------
--  DDL for View V_FAI_LISTINI_FORNITORI
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "FAI"."V_FAI_LISTINI_FORNITORI" ("FONTE", "CODICE_FORNITORE_FONTE", "CODICE_FORNITORE", "OID_FORNITORE", "CODICE", "CODICE_MINSAN", "CODICE_EAN", "DESCRIZIONE", "ALIQUOTA_IVA", "PREZZO_LORDO_SCONTI", "PREZZO_LORDO_SCONTI_IVATO", "PREZZO", "PREZZO_IVATO") AS 
     'FARMACLICK'                                   FONTE  ,FAI_FARMACLICK_FORNITORE.CODICE                CODICE_FORNITORE_FONTE  ,FAI_FORNITORE.CODICE                           CODICE_FORNITORE  ,FAI_FORNITORE.OID                              OID_FORNITORE  ,NVL(LISTINO.CODICE_MINSAN, LISTINO.CODICE_EAN) CODICE  ,LISTINO.CODICE_MINSAN                          CODICE_MINSAN    ,LISTINO.CODICE_EAN                             CODICE_EAN       ,LISTINO.DESCR_PRODOTTO                         DESCRIZIONE  ,LISTINO.ALIQUOTA_IVA                           ALIQUOTA_IVA  ,LISTINO.PREZ_VEND_LIST_LORDO_SC                PREZZO_LORDO_SCONTI  ,ROUND(LISTINO.PREZ_VEND_LIST_LORDO_SC * (1 + ALIQUOTA_IVA/100)                           , 2)    PREZZO_LORDO_SCONTI_IVATO  ,ROUND(LISTINO.PREZ_VEND_LIST_LORDO_SC * (1-SOMMA_SCONTO_1_2/100)                         , 2)    PREZZO  ,ROUND(LISTINO.PREZ_VEND_LIST_LORDO_SC * (1 + ALIQUOTA_IVA/100) * (1-SOMMA_SCONTO_1_2/100), 2)    PREZZO_IVATOFROM    FAI_FORNITORE  ,FAI_FARMACLICK_FORNITORE   ,FAI_RECORD_L_LISTINO LISTINOWHERE     FAI_FORNITORE.CODICE_FARMACLICK= FAI_FARMACLICK_FORNITORE.CODICE   AND   FAI_FARMACLICK_FORNITORE.OID = LISTINO.OID_FORNITORE-- UNION via via le altre tabelle delle altre fonti   
;
REM INSERTING into FAI.FAI_APPROVVIGFARMACO_ORDINRIGA
SET DEFINE OFF;
REM INSERTING into FAI.FAI_APPROVVIGIONAMENTO_FARMACO
SET DEFINE OFF;
REM INSERTING into FAI.FAI_COLUMN_EXPORT
SET DEFINE OFF;
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (19,1,'INTERFACE_LINE_KEY',' Interface Line Key','S','VARCHAR2',50,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (20,1,'INTERFACE_LINE_KEY',' Interface Line Key','S','VARCHAR2',50,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (21,2,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (22,2,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (23,3,'ACTION',' Action','S','VARCHAR2',25,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (24,3,'ACTION',' Action','S','VARCHAR2',25,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (25,4,'LINE_NUM','Line','N','NUMBER',22,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (26,4,'LINE_NUM','Line','N','NUMBER',22,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (27,5,'LINE_TYPE','Line Type','N','VARCHAR2',30,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (28,5,'LINE_TYPE','Line Type','N','VARCHAR2',30,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (29,6,'ITEM','Item','N','VARCHAR2',300,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (30,6,'ITEM','Item','N','VARCHAR2',300,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (31,7,'ITEM_DESCRIPTION','Item Description','N','VARCHAR2',240,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (32,7,'ITEM_DESCRIPTION','Item Description','N','VARCHAR2',240,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (33,8,'ITEM_REVISION','Item Revision','N','VARCHAR2',18,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (34,8,'ITEM_REVISION','Item Revision','N','VARCHAR2',18,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (35,9,'CATEGORY','Category Name','N','VARCHAR2',2000,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (36,9,'CATEGORY','Category Name','N','VARCHAR2',2000,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (37,10,'COMMITTED_AMOUNT','Agreement Amount','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (38,10,'COMMITTED_AMOUNT','Agreement Amount','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (39,11,'UNIT_OF_MEASURE','UOM','N','VARCHAR2',25,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (40,11,'UNIT_OF_MEASURE','UOM','N','VARCHAR2',25,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (41,12,'UNIT_PRICE','Price','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (42,12,'UNIT_PRICE','Price','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (43,13,'ALLOW_PRICE_OVERRIDE_FLAG','Allow Price Override','N','VARCHAR2',1,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (44,13,'ALLOW_PRICE_OVERRIDE_FLAG','Allow Price Override','N','VARCHAR2',1,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (45,14,'NOT_TO_EXCEED_PRICE','Not to exceed price','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (46,14,'NOT_TO_EXCEED_PRICE','Not to exceed price','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (47,15,'VENDOR_PRODUCT_NUM','Supplier Item','N','VARCHAR2',25,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (48,15,'VENDOR_PRODUCT_NUM','Supplier Item','N','VARCHAR2',25,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (49,16,'NEGOTIATED_BY_PREPARER_FLAG','Negotiated','N','VARCHAR2',1,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (50,16,'NEGOTIATED_BY_PREPARER_FLAG','Negotiated','N','VARCHAR2',1,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (51,17,'NOTE_TO_VENDOR','Note to Supplier','N','VARCHAR2',1000,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (52,17,'NOTE_TO_VENDOR','Note to Supplier','N','VARCHAR2',1000,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (53,18,'NOTE_TO_RECEIVER','Note to Receiver','N','VARCHAR2',1000,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (54,18,'NOTE_TO_RECEIVER','Note to Receiver','N','VARCHAR2',1000,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (55,19,'MIN_RELEASE_AMOUNT','Minimum Release Amount','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (56,19,'MIN_RELEASE_AMOUNT','Minimum Release Amount','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (57,20,'EXPIRATION_DATE','Expiration Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (58,20,'EXPIRATION_DATE','Expiration Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (59,21,'SUPPLIER_PART_AUXID','Supplier Part Auxiliary ID','N','VARCHAR2',255,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (60,21,'SUPPLIER_PART_AUXID','Supplier Part Auxiliary ID','N','VARCHAR2',255,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (61,22,'SUPPLIER_REF_NUMBER','Supplier Ref Number','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (62,22,'SUPPLIER_REF_NUMBER','Supplier Ref Number','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (63,23,'LINE_ATTRIBUTE_CATEGORY_LINES','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (64,23,'LINE_ATTRIBUTE_CATEGORY_LINES','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (65,24,'LINE_ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (66,24,'LINE_ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (67,25,'LINE_ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (68,25,'LINE_ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (69,26,'LINE_ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (70,26,'LINE_ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (71,27,'LINE_ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (72,27,'LINE_ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (73,28,'LINE_ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (74,28,'LINE_ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (75,29,'LINE_ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (76,29,'LINE_ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (77,30,'LINE_ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (78,30,'LINE_ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (79,31,'LINE_ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (80,31,'LINE_ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (81,32,'LINE_ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (82,32,'LINE_ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (83,33,'LINE_ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (84,33,'LINE_ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (85,34,'LINE_ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (86,34,'LINE_ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (87,35,'LINE_ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (88,35,'LINE_ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (89,36,'LINE_ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (90,36,'LINE_ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (91,37,'LINE_ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (92,37,'LINE_ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (93,38,'LINE_ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (94,38,'LINE_ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (95,39,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (96,39,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (97,40,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (98,40,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (99,41,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (100,41,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (101,42,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (102,42,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (103,43,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (104,43,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (105,44,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (106,44,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (107,45,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (108,45,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (109,46,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (110,46,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (111,47,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (112,47,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (113,48,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (114,48,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (115,49,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (116,49,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (117,50,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (118,50,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (119,51,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (120,51,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (121,52,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (122,52,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (123,53,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (124,53,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (125,54,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (126,54,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (127,55,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (128,55,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (129,56,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (130,56,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (131,57,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (132,57,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (133,58,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (134,58,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (135,59,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (136,59,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (137,60,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (138,60,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (139,61,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (140,61,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (141,62,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (142,62,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (143,63,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (144,63,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (145,64,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (146,64,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (147,65,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (148,65,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (149,66,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (150,66,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (151,67,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (152,67,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (153,68,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (154,68,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (155,69,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (156,69,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (157,70,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (158,70,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (159,71,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (160,71,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (161,72,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (162,72,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (163,73,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (164,73,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (165,74,'AGING_PERIOD_DAYS','Aging Period Days','N','NUMBER',22,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (166,74,'AGING_PERIOD_DAYS','Aging Period Days','N','NUMBER',22,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (167,75,'CONSIGNMENT_LINE_FLAG','Consignment Line','N','VARCHAR2',1,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (168,75,'CONSIGNMENT_LINE_FLAG','Consignment Line','N','VARCHAR2',1,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (169,76,'UNIT_WEIGHT','Unit Weight','N','NUMBER',22,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (170,76,'UNIT_WEIGHT','Unit Weight','N','NUMBER',22,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (171,77,'WEIGHT_UOM_CODE','Weight UOM Code','N','VARCHAR2',3,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (172,77,'WEIGHT_UOM_CODE','Weight UOM Code','N','VARCHAR2',3,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (173,78,'WEIGHT_UNIT_OF_MEASURE','Weight UOM Name','N','VARCHAR2',25,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (174,78,'WEIGHT_UNIT_OF_MEASURE','Weight UOM Name','N','VARCHAR2',25,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (175,79,'UNIT_VOLUME','Unit Volumn','N','NUMBER',22,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (176,79,'UNIT_VOLUME','Unit Volumn','N','NUMBER',22,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (177,80,'VOLUME_UOM_CODE','Volume UOM Code','N','VARCHAR2',3,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (178,80,'VOLUME_UOM_CODE','Volume UOM Code','N','VARCHAR2',3,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (179,81,'VOLUME_UNIT_OF_MEASURE','Volume UOM Name','N','VARCHAR2',25,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (180,81,'VOLUME_UNIT_OF_MEASURE','Volume UOM Name','N','VARCHAR2',25,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (181,82,'TEMPLATE_NAME','Template Name','N','VARCHAR2',30,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (182,82,'TEMPLATE_NAME','Template Name','N','VARCHAR2',30,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (183,83,'ITEM_ATTRIBUTE_CATEGORY','ITEM_ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (184,83,'ITEM_ATTRIBUTE_CATEGORY','ITEM_ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (185,84,'ITEM_ATTRIBUTE1','ITEM_ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (186,84,'ITEM_ATTRIBUTE1','ITEM_ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (187,85,'ITEM_ATTRIBUTE2','ITEM_ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (188,85,'ITEM_ATTRIBUTE2','ITEM_ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (189,86,'ITEM_ATTRIBUTE3','ITEM_ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (190,86,'ITEM_ATTRIBUTE3','ITEM_ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (191,87,'ITEM_ATTRIBUTE4','ITEM_ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (192,87,'ITEM_ATTRIBUTE4','ITEM_ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (193,88,'ITEM_ATTRIBUTE5','ITEM_ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (194,88,'ITEM_ATTRIBUTE5','ITEM_ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (195,89,'ITEM_ATTRIBUTE6','ITEM_ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (196,89,'ITEM_ATTRIBUTE6','ITEM_ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (197,90,'ITEM_ATTRIBUTE7','ITEM_ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (198,90,'ITEM_ATTRIBUTE7','ITEM_ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (199,91,'ITEM_ATTRIBUTE8','ITEM_ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (200,91,'ITEM_ATTRIBUTE8','ITEM_ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (201,92,'ITEM_ATTRIBUTE9','ITEM_ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (202,92,'ITEM_ATTRIBUTE9','ITEM_ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (203,93,'ITEM_ATTRIBUTE10','ITEM_ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (204,93,'ITEM_ATTRIBUTE10','ITEM_ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (205,94,'ITEM_ATTRIBUTE11','ITEM_ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (206,94,'ITEM_ATTRIBUTE11','ITEM_ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (207,95,'ITEM_ATTRIBUTE12','ITEM_ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (208,95,'ITEM_ATTRIBUTE12','ITEM_ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (209,96,'ITEM_ATTRIBUTE13','ITEM_ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (210,96,'ITEM_ATTRIBUTE13','ITEM_ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (211,97,'ITEM_ATTRIBUTE14','ITEM_ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (212,97,'ITEM_ATTRIBUTE14','ITEM_ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (213,98,'ITEM_ATTRIBUTE15','ITEM_ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (214,98,'ITEM_ATTRIBUTE15','ITEM_ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (215,99,'PARENT_ITEM','Parent Item','N','VARCHAR2',300,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (216,99,'PARENT_ITEM','Parent Item','N','VARCHAR2',300,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (217,100,'TOP_MODEL','Top Model','N','VARCHAR2',300,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (218,100,'TOP_MODEL','Top Model','N','VARCHAR2',300,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (219,101,'SUPPLIER_PARENT_ITEM','Supplier Parent Item','N','VARCHAR2',300,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (220,101,'SUPPLIER_PARENT_ITEM','Supplier Parent Item','N','VARCHAR2',300,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (221,102,'SUPPLIER_TOP_MODEL','Supplier Top Model','N','VARCHAR2',300,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (222,102,'SUPPLIER_TOP_MODEL','Supplier Top Model','N','VARCHAR2',300,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (223,103,'AMOUNT','Amount','N','NUMBER',22,4,null,'###0.##','''','.',17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (224,103,'AMOUNT','Amount','N','NUMBER',22,4,null,'###0.##','''','.',18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (225,104,'PRICE_BREAK_LOOKUP_CODE','Price Break Type','N','VARCHAR2',25,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (226,104,'PRICE_BREAK_LOOKUP_CODE','Price Break Type','N','VARCHAR2',25,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (227,105,'QUANTITY_COMMITTED','Agreement Quantity','N','NUMBER',22,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (228,105,'QUANTITY_COMMITTED','Agreement Quantity','N','NUMBER',22,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (229,106,'ALLOW_DESCRIPTION_UPDATE_FLAG','Allow Description Update','N','VARCHAR2',1,null,null,null,null,null,17,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (230,106,'ALLOW_DESCRIPTION_UPDATE_FLAG','Allow Description Update','N','VARCHAR2',1,null,null,null,null,null,18,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (235,1,'INTERFACE_BU_ASSIGNMENT_KEY',' Interface BU Assignment Key','S','VARCHAR2',50,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (236,1,'INTERFACE_BU_ASSIGNMENT_KEY',' Interface BU Assignment Key','S','VARCHAR2',50,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (237,1,'INTERFACE_BU_ASSIGNMENT_KEY',' Interface BU Assignment Key','S','VARCHAR2',50,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (238,2,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (239,2,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (240,2,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (241,3,'REQ_BU_NAME','Requisitioning BU','N','VARCHAR2',240,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (242,3,'REQ_BU_NAME','Requisitioning BU','N','VARCHAR2',240,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (243,3,'REQ_BU_NAME','Requisitioning BU','N','VARCHAR2',240,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (244,4,'ORDERED_LOCALLY_FLAG','Order Locally','N','VARCHAR2',1,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (245,4,'ORDERED_LOCALLY_FLAG','Order Locally','N','VARCHAR2',1,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (246,4,'ORDERED_LOCALLY_FLAG','Order Locally','N','VARCHAR2',1,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (247,5,'VENDOR_SITE_CODE','Purchasing Site','N','VARCHAR2',15,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (248,5,'VENDOR_SITE_CODE','Purchasing Site','N','VARCHAR2',15,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (249,5,'VENDOR_SITE_CODE','Purchasing Site','N','VARCHAR2',15,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (250,6,'SHIP_TO_LOCATION','Ship-to Location','N','VARCHAR2',60,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (251,6,'SHIP_TO_LOCATION','Ship-to Location','N','VARCHAR2',60,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (252,6,'SHIP_TO_LOCATION','Ship-to Location','N','VARCHAR2',60,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (253,7,'BILL_TO_BU_NAME','Bill-to BU','N','VARCHAR2',240,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (254,7,'BILL_TO_BU_NAME','Bill-to BU','N','VARCHAR2',240,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (255,7,'BILL_TO_BU_NAME','Bill-to BU','N','VARCHAR2',240,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (256,8,'BILL_TO_LOCATION','Bill-to Location','N','VARCHAR2',60,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (257,8,'BILL_TO_LOCATION','Bill-to Location','N','VARCHAR2',60,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (258,8,'BILL_TO_LOCATION','Bill-to Location','N','VARCHAR2',60,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (259,9,'ENABLED','Enabled','N','VARCHAR2',1,null,null,null,null,null,232,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (260,9,'ENABLED','Enabled','N','VARCHAR2',1,null,null,null,null,null,233,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (261,9,'ENABLED','Enabled','N','VARCHAR2',1,null,null,null,null,null,234,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (267,1,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (268,1,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (269,1,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (270,1,'INTERFACE_HEADER_KEY',' Interface Header Key','S','VARCHAR2',50,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (271,2,'ACTION',' Action','S','VARCHAR2',25,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (272,2,'ACTION',' Action','S','VARCHAR2',25,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (273,2,'ACTION',' Action','S','VARCHAR2',25,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (274,2,'ACTION',' Action','S','VARCHAR2',25,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (275,3,'BATCH_ID','Batch ID','N','NUMBER',22,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (276,3,'BATCH_ID','Batch ID','N','NUMBER',22,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (277,3,'BATCH_ID','Batch ID','N','NUMBER',22,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (278,3,'BATCH_ID','Batch ID','N','NUMBER',22,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (279,4,'INTERFACE_SOURCE_CODE','Import Source','N','VARCHAR2',25,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (280,4,'INTERFACE_SOURCE_CODE','Import Source','N','VARCHAR2',25,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (281,4,'INTERFACE_SOURCE_CODE','Import Source','N','VARCHAR2',25,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (282,4,'INTERFACE_SOURCE_CODE','Import Source','N','VARCHAR2',25,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (283,5,'APPROVAL_ACTION','Approval Action','N','VARCHAR2',25,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (284,5,'APPROVAL_ACTION','Approval Action','N','VARCHAR2',25,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (285,5,'APPROVAL_ACTION','Approval Action','N','VARCHAR2',25,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (286,5,'APPROVAL_ACTION','Approval Action','N','VARCHAR2',25,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (287,6,'DOCUMENT_NUM','Agreement','N','VARCHAR2',30,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (288,6,'DOCUMENT_NUM','Agreement','N','VARCHAR2',30,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (289,6,'DOCUMENT_NUM','Agreement','N','VARCHAR2',30,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (290,6,'DOCUMENT_NUM','Agreement','N','VARCHAR2',30,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (291,7,'DOCUMENT_TYPE_CODE','Document Type Code','N','VARCHAR2',25,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (292,7,'DOCUMENT_TYPE_CODE','Document Type Code','N','VARCHAR2',25,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (293,7,'DOCUMENT_TYPE_CODE','Document Type Code','N','VARCHAR2',25,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (294,7,'DOCUMENT_TYPE_CODE','Document Type Code','N','VARCHAR2',25,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (295,8,'STYLE_DISPLAY_NAME','Style','N','VARCHAR2',240,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (296,8,'STYLE_DISPLAY_NAME','Style','N','VARCHAR2',240,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (297,8,'STYLE_DISPLAY_NAME','Style','N','VARCHAR2',240,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (298,8,'STYLE_DISPLAY_NAME','Style','N','VARCHAR2',240,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (299,9,'PRC_BU_NAME','Procurement BU','N','VARCHAR2',240,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (300,9,'PRC_BU_NAME','Procurement BU','N','VARCHAR2',240,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (301,9,'PRC_BU_NAME','Procurement BU','N','VARCHAR2',240,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (302,9,'PRC_BU_NAME','Procurement BU','N','VARCHAR2',240,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (303,10,'AGENT_NAME','Buyer','N','VARCHAR2',2000,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (304,10,'AGENT_NAME','Buyer','N','VARCHAR2',2000,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (305,10,'AGENT_NAME','Buyer','N','VARCHAR2',2000,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (306,10,'AGENT_NAME','Buyer','N','VARCHAR2',2000,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (307,11,'CURRENCY_CODE','Currency Code','N','VARCHAR2',15,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (308,11,'CURRENCY_CODE','Currency Code','N','VARCHAR2',15,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (309,11,'CURRENCY_CODE','Currency Code','N','VARCHAR2',15,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (310,11,'CURRENCY_CODE','Currency Code','N','VARCHAR2',15,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (311,12,'COMMENTS','Description','N','VARCHAR2',240,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (312,12,'COMMENTS','Description','N','VARCHAR2',240,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (313,12,'COMMENTS','Description','N','VARCHAR2',240,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (314,12,'COMMENTS','Description','N','VARCHAR2',240,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (315,13,'VENDOR_NAME','Supplier','N','VARCHAR2',360,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (316,13,'VENDOR_NAME','Supplier','N','VARCHAR2',360,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (317,13,'VENDOR_NAME','Supplier','N','VARCHAR2',360,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (318,13,'VENDOR_NAME','Supplier','N','VARCHAR2',360,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (319,14,'VENDOR_NUM','Supplier Number','N','VARCHAR2',30,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (320,14,'VENDOR_NUM','Supplier Number','N','VARCHAR2',30,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (321,14,'VENDOR_NUM','Supplier Number','N','VARCHAR2',30,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (322,14,'VENDOR_NUM','Supplier Number','N','VARCHAR2',30,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (323,15,'VENDOR_SITE_CODE','Supplier Site','N','VARCHAR2',15,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (324,15,'VENDOR_SITE_CODE','Supplier Site','N','VARCHAR2',15,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (325,15,'VENDOR_SITE_CODE','Supplier Site','N','VARCHAR2',15,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (326,15,'VENDOR_SITE_CODE','Supplier Site','N','VARCHAR2',15,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (327,16,'VENDOR_CONTACT','Supplier Contact','N','VARCHAR2',360,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (328,16,'VENDOR_CONTACT','Supplier Contact','N','VARCHAR2',360,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (329,16,'VENDOR_CONTACT','Supplier Contact','N','VARCHAR2',360,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (330,16,'VENDOR_CONTACT','Supplier Contact','N','VARCHAR2',360,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (331,17,'VENDOR_DOC_NUM','Supplier Order','N','VARCHAR2',25,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (332,17,'VENDOR_DOC_NUM','Supplier Order','N','VARCHAR2',25,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (333,17,'VENDOR_DOC_NUM','Supplier Order','N','VARCHAR2',25,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (334,17,'VENDOR_DOC_NUM','Supplier Order','N','VARCHAR2',25,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (335,18,'FOB','Fob','N','VARCHAR2',30,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (336,18,'FOB','Fob','N','VARCHAR2',30,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (337,18,'FOB','Fob','N','VARCHAR2',30,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (338,18,'FOB','Fob','N','VARCHAR2',30,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (339,19,'FREIGHT_CARRIER','Carrier','N','VARCHAR2',360,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (340,19,'FREIGHT_CARRIER','Carrier','N','VARCHAR2',360,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (341,19,'FREIGHT_CARRIER','Carrier','N','VARCHAR2',360,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (342,19,'FREIGHT_CARRIER','Carrier','N','VARCHAR2',360,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (343,20,'FREIGHT_TERMS','Freight Terms','N','VARCHAR2',30,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (344,20,'FREIGHT_TERMS','Freight Terms','N','VARCHAR2',30,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (345,20,'FREIGHT_TERMS','Freight Terms','N','VARCHAR2',30,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (346,20,'FREIGHT_TERMS','Freight Terms','N','VARCHAR2',30,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (347,21,'PAY_ON_CODE','Pay On Code','N','VARCHAR2',25,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (348,21,'PAY_ON_CODE','Pay On Code','N','VARCHAR2',25,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (349,21,'PAY_ON_CODE','Pay On Code','N','VARCHAR2',25,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (350,21,'PAY_ON_CODE','Pay On Code','N','VARCHAR2',25,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (351,22,'PAYMENT_TERMS','Payment Terms','N','VARCHAR2',50,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (352,22,'PAYMENT_TERMS','Payment Terms','N','VARCHAR2',50,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (353,22,'PAYMENT_TERMS','Payment Terms','N','VARCHAR2',50,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (354,22,'PAYMENT_TERMS','Payment Terms','N','VARCHAR2',50,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (355,23,'ORIGINATOR_ROLE','Initiating Party','N','VARCHAR2',25,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (356,23,'ORIGINATOR_ROLE','Initiating Party','N','VARCHAR2',25,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (357,23,'ORIGINATOR_ROLE','Initiating Party','N','VARCHAR2',25,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (358,23,'ORIGINATOR_ROLE','Initiating Party','N','VARCHAR2',25,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (359,24,'CHANGE_ORDER_DESC','Change Order Description','N','VARCHAR2',2000,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (360,24,'CHANGE_ORDER_DESC','Change Order Description','N','VARCHAR2',2000,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (361,24,'CHANGE_ORDER_DESC','Change Order Description','N','VARCHAR2',2000,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (362,24,'CHANGE_ORDER_DESC','Change Order Description','N','VARCHAR2',2000,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (363,25,'ACCEPTANCE_REQUIRED_FLAG','Required Acknowledgment','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (364,25,'ACCEPTANCE_REQUIRED_FLAG','Required Acknowledgment','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (365,25,'ACCEPTANCE_REQUIRED_FLAG','Required Acknowledgment','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (366,25,'ACCEPTANCE_REQUIRED_FLAG','Required Acknowledgment','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (367,26,'ACCEPTANCE_WITHIN_DAYS','Acknowledge Within (Days)','N','NUMBER',22,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (368,26,'ACCEPTANCE_WITHIN_DAYS','Acknowledge Within (Days)','N','NUMBER',22,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (369,26,'ACCEPTANCE_WITHIN_DAYS','Acknowledge Within (Days)','N','NUMBER',22,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (370,26,'ACCEPTANCE_WITHIN_DAYS','Acknowledge Within (Days)','N','NUMBER',22,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (371,27,'SUPPLIER_NOTIF_METHOD','Communication Method','N','VARCHAR2',25,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (372,27,'SUPPLIER_NOTIF_METHOD','Communication Method','N','VARCHAR2',25,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (373,27,'SUPPLIER_NOTIF_METHOD','Communication Method','N','VARCHAR2',25,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (374,27,'SUPPLIER_NOTIF_METHOD','Communication Method','N','VARCHAR2',25,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (375,28,'FAX','Fax','N','VARCHAR2',60,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (376,28,'FAX','Fax','N','VARCHAR2',60,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (377,28,'FAX','Fax','N','VARCHAR2',60,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (378,28,'FAX','Fax','N','VARCHAR2',60,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (379,29,'EMAIL_ADDRESS','E-mail','N','VARCHAR2',2000,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (380,29,'EMAIL_ADDRESS','E-mail','N','VARCHAR2',2000,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (381,29,'EMAIL_ADDRESS','E-mail','N','VARCHAR2',2000,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (382,29,'EMAIL_ADDRESS','E-mail','N','VARCHAR2',2000,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (383,30,'CONFIRMING_ORDER_FLAG','Confirming order','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (384,30,'CONFIRMING_ORDER_FLAG','Confirming order','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (385,30,'CONFIRMING_ORDER_FLAG','Confirming order','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (386,30,'CONFIRMING_ORDER_FLAG','Confirming order','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (387,31,'AMOUNT_AGREED','Agreement Amount','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (388,31,'AMOUNT_AGREED','Agreement Amount','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (389,31,'AMOUNT_AGREED','Agreement Amount','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (390,31,'AMOUNT_AGREED','Agreement Amount','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (391,32,'AMOUNT_LIMIT','Amount Limit','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (392,32,'AMOUNT_LIMIT','Amount Limit','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (393,32,'AMOUNT_LIMIT','Amount Limit','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (394,32,'AMOUNT_LIMIT','Amount Limit','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (395,33,'MIN_RELEASE_AMOUNT','Minimum Release Amount','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (396,33,'MIN_RELEASE_AMOUNT','Minimum Release Amount','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (397,33,'MIN_RELEASE_AMOUNT','Minimum Release Amount','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (398,33,'MIN_RELEASE_AMOUNT','Minimum Release Amount','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (399,34,'EFFECTIVE_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (400,34,'EFFECTIVE_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (401,34,'EFFECTIVE_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (402,34,'EFFECTIVE_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (403,35,'EXPIRATION_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (404,35,'EXPIRATION_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (405,35,'EXPIRATION_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (406,35,'EXPIRATION_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (407,36,'NOTE_TO_VENDOR','Note to Supplier','N','VARCHAR2',1000,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (408,36,'NOTE_TO_VENDOR','Note to Supplier','N','VARCHAR2',1000,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (409,36,'NOTE_TO_VENDOR','Note to Supplier','N','VARCHAR2',1000,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (410,36,'NOTE_TO_VENDOR','Note to Supplier','N','VARCHAR2',1000,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (411,37,'NOTE_TO_RECEIVER','Note to Receiver','N','VARCHAR2',1000,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (412,37,'NOTE_TO_RECEIVER','Note to Receiver','N','VARCHAR2',1000,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (413,37,'NOTE_TO_RECEIVER','Note to Receiver','N','VARCHAR2',1000,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (414,37,'NOTE_TO_RECEIVER','Note to Receiver','N','VARCHAR2',1000,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (415,38,'GENERATE_ORDERS_AUTOMATIC','Automatically generate orders','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (416,38,'GENERATE_ORDERS_AUTOMATIC','Automatically generate orders','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (417,38,'GENERATE_ORDERS_AUTOMATIC','Automatically generate orders','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (418,38,'GENERATE_ORDERS_AUTOMATIC','Automatically generate orders','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (419,39,'SUBMIT_APPROVAL_AUTOMATIC','Automatically submit for approval','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (420,39,'SUBMIT_APPROVAL_AUTOMATIC','Automatically submit for approval','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (421,39,'SUBMIT_APPROVAL_AUTOMATIC','Automatically submit for approval','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (422,39,'SUBMIT_APPROVAL_AUTOMATIC','Automatically submit for approval','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (423,40,'GROUP_REQUISITIONS','Group requisitions','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (424,40,'GROUP_REQUISITIONS','Group requisitions','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (425,40,'GROUP_REQUISITIONS','Group requisitions','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (426,40,'GROUP_REQUISITIONS','Group requisitions','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (427,41,'GROUP_REQUISITION_LINES','Group requisition lines','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (428,41,'GROUP_REQUISITION_LINES','Group requisition lines','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (429,41,'GROUP_REQUISITION_LINES','Group requisition lines','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (430,41,'GROUP_REQUISITION_LINES','Group requisition lines','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (431,42,'USE_SHIP_TO','Use ship-to organization and location','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (432,42,'USE_SHIP_TO','Use ship-to organization and location','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (433,42,'USE_SHIP_TO','Use ship-to organization and location','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (434,42,'USE_SHIP_TO','Use ship-to organization and location','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (435,43,'USE_NEED_BY_DATE','Use need-by date','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (436,43,'USE_NEED_BY_DATE','Use need-by date','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (437,43,'USE_NEED_BY_DATE','Use need-by date','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (438,43,'USE_NEED_BY_DATE','Use need-by date','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (439,44,'CAT_ADMIN_AUTH_ENABLED_FLAG','Catalog Administrator Authoring','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (440,44,'CAT_ADMIN_AUTH_ENABLED_FLAG','Catalog Administrator Authoring','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (441,44,'CAT_ADMIN_AUTH_ENABLED_FLAG','Catalog Administrator Authoring','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (442,44,'CAT_ADMIN_AUTH_ENABLED_FLAG','Catalog Administrator Authoring','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (443,45,'RETRO_PRICE_APPLY_UPDATES_FLAG','Apply price updates to existing orders','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (444,45,'RETRO_PRICE_APPLY_UPDATES_FLAG','Apply price updates to existing orders','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (445,45,'RETRO_PRICE_APPLY_UPDATES_FLAG','Apply price updates to existing orders','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (446,45,'RETRO_PRICE_APPLY_UPDATES_FLAG','Apply price updates to existing orders','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (447,46,'RETRO_PRICE_COMM_UPDATES_FLAG','Communicate price updates','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (448,46,'RETRO_PRICE_COMM_UPDATES_FLAG','Communicate price updates','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (449,46,'RETRO_PRICE_COMM_UPDATES_FLAG','Communicate price updates','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (450,46,'RETRO_PRICE_COMM_UPDATES_FLAG','Communicate price updates','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (451,47,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (452,47,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (453,47,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (454,47,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (455,48,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (456,48,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (457,48,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (458,48,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (459,49,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (460,49,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (461,49,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (462,49,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (463,50,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (464,50,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (465,50,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (466,50,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (467,51,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (468,51,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (469,51,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (470,51,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (471,52,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (472,52,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (473,52,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (474,52,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (475,53,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (476,53,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (477,53,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (478,53,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (479,54,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (480,54,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (481,54,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (482,54,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (483,55,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (484,55,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (485,55,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (486,55,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (487,56,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (488,56,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (489,56,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (490,56,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (491,57,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (492,57,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (493,57,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (494,57,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (495,58,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (496,58,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (497,58,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (498,58,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (499,59,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (500,59,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (501,59,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (502,59,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (503,60,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (504,60,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (505,60,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (506,60,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (507,61,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (508,61,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (509,61,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (510,61,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (511,62,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (512,62,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (513,62,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (514,62,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (515,63,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (516,63,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (517,63,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (518,63,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (519,64,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (520,64,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (521,64,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (522,64,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (523,65,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (524,65,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (525,65,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (526,65,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (527,66,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (528,66,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (529,66,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (530,66,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (531,67,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (532,67,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (533,67,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (534,67,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (535,68,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (536,68,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (537,68,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (538,68,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (539,69,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (540,69,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (541,69,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (542,69,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (543,70,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (544,70,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (545,70,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (546,70,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (547,71,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (548,71,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (549,71,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (550,71,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (551,72,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (552,72,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (553,72,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (554,72,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (555,73,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (556,73,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (557,73,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (558,73,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (559,74,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (560,74,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (561,74,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (562,74,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (563,75,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (564,75,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (565,75,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (566,75,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (567,76,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (568,76,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (569,76,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (570,76,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (571,77,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (572,77,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (573,77,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (574,77,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (575,78,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (576,78,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (577,78,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (578,78,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (579,79,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (580,79,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (581,79,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (582,79,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (583,80,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (584,80,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (585,80,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (586,80,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (587,81,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (588,81,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (589,81,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (590,81,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (591,82,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (592,82,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (593,82,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (594,82,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (595,83,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (596,83,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (597,83,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (598,83,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (599,84,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (600,84,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (601,84,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (602,84,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (603,85,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (604,85,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (605,85,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (606,85,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (607,86,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (608,86,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (609,86,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (610,86,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (611,87,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (612,87,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (613,87,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (614,87,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (615,88,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (616,88,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (617,88,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (618,88,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (619,89,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (620,89,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (621,89,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (622,89,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (623,90,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (624,90,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (625,90,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (626,90,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (627,91,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (628,91,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (629,91,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (630,91,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (631,92,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (632,92,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (633,92,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (634,92,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (635,93,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (636,93,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (637,93,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (638,93,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (639,94,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (640,94,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (641,94,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (642,94,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (643,95,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (644,95,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (645,95,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (646,95,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (647,96,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (648,96,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (649,96,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (650,96,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (651,97,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (652,97,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (653,97,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (654,97,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (655,98,'AGENT_EMAIL_ADDRESS','Buyer E-mail','N','VARCHAR2',240,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (656,98,'AGENT_EMAIL_ADDRESS','Buyer E-mail','N','VARCHAR2',240,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (657,98,'AGENT_EMAIL_ADDRESS','Buyer E-mail','N','VARCHAR2',240,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (658,98,'AGENT_EMAIL_ADDRESS','Buyer E-mail','N','VARCHAR2',240,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (659,99,'MODE_OF_TRANSPORT','Mode of Transport','N','VARCHAR2',80,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (660,99,'MODE_OF_TRANSPORT','Mode of Transport','N','VARCHAR2',80,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (661,99,'MODE_OF_TRANSPORT','Mode of Transport','N','VARCHAR2',80,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (662,99,'MODE_OF_TRANSPORT','Mode of Transport','N','VARCHAR2',80,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (663,100,'SERVICE_LEVEL','Service Level','N','VARCHAR2',80,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (664,100,'SERVICE_LEVEL','Service Level','N','VARCHAR2',80,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (665,100,'SERVICE_LEVEL','Service Level','N','VARCHAR2',80,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (666,100,'SERVICE_LEVEL','Service Level','N','VARCHAR2',80,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (667,101,'AGING_PERIOD_DAYS','Aging Period Days','N','NUMBER',22,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (668,101,'AGING_PERIOD_DAYS','Aging Period Days','N','NUMBER',22,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (669,101,'AGING_PERIOD_DAYS','Aging Period Days','N','NUMBER',22,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (670,101,'AGING_PERIOD_DAYS','Aging Period Days','N','NUMBER',22,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (671,102,'AGING_ONSET_POINT','Aging Onset Point','N','VARCHAR2',30,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (672,102,'AGING_ONSET_POINT','Aging Onset Point','N','VARCHAR2',30,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (673,102,'AGING_ONSET_POINT','Aging Onset Point','N','VARCHAR2',30,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (674,102,'AGING_ONSET_POINT','Aging Onset Point','N','VARCHAR2',30,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (675,103,'CONSUMPTION_ADVICE_FREQUENCY','Consumption Advice Frequency','N','VARCHAR2',30,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (676,103,'CONSUMPTION_ADVICE_FREQUENCY','Consumption Advice Frequency','N','VARCHAR2',30,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (677,103,'CONSUMPTION_ADVICE_FREQUENCY','Consumption Advice Frequency','N','VARCHAR2',30,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (678,103,'CONSUMPTION_ADVICE_FREQUENCY','Consumption Advice Frequency','N','VARCHAR2',30,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (679,104,'CONSUMPTION_ADVICE_SUMMARY','Consumption Advice Summary','N','VARCHAR2',30,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (680,104,'CONSUMPTION_ADVICE_SUMMARY','Consumption Advice Summary','N','VARCHAR2',30,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (681,104,'CONSUMPTION_ADVICE_SUMMARY','Consumption Advice Summary','N','VARCHAR2',30,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (682,104,'CONSUMPTION_ADVICE_SUMMARY','Consumption Advice Summary','N','VARCHAR2',30,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (683,105,'DEFAULT_CONSIGNMENT_LINE_FLAG','Default line as consignment line','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (684,105,'DEFAULT_CONSIGNMENT_LINE_FLAG','Default line as consignment line','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (685,105,'DEFAULT_CONSIGNMENT_LINE_FLAG','Default line as consignment line','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (686,105,'DEFAULT_CONSIGNMENT_LINE_FLAG','Default line as consignment line','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (687,106,'PAY_ON_USE_FLAG','Pay on use','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (688,106,'PAY_ON_USE_FLAG','Pay on use','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (689,106,'PAY_ON_USE_FLAG','Pay on use','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (690,106,'PAY_ON_USE_FLAG','Pay on use','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (691,107,'BILLING_CYCLE_CLOSING_DATE','Billing Cycle Closing Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (692,107,'BILLING_CYCLE_CLOSING_DATE','Billing Cycle Closing Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (693,107,'BILLING_CYCLE_CLOSING_DATE','Billing Cycle Closing Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (694,107,'BILLING_CYCLE_CLOSING_DATE','Billing Cycle Closing Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (695,108,'CONFIGURED_ITEM_FLAG','Configuration Ordering Enabled','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (696,108,'CONFIGURED_ITEM_FLAG','Configuration Ordering Enabled','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (697,108,'CONFIGURED_ITEM_FLAG','Configuration Ordering Enabled','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (698,108,'CONFIGURED_ITEM_FLAG','Configuration Ordering Enabled','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (699,109,'USE_SALES_ORDER_NUMBER_FLAG','Use Customer Sales Order','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (700,109,'USE_SALES_ORDER_NUMBER_FLAG','Use Customer Sales Order','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (701,109,'USE_SALES_ORDER_NUMBER_FLAG','Use Customer Sales Order','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (702,109,'USE_SALES_ORDER_NUMBER_FLAG','Use Customer Sales Order','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (703,110,'BUYER_MANAGED_TRANSPORT_FLAG','Buyer Managed Transportation','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (704,110,'BUYER_MANAGED_TRANSPORT_FLAG','Buyer Managed Transportation','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (705,110,'BUYER_MANAGED_TRANSPORT_FLAG','Buyer Managed Transportation','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (706,110,'BUYER_MANAGED_TRANSPORT_FLAG','Buyer Managed Transportation','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (707,111,'ALLOW_ORDER_FROM_UNASS_SITES','Allow ordering from unassigned sites','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (708,111,'ALLOW_ORDER_FROM_UNASS_SITES','Allow ordering from unassigned sites','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (709,111,'ALLOW_ORDER_FROM_UNASS_SITES','Allow ordering from unassigned sites','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (710,111,'ALLOW_ORDER_FROM_UNASS_SITES','Allow ordering from unassigned sites','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (711,112,'OUTSIDE_PROCESS_ENABLED_FLAG','Outside Processing Enabled','N','VARCHAR2',1,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (712,112,'OUTSIDE_PROCESS_ENABLED_FLAG','Outside Processing Enabled','N','VARCHAR2',1,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (713,112,'OUTSIDE_PROCESS_ENABLED_FLAG','Outside Processing Enabled','N','VARCHAR2',1,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (714,112,'OUTSIDE_PROCESS_ENABLED_FLAG','Outside Processing Enabled','N','VARCHAR2',1,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (715,113,'MASTER_CONTRACT_NUMBER','Master Contract Number','N','VARCHAR2',120,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (716,113,'MASTER_CONTRACT_NUMBER','Master Contract Number','N','VARCHAR2',120,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (717,113,'MASTER_CONTRACT_NUMBER','Master Contract Number','N','VARCHAR2',120,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (718,113,'MASTER_CONTRACT_NUMBER','Master Contract Number','N','VARCHAR2',120,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (719,114,'MASTER_CONTRACT_TYPE','Master Contract Type','N','VARCHAR2',150,null,null,null,null,null,263,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (720,114,'MASTER_CONTRACT_TYPE','Master Contract Type','N','VARCHAR2',150,null,null,null,null,null,264,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (721,114,'MASTER_CONTRACT_TYPE','Master Contract Type','N','VARCHAR2',150,null,null,null,null,null,265,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (722,114,'MASTER_CONTRACT_TYPE','Master Contract Type','N','VARCHAR2',150,null,null,null,null,null,266,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (729,1,'INTERFACE_LINE_LOCATION_KEY',' Interface Line Location Key','S','VARCHAR2',50,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (730,1,'INTERFACE_LINE_LOCATION_KEY',' Interface Line Location Key','S','VARCHAR2',50,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (731,1,'INTERFACE_LINE_LOCATION_KEY',' Interface Line Location Key','S','VARCHAR2',50,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (732,1,'INTERFACE_LINE_LOCATION_KEY',' Interface Line Location Key','S','VARCHAR2',50,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (733,1,'INTERFACE_LINE_LOCATION_KEY',' Interface Line Location Key','S','VARCHAR2',50,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (734,2,'INTERFACE_LINE_KEY',' Interface Line Key','S','VARCHAR2',50,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (735,2,'INTERFACE_LINE_KEY',' Interface Line Key','S','VARCHAR2',50,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (736,2,'INTERFACE_LINE_KEY',' Interface Line Key','S','VARCHAR2',50,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (737,2,'INTERFACE_LINE_KEY',' Interface Line Key','S','VARCHAR2',50,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (738,2,'INTERFACE_LINE_KEY',' Interface Line Key','S','VARCHAR2',50,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (739,3,'SHIPMENT_NUM','Price Break','N','NUMBER',22,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (740,3,'SHIPMENT_NUM','Price Break','N','NUMBER',22,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (741,3,'SHIPMENT_NUM','Price Break','N','NUMBER',22,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (742,3,'SHIPMENT_NUM','Price Break','N','NUMBER',22,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (743,3,'SHIPMENT_NUM','Price Break','N','NUMBER',22,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (744,4,'SHIP_TO_LOCATION','Ship-to Location','N','VARCHAR2',60,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (745,4,'SHIP_TO_LOCATION','Ship-to Location','N','VARCHAR2',60,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (746,4,'SHIP_TO_LOCATION','Ship-to Location','N','VARCHAR2',60,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (747,4,'SHIP_TO_LOCATION','Ship-to Location','N','VARCHAR2',60,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (748,4,'SHIP_TO_LOCATION','Ship-to Location','N','VARCHAR2',60,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (749,5,'SHIP_TO_ORGANIZATION_CODE','Ship-to Organization','N','VARCHAR2',18,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (750,5,'SHIP_TO_ORGANIZATION_CODE','Ship-to Organization','N','VARCHAR2',18,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (751,5,'SHIP_TO_ORGANIZATION_CODE','Ship-to Organization','N','VARCHAR2',18,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (752,5,'SHIP_TO_ORGANIZATION_CODE','Ship-to Organization','N','VARCHAR2',18,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (753,5,'SHIP_TO_ORGANIZATION_CODE','Ship-to Organization','N','VARCHAR2',18,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (754,6,'QUANTITY','Quantity','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (755,6,'QUANTITY','Quantity','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (756,6,'QUANTITY','Quantity','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (757,6,'QUANTITY','Quantity','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (758,6,'QUANTITY','Quantity','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (759,7,'PRICE_OVERRIDE','Price','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (760,7,'PRICE_OVERRIDE','Price','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (761,7,'PRICE_OVERRIDE','Price','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (762,7,'PRICE_OVERRIDE','Price','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (763,7,'PRICE_OVERRIDE','Price','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (764,8,'PRICE_DISCOUNT','Discount (%)','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (765,8,'PRICE_DISCOUNT','Discount (%)','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (766,8,'PRICE_DISCOUNT','Discount (%)','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (767,8,'PRICE_DISCOUNT','Discount (%)','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (768,8,'PRICE_DISCOUNT','Discount (%)','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (769,9,'START_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (770,9,'START_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (771,9,'START_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (772,9,'START_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (773,9,'START_DATE','Start Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (774,10,'END_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (775,10,'END_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (776,10,'END_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (777,10,'END_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (778,10,'END_DATE','End Date','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (779,11,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (780,11,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (781,11,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (782,11,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (783,11,'ATTRIBUTE_CATEGORY','ATTRIBUTE_CATEGORY','N','VARCHAR2',30,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (784,12,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (785,12,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (786,12,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (787,12,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (788,12,'ATTRIBUTE1','ATTRIBUTE1','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (789,13,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (790,13,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (791,13,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (792,13,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (793,13,'ATTRIBUTE2','ATTRIBUTE2','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (794,14,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (795,14,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (796,14,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (797,14,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (798,14,'ATTRIBUTE3','ATTRIBUTE3','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (799,15,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (800,15,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (801,15,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (802,15,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (803,15,'ATTRIBUTE4','ATTRIBUTE4','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (804,16,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (805,16,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (806,16,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (807,16,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (808,16,'ATTRIBUTE5','ATTRIBUTE5','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (809,17,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (810,17,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (811,17,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (812,17,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (813,17,'ATTRIBUTE6','ATTRIBUTE6','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (814,18,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (815,18,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (816,18,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (817,18,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (818,18,'ATTRIBUTE7','ATTRIBUTE7','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (819,19,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (820,19,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (821,19,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (822,19,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (823,19,'ATTRIBUTE8','ATTRIBUTE8','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (824,20,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (825,20,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (826,20,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (827,20,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (828,20,'ATTRIBUTE9','ATTRIBUTE9','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (829,21,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (830,21,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (831,21,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (832,21,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (833,21,'ATTRIBUTE10','ATTRIBUTE10','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (834,22,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (835,22,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (836,22,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (837,22,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (838,22,'ATTRIBUTE11','ATTRIBUTE11','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (839,23,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (840,23,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (841,23,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (842,23,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (843,23,'ATTRIBUTE12','ATTRIBUTE12','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (844,24,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (845,24,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (846,24,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (847,24,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (848,24,'ATTRIBUTE13','ATTRIBUTE13','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (849,25,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (850,25,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (851,25,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (852,25,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (853,25,'ATTRIBUTE14','ATTRIBUTE14','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (854,26,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (855,26,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (856,26,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (857,26,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (858,26,'ATTRIBUTE15','ATTRIBUTE15','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (859,27,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (860,27,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (861,27,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (862,27,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (863,27,'ATTRIBUTE16','ATTRIBUTE16','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (864,28,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (865,28,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (866,28,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (867,28,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (868,28,'ATTRIBUTE17','ATTRIBUTE17','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (869,29,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (870,29,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (871,29,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (872,29,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (873,29,'ATTRIBUTE18','ATTRIBUTE18','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (874,30,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (875,30,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (876,30,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (877,30,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (878,30,'ATTRIBUTE19','ATTRIBUTE19','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (879,31,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (880,31,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (881,31,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (882,31,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (883,31,'ATTRIBUTE20','ATTRIBUTE20','N','VARCHAR2',150,null,null,null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (884,32,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (885,32,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (886,32,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (887,32,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (888,32,'ATTRIBUTE_DATE1','ATTRIBUTE_DATE1','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (889,33,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (890,33,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (891,33,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (892,33,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (893,33,'ATTRIBUTE_DATE2','ATTRIBUTE_DATE2','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (894,34,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (895,34,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (896,34,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (897,34,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (898,34,'ATTRIBUTE_DATE3','ATTRIBUTE_DATE3','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (899,35,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (900,35,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (901,35,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (902,35,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (903,35,'ATTRIBUTE_DATE4','ATTRIBUTE_DATE4','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (904,36,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (905,36,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (906,36,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (907,36,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (908,36,'ATTRIBUTE_DATE5','ATTRIBUTE_DATE5','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (909,37,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (910,37,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (911,37,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (912,37,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (913,37,'ATTRIBUTE_DATE6','ATTRIBUTE_DATE6','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (914,38,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (915,38,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (916,38,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (917,38,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (918,38,'ATTRIBUTE_DATE7','ATTRIBUTE_DATE7','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (919,39,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (920,39,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (921,39,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (922,39,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (923,39,'ATTRIBUTE_DATE8','ATTRIBUTE_DATE8','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (924,40,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (925,40,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (926,40,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (927,40,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (928,40,'ATTRIBUTE_DATE9','ATTRIBUTE_DATE9','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (929,41,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (930,41,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (931,41,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (932,41,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (933,41,'ATTRIBUTE_DATE10','ATTRIBUTE_DATE10','N','DATE',null,null,'yyyy-MM-dd',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (934,42,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (935,42,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (936,42,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (937,42,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (938,42,'ATTRIBUTE_NUMBER1','ATTRIBUTE_NUMBER1','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (939,43,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (940,43,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (941,43,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (942,43,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (943,43,'ATTRIBUTE_NUMBER2','ATTRIBUTE_NUMBER2','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (944,44,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (945,44,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (946,44,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (947,44,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (948,44,'ATTRIBUTE_NUMBER3','ATTRIBUTE_NUMBER3','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (949,45,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (950,45,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (951,45,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (952,45,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (953,45,'ATTRIBUTE_NUMBER4','ATTRIBUTE_NUMBER4','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (954,46,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (955,46,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (956,46,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (957,46,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (958,46,'ATTRIBUTE_NUMBER5','ATTRIBUTE_NUMBER5','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (959,47,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (960,47,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (961,47,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (962,47,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (963,47,'ATTRIBUTE_NUMBER6','ATTRIBUTE_NUMBER6','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (964,48,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (965,48,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (966,48,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (967,48,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (968,48,'ATTRIBUTE_NUMBER7','ATTRIBUTE_NUMBER7','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (969,49,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (970,49,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (971,49,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (972,49,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (973,49,'ATTRIBUTE_NUMBER8','ATTRIBUTE_NUMBER8','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (974,50,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (975,50,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (976,50,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (977,50,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (978,50,'ATTRIBUTE_NUMBER9','ATTRIBUTE_NUMBER9','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (979,51,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (980,51,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (981,51,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (982,51,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (983,51,'ATTRIBUTE_NUMBER10','ATTRIBUTE_NUMBER10','N','NUMBER',22,4,null,'###0.##','''','.',728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (984,52,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (985,52,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (986,52,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (987,52,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (988,52,'ATTRIBUTE_TIMESTAMP1','ATTRIBUTE_TIMESTAMP1','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (989,53,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (990,53,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (991,53,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (992,53,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (993,53,'ATTRIBUTE_TIMESTAMP2','ATTRIBUTE_TIMESTAMP2','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (994,54,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (995,54,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (996,54,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (997,54,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (998,54,'ATTRIBUTE_TIMESTAMP3','ATTRIBUTE_TIMESTAMP3','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (999,55,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1000,55,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1001,55,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1002,55,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1003,55,'ATTRIBUTE_TIMESTAMP4','ATTRIBUTE_TIMESTAMP4','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1004,56,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1005,56,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1006,56,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1007,56,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1008,56,'ATTRIBUTE_TIMESTAMP5','ATTRIBUTE_TIMESTAMP5','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1009,57,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1010,57,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1011,57,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1012,57,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1013,57,'ATTRIBUTE_TIMESTAMP6','ATTRIBUTE_TIMESTAMP6','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1014,58,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1015,58,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1016,58,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1017,58,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1018,58,'ATTRIBUTE_TIMESTAMP7','ATTRIBUTE_TIMESTAMP7','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1019,59,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1020,59,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1021,59,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1022,59,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1023,59,'ATTRIBUTE_TIMESTAMP8','ATTRIBUTE_TIMESTAMP8','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1024,60,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1025,60,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1026,60,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1027,60,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1028,60,'ATTRIBUTE_TIMESTAMP9','ATTRIBUTE_TIMESTAMP9','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1029,61,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,724,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1030,61,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,725,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1031,61,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,726,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1032,61,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,727,null,null,null,null,null);
Insert into FAI.FAI_COLUMN_EXPORT (OID,COLUMN_IDX,COLUMN_NAME_ORACLE,COLUMN_NAME_EXPORT,REQUIRED,SQL_TYPE,SQL_TYPE_LEN,SQL_TYPE_SCALE,DATE_FORMAT_PATTERN,NUMB_FORMAT_PATTERN,NUMB_FORMAT_GRPCH,NUMB_FORMAT_DECCH,OID_TABLEEXPORT,TEXT_FORMAT_TYPE,TEXT_FORMAT_SEARCH,TEXT_FORMAT_REPLACE,INDEX_NAME_ORACLE,INDEX_NAME_TABLESPACE) values (1033,61,'ATTRIBUTE_TIMESTAMP10','ATTRIBUTE_TIMESTAMP10','N','DATE',null,null,'yyyy-MM-dd HH:mm:ss',null,null,null,728,null,null,null,null,null);
REM INSERTING into FAI.FAI_DISPONIBILITA_REQ_TEMP
SET DEFINE OFF;
REM INSERTING into FAI.FAI_DISPONIBILITA_RES_TEMP
SET DEFINE OFF;
REM INSERTING into FAI.FAI_DISPONIBILITA_TEMP
SET DEFINE OFF;
REM INSERTING into FAI.FAI_EXPORT_CONFIG
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FARMACLICK_FORNITORE
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FARMACO
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FARMADATI_DDL
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FARMADATI_TABLE_TO_IGNORE
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FARMADATI_WS
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FARMADATI_WS_CONFIG
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FARMADATI_WS_STATUS
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FORNITORE
SET DEFINE OFF;
Insert into FAI.FAI_FORNITORE (OID,CODICE,CODICE_FARMACLICK,SUPPLIERSERVICE_CLASS_NAME) values (9,'DEMO03','DEMO03','fai.broker.supplier.driver.fake.FakeSupplierService');
Insert into FAI.FAI_FORNITORE (OID,CODICE,CODICE_FARMACLICK,SUPPLIERSERVICE_CLASS_NAME) values (11,'FaiRnd0001','FaiRnd0001','fai.broker.supplier.driver.fake.FakeSupplierService');
Insert into FAI.FAI_FORNITORE (OID,CODICE,CODICE_FARMACLICK,SUPPLIERSERVICE_CLASS_NAME) values (12,'FaiRnd0002','FaiRnd0002','fai.broker.supplier.driver.fake.FakeSupplierService');
REM INSERTING into FAI.FAI_FORNITORE_FARMACO
SET DEFINE OFF;
REM INSERTING into FAI.FAI_FORNITORE_PROPERTY
SET DEFINE OFF;
REM INSERTING into FAI.FAI_GENERIC_TASK
SET DEFINE OFF;
REM INSERTING into FAI.FAI_GENERIC_TASK_PROPERTY
SET DEFINE OFF;
REM INSERTING into FAI.FAI_GROUP_EXPORT
SET DEFINE OFF;
Insert into FAI.FAI_GROUP_EXPORT (OID,GROUP_IDX,GROUP_NAME_EXPORT) values (15,1,'PoImportBlanketAgreements');
Insert into FAI.FAI_GROUP_EXPORT (OID,GROUP_IDX,GROUP_NAME_EXPORT) values (16,1,'PoImportBlanketAgreements');
Insert into FAI.FAI_GROUP_EXPORT (OID,GROUP_IDX,GROUP_NAME_EXPORT) values (231,1,'PoImportBlanketAgreements');
Insert into FAI.FAI_GROUP_EXPORT (OID,GROUP_IDX,GROUP_NAME_EXPORT) values (262,1,'PoImportBlanketAgreements');
Insert into FAI.FAI_GROUP_EXPORT (OID,GROUP_IDX,GROUP_NAME_EXPORT) values (723,1,'PoImportBlanketAgreements');
REM INSERTING into FAI.FAI_IMPORT_CONFIG
SET DEFINE OFF;
Insert into FAI.FAI_IMPORT_CONFIG (OID,ACRONYM,DESCR,SERVICE_LOGIN,SERVICE_PASS,SERVICE_PASS_ENCR,SERVICE_QUERY_TIME,SERVICE_QUERY_SMTWTFS,SERVICE_QUERY_URL,SERVICE_RESUME_PREV_SESS,LAST_RUN_START_TS,LAST_RUN_END_TS,LAST_RUN_TYPE,LAST_RUN_DONE,LAST_RUN_DESCR,CLASS_NAME,SERVICE_QUERY_ZIPPED_CONTENT) values (13,'FARMADATI',null,'BDF250035w','u/w6SSKTNwa0d+B+9cuMJ5FzksCyuXgnOrh0V5qbsSpyEbe86mpMoxcPEJKOOUnklFZqVJ9sK8oHh4A+AMwAxg==','S',to_date('01-01-70','DD-MM-RR'),'CCCCCCC','https://webservices.farmadati.it/WS2S/FarmadatiItaliaWebServicesM2.svc','S',null,null,null,null,null,'farmadati.task.FarmadatiDataCollector','S');
Insert into FAI.FAI_IMPORT_CONFIG (OID,ACRONYM,DESCR,SERVICE_LOGIN,SERVICE_PASS,SERVICE_PASS_ENCR,SERVICE_QUERY_TIME,SERVICE_QUERY_SMTWTFS,SERVICE_QUERY_URL,SERVICE_RESUME_PREV_SESS,LAST_RUN_START_TS,LAST_RUN_END_TS,LAST_RUN_TYPE,LAST_RUN_DONE,LAST_RUN_DESCR,CLASS_NAME,SERVICE_QUERY_ZIPPED_CONTENT) values (14,'FARMACLICK',null,'FCK00042794','KLyQD+K31DEc5HDIasdgiat6FQHuUh7Gr9cotQblUNHvQZTKLUooUm861qhbEZHYJ9WS1lWd8uZUbl9seWFoMQ==','S',to_date('01-01-70','DD-MM-RR'),'CCCCCCC','https://secure.infarmaclick.com/public_server_pro/Farmaclick2010001FCKLogin','S',null,to_date('08-11-21','DD-MM-RR'),'C','S',null,'farmaclick.task.FarmaclickDataCollector','S');
REM INSERTING into FAI.FAI_ITEM_STATUS
SET DEFINE OFF;
Insert into FAI.FAI_ITEM_STATUS (OID,ACRONYM,DESCR) values (1,'TO PROCESS','TO PROCESS');
Insert into FAI.FAI_ITEM_STATUS (OID,ACRONYM,DESCR) values (2,'PROCESSING','PROCESSING');
Insert into FAI.FAI_ITEM_STATUS (OID,ACRONYM,DESCR) values (3,'PROCESSED','PROCESSED');
Insert into FAI.FAI_ITEM_STATUS (OID,ACRONYM,DESCR) values (4,'ERROR','ERROR');
Insert into FAI.FAI_ITEM_STATUS (OID,ACRONYM,DESCR) values (5,'SUSPENDED','SUSPENDED');
Insert into FAI.FAI_ITEM_STATUS (OID,ACRONYM,DESCR) values (6,'TO APPROVE','TO APPROVE');
Insert into FAI.FAI_ITEM_STATUS (OID,ACRONYM,DESCR) values (7,'APPROVED','APPROVED');
Insert into FAI.FAI_ITEM_STATUS (OID,ACRONYM,DESCR) values (8,'VOIDED','VOIDED');
REM INSERTING into FAI.FAI_LISTINI_DISPONIBILITA_TEMP
SET DEFINE OFF;
REM INSERTING into FAI.FAI_LOG
SET DEFINE OFF;
REM INSERTING into FAI.FAI_MAGAZZINO
SET DEFINE OFF;
Insert into FAI.FAI_MAGAZZINO (OID,ACRONYM,DESCR,COMUNE,PROVINCIA,REGIONE,CAP,INDIRIZZO,SUPPLIERSERVICE_CLASS_NAME) values (1,'UPS','UPS di Formello','Formello','RM','Lazio',null,null,'fai.broker.supplier.driver.fake.FakeSupplierService');
Insert into FAI.FAI_MAGAZZINO (OID,ACRONYM,DESCR,COMUNE,PROVINCIA,REGIONE,CAP,INDIRIZZO,SUPPLIERSERVICE_CLASS_NAME) values (5,'LORETO','Magazzino Loreto','Casoria','NA','Campania',null,null,'fai.broker.supplier.driver.fake.FakeSupplierService');
REM INSERTING into FAI.FAI_MAGAZZINO_PROPERTY
SET DEFINE OFF;
REM INSERTING into FAI.FAI_MAGAZZINO_TIPOFARMACO
SET DEFINE OFF;
Insert into FAI.FAI_MAGAZZINO_TIPOFARMACO (OID,OID_MAGAZZINO,OID_TIPOFARMACO,CONTEXT) values (2,1,2,'FAI_ORDINE_IN*');
Insert into FAI.FAI_MAGAZZINO_TIPOFARMACO (OID,OID_MAGAZZINO,OID_TIPOFARMACO,CONTEXT) values (3,1,1,null);
Insert into FAI.FAI_MAGAZZINO_TIPOFARMACO (OID,OID_MAGAZZINO,OID_TIPOFARMACO,CONTEXT) values (4,1,2,null);
Insert into FAI.FAI_MAGAZZINO_TIPOFARMACO (OID,OID_MAGAZZINO,OID_TIPOFARMACO,CONTEXT) values (6,5,1,'FAI_ORDINE_IN*');
Insert into FAI.FAI_MAGAZZINO_TIPOFARMACO (OID,OID_MAGAZZINO,OID_TIPOFARMACO,CONTEXT) values (7,5,1,null);
Insert into FAI.FAI_MAGAZZINO_TIPOFARMACO (OID,OID_MAGAZZINO,OID_TIPOFARMACO,CONTEXT) values (8,5,2,null);
REM INSERTING into FAI.FAI_NO_GAP_SEQUENCE
SET DEFINE OFF;
REM INSERTING into FAI.FAI_ORDINE_IN
SET DEFINE OFF;
REM INSERTING into FAI.FAI_ORDINE_IN_COLLECTION
SET DEFINE OFF;
REM INSERTING into FAI.FAI_ORDINE_IN_RIGA_DETTAGLIO
SET DEFINE OFF;
REM INSERTING into FAI.FAI_ORDINE_OUT
SET DEFINE OFF;
REM INSERTING into FAI.FAI_RECORD_A_ADDEBITO_FINANZ
SET DEFINE OFF;
REM INSERTING into FAI.FAI_RECORD_C_CONDIZIONI
SET DEFINE OFF;
REM INSERTING into FAI.FAI_RECORD_D_RESET
SET DEFINE OFF;
REM INSERTING into FAI.FAI_RECORD_L_LISTINO
SET DEFINE OFF;
REM INSERTING into FAI.FAI_RECORD_R_RAGGR_COND
SET DEFINE OFF;
REM INSERTING into FAI.FAI_RECORD_V_RAGGR_COND
SET DEFINE OFF;
REM INSERTING into FAI.FAI_RECORD_Z_CHIUSURA
SET DEFINE OFF;
REM INSERTING into FAI.FAI_TABLE_EXPORT
SET DEFINE OFF;
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (17,null,'XXXXXXX','FAI_PO_LINES_INTERFACE',1,'PoLinesInterfaceBlanket',15,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (18,null,'XXXXXXX','FAI_PO_LINES_INTERFACE',1,'PoLinesInterfaceBlanket',16,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (232,null,'XXXXXXX','FAI_PO_GA_ORG_ASSIGN_INTERFACE',1,'PoGAOrgAssignInterfaceBlanket',15,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (233,null,'XXXXXXX','FAI_PO_GA_ORG_ASSIGN_INTERFACE',1,'PoGAOrgAssignInterfaceBlanket',16,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (234,null,'XXXXXXX','FAI_PO_GA_ORG_ASSIGN_INTERFACE',1,'PoGAOrgAssignInterfaceBlanket',231,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (263,null,'XXXXXXX','FAI_PO_HEADERS_INTERFACE',1,'PoHeadersInterfaceBlanket',15,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (264,null,'XXXXXXX','FAI_PO_HEADERS_INTERFACE',1,'PoHeadersInterfaceBlanket',16,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (265,null,'XXXXXXX','FAI_PO_HEADERS_INTERFACE',1,'PoHeadersInterfaceBlanket',231,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (266,null,'XXXXXXX','FAI_PO_HEADERS_INTERFACE',1,'PoHeadersInterfaceBlanket',262,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (724,null,'XXXXXXX','FAI_LINE_LOCATIONS_INTERFACE',1,'PoLineLocationsInterfaceBlanket',15,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (725,null,'XXXXXXX','FAI_LINE_LOCATIONS_INTERFACE',1,'PoLineLocationsInterfaceBlanket',16,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (726,null,'XXXXXXX','FAI_LINE_LOCATIONS_INTERFACE',1,'PoLineLocationsInterfaceBlanket',231,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (727,null,'XXXXXXX','FAI_LINE_LOCATIONS_INTERFACE',1,'PoLineLocationsInterfaceBlanket',262,null,null);
Insert into FAI.FAI_TABLE_EXPORT (OID,SQL_QUERY_IN_FILE,SMTWTFS_EXPORT_PATTERN,TABLE_NAME_ORACLE,TABLE_IDX,TABLE_NAME_EXPORT,OID_GROUPEXPORT,SQL_QUERY_OUT_FILE,LAST_EXPORTED_VERSION_NUMBER) values (728,null,'XXXXXXX','FAI_LINE_LOCATIONS_INTERFACE',1,'PoLineLocationsInterfaceBlanket',723,null,null);
REM INSERTING into FAI.FAI_TIPO_FARMACO
SET DEFINE OFF;
Insert into FAI.FAI_TIPO_FARMACO (OID,ACRONYM,DESCR) values (1,'FARMACO','FARMACO');
Insert into FAI.FAI_TIPO_FARMACO (OID,ACRONYM,DESCR) values (2,'PARAFARMACO','PARAFARMACO');
REM INSERTING into FAI.SD_SCHEMA_VERSION
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index IX_RECV_FORNITORE
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECV_FORNITORE" ON "FAI"."FAI_RECORD_V_RAGGR_COND" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_APPRFARM_FORN
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_APPRFARM_FORN" ON "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_GENTASK_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_GENTASK_OID" ON "FAI"."FAI_GENERIC_TASK" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_GENTSKPROP_GENTASK
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_GENTSKPROP_GENTASK" ON "FAI"."FAI_GENERIC_TASK_PROPERTY" ("OID_GENERICTASK") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_ORDINRDETT_OOR
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_ORDINRDETT_OOR" ON "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" ("OID_ORDINEIN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_ORDOUT_FORN
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_ORDOUT_FORN" ON "FAI"."FAI_ORDINE_OUT" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECC_CODPRO
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECC_CODPRO" ON "FAI"."FAI_RECORD_C_CONDIZIONI" ("CODICE_PRODOTTO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_ITEMSTAT_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_ITEMSTAT_OID" ON "FAI"."FAI_ITEM_STATUS" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_ORDOUT_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_ORDOUT_OID" ON "FAI"."FAI_ORDINE_OUT" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_COLEX_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_COLEX_OID" ON "FAI"."FAI_COLUMN_EXPORT" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECD_FORNITORE
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECD_FORNITORE" ON "FAI"."FAI_RECORD_D_RESET" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FORNPROP_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FORNPROP_OID" ON "FAI"."FAI_FORNITORE_PROPERTY" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FORNITORE_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FORNITORE_OID" ON "FAI"."FAI_FORNITORE" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FORNITFARM_FORNLID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FORNITFARM_FORNLID" ON "FAI"."FAI_FORNITORE_FARMACO" ("OID_FORNITORE", "LINE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_MAGAZZTIPOF_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_MAGAZZTIPOF_OID" ON "FAI"."FAI_MAGAZZINO_TIPOFARMACO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_LISTDISPTMP_FMINSANEAN
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_LISTDISPTMP_FMINSANEAN" ON "FAI"."FAI_LISTINI_DISPONIBILITA_TEMP" ("OID_FORNITORE", "CODICE_MINSAN", "CODICE_EAN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECL_CODCLI
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECL_CODCLI" ON "FAI"."FAI_RECORD_L_LISTINO" ("CODICE_CLIENTE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_GENTASK_ACR
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_GENTASK_ACR" ON "FAI"."FAI_GENERIC_TASK" ("ACRONYM") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_APPRFAROINRIGA_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_APPRFAROINRIGA_OID" ON "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECD_CODCLI
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECD_CODCLI" ON "FAI"."FAI_RECORD_D_RESET" ("CODICE_CLIENTE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_GRPEXP_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_GRPEXP_OID" ON "FAI"."FAI_GROUP_EXPORT" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECC_FORNITORE
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECC_FORNITORE" ON "FAI"."FAI_RECORD_C_CONDIZIONI" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index IX_DISPTEMP_FORN
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_DISPTEMP_FORN" ON "FAI"."FAI_DISPONIBILITA_TEMP" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_FORNPROP_FORNITORE
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_FORNPROP_FORNITORE" ON "FAI"."FAI_FORNITORE_PROPERTY" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_TIPOFARM_ACR
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_TIPOFARM_ACR" ON "FAI"."FAI_TIPO_FARMACO" ("ACRONYM") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index UX_GENTSKPROP_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_GENTSKPROP_OID" ON "FAI"."FAI_GENERIC_TASK_PROPERTY" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECL_CODPRO
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECL_CODPRO" ON "FAI"."FAI_RECORD_L_LISTINO" ("CODICE_PRODOTTO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_DISPREQTEMP_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_DISPREQTEMP_OID" ON "FAI"."FAI_DISPONIBILITA_REQ_TEMP" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FARMDTDDL_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FARMDTDDL_OID" ON "FAI"."FAI_FARMADATI_DDL" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_RECD_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_RECD_OID" ON "FAI"."FAI_RECORD_D_RESET" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_DISPRESTEMP_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_DISPRESTEMP_OID" ON "FAI"."FAI_DISPONIBILITA_RES_TEMP" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_FAIORINCL_ST
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_FAIORINCL_ST" ON "FAI"."FAI_ORDINE_IN_COLLECTION" ("OID_STATUS") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index UX_RECA_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_RECA_OID" ON "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_RECC_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_RECC_OID" ON "FAI"."FAI_RECORD_C_CONDIZIONI" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_APPRFAROINRIGA_APPRF_OINR
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_APPRFAROINRIGA_APPRF_OINR" ON "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" ("OID_APPROVFARMACO", "OID_ORDINEINRIGA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_ORDINEIN_OICOLL
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_ORDINEIN_OICOLL" ON "FAI"."FAI_ORDINE_IN" ("OID_ORDINEINCOLLECTION") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_GENTSKPROP_GTASK_KEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."IX_GENTSKPROP_GTASK_KEY" ON "FAI"."FAI_GENERIC_TASK_PROPERTY" ("OID_GENERICTASK", "KEY") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECC_CODCLI
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECC_CODCLI" ON "FAI"."FAI_RECORD_C_CONDIZIONI" ("CODICE_CLIENTE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_COLEX_TABEXP
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_COLEX_TABEXP" ON "FAI"."FAI_COLUMN_EXPORT" ("OID_TABLEEXPORT") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index IX_ORDINEIN_ST
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_ORDINEIN_ST" ON "FAI"."FAI_ORDINE_IN" ("OID_STATUS") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_MAGAZ_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_MAGAZ_OID" ON "FAI"."FAI_MAGAZZINO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FAIORINCL_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FAIORINCL_OID" ON "FAI"."FAI_ORDINE_IN_COLLECTION" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_DISPRESTEMP_QDIS
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_DISPRESTEMP_QDIS" ON "FAI"."FAI_DISPONIBILITA_RES_TEMP" ("OID_DISPONIBILITAREQTEMP") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECZ_FORNITORE
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECZ_FORNITORE" ON "FAI"."FAI_RECORD_Z_CHIUSURA" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FORNIT_COD
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FORNIT_COD" ON "FAI"."FAI_FARMACLICK_FORNITORE" ("CODICE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_FORNITFARM_FORNIT
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_FORNITFARM_FORNIT" ON "FAI"."FAI_FORNITORE_FARMACO" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FARMDTWS_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FARMDTWS_OID" ON "FAI"."FAI_FARMADATI_WS" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_RECV_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_RECV_OID" ON "FAI"."FAI_RECORD_V_RAGGR_COND" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECL_FORNITORE
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECL_FORNITORE" ON "FAI"."FAI_RECORD_L_LISTINO" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_RECR_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_RECR_OID" ON "FAI"."FAI_RECORD_R_RAGGR_COND" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_IMPPRO_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_IMPPRO_OID" ON "FAI"."FAI_IMPORT_CONFIG" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_ORDINEIN_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_ORDINEIN_OID" ON "FAI"."FAI_ORDINE_IN" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_ORDOUT_ST
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_ORDOUT_ST" ON "FAI"."FAI_ORDINE_OUT" ("OID_STATUS") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_DISPTEMP_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_DISPTEMP_OID" ON "FAI"."FAI_DISPONIBILITA_TEMP" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index XPKFAI_FARMACO
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."XPKFAI_FARMACO" ON "FAI"."FAI_FARMACO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index IX_RECA_FORNITORE
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECA_FORNITORE" ON "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index IX_TABEXP_GRPEXP
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_TABEXP_GRPEXP" ON "FAI"."FAI_TABLE_EXPORT" ("OID_GROUPEXPORT") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_TIPOFARM_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_TIPOFARM_OID" ON "FAI"."FAI_TIPO_FARMACO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_NOGAPSEQ_ACR
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_NOGAPSEQ_ACR" ON "FAI"."FAI_NO_GAP_SEQUENCE" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECA_CODCLI
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECA_CODCLI" ON "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" ("CODICE_CLIENTE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_LOG_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_LOG_OID" ON "FAI"."FAI_LOG" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_DISPTEMP_STATUS
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_DISPTEMP_STATUS" ON "FAI"."FAI_DISPONIBILITA_TEMP" ("OID_STATUS") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_IMPPRO_ACRONYM
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_IMPPRO_ACRONYM" ON "FAI"."FAI_IMPORT_CONFIG" ("ACRONYM") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_ORDINRDETT_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_ORDINRDETT_OID" ON "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_MAGAZPROP_MAGAZZINO
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_MAGAZPROP_MAGAZZINO" ON "FAI"."FAI_MAGAZZINO_PROPERTY" ("OID_MAGAZZINO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FORNITFARM_FORMSAN
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FORNITFARM_FORMSAN" ON "FAI"."FAI_FORNITORE_FARMACO" ("OID_FORNITORE", "CODICE_MINSAN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_RECL_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_RECL_OID" ON "FAI"."FAI_RECORD_L_LISTINO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_DISPTEMP_MAG
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_DISPTEMP_MAG" ON "FAI"."FAI_DISPONIBILITA_TEMP" ("OID_MAGAZZINO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECV_CODCLI
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECV_CODCLI" ON "FAI"."FAI_RECORD_V_RAGGR_COND" ("CODICE_CLIENTE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_APPRFARM_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_APPRFARM_OID" ON "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_APPRFARM_MAGAZZ
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_APPRFARM_MAGAZZ" ON "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ("OID_MAGAZZINO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_MAGAZZTIPOF_TIPOF
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_MAGAZZTIPOF_TIPOF" ON "FAI"."FAI_MAGAZZINO_TIPOFARMACO" ("OID_TIPOFARMACO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECZ_CODCLI
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECZ_CODCLI" ON "FAI"."FAI_RECORD_Z_CHIUSURA" ("CODICE_CLIENTE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_APPRFARM_ORDOUT
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_APPRFARM_ORDOUT" ON "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ("OID_ORDINEOUT") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_APPRFAROINRIGA_OINRIGA
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_APPRFAROINRIGA_OINRIGA" ON "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" ("OID_ORDINEINRIGA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_MAGAZZTIPOF_MAG
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_MAGAZZTIPOF_MAG" ON "FAI"."FAI_MAGAZZINO_TIPOFARMACO" ("OID_MAGAZZINO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECR_FORNITORE
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECR_FORNITORE" ON "FAI"."FAI_RECORD_R_RAGGR_COND" ("OID_FORNITORE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_RECZ_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_RECZ_OID" ON "FAI"."FAI_RECORD_Z_CHIUSURA" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_MAGAZPROP_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_MAGAZPROP_OID" ON "FAI"."FAI_MAGAZZINO_PROPERTY" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_RECR_CODCLI
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_RECR_CODCLI" ON "FAI"."FAI_RECORD_R_RAGGR_COND" ("CODICE_CLIENTE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_IMPPRO_CLASSNAME
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_IMPPRO_CLASSNAME" ON "FAI"."FAI_IMPORT_CONFIG" ("CLASS_NAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FORNITFARM_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FORNITFARM_OID" ON "FAI"."FAI_FORNITORE_FARMACO" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_TABEXP_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_TABEXP_OID" ON "FAI"."FAI_TABLE_EXPORT" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FAIORINCL_UID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FAIORINCL_UID" ON "FAI"."FAI_ORDINE_IN_COLLECTION" ("UNIQUE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_APPRFARM_STATUS
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_APPRFARM_STATUS" ON "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ("OID_STATUS") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_APPRFAROINRIGA_APPRF
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_APPRFAROINRIGA_APPRF" ON "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" ("OID_APPROVFARMACO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index IX_DISPREQTEMP_DISP
--------------------------------------------------------

  CREATE INDEX "FAI"."IX_DISPREQTEMP_DISP" ON "FAI"."FAI_DISPONIBILITA_REQ_TEMP" ("OID_DISPONIBILITATEMP") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  DDL for Index UX_FORNIT_OID
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAI"."UX_FORNIT_OID" ON "FAI"."FAI_FARMACLICK_FORNITORE" ("OID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX" ;
--------------------------------------------------------
--  Constraints for Table FAI_RECORD_D_RESET
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_D_RESET" ADD CONSTRAINT "PK_RECD" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_RECORD_D_RESET" MODIFY ("CREATION_TS" CONSTRAINT "NN_RECD_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_D_RESET" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_RECD_FORNITORE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_D_RESET" MODIFY ("OID" CONSTRAINT "NN_RECD_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FARMACLICK_FORNITORE
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FARMACLICK_FORNITORE" ADD CONSTRAINT "UQ_FORNIT_COD" UNIQUE ("CODICE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FARMACLICK_FORNITORE" ADD CONSTRAINT "PK_FORNIT" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FARMACLICK_FORNITORE" MODIFY ("CODICE" CONSTRAINT "NN_FORNIT_COD" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMACLICK_FORNITORE" MODIFY ("OID" CONSTRAINT "NN_FORNIT_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_MAGAZZINO_TIPOFARMACO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_MAGAZZINO_TIPOFARMACO" ADD CONSTRAINT "PK_MAGAZZTIPOF" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_MAGAZZINO_TIPOFARMACO" MODIFY ("OID_TIPOFARMACO" CONSTRAINT "NN_MAGAZZTIPOF_TIPOF" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_MAGAZZINO_TIPOFARMACO" MODIFY ("OID_MAGAZZINO" CONSTRAINT "NN_MAGAZZTIPOF_MAG" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_MAGAZZINO_TIPOFARMACO" MODIFY ("OID" CONSTRAINT "NN_MAGAZZTIPOF_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_ORDINE_IN
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ORDINE_IN" ADD CONSTRAINT "PK_ORDINEIN" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_ORDINE_IN" MODIFY ("STATUS_UPDATED_TS" CONSTRAINT "NN_ORDINEIN_STUPTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN" MODIFY ("OID_ORDINEINCOLLECTION" CONSTRAINT "NN_ORDINEIN_OICOLL" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN" MODIFY ("OID_STATUS" CONSTRAINT "NN_ORDINEIN_ST" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN" MODIFY ("CREATION_TS" CONSTRAINT "NN_ORDINEIN_CRTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN" MODIFY ("ID_VENDITA" CONSTRAINT "NN_ORDINEIN_IDVEND" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN" MODIFY ("OID" CONSTRAINT "NN_ORDINEIN_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FORNITORE_PROPERTY
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FORNITORE_PROPERTY" ADD CONSTRAINT "PK_FORNPROP" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FORNITORE_PROPERTY" MODIFY ("VALUE" CONSTRAINT "NN_FORNPROP_VALUE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FORNITORE_PROPERTY" MODIFY ("KEY" CONSTRAINT "NN_FORNPROP_KEY" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FORNITORE_PROPERTY" MODIFY ("OID" CONSTRAINT "NN_FORNPROP_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_ITEM_STATUS
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ITEM_STATUS" ADD CONSTRAINT "PK_ITEMSTAT" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_ITEM_STATUS" MODIFY ("DESCR" CONSTRAINT "NN_ITEMSTAT_DESCR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ITEM_STATUS" MODIFY ("ACRONYM" CONSTRAINT "NN_ITEMSTAT_ACR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ITEM_STATUS" MODIFY ("OID" CONSTRAINT "NN_ITEMSTAT_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_APPROVVIGIONAMENTO_FARMACO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ADD CONSTRAINT "PK_APPRFARM" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ADD CONSTRAINT "CK_APPRFARM_MINSANEAN" CHECK ( CODICE_MINSAN IS NOT NULL OR CODICE_EAN IS NOT NULL ) ENABLE;
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" MODIFY ("CREATION_TS" CONSTRAINT "NN_APPRFARM_CRTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" MODIFY ("STATUS_UPDATED_TS" CONSTRAINT "NN_APPRFARM_STATUSUPDTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" MODIFY ("OID_STATUS" CONSTRAINT "NN_APPRFARM_STATUS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" MODIFY ("QUANTITA" CONSTRAINT "NN_APPRFARM_QUANT" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" MODIFY ("OID" CONSTRAINT "NN_APPRFARM_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_LOG
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_LOG" ADD CONSTRAINT "PK_LOG" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_LOG" ADD CONSTRAINT "CK_LOG_LEVEL" CHECK (LOG_LEVEL IN ('T', 'D', 'I', 'W', 'E', 'F')) ENABLE;
  ALTER TABLE "FAI"."FAI_LOG" MODIFY ("THREAD" CONSTRAINT "NN_LOG_THREAD" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_LOG" MODIFY ("TS" CONSTRAINT "NN_LOG_TS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_LOG" MODIFY ("LOG_LEVEL" CONSTRAINT "NN_LOG_LEVEL" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_LOG" MODIFY ("OID" CONSTRAINT "NN_LOG_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_ORDINE_IN_RIGA_DETTAGLIO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" ADD CONSTRAINT "PK_ORDINRDETT" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" ADD CONSTRAINT "CK_ORDINRDETT_ISCOUP" CHECK (IS_COUPON IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" MODIFY ("IS_COUPON" CONSTRAINT "NN_ORDINRDETT_ISCOUP" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" MODIFY ("CREATION_TS" CONSTRAINT "NN_ORDINRDETT_CRTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" MODIFY ("OID_ORDINEIN" CONSTRAINT "NN_ORDINRDETT_OOR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" MODIFY ("ALIQUOTA_IVA_INCLUSA" CONSTRAINT "NN_ORDINRDETT_IVAI" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" MODIFY ("PREZZO_FINALE" CONSTRAINT "NN_ORDINRDETT_PRZF" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" MODIFY ("QUANTITA" CONSTRAINT "NN_ORDINRDETT_QT" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" MODIFY ("NUMERO_OGGETTO" CONSTRAINT "NN_ORDINRDETT_NOGG" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" MODIFY ("OID" CONSTRAINT "NN_ORDINRDETT_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FARMADATI_DDL
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FARMADATI_DDL" ADD CONSTRAINT "PK_FARMDTDDL" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FARMADATI_DDL" MODIFY ("CREATION_TS" CONSTRAINT "NN_FARMDTDDL_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_DDL" MODIFY ("SQL_CREATE" CONSTRAINT "NN_FARMDTDDL_SQLC" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_DDL" MODIFY ("TABLE_NAME" CONSTRAINT "NN_FARMDTDDL_TNAME" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_DDL" MODIFY ("OID" CONSTRAINT "NN_FARMDTDDL_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FARMADATI_WS
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FARMADATI_WS" ADD CONSTRAINT "PK_FARMDTWS" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FARMADATI_WS" ADD CONSTRAINT "CK_FARMDTWS_RESS" CHECK (RESULT_SUCCESS IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_FARMADATI_WS" MODIFY ("OID" CONSTRAINT "NN_FARMDTWS_OID" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_WS" MODIFY ("SERVICE_NAME" CONSTRAINT "NN_FARMDTWS_SNAME" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_WS" MODIFY ("CREATION_TS" CONSTRAINT "NN_FARMDTWS_CREATS" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_GROUP_EXPORT
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_GROUP_EXPORT" ADD CONSTRAINT "PK_GRPEXP" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_GROUP_EXPORT" MODIFY ("GROUP_NAME_EXPORT" CONSTRAINT "NN_GRPEXP_NAMEEXP" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_GROUP_EXPORT" MODIFY ("GROUP_IDX" CONSTRAINT "NN_GRPEXP_IDX" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_GROUP_EXPORT" MODIFY ("OID" CONSTRAINT "NN_GRPEXP_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_DISPONIBILITA_RES_TEMP
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_DISPONIBILITA_RES_TEMP" ADD CONSTRAINT "PK_DISPRESTEMP" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_RES_TEMP" MODIFY ("OID_DISPONIBILITAREQTEMP" CONSTRAINT "NN_DISPRESTEMP_REQ" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_RES_TEMP" MODIFY ("QUANTITA_DISPONIBILE" CONSTRAINT "NN_DISPRESTEMP_QDIS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_RES_TEMP" MODIFY ("OID" CONSTRAINT "NN_DISPRESTEMP_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FORNITORE_FARMACO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" ADD CONSTRAINT "UQ_FORNITFARM_FORNLID" UNIQUE ("OID_FORNITORE", "LINE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" ADD CONSTRAINT "UQ_FORNITFARM_FORMSAN" UNIQUE ("OID_FORNITORE", "CODICE_MINSAN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" ADD CONSTRAINT "UQ_FORNITFARM_OID" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" MODIFY ("CREATION_TS" CONSTRAINT "NN_FORNITFARM_CRTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_FORNITFARM_FORNIT" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" MODIFY ("LINE_ID" CONSTRAINT "NN_FORNITFARM_LINEID" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" MODIFY ("CODICE_MINSAN" CONSTRAINT "NN_FORNITFARM_CMINSAN" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" MODIFY ("OID" CONSTRAINT "NN_FORNITFARM_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_IMPORT_CONFIG
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "UQ_IMPPRO_CLASSNAME" UNIQUE ("CLASS_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "UQ_IMPPRO_ACRONYM" UNIQUE ("ACRONYM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "PK_IMPPRO" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "CK_IMPPRO_SQZIPPEDC" CHECK (SERVICE_QUERY_ZIPPED_CONTENT IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "CK_IMPPRO_LASTRDONE" CHECK (LAST_RUN_DONE IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "CK_IMPPRO_LASTRTYPE" CHECK (LAST_RUN_TYPE IN ('C', 'V')) ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "CK_IMPPRO_RESUMEPREVS" CHECK (SERVICE_RESUME_PREV_SESS IN ('S', 'N', 'A')) ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "CK_IMPPRO_SQSMTWTFS" CHECK (REGEXP_LIKE(SERVICE_QUERY_SMTWTFS, '^[CV\-]*$')) ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "CK_IMPPRO_SQTIME" CHECK (TRUNC(SERVICE_QUERY_TIME) = TO_DATE('1970-01-01', 'YYYY-MM-DD')) ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" ADD CONSTRAINT "CK_IMPPRO_PASSENCR" CHECK (SERVICE_PASS_ENCR IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" MODIFY ("SERVICE_QUERY_ZIPPED_CONTENT" CONSTRAINT "NN_IMPPRO_SQZIPPEDC" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" MODIFY ("CLASS_NAME" CONSTRAINT "NN_IMPPRO_CLASSNAME" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" MODIFY ("SERVICE_RESUME_PREV_SESS" CONSTRAINT "NN_IMPPRO_RESUMEPREVS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" MODIFY ("SERVICE_QUERY_SMTWTFS" CONSTRAINT "NN_IMPPRO_SQSMTWTFS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" MODIFY ("SERVICE_QUERY_TIME" CONSTRAINT "NN_IMPPRO_SQTIME" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" MODIFY ("SERVICE_PASS_ENCR" CONSTRAINT "NN_IMPPRO_PASSENCR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" MODIFY ("ACRONYM" CONSTRAINT "NN_IMPPRO_ACRONYM" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_IMPORT_CONFIG" MODIFY ("OID" CONSTRAINT "NN_IMPPRO_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_NO_GAP_SEQUENCE
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_NO_GAP_SEQUENCE" ADD CONSTRAINT "UQ_NOGAPSEQ_ACR" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_NO_GAP_SEQUENCE" MODIFY ("LAST_VALUE" CONSTRAINT "NN_NOGAPSEQ_LASTVAL" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_NO_GAP_SEQUENCE" MODIFY ("ACRONYM" CONSTRAINT "NN_NOGAPSEQ_ACR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_NO_GAP_SEQUENCE" MODIFY ("OID" CONSTRAINT "NN_NOGAPSEQ_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_RECORD_R_RAGGR_COND
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_R_RAGGR_COND" ADD CONSTRAINT "PK_RECR" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_RECORD_R_RAGGR_COND" MODIFY ("UPDATE_TS" CONSTRAINT "NN_RECR_UPDTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_R_RAGGR_COND" MODIFY ("CREATION_TS" CONSTRAINT "NN_RECR_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_R_RAGGR_COND" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_RECR_FORNITORE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_R_RAGGR_COND" MODIFY ("OID" CONSTRAINT "NN_RECR_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_ORDINE_IN_COLLECTION
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" ADD CONSTRAINT "UQ_FAIORINCL_UID" UNIQUE ("UNIQUE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" ADD CONSTRAINT "UX_FAIORINCL_OID" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" MODIFY ("UNIQUE_ID" CONSTRAINT "NN_FAIORINCL_UID" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" MODIFY ("STATUS_UPDATED_TS" CONSTRAINT "NN_FAIORINCL_STUPDTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" MODIFY ("OID_STATUS" CONSTRAINT "NN_FAIORINCL_ST" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" MODIFY ("CREATION_TS" CONSTRAINT "NN_FAIORINCL_CRTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" MODIFY ("BATCH_ID" CONSTRAINT "NN_FAIORINCL_BATID" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" MODIFY ("OID" CONSTRAINT "NN_FAIORINCL_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FARMADATI_WS_CONFIG
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" ADD CONSTRAINT "CK_FDCFG_RSMEPRVSMODE" CHECK (RESUME_PREV_SESSION_MODE IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" ADD CONSTRAINT "CK_FDCFG_DEBUGMODE" CHECK (DEBUG_MODE IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" ADD CONSTRAINT "CK_FDCFG_WSPASSENCR" CHECK (WS_PASSWORD_ENCR IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" MODIFY ("RESUME_PREV_SESSION_MODE" CONSTRAINT "NN_FDCFG_RSMEPRVSMODE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" MODIFY ("DEBUG_MODE" CONSTRAINT "NN_FDCFG_DEBUGMODE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" MODIFY ("WS_PASSWORD_ENCR" CONSTRAINT "NN_FDCFG_WSPASSENCR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" MODIFY ("WS_QUERY_TIME" CONSTRAINT "NN_FDCFG_QUERYTIME" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" MODIFY ("WS_QUERY_INTERVAL" CONSTRAINT "NN_FDCFG_QUERYINT" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_CONFIG" MODIFY ("WS_URL" CONSTRAINT "NN_FDCFG_WSURL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_TABLE_EXPORT
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" ADD CONSTRAINT "PK_TABEXP" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" ADD CONSTRAINT "CK_TABEXP_SMTWTFS" CHECK (REGEXP_LIKE(SMTWTFS_EXPORT_PATTERN, '^[X\-]*$')) ENABLE;
  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" MODIFY ("OID_GROUPEXPORT" CONSTRAINT "NN_TABEXP_GRPEXP" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" MODIFY ("TABLE_NAME_EXPORT" CONSTRAINT "NN_TABEXP_NAMEEXP" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" MODIFY ("TABLE_IDX" CONSTRAINT "NN_TABEXP_IDX" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" MODIFY ("TABLE_NAME_ORACLE" CONSTRAINT "NN_TABEXP_NAMEORA" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" MODIFY ("SMTWTFS_EXPORT_PATTERN" CONSTRAINT "NN_TABEXP_SMTWTFS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" MODIFY ("OID" CONSTRAINT "NN_TABEXP_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_RECORD_A_ADDEBITO_FINANZ
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" ADD CONSTRAINT "PK_RECA" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" MODIFY ("UPDATE_TS" CONSTRAINT "NN_RECA_UPDTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" MODIFY ("CREATION_TS" CONSTRAINT "NN_RECA_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_RECA_FORNITORE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" MODIFY ("OID" CONSTRAINT "NN_RECA_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_RECORD_Z_CHIUSURA
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_Z_CHIUSURA" ADD CONSTRAINT "PK_RECZ" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_RECORD_Z_CHIUSURA" MODIFY ("UPDATE_TS" CONSTRAINT "NN_RECZ_UPDTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_Z_CHIUSURA" MODIFY ("CREATION_TS" CONSTRAINT "NN_RECZ_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_Z_CHIUSURA" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_RECZ_FORNITORE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_Z_CHIUSURA" MODIFY ("OID" CONSTRAINT "NN_RECZ_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_MAGAZZINO_PROPERTY
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_MAGAZZINO_PROPERTY" ADD CONSTRAINT "PK_MAGAZPROP" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_MAGAZZINO_PROPERTY" MODIFY ("OID_MAGAZZINO" CONSTRAINT "NN_MAGAZPROP_MAGAZZINO" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_MAGAZZINO_PROPERTY" MODIFY ("VALUE" CONSTRAINT "NN_MAGAZPROP_VALUE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_MAGAZZINO_PROPERTY" MODIFY ("KEY" CONSTRAINT "NN_MAGAZPROP_KEY" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_MAGAZZINO_PROPERTY" MODIFY ("OID" CONSTRAINT "NN_MAGAZPROP_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_GENERIC_TASK
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_GENERIC_TASK" ADD CONSTRAINT "UQ_GENTASK_ACR" UNIQUE ("ACRONYM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_GENERIC_TASK" ADD CONSTRAINT "PK_GENTASK" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_GENERIC_TASK" ADD CONSTRAINT "CK_GENTASK_TMS_SMTWTFS" CHECK ( (SCHEDULED_TIMES IS NULL AND SCHEDULED_SMTWTFS IS NULL) OR (SCHEDULED_TIMES IS NOT NULL AND SCHEDULED_SMTWTFS IS NOT NULL)  ) ENABLE;
  ALTER TABLE "FAI"."FAI_GENERIC_TASK" ADD CONSTRAINT "CK_GENTASK_ENABL" CHECK (ENABLED IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_GENERIC_TASK" ADD CONSTRAINT "CK_GENTASK_SMTWTFS" CHECK (REGEXP_LIKE(SCHEDULED_SMTWTFS, '^[X\-]*$')) ENABLE;
  ALTER TABLE "FAI"."FAI_GENERIC_TASK" MODIFY ("ENABLED" CONSTRAINT "NN_GENTASK_ENABL" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_GENERIC_TASK" MODIFY ("CLASS_NAME" CONSTRAINT "NN_GENTASK_CLNAME" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_GENERIC_TASK" MODIFY ("ACRONYM" CONSTRAINT "NN_GENTASK_ACR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_GENERIC_TASK" MODIFY ("OID" CONSTRAINT "NN_GENTASK_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_MAGAZZINO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_MAGAZZINO" ADD CONSTRAINT "UX_MAGAZ_OID" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_MAGAZZINO" MODIFY ("ACRONYM" CONSTRAINT "NN_MAGAZ_ACR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_MAGAZZINO" MODIFY ("OID" CONSTRAINT "NN_MAGAZ_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_TIPO_FARMACO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_TIPO_FARMACO" ADD CONSTRAINT "UQ_TIPOFARM_ACR" UNIQUE ("ACRONYM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "FAI"."FAI_TIPO_FARMACO" ADD CONSTRAINT "PK_TIPOFARM" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_TIPO_FARMACO" MODIFY ("OID" CONSTRAINT "NN_TIPOFARM_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_GENERIC_TASK_PROPERTY
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY" ADD CONSTRAINT "UQ_GENTSKPROP_GTASK_KEY" UNIQUE ("OID_GENERICTASK", "KEY")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY" ADD CONSTRAINT "UQ_GENTSKPROP" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY" MODIFY ("OID_GENERICTASK" CONSTRAINT "NN_GENTSKPROP_GENTASK" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY" MODIFY ("VALUE" CONSTRAINT "NN_GENTSKPROP_VALUE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY" MODIFY ("KEY" CONSTRAINT "NN_GENTSKPROP_KEY" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY" MODIFY ("OID" CONSTRAINT "NN_GENTSKPROP_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_DISPONIBILITA_TEMP
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_DISPONIBILITA_TEMP" ADD CONSTRAINT "PK_DISPTEMP" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_TEMP" ADD CONSTRAINT "CK_DISPTEMP_FORNMAG" CHECK ( (OID_FORNITORE IS NOT NULL AND OID_MAGAZZINO IS NULL) OR (OID_FORNITORE IS NULL AND OID_MAGAZZINO IS NOT NULL) ) ENABLE;
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_TEMP" MODIFY ("REQUEST_TS" CONSTRAINT "NN_DISPTEMP_REQTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_TEMP" MODIFY ("OID" CONSTRAINT "NN_DISPTEMP_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_RECORD_L_LISTINO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_L_LISTINO" ADD CONSTRAINT "PK_RECL" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_RECORD_L_LISTINO" MODIFY ("UPDATE_TS" CONSTRAINT "NN_RECL_UPDTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_L_LISTINO" MODIFY ("CREATION_TS" CONSTRAINT "NN_RECL_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_L_LISTINO" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_RECL_FORNITORE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_L_LISTINO" MODIFY ("OID" CONSTRAINT "NN_RECL_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FORNITORE
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FORNITORE" ADD CONSTRAINT "PK_FORNITORE" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_FORNITORE" MODIFY ("CODICE" CONSTRAINT "NN_FORNITORE_CODICE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_FORNITORE" MODIFY ("OID" CONSTRAINT "NN_FORNITORE_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_APPROVVIGFARMACO_ORDINRIGA
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" MODIFY ("OID_ORDINEINRIGA" CONSTRAINT "NN_APPRFAROINRIGA_OINRIGA" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" MODIFY ("OID_APPROVFARMACO" CONSTRAINT "NN_APPRFAROINRIGA_APPRF" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" MODIFY ("OID" CONSTRAINT "NN_APPRFAROINRIGA_OID" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" ADD CONSTRAINT "UQ_APPRFAROINRIGA_APPRF_OINR" UNIQUE ("OID_APPROVFARMACO", "OID_ORDINEINRIGA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" ADD CONSTRAINT "PK_APPRFAROINRIGA" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" MODIFY ("QUANTITA" CONSTRAINT "NN_APPRFAROINRIGA_QUANT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SD_SCHEMA_VERSION
--------------------------------------------------------

  ALTER TABLE "FAI"."SD_SCHEMA_VERSION" MODIFY ("LAST_MODIFY" CONSTRAINT "NN_SCHEMAVER_LASTMOD" NOT NULL ENABLE);
  ALTER TABLE "FAI"."SD_SCHEMA_VERSION" MODIFY ("VERSION" CONSTRAINT "NN_SCHEMAVER_VER" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_RECORD_C_CONDIZIONI
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_C_CONDIZIONI" ADD CONSTRAINT "PK_RECC" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_RECORD_C_CONDIZIONI" MODIFY ("UPDATE_TS" CONSTRAINT "NN_RECC_UPDTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_C_CONDIZIONI" MODIFY ("CREATION_TS" CONSTRAINT "NN_RECC_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_C_CONDIZIONI" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_RECC_FORNITORE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_C_CONDIZIONI" MODIFY ("OID" CONSTRAINT "NN_RECC_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_RECORD_V_RAGGR_COND
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_V_RAGGR_COND" ADD CONSTRAINT "PK_RECV" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_RECORD_V_RAGGR_COND" MODIFY ("UPDATE_TS" CONSTRAINT "NN_RECV_UPDTS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_V_RAGGR_COND" MODIFY ("CREATION_TS" CONSTRAINT "NN_RECV_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_V_RAGGR_COND" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_RECV_FORNITORE" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_RECORD_V_RAGGR_COND" MODIFY ("OID" CONSTRAINT "NN_RECV_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_EXPORT_CONFIG
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" ADD CONSTRAINT "CK_VR_EXPCFG_LASTRDONE" CHECK (LAST_RUN_DONE in ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" ADD CONSTRAINT "CK_EXPCFG_EXPTIME" CHECK (TRUNC(EXPORT_TIME) = TO_DATE('1970-01-01', 'YYYY-MM-DD')) ENABLE;
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" ADD CONSTRAINT "CK_EXPCFG_PASSENCR" CHECK (FTP_PASSWORD_ENCR IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" MODIFY ("EXPORT_TIME" CONSTRAINT "NN_EXPCFG_EXPTIME" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" MODIFY ("FTP_PASSWORD_ENCR" CONSTRAINT "NN_EXPCFG_PASSENCR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" MODIFY ("FTP_PASSWORD" CONSTRAINT "NN_EXPCFG_PASS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" MODIFY ("FTP_LOGIN" CONSTRAINT "NN_EXPCFG_LOGIN" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" MODIFY ("FTP_DIR" CONSTRAINT "NN_EXPCFG_DIR" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" MODIFY ("FTP_PORT" CONSTRAINT "NN_EXPCFG_PORT" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" MODIFY ("FTP_HOST" CONSTRAINT "NN_EXPCFG_HOST" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_EXPORT_CONFIG" MODIFY ("FTP_PROTOCOL" CONSTRAINT "NN_EXPCFG_PROTOCL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_COLUMN_EXPORT
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" ADD CONSTRAINT "UX_COLEX_OID" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" ADD CONSTRAINT "CK_COLEX_NUMFMT" CHECK ( (NUMB_FORMAT_PATTERN IS NOT NULL AND NUMB_FORMAT_GRPCH IS NOT NULL AND NUMB_FORMAT_DECCH IS NOT NULL)
OR
(NUMB_FORMAT_PATTERN IS NULL AND NUMB_FORMAT_GRPCH IS NULL AND NUMB_FORMAT_DECCH IS NULL) ) ENABLE;
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" ADD CONSTRAINT "CK_COLEX_TXTFMT" CHECK ( (TEXT_FORMAT_TYPE IS NOT NULL AND TEXT_FORMAT_SEARCH IS NOT NULL AND TEXT_FORMAT_REPLACE IS NOT NULL)
OR
(TEXT_FORMAT_TYPE IS NULL AND TEXT_FORMAT_SEARCH IS NULL AND TEXT_FORMAT_REPLACE IS NULL)
 ) ENABLE;
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" ADD CONSTRAINT "CK_COLEX_TXTFMTTYP" CHECK (TEXT_FORMAT_TYPE IN ('RE', 'SR')) ENABLE;
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" ADD CONSTRAINT "CK_COLEX_REQ" CHECK (REQUIRED IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" MODIFY ("OID_TABLEEXPORT" CONSTRAINT "NN_COLEX_TABEXP" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" MODIFY ("SQL_TYPE" CONSTRAINT "NN_COLEX_SQLTYP" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" MODIFY ("REQUIRED" CONSTRAINT "NN_COLEX_REQ" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" MODIFY ("COLUMN_NAME_EXPORT" CONSTRAINT "NN_COLEX_NAMEEXP" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" MODIFY ("COLUMN_NAME_ORACLE" CONSTRAINT "NN_COLEX_NAMEORA" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" MODIFY ("COLUMN_IDX" CONSTRAINT "NN_COLEX_IDX" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" MODIFY ("OID" CONSTRAINT "NN_COLEX_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_DISPONIBILITA_REQ_TEMP
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_DISPONIBILITA_REQ_TEMP" ADD CONSTRAINT "PK_DISPREQTEMP" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_REQ_TEMP" ADD CONSTRAINT "CK_DISPRICTEMP_MINSANEAN" CHECK ( CODICE_MINSAN IS NOT NULL OR CODICE_EAN IS NOT NULL ) ENABLE;
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_REQ_TEMP" MODIFY ("QUANTITA_RICHIESTA" CONSTRAINT "NN_DISPREQTEMP_QRIC" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_REQ_TEMP" MODIFY ("OID_DISPONIBILITATEMP" CONSTRAINT "NN_DISPREQTEMP_DISP" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_REQ_TEMP" MODIFY ("OID" CONSTRAINT "NN_DISPREQTEMP_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FARMADATI_WS_STATUS
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FARMADATI_WS_STATUS" ADD CONSTRAINT "CK_STATUS_LRUNDONE" CHECK (LAST_RUN_DONE IN ('S', 'N')) ENABLE;
  ALTER TABLE "FAI"."FAI_FARMADATI_WS_STATUS" MODIFY ("LAST_RUN_DONE" CONSTRAINT "NN_STATUS_LRUNDONE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_LISTINI_DISPONIBILITA_TEMP
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_LISTINI_DISPONIBILITA_TEMP" ADD CONSTRAINT "UQ_LISTDISPTMP_FMINSAN" UNIQUE ("OID_FORNITORE", "CODICE_MINSAN", "CODICE_EAN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_LISTINI_DISPONIBILITA_TEMP" MODIFY ("PREZZO" CONSTRAINT "NN_LISTDISPTMP_PREZZO" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_LISTINI_DISPONIBILITA_TEMP" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_LISTDISPTMP_FORN" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_FARMACO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FARMACO" ADD CONSTRAINT "XPKFAI_FARMACO" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "FAI"."FAI_FARMACO" MODIFY ("OID" CONSTRAINT "NN_FAIFARMACO_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAI_ORDINE_OUT
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ORDINE_OUT" ADD CONSTRAINT "PK_ORDOUT" PRIMARY KEY ("OID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "FAI_IDX"  ENABLE;
  ALTER TABLE "FAI"."FAI_ORDINE_OUT" MODIFY ("CREATION_TS" CONSTRAINT "NN_ORDOUT_CREATS" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_OUT" MODIFY ("STATUS_UPDATED_TS" CONSTRAINT "NN_ORDOUT_UPDST" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_OUT" MODIFY ("OID_STATUS" CONSTRAINT "NN_ORDOUT_ST" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_OUT" MODIFY ("OID_FORNITORE" CONSTRAINT "NN_ORDOUT_FORN" NOT NULL ENABLE);
  ALTER TABLE "FAI"."FAI_ORDINE_OUT" MODIFY ("OID" CONSTRAINT "NN_ORDOUT_OID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table FAI_APPROVVIGFARMACO_ORDINRIGA
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" ADD CONSTRAINT "FK_APPRFAROINRIGA_APPRF" FOREIGN KEY ("OID_APPROVFARMACO")
	  REFERENCES "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ("OID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "FAI"."FAI_APPROVVIGFARMACO_ORDINRIGA" ADD CONSTRAINT "FK_APPRFAROINRIGA_OINRIGA" FOREIGN KEY ("OID_ORDINEINRIGA")
	  REFERENCES "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" ("OID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_APPROVVIGIONAMENTO_FARMACO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ADD CONSTRAINT "FK_APPRFARM_FORN" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FORNITORE" ("OID") ENABLE;
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ADD CONSTRAINT "FK_APPRFARM_MAGAZZ" FOREIGN KEY ("OID_MAGAZZINO")
	  REFERENCES "FAI"."FAI_MAGAZZINO" ("OID") ENABLE;
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ADD CONSTRAINT "FK_APPRFARM_ORDOUT" FOREIGN KEY ("OID_ORDINEOUT")
	  REFERENCES "FAI"."FAI_ORDINE_OUT" ("OID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "FAI"."FAI_APPROVVIGIONAMENTO_FARMACO" ADD CONSTRAINT "FK_APPRFARM_STATUS" FOREIGN KEY ("OID_STATUS")
	  REFERENCES "FAI"."FAI_ITEM_STATUS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_COLUMN_EXPORT
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_COLUMN_EXPORT" ADD CONSTRAINT "FK_COLEX_TABEXP" FOREIGN KEY ("OID_TABLEEXPORT")
	  REFERENCES "FAI"."FAI_TABLE_EXPORT" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_DISPONIBILITA_REQ_TEMP
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_DISPONIBILITA_REQ_TEMP" ADD CONSTRAINT "FK_DISPREQTEMP_DISP" FOREIGN KEY ("OID_DISPONIBILITATEMP")
	  REFERENCES "FAI"."FAI_DISPONIBILITA_TEMP" ("OID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_DISPONIBILITA_RES_TEMP
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_DISPONIBILITA_RES_TEMP" ADD CONSTRAINT "FK_DISPRESTEMP_QDIS" FOREIGN KEY ("OID_DISPONIBILITAREQTEMP")
	  REFERENCES "FAI"."FAI_DISPONIBILITA_REQ_TEMP" ("OID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_DISPONIBILITA_TEMP
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_DISPONIBILITA_TEMP" ADD CONSTRAINT "FK_DISPTEMP_FORN" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FORNITORE" ("OID") ENABLE;
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_TEMP" ADD CONSTRAINT "FK_DISPTEMP_MAG" FOREIGN KEY ("OID_MAGAZZINO")
	  REFERENCES "FAI"."FAI_MAGAZZINO" ("OID") ENABLE;
  ALTER TABLE "FAI"."FAI_DISPONIBILITA_TEMP" ADD CONSTRAINT "FK_DISPTEMP_STATUS" FOREIGN KEY ("OID_STATUS")
	  REFERENCES "FAI"."FAI_ITEM_STATUS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_FORNITORE_FARMACO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FORNITORE_FARMACO" ADD CONSTRAINT "FK_FORNITFARM_FORNIT" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FORNITORE" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_FORNITORE_PROPERTY
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_FORNITORE_PROPERTY" ADD CONSTRAINT "FK_FORNPROP_FORNITORE" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FORNITORE" ("OID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_GENERIC_TASK_PROPERTY
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_GENERIC_TASK_PROPERTY" ADD CONSTRAINT "FK_GENTSKPROP_GENTASK" FOREIGN KEY ("OID_GENERICTASK")
	  REFERENCES "FAI"."FAI_GENERIC_TASK" ("OID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_MAGAZZINO_PROPERTY
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_MAGAZZINO_PROPERTY" ADD CONSTRAINT "FK_MAGAZPROP_MAGAZZINO" FOREIGN KEY ("OID_MAGAZZINO")
	  REFERENCES "FAI"."FAI_MAGAZZINO" ("OID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_MAGAZZINO_TIPOFARMACO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_MAGAZZINO_TIPOFARMACO" ADD CONSTRAINT "FK_MAGAZZTIPOF_MAG" FOREIGN KEY ("OID_MAGAZZINO")
	  REFERENCES "FAI"."FAI_MAGAZZINO" ("OID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "FAI"."FAI_MAGAZZINO_TIPOFARMACO" ADD CONSTRAINT "FK_MAGAZZTIPOF_TIPOF" FOREIGN KEY ("OID_TIPOFARMACO")
	  REFERENCES "FAI"."FAI_TIPO_FARMACO" ("OID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_ORDINE_IN
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ORDINE_IN" ADD CONSTRAINT "FK_ORDINEIN_OICOLL" FOREIGN KEY ("OID_ORDINEINCOLLECTION")
	  REFERENCES "FAI"."FAI_ORDINE_IN_COLLECTION" ("OID") ENABLE;
  ALTER TABLE "FAI"."FAI_ORDINE_IN" ADD CONSTRAINT "R_8" FOREIGN KEY ("OID_STATUS")
	  REFERENCES "FAI"."FAI_ITEM_STATUS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_ORDINE_IN_COLLECTION
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ORDINE_IN_COLLECTION" ADD CONSTRAINT "FK_FAIORINCL_ST" FOREIGN KEY ("OID_STATUS")
	  REFERENCES "FAI"."FAI_ITEM_STATUS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_ORDINE_IN_RIGA_DETTAGLIO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ORDINE_IN_RIGA_DETTAGLIO" ADD CONSTRAINT "FK_ORDINRDETT_OOR" FOREIGN KEY ("OID_ORDINEIN")
	  REFERENCES "FAI"."FAI_ORDINE_IN" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_ORDINE_OUT
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_ORDINE_OUT" ADD CONSTRAINT "FK_ORDOUT_FORN" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FORNITORE" ("OID") ON DELETE SET NULL ENABLE;
  ALTER TABLE "FAI"."FAI_ORDINE_OUT" ADD CONSTRAINT "FK_ORDOUT_ST" FOREIGN KEY ("OID_STATUS")
	  REFERENCES "FAI"."FAI_ITEM_STATUS" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_RECORD_A_ADDEBITO_FINANZ
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_A_ADDEBITO_FINANZ" ADD CONSTRAINT "FK_RECA_FORNITORE" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FARMACLICK_FORNITORE" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_RECORD_C_CONDIZIONI
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_C_CONDIZIONI" ADD CONSTRAINT "FK_RECC_FORNITORE" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FARMACLICK_FORNITORE" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_RECORD_D_RESET
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_D_RESET" ADD CONSTRAINT "FK_RECD_FORNITORE" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FARMACLICK_FORNITORE" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_RECORD_L_LISTINO
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_L_LISTINO" ADD CONSTRAINT "FK_RECL_FORNITORE" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FARMACLICK_FORNITORE" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_RECORD_R_RAGGR_COND
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_R_RAGGR_COND" ADD CONSTRAINT "FK_RECR_FORNITORE" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FARMACLICK_FORNITORE" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_RECORD_V_RAGGR_COND
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_V_RAGGR_COND" ADD CONSTRAINT "FK_RECV_FORNITORE" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FARMACLICK_FORNITORE" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_RECORD_Z_CHIUSURA
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_RECORD_Z_CHIUSURA" ADD CONSTRAINT "FK_RECZ_FORNITORE" FOREIGN KEY ("OID_FORNITORE")
	  REFERENCES "FAI"."FAI_FARMACLICK_FORNITORE" ("OID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAI_TABLE_EXPORT
--------------------------------------------------------

  ALTER TABLE "FAI"."FAI_TABLE_EXPORT" ADD CONSTRAINT "FK_TABEXP_GRPEXP" FOREIGN KEY ("OID_GROUPEXPORT")
	  REFERENCES "FAI"."FAI_GROUP_EXPORT" ("OID") ENABLE;
