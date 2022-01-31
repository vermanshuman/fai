package fai.imp.farmaclick.task;

import fai.common.csv.CsvException;
import fai.common.http.Http;
import fai.common.util.Filesystem;
import fai.common.util.Timeout;
import fai.imp.base.bean.ProcessedOrderBean;
import fai.imp.base.bean.ProductBean;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.base.task.AbstractDataCollector;
import fai.imp.farmaclick.config.PropertiesLoader;
import fai.imp.farmaclick.csv.*;
import fai.imp.farmaclick.db.SqlQueries;
import fai.imp.farmaclick.models.Fornitore;
import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloOutputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.DettaglioArticoliOutputBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean;
import fai.imp.farmaclick.ws.FarmaclickWS;
import fai.imp.farmaclick.ws.bean.ArticoloInputBean;
import fai.imp.farmaclick.ws.bean.DownloadListinoOutputBean;
import fai.imp.farmaclick.ws.bean.OrdineInputBean;
import fai.imp.farmaclick.ws.bean.OrdineOutputBean;
import it.swdes.decrypt.Decryptor;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class FarmaclickDataCollector extends AbstractDataCollector {


	static Logger logger = Logger.getLogger(FarmaclickDataCollector.class);

	static final boolean DEBUG_DO_NOT_CONFIRM_DOWNLOAD = false; // perché sennò otterrò sempre zero differenze se scarico le variazioni 

	private Http http = null;
	private FarmaclickWS ws = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmm");

	public FarmaclickDataCollector(FaiImportConfig config, Connection conn) {
		super(config, conn);
	}


	/**
	 * Poiché gli url restituiti dal WebService contengono 
	 * caratteri speciali "\n", questo metodo li rimuove
	 * 
	 * 
	 * @param url
	 * @return
	 */
	private String cleanUrl(String url) {
		url = url.replace("\n", "");
		url = url.replace("\r", "");
		url = url.replace("\t", "");
		url = url.trim();
		return url;
	}

	protected String doCollectData_executeAll() throws Exception {
		//
		if (noSessionForToday) return null;
		//
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");

		loginWebService();

		Set<String> codiciFornitoreToSkip = new HashSet<String>();
		if (resumePrevSession == true) {
			codiciFornitoreToSkip = SqlQueries.getAllFornitoreCodiceWithDataAlreadyStored(conn);
		}
		List<FornitoreBean> fornitoreBeanList = ws.getFornitoreBeanList();
		for (FornitoreBean fornitoreBean : fornitoreBeanList) {
			String codiceFornitore = fornitoreBean.getCodice();
			if (codiciFornitoreToSkip.contains(codiceFornitore)) {
				logger.info(LOG_PREFIX + " "+codiceFornitore+" ignorato (dati già scaricati)");
			}
			else {
				logger.info(LOG_PREFIX + " "+codiceFornitore+" recupero dettagli ...");
				DownloadListinoOutputBean dlob = ws.callDownloadListino(fornitoreBean, onlyVariationQueryType, config.isServiceQureyZippedContent());
				if (dlob != null) {
					SqlQueries.storeFornitore(
							fornitoreBean, false, cleanUrl(dlob.getUrlDownload()),
								cleanUrl(dlob.getUrlConfermaDownload()),config.getOid(), conn);
				}
				else {
					logger.warn(LOG_PREFIX + " "+codiceFornitore+"; listino non trovato");
				}
			}
		}
		conn.commit();
		//
		// --- Fornitori: recupero dei fornitori per i quali va scaricato il listino ---
		//
		List<Fornitore> fornitori = SqlQueries.findAllFornitoreFromConfig(conn,config.getOid());
		for (Fornitore fornitore : fornitori) {
			try {
				logger.info(LOG_PREFIX + " "+fornitore.getCodice()+" download e registrazione dati in banca dati ...");
				List<CsvException> errors = doCollect_fornitore(fornitore);
				if (errors.size() == 0)
					logger.info(LOG_PREFIX + " "+fornitore.getCodice()+" download e registrazione dati in banca dati completata");
				else
					logger.warn(LOG_PREFIX + " "+fornitore.getCodice()+" download e registrazione dati in banca dati completata con errori");
			}
			catch (Throwable th) {
				String msg = LOG_PREFIX + " "+fornitore.getCodice()+" download e registrazione dati in banca dati non riuscita causa eccezione "+th.getClass().getName()+" «"+th.getMessage()+"»; l'attività proseguirà col prossimo fornitore da processare";
				logger.error(msg, th);
				conn.rollback();
			} 
		}
		return null;
	}

	private List<CsvException> doCollect_fornitore(Fornitore fornitore) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ", fornitore "+fornitore.getCodice()+": ";
		List<CsvException> errors = new ArrayList<CsvException>();
		//
		// --- il CSV è già stato scaricato e confermato? ---
		//
		boolean downloadCsv = false;
		if (fornitore.getLastCsvDownloadAtTs() == null && fornitore.getLastCsvConfirmedAtTs() == null) {
			logger.info(LOG_PREFIX+"il CSV deve essere scaricato/scaricato di nuovo");
			downloadCsv = true;
		}
		else if (fornitore.getLastCsvDownloadAtTs() != null && fornitore.getLastCsvConfirmedAtTs() == null) {
			logger.warn(LOG_PREFIX+"download del CSV iniziato, ma non confermato alla controparte; è una condizione anomala dato che i due timestamp dovrebbero esser \"committati\" insieme: il CSV deve essere scaricato/scaricato di nuovo");
			downloadCsv = true;
		}
		else  if (fornitore.getLastCsvDownloadAtTs() == null && fornitore.getLastCsvConfirmedAtTs() != null) {
			logger.error(LOG_PREFIX+"download del CSV non iniziato, ma confermato alla controparte (!?); è una condizione fortemente anomala: il CSV deve essere scaricato/scaricato di nuovo");
			downloadCsv = true;
		}
		else if (fornitore.getLastCsvDownloadAtTs() != null && fornitore.getLastCsvConfirmedAtTs() != null) {
			logger.warn(LOG_PREFIX+"download del CSV già inziiato e già confermato alla controparte");
			downloadCsv = false;
		}
		//
		if (downloadCsv) {
			String urlDownload = "".equals(fornitore.getLastCsvDownloadUrl().trim()) ? null : fornitore.getLastCsvDownloadUrl();
			String urConfirmation = "".equals(fornitore.getLastCsvConfirmUrl().trim()) ? null : fornitore.getLastCsvConfirmUrl();
			Calendar downloadAt = null;
			Calendar downloadConfirmedAt = null;
			logger.info(LOG_PREFIX+"url di download "+urlDownload);
			logger.info(LOG_PREFIX+"url di conferma"+urConfirmation);
			if (urlDownload == null || urConfirmation == null) throw new IllegalStateException(LOG_PREFIX+"inammissibile, almeno uno dei due url (donwload e/o conferma) è non disponibile");
			//
			//  --- download del CSV come InputStream --- 
			//
			logger.info(LOG_PREFIX + "recupero dell' "+InputStream.class.getName()+" dall'url di download ...");
			InputStream is = http.getInputStreamGET(urlDownload);
			if (http.getLastHttpResult() != 200) throw new IllegalStateException(LOG_PREFIX+"download non riuscito, errore HTTP "+http.getLastHttpResult());
			//
			//  --- registrazione del CSV/InputStream in banca dati--- 
			//
			downloadAt = Calendar.getInstance();
			SqlQueries.setFornitoreCsvDownloadTimestamps(fornitore.getCodice(), downloadAt, downloadConfirmedAt, conn);
			logger.info(LOG_PREFIX + "registrazione dell' "+InputStream.class.getName()+" in banca dati ...");
			SqlQueries.setFornitoreCsvData(fornitore.getCodice(), is, config.isServiceQureyZippedContent(), config.getOid(), conn);
			fornitore.setLastCsvZipped(config.isServiceQureyZippedContent());
			//
			//  --- notifica completamento alla controparte --- 
			//
			if (DEBUG_DO_NOT_CONFIRM_DOWNLOAD) {
				logger.warn("**** ATTENZIONE! NESSUNA CONFERMA ALLA CONTROPARTE!! QUESTO MESSAGGIO NON DEVE COMPARIRE IN AMBIENTE DI PRODUZIONE!!!  ****");
			}
			else {
				logger.info(LOG_PREFIX + "notifica acquisizione dell'"+InputStream.class.getName()+" completata all'apposito url di conferma ...");
				is = http.getInputStreamGET(urConfirmation);
				if (is != null) is.close(); // lo chiudo subito, perché non mi serve
				if (http.getLastHttpResult() != 200) {
					//throw new IllegalStateException(LOG_PREFIX+"download non riuscito, errore HTTP "+http.getLastHttpResult());
					logger.warn(LOG_PREFIX+"conferma downaload listino non riuscita, errore HTTP "+http.getLastHttpResult());
				}
			}
			downloadConfirmedAt = Calendar.getInstance();
			SqlQueries.setFornitoreCsvDownloadTimestamps(fornitore.getCodice(), downloadAt, downloadConfirmedAt, conn);
			//
			//  --- consolidamento (commit) di quanto acquisito --- 
			//
			conn.commit();
		}
		//
		// --- recupero dell'InputStream del CSV dalla banca dati e registrazione dei record ---
		//
		logger.info(LOG_PREFIX + "preparsing del csv alla ricerca di errori ...");
		errors = doCollect_fornitore_csv2database(fornitore, true);
		if (errors.size() > 0) {
			logger.warn(LOG_PREFIX + errors.size()+" errori di parsing del CSV riscontrati per questo fornitore");
			//return errors;
		}
		//
		doCollect_fornitore_csv2database(fornitore, false);
		//
		return errors;
	}


	private List<CsvException> doCollect_fornitore_csv2database(Fornitore fornitore, boolean preparseOnly) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + "/"+(preparseOnly ? "solo parsing" : "registrazione dei record in banca dati")+" "+fornitore.getCodice()+": ";
		logger.info(LOG_PREFIX + "...");
		List<CsvException> errors = new ArrayList<CsvException>();
		InputStream zippedInputStream = null;
		InputStream unzippedInputStream = null;
		//
		try {
			if (fornitore.isLastCsvZipped()) {
				zippedInputStream = SqlQueries.getFornitoreCsvData(fornitore.getCodice(), conn);
				unzippedInputStream = Filesystem.getFirstEntryAsInputStream(zippedInputStream);
			}
			else {
				unzippedInputStream = SqlQueries.getFornitoreCsvData(fornitore.getCodice(), conn);
			}
			//
			// --- parsing del csv ---
			//
			BufferedReader reader = new BufferedReader(new InputStreamReader(unzippedInputStream));
			String line = null;
			int lineNumber = 0;
			Timeout timeout = new Timeout(5000, false);
			int recordsCount = 0;
			while ((line = reader.readLine()) != null) {
				boolean lineParserError = false;
				//
				// --- parsing del record ---
				//
				lineNumber++;
				CsvRecordFarmaclick record = null;
				try {
					record = CsvRecordFarmaclickFactory.asCsvRecordFarmaclick(lineNumber, line);
				}
				catch (CsvException e) {
					if (preparseOnly) {
						errors.add(e);
						logger.error(LOG_PREFIX+e.getMessage());
					}
					else {
						//throw e;
						logger.error(LOG_PREFIX+e.getMessage());
						lineParserError = true;
					}
				}
				//
				// --- se in modalità parsing/verifia, verifica di ogni singolo campo ---
				//
				if (preparseOnly) {
					List<CsvException> methErrors = CsvRecordFieldsTester.testAllMethods(record);
					errors.addAll(methErrors);
				}
				//
				// --- altrimenti, se non in modalità parsing/verifica, registrazione in banca dati ---
				//
				else if (!lineParserError){
					Long oidFornitore = fornitore.getOid();
					if (record instanceof CsvRecordFarmaclickA) {
						CsvRecordFarmaclickA rec = (CsvRecordFarmaclickA)record;
						SqlQueries.store(rec, oidFornitore, conn);
					}
					else if (record instanceof CsvRecordFarmaclickC) {
						CsvRecordFarmaclickC rec = (CsvRecordFarmaclickC)record;
						SqlQueries.store(rec, oidFornitore, conn);
					}
					else if (record instanceof CsvRecordFarmaclickD) {
						CsvRecordFarmaclickD rec = (CsvRecordFarmaclickD)record;
						SqlQueries.insert(rec, oidFornitore, conn);
					}
					else if (record instanceof CsvRecordFarmaclickL) {
						CsvRecordFarmaclickL rec = (CsvRecordFarmaclickL)record;
						SqlQueries.store(rec, oidFornitore, conn);
					}
					else if (record instanceof CsvRecordFarmaclickR) {
						CsvRecordFarmaclickR rec = (CsvRecordFarmaclickR)record;
						SqlQueries.store(rec, oidFornitore, conn);
					}
					else if (record instanceof CsvRecordFarmaclickV) {
						CsvRecordFarmaclickV rec = (CsvRecordFarmaclickV)record;
						SqlQueries.store(rec, oidFornitore, conn);
					}
					else if (record instanceof CsvRecordFarmaclickZ) {
						CsvRecordFarmaclickZ rec = (CsvRecordFarmaclickZ)record;
						SqlQueries.store(rec, oidFornitore, conn);
					}
					else {
						logger.error(LOG_PREFIX + "tipo di record non gestito " + record.getClass().getName());
						//throw new IllegalStateException(LOG_PREFIX + "tipo di record non gestito " + record.getClass().getName());
					}
				}
				recordsCount++;
				if (timeout.isExpired()) logger.info(LOG_PREFIX+recordsCount+" record "+(preparseOnly ? "verificati" : "inseriti oppure aggiornati")+" ...");
			}
			logger.info(LOG_PREFIX+recordsCount+" record complessivamente "+(preparseOnly ? "verificati" : "inseriti oppure aggiornati"));
			//
			// --- da evidenza nel log dei problemi riscontrati ---
			//
			for (CsvException csvException : errors) {
				logger.error(LOG_PREFIX+csvException.getMessage());
			}
			//
			// --- Se non in modalità simulazione, commit o rollback ---
			//
			if (preparseOnly == false) {
				if (errors.size() == 0) {
					SqlQueries.setFornitoreAllDataStoredTs(fornitore.getOid(), conn);
					conn.commit();
				}
				else {
					conn.rollback();
				}
			}
			return errors;
		}
		catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		finally {
			if (unzippedInputStream != null) unzippedInputStream.close();
			if (zippedInputStream != null) zippedInputStream.close();
		}
	}


	@Override
	protected void doCollectData_prepare_specificSetup() throws Exception {
		//
		// --- creazione istanza WebService ---
		//
		ws = new FarmaclickWS();
		ws.setEndpointAddress(config.getServiceQueryUrl());
		ws.setLogRequestAsXml(true);
		ws.setLogReponsesAsXml(true);
		ws.setServiceAPILevel(config.getServiceApiLevel());
		//
		// --- preparazione dell'istanza di Http per il download dei listino ---
		//
		http = new Http();


	}


	@Override
	protected void doCollectData_prepare_resumePrevSession() throws Exception {
		if (onlyVariationQueryType == false) {    
			SqlQueries.deleteAlLRecordsFromNoLongerExiststingFornitore(conn);
		}
		else {
			SqlQueries.setAllTablesRecordsActionFromNoLongerExistingFornitore("C", conn);
		}
	}


	@Override
	protected void doCollectData_prepare_startNewSession() throws Exception {
		SqlQueries.setAllFornitoreNoLongerExists(conn);    
		if (onlyVariationQueryType == false) {
			SqlQueries.deleteAlLRecordsFromNoLongerExiststingFornitore(conn);
		}
		else {
			SqlQueries.setAllTablesRecordsActionFromNoLongerExistingFornitore("C", conn);
		}
	}

	@Override
	protected List<ProductBean> doCollectData_getAvailability(List<ProductBean> products) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		List<ProductBean> productAvailibilityBeans = new ArrayList<>();
		if(products != null && !products.isEmpty()) {
			loginWebService();
			List<FornitoreBean> fornitoreBeanList = ws.getFornitoreBeanList();
			for (FornitoreBean fornitoreBean : fornitoreBeanList) {
				String codiceFornitore = fornitoreBean.getCodice();
				logger.info(LOG_PREFIX + " "+codiceFornitore+" recupero dettagli ...");
				List<fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloInputBean> articles = products
						.stream()
						.filter(productCode -> productCode != null)
						.map(productCode -> new fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloInputBean(1, -1, -1, productCode.getProductCode(), true, productCode.getQuantity()))
						.collect(Collectors.toList());
				DettaglioArticoliOutputBean daob = ws.callDisponibilita(fornitoreBean, true, false, false, articles);
				if(daob != null && daob.getEsitoServizio() == 0 && daob.getArrayArticoli() != null 
						&& daob.getArrayArticoli().length > 0) {
					for(int a = 0; a < daob.getArrayArticoli().length; a++) {
						ArticoloOutputBean articoloOutputBean = daob.getArrayArticoli()[a];
						ProductBean productBean = new ProductBean();
						if (articoloOutputBean.getCodiceProdottoSostituito()!=null) {
							productBean.setProductCode(articoloOutputBean.getCodiceProdottoSostituito());
						}else {
							productBean.setProductCode(articoloOutputBean.getCodiceProdotto());
						}
						productBean.setAvailibility(articoloOutputBean.getQuantitaConsegnata() > 0 ? 
								Boolean.TRUE : Boolean.FALSE);
						productBean.setQuantity(articoloOutputBean.getQuantitaConsegnata());
						productAvailibilityBeans.add(productBean);
					}
				}
			}
		}
		return productAvailibilityBeans;
	}

	private void loginWebService() {
		String login = config.getServiceLogin();
		if (login != null && login.trim().equals("")) login = null;
		String pass = config.getServicePass();
		Boolean isPasswordEncrypted = config.getServicePassEncr();
		if (pass != null) {
			if (pass.trim().equals("")) { 
				pass = null;
			}
			else {
				if (isPasswordEncrypted != null && isPasswordEncrypted) {
					logger.info("decrittazione password del WebService ...");
					pass = Decryptor.decrypt(pass);
				}
			}
		}
		//
		// --- Fornitori: download e registrazione in banca dati  ---
		//
		ws.login(login, pass, "SERVER", "");
	}



	@Override
	protected void do_prepare_specificSetup() throws Exception {
		doCollectData_prepare_specificSetup();
	}


	@Override
	protected void doCollectData_getDDTList(Date dataInzio, Date dataFine) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected List<ProcessedOrderBean> do_OrderProducts(List<ProductBean> productOrderRequests) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		List<ProcessedOrderBean> processedOrders = null;
		if(productOrderRequests != null && !productOrderRequests.isEmpty()) {
			loginWebService();
			List<FornitoreBean> fornitoreBeanList = ws.getFornitoreBeanList();
			for (FornitoreBean fornitoreBean : fornitoreBeanList) {
				String codiceFornitore = fornitoreBean.getCodice();
				logger.info(LOG_PREFIX + " "+codiceFornitore+" recupero dettagli ...");
				String serviceApiLevel = config.getServiceApiLevel();
				// && serviceApiLevel.equalsIgnoreCase("2005.001")
				List<ArticoloInputBean> articles = null;
				if(serviceApiLevel != null){
					if(serviceApiLevel.equalsIgnoreCase("2005.001")){
						articles = productOrderRequests
								.stream()
								.filter(por -> por != null)
								//.map(por -> new ArticoloInputBean(false, por.getProductCode(), "", false, por.getQuantity(), ""))
								.map(por -> new ArticoloInputBean(1, -1, -1, por.getProductCode(), false, por.getQuantity()))
								.collect(Collectors.toList());
					}else if(serviceApiLevel.equalsIgnoreCase("2010.001")){
						articles = productOrderRequests
								.stream()
								.filter(por -> por != null)
								.map(por -> new ArticoloInputBean(false, por.getProductCode(), "", false, por.getQuantity(), ""))
								.collect(Collectors.toList());
					}
				}
				if(articles != null && !articles.isEmpty()){

					OrdineInputBean oib = new OrdineInputBean();
					oib.setCodiceFornitore(codiceFornitore);
					oib.setDescrizioneArticoli(true);
					oib.setDescrizioneMotivazioneMancanza(false);
					oib.setIndicazioneDepositoAllestimento(false);
					oib.setIndicazioneDatiConsegna(false);
					oib.setRiferimentoOrdineFarmacia(dateFormat.format(new java.util.Date()));
					oib.setArticoli(articles);
					OrdineOutputBean oob = ws.callOrdine(oib);
					if(oob != null && oob.getOrdineOutputBean2005001() != null &&
							oob.getOrdineOutputBean2005001().getArrayArticoli() != null &&
							oob.getOrdineOutputBean2005001().getArrayArticoli().length > 0){
						processedOrders = Arrays.stream(oob.getOrdineOutputBean2005001().getArrayArticoli())
								.map(article -> new ProcessedOrderBean(article.getCodiceProdotto(), article.getQuantitaRichiesta(),
										article.getQuantitaMancante(),
										article.getQuantitaRichiesta() == article.getQuantitaMancante(),
										article.getCodiceMancanza(), article.getDescrizioneMancanza(),
										oob.getNumeroOrdineFornitore()))
								.collect(Collectors.toList());
					}else if(oob != null && oob.getOrdineOutputBean2010001() != null &&
							oob.getOrdineOutputBean2010001().getArrayArticoli() != null &&
							oob.getOrdineOutputBean2010001().getArrayArticoli().length > 0){
						processedOrders = Arrays.stream(oob.getOrdineOutputBean2010001().getArrayArticoli())
								.map(article -> new ProcessedOrderBean(article.getCodiceProdotto(), article.getQuantitaRichiesta(),
										article.getQuantitaMancante(),
										article.getQuantitaRichiesta() == article.getQuantitaMancante(),
										article.getCodiceMancanza(), article.getDescrizioneMancanza(),
										oob.getNumeroOrdineFornitore()))
								.collect(Collectors.toList());
					}
				}
			}
		}
		return processedOrders;
	}
}
