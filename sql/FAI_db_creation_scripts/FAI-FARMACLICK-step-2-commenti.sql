
COMMENT ON TABLE FAI_FARMACLICK_FORNITORE IS 'Ogni singolo record corrisponde ad un elemento "/LoginOutputBean/arrayFornitori/arrayFornitori" della risposta al servizio "FCKLogin" (2021.05.12)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.OID IS 'Identificativo univoco della tupla (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.CODICE IS 'Corrisponde all''omonimo campo di "/LoginOutputBean/arrayFornitori/arrayFornitori" della risposta a "FCKLogin" (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.CODICE_SITO_LOGISTICO IS 'Corrisponde all''omonimo campo di "/LoginOutputBean/arrayFornitori/arrayFornitori" della risposta a "FCKLogin" (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.DESCRIZIONE_BREVE IS 'Corrisponde all''omonimo campo di "/LoginOutputBean/arrayFornitori/arrayFornitori" della risposta a "FCKLogin" (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.LAST_CSV IS 'Se valorizzato, contenuto binario del CSV scaricato (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.LAST_CSV_FILENAME IS 'Nome del CSV, se disponibile (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.LAST_CSV_DOWNLOAD_URL IS 'url dal quale il CSV è stato scaricato (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.LAST_CSV_CONFIRM_URL IS 'url di conferma del download (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.LAST_CSV_ZIPPED IS 'Se ''S'', il contenuto di LAST_CSV è da conisderarsi compresso, altrimenti ''N'' (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.NO_LONGER_EXISTS_TS IS 'Se NOT NULL indica la data/ora a partire dalla quale non si ha più riscontro del FORNITORE. Potrebbe risultare NOT NULL quando l''operazione di interrogazione dei Web Service è in corso (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.ALL_DATA_STORED_TS IS 'Se NOT NULL indica la data/ora di completamento della registrazione in banca dati dei dati scaricati (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.LAST_CSV_CONFIRMED_AT_TS IS 'Se NOT NULL, indica la data/ora alla quale il download è stato confermato per mezzo dell''apposito link (2021.05.11)';

COMMENT ON COLUMN FAI_FARMACLICK_FORNITORE.LAST_CSV_DOWNLOAD_AT_TS IS 'Se NOT NULL indica la data/ora di inizio del download dall''url dedicato (2021.05.11)';

COMMENT ON TABLE FAI_RECORD_A_ADDEBITO_FINANZ IS 'Record "A": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_A_ADDEBITO_FINANZ.OID IS 'Identificativo univoco della tupla (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_A_ADDEBITO_FINANZ.OID_FORNITORE IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_A_ADDEBITO_FINANZ.AZIONE IS 'I Inserimento, V Variazione, C Cancellazione (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_A_ADDEBITO_FINANZ.CREATION_TS IS 'data/ora di creazione del record (2021.05.12)';

COMMENT ON COLUMN FAI_RECORD_A_ADDEBITO_FINANZ.UPDATE_TS IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';

COMMENT ON TABLE FAI_RECORD_C_CONDIZIONI IS 'Record "C": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_C_CONDIZIONI.OID IS 'Identificativo univoco della tupla (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_C_CONDIZIONI.OID_FORNITORE IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_C_CONDIZIONI.AZIONE IS 'Azione: I Inserimento, V Variazione, C Cancellazione (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_C_CONDIZIONI.PERC_SC_PRZPUB_PRZNETUN IS 'Percentuale sconto tra prezzo al pubblico e il prezzo netto unitario (Il campo viene valorizzato solo se è presente un prezzo al pubblico del prodotto.) (2021.05.11);';

COMMENT ON COLUMN FAI_RECORD_C_CONDIZIONI.PERC_SC_PRZPUBDR_PRZNETUN IS 'Percentuale sconto tra prezzo al pubblico derivato e il prezzo netto unitario ( Il campo viene valorizzato solo se è presente un prezzo al pubblico del prodotto.) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_C_CONDIZIONI.CREATION_TS IS 'data/ora di creazione del record (2021.05.12)';

COMMENT ON COLUMN FAI_RECORD_C_CONDIZIONI.UPDATE_TS IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';

COMMENT ON TABLE FAI_RECORD_D_RESET IS 'Record "D": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_D_RESET.OID IS 'Identificativo univoco della tupla (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_D_RESET.OID_FORNITORE IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_D_RESET.CREATION_TS IS 'data/ora di creazione del record (2021.05.12)';

COMMENT ON COLUMN FAI_RECORD_D_RESET.UPDATE_TS IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';

COMMENT ON TABLE FAI_RECORD_L_LISTINO IS 'Record "L": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.OID IS 'Identificativo univoco della tupla (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.OID_FORNITORE IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.AZIONE IS 'Azione: I Inserimento, V Variazione, C Cancellazione (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.PREZ_VEND_LIST_LORDO_SC IS 'Prezzo di vendita a listino lordo sconti (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.DILAZ_PAGAM_GIORNI IS 'Dilazione pagamento in giorni (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.GIORNI_ABBU_CALC_ADD IS 'Giorni abbuono per il calcolo dell''addebito (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.PERC_ADDEBITO_AGG IS 'Percentuale addebito aggiuntiva (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.ALLINEAM_SCADENZA IS 'Allineamento scadenza: "D": Data fattura, "F": Fine mese (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.PERIODICITA_FATTURAZ IS 'Periodicità di fatturazione. "A":  Accompagnatoria, "S": Settimanale, "Q": Quindicinale, "M": Mensile (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.PERC_SC_PRZPUB_PRZNETUN IS 'Percentuale sconto tra prezzo al pubblico e il prezzo netto unitario (Il campo viene valorizzato solo se è presente un prezzo al pubblico del prodotto.) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.PERC_SC_PRZPUBDR_PRZNETUN IS 'Percentuale sconto tra prezzo al pubblico derivato e il prezzo netto unitario ( Il campo viene valorizzato solo se è presente un prezzo al pubblico del prodotto.) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.SOMMA_SCONTO_1_2 IS 'Somma sconto 1 e sconto 2 (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.CREATION_TS IS 'data/ora di creazione del record (2021.05.12)';

COMMENT ON COLUMN FAI_RECORD_L_LISTINO.UPDATE_TS IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)
';

COMMENT ON TABLE FAI_RECORD_R_RAGGR_COND IS 'Record "R": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_R_RAGGR_COND.OID IS 'Identificativo univoco della tupla (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_R_RAGGR_COND.OID_FORNITORE IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_R_RAGGR_COND.AZIONE IS 'I Inserimento, V Variazione, C Cancellazione (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_R_RAGGR_COND.CODICE_REGGRUPP_CONDIZ IS 'Codice raggruppamento condizione (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_R_RAGGR_COND.CREATION_TS IS 'data/ora di creazione del record (2021.05.12)';

COMMENT ON COLUMN FAI_RECORD_R_RAGGR_COND.UPDATE_TS IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';

COMMENT ON TABLE FAI_RECORD_V_RAGGR_COND IS 'Record "V": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.OID IS 'Identificativo univoco della tupla (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.OID_FORNITORE IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.AZIONE IS 'I Inserimento, V Variazione, C Cancellazione (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.DESTINAZIONE_ADDEBITO IS 'Destinazione addebito. ''B'': DDT o fattura accompagnatoria, ''F'': Fattura';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.PERIODICITA_FATTURAZ IS 'Periodicità di fatturazione. "A": Accompagnatoria, "S": Settimanale, "Q": Quindicinale, "M": Mensile';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.IMORTO_MASSIMO_DA_ADDEB IS 'Importo massimo da addebitare.
Oltre tale importo non viene più applicato l''addebito (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.NUMERO_MASSIMO_ADDEB IS 'Numero massimi addebiti. (Solo per destinazione addebito "F") (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.IMPORTO_DA_ADDEB IS 'Importo da addebitare. (Deve essere addebitato ad ogni documento) (2011.05.11)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.PERC_ADDEB_SU_IMPONIBILE IS 'Percentuale di addebito su imponibile (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.CREATION_TS IS 'data/ora di creazione del record (2021.05.12)';

COMMENT ON COLUMN FAI_RECORD_V_RAGGR_COND.UPDATE_TS IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)
';

COMMENT ON TABLE FAI_RECORD_Z_CHIUSURA IS 'Record "Z": i nomi dei campi coincidono o richiamano quelli della documentazione ufficiale, per cui, la documentazione agli stessi viene fornita solo quando necessaio (es., nome utilizzato dalla documentazione troppo lungo come nome del campo della tabella) (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_Z_CHIUSURA.OID IS 'Identificativo univoco della tupla (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_Z_CHIUSURA.OID_FORNITORE IS 'Foreign key verso FAI_FARMACLICK_FORNITORE (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_Z_CHIUSURA.AZIONE IS 'I Inserimento, V Variazione, C Cancellazione (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_Z_CHIUSURA.DATA_ORA_GENERAZ IS 'E'' l''unione dei campi "Data di generazione" e "Orario di generazione" (2021.05.11)';

COMMENT ON COLUMN FAI_RECORD_Z_CHIUSURA.CREATION_TS IS 'data/ora di creazione del record (2021.05.12)';

COMMENT ON COLUMN FAI_RECORD_Z_CHIUSURA.UPDATE_TS IS 'data/ora dell''ultimo aggiornamento del record (2021.05.12)';

COMMENT ON TABLE SD_SCHEMA_VERSION IS 'Tabella di versioning dello schema (2021.05.12)';
