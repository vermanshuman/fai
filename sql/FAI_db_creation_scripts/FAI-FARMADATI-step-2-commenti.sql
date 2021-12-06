COMMENT ON COLUMN FAI_FARMADATI_TABLE_TO_IGNORE.TABLE_NAME IS 'Ie tabelle elencate nella presente, NON saranno mai scaricate.Il campo TABLE_NAME coincide con l''omonimo campo di FARMADATI_DDL (2021.05.03)';

COMMENT ON TABLE FAI_FARMADATI_DDL IS 'Serve a mantenere copia del DDL dinamicamente generato per la creazione delle tabelle ospitanti i dati restituiti da Farmadati. Ciò consente di dedurre rapidamente se una tabella, magari esistente, è nel frattempo cambiata (semplicemente confrontando l''SQL qui conservato con l''SQL che sarebbe necessario eseguire per ri-crearla)  imponendo la DROP della stessa prima del ri-popolamento, anziché la semplice DELETE (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_DDL.CREATION_TS IS 'data/ora di creazione del record (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_DDL.NO_LONGER_EXISTS_TS IS 'Se NOT NULL indica la data/ora alla quale è stato determinato che la tabella non è più esistente. In fase di progetto, si ipotizza di impostare a SYSDATE questo campo ad ogni sessione di acquisizione, per poi impostarlo a NULL se si ha ancora riscontro della tabella (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_DDL.ALL_DATA_LOADED_TS IS 'Impostato a NULL all''avvio della sessione di acquisizione (o alla creazione del record), se NOT NULL indica la data/ora in corrispondenza della quale la tabella è stata completamente popolata (2021.04.29)';

COMMENT ON TABLE FAI_FARMADATI_WS IS 'Mantiene, per ogni servizio invocato, copia dell''XML ricevuto come risposta (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS.CREATION_TS IS 'data/ora di creazione del record (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS.RESULT_XML IS 'XML restituto dal WebService (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS.SERVICE_NAME IS 'nome del servizio invocato, es. "GetEnabledDataSet", "GetSchemaDataSet", ecc... (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS.SERVICE_PARAMS IS 'Normalmente NULL, può contenere informazioni sulla valoriazzazione dei parametri utilizzati per quei servizi che li prevedono (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS.OID IS 'Identificativo univoco della tupla (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS.RESULT_SUCCESS IS 'Se ''S'' l''interrgoazione ha avuto successo, altrimenti ''N''. (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS.RESULT_DESCR IS 'ulteriori informazioni circa l''esito dell''interrogazione. Utile soprattutto se RESULT_SUCCESS=''N''. (2021.04.27)';

COMMENT ON TABLE FAI_FARMADATI_WS_CONFIG IS 'Raccoglie tutti i parametri di configurazione del sistema (2021.04.26)';

COMMENT ON COLUMN FAI_FARMADATI_WS_CONFIG.WS_URL IS 'Url del WebSevice Farmadati. Al 2021.04.26, tale url è https://webservices.farmadati.it/WS2S/FarmadatiItaliaWebServicesM2.svc (2021.04.26)';

COMMENT ON COLUMN FAI_FARMADATI_WS_CONFIG.WS_LOGIN IS 'Login per l''accesso al WebService Farmadati(2021.04.26)';

COMMENT ON COLUMN FAI_FARMADATI_WS_CONFIG.WS_PASSWORD IS 'Password per l''accesso al WebService Farmadati. Tale password sarà da considerarsi cifrata con gli strumenti it.swdes se WS_PASSWORD_ENCR = ''S'', altrimenti (se WS_PASSWORD_ENCR = ''N'') sarà da considerarsi in chiaro (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS_CONFIG.WS_QUERY_INTERVAL IS 'Espresso in minuti, rappresenta l''intervallo che deve intercorrere tra una esecuzione del processo (interrogazione WebService Farmadati, ecc..) e la successiva. La prima esecuzione avverrà all''ora specificata in WS_QUERY_TIME che, all''avvio della pianificazione, sarà sempre considerata ricadente nel futuro (2021.04.30)';

COMMENT ON COLUMN FAI_FARMADATI_WS_CONFIG.WS_QUERY_TIME IS 'Fare riferimento a quanto documentato per WS_QUERY_INTERVAL (2021.04.30)';

COMMENT ON COLUMN FAI_FARMADATI_WS_CONFIG.WS_PASSWORD_ENCR IS 'v. quanto documentato per il campo WS_PASSWORD (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS_CONFIG.DEBUG_MODE IS 'DEVE normalmente essere impostata ad ''N''. La modalità debug è concepita per ridurre il tempo impegnato dalle sessioni di test, ad esempio caricando solo un ridotto numero di record per tabella (anziché centinaia di migliaia o milioni). La modalità debug va attivata impostando il campo ad ''S'' e TASSATIVAMENTE NON VA UTILIZZATA IN PRODUZIONE (2021.04.29)';

COMMENT ON COLUMN FAI_FARMADATI_WS_CONFIG.RESUME_PREV_SESSION_MODE IS 'DEVE normalmente essere impostata ad ''N''. Se impostato ad ''S'', tutti i dati già disponibili in banca dati, non saranno nuovamente scaricati. Più precisamente, non saranno invocati i WebService per i quali sono già state registrate le risposte in FARMADATI_WS e non saranno ripopolate le tabelle dinamicamente generate, per le quali, nella FARMADATI_DDL, esiste il corrispondente record ed il suo campo ALL_DATA_LOADED risulta essere NOT NULL (2021.04.30)';

COMMENT ON TABLE FAI_FARMADATI_WS_STATUS IS 'Mantiene lo stato di acquisizione dei dati dal Servivizio Farmadati  (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS_STATUS.LAST_RUN_START_TS IS 'data/ora di avvio dell''ultima operazione periodica di import da FarmaDati. NULL se il processo non è mai stato eseguito (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS_STATUS.LAST_RUN_END_TS IS 'data/ora di completamento dell''ultima operazione periodica di import da FarmaDati. NULL se il processo non è mai stato eseguito o se l''import è in corso al momento della lettura del campo  (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS_STATUS.LAST_RUN_DONE IS 'Se ''S'', l''ultimo import dei dati si è concluso con successo, altrimetni ''N'' (2021.04.27)';

COMMENT ON COLUMN FAI_FARMADATI_WS_STATUS.LAST_RUN_DESCR IS 'Fornisce informazioni circa l''ultima esecuzione (2021.04.28)';
