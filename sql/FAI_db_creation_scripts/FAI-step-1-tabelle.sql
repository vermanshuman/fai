
CREATE TABLE FAI_APPROVVIGFARMACO_ORDINRIGA
(
	OID                  INTEGER CONSTRAINT NN_APPRFAROINRIGA_OID NOT NULL ,
	OID_APPROVFARMACO    INTEGER CONSTRAINT NN_APPRFAROINRIGA_APPRF NOT NULL ,
	OID_ORDINEINRIGA     INTEGER CONSTRAINT NN_APPRFAROINRIGA_OINRIGA NOT NULL ,
	QUANTITA             INTEGER CONSTRAINT NN_APPRFAROINRIGA_QUANT NOT NULL 
);

CREATE UNIQUE INDEX UX_APPRFAROINRIGA_OID ON FAI_APPROVVIGFARMACO_ORDINRIGA
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_APPROVVIGFARMACO_ORDINRIGA
	ADD CONSTRAINT  PK_APPRFAROINRIGA PRIMARY KEY (OID);

CREATE UNIQUE INDEX UX_APPRFAROINRIGA_APPRF_OINR ON FAI_APPROVVIGFARMACO_ORDINRIGA
(OID_APPROVFARMACO   ASC,OID_ORDINEINRIGA   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_APPROVVIGFARMACO_ORDINRIGA
ADD CONSTRAINT  UQ_APPRFAROINRIGA_APPRF_OINR UNIQUE (OID_APPROVFARMACO,OID_ORDINEINRIGA);

CREATE INDEX IX_APPRFAROINRIGA_APPRF ON FAI_APPROVVIGFARMACO_ORDINRIGA
(OID_APPROVFARMACO   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_APPRFAROINRIGA_OINRIGA ON FAI_APPROVVIGFARMACO_ORDINRIGA
(OID_ORDINEINRIGA   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_APPROVVIGIONAMENTO_FARMACO
(
	OID                  INTEGER CONSTRAINT NN_APPRFARM_OID NOT NULL ,
	OID_MAGAZZINO        INTEGER NULL ,
	OID_FORNITORE        INTEGER NULL ,
	CODICE_MINSAN        VARCHAR2(9) NULL ,
	CODICE_SOSTITUITO_MINSAN        VARCHAR2(9) NULL ,
	QUANTITA             INTEGER CONSTRAINT NN_APPRFARM_QUANT NOT NULL ,
	DISPONIBILITA_RICHIESTA_REQ DATE CONSTRAINT NN_APPRFARM_DISPRIC NULL ,
	DISPONIBILITA_CONFERMATA_REQ DATE CONSTRAINT NN_APPRFARM_DISPCONF NULL ,
	ID_ORDINE            VARCHAR2(30) NULL ,
	ID_RICEVUTA          VARCHAR2(30) NULL ,
	DISPONIBILITA_RICHIESTA_RES DATE CONSTRAINT NN_APPRFARM_DISPRICOK NULL ,
	DISPONIBILITA_CONFERMATA_RES DATE CONSTRAINT NN_APPRFARM_DISPCONFOK NULL ,
	OID_STATUS           INTEGER DEFAULT  1  CONSTRAINT NN_APPRFARM_STATUS NOT NULL ,
	STATUS_DESCR         CLOB NULL ,
	STATUS_TECH_DESCR    CLOB NULL ,
	PREZZO_UNITARIO      NUMBER(22,4) NULL ,
	PREZZO_TOTALE        NUMBER(22,4) NULL ,
	ALIQUOTA_IVA         NUMBER(22,4) NULL ,
	ALIQUOTA_IVA_INCLUSA CHAR(1) CONSTRAINT NN_APPRFARM_IVAINCL NULL ,
	OID_ORDINEOUT        INTEGER NULL ,
	STATUS_UPDATED_TS    DATE DEFAULT  SYSDATE  CONSTRAINT NN_APPRFARM_STATUSUPDTS NOT NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_APPRFARM_CRTS NOT NULL ,
	CODICE_EAN           VARCHAR2(13) NULL ,
	MAGAZZINO_ACRONYM    VARCHAR2(50) NULL ,
CONSTRAINT CK_APPRFARM_MINSANEAN CHECK ( CODICE_MINSAN IS NOT NULL OR CODICE_EAN IS NOT NULL )
);

CREATE UNIQUE INDEX UX_APPRFARM_OID ON FAI_APPROVVIGIONAMENTO_FARMACO
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_APPROVVIGIONAMENTO_FARMACO
	ADD CONSTRAINT  PK_APPRFARM PRIMARY KEY (OID);

CREATE INDEX IX_APPRFARM_FORN ON FAI_APPROVVIGIONAMENTO_FARMACO
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_APPRFARM_MAGAZZ ON FAI_APPROVVIGIONAMENTO_FARMACO
(OID_MAGAZZINO   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_APPRFARM_ORDOUT ON FAI_APPROVVIGIONAMENTO_FARMACO
(OID_ORDINEOUT   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_APPRFARM_STATUS ON FAI_APPROVVIGIONAMENTO_FARMACO
(OID_STATUS   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_COLUMN_EXPORT
(
	OID                  INTEGER CONSTRAINT NN_COLEX_OID NOT NULL ,
	COLUMN_IDX           INTEGER CONSTRAINT NN_COLEX_IDX NOT NULL ,
	COLUMN_NAME_ORACLE   VARCHAR2(30) CONSTRAINT NN_COLEX_NAMEORA NOT NULL ,
	COLUMN_NAME_EXPORT   VARCHAR2(100) CONSTRAINT NN_COLEX_NAMEEXP NOT NULL ,
	REQUIRED             CHAR(1) CONSTRAINT NN_COLEX_REQ NOT NULL  CONSTRAINT  CK_COLEX_REQ CHECK (REQUIRED IN ('S', 'N')),
	SQL_TYPE             VARCHAR2(30) CONSTRAINT NN_COLEX_SQLTYP NOT NULL ,
	SQL_TYPE_LEN         INTEGER NULL ,
	SQL_TYPE_SCALE       INTEGER NULL ,
	DATE_FORMAT_PATTERN  VARCHAR2(100) NULL ,
	NUMB_FORMAT_PATTERN  VARCHAR2(100) NULL ,
	NUMB_FORMAT_GRPCH    CHAR(1) NULL ,
	NUMB_FORMAT_DECCH    CHAR(1) NULL ,
	OID_TABLEEXPORT      INTEGER CONSTRAINT NN_COLEX_TABEXP NOT NULL ,
	TEXT_FORMAT_TYPE     CHAR(2) NULL  CONSTRAINT  CK_COLEX_TXTFMTTYP CHECK (TEXT_FORMAT_TYPE IN ('RE', 'SR')),
	TEXT_FORMAT_SEARCH   VARCHAR2(50) NULL ,
	TEXT_FORMAT_REPLACE  VARCHAR2(50) NULL ,
	INDEX_NAME_ORACLE    VARCHAR2(30) NULL ,
	INDEX_NAME_TABLESPACE VARCHAR2(30) NULL ,
CONSTRAINT CK_COLEX_TXTFMT CHECK ( (TEXT_FORMAT_TYPE IS NOT NULL AND TEXT_FORMAT_SEARCH IS NOT NULL AND TEXT_FORMAT_REPLACE IS NOT NULL)
OR
(TEXT_FORMAT_TYPE IS NULL AND TEXT_FORMAT_SEARCH IS NULL AND TEXT_FORMAT_REPLACE IS NULL)
 ),
CONSTRAINT CK_COLEX_NUMFMT CHECK ( (NUMB_FORMAT_PATTERN IS NOT NULL AND NUMB_FORMAT_GRPCH IS NOT NULL AND NUMB_FORMAT_DECCH IS NOT NULL)
OR
(NUMB_FORMAT_PATTERN IS NULL AND NUMB_FORMAT_GRPCH IS NULL AND NUMB_FORMAT_DECCH IS NULL) )
);

CREATE UNIQUE INDEX UX_COLEX_OID ON FAI_COLUMN_EXPORT
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_COLUMN_EXPORT
	ADD CONSTRAINT  UX_COLEX_OID PRIMARY KEY (OID);

CREATE INDEX IX_COLEX_TABEXP ON FAI_COLUMN_EXPORT
(OID_TABLEEXPORT   ASC);

CREATE TABLE FAI_DISPONIBILITA_REQ_TEMP
(
	OID                  INTEGER CONSTRAINT NN_DISPREQTEMP_OID NOT NULL ,
	OID_DISPONIBILITATEMP INTEGER CONSTRAINT NN_DISPREQTEMP_DISP NOT NULL ,
	CODICE_MINSAN        VARCHAR2(9) NULL ,
	CODICE_EAN           VARCHAR2(13) NULL ,
	QUANTITA_RICHIESTA   INTEGER CONSTRAINT NN_DISPREQTEMP_QRIC NOT NULL ,
CONSTRAINT CK_DISPRICTEMP_MINSANEAN CHECK ( CODICE_MINSAN IS NOT NULL OR CODICE_EAN IS NOT NULL )
);

CREATE UNIQUE INDEX UX_DISPREQTEMP_OID ON FAI_DISPONIBILITA_REQ_TEMP
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_DISPONIBILITA_REQ_TEMP
	ADD CONSTRAINT  PK_DISPREQTEMP PRIMARY KEY (OID);

CREATE INDEX IX_DISPREQTEMP_DISP ON FAI_DISPONIBILITA_REQ_TEMP
(OID_DISPONIBILITATEMP   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_DISPONIBILITA_RES_TEMP
(
	OID                  INTEGER CONSTRAINT NN_DISPRESTEMP_OID NOT NULL ,
	QUANTITA_DISPONIBILE INTEGER CONSTRAINT NN_DISPRESTEMP_QDIS NOT NULL ,
	OID_DISPONIBILITAREQTEMP INTEGER CONSTRAINT NN_DISPRESTEMP_REQ NOT NULL 
);

CREATE UNIQUE INDEX UX_DISPRESTEMP_OID ON FAI_DISPONIBILITA_RES_TEMP
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_DISPONIBILITA_RES_TEMP
	ADD CONSTRAINT  PK_DISPRESTEMP PRIMARY KEY (OID);

CREATE INDEX IX_DISPRESTEMP_QDIS ON FAI_DISPONIBILITA_RES_TEMP
(OID_DISPONIBILITAREQTEMP   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_DISPONIBILITA_TEMP
(
	OID                  INTEGER CONSTRAINT NN_DISPTEMP_OID NOT NULL ,
	OID_FORNITORE        INTEGER NULL ,
	OID_MAGAZZINO        INTEGER NULL ,
	REQUEST_TS           DATE DEFAULT  SYSDATE  CONSTRAINT NN_DISPTEMP_REQTS NOT NULL ,
	REQUEST_RAW_DATA     CLOB NULL ,
	RESPONSE_TS          DATE NULL ,
	RESPONSE_RAW_DATA    CLOB NULL ,
	STATUS_DESCR         CLOB NULL ,
	STATUS_TECH_DESCR    CLOB NULL ,
	STATUS_UPDATED_TS    DATE NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  NULL ,
	OID_STATUS           INTEGER NULL ,
CONSTRAINT CK_DISPTEMP_FORNMAG CHECK ( (OID_FORNITORE IS NOT NULL AND OID_MAGAZZINO IS NULL) OR (OID_FORNITORE IS NULL AND OID_MAGAZZINO IS NOT NULL) )
);

CREATE UNIQUE INDEX UX_DISPTEMP_OID ON FAI_DISPONIBILITA_TEMP
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_DISPONIBILITA_TEMP
	ADD CONSTRAINT  PK_DISPTEMP PRIMARY KEY (OID);

CREATE INDEX IX_DISPTEMP_FORN ON FAI_DISPONIBILITA_TEMP
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_DISPTEMP_MAG ON FAI_DISPONIBILITA_TEMP
(OID_MAGAZZINO   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_DISPTEMP_STATUS ON FAI_DISPONIBILITA_TEMP
(OID_STATUS   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_EXPORT_CONFIG
(
	FTP_PROTOCOL         VARCHAR2(5) CONSTRAINT NN_EXPCFG_PROTOCL NOT NULL ,
	FTP_HOST             VARCHAR2(200) CONSTRAINT NN_EXPCFG_HOST NOT NULL ,
	FTP_PORT             INTEGER CONSTRAINT NN_EXPCFG_PORT NOT NULL ,
	FTP_DIR              VARCHAR2(200) CONSTRAINT NN_EXPCFG_DIR NOT NULL ,
	FTP_LOGIN            VARCHAR2(200) CONSTRAINT NN_EXPCFG_LOGIN NOT NULL ,
	FTP_PASSWORD         VARCHAR2(200) CONSTRAINT NN_EXPCFG_PASS NOT NULL ,
	FTP_PASSWORD_ENCR    CHAR(1) CONSTRAINT NN_EXPCFG_PASSENCR NOT NULL  CONSTRAINT  CK_EXPCFG_PASSENCR CHECK (FTP_PASSWORD_ENCR IN ('S', 'N')),
	EXPORT_TIME          DATE CONSTRAINT NN_EXPCFG_EXPTIME NOT NULL  CONSTRAINT  CK_EXPCFG_EXPTIME CHECK (TRUNC(EXPORT_TIME) = TO_DATE('1970-01-01', 'YYYY-MM-DD')
),
	CSV_FIELD_SEP        CHAR(1) NULL ,
	CSV_FIELD_SEP_REPLACEMENT VARCHAR(10) NULL ,
	CSV_HEADER_EXPORT_ENABLED CHAR(1) NULL ,
	CSV_LINE_SEP         VARCHAR(2) NULL ,
	LAST_RUN_START_TS    DATE NULL ,
	LAST_RUN_END_TS      DATE NULL ,
	LAST_RUN_DONE        CHAR(1) NULL  CONSTRAINT  CK_VR_EXPCFG_LASTRDONE CHECK (LAST_RUN_DONE in ('S', 'N')),
	LAST_RUN_DESCR       VARCHAR2(2048) NULL 
);

CREATE TABLE FAI_FARMACO
(
	OID                  INTEGER CONSTRAINT NN_FAIFARMACO_OID NOT NULL ,
	IS_DELETED           CHAR(1) CONSTRAINT NN_FAIFARMACO_ISDELETED NULL ,
	CODICE_MINSAN        VARCHAR2(9) NULL ,
	CODICE_EAN           VARCHAR2(13) NULL ,
	DESCR_PROD           VARCHAR2(30) NULL ,
	ANAGRAFICA           VARCHAR2(58) NULL ,
	DESCR_TIPO_PROD      VARCHAR2(30) NULL ,
	DESCR_ESTESA_TIPO_PROD VARCHAR2(120) NULL 
);

CREATE UNIQUE INDEX XPKFAI_FARMACO ON FAI_FARMACO
(OID   ASC);

ALTER TABLE FAI_FARMACO
	ADD CONSTRAINT  XPKFAI_FARMACO PRIMARY KEY (OID);

CREATE TABLE FAI_FORNITORE
(
	OID                  INTEGER CONSTRAINT NN_FORNITORE_OID NOT NULL ,
	CODICE               VARCHAR2(100) CONSTRAINT NN_FORNITORE_CODICE NOT NULL ,
	CODICE_FARMACLICK    VARCHAR2(20) NULL ,
	SUPPLIERSERVICE_CLASS_NAME VARCHAR2(500) NULL 
);

CREATE UNIQUE INDEX UX_FORNITORE_OID ON FAI_FORNITORE
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_FORNITORE
	ADD CONSTRAINT  PK_FORNITORE PRIMARY KEY (OID);

CREATE TABLE FAI_FORNITORE_FARMACO
(
	OID                  INTEGER CONSTRAINT NN_FORNITFARM_OID NOT NULL ,
	CODICE_MINSAN        VARCHAR2(9) CONSTRAINT NN_FORNITFARM_CMINSAN NOT NULL ,
	LINE_ID              INTEGER CONSTRAINT NN_FORNITFARM_LINEID NOT NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_FORNITFARM_FORNIT NOT NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_FORNITFARM_CRTS NOT NULL 
);

CREATE UNIQUE INDEX UX_FORNITFARM_OID ON FAI_FORNITORE_FARMACO
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_FORNITORE_FARMACO
	ADD CONSTRAINT  UQ_FORNITFARM_OID PRIMARY KEY (OID);

CREATE UNIQUE INDEX UX_FORNITFARM_FORMSAN ON FAI_FORNITORE_FARMACO
(OID_FORNITORE   ASC,CODICE_MINSAN   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_FORNITORE_FARMACO
ADD CONSTRAINT  UQ_FORNITFARM_FORMSAN UNIQUE (OID_FORNITORE,CODICE_MINSAN);

CREATE UNIQUE INDEX UX_FORNITFARM_FORNLID ON FAI_FORNITORE_FARMACO
(OID_FORNITORE   ASC,LINE_ID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_FORNITORE_FARMACO
ADD CONSTRAINT  UQ_FORNITFARM_FORNLID UNIQUE (OID_FORNITORE,LINE_ID);

CREATE INDEX IX_FORNITFARM_FORNIT ON FAI_FORNITORE_FARMACO
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_FORNITORE_PROPERTY
(
	OID                  INTEGER CONSTRAINT NN_FORNPROP_OID NOT NULL ,
	KEY                  VARCHAR2(100) CONSTRAINT NN_FORNPROP_KEY NOT NULL ,
	VALUE                CLOB CONSTRAINT NN_FORNPROP_VALUE NOT NULL ,
	OID_FORNITORE        INTEGER NULL 
);

CREATE UNIQUE INDEX UX_FORNPROP_OID ON FAI_FORNITORE_PROPERTY
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_FORNITORE_PROPERTY
	ADD CONSTRAINT  PK_FORNPROP PRIMARY KEY (OID);

CREATE INDEX IX_FORNPROP_FORNITORE ON FAI_FORNITORE_PROPERTY
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_GENERIC_TASK
(
	OID                  INTEGER CONSTRAINT NN_GENTASK_OID NOT NULL ,
	ACRONYM              VARCHAR2(50) CONSTRAINT NN_GENTASK_ACR NOT NULL ,
	DESCR                VARCHAR2(200) NULL ,
	CLASS_NAME           VARCHAR2(500) CONSTRAINT NN_GENTASK_CLNAME NOT NULL ,
	SCHEDULED_TIMES      VARCHAR2(100) NULL ,
	SCHEDULED_SMTWTFS    CHAR(7) NULL  CONSTRAINT  CK_GENTASK_SMTWTFS CHECK (REGEXP_LIKE(SCHEDULED_SMTWTFS, '^[X\-]*$')),
	LAST_RUN_START_TS    DATE NULL ,
	LAST_RUN_END_TS      DATE NULL ,
	LAST_RUN_DONE        CHAR(1) NULL ,
	LAST_RUN_DESCR       CLOB NULL ,
	ENABLED              CHAR(1) CONSTRAINT NN_GENTASK_ENABL NOT NULL  CONSTRAINT  CK_GENTASK_ENABL CHECK (ENABLED IN ('S', 'N')),
CONSTRAINT CK_GENTASK_TMS_SMTWTFS CHECK ( (SCHEDULED_TIMES IS NULL AND SCHEDULED_SMTWTFS IS NULL) OR (SCHEDULED_TIMES IS NOT NULL AND SCHEDULED_SMTWTFS IS NOT NULL)  )
);

CREATE UNIQUE INDEX UX_GENTASK_OID ON FAI_GENERIC_TASK
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_GENERIC_TASK
	ADD CONSTRAINT  PK_GENTASK PRIMARY KEY (OID);

CREATE UNIQUE INDEX UX_GENTASK_ACR ON FAI_GENERIC_TASK
(ACRONYM   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_GENERIC_TASK
ADD CONSTRAINT  UQ_GENTASK_ACR UNIQUE (ACRONYM);

CREATE TABLE FAI_GENERIC_TASK_PROPERTY
(
	OID                  INTEGER CONSTRAINT NN_GENTSKPROP_OID NOT NULL ,
	KEY                  VARCHAR2(100) CONSTRAINT NN_GENTSKPROP_KEY NOT NULL ,
	VALUE                CLOB CONSTRAINT NN_GENTSKPROP_VALUE NOT NULL ,
	OID_GENERICTASK      INTEGER CONSTRAINT NN_GENTSKPROP_GENTASK NOT NULL 
);

CREATE UNIQUE INDEX UX_GENTSKPROP_OID ON FAI_GENERIC_TASK_PROPERTY
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_GENERIC_TASK_PROPERTY
	ADD CONSTRAINT  UQ_GENTSKPROP PRIMARY KEY (OID);

CREATE UNIQUE INDEX IX_GENTSKPROP_GTASK_KEY ON FAI_GENERIC_TASK_PROPERTY
(OID_GENERICTASK   ASC,KEY   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_GENERIC_TASK_PROPERTY
ADD CONSTRAINT  UQ_GENTSKPROP_GTASK_KEY UNIQUE (OID_GENERICTASK,KEY);

CREATE INDEX IX_GENTSKPROP_GENTASK ON FAI_GENERIC_TASK_PROPERTY
(OID_GENERICTASK   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_GROUP_EXPORT
(
	OID                  INTEGER CONSTRAINT NN_GRPEXP_OID NOT NULL ,
	GROUP_IDX            INTEGER CONSTRAINT NN_GRPEXP_IDX NOT NULL ,
	GROUP_NAME_EXPORT    VARCHAR(100) CONSTRAINT NN_GRPEXP_NAMEEXP NOT NULL 
);

CREATE UNIQUE INDEX UX_GRPEXP_OID ON FAI_GROUP_EXPORT
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_GROUP_EXPORT
	ADD CONSTRAINT  PK_GRPEXP PRIMARY KEY (OID);

CREATE TABLE FAI_IMPORT_CONFIG
(
	OID                  INTEGER CONSTRAINT NN_IMPPRO_OID NOT NULL ,
	ACRONYM              VARCHAR2(50) CONSTRAINT NN_IMPPRO_ACRONYM NOT NULL ,
	DESCR                VARCHAR2(200) NULL ,
	SERVICE_LOGIN        VARCHAR2(200) NULL ,
	SERVICE_PASS         VARCHAR2(200) NULL ,
	SERVICE_PASS_ENCR    CHAR(1) CONSTRAINT NN_IMPPRO_PASSENCR NOT NULL  CONSTRAINT  CK_IMPPRO_PASSENCR CHECK (SERVICE_PASS_ENCR IN ('S', 'N')),
	SERVICE_QUERY_TIME   DATE CONSTRAINT NN_IMPPRO_SQTIME NOT NULL  CONSTRAINT  CK_IMPPRO_SQTIME CHECK (TRUNC(SERVICE_QUERY_TIME) = TO_DATE('1970-01-01', 'YYYY-MM-DD')),
	SERVICE_QUERY_SMTWTFS CHAR(7) DEFAULT  'CCCCCCC'  CONSTRAINT NN_IMPPRO_SQSMTWTFS NOT NULL  CONSTRAINT  CK_IMPPRO_SQSMTWTFS CHECK (REGEXP_LIKE(SERVICE_QUERY_SMTWTFS, '^[CVRA\-]*$')),
	SERVICE_QUERY_URL    VARCHAR2(2048) NULL ,
	SERVICE_RESUME_PREV_SESS CHAR(1) DEFAULT  'N'  CONSTRAINT NN_IMPPRO_RESUMEPREVS NOT NULL  CONSTRAINT  CK_IMPPRO_RESUMEPREVS CHECK (SERVICE_RESUME_PREV_SESS IN ('S', 'N', 'A')),
	LAST_RUN_START_TS    DATE NULL ,
	LAST_RUN_END_TS      DATE NULL ,
	LAST_RUN_TYPE        CHAR(1) NULL  CONSTRAINT  CK_IMPPRO_LASTRTYPE CHECK (LAST_RUN_TYPE IN ('C', 'V','A', 'R')),
	LAST_RUN_DONE        CHAR(1) NULL  CONSTRAINT  CK_IMPPRO_LASTRDONE CHECK (LAST_RUN_DONE IN ('S', 'N')),
	LAST_RUN_DESCR       VARCHAR2(2048) NULL ,
	CLASS_NAME           VARCHAR2(500) CONSTRAINT NN_IMPPRO_CLASSNAME NOT NULL ,
	SERVICE_QUERY_ZIPPED_CONTENT CHAR(1) DEFAULT  'N'  CONSTRAINT NN_IMPPRO_SQZIPPEDC NOT NULL  CONSTRAINT  CK_IMPPRO_SQZIPPEDC CHECK (SERVICE_QUERY_ZIPPED_CONTENT IN ('S', 'N')),
	SERVICE_PASS_SECOND_LEVEL         VARCHAR2(200) NULL
);

CREATE UNIQUE INDEX UX_IMPPRO_OID ON FAI_IMPORT_CONFIG
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_IMPORT_CONFIG
	ADD CONSTRAINT  PK_IMPPRO PRIMARY KEY (OID);

CREATE UNIQUE INDEX UX_IMPPRO_ACRONYM ON FAI_IMPORT_CONFIG
(ACRONYM   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_IMPORT_CONFIG
ADD CONSTRAINT  UQ_IMPPRO_ACRONYM UNIQUE (ACRONYM);


CREATE TABLE FAI_ITEM_STATUS
(
	OID                  INTEGER CONSTRAINT NN_ITEMSTAT_OID NOT NULL ,
	ACRONYM              VARCHAR2(20) CONSTRAINT NN_ITEMSTAT_ACR NOT NULL ,
	DESCR                VARCHAR2(100) CONSTRAINT NN_ITEMSTAT_DESCR NOT NULL 
);

CREATE UNIQUE INDEX UX_ITEMSTAT_OID ON FAI_ITEM_STATUS
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_ITEM_STATUS
	ADD CONSTRAINT  PK_ITEMSTAT PRIMARY KEY (OID);

CREATE TABLE FAI_LISTINI_DISPONIBILITA_TEMP
(
	OID_FORNITORE        INTEGER CONSTRAINT NN_LISTDISPTMP_FORN NOT NULL ,
	CODICE_MINSAN        VARCHAR2(9) NULL ,
	CODICE_EAN           VARCHAR2(13) NULL ,
	PREZZO               NUMBER(22,4) CONSTRAINT NN_LISTDISPTMP_PREZZO NOT NULL 
);

CREATE UNIQUE INDEX UX_LISTDISPTMP_FMINSANEAN ON FAI_LISTINI_DISPONIBILITA_TEMP
(OID_FORNITORE   ASC,CODICE_MINSAN   ASC,CODICE_EAN   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_LISTINI_DISPONIBILITA_TEMP
ADD CONSTRAINT  UQ_LISTDISPTMP_FMINSAN UNIQUE (OID_FORNITORE,CODICE_MINSAN,CODICE_EAN);

CREATE TABLE FAI_LOG
(
	OID                  INTEGER CONSTRAINT NN_LOG_OID NOT NULL ,
	LOG_LEVEL            CHAR(1) CONSTRAINT NN_LOG_LEVEL NOT NULL  CONSTRAINT  CK_LOG_LEVEL CHECK (LOG_LEVEL IN ('T', 'D', 'I', 'W', 'E', 'F')),
	TS                   DATE DEFAULT  SYSDATE  CONSTRAINT NN_LOG_TS NOT NULL ,
	THREAD               VARCHAR2(20) CONSTRAINT NN_LOG_THREAD NOT NULL ,
	MESSAGE              CLOB NULL ,
	TECH_MESSAGE         CLOB NULL ,
	STACK_TRACE          CLOB NULL 
);

CREATE UNIQUE INDEX UX_LOG_OID ON FAI_LOG
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_LOG
	ADD CONSTRAINT  PK_LOG PRIMARY KEY (OID);

CREATE TABLE FAI_MAGAZZINO
(
	OID                  INTEGER CONSTRAINT NN_MAGAZ_OID NOT NULL ,
	ACRONYM              VARCHAR2(50) CONSTRAINT NN_MAGAZ_ACR NOT NULL ,
	DESCR                VARCHAR2(100) NULL ,
	COMUNE               VARCHAR2(20) NULL ,
	PROVINCIA            VARCHAR2(2) NULL ,
	REGIONE              VARCHAR2(20) NULL ,
	CAP                  VARCHAR2(10) NULL ,
	INDIRIZZO            VARCHAR2(100) NULL ,
	SUPPLIERSERVICE_CLASS_NAME VARCHAR2(500) NULL 
);

CREATE UNIQUE INDEX UX_MAGAZ_OID ON FAI_MAGAZZINO
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_MAGAZZINO
	ADD CONSTRAINT  UX_MAGAZ_OID PRIMARY KEY (OID);

CREATE TABLE FAI_MAGAZZINO_PROPERTY
(
	OID                  INTEGER CONSTRAINT NN_MAGAZPROP_OID NOT NULL ,
	KEY                  VARCHAR2(100) CONSTRAINT NN_MAGAZPROP_KEY NOT NULL ,
	VALUE                CLOB CONSTRAINT NN_MAGAZPROP_VALUE NOT NULL ,
	OID_MAGAZZINO        INTEGER CONSTRAINT NN_MAGAZPROP_MAGAZZINO NOT NULL 
);

CREATE UNIQUE INDEX UX_MAGAZPROP_OID ON FAI_MAGAZZINO_PROPERTY
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_MAGAZZINO_PROPERTY
	ADD CONSTRAINT  PK_MAGAZPROP PRIMARY KEY (OID);

CREATE INDEX IX_MAGAZPROP_MAGAZZINO ON FAI_MAGAZZINO_PROPERTY
(OID_MAGAZZINO   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_MAGAZZINO_TIPOFARMACO
(
	OID                  INTEGER CONSTRAINT NN_MAGAZZTIPOF_OID NOT NULL ,
	OID_MAGAZZINO        INTEGER CONSTRAINT NN_MAGAZZTIPOF_MAG NOT NULL ,
	OID_TIPOFARMACO      INTEGER CONSTRAINT NN_MAGAZZTIPOF_TIPOF NOT NULL ,
	CONTEXT              VARCHAR2(30) NULL 
);

CREATE UNIQUE INDEX UX_MAGAZZTIPOF_OID ON FAI_MAGAZZINO_TIPOFARMACO
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_MAGAZZINO_TIPOFARMACO
	ADD CONSTRAINT  PK_MAGAZZTIPOF PRIMARY KEY (OID);

CREATE INDEX IX_MAGAZZTIPOF_MAG ON FAI_MAGAZZINO_TIPOFARMACO
(OID_MAGAZZINO   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_MAGAZZTIPOF_TIPOF ON FAI_MAGAZZINO_TIPOFARMACO
(OID_TIPOFARMACO   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_NO_GAP_SEQUENCE
(
	OID                  INTEGER CONSTRAINT NN_NOGAPSEQ_OID NOT NULL ,
	ACRONYM              VARCHAR2(30) CONSTRAINT NN_NOGAPSEQ_ACR NOT NULL ,
	LAST_VALUE           INTEGER CONSTRAINT NN_NOGAPSEQ_LASTVAL NOT NULL 
);

CREATE UNIQUE INDEX UX_NOGAPSEQ_ACR ON FAI_NO_GAP_SEQUENCE
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_NO_GAP_SEQUENCE
	ADD CONSTRAINT  UQ_NOGAPSEQ_ACR PRIMARY KEY (OID);

CREATE TABLE FAI_ORDINE_IN
(
	OID                  INTEGER CONSTRAINT NN_ORDINEIN_OID NOT NULL ,
	ID_VENDITA           INTEGER CONSTRAINT NN_ORDINEIN_IDVEND NOT NULL ,
	ID_UTENTE            INTEGER NULL ,
	NOME_COMPLETO_ACQUIRENTE VARCHAR2(100) NULL ,
	NUMERO_TELEFONO_ACQUIRENTE VARCHAR2(30) NULL ,
	EMAIL_ACQUIRENTE     VARCHAR2(50) NULL ,
	INDIRIZZO_ACQUIRENTE_1 VARCHAR2(100) NULL ,
	INDIRIZZO_ACQUIRENTE_2 VARCHAR2(100) NULL ,
	CITTA_ACQUIRENTE     VARCHAR2(50) NULL ,
	STATO_ACQUIRENTE     VARCHAR2(50) NULL ,
	CAP_ACQUIRENTE       VARCHAR2(10) NULL ,
	PAESE_ACQUIRENTE     VARCHAR2(50) NULL ,
	QUANTITA             INTEGER NULL ,
	SPEDIZIONE_E_IMBALLAGGIO NUMBER(22,4) NULL ,
	ASSICURAZIONE        NUMBER(22,4) NULL ,
	TARIFFA_PER_CONTRASSEGNO NUMBER(22,4) NULL ,
	PREZZO_TOTALE        NUMBER(22,4) NULL ,
	METODO_DI_PAGAMENTO  INTEGER NULL ,
	DATA_DI_VENDITA      DATE NULL ,
	DATA_DEL_MODULO_DI_PAGAMENTO DATE NULL ,
	DATA_PAGAMENTO       DATE NULL ,
	DATA_SPEDIZIONE      DATE NULL ,
	DATA_FATTURA         DATE NULL ,
	NUMERO_FATTURA       VARCHAR2(30) NULL ,
	FEEDBACK_LASCIATO    CHAR(1) NULL ,
	FEEDBACK_RICEVUTO    CHAR(1) NULL ,
	MEMO_PER_USO_PERSONALE VARCHAR2(50) NULL ,
	CAMPO_PRIVATO        INTEGER NULL ,
	ID_TRANSAZIONE_PAYPAL VARCHAR2(30) NULL ,
	SERVIZIO_DI_SPEDIZIONE INTEGER NULL ,
	OPZIONE_CONTRASSEGNO CHAR(1) NULL ,
	ID_TRANSAZIONE       INTEGER NULL ,
	ID_ORDINE            INTEGER NULL ,
	ALIQUOTA_IVA_INCLUSA INTEGER NULL ,
	DETTAGLI_VARIAZIONE  VARCHAR2(60) NULL ,
	AZIENDA_ACQUIRENTE   VARCHAR2(50) NULL ,
	NOME_COMPLETO_FATTURAZIONE VARCHAR2(50) NULL ,
	AZIENDA_FATTURAZIONE VARCHAR2(50) NULL ,
	CF_FATTURAZIONE      VARCHAR2(20) NULL ,
	PIVA_FATTURAZIONE    VARCHAR2(30) NULL ,
	INDIRIZZO_FATTURAZIONE VARCHAR2(100) NULL ,
	CITTA_FATTURAZIONE   VARCHAR2(50) NULL ,
	STATO_FATTURAZIONE   VARCHAR2(50) NULL ,
	CAP_FATTURAZIONE     VARCHAR2(10) NULL ,
	PAESE_FATTURAZIONE   VARCHAR2(50) NULL ,
	CODICEFE             VARCHAR2(30) NULL ,
	PEC                  VARCHAR2(30) NULL ,
	NOTE                 CLOB NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_ORDINEIN_CRTS NOT NULL ,
	OID_STATUS           INTEGER DEFAULT  1  CONSTRAINT NN_ORDINEIN_ST NOT NULL ,
	STATUS_DESCR         CLOB NULL ,
	STATUS_TECH_DESCR    CLOB NULL ,
	OID_ORDINEINCOLLECTION INTEGER CONSTRAINT NN_ORDINEIN_OICOLL NOT NULL ,
	STATUS_UPDATED_TS    DATE DEFAULT  SYSDATE  CONSTRAINT NN_ORDINEIN_STUPTS NOT NULL 
);

CREATE UNIQUE INDEX UX_ORDINEIN_OID ON FAI_ORDINE_IN
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_ORDINE_IN
	ADD CONSTRAINT  PK_ORDINEIN PRIMARY KEY (OID);

CREATE INDEX IX_ORDINEIN_OICOLL ON FAI_ORDINE_IN
(OID_ORDINEINCOLLECTION   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_ORDINEIN_ST ON FAI_ORDINE_IN
(OID_STATUS   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_ORDINE_IN_COLLECTION
(
	OID                  INTEGER CONSTRAINT NN_FAIORINCL_OID NOT NULL ,
	INPUT_RESOURCE       VARCHAR2(100) NULL ,
	INPUT_RESOURCE_FULL_PATH VARCHAR2(500) NULL ,
	BATCH_ID             INTEGER CONSTRAINT NN_FAIORINCL_BATID NOT NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_FAIORINCL_CRTS NOT NULL ,
	OID_STATUS           INTEGER DEFAULT  1  CONSTRAINT NN_FAIORINCL_ST NOT NULL ,
	STATUS_DESCR         CLOB NULL ,
	STATUS_TECH_DESCR    CLOB NULL ,
	STATUS_UPDATED_TS    DATE DEFAULT  SYSDATE  CONSTRAINT NN_FAIORINCL_STUPDTS NOT NULL ,
	UNIQUE_ID            VARCHAR2(100) CONSTRAINT NN_FAIORINCL_UID NOT NULL 
);

CREATE UNIQUE INDEX UX_FAIORINCL_OID ON FAI_ORDINE_IN_COLLECTION
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_ORDINE_IN_COLLECTION
	ADD CONSTRAINT  UX_FAIORINCL_OID PRIMARY KEY (OID);

CREATE UNIQUE INDEX UX_FAIORINCL_UID ON FAI_ORDINE_IN_COLLECTION
(UNIQUE_ID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_ORDINE_IN_COLLECTION
ADD CONSTRAINT  UQ_FAIORINCL_UID UNIQUE (UNIQUE_ID);

CREATE INDEX IX_FAIORINCL_ST ON FAI_ORDINE_IN_COLLECTION
(OID_STATUS   ASC);

CREATE TABLE FAI_ORDINE_IN_RIGA_DETTAGLIO
(
	OID                  INTEGER CONSTRAINT NN_ORDINRDETT_OID NOT NULL ,
	NUMERO_OGGETTO       VARCHAR(30) CONSTRAINT NN_ORDINRDETT_NOGG NOT NULL ,
	TITOLO_INSERZIONE    VARCHAR2(100) NULL ,
	ETICHETTA_PERSONALIZZATA VARCHAR2(100) NULL ,
	QUANTITA             INTEGER CONSTRAINT NN_ORDINRDETT_QT NOT NULL ,
	PREZZO_FINALE        NUMBER(22,4) CONSTRAINT NN_ORDINRDETT_PRZF NOT NULL ,
	ALIQUOTA_IVA_INCLUSA INTEGER CONSTRAINT NN_ORDINRDETT_IVAI NOT NULL ,
	ID_PRODOTTO_UNIVOCO  VARCHAR2(50) NULL ,
	PRODUCTIDTYPE        VARCHAR2(20) NULL ,
	PRODUCTIDVALUE_2     NUMBER(22,4) NULL ,
	PRODUCTIDVALUE       NUMBER(22,4) NULL ,
	DETTAGLI_VARIAZIONE  VARCHAR2(60) NULL ,
	OID_ORDINEIN         INTEGER CONSTRAINT NN_ORDINRDETT_OOR NOT NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_ORDINRDETT_CRTS NOT NULL ,
	CODICE_MINSAN        VARCHAR2(9) NULL ,
	CODICE_EAN           VARCHAR2(13) NULL ,
	IS_COUPON            CHAR(1) CONSTRAINT NN_ORDINRDETT_ISCOUP NOT NULL  CONSTRAINT  CK_ORDINRDETT_ISCOUP CHECK (IS_COUPON IN ('S', 'N')),
	MAGAZZINO_ACRONYM    VARCHAR2(50) NULL 
);

CREATE UNIQUE INDEX UX_ORDINRDETT_OID ON FAI_ORDINE_IN_RIGA_DETTAGLIO
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_ORDINE_IN_RIGA_DETTAGLIO
	ADD CONSTRAINT  PK_ORDINRDETT PRIMARY KEY (OID);

CREATE INDEX IX_ORDINRDETT_OOR ON FAI_ORDINE_IN_RIGA_DETTAGLIO
(OID_ORDINEIN   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_ORDINE_OUT
(
	OID                  INTEGER CONSTRAINT NN_ORDOUT_OID NOT NULL ,
	ID_ORDINE            VARCHAR2(20) NULL ,
	ID_RICEVUTA          VARCHAR2(20) NULL ,
	PREZZO_TOTALE        NUMBER(22,4) NULL ,
	ALIQUOTA_IVA         NUMBER(22,4) NULL ,
	ALIQUOTA_IVA_INCLUSA NUMBER(22,4) NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_ORDOUT_FORN NOT NULL ,
	OID_STATUS           INTEGER DEFAULT  1  CONSTRAINT NN_ORDOUT_ST NOT NULL ,
	STATUS_DESCR         CLOB NULL ,
	STATUS_TECH_DESCR    CLOB NULL ,
	STATUS_UPDATED_TS    DATE DEFAULT  SYSDATE  CONSTRAINT NN_ORDOUT_UPDST NOT NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_ORDOUT_CREATS NOT NULL,
	REQUEST_XML          CLOB NULL ,
	RESPONSE_XML         CLOB NULL
);

CREATE UNIQUE INDEX UX_ORDOUT_OID ON FAI_ORDINE_OUT
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_ORDINE_OUT
	ADD CONSTRAINT  PK_ORDOUT PRIMARY KEY (OID);

CREATE INDEX IX_ORDOUT_FORN ON FAI_ORDINE_OUT
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_ORDOUT_ST ON FAI_ORDINE_OUT
(OID_STATUS   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_TABLE_EXPORT
(
	OID                  INTEGER CONSTRAINT NN_TABEXP_OID NOT NULL ,
	SQL_QUERY_IN         CLOB NULL ,
	SQL_QUERY_IN_FILE    VARCHAR2(500) NULL ,
	SMTWTFS_EXPORT_PATTERN CHAR(7) CONSTRAINT NN_TABEXP_SMTWTFS NOT NULL  CONSTRAINT  CK_TABEXP_SMTWTFS CHECK (REGEXP_LIKE(SMTWTFS_EXPORT_PATTERN, '^[X\-]*$')),
	TABLE_NAME_ORACLE    VARCHAR2(30) CONSTRAINT NN_TABEXP_NAMEORA NOT NULL ,
	TABLE_IDX            INTEGER CONSTRAINT NN_TABEXP_IDX NOT NULL ,
	TABLE_NAME_EXPORT    VARCHAR2(100) CONSTRAINT NN_TABEXP_NAMEEXP NOT NULL ,
	SQL_DDL              CLOB NULL ,
	OID_GROUPEXPORT      INTEGER CONSTRAINT NN_TABEXP_GRPEXP NOT NULL ,
	SQL_QUERY_OUT        CLOB NULL ,
	SQL_QUERY_OUT_FILE   VARCHAR2(500) NULL ,
	LAST_EXPORTED_VERSION_NUMBER INTEGER NULL 
);

CREATE UNIQUE INDEX UX_TABEXP_OID ON FAI_TABLE_EXPORT
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_TABLE_EXPORT
	ADD CONSTRAINT  PK_TABEXP PRIMARY KEY (OID);

CREATE INDEX IX_TABEXP_GRPEXP ON FAI_TABLE_EXPORT
(OID_GROUPEXPORT   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_TIPO_FARMACO
(
	OID                  INTEGER CONSTRAINT NN_TIPOFARM_OID NOT NULL ,
	ACRONYM              VARCHAR2(20) CONSTRAINT NN_TIPOFARM_ACR NULL ,
	DESCR                VARCHAR2(100) NULL 
);

CREATE UNIQUE INDEX UX_TIPOFARM_OID ON FAI_TIPO_FARMACO
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_TIPO_FARMACO
	ADD CONSTRAINT  PK_TIPOFARM PRIMARY KEY (OID);

CREATE UNIQUE INDEX UX_TIPOFARM_ACR ON FAI_TIPO_FARMACO
(ACRONYM   ASC);

ALTER TABLE FAI_TIPO_FARMACO
ADD CONSTRAINT  UQ_TIPOFARM_ACR UNIQUE (ACRONYM);



CREATE TABLE FAI_EXECUTION_STATUS
(
	OID                  INTEGER CONSTRAINT NN_CSVSTAT_OID NOT NULL ,
	ACRONYM              VARCHAR2(20) CONSTRAINT NN_CSVSTAT_ACR NOT NULL ,
	DESCR                VARCHAR2(100) CONSTRAINT NN_CSVSTAT_DESCR NOT NULL 
);

CREATE UNIQUE INDEX UX_EXECSTAT_OID ON FAI_EXECUTION_STATUS
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_EXECUTION_STATUS
	ADD CONSTRAINT  PK_EXECSTAT PRIMARY KEY (OID);
	


  
  
  CREATE TABLE FAI_UPLOAD_TASK
(
	OID                  INTEGER CONSTRAINT NN_UPLTASK_OID NOT NULL ,
	DESCR                VARCHAR2(200) NULL ,
	CSV_FILE_NAME        VARCHAR2(200) NULL ,
	MAGAZZINO_ACRONYM    VARCHAR2(50) NULL ,
	OID_STATUS           INTEGER DEFAULT  1  CONSTRAINT NN_UPLAODTASK_STATUS NOT NULL ,
	ORDER_STATUS    	  VARCHAR2(50) NULL ,
	STATUS_DESCR         CLOB NULL ,
	STATUS_TECH_DESCR    CLOB NULL ,
	STATUS_UPDATED_TS    DATE DEFAULT  SYSDATE  CONSTRAINT NN_UPLDTSK_STATUSUPDTS NOT NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_UPLDTSK_CRTS NOT NULL ,
	RUN_START_TS         DATE NULL ,
	RUN_END_TS           DATE NULL ,
	RUN_DONE             CHAR(1) NULL  CONSTRAINT  CK_UPLTSK_LASTRDONE CHECK (RUN_DONE in ('S', 'N')),
	RUN_DESCR            CLOB NULL
);

CREATE UNIQUE INDEX UX_UPLOADTASK_OID ON FAI_UPLOAD_TASK
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_UPLOAD_TASK
	ADD CONSTRAINT  PK_UPLOADTASK PRIMARY KEY (OID);

ALTER TABLE FAI_UPLOAD_TASK
ADD CONSTRAINT  UQ_UPLTASK_ACR UNIQUE (MAGAZZINO_ACRONYM, CSV_FILE_NAME);


CREATE TABLE FAI_UPLOAD_TASK_PROPERTY
(
	OID                  INTEGER CONSTRAINT NN_UPLTSKPROP_OID NOT NULL ,
	KEY                  VARCHAR2(100) CONSTRAINT NN_UPLTSKPROP_KEY NOT NULL ,
	VALUE                CLOB CONSTRAINT NN_UPLTSKPROP_VALUE NOT NULL  
);

CREATE UNIQUE INDEX UX_UPLTSKPROP_OID ON FAI_UPLOAD_TASK_PROPERTY
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_UPLOAD_TASK_PROPERTY
	ADD CONSTRAINT  UQ_UPLTSKPROP PRIMARY KEY (OID);


CREATE OR REPLACE VIEW V_FAI_FDATI_PARAF_E_DISPOSIT 
AS 
SELECT
   FDI_0001 COD_MINSAN
  ,FDI_0002 COD_EAN
  ,FDI_0500 COD_PROD_SOSTITUITO_DA
  ,FDI_0499 COD_PROD_SOSTITUISCE_IL
  ,FDI_0002 COD_EAN_1
  ,FDI_1000 COD_EAN_2
  ,FDI_1001 COD_EAN_3
  ,FDI_0004 DESCR_PROD
  ,FDI_0040 COD_DITTA
  ,FDI_0008 COD_TIPO_PROD
  ,FDI_0329 COD_ATC_GMP_LIV_5
  ,FDI_0245 FLAG_COMMERCIO
  ,FDI_0250 DATA_COMMERCIALIZZ
  ,FDI_0251 DATA_INIZIO_ESAURIM
  ,FDI_9007 PREZZO_1
  ,FDI_9144 DATA_PREZZO_1
  ,FDI_9145 PREZZO_2
  ,FDI_9146 DATA_PREZZO_2
  ,FDI_0248 ALIQUOTA_IVA
FROM
  TE001
;

CREATE OR REPLACE VIEW V_FAI_FDATI_MEDIC_SOPOTC_ECOMM
AS
SELECT
   FDI_0001 COD_MINSAN
  ,NULL     COD_EAN
  ,FDI_0500 COD_PROD_SOSTITUITO_DA
  ,FDI_0499 COD_PROD_SOSTITUISCE_IL
  ,FDI_0004 DESCR_PROD
  ,FDI_0040 COD_DITTA
  ,FDI_0008 COD_TIPO_PROD
  ,FDI_0012 COD_TIPO_RICETTA_1
  ,FDI_0329 COD_ATC_GMP_LIV_5
  ,FDI_0245 FLAG_COMMERCIO
  ,FDI_0250 DATA_COMMERCIALIZZ
  ,FDI_0251 DATA_INIZIO_ESAURIM
  ,FDI_9007 PREZZO_1
  ,FDI_9144 DATA_PREZZO_1
  ,FDI_9145 PREZZO_2
  ,FDI_9146 DATA_PREZZO_2
  ,FDI_0248 ALIQUOTA_IVA
FROM 
  TE002;

CREATE OR REPLACE VIEW V_FAI_FDATI_OMEOPATICI_ECOMM  
AS
SELECT
   FDI_0001 COD_MINSAN
  ,FDI_0002 COD_EAN
  ,FDI_0500 COD_PROD_SOSTITUITO_DA
  ,FDI_0499 COD_PROD_SOSTITUISCE_IL
  ,FDI_0002 COD_EAN_1
  ,FDI_1000 COD_EAN_2
  ,FDI_1001 COD_EAN_3
  ,FDI_0004 DESCR_PROD
  ,FDI_4875 DESCR_ESTESA
  ,FDI_0040 COD_DITTA
  ,FDI_0008 COD_TIPO_PROD
  ,FDI_0329 COD_ATC_GMP_LIV_5
  ,FDI_0245 FLAG_COMMERCIO
  ,FDI_0250 DATA_COMMERCIALIZZ
  ,FDI_0251 DATA_INIZIO_ESAURIM
  ,FDI_9007 PREZZO_1
  ,FDI_9144 DATA_PREZZO_1
  ,FDI_9145 PREZZO_2
  ,FDI_9146 DATA_PREZZO_2
  ,FDI_0248 ALIQUOTA_IVA
  ,FDI_9004 IMMAGINE
  ,FDI_1702 SCHEDA_DESCRITTIVA
FROM
  TE006;

CREATE OR REPLACE VIEW V_FAI_FDATI_MEDIC_VETER_ECOMM
AS
SELECT 
   FDI_0001     COD_MINSAN
  ,NULL         COD_EAN
  ,FDI_0500	COD_PROD_SOSTITUITO_DA
  ,FDI_0499	COD_PROD_SOSTITUISCE_IL
  ,FDI_0004	DESCR_PROD
  ,FDI_0040	COD_DITTA
  ,FDI_0008	COD_TIPO_PROD
  ,FDI_0329	COD_ATC_GMP_LIV_5
  ,FDI_0245	FLAG_COMMERCIO
  ,FDI_0250	DATA_COMMERCIALIZZ
  ,FDI_0251	DATA_INIZIO_ESAURIM
  ,FDI_9007	PREZZO_1
  ,FDI_9144	DATA_PREZZO_1
  ,FDI_9145	PREZZO_2
  ,FDI_9146	DATA_PREZZO_2
  ,FDI_0248	ALIQUOTA_IVA
  ,FDI_9243	IMMAGINE_MEDICINALI
FROM   
   TE011
;

CREATE OR REPLACE VIEW V_FAI_FDATI_TIPI_PRODOTTO
AS 
SELECT
   FDI_T047 COD_TIPO_PROD
  ,FDI_T048 DESCR_TIPO_PROD
  ,FDI_T049 DESCR_ESTESA_TIPO_PROD
  ,FDI_T050 MACROCATEGORIA_TIPO_PROD
  ,FDI_T056 ELEMENTO_ATTIVO_TIPO_PROD
FROM  
   TS043
;

CREATE OR REPLACE VIEW V_FAI_FDATI_TUTTI 
AS
SELECT 
    PRODOTTO.COD_MINSAN
   ,PRODOTTO.COD_EAN
   ,PRODOTTO.DESCR_PROD
   ,'ANAGRAFICA MEDICINALI SOP-OTC PER E-COMMERCE' ANAGRAFICA
   ,TIPO.DESCR_TIPO_PROD
   ,TIPO.DESCR_ESTESA_TIPO_PROD
FROM
	V_FAI_FDATI_MEDIC_SOPOTC_ECOMM PRODOTTO
   ,V_FAI_FDATI_TIPI_PRODOTTO TIPO
WHERE
	PRODOTTO.COD_TIPO_PROD = TIPO.COD_TIPO_PROD
UNION   
SELECT 
    PRODOTTO.COD_MINSAN
   ,PRODOTTO.COD_EAN
   ,PRODOTTO.DESCR_PROD
   ,'ANAGRAFICA MEDICINALI VETERINARI PER E-COMMERCE' ANAGRAFICA
   ,TIPO.DESCR_TIPO_PROD
   ,TIPO.DESCR_ESTESA_TIPO_PROD
FROM
	V_FAI_FDATI_MEDIC_VETER_ECOMM PRODOTTO
   ,V_FAI_FDATI_TIPI_PRODOTTO TIPO
WHERE
	PRODOTTO.COD_TIPO_PROD = TIPO.COD_TIPO_PROD
UNION   
SELECT 
    PRODOTTO.COD_MINSAN
   ,PRODOTTO.COD_EAN
   ,PRODOTTO.DESCR_PROD
   ,'ANAGRAFICA OMEOPATICI PER E-COMMERCE' ANAGRAFICA
   ,TIPO.DESCR_TIPO_PROD
   ,TIPO.DESCR_ESTESA_TIPO_PROD
FROM
	V_FAI_FDATI_OMEOPATICI_ECOMM PRODOTTO
   ,V_FAI_FDATI_TIPI_PRODOTTO TIPO
WHERE
	PRODOTTO.COD_TIPO_PROD = TIPO.COD_TIPO_PROD
UNION   
SELECT 
    PRODOTTO.COD_MINSAN
   ,PRODOTTO.COD_EAN
   ,PRODOTTO.DESCR_PROD
   ,'ANAGRAFICA PARAFARMACI E DISPOSITIVI MEDICI PER E-COMMERCE' ANAGRAFICA
   ,TIPO.DESCR_TIPO_PROD
   ,TIPO.DESCR_ESTESA_TIPO_PROD
FROM
	V_FAI_FDATI_PARAF_E_DISPOSIT PRODOTTO
   ,V_FAI_FDATI_TIPI_PRODOTTO TIPO
WHERE
	PRODOTTO.COD_TIPO_PROD = TIPO.COD_TIPO_PROD
;

CREATE OR REPLACE VIEW V_FAI_FARMACO
AS
SELECT -- custom 
    custom.COD_MINSAN
   ,custom.COD_EAN
   ,custom.DESCR_PROD
   ,custom.ANAGRAFICA
   ,custom.DESCR_TIPO_PROD
   ,custom.DESCR_ESTESA_TIPO_PROD
   ,custom.IS_DELETED
   ,custom.OID
FROM
    FAI_FARMACO custom
UNION  
SELECT 
   read_only.COD_MINSAN
   ,read_only.COD_EAN
   ,read_only.DESCR_PROD
   ,read_only.ANAGRAFICA
   ,read_only.DESCR_TIPO_PROD
   ,read_only.DESCR_ESTESA_TIPO_PROD
   ,'N' IS_DELETED
   ,null OID
FROM
    V_FAI_FDATI_TUTTI read_only 
WHERE
  (read_only.COD_MINSAN is not null 
   AND  
   read_only.COD_MINSAN  not in (select COD_MINSAN from FAI_FARMACO))
   or
   (read_only.COD_EAN  is not null 
   and 
   read_only.COD_EAN  not in (select COD_EAN from FAI_FARMACO))
;

ALTER TABLE FAI_APPROVVIGFARMACO_ORDINRIGA
	ADD (CONSTRAINT FK_APPRFAROINRIGA_APPRF FOREIGN KEY (OID_APPROVFARMACO) REFERENCES FAI_APPROVVIGIONAMENTO_FARMACO (OID) ON DELETE CASCADE);

ALTER TABLE FAI_APPROVVIGFARMACO_ORDINRIGA
	ADD (CONSTRAINT FK_APPRFAROINRIGA_OINRIGA FOREIGN KEY (OID_ORDINEINRIGA) REFERENCES FAI_ORDINE_IN_RIGA_DETTAGLIO (OID) ON DELETE CASCADE);

ALTER TABLE FAI_APPROVVIGIONAMENTO_FARMACO
	ADD (CONSTRAINT FK_APPRFARM_FORN FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FORNITORE (OID));

ALTER TABLE FAI_APPROVVIGIONAMENTO_FARMACO
	ADD (CONSTRAINT FK_APPRFARM_MAGAZZ FOREIGN KEY (OID_MAGAZZINO) REFERENCES FAI_MAGAZZINO (OID));

ALTER TABLE FAI_APPROVVIGIONAMENTO_FARMACO
	ADD (CONSTRAINT FK_APPRFARM_ORDOUT FOREIGN KEY (OID_ORDINEOUT) REFERENCES FAI_ORDINE_OUT (OID) ON DELETE SET NULL);

ALTER TABLE FAI_APPROVVIGIONAMENTO_FARMACO
	ADD (CONSTRAINT FK_APPRFARM_STATUS FOREIGN KEY (OID_STATUS) REFERENCES FAI_ITEM_STATUS (OID));

ALTER TABLE FAI_COLUMN_EXPORT
	ADD (CONSTRAINT FK_COLEX_TABEXP FOREIGN KEY (OID_TABLEEXPORT) REFERENCES FAI_TABLE_EXPORT (OID));

ALTER TABLE FAI_DISPONIBILITA_REQ_TEMP
	ADD (CONSTRAINT FK_DISPREQTEMP_DISP FOREIGN KEY (OID_DISPONIBILITATEMP) REFERENCES FAI_DISPONIBILITA_TEMP (OID) ON DELETE CASCADE);

ALTER TABLE FAI_DISPONIBILITA_RES_TEMP
	ADD (CONSTRAINT FK_DISPRESTEMP_QDIS FOREIGN KEY (OID_DISPONIBILITAREQTEMP) REFERENCES FAI_DISPONIBILITA_REQ_TEMP (OID) ON DELETE CASCADE);

ALTER TABLE FAI_DISPONIBILITA_TEMP
	ADD (CONSTRAINT FK_DISPTEMP_FORN FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FORNITORE (OID));

ALTER TABLE FAI_DISPONIBILITA_TEMP
	ADD (CONSTRAINT FK_DISPTEMP_MAG FOREIGN KEY (OID_MAGAZZINO) REFERENCES FAI_MAGAZZINO (OID));

ALTER TABLE FAI_DISPONIBILITA_TEMP
	ADD (CONSTRAINT FK_DISPTEMP_STATUS FOREIGN KEY (OID_STATUS) REFERENCES FAI_ITEM_STATUS (OID));

ALTER TABLE FAI_FORNITORE_FARMACO
	ADD (CONSTRAINT FK_FORNITFARM_FORNIT FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FORNITORE (OID));

ALTER TABLE FAI_FORNITORE_PROPERTY
	ADD (CONSTRAINT FK_FORNPROP_FORNITORE FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FORNITORE (OID) ON DELETE CASCADE);

ALTER TABLE FAI_GENERIC_TASK_PROPERTY
	ADD (CONSTRAINT FK_GENTSKPROP_GENTASK FOREIGN KEY (OID_GENERICTASK) REFERENCES FAI_GENERIC_TASK (OID) ON DELETE CASCADE);

ALTER TABLE FAI_MAGAZZINO_PROPERTY
	ADD (CONSTRAINT FK_MAGAZPROP_MAGAZZINO FOREIGN KEY (OID_MAGAZZINO) REFERENCES FAI_MAGAZZINO (OID) ON DELETE CASCADE);

ALTER TABLE FAI_MAGAZZINO_TIPOFARMACO
	ADD (CONSTRAINT FK_MAGAZZTIPOF_MAG FOREIGN KEY (OID_MAGAZZINO) REFERENCES FAI_MAGAZZINO (OID) ON DELETE CASCADE);

ALTER TABLE FAI_MAGAZZINO_TIPOFARMACO
	ADD (CONSTRAINT FK_MAGAZZTIPOF_TIPOF FOREIGN KEY (OID_TIPOFARMACO) REFERENCES FAI_TIPO_FARMACO (OID) ON DELETE CASCADE);

ALTER TABLE FAI_ORDINE_IN
	ADD (CONSTRAINT FK_ORDINEIN_OICOLL FOREIGN KEY (OID_ORDINEINCOLLECTION) REFERENCES FAI_ORDINE_IN_COLLECTION (OID));

ALTER TABLE FAI_ORDINE_IN
	ADD (CONSTRAINT R_8 FOREIGN KEY (OID_STATUS) REFERENCES FAI_ITEM_STATUS (OID));

ALTER TABLE FAI_ORDINE_IN_COLLECTION
	ADD (CONSTRAINT FK_FAIORINCL_ST FOREIGN KEY (OID_STATUS) REFERENCES FAI_ITEM_STATUS (OID));

ALTER TABLE FAI_ORDINE_IN_RIGA_DETTAGLIO
	ADD (CONSTRAINT FK_ORDINRDETT_OOR FOREIGN KEY (OID_ORDINEIN) REFERENCES FAI_ORDINE_IN (OID));

ALTER TABLE FAI_ORDINE_OUT
	ADD (CONSTRAINT FK_ORDOUT_FORN FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FORNITORE (OID) ON DELETE SET NULL);

ALTER TABLE FAI_ORDINE_OUT
	ADD (CONSTRAINT FK_ORDOUT_ST FOREIGN KEY (OID_STATUS) REFERENCES FAI_ITEM_STATUS (OID));

ALTER TABLE FAI_TABLE_EXPORT
	ADD (CONSTRAINT FK_TABEXP_GRPEXP FOREIGN KEY (OID_GROUPEXPORT) REFERENCES FAI_GROUP_EXPORT (OID));

ALTER TABLE FAI_MAGAZZINO add(ORGANIZATION_CODICE VARCHAR2(100));


ALTER TABLE FAI_ORDINE_IN ADD FULFILLED CHAR(1);

ALTER TABLE FAI_APPROVVIGFARMACO_ORDINRIGA ADD QUANTITA_EVASA VARCHAR2(100);

ALTER TABLE FAI_ORDINE_IN_COLLECTION ADD MAGAZZINO_ACRONYM VARCHAR2(50);

CREATE TABLE FAI_FORNITORE_CALENDAR 
   (	OID INTEGER NOT NULL , 
	OID_FORNITORE INTEGER NOT NULL , 
	DATE_OF_WEEK INTEGER NOT NULL , 
	HOUR_START DATE, 
	HOUR_END DATE
   ); 
   
  
CREATE UNIQUE INDEX UX_FAI_FORNITORE_CALENDAR_OID ON FAI_FORNITORE_CALENDAR 
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_FORNITORE_CALENDAR
	ADD CONSTRAINT  PK_FAI_FORNITORE_CALENDAR PRIMARY KEY (OID);
	
ALTER TABLE FAI_FORNITORE_CALENDAR
	ADD (CONSTRAINT FK_FAI_FORNITORE_CALENDAR FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FORNITORE (OID));
	
	
	
ALTER TABLE FAI_IMPORT_CONFIG ADD SERVICE_API_LEVEL VARCHAR2(50);

ALTER TABLE FAI_FORNITORE_CALENDAR ADD ATTEMPT_NUMBER INTEGER;