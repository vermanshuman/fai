COMMENT ON COLUMN FAI_FARMACO.IS_DELETED IS 'normalmente ''N'', se ''S'' indica che la riga è cancellata (2021.09.29)';
COMMENT ON COLUMN FAI_FORNITORE.OID IS 'Identificativo univoco della tupla (2021.06.09)';

COMMENT ON COLUMN FAI_FORNITORE.CODICE IS 'Codice identificativo del Fornitore  (2021.06.09)';

COMMENT ON COLUMN FAI_FORNITORE.CODICE_FARMACLICK IS 'Codice identificativo del fornitore in Farmaclick, se presente in tale fonte. Questo campo, che forse troverebbe miglior collocazione in una tabella di transcodifica, viene invece posto qui per brevità (2021.06.09)';

COMMENT ON COLUMN FAI_FORNITORE.SUPPLIERSERVICE_CLASS_NAME IS 'nome della classe implementante l''interfaccia fai.broker.supplier.SupplierService  che consente di interagire coi sistemi del Fornitore. Il campo è NULLable al solo scopo di consentire il popolamento della banca dati per non impedire tutte le operazioni che è possibile effettuare sulla stessa senza interagire coi sistemi del Fornitore. Ovviamente, se questo campo è NULL, qualsiasi tentativo di interazione coi sistemi del Fornitore, risulterà impossibile (2021.07.27)';
COMMENT ON COLUMN FAI_LOG.OID IS 'Identificativo univoco della tupla (2021.07.07)';

COMMENT ON COLUMN FAI_LOG.LOG_LEVEL IS 'Ammessi: "T" (Trace), "D" (Debug), "I" (Info), "W" (Warning), "E" (Error), "F" (Fatal). Di fatto, questa tabella conserverà, rispetto ad un log tradizionale, solo un sottinsieme ridotto di righe dal contenuo fruibile (almeno in gran parte) anche dall''utente finale del sistema (2021.07.07)';

COMMENT ON COLUMN FAI_LOG.TS IS 'Data/ora di creazione del record. Per scorrere la tabella in modo cronologico, è opportuno che l''ordinamento (ORDER BY) sia per TS, OID, in quanto i) nulla vieta di avere più record allo stesso secondo e ii) l''OID è legato ad una sequence che si assume sempre crescente. Quand''anche la sequence venisse azzerata, l''ordinamento potrebbe fallire solo nel punto di azzeramento della sequence, ammesso che proprio in quel punto due record consecutivi siano attribuiti allo stesso secondo (2021.07.07)';

COMMENT ON COLUMN FAI_LOG.THREAD IS 'E'' il thread che ha generato la riga di log (2021.07.07)';

COMMENT ON COLUMN FAI_LOG.MESSAGE IS 'testo della "riga" di log (2021.07.07)';

COMMENT ON COLUMN FAI_LOG.TECH_MESSAGE IS 'eventuali ulteriori informazioni di carattere tecnico (2021.07.07)';

COMMENT ON COLUMN FAI_LOG.STACK_TRACE IS 'campo disponibile per l''eventuale stacktrace (2021.07.07)';

COMMENT ON TABLE FAI_APPROVVIGFARMACO_ORDINRIGA IS 'Consente di associare un recod FAI_APPROVVIGIONAMENTO_FARMACO a tutte le FAI_ORDINE_IN_RIGA_DETTAGLIO che detrerminano il record di approvvigionamento. Ovviamente risulterà sempre FAI_APPROVVIGIONAMENTO_FARMACO.QUANTITA = SUM(FAI_APPROVVIGFARMACO_ORDINRIGA.QUANTITA). Si osservi che il record di approvvigionamento può far riferimento, non solo a righe di diversi FAI_ORDINE_IN, ma anche ad FAI_ORDINE_IN relativi a FAI_ORDINE_IN_COLLECTION differenti  (2021.06.30)';

COMMENT ON COLUMN FAI_APPROVVIGFARMACO_ORDINRIGA.OID_APPROVFARMACO IS 'Identificativo univoco della tupla (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGFARMACO_ORDINRIGA.OID_ORDINEINRIGA IS 'identificativo univoco della tupla (2021.06.10)';

COMMENT ON TABLE FAI_APPROVVIGIONAMENTO_FARMACO IS 'un record di questa tabella nasce, in stato "TO_PROCESS", sempre contestualmente ("commit") al record FAI_FABBISOGNO_FARMACO e reca la stessa quantità del record referenziato, oltre che lo stesso CODICE_MINSAN, con entrambe le fk verso Magazzino e Fornitore NULL. Man mano che il processo reperisce il Farmaco presso i Magazzini o i Fornitori, vengono creati nuovi record, referenzianti la fonte individuata, con la QUANTITA che la fonte riesce a soddisfare. La SUM(QUANTITA) sarà sempre pari alla QUANTITA che record referenziano nel Fabbisogno. Quando la QUANTITA richiesta è stata reperita, il record con le due fk NULL viene rimosso (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.OID IS 'Identificativo univoco della tupla (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.OID_MAGAZZINO IS 'foreign key verso FAI_MAGAZZINO. NULL se la QUANTITA indicata viene soddisfatta per mezzo di un FAI_FORNITORE, oppure se il recordo è stato appena creato. In tal caso QUANTITA coinciderà con QUANTITA di FAI_FABBISOGNO_FARMACO e anche OID_FORNITORE sarà NULL (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.OID_FORNITORE IS 'foreign key verso FAI_FORNITORE. NULL se la QUANTITA indicata viene soddisfatta per mezzo di un FAI_MAGAZZINO, oppure se il recordo è stato appena creato. In tal caso QUANTITA coinciderà con QUANTITA di FAI_FABBISOGNO_FARMACO e anche OID_MAGAZZINO sarà NULL (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.CODICE_MINSAN IS 'codice MinSan identificativo del farmaco. Almeno uno dei due - CODICE_MINSAN o CODICE_EAN - deve essere valorizzato (2021.06.28)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.QUANTITA IS 'quantità soddisfatta dal presente record (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.DISPONIBILITA_RICHIESTA_REQ IS 'Se NOT NULL indica la data/ora alla quale la richiesta di disponibilità (verso il Magazzino o il Fornitore) è stata inoltrata. (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.DISPONIBILITA_CONFERMATA_REQ IS 'Se NOT NULL indica la data/ora alla quale richiesta di confermare la quantità (precedentemente data come disponibile) è inviata (al Magazzino o al Fornitore).  (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.ID_ORDINE IS 'Identificativo dell''Ordine verso il Magazzino o il Fornitore, se applicabile (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.ID_RICEVUTA IS 'Identificativo della Ricevutap per l''Ordine verso il Magazzino o il Fornitore, se applicabile (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.DISPONIBILITA_RICHIESTA_RES IS 'Se NOT NULL indica la data/ora alla quale è stata ricevuta la risposta alla richiesta di disponibilità (verso il Magazzino o il Fornitore), qualunque essa sia (es., anche "farmaco non disponibile") (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.DISPONIBILITA_CONFERMATA_RES IS 'Se NOT NULL indica la data/ora alla quale è stata ricevuta la risposta alla richiesta di conferma della qualità (verso il Magazzino o il Fornitore). (2021.06.17)

';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.OID_STATUS IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.STATUS_DESCR IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.STATUS_TECH_DESCR IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.PREZZO_UNITARIO IS 'prezzo unitario del farmaco (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.PREZZO_TOTALE IS 'prezzo unitario del farmaco. In genere dovrebbe coincidere con PREZZO_UNITARIO * QUANTITA, a meno di particolari gestioni da parte del Fornitore (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.ALIQUOTA_IVA IS 'aliquota IVA espresa in percentuale. 20.5 rappresenta il "20.5%" (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.ALIQUOTA_IVA_INCLUSA IS '''S'' se l''aliquota IVA è inclusa, altrimenti ''N''.  (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.OID_ORDINEOUT IS 'foreign key verso FAI_ORDINE_OUT (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.STATUS_UPDATED_TS IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.CREATION_TS IS 'data/ora di creazione del record (2021.06.17)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.CODICE_EAN IS 'codice EAN identificativo del farmaco. Almeno uno dei due - CODICE_MINSAN o CODICE_EAN - deve essere valorizzato (2021.06.28)';

COMMENT ON COLUMN FAI_APPROVVIGIONAMENTO_FARMACO.MAGAZZINO_ACRONYM IS 'Ha la stessa funzione dell''omonimo campo della tabella FAI_ORDINE_IN_RIGA_DETTAGLIO, cui si rimanda e di cui rappresenta una copia. Va da se che tutte le FAI_ORDINE_IN_RIGA_DETTAGLIO referenziate per mezzo dell''associativa FAI_APPROVVIGFARMACO_ORDINRIGA saranno caratterizzate dallo stesso valore per il campo MAGAZZINO_ACRONYM. Al momento (2021.09.21) si ipotizza che non possano esistere due FAI_ORDINE_IN_RIGA_DETTAGLIO che, a parità di Farmaco, presentano MAGAZZINO_ACRONYM diversi (2021.09.21)';

COMMENT ON TABLE FAI_COLUMN_EXPORT IS 'fare riferimento a quanto documentato per FAI_TABLE_EXPORT ed alla documentazione ai singoli campi della presente (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.OID IS 'identificativo univoco della tupla (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.COLUMN_IDX IS 'analogamente a GROUP_IDX e TABLE_IDX consente di definire l''ordinamento delle colonne  (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.COLUMN_NAME_ORACLE IS 'nome della colonna conforme ai vincoli Oracle per le tabelle  (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.COLUMN_NAME_EXPORT IS 'nome della colonna nel sistema di destinazione. Al momento (2021.05.18) tale nome non sarà esportato verso il prodotto Oracle, in quanto il foramto CSV previsto non include la riga di intestazione (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.REQUIRED IS 'Se ''S'' indica che il campo è obbligatorio, altrimenti ''N'' (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.SQL_TYPE IS 'insieme a SQL_TYPE_LEN ed SQL_TYPE_SCALE definisce il tipo SQL. Ad esempio per "VARCHAR2(10)" si avrebbe SQL_TYPE "VARCHAR2", SQL_TYPE_LEN 10 ed SQL_TYPE_SCALE NULL. Per NUMBER(20,4), invece, rispettivamente, "NUMBER", 20 e 4. Al momento (2021.05.18), dato il formato CSV e l''esigenza di formattare i valori data e numerici, l''unico tipo supportato inizialmente sarà "VARCHAR2" (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.SQL_TYPE_LEN IS 'v. quanto documentato per SQL_TYPE (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.SQL_TYPE_SCALE IS 'v. quanto documentato per SQL_TYPE (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.DATE_FORMAT_PATTERN IS 'se SQL_TYPE è "DATE" definisce, se NOT NULL, il pattern di formattazione da adottare per convertire la data in testo (2021.05.28)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.NUMB_FORMAT_PATTERN IS 'se SQL_TYPE è "NUMBER", definisce, se NOT NULL, il pattern di formattazione da adottare per convertire il dato numerico in testo. V. anche NUB_FORMAT_GRPCH e NUB_FORMAT_DECCH (2021.05.28)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.NUMB_FORMAT_GRPCH IS 'Se NOT NULL indica il carattere di raggruppamento da adottare nel pattern NUMB_FORMAT_PATTERN (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.NUMB_FORMAT_DECCH IS 'Se NOT NULL indica il carattere di separazione dei decimali da adottare nel pattern NUMB_FORMAT_PATTERN (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.OID_TABLEEXPORT IS 'identificativo univoco della tupla (2021.05.18)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.TEXT_FORMAT_TYPE IS 'Se NOT NULL, ed SQL_TYPE è "VARCHAR2" o "CHAR", indica come interpretare i parametri TEXT_FORMAT_SEARCH e TEXT_FORMAT_REPLACE (2021.05.28)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.TEXT_FORMAT_SEARCH IS 'Se TEXT_FORMAT_TYPE è "RE" ("Regular Expression"), allora questo parametro sarà interprato come espressione regolare e, concretamente, costituirà il primo parametro del metodo replaceAll della classe java.lang.String. Se, invece, TEXT_FORMAT_TYPE è "SR" ("Search and Replace"), allora il testo da cercare, ossia il primo parametro del metodo replace della classe java.lang.String (2021.05.28)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.TEXT_FORMAT_REPLACE IS 'Se TEXT_FORMAT_TYPE (cui si rimanda) è NOT NULL, rappresenta il secondo parametro dell''operazione di formattazione del testo, ossia, concretamente, il secondo parametro del metodo replaceAll o replace di java.lang.String(2021.05.28)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.INDEX_NAME_ORACLE IS 'Se NOT NULL indica il nome dell''INDEX non-unique per questa colonna. L''indice viene creato solo contestualmente alla tabella: modificare INDEX_NAME_ORACLE o INDEX_NAME_TABLESPACE successivamente alla creazione della tabella non ha alcun effetto (2021.05.28)';

COMMENT ON COLUMN FAI_COLUMN_EXPORT.INDEX_NAME_TABLESPACE IS 'Se NOT NULL indica il TABLESPACE nel quale crare l''indice INDEX_NAME_ORACLE (2021.05.28)';

COMMENT ON TABLE FAI_DISPONIBILITA_REQ_TEMP IS 'fare riferimento alla documentazione della tabella FAI_DISPONIBILITA_TEMP (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_REQ_TEMP.OID IS 'Identificativo univoco della tupla (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_REQ_TEMP.OID_DISPONIBILITATEMP IS 'foreign key verso FAI_DISPONIBILITA_TEMP (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_REQ_TEMP.CODICE_MINSAN IS 'fare riferimento alla documentazione della tabella (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_REQ_TEMP.CODICE_EAN IS 'fare riferimento alla documentazione della tabella (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_REQ_TEMP.QUANTITA_RICHIESTA IS 'fare riferimento alla documentazione della tabella (2021.07.26)';

COMMENT ON TABLE FAI_DISPONIBILITA_RES_TEMP IS 'fare riferimento alla documentazione della tabella FAI_DISPONIBILITA_TEMP (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_RES_TEMP.OID IS 'Identificativo univoco della tupla (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_RES_TEMP.QUANTITA_DISPONIBILE IS 'fare riferimento alla documentazione della tabella (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_RES_TEMP.OID_DISPONIBILITAREQTEMP IS 'foreign key verso FAI_DISPONIBILITA_REQ_TEMP (2021.07.26)';

COMMENT ON TABLE FAI_DISPONIBILITA_TEMP IS 'Tiene traccia di una "Richiesta di Disponibilità" verso un FAI_MAGAZZINO o un FAI_FORNITORE, registrando sia la data/ora della richiesta (REQUEST_TS) che quella della risposta (RESPONSE_TS), nonché (sempre che sia possibile) il contenuto raw (XML, JSON, ...) della richiesta inolrata (REQUEST_RAW_DATA) e della risposta ottenuta (RESPONSE_RAW_DATA). Poiché, con la stessa richiesta, è possibile interrogare il Magazzino/Fornitore per più Prodotti/Farmaci, i relativi CODICE_MINSAN/CODICE_EAN e QUANTITA_RICHIESTA trovano posto nella FAI_DISPONIBILITA_REQ_TEMP. E poiché, per ogni QUANTITA_RICHIESTA (es., "7 aspririna"), il Magazzino/Fornitore può rispondere con più QUANTITA_DISPONIBILE (es., "7 non disponibile, ma disponibili 5 e 10"), ecco tali quantità sono registrate nella FAI_DISPONIBILITA_REQ_TEMP (2021.07.26)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.OID IS 'Identificativo univoco della tupla (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.OID_FORNITORE IS 'fare riferimento alla documentazione della tabella (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.OID_MAGAZZINO IS 'fare riferimento alla documentazione della tabella (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.REQUEST_TS IS 'fare riferimento alla documentazione della tabella (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.REQUEST_RAW_DATA IS 'fare riferimento alla documentazione della tabella (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.RESPONSE_TS IS 'fare riferimento alla documentazione della tabella (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.RESPONSE_RAW_DATA IS 'fare riferimento alla documentazione della tabella (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.STATUS_DESCR IS 'v. quanto documentato per il campo OID_STATUS (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.STATUS_TECH_DESCR IS 'v. quanto documentato per il campo OID_STATUS (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.STATUS_UPDATED_TS IS 'v. quanto documentato per il campo OID_STATUS (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.CREATION_TS IS 'data/ora di creazione del record, anche se di fatto superflua, da momento che il record DEVE essere creato contestualmente alla richiesta: CREATION_TS è sensibilmente diverso da REQUEST_TS? Qualcosa non va (2021.07.16)';

COMMENT ON COLUMN FAI_DISPONIBILITA_TEMP.OID_STATUS IS 'Come per gli altri campi STATUS_*, vale quanto documentato per gli omonimi campi di FAI_ORDINE_IN. Tuttavia, in questo caso, OID_STATUS e STATUS_* sono stati aggiunti principalmente per tener traccia delle condizioni d''errore. Oltre lo Status "ERROR", pertanto, gli unici altri Status previsti al momento (2021.07.16) saranno "PROCESSING" (stato con ci si aspetta il record venga creato) e "PROCESSED" alla ricezione ed elaborazione, senza errori (sennò è "ERROR") della risposta (2021.07.16)';

COMMENT ON TABLE FAI_EXPORT_CONFIG IS 'Configurazione. Prevede un solo record (2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.FTP_PROTOCOL IS 'Protocollo FTP da utilizzare. Insieme ad FTP_HOST ed FTP_PORT definisce il Server ftp destinatario del file CSV di ouput. I valori ammessi sono ''FTP'', ''FTPS'' ("FTP over SSL") ed ''SFTP'' (noto anche come "SSH File Transfer Protocol" oppure "Secure File Transfer Protocol"). E'' ammesso anche ''LOCAL '': in tal caso solo FTP_DIR ed FTP_FILENAME sono presi in considerazione e rappresenteranno un path locale (es., ''C:\DEBUG'' e ''testftp.csv'')  (2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.FTP_HOST IS 'Es: FTP_HOST = ''192.168.21.1''. V. anche  documentazione al campo FTP_PROTOCOL (2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.FTP_PORT IS 'Es: FTP_HOST = 21. V. anche  documentazione al campo FTP_PROTOCOL (2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.FTP_DIR IS 'Directory di destinazione del file di output(FTP_FILENAME); es.: FTP_DIR = ''/farmadati/out'' (2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.FTP_LOGIN IS 'Login per l''accesso al Server FTP di output(2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.FTP_PASSWORD IS 'Password per l''accesso al Server FTP di output. Tale password sarà da considerarsi cifrata con gli strumenti it.swdes se FTP_PASSWORD_ENCR = ''S'', altrimenti (se FTP_PASSWORD_ENCR = ''N'') sarà da considerarsi in chiaro (2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.FTP_PASSWORD_ENCR IS 'v. quanto documentato per il campo FTP_PASSWORD (2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.EXPORT_TIME IS 'Orario di export.La data è da considerarsi meaningless e convenzionalmente fissata al 1 gennario 1970 (2021.05.18)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.CSV_FIELD_SEP IS 'Separatore di campo per il CSV esportato (2021.05.30)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.CSV_FIELD_SEP_REPLACEMENT IS 'Se NOT NULL, indica che l''occorrenza del CSV_FIELD_SEP in uno qualsiasi dei campi esportati va sostituita col carattere così indicato. Per esempio, se CSV_FIELD_SEP="," e CSV_FIELD_SEP_REPLACEMENT="*", "aspirina, 20 compresse" viene sostituito nell''export con "aspirina* 20 compresse" (2021.05.28)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.CSV_HEADER_EXPORT_ENABLED IS 'Se ''S'' indica che la prima riga esportata sarà rappresentata dalle intestazioni, ossia i nomi di colonna (2021.05.28)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.CSV_LINE_SEP IS 'Separatore di riga. Tipicamente, CHR(13)||CHR(10) oppure CHR(13) oppure CHR(10)  (2021.05.28)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.LAST_RUN_START_TS IS 'data/ora di avvio dell''ultima operazione periodica di export dei dati. NULL se il processo non è mai stato eseguito (2021.04.27)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.LAST_RUN_END_TS IS 'data/ora di completamento dell''ultima operazione periodica di export dei datii. NULL se il processo non è mai stato eseguito o se l''export è in corso al momento della lettura del campo (2021.05.28)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.LAST_RUN_DONE IS 'Se ''S'', l''ultimo export dei dati si è concluso con successo, altrimetni ''N'' (2021.05.28)';

COMMENT ON COLUMN FAI_EXPORT_CONFIG.LAST_RUN_DESCR IS 'Fornisce informazioni circa l''ultima esecuzione (2021.05.28)';

COMMENT ON TABLE FAI_FORNITORE_FARMACO IS 'defnisce il LINE_ID che identifica il farmaco individuato dal codice MINSAN per il FAI_FORNITORE referenziato (2021.06.10)';

COMMENT ON COLUMN FAI_FORNITORE_FARMACO.OID IS 'Identificativo univoco della tupla (2021.06.17)';

COMMENT ON COLUMN FAI_FORNITORE_FARMACO.CODICE_MINSAN IS 'Codice MinSan Identificativo del Farmaco (2021.06.10)';

COMMENT ON COLUMN FAI_FORNITORE_FARMACO.LINE_ID IS 'E'' una "sequence senza gap", 1-based, che parte dal valore 1 per ogni singolo fornitore (2021.06.10)';

COMMENT ON COLUMN FAI_FORNITORE_FARMACO.OID_FORNITORE IS 'foreign key verso FAI_FORNITORE (2021.06.10)';

COMMENT ON COLUMN FAI_FORNITORE_FARMACO.CREATION_TS IS 'data/ora di creazione del record (2021.06.18)';

COMMENT ON TABLE FAI_FORNITORE_PROPERTY IS 'rappresenta una proprieta'' del FAI_FORNITORE referenziato espressa come coppia chiave/valore, dove la chiave è costitutita dal campo KEY ed il valore dal campo VALUE. Tali proprietà saranno essenzialmente utilizzate dalla classe specificata dal campo  SUPPLIERSERVICE_CLASS_NAME del FAI_FORNITORE (2021.07.27)';

COMMENT ON COLUMN FAI_FORNITORE_PROPERTY.KEY IS 'v. documentazione alla tabella (2021.07.27)';

COMMENT ON COLUMN FAI_FORNITORE_PROPERTY.VALUE IS 'v. documentazione alla tabella (2021.07.27)';

COMMENT ON COLUMN FAI_FORNITORE_PROPERTY.OID_FORNITORE IS 'foreign key verso FAI_FORNITORE (2021.07.27)';

COMMENT ON TABLE FAI_GENERIC_TASK IS 'Rappresenta un generico "task". I campi SCHEDULED_TIMES e SCHEDULED_SMTWTFS risulteranno entrambi valorizzati, se il task va schedulato, o entrambi NULL se il task è invocato da un task principale. Ad esempio, esisterà plausibilmente un task che importa "web_order.csv", un''altro che si occupa di reperire i farmaci dai magazzini oppure ordinarli dai fornitori e così via. Esisterà un solo task con SCHEDULED_TIMES e SCHEDULED_SMTWTFS valorizzati che esegue in sequenza quelli citati i quali, necessariamente, avranno SCHEDULED_TIMES e SCHEDULED_SMTWTFS  posti a NULL (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.OID IS 'identificativo univoco della tupla (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.ACRONYM IS 'mnemonico identificativo univoco della tupla (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.DESCR IS 'descrizione facoltativa (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.CLASS_NAME IS 'classe implementante il task (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.SCHEDULED_TIMES IS 'elenco di orari di attivazione del task - formato HH:mm con virgola come separatore - , a patto che venga soddisfatta anche la condizione configurata in SCHEDULED_SMTWTFS. Può essere NULL (v. documentazione alla tabella)';

COMMENT ON COLUMN FAI_GENERIC_TASK.SCHEDULED_SMTWTFS IS 'pattern di attivazione settimanale (Sunday, Monday, ...). Il task verrà eseguito a patto che per il corrispondente giorno della settimana sia specificato "X" invece che "-". Può essere NULL (v. documentazione alla tabella) (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.LAST_RUN_START_TS IS 'ultima data/ora di avvio del taski. NULL se il task non è mai stato eseguito (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.LAST_RUN_END_TS IS 'ultima data/ora di completamento del taski. NULL se il task non è mai stato eseguito o se la sua esecuzione è in corso al momento della lettura del campo (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.LAST_RUN_DONE IS '''S'', se l''ultima attività del task si è conclusa con sucesso, altrimetni ''N''  (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.LAST_RUN_DESCR IS 'fornisce informazioni circa l''ultima esecuzione del task (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK.ENABLED IS 'normalmente ''S'', se ''N'' consente di disabilitare il task';

COMMENT ON TABLE FAI_GENERIC_TASK_PROPERTY IS 'rappresenta una proprieta'' del FAI_GENERIC_TASK referenziato espressa come coppia chiave/valore, dove la chiave è costitutita dal campo KEY ed il valore dal campo VALUE (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK_PROPERTY.OID IS 'Identificativo univoco della tupla (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK_PROPERTY.KEY IS 'v. documentazione alla tabella (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK_PROPERTY.VALUE IS 'v. documentazione alla tabella (2021.06.18)';

COMMENT ON COLUMN FAI_GENERIC_TASK_PROPERTY.OID_GENERICTASK IS 'foreign key verso FAI_GENERIC_TASK  (2021.06.18)';

COMMENT ON TABLE FAI_GROUP_EXPORT IS 'Rappresenta un raggruppamento. FAI_TABLE_EXPORT con lo stesso FAI_GROUP_EXPORT saranno considerate parte dello stesso raggruppamento. Al momento (2021.05.18) il valore attribuito a questo campo GROUP_NAME_EXPORT (es., "PoImportBlanketAgreements") corrisponderà al nome dello zip (es., "PoImportBlanketAgreements.zip") che conterrà tutte le FAI_TABLE_EXPORT dello stesso gruppo (2021.05.18)';

COMMENT ON COLUMN FAI_GROUP_EXPORT.OID IS 'Identificativo univoco della tupla (2021.05.18)';

COMMENT ON COLUMN FAI_GROUP_EXPORT.GROUP_IDX IS 'inseme a TABLE_EXPORT.TABLE_IDX  consente di statabilire l''ordine con cui i dati delle tabelle sono importati in questo schema ed esportarti verso il sistema target (2021.05.18)';

COMMENT ON COLUMN FAI_GROUP_EXPORT.GROUP_NAME_EXPORT IS 'v. quanto documentato per la tabella (2021.05.18)';

COMMENT ON TABLE FAI_IMPORT_CONFIG IS 'configurazione e stato del generico Provider di Import dei dati. Al momento (2021.05.18) sono già stati implementati i Provider "FARMADATI" e  "FARMACLICK", ma ne sono previsti altri. Sia ''ACRONYM che la CLASS_NAME sono unique. I parametri (SERVICE_*) coprono l''esigenza della maggior parte (forse tutti) i sistemi terzi a cui attingere. Qualora dovesse rendersi necessaria la configurazione di parametri peculiarissimi, sarà sufficiente introdurre una tabella FAI_IMPORT_PROVIDER_PROPERTY con KEY/VALUE referenziante la presente (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.OID IS 'identificativo univoco della tupla (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.ACRONYM IS 'mnemonico identificativo del provider. Es., "FARMADATI", "FARMACLICK" (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.DESCR IS 'descrizione facoltativa (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.SERVICE_LOGIN IS 'login, se prevista dal provider (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.SERVICE_PASS IS 'password, se prevista dal provider. Tale password sara'' da considerarsi cifrata con gli strumenti it.swdes se SERVICE_PASS_ENCR = ''S'', altrimenti (se SERVICE_PASS_ENCR = ''N'') sara'' da considerarsi in chiaro  (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.SERVICE_PASS_ENCR IS 'v. quanto documentato per il campo SERVICE_PASS  (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.SERVICE_QUERY_TIME IS 'Orario di interrogazione del servizio e, quindi, di scaricamento/import dei dati ed aggiornamento della banca dati. Più precisamente, ogni giorno, all''orario specificato da questo parametro, avrà luogo l''operazione specificata dal pattern settimanale SERVICE_QUERY_SMTWTFS cui si rimanda. La data è da considerarsi meaningless e convenzionalmente fissata al 1 gennario 1970  (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.SERVICE_QUERY_SMTWTFS IS 'Pattern settimanale costituito da una stringa di 7 caratteri ove ognuno di essi può essere "C" per il download integerale dei dati, "V" per le sole variazioni (se supportato), "-" per nessuna operazione (in questo mdoo è possibile avere uno più giorni della settimana in corripondenza dei quali all''orario SERVICE_QUERY_TIME non accade assolutamente nulla). Come richiamato dal nome del campo, il primo giorno della settimana è "Sunday" (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.SERVICE_QUERY_URL IS 'url principale del servizio, se previsto dal provider (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.SERVICE_RESUME_PREV_SESS IS 'Se ''S'' ("Si", "true") le interrogazioni già completate con successo non saranno di nuovo ripetute. Di norma si utilizzerà questo valore solo per scopi di debug in quanto, una volta scaricati/importati i dati, questi non sarebbero mai più scaricati di nuovo. Se ''A'' ("Auto") si otterrà esattametne lo stesso comportamento di ''S'' se e solo se il download precedente non è terminato con successo e si tratta dello stesso  tipo di download ("C" o "V" ). Se ''N'', il download riprenderà da capo ad ogni sessione (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.LAST_RUN_START_TS IS 'data/ora di avvio dell''ultima operazione periodica di import. NULL se il processo non e'' mai stato eseguito (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.LAST_RUN_END_TS IS 'data/ora di avvio dell''ultima operazione periodica di import. NULL se il processo non e'' mai stato eseguito (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.LAST_RUN_TYPE IS 'Tipologia dell''ultimo import, determinata dal pattern settimanale SERVICE_QUERY_SMTWTFS. Può dunque essere "C" opprue "V" o NULL se il processo non è mai stato avviato prima (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.LAST_RUN_DONE IS ' ''S'' se l''ultimo import dei dati si e'' concluso con successo, altrimetni ''N'' (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.LAST_RUN_DESCR IS 'Fornisce informazioni circa l''ultima esecuzione  (2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.CLASS_NAME IS 'classe implementante lo specifico provider(2021.05.18)';

COMMENT ON COLUMN FAI_IMPORT_CONFIG.SERVICE_QUERY_ZIPPED_CONTENT IS 'se ''S'', e se la funzionalità è supportata dal servizio, i dati saranno scaricato in formato compresso (tipicamente, zip) (2021.05.18)';

COMMENT ON TABLE FAI_ITEM_STATUS IS 'Consente di contrassegnare lo stato di un oggetto. Ad esempio, un ORDINE_IN appena creato sarà in stato "TO PROCESS". Gli stati previsti, al momento, sono "TO PROCESS", "PROCESSING", "PROCESSED",  "ERROR", "SUSPENDED", a seconda che il record sia, rispettivamente, in attesa di essere elaborato, in corso di elaborazione, l''elaborazione sia stata completata con successo, in errore (ciò determina la sospensione dell''intero processo finché il problema non viene risolto da un operatore), sospeso da parte di un operatore (il processo potrà continuare operando sugli elementi che non sono impattati dall''elemento sospeso) (2021.06.18) Sono poi stati aggiunti gli stati "TO APPROVE", ad indicare un record che richiede l''approvazione da parte di un Utente, "APPROVED", dall''ovvio significato, e "VOIDED", simile a "SUSPENDED", ma assegnato da un Utente (2021.06.30)';

COMMENT ON COLUMN FAI_ITEM_STATUS.OID IS 'idenrtificativo univoco della tupla (2016.06.16)';

COMMENT ON COLUMN FAI_ITEM_STATUS.ACRONYM IS 'Acronimo univoco della tupla (2016.06.16)';

COMMENT ON COLUMN FAI_ITEM_STATUS.DESCR IS 'Descrizione (2016.06.16)';

COMMENT ON TABLE FAI_LISTINI_DISPONIBILITA_TEMP IS 'Viene popolata "al volo" nell''ambito del task di determinazione della disponibilità dei farmaci. Più precisamente è di ausilio all''individuazione dei Fornitori in grado di offrire l''offerta migliore per ognuno dei farmaci che è necessario reperire ed all''aggregazione delle offerte migliori per singolo Fornitore (in modo da poter poi interrogare ogni fornitore per più farmaci contemporaneamente, almeno dove l''API supporta tale possibilità). Mantenendo sempre un subset ridotto di tutti i Listini di tutti i Fornitori, e dotata di indici ad-hoc, garantisce, necessariamente, velocità di accesso in lettura e scrittura necessariamente superiori alle tabelle di origine (al momento, 2021.07.14, omogeneizzate dalla V_FAI_LISTINI_FORNITORI) (2021.07.14)';

COMMENT ON COLUMN FAI_LISTINI_DISPONIBILITA_TEMP.OID_FORNITORE IS 'foreign key verso FAI_FORNITORE. Dato il carattere di temporaneità del contenuto della tabella, non necessariamente risulteranno abilitati i vincoli di integrità referenziale.';

COMMENT ON TABLE FAI_MAGAZZINO IS 'Rappresenta uno dei magazzini anagrafati e gestiti, come Loreto o UPS (2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.OID IS 'identificativo univoco della tupla (2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.ACRONYM IS 'acronimo univoco della tupla (2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.DESCR IS 'descrizione (2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.COMUNE IS '(2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.PROVINCIA IS '(2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.REGIONE IS '(2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.CAP IS '(2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.INDIRIZZO IS '(2021.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO.SUPPLIERSERVICE_CLASS_NAME IS 'nome della classe implementante l''interfaccia fai.broker.supplier.SupplierService  che consente di interagire coi sistemi del Magazzino. Il campo è NULLable al solo scopo di consentire il popolamento della banca dati per non impedire tutte le operazioni che è possibile effettuare sulla stessa senza interagire coi sistemi del Magazzino. Ovviamente, se questo campo è NULL, qualsiasi tentativo di interazione coi sistemi del Magazzino, risulterà impossibile (2021.07.27)';

COMMENT ON TABLE FAI_MAGAZZINO_PROPERTY IS 'rappresenta una proprieta'' del FAI_MAGAZZINO referenziato espressa come coppia chiave/valore, dove la chiave è costitutita dal campo KEY ed il valore dal campo VALUE. Tali proprietà saranno essenzialmente utilizzate dalla classe specificata dal campo  SUPPLIERSERVICE_CLASS_NAME del FAI_MAGAZZINO (2021.07.27)';

COMMENT ON COLUMN FAI_MAGAZZINO_PROPERTY.OID IS 'identificativo univoco della tupla (2021.07.27)';

COMMENT ON COLUMN FAI_MAGAZZINO_PROPERTY.KEY IS 'v. documentazione alla tabella (2021.07.27)';

COMMENT ON COLUMN FAI_MAGAZZINO_PROPERTY.VALUE IS 'v. documentazione alla tabella (2021.07.27)';

COMMENT ON COLUMN FAI_MAGAZZINO_PROPERTY.OID_MAGAZZINO IS 'foreign key verso FAI_MAGAZZINO (2021.07.27)';

COMMENT ON TABLE FAI_MAGAZZINO_TIPOFARMACO IS 'Associativa che consente di specificare, per ogni FAI_MAGAZZINO, quali FAI_TIPO_FARMACO sono supportati. Al momento (2016.06.16) sono previsti i soli due magazzini "Loreto", di Casoria, per i Farmaci, e "UPS", di Formello (Roma), per i Parafarmaci (2016.06.16)';

COMMENT ON COLUMN FAI_MAGAZZINO_TIPOFARMACO.OID IS 'Identificativo univoco della tupla (2021.06.17)';

COMMENT ON COLUMN FAI_MAGAZZINO_TIPOFARMACO.OID_MAGAZZINO IS 'foreign key verso FAI_MAGAZZINO (2021.06.17)';

COMMENT ON COLUMN FAI_MAGAZZINO_TIPOFARMACO.OID_TIPOFARMACO IS 'foreign key verso FAI_TIPO_FARMACO (2021.06.17)';

COMMENT ON COLUMN FAI_MAGAZZINO_TIPOFARMACO.CONTEXT IS 'Contesto nel quale l''associazione è valida. Si è reso necessario in quanto - per esempio - UPS Casoria va considerato come Magazzino cui attingere solo ed esclusivamente Parafarmaci nel _contesto_ dell''evasione dei  FAI_ORDINE_IN*, mentre nel _cotesto_ del "Flusso Trade" e "Flusso Retail" del §5 dell''allegato "nota_fai_v6.docx" può essere usato sia per i Farmaci che per i Parafarmaci. Essendo impossible al momento determinare quanti e quali "contesti" possono esistere, il presente non può che essere un campo VARCHAR2 che recherà un testo identificativo del contesto man mano che nuovi contesti di utilizzo andranno a palesarsi (21.07.05)
';

COMMENT ON TABLE FAI_NO_GAP_SEQUENCE IS 'Tabella di servizio per la generazione di "sequences senza gap". La funzione (procedure o metodo/classe Java) che opera sulla tabella, per un qualsiasi ACRONYM, incrementa il valore LAST_VALUE di una unità. Se il record per l''ACRONYM indicato non esiste, viene creato con LAST_VALUE = 1. Il valore di LAST_VALUE viene quindi restituito. Se tutte le operazioni nella transazione terminano con successo, la transazione viene confermata - commit - e LAST_VALUE  diventa consolidato. Se la transazione viene abortita - rollback - LAST_VALUE si troverà automaticamente impostato al valore precedente per efffetto della rollback (2021.06.10)';

COMMENT ON COLUMN FAI_NO_GAP_SEQUENCE.OID IS 'identificativo univoco della tupla (2021.06.10)';

COMMENT ON COLUMN FAI_NO_GAP_SEQUENCE.ACRONYM IS 'v. documentazione alla tabella (2021.06.10)';

COMMENT ON COLUMN FAI_NO_GAP_SEQUENCE.LAST_VALUE IS 'v. documentazione alla tabella (2021.06.10)';

COMMENT ON TABLE FAI_ORDINE_IN IS 'Rappresenta le "Riga Testata" del file di Ordini documentato nel "ordercsv.pdf" reso disponibile al 2021.05.06 e corredato del file "web_order.csv" di esempio. La documentazione ai singoli campi della presente tabella è attinta (copia-incolla) dal "ordercsv.pdf". I campi non documentati, sono presenti in "web_order.csv", ma non citati in "ordercsv.pdf". Di questi, quelli che non è chiara l''appartenenza alla "Testata" o alla "Riga", sono riportati su entrambe le tabelle. "ordercsv.pdf"  prescrive che «Tutti i campi devono essere racchiusi nelle doppie virgolette " (anche quelli vuoti)», ma ciò non trova riscontro in "web_order.csv" (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN.OID IS 'Identificativo univoco della tupla (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN.ID_VENDITA IS 'ID vendita (numero ordine) = 34257 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.NOME_COMPLETO_ACQUIRENTE IS 'Nome completo dell''acquirente = Maria Rossi (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.NUMERO_TELEFONO_ACQUIRENTE IS 'Numero di telefono dell''acquirente = 3447989467 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.EMAIL_ACQUIRENTE IS 'Email dell''acquirente = mrossi@gmail.com (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.INDIRIZZO_ACQUIRENTE_1 IS 'Indirizzo dell''acquirente 1 = Via Nazionale, 190 scala C (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.CITTA_ACQUIRENTE IS 'Città dell''acquirente (città) = Sorrento (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.STATO_ACQUIRENTE IS 'Stato dell''acquirente (provincia) = Na (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.CAP_ACQUIRENTE IS 'CAP dell''acquirente (cap) = 80067 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.PAESE_ACQUIRENTE IS 'Paese dell''acquirente (nazione) = Italia (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.QUANTITA IS 'Quantità (pezzi totali) = 4 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.SPEDIZIONE_E_IMBALLAGGIO IS 'Spedizione e imballaggio (spese di spedizione, è un di cui del prezzo totale) = EUR 6,90 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.PREZZO_TOTALE IS 'Prezzo totale (prezzo di vendita totale) = EUR 61,98 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.METODO_DI_PAGAMENTO IS 'Metodo di pagamento (Paypal = 1, Contrassegno = 2, CartaDiCredito = 3, BonificoBancario = 4) = 2 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.DATA_DI_VENDITA IS 'Data di vendita (data dell''ordine) = 23-jul-14 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.FEEDBACK_LASCIATO IS '''S'' oppure ''N'' (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.CAMPO_PRIVATO IS 'Campo privato (1 se pagato, 0 se non pagato) = 1 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.SERVIZIO_DI_SPEDIZIONE IS 'Servizio di spedizione (0 se Ritiro in farmacia, 1 se Spedito) = 1 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.ALIQUOTA_IVA_INCLUSA IS 'Aliquota IVA inclusa = 0 se i valori indicati sono deivati (clienti esteri che non pagano iva), 1 oppure vuoto se i valori indicati sono ivati (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.NOME_COMPLETO_FATTURAZIONE IS 'Nome completo fatturazione = ROSSI snc di Maria Rossi (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.CF_FATTURAZIONE IS 'CF fatturazione (Codice Fiscale acquirente) = XXXXXX77E09XXXXY (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.PIVA_FATTURAZIONE IS 'PIVA fatturazione (Partita iva acquirente) = (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.INDIRIZZO_FATTURAZIONE IS 'Indirizzo fatturazione = Via Pertini, 90 Città (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.CITTA_FATTURAZIONE IS 'Città fatturazione (città) = Sorrento (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.STATO_FATTURAZIONE IS 'Stato fatturazione (provincia) = Napoli (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.CAP_FATTURAZIONE IS 'CAP fatturazione (cap) = 80067 (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.PAESE_FATTURAZIONE IS 'Paese fatturazione (nazione, 2 caratteri) = IT (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.CODICEFE IS 'CodiceFE (Codice Destinatario Fattura Elettronica) (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.PEC IS 'PEC (PEC destinatario Fattura Elettronica) (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.NOTE IS 'Note (testo libero, ATTENZIONE non usare il carattere " all''interno del testo) (2021.06.09)';

COMMENT ON COLUMN FAI_ORDINE_IN.CREATION_TS IS 'data/ora di creazione del record (2021.06.14)';

COMMENT ON COLUMN FAI_ORDINE_IN.OID_STATUS IS 'foreign key verso FAI_ITEM_STATUS (2021.06.16)';

COMMENT ON COLUMN FAI_ORDINE_IN.STATUS_DESCR IS 'fornisce informazioni aggiuntive circa il FAI_ITEM_STATUS referenziato. Essenzialmente concepito per poter fornire ulteriori informazioni - fruibili all''utente finale - nel caso in cui lo scenario in cui lo stato rerenziato è "ERROR" (2021.06.16)';

COMMENT ON COLUMN FAI_ORDINE_IN.STATUS_TECH_DESCR IS 'analogo a STATUS_DESCR, ma, rispetto a tale campo, potrà contenere anche informazioni tecniche di più basso livello tecnico (codici d''errore HTTP, Stack Trace, ecc...), non necessariamente fruibili all''utente finale  (2021.06.16)';

COMMENT ON COLUMN FAI_ORDINE_IN.OID_ORDINEINCOLLECTION IS 'foreign key verso FAI_ORDINE_IN_COLLECTION (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN.STATUS_UPDATED_TS IS 'data/ora di aggiornamento dello status  (2021.06.16)';

COMMENT ON TABLE FAI_ORDINE_IN_COLLECTION IS 'Rappresenta una collezione di ordini in ingresso acquisita in blocco, in modo atomico, dal processo. Facendo riferimento agli elementi noti al 2021.06.17, FAI_ORDINE_IN_COLLECTION, FAI_ORDINE_IN e FAI_ORDINE_IN_RIGA_DETTAGLIO sono gli equivalenti E-R, rispettivamente, del file in ingresso "web_order.csv" e (con terminologia attinta a "ordercsv.pdf") "Riga Testata" e "Riga Dettaglio" (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.OID IS 'Identificativo univoco della tupla (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.INPUT_RESOURCE IS 'se valorizzato, mantiene traccia del nome della risorsa dalla quale sono stati estratti i FAI_ORDINE_IN (e FAI_ORDINE_IN_RIGA_DETTAGLIO) referenzianti questo record. Al momento, l''unica copia resa disponibile di tale risorsa, in forma di file, reca il nome "web_order.csv" (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.INPUT_RESOURCE_FULL_PATH IS 'Vale quanto scritto per INPUT_RESOURCE, ma, se valorizzata, include anche altri informazioni circa la fonte alla quale è stata attinta la risorsa. Es. "ftp://localhost:21/test/web_order.csv". La funzione del campo è puramente documetnativa, per cui potrebbero esser presenti URI che URI non sono, es. "[soap]http://localhost/serviceTest/getCsv-->web_order.csv" (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.BATCH_ID IS 'Identificativo dell''intero processo originante da dal csv in ingresso ("web_order.csv"). E'' pertanto unique e, per convenzione, coincide con la data/ora di ricezione del csv espressa in formato yyyyMMddHHmmss. Ad esempio, tutti gli ordini (FAI_ORDINE_IN) estratti dal csv ricevuto alla 15:27:01 del 16 giugno 2021 avranno BATCH_ID 20210616152701 (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.CREATION_TS IS 'data/ora di creazione del record (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.OID_STATUS IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.STATUS_DESCR IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.STATUS_TECH_DESCR IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.STATUS_UPDATED_TS IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_IN_COLLECTION.UNIQUE_ID IS 'Al momento non è chiaro se il file sarà univocamente identificato dal suo nome, o dal suo contenuto, o da altro. A prescindere da quella che sarà la decisione finale, il presente campo risulterà sempre valorizzato, per l''appunto, con tale identificativo (2021.06.21)';

COMMENT ON TABLE FAI_ORDINE_IN_RIGA_DETTAGLIO IS 'Rappresenta le "Riga Dettaglio" del file di Ordini documentato nel "ordercsv.pdf" reso disponibile al 2021.05.06 e corredato del file "web_order.csv" di esempio. La documentazione ai singoli campi della presente tabella è attinta dal  "ordercsv.pdf". I campi non documentati, sono presenti in "web_order.csv", ma non citati in "ordercsv.pdf". Di questi, quelli che non è chiara l''appartenenza alla "Testata" o alla "Riga", sono riportati su entrambe le tabelle. "ordercsv.pdf"  prescrive che «Tutti i campi devono essere racchiusi nelle doppie virgolette " (anche quelli vuoti)», ma ciò non trova riscontro in "web_order.csv". (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.OID IS 'identificativo univoco della tupla (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.TITOLO_INSERZIONE IS 'Titolo inserzione (nome prodotto), es. "Phyto 7 Crema Idratante Da Giorno Capelli Secchi 50m" oppure "Coupon" (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.ETICHETTA_PERSONALIZZATA IS 'Etichetta personalizzata (codice aic del prodotto), es. "911116806", "P99999998" (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.QUANTITA IS 'Quantità (pezzi del prodotto), es., 2, 1 (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.PREZZO_FINALE IS 'Prezzo finale (prezzo di vendita unitario), es EUR 10,53 o EUR -5,00 (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.ALIQUOTA_IVA_INCLUSA IS 'Attenzione: per i clienti esteri che non pagano iva bisogna indicare nel campo "Aliquota IVA inclusa" il valore 0 e tutti i valori di PREZZO devono essere già deivati. (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.OID_ORDINEIN IS 'foreign key verso FAI_ORDINE_IN, ossia l''Ordine in Ingresso che contiene questa riga (2021.06.10)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.CREATION_TS IS 'data/ora di creazione del record (2021.06.14)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.IS_COUPON IS 'normalmente ''N'', se ''S'' indica che la riga è un Coupon invece che un Prodotto (2021.07.23)';

COMMENT ON COLUMN FAI_ORDINE_IN_RIGA_DETTAGLIO.MAGAZZINO_ACRONYM IS 'Se NOT NULL, indica il FAI_MAGAZZINO presso il quale va reperito il prodotto. Alcune precisazioni. Primo, nell''ambito del processo di elaborazione degli Ordini, questo campo sarà plausibilmente obblgiatorio, pertanto i controlli di obbligatorietà andranno fatti lato codice. Secondo, al momento (2021.09.21) NON è certo (neppure è disponibile il CSV) che la stringa coinciderà con l''ACRONYM di FAI_MAGAZZINO: potrebbe tranquillamente essere un testo differente che richiederà o un nuovo campo su FAI_MAGAZZINO (es. FAI_ORDINE_MAGAZZINO_ACRONYM) o un''intera tabella per la trascodifica (2021.09.21).';

COMMENT ON TABLE FAI_ORDINE_OUT IS 'Aggrega i FAI_APPROVVIGGIONAMENTO_FARMACO relativi allo stesso FAI_FORNITORE. Rispetto a FAI_ORDINE_OUT, quindi, FAI_APPROVVIGGIONAMENTO_FARMACO sono i "FAI_ORDINE_OUT_RIGA_DETTAGLIO" (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.OID IS 'Identificativo univoco della tupla (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.ID_ORDINE IS 'Identificativo dell''Ordine, se applicabile (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.ID_RICEVUTA IS 'Identificativo della Ricevuta dell''Ordine, se applicabile (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.PREZZO_TOTALE IS 'importo totale dell''ordine (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.ALIQUOTA_IVA IS 'aliquota IVA espresa in percentuale. 20.5 rappresenta il "20.5%" (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.ALIQUOTA_IVA_INCLUSA IS '''S'' se l''aliquota IVA è inclusa, altrimenti ''N''.  (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.OID_FORNITORE IS 'foreign key verso FAI_FORNITORE (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.OID_STATUS IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.STATUS_DESCR IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.STATUS_TECH_DESCR IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.STATUS_UPDATED_TS IS 'vale quanto documentato per l''omonimo campo di FAI_ORDINE_IN (2021.06.17)';

COMMENT ON COLUMN FAI_ORDINE_OUT.CREATION_TS IS 'data/ora di creazione del record (2021.06.18)';

COMMENT ON TABLE FAI_TABLE_EXPORT IS 'Rappresenta una tabella di dati da esportare verso un sistema esterno. Al momento (2021.05.18) il formato di export è CSV e il sistema esterno è un prodotto Oracle. La struttura della tabella è definita da FAI_TABLE_EXPORT e FAI_COLUMN_EXPORT. Ulteriori campi forniscono informazioni circa il recupero dei dati che la popolano, la periodicità dell''export, ecc... I dati da esportare passano sempre per una tabella Oracle fisica, il cui nome è specificato da TABLE_NAME_ORACLE e le cui colonne sono FAI_COLUMN_EXPORT.COLUMN_NAME_ORACLE, generata automaticamente dal sistema (2021.05.18)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.OID IS 'identificativo univoco della tupla (2021.05.18)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.SQL_QUERY_IN IS 'Se NOT NULL è la query per mezzo della quale viene vengono estratti i dati che saranno importati nella TABLE_NAME_ORACLE. La query deve presentare i campi oracle indicati nelle COLUMN_EXPORT.COLUMN_NAME_ORACLE referenzianti questo record e si presenterà nel formato INSERT INTO ... SELECT ...(2021.05.18)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.SQL_QUERY_IN_FILE IS 'Se NOT NULL indica il percorso completo sul filesystem della query avente le medesime caratteristiche documetnate per SQL_QUERY_IN, cui si rimanda (2021.05.25)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.SMTWTFS_EXPORT_PATTERN IS 'Pattern di esportazione settimanale (Sunday, Monday, ...). I dati saranno esportati verso il sistema target (previa importazione da altri sistemi sorgente) a patto che per il corrispondente giorno della settimana sia specificato "X" invece che "-"  (2021.05.18)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.TABLE_NAME_ORACLE IS 'nome della tabella Oracle. Per ogni record di questa tabella, infatti, sarà creata una tabella Oracle il cui nome è rappresetato da questo campo (2021.05.18)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.TABLE_IDX IS 'inseme a GROUP_EXPORT.GROUP_IDX  consente di statabilire l''ordine con cui i dati delle tabelle sono importati in questo schema ed esportarti verso il sistema target. (2021.05.18)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.TABLE_NAME_EXPORT IS 'nome della tabella nel sistema di destinazione (es. "PoHeadersInterfaceBlanket"). Al momento (2021.05.18) tale nome corrisponderà al nome del CSV esportato verso il prodotto Oracle (es. "PoHeadersInterfaceBlanket.csv") (2021.05.18)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.SQL_DDL IS 'utilizzato dal sistema contiene lo statement - o gli stamtent - SQL utilizzati per creare la tabella Oracle la cui struttura è defnita per mezzo di questo record e dei record tabella COLUMN_EXPORT che lo referenziano. Il sistema utilizza questo campo per capire se la struttura della tabella è cambiata e, in caso affermativo, la cancella (DROP) e ricrea (2021.05.28)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.OID_GROUPEXPORT IS 'Identificativo univoco della tupla (2021.05.18)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.SQL_QUERY_OUT IS 'Analogo a SQL_QUERY_IN, ma è la SELECT per mezzo della quale i dati importati nella TABLE_NAME_ORACLE vengono recuperati e formattati per l''export. Obbligatorio rispettare i nomi dei campi che seguono la keyword SELECT e che dovranno coincidere coi nomi dei campi FAI_COLUMN_EXPORT.COLUMN_NAME_ORACLE. In base a questa corrispondenza, infatti, viene determinato il formatter da utilzizare (DATE_FORMAT_PATTERN, NUMB_FORMAT_PATTERN) e quant''altro necessario all''operazione di trascodifica (2021.05.25)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.SQL_QUERY_OUT_FILE IS 'E'' per SQL_QUERY_OUT ciò che SQL_QUERY_IN_FILE è per SQL_QUERY_IN (2021.05.21)';

COMMENT ON COLUMN FAI_TABLE_EXPORT.LAST_EXPORTED_VERSION_NUMBER IS 'Riporta l''ultimo numero di versione della TABLE_NAME_ORACLE esportata con successo. Se nessuna esportazione ha avuto ancora luogo per la tabella, questo campo risulterà valorizzato con NULL (2021.05.28)';

COMMENT ON TABLE FAI_TIPO_FARMACO IS 'Tipologia di Farmaco, quali "FARMACO" o "PARAFARMACO" (2021.06.16)';

COMMENT ON COLUMN FAI_TIPO_FARMACO.OID IS 'identificativo univoco della tupla (2021.06.16)';

COMMENT ON COLUMN FAI_TIPO_FARMACO.ACRONYM IS 'acronimo univoco della tupla (2021.06.16)';

COMMENT ON COLUMN FAI_TIPO_FARMACO.DESCR IS 'descrizione (2021.06.16)';
