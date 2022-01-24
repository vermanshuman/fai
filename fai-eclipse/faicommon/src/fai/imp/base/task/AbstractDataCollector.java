package fai.imp.base.task;

import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import fai.imp.base.bean.ProcessedOrderBean;
import fai.imp.base.bean.ProductAvailibilityBean;
import fai.imp.base.bean.ProductBean;
import fai.imp.base.db.SqlQueries;
import fai.imp.base.models.FaiImportConfig;


public abstract class AbstractDataCollector {


	static Logger logger = Logger.getLogger(AbstractDataCollector.class);

	protected Connection conn = null;
	protected FaiImportConfig config = null;
	protected boolean noSessionForToday = false;
	protected boolean onlyVariationQueryType = false;
	protected boolean resumePrevSession = false;
	protected String sessionQueryType = null;
	private List<String> productCodes;
	private List<ProductBean> productOrderRequests;

	public AbstractDataCollector(FaiImportConfig config, Connection conn) {
		super();
		this.config = config;
		this.conn = conn;
	}

	protected String getThisSessionQueryType() {
		Calendar now = Calendar.getInstance();
		int currDayOfWeek = now.get(Calendar.DAY_OF_WEEK);
		//
		int [] weekday = new int [] { Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY };
		for (int i = 0; i < weekday.length; i++) {
			if (currDayOfWeek == weekday[i]) {
				String type = config.getServiceQuerySMTWTFS().substring(i, i+1);
				logger.info("identificativo tipologia di interrogazione estratto dal pattern settimanale "+config.getServiceQuerySMTWTFS()+": "+type);
				return type;
			}
		}
		throw new IllegalStateException("configurazione pattern settimanale errata: "+config.getServiceQuerySMTWTFS());
	}


	protected void reloadConfig() throws Exception {
		config = SqlQueries.getFaiImportConfig(config.getOid(), conn);
	}

	public String doCollectData() throws Exception {
		String error = null;
		try {
			doCollectData_prepare();
			if (noSessionForToday == false) {
				error = doCollectData_executeAll();
			}
		}
		catch (Throwable th) {
			error = "Eccezione " + th.getClass().getName() + ", �" + th.getMessage() + "�";
			logger.error(error, th);
			throw new Exception(error, th);
		}
		finally {
			if (noSessionForToday == false) {
				SqlQueries.updateStatusSessionCompleted(config.getOid(), sessionQueryType, error == null || error.length() == 0, error, conn);
				conn.commit();
				reloadConfig();
				logger.info("sessione completata"+(error == null ? "" : " con errori ("+error+")"));
			}
		}
		//
		return error;
	}


	protected abstract String doCollectData_executeAll() throws Exception;


	protected void doCollectData_prepare() throws Exception {
		if (config == null) throw new IllegalStateException(FaiImportConfig.class.getName()+" non assegnata");
		//
		// --- tipologia di sessione ---
		//
		sessionQueryType = getThisSessionQueryType();
		onlyVariationQueryType = false;
		noSessionForToday = false;
		if ("-".equals(sessionQueryType)) {
			logger.info("nessuna sessione di acquisizione prevista per oggi ("+sessionQueryType+")");
			noSessionForToday = true;
			return;
		}
		else if ("C".equals(sessionQueryType) || "A".equals(sessionQueryType) || "R".equals(sessionQueryType)) {
			onlyVariationQueryType = false;
		}
		else if ("V".equals(sessionQueryType)) {
			onlyVariationQueryType = true;
		}
		else {
			throw new IllegalStateException("tipologia di interrogazione non gestita: "+sessionQueryType);
		}
		logger.info("dati oggetto dell'acquisizione: "+(onlyVariationQueryType ? "solo variazioni" : "tutti (ri-acquisizione da zero)"));
		//
		// --- continuare dalla sessione precedente? ---
		//
		String prevSessMode = config.getServiceResumePrevSession();
		resumePrevSession = false;
		if ("S".equals(prevSessMode)) {
			resumePrevSession = true;
		} 
		else if ("A".equals(prevSessMode)) {
			if (sessionQueryType.equals(config.getLastRunType()) && config.isLastRunDone() == false) {
				resumePrevSession = true;
			}
			else {
				resumePrevSession = false;
			}
		}
		else if ("N".equals(prevSessMode)) {
			// nothing todo
		}
		else {
			throw new IllegalStateException("modalit� di ripristino della sessione precedente non gestita: "+config.getServiceResumePrevSession());
		}
		//
		// --- completamento inzializzazione con inizializzazioni ---
		//
		do_prepare_specificSetup();
		doCollectData_prepare_specificSetup();
		//
		// --- avvio nuova sessione o ripristino precedente ---
		//
		logger.info("sessione di acquisizione: "+(resumePrevSession ? "ripristino sessione precedente" : "nuova sessione")+" ("+prevSessMode+")");
		if (resumePrevSession) {
			logger.info("recupero sessione precedente ...");
			doCollectData_prepare_resumePrevSession();
		}
		else { 
			logger.info("avvio nuova sessione ...");
			doCollectData_prepare_startNewSession();
			SqlQueries.updateStatusNewSession(config.getOid(), sessionQueryType, conn);
		}
		conn.commit();
		reloadConfig();
	}


	/**
	 * Es. (Farmaclick):
	 *
	 * <pre>
	 * protected void doCollectData_prepare_specificSetup() throws Exception { 
	 *     //
	 *     // --- creazione istanza WebService ---
	 *     //
	 *     ws = new FarmaclickWS();
	 *     ws.setEndpointAddress(config.getWsUrl());
	 *     ws.setLogRequestAsXml(true);
	 *     ws.setLogReponsesAsXml(true);
	 *     //
	 *     // --- preparazione dell'istanza di Http per il download dei listino ---
	 *     //
	 *     http = new Http();
	 * }
	 * </pre>
	 *
	 */

	protected abstract void doCollectData_prepare_specificSetup() throws Exception;


	/**  
	 * Es. (Farmaclick):
	 *
	 * <pre>
	 * protected void doCollectData_prepare_resumePrevSession() throws Exception {}
	 *     if (onlyVariationQueryType == false) {    
	 *       SqlQueries.deleteAlLRecordsFromNoLongerExiststingFornitore(conn);
	 *     }
	 *     else {
	 *       SqlQueries.setAllTablesRecordsActionFromNoLongerExistingFornitore("C", conn);
	 *     }
	 * }
	 * </pre>
	 *
	 */
	protected abstract void doCollectData_prepare_resumePrevSession() throws Exception;


	/**  
	 * Es. (Farmaclick):
	 *
	 * <pre>
	 * protected void doCollectData_prepare_startNewSession() throws Exception {
	 *     SqlQueries.setAllFornitoreNoLongerExists(conn);    
	 *     if (onlyVariationQueryType == false) {
	 *       SqlQueries.deleteAlLRecordsFromNoLongerExiststingFornitore(conn);
	 *     }
	 *     else {
	 *       SqlQueries.setAllTablesRecordsActionFromNoLongerExistingFornitore("C", conn);
	 *     }
	 * }
	 * </pre>
	 *
	 */
	protected abstract void doCollectData_prepare_startNewSession() throws Exception;

	public List<ProductBean> doGetAvailiblityData(List<ProductBean> products) throws Exception {
		try {
			reloadConfig();
			setProductCodes(
					products.stream()
					.map(p -> p.getProductCode() )
					.collect(Collectors.toList()));
			do_prepare_specificSetup();
			return doCollectData_getAvailability(products);
		}
		catch (Throwable th) {
			String error = "Eccezione " + th.getClass().getName() + ", " + th.getMessage() + "";
			logger.error(error, th);
			throw new Exception(error, th);
		}
	}


	public List<ProcessedOrderBean> doOrderProducts(List<ProductBean> productOrderRequests) throws Exception {
		try {
			reloadConfig();
			setProductOrderRequests(productOrderRequests);
			do_prepare_specificSetup();
			return do_OrderProducts(productOrderRequests);
		}
		catch (Throwable th) {
			String error = "Eccezione " + th.getClass().getName() + ", " + th.getMessage() + "";
			logger.error(error, th);
			throw new Exception(error, th);
		}
	}

	protected abstract List<ProductBean> doCollectData_getAvailability(List<ProductBean> productCodes) throws Exception;

	protected abstract void do_prepare_specificSetup() throws Exception;

	protected abstract void doCollectData_getDDTList(Date dataInzio, Date dataFine) throws Exception;

	protected abstract List<ProcessedOrderBean> do_OrderProducts(List<ProductBean> productOrderRequests) throws Exception;

	public List<String> getProductCodes() {
		return productCodes;
	}

	public void setProductCodes(List<String> productCodes) {
		this.productCodes = productCodes;
	}

	public List<ProductBean> getProductOrderRequests() {
		return productOrderRequests;
	}

	public void setProductOrderRequests(List<ProductBean> productOrderRequests) {
		this.productOrderRequests = productOrderRequests;
	}
}
