
CREATE SEQUENCE OID;

CREATE TABLE FAI_FARMACLICK_FORNITORE
(
	OID                  INTEGER CONSTRAINT NN_FORNIT_OID NOT NULL ,
	CODICE               VARCHAR2(100) CONSTRAINT NN_FORNIT_COD NOT NULL ,
	CODICE_SITO_LOGISTICO VARCHAR2(100) NULL ,
	DESCRIZIONE_BREVE    VARCHAR2(200) NULL ,
	LAST_CSV             BLOB NULL ,
	LAST_CSV_FILENAME    VARCHAR2(100) NULL ,
	LAST_CSV_DOWNLOAD_URL VARCHAR2(500) NULL ,
	LAST_CSV_CONFIRM_URL VARCHAR2(500) NULL ,
	LAST_CSV_ZIPPED      CHAR(1) NULL ,
	NO_LONGER_EXISTS_TS  DATE NULL ,
	ALL_DATA_STORED_TS   DATE NULL ,
	LAST_CSV_CONFIRMED_AT_TS DATE NULL ,
	LAST_CSV_DOWNLOAD_AT_TS DATE NULL 
);

CREATE UNIQUE INDEX UX_FORNIT_OID ON FAI_FARMACLICK_FORNITORE
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_FARMACLICK_FORNITORE
	ADD CONSTRAINT  PK_FORNIT PRIMARY KEY (OID);

CREATE UNIQUE INDEX UX_FORNIT_COD ON FAI_FARMACLICK_FORNITORE
(CODICE   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_FARMACLICK_FORNITORE
ADD CONSTRAINT  UQ_FORNIT_COD UNIQUE (CODICE);

CREATE TABLE FAI_RECORD_A_ADDEBITO_FINANZ
(
	OID                  INTEGER CONSTRAINT NN_RECA_OID NOT NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_RECA_FORNITORE NOT NULL ,
	AZIONE               CHAR(1) NULL ,
	CODICE_CLIENTE       VARCHAR2(7) NULL ,
	CODICE_ADDEBITO_FINANZ VARCHAR2(3) NULL ,
	DESCR_ADDEBITO_FINANZ VARCHAR2(25) NULL ,
	PERC_ADDEBITO_FINANZ NUMBER(4,2) NULL ,
	NUM_GIORNI_ANNO_COMM INTEGER NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_RECA_CREATS NOT NULL ,
	UPDATE_TS            DATE CONSTRAINT NN_RECA_UPDTS NOT NULL 
);

CREATE UNIQUE INDEX UX_RECA_OID ON FAI_RECORD_A_ADDEBITO_FINANZ
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_RECORD_A_ADDEBITO_FINANZ
	ADD CONSTRAINT  PK_RECA PRIMARY KEY (OID);

CREATE INDEX IX_RECA_CODCLI ON FAI_RECORD_A_ADDEBITO_FINANZ
(CODICE_CLIENTE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECA_FORNITORE ON FAI_RECORD_A_ADDEBITO_FINANZ
(OID_FORNITORE   ASC);

CREATE TABLE FAI_RECORD_C_CONDIZIONI
(
	OID                  INTEGER CONSTRAINT NN_RECC_OID NOT NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_RECC_FORNITORE NOT NULL ,
	AZIONE               CHAR(1) NULL ,
	CODICE_CLIENTE       VARCHAR2(7) NULL ,
	CODICE_PRODOTTO      VARCHAR2(7) NULL ,
	CODICE_MINSAN        VARCHAR2(9) NULL ,
	CODICE_EAN           VARCHAR2(13) NULL ,
	DESCR_PRODOTTO       VARCHAR2(40) NULL ,
	SEQUENZA             CHAR(1) NULL ,
	SOTTOSEQUENZA        INTEGER NULL ,
	QUANTITA_MASSIMA     INTEGER NULL ,
	QUANTITA_MINIMA      INTEGER NULL ,
	QUANTITA_ASSEGNATA   INTEGER NULL ,
	QUANTITA_OMAGGIO     INTEGER NULL ,
	DATA_INIZIO_VALIDITA DATE NULL ,
	DATA_FINE_VALIDITA   DATE NULL ,
	PREZZO_VEND_LORDO_SCONTI NUMBER(11,4) NULL ,
	CODICE_PRODOTTO_OMAGGIO VARCHAR2(13) NULL ,
	DESCR_PRODOTTO_OMAGGIO VARCHAR2(40) NULL ,
	QUANTITA_PRODOTTO_OMAGGIO INTEGER NULL ,
	DILAZIONE_PAGAM_GIORNI INTEGER NULL ,
	GIORNI_ABBU_CALC_ADD INTEGER NULL ,
	CODICE_ADDEBITO      VARCHAR2(3) NULL ,
	PERC_ADDEBITO_AGG    NUMBER(4,2) NULL ,
	SCONTO_CASSA         NUMBER(4,2) NULL ,
	ALLINEAM_SCADENZA    CHAR(1) NULL ,
	PERIODICITA_FATTURAZ CHAR(1) NULL ,
	PREZZO_NETTO_UNITARIO NUMBER(13,6) NULL ,
	PERC_SC_PRZPUB_PRZNETUN NUMBER(8,2) NULL ,
	PERC_SC_PRZPUBDR_PRZNETUN NUMBER(8,2) NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_RECC_CREATS NOT NULL ,
	UPDATE_TS            DATE CONSTRAINT NN_RECC_UPDTS NOT NULL 
);

CREATE UNIQUE INDEX UX_RECC_OID ON FAI_RECORD_C_CONDIZIONI
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_RECORD_C_CONDIZIONI
	ADD CONSTRAINT  PK_RECC PRIMARY KEY (OID);

CREATE INDEX IX_RECC_CODCLI ON FAI_RECORD_C_CONDIZIONI
(CODICE_CLIENTE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECC_CODPRO ON FAI_RECORD_C_CONDIZIONI
(CODICE_PRODOTTO   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECC_FORNITORE ON FAI_RECORD_C_CONDIZIONI
(OID_FORNITORE   ASC);

CREATE TABLE FAI_RECORD_D_RESET
(
	OID                  INTEGER CONSTRAINT NN_RECD_OID NOT NULL ,
	CODICE_CLIENTE       VARCHAR2(7) NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_RECD_FORNITORE NOT NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_RECD_CREATS NOT NULL ,
	UPDATE_TS            DATE CONSTRAINT NN_RECD_UPDTS NULL,
	AZIONE 				 VARCHAR2(100)
);

CREATE UNIQUE INDEX UX_RECD_OID ON FAI_RECORD_D_RESET
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_RECORD_D_RESET
	ADD CONSTRAINT  PK_RECD PRIMARY KEY (OID);

CREATE INDEX IX_RECD_CODCLI ON FAI_RECORD_D_RESET
(CODICE_CLIENTE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECD_FORNITORE ON FAI_RECORD_D_RESET
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_RECORD_L_LISTINO
(
	OID                  INTEGER CONSTRAINT NN_RECL_OID NOT NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_RECL_FORNITORE NOT NULL ,
	AZIONE               CHAR(1) NULL ,
	CODICE_CLIENTE       VARCHAR2(7) NULL ,
	CODICE_PRODOTTO      VARCHAR2(7) NULL ,
	CODICE_MINSAN        VARCHAR2(9) NULL ,
	CODICE_EAN           VARCHAR2(13) NULL ,
	DESCR_PRODOTTO       VARCHAR2(40) NULL ,
	CATEGORIA_TIPO_PRODOTTO VARCHAR2(2) NULL ,
	PREZZO_AL_PUBBLICO   NUMBER(11,4) NULL ,
	ALIQUOTA_IVA         NUMBER(3,1) NULL ,
	PREZ_VEND_LIST_LORDO_SC NUMBER(11,4) NULL ,
	SCONTO_1             NUMBER(4,2) NULL ,
	SCONTO_2             NUMBER(4,2) NULL ,
	DILAZ_PAGAM_GIORNI   INTEGER NULL ,
	GIORNI_ABBU_CALC_ADD INTEGER NULL ,
	CODICE_ADDEBITO      VARCHAR2(3) NULL ,
	PERC_ADDEBITO_AGG    NUMBER(4,2) NULL ,
	SCONTO_CASSA         NUMBER(4,2) NULL ,
	ALLINEAM_SCADENZA    CHAR(1) NULL ,
	PERIODICITA_FATTURAZ CHAR(1) NULL ,
	QUANT_PER_COLLO_VEND INTEGER NULL ,
	PREZZO_VEND_CONSIG   NUMBER(11,4) NULL ,
	PREZZO_PUBB_DERIVATO NUMBER(11,4) NULL ,
	PREZZO_NETTO_UNITARIO NUMBER(13,6) NULL ,
	PERC_SC_PRZPUB_PRZNETUN NUMBER(8,2) NULL ,
	PERC_SC_PRZPUBDR_PRZNETUN NUMBER(8,2) NULL ,
	SOMMA_SCONTO_1_2     NUMBER(4,2) NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_RECL_CREATS NOT NULL ,
	UPDATE_TS            DATE CONSTRAINT NN_RECL_UPDTS NOT NULL 
);

CREATE UNIQUE INDEX UX_RECL_OID ON FAI_RECORD_L_LISTINO
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_RECORD_L_LISTINO
	ADD CONSTRAINT  PK_RECL PRIMARY KEY (OID);

CREATE INDEX IX_RECL_CODCLI ON FAI_RECORD_L_LISTINO
(CODICE_CLIENTE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECL_CODPRO ON FAI_RECORD_L_LISTINO
(CODICE_PRODOTTO   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECL_FORNITORE ON FAI_RECORD_L_LISTINO
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_RECORD_R_RAGGR_COND
(
	OID                  INTEGER CONSTRAINT NN_RECR_OID NOT NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_RECR_FORNITORE NOT NULL ,
	AZIONE               CHAR(1) NULL ,
	CODICE_CLIENTE       VARCHAR2(7) NULL ,
	CODICE_REGGRUPP_CONDIZ VARCHAR2(3) NULL ,
	RIGA                 CHAR(1) NULL ,
	DESCR_REGGRUPP_CONDIZ VARCHAR2(78) NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_RECR_CREATS NOT NULL ,
	UPDATE_TS            DATE CONSTRAINT NN_RECR_UPDTS NOT NULL 
);

CREATE UNIQUE INDEX UX_RECR_OID ON FAI_RECORD_R_RAGGR_COND
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_RECORD_R_RAGGR_COND
	ADD CONSTRAINT  PK_RECR PRIMARY KEY (OID);

CREATE INDEX IX_RECR_CODCLI ON FAI_RECORD_R_RAGGR_COND
(CODICE_CLIENTE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECR_FORNITORE ON FAI_RECORD_R_RAGGR_COND
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_RECORD_V_RAGGR_COND
(
	OID                  INTEGER CONSTRAINT NN_RECV_OID NOT NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_RECV_FORNITORE NOT NULL ,
	AZIONE               CHAR(1) NULL ,
	CODICE_CLIENTE       VARCHAR2(7) NULL ,
	CODICE_ADDEBITO      VARCHAR2(2) NULL ,
	DESCR_ADDEBITO       VARCHAR2(30) NULL ,
	DESTINAZIONE_ADDEBITO CHAR(1) NULL ,
	PERIODICITA_FATTURAZ CHAR(1) NULL ,
	IMORTO_MASSIMO_DA_ADDEB NUMBER(10,4) NULL ,
	NUMERO_MASSIMO_ADDEB INTEGER NULL ,
	IMPORTO_DA_ADDEB     NUMBER(11,4) NULL ,
	PERC_ADDEB_SU_IMPONIBILE NUMBER(4,2) NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_RECV_CREATS NOT NULL ,
	UPDATE_TS            DATE CONSTRAINT NN_RECV_UPDTS NOT NULL 
);

CREATE UNIQUE INDEX UX_RECV_OID ON FAI_RECORD_V_RAGGR_COND
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_RECORD_V_RAGGR_COND
	ADD CONSTRAINT  PK_RECV PRIMARY KEY (OID);

CREATE INDEX IX_RECV_CODCLI ON FAI_RECORD_V_RAGGR_COND
(CODICE_CLIENTE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECV_FORNITORE ON FAI_RECORD_V_RAGGR_COND
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE FAI_RECORD_Z_CHIUSURA
(
	OID                  INTEGER CONSTRAINT NN_RECZ_OID NOT NULL ,
	OID_FORNITORE        INTEGER CONSTRAINT NN_RECZ_FORNITORE NOT NULL ,
	AZIONE               CHAR(1) NULL ,
	CODICE_CLIENTE       VARCHAR2(7) NULL ,
	DATA_ORA_GENERAZ     DATE NULL ,
	CREATION_TS          DATE DEFAULT  SYSDATE  CONSTRAINT NN_RECZ_CREATS NOT NULL ,
	UPDATE_TS            DATE CONSTRAINT NN_RECZ_UPDTS NOT NULL 
);

CREATE UNIQUE INDEX UX_RECZ_OID ON FAI_RECORD_Z_CHIUSURA
(OID   ASC)
	TABLESPACE FAI_IDX;

ALTER TABLE FAI_RECORD_Z_CHIUSURA
	ADD CONSTRAINT  PK_RECZ PRIMARY KEY (OID);

CREATE INDEX IX_RECZ_CODCLI ON FAI_RECORD_Z_CHIUSURA
(CODICE_CLIENTE   ASC)
	TABLESPACE FAI_IDX;

CREATE INDEX IX_RECZ_FORNITORE ON FAI_RECORD_Z_CHIUSURA
(OID_FORNITORE   ASC)
	TABLESPACE FAI_IDX;

CREATE TABLE SD_SCHEMA_VERSION
(
	VERSION              VARCHAR2(64) CONSTRAINT NN_SCHEMAVER_VER NOT NULL ,
	LAST_MODIFY          DATE CONSTRAINT NN_SCHEMAVER_LASTMOD NOT NULL 
);

ALTER TABLE FAI_RECORD_A_ADDEBITO_FINANZ
	ADD (CONSTRAINT FK_RECA_FORNITORE FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FARMACLICK_FORNITORE (OID));

ALTER TABLE FAI_RECORD_C_CONDIZIONI
	ADD (CONSTRAINT FK_RECC_FORNITORE FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FARMACLICK_FORNITORE (OID));

ALTER TABLE FAI_RECORD_D_RESET
	ADD (CONSTRAINT FK_RECD_FORNITORE FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FARMACLICK_FORNITORE (OID));

ALTER TABLE FAI_RECORD_L_LISTINO
	ADD (CONSTRAINT FK_RECL_FORNITORE FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FARMACLICK_FORNITORE (OID));

ALTER TABLE FAI_RECORD_R_RAGGR_COND
	ADD (CONSTRAINT FK_RECR_FORNITORE FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FARMACLICK_FORNITORE (OID));

ALTER TABLE FAI_RECORD_V_RAGGR_COND
	ADD (CONSTRAINT FK_RECV_FORNITORE FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FARMACLICK_FORNITORE (OID));

ALTER TABLE FAI_RECORD_Z_CHIUSURA
	ADD (CONSTRAINT FK_RECZ_FORNITORE FOREIGN KEY (OID_FORNITORE) REFERENCES FAI_FARMACLICK_FORNITORE (OID));
