package fai.imp.farmaclick.ws;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import fai.common.util.CalendarWrapper;
import fai.common.util.ExceptionsTool;
import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.ArticoloInputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.DettaglioArticoliInputBean;
import fai.imp.farmaclick.soap.api_2005_001.FCKDisponibilita.DettaglioArticoliOutputBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.Farmaclick2010001FCKLoginSoapBindingStub;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.Farmaclick2010001ServiceLocator;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.LoginInputBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.LoginOutputBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.ServiziBean;
import fai.imp.farmaclick.ws.bean.DownloadListinoOutputBean;
import fai.imp.farmaclick.ws.bean.OrdineInputBean;
import fai.imp.farmaclick.ws.bean.OrdineOutputBean;


/**
 * Fa da wrapper e agevola l'utilizzo degli oggetti AXIS
 * 
 * @author Gianni Radice
 *
 */
public class FarmaclickWS extends AbstractFarmaclickWS {


	static Logger logger = Logger.getLogger(FarmaclickWS.class);

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss/SSS");

	private FarmaclickWS_2005_001 farmaclickWS_2005_001 = new FarmaclickWS_2005_001();
	private FarmaclickWS_2008_001 farmaclickWS_2008_001 = new FarmaclickWS_2008_001();
	private FarmaclickWS_2010_001 farmaclickWS_2010_001 = new FarmaclickWS_2010_001();
	private FarmaclickWS_2010_002 farmaclickWS_2010_002 = new FarmaclickWS_2010_002();


	private LoginInputBean loginInputBeanSuccess = null;
	private LoginOutputBean loginOutputBeanSuccess = null;

	private Hashtable<String, FornitoreBean> fornitoreByCodice = new Hashtable<String, FornitoreBean>();


	public void setLogRequestAsXml(boolean logRequestAsXml) {
		super.setLogRequestAsXml(logRequestAsXml); 
		farmaclickWS_2005_001.setLogRequestAsXml(logRequestAsXml); 
		farmaclickWS_2008_001.setLogRequestAsXml(logRequestAsXml); 
		farmaclickWS_2010_001.setLogRequestAsXml(logRequestAsXml); 
		farmaclickWS_2010_002.setLogRequestAsXml(logRequestAsXml); 
	}


	public void setLogReponsesAsXml(boolean logReponsesAsXml) {
		super.setLogReponsesAsXml(logReponsesAsXml); 
		farmaclickWS_2005_001.setLogReponsesAsXml(logReponsesAsXml); 
		farmaclickWS_2008_001.setLogReponsesAsXml(logReponsesAsXml); 
		farmaclickWS_2010_001.setLogReponsesAsXml(logReponsesAsXml); 
		farmaclickWS_2010_002.setLogReponsesAsXml(logReponsesAsXml); 
	}


	/**
	 * restituisce <tt>true</tt> se la login ha successo.
	 * 
	 * @param userName
	 * @param password
	 * @param nomeTerminale
	 * @param passwordSH
	 * @return
	 */
	public boolean login(String userName, String password, String nomeTerminale, String passwordSH) {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + ": ";
		logger.info(LOG_PREFIX + "...");
		loginInputBeanSuccess = null;
		loginOutputBeanSuccess = null;
		Farmaclick2010001FCKLoginSoapBindingStub binding = null;
		fornitoreByCodice.clear();
		try {
			//
			// --- binding ---
			//
			Farmaclick2010001ServiceLocator locator = new Farmaclick2010001ServiceLocator();
			locator.setFarmaclick2010001FCKLoginEndpointAddress(endpointAddress);
			logger.info(LOG_PREFIX + "login endpoint address: "+locator.getFarmaclick2010001FCKLoginAddress());
			binding = (Farmaclick2010001FCKLoginSoapBindingStub)locator.getFarmaclick2010001FCKLogin(new java.net.URL(locator.getFarmaclick2010001FCKLoginAddress()));
			//
			// --- call ---
			//
			LoginInputBean inBean = new LoginInputBean(nomeTerminale, password, passwordSH, userName);
			logRequestAsXml(LOG_PREFIX, inBean);
			Calendar now1 = Calendar.getInstance();
			LoginOutputBean outBean = binding.FCKLogin(inBean);
			Calendar now2 = Calendar.getInstance();
			logEsitoServizio(LOG_PREFIX, outBean);
			logResponseAsXml(LOG_PREFIX, outBean);
			//
			// --- valutazione esito ---
			//
			if (outBean != null && outBean.getEsitoServizio() == 0) { 
				loginInputBeanSuccess = inBean;
				loginOutputBeanSuccess = outBean;
				FornitoreBean[] fornitore = loginOutputBeanSuccess.getArrayFornitori();
				for (int i = 0; i < fornitore.length; i++) {
					fornitoreByCodice.put(fornitore[i].getCodice(), fornitore[i]);
				}
				Calendar serverTime = loginOutputBeanSuccess.getServerTime();
				if (serverTime == null) {
					logger.error("orario del serverver non disponibil");
				}
				else {
					long deltaNow = now2.getTimeInMillis() - now1.getTimeInMillis();
					Calendar now = (Calendar)now1.clone();
					now.add(Calendar.MILLISECOND, (int)(deltaNow / 2));
					long deltaLocalFarmaclick = now.getTimeInMillis() - now.getTimeInMillis();
					String msg = "scostamento orari ( server locale - server farmaclick ) = ( "+df.format(now.getTime())+" - "+df.format(now.getTime())+ ") = "+deltaLocalFarmaclick+" millisecondi";
					if (deltaLocalFarmaclick <= 5000) {
						logger.info(msg);
					}
					else if (5000 < deltaLocalFarmaclick && deltaLocalFarmaclick <= 30000) {
						logger.warn(msg);
					}
					else if (30000 < deltaLocalFarmaclick) {
						logger.error(msg);
					}
				}

			}
		}
		catch (javax.xml.rpc.ServiceException jre) {
			logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre));
			if(jre.getLinkedCause()!=null) {
				logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause()));
				logger.error(LOG_PREFIX+"operazione non riuscita; trovata linked cause", jre.getLinkedCause());
			}
			throw new IllegalStateException(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre), jre);
		}
		catch (Throwable th) {
			logger.error(ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", th));
		}
		return loginOutputBeanSuccess != null;
	}

	/**
	 * L'elenco risulterà disponibile solo se la login - {@link #login(String, String, String, String)} - è 
	 * stata effettuata con successo
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<FornitoreBean> getFornitoreBeanList() throws Exception {
		List<FornitoreBean> list = new ArrayList<FornitoreBean>();
		if (loginOutputBeanSuccess == null) return list;
		//
		FornitoreBean[] fornitore = loginOutputBeanSuccess.getArrayFornitori();
		for (int i = 0; i < fornitore.length; i++) {
			list.add(fornitore[i]);
		}
		return list;
	}

	public boolean isLoggedIn() {
		return loginInputBeanSuccess != null;
	}


	public DownloadListinoOutputBean callDownloadListino(FornitoreBean fornitoreBean, boolean soloVariazioni, boolean outputCompresso) {
		return callDownloadListino(fornitoreBean.getCodice(), soloVariazioni, outputCompresso);
	}

	public DownloadListinoOutputBean callDownloadListino(String codiceFornitore, boolean soloVariazioni, boolean outputCompresso) {
		return callDownloadListino(codiceFornitore, soloVariazioni, outputCompresso, true);
	}

	private boolean isFermoMacchina(FornitoreBean fornitore) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + " fornitore "+(fornitore == null ? "null" : fornitore.getCodice())+": ";
		logger.info(LOG_PREFIX + "...");
		boolean isFermoMacchina = false;
		Calendar now = Calendar.getInstance();
		Calendar inizioFermoMacchina = fornitore.getInizioFermoMacchina();
		Calendar fineFermoMacchina = fornitore.getFineFermoMacchina();
		if (inizioFermoMacchina != null || fineFermoMacchina != null) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss/SSS");
			// nel caso in cui uno dei due estremi fosse null, lo considero
			// "da/a tempo indeterminato", assegnadogli timestamp 
			// antecedente/successiovo a now
			if (inizioFermoMacchina == null) {
				inizioFermoMacchina = CalendarWrapper.getNowInstance().add(Calendar.DAY_OF_MONTH, -1).getCalendar();
				logger.warn(LOG_PREFIX+"inizio fermo macchina non specificato; assegnato "+df.format(inizioFermoMacchina.getTime()));
			}
			if (fineFermoMacchina == null) {
				fineFermoMacchina = CalendarWrapper.getNowInstance().add(Calendar.DAY_OF_MONTH, 1).getCalendar();
				logger.warn(LOG_PREFIX+"fine fermo macchina non specificato; assegnato "+df.format(fineFermoMacchina.getTime()));
			}
			if (!now.before(inizioFermoMacchina) && !now.after(fineFermoMacchina)) {
				logger.error(LOG_PREFIX+"fermo macchina dalle "+df.format(inizioFermoMacchina.getTime())+" alle "+df.format(fineFermoMacchina.getTime())+", impossibile interrogare il servizio");
				isFermoMacchina = true;
			}
		}
		return isFermoMacchina;

	}

	private DownloadListinoOutputBean callDownloadListino(String codiceFornitore, boolean soloVariazioni, boolean outputCompresso, boolean onSessioneScadutaRecursive) {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + " fornitore "+codiceFornitore+": ";
		logger.info(LOG_PREFIX + "...");
		Calendar now = Calendar.getInstance();
		try {
			//
			// --- recupero del fornitore ---
			//
			FornitoreBean fornitore = fornitoreByCodice.get(codiceFornitore);
			if (fornitore == null)  {
				logger.error(LOG_PREFIX+"nessun fornitore per il codice "+codiceFornitore+"");
				return null;
			}
			logger.info(LOG_PREFIX+"individuato fornitore "+fornitore.getDescrizioneBreve());
			//
			// --- valutazione scadenza ID di Sessione ---
			//
			Calendar scadenzaSessione = fornitore.getScadenzaSessione();
			if (scadenzaSessione == null) {
				logger.warn(LOG_PREFIX+"scadenza della sessione non nota");
			}
			else if (!now.before(scadenzaSessione)) {
				if (onSessioneScadutaRecursive) {
					logger.info(LOG_PREFIX+"sessione scaduta alle "+df.format(scadenzaSessione.getTime())+"; ripetizione della login ...");
					boolean loginSuccess = login(loginInputBeanSuccess.getUserName(), loginInputBeanSuccess.getPassword(), loginInputBeanSuccess.getNomeTerminale(), loginInputBeanSuccess.getPasswordSH());
					if (loginSuccess == false) {
						logger.error(LOG_PREFIX+"login non riuscita, impossibile procedere");
						return null;
					}
					else {
						return callDownloadListino(codiceFornitore, soloVariazioni, outputCompresso, false);
					}
				}
				else {
					logger.error(LOG_PREFIX+"sessione scaduta alle "+df.format(scadenzaSessione.getTime()));
					return null;
				}
			}
			//
			// --- valutazione del fermo macchina ---
			//
			if (isFermoMacchina(fornitore)) return null;
			//
			// --- invocazione servizio ---
			//
			String idSessione = fornitore.getIDSessione();
			String fornitoreLivelloAPI = fornitore.getLivelloAPI();
			logger.info(LOG_PREFIX+"livello API "+fornitoreLivelloAPI+" ...");
			String serviceName = "FCKDownloadListino";
			ServiziBean servizio = (new ServiziBeanChooser().setFornitoreBean(fornitore)).getServiziBean(serviceName);
			if (servizio == null) {
				logger.error(LOG_PREFIX+"servizio "+serviceName+" non disponibile, impossibile procedere con l'interrogazione");
				return null;
			}
			String servizioLivelloAPI = servizio.getLivelloAPI();
			String tipoRichiesta = soloVariazioni ? "V" : "C";
			String formatoOutput = outputCompresso ? "C" : "A";
			Object risposta = null;
			String [] url = new String [] { servizio.getUrl1(), servizio.getUrl2() };
			for (int i = 0; i < url.length; i++) {
				if (url[i] == null) continue;
				String callInfo = LOG_PREFIX+"interrogazione servizio "+serviceName+", livello API "+servizioLivelloAPI+", url "+url[i]; 
				logger.info(callInfo+" ...");
				try {
					if ("2005.001".equals(servizioLivelloAPI)) {
						farmaclickWS_2005_001.setEndpointAddress(url[i]);
						risposta = farmaclickWS_2005_001.callDownloadListino(idSessione, codiceFornitore, tipoRichiesta, formatoOutput);
					}
					else if ("2008.001".equals(servizioLivelloAPI)) {
						farmaclickWS_2008_001.setEndpointAddress(url[i]);
						risposta = farmaclickWS_2008_001.callDownloadListino(idSessione, codiceFornitore, tipoRichiesta, formatoOutput);
					}
					else if ("2010.001".equals(servizioLivelloAPI)) {
						farmaclickWS_2010_001.setEndpointAddress(url[i]);
						risposta = farmaclickWS_2010_001.callDownloadListino(idSessione, codiceFornitore, tipoRichiesta, formatoOutput);
					}
					else if ("2010.002".equals(servizioLivelloAPI)) {
						farmaclickWS_2010_002.setEndpointAddress(url[i]);
						risposta = farmaclickWS_2010_002.callDownloadListino(idSessione, codiceFornitore, tipoRichiesta);
					}
					else {
						logger.error(LOG_PREFIX+"livello API "+servizioLivelloAPI+" non gestito (nuova versione dell'API?)");
					}
				}
				catch (Throwable e) {
					String msg = ExceptionsTool.getExceptionDescription(callInfo+" non riuscita", e);
					logger.error(msg, e);
				}
			}
			//
			// --- gestione esito ---
			//
			DownloadListinoOutputBean downloadListino = UniversalAPIUtil.getDownloadListinoOutputBean(risposta);
			return downloadListino != null && downloadListino.getEsitoServizio() == 0 ? downloadListino : null; 
		}
		catch (Throwable jre) {
			String msg = ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre);
			logger.error(msg);
			throw new IllegalStateException(msg, jre);
		}
	}

	public DettaglioArticoliOutputBean callDisponibilita(FornitoreBean fornitore, boolean descrizioneArticoli, boolean descrizioneMotivazioneMancanza, boolean indicazioneDepositoAllestimento, List<ArticoloInputBean> articoli) {
		return callDisponibilita(fornitore.getCodice(), descrizioneArticoli, descrizioneMotivazioneMancanza, indicazioneDepositoAllestimento, articoli);
	}

	public DettaglioArticoliOutputBean callDisponibilita(String codiceFornitore, boolean descrizioneArticoli, boolean descrizioneMotivazioneMancanza, boolean indicazioneDepositoAllestimento, List<ArticoloInputBean> articoli) {
		return callDisponibilita(codiceFornitore, descrizioneArticoli, descrizioneMotivazioneMancanza, indicazioneDepositoAllestimento, articoli, true);
	}

	private DettaglioArticoliOutputBean callDisponibilita(String codiceFornitore, boolean descrizioneArticoli, boolean descrizioneMotivazioneMancanza, boolean indicazioneDepositoAllestimento, List<ArticoloInputBean> articoli, boolean onSessioneScadutaRecursive) {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + " fornitore "+codiceFornitore+": ";
		logger.info(LOG_PREFIX + "...");
		Calendar now = Calendar.getInstance();
		try {
			//
			// --- recupero del fornitore ---
			//
			FornitoreBean fornitore = fornitoreByCodice.get(codiceFornitore);
			if (fornitore == null)  {
				logger.error(LOG_PREFIX+"nessun fornitore per il codice "+codiceFornitore+"");
				return null;
			}
			logger.info(LOG_PREFIX+"individuato fornitore "+fornitore.getDescrizioneBreve());
			//
			// --- valutazione scadenza ID di Sessione ---
			//
			Calendar scadenzaSessione = fornitore.getScadenzaSessione();
			if (scadenzaSessione == null) {
				logger.warn(LOG_PREFIX+"scadenza della sessione non nota");
			}
			else if (!now.before(scadenzaSessione)) {
				if (onSessioneScadutaRecursive) {
					logger.info(LOG_PREFIX+"sessione scaduta alle "+df.format(scadenzaSessione.getTime())+"; ripetizione della login ...");
					boolean loginSuccess = login(loginInputBeanSuccess.getUserName(), loginInputBeanSuccess.getPassword(), loginInputBeanSuccess.getNomeTerminale(), loginInputBeanSuccess.getPasswordSH());
					if (loginSuccess == false) {
						logger.error(LOG_PREFIX+"login non riuscita, impossibile procedere");
						return null;
					}
					else {
						return callDisponibilita(codiceFornitore, descrizioneArticoli, descrizioneMotivazioneMancanza, indicazioneDepositoAllestimento, articoli, false);
					}
				}
				else {
					logger.error(LOG_PREFIX+"sessione scaduta alle "+df.format(scadenzaSessione.getTime()));
					return null;
				}
			}
			//
			// --- valutazione del fermo macchina ---
			//
			if (isFermoMacchina(fornitore)) return null;
			//
			// --- invocazione servizio ---
			//
			String idSessione = fornitore.getIDSessione();
			String fornitoreLivelloAPI = fornitore.getLivelloAPI();
			logger.info(LOG_PREFIX+"livello API "+fornitoreLivelloAPI+" ...");
			String serviceName = "FCKDisponibilita";
			ServiziBean servizio = (new ServiziBeanChooser().setFornitoreBean(fornitore)).getServiziBean(serviceName);
			if (servizio == null) {
				logger.error(LOG_PREFIX+"servizio "+serviceName+" non disponibile, impossibile procedere con l'interrogazione");
				return null;
			}
			String servizioLivelloAPI = servizio.getLivelloAPI();
			Object risposta = null;
			String [] url = new String [] { servizio.getUrl1(), servizio.getUrl2() };
			for (int i = 0; i < url.length; i++) {
				if (url[i] == null) continue;
				String callInfo = LOG_PREFIX+"interrogazione servizio "+serviceName+", livello API "+servizioLivelloAPI+", url "+url[i]; 
				logger.info(callInfo+" ...");
				try {
					if ("2005.001".equals(servizioLivelloAPI)) {
						farmaclickWS_2005_001.setEndpointAddress(url[i]);
						risposta = farmaclickWS_2005_001.callDisponibilita(idSessione, codiceFornitore, descrizioneArticoli, descrizioneMotivazioneMancanza, indicazioneDepositoAllestimento, articoli);
					}
					else {
						logger.error(LOG_PREFIX+"livello API "+servizioLivelloAPI+" non gestito (nuova versione dell'API?)");
					}
				}
				catch (Throwable e) {
					String msg = ExceptionsTool.getExceptionDescription(callInfo+" non riuscita", e);
					logger.error(msg, e);
				}
			}
			//
			// --- gestione esito ---
			//
			DettaglioArticoliOutputBean dettaglioArticoli = UniversalAPIUtil.getDettaglioArticoliOutputBean(risposta);
			return dettaglioArticoli != null && dettaglioArticoli.getEsitoServizio() == 0 ? dettaglioArticoli : null; 
		}
		catch (Throwable jre) {
			String msg = ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre);
			logger.error(msg);
			throw new IllegalStateException(msg, jre);
		}
	}



	public OrdineOutputBean callOrdine(OrdineInputBean oib) {
		return callOrdine(oib, true);
	}

	private OrdineOutputBean callOrdine(OrdineInputBean oib, boolean onSessioneScadutaRecursive) {

		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + " fornitore "+oib.getCodiceFornitore()+": ";
		logger.info(LOG_PREFIX + "...");
		Calendar now = Calendar.getInstance();
		String codiceFornitore = oib.getCodiceFornitore();   
		try {
			//
			// --- recupero del fornitore ---
			//
			FornitoreBean fornitore = fornitoreByCodice.get(codiceFornitore);
			if (fornitore == null)  {
				logger.error(LOG_PREFIX+"nessun fornitore per il codice "+codiceFornitore+"");
				return null;
			}
			logger.info(LOG_PREFIX+"individuato fornitore "+fornitore.getDescrizioneBreve());
			//
			// --- valutazione scadenza ID di Sessione ---
			//
			Calendar scadenzaSessione = fornitore.getScadenzaSessione();
			if (scadenzaSessione == null) {
				logger.warn(LOG_PREFIX+"scadenza della sessione non nota");
			}
			else if (!now.before(scadenzaSessione)) {
				if (onSessioneScadutaRecursive) {
					logger.info(LOG_PREFIX+"sessione scaduta alle "+df.format(scadenzaSessione.getTime())+"; ripetizione della login ...");
					boolean loginSuccess = login(loginInputBeanSuccess.getUserName(), loginInputBeanSuccess.getPassword(), loginInputBeanSuccess.getNomeTerminale(), loginInputBeanSuccess.getPasswordSH());
					if (loginSuccess == false) {
						logger.error(LOG_PREFIX+"login non riuscita, impossibile procedere");
						return null;
					}
					else {
						return callOrdine(oib, false);
					}
				}
				else {
					logger.error(LOG_PREFIX+"sessione scaduta alle "+df.format(scadenzaSessione.getTime()));
					return null;
				}
			}
			//
			// --- valutazione del fermo macchina ---
			//
			if (isFermoMacchina(fornitore)) return null;
			//
			// --- invocazione servizio ---
			//
			String idSessione = fornitore.getIDSessione();
			String fornitoreLivelloAPI = fornitore.getLivelloAPI();
			logger.info(LOG_PREFIX+"livello API "+fornitoreLivelloAPI+" ...");
			String serviceName = "FCKDisponibilita";
			ServiziBean servizio = (new ServiziBeanChooser().setFornitoreBean(fornitore)).getServiziBean(serviceName);
			if (servizio == null) {
				logger.error(LOG_PREFIX+"servizio "+serviceName+" non disponibile, impossibile procedere con l'interrogazione");
				return null;
			}
			String servizioLivelloAPI = servizio.getLivelloAPI();
			Object risposta = null;
			String [] url = new String [] { servizio.getUrl1(), servizio.getUrl2() };
			for (int i = 0; i < url.length; i++) {
				if (url[i] == null) continue;
				String callInfo = LOG_PREFIX+"interrogazione servizio "+serviceName+", livello API "+servizioLivelloAPI+", url "+url[i]; 
				logger.info(callInfo+" ...");
				try {
					if ("2005.001".equals(servizioLivelloAPI)) {
						farmaclickWS_2005_001.setEndpointAddress(url[i]);
						risposta = farmaclickWS_2005_001.callOrdine(UniversalAPIUtil.getOrdineInputBean_2005_001(oib));
					}
					else if ("2010.001".equals(servizioLivelloAPI)) {
						farmaclickWS_2010_001.setEndpointAddress(url[i]);
						risposta = farmaclickWS_2010_001.callOrdine(UniversalAPIUtil.getOrdineInputBean_2010_001(oib));
					}
					else {
						logger.error(LOG_PREFIX+"livello API "+servizioLivelloAPI+" non gestito (nuova versione dell'API?)");
					}
				}
				catch (Throwable e) {
					String msg = ExceptionsTool.getExceptionDescription(callInfo+" non riuscita", e);
					logger.error(msg, e);
				}
			}
			//
			// --- gestione esito ---
			//
			OrdineOutputBean ordineOutputBean = UniversalAPIUtil.getOrdineOutputBean(risposta);
			return ordineOutputBean != null && ordineOutputBean.getEsitoServizio() == 0 ? ordineOutputBean : null; 
		}
		catch (Throwable jre) {
			String msg = ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre);
			logger.error(msg);
			throw new IllegalStateException(msg, jre);
		}
	}
	
	public DettaglioArticoliOutputBean callDisponibilita(FornitoreBean fornitoreBean) {
		return callDisponibilita(fornitoreBean.getCodice());
	}
	
	public DettaglioArticoliOutputBean callDisponibilita(String codiceFornitore) {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		final String LOG_PREFIX = METH_NAME + " fornitore "+codiceFornitore+": ";
		logger.info(LOG_PREFIX + "...");
		try {
			//
			// --- recupero del fornitore ---
			//
			FornitoreBean fornitore = fornitoreByCodice.get(codiceFornitore);
			if (fornitore == null)  {
				logger.error(LOG_PREFIX+"nessun fornitore per il codice "+codiceFornitore+"");
				return null;
			}
			logger.info(LOG_PREFIX+"individuato fornitore "+fornitore.getDescrizioneBreve());
			
			// --- invocazione servizio ---
		    //
			
			
		}catch (Throwable jre) {
			String msg = ExceptionsTool.getExceptionDescription(LOG_PREFIX+"operazione non riuscita", jre);
			logger.error(msg);
			throw new IllegalStateException(msg, jre);
		}
		return null;
	}
}
