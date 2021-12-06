package fai.imp.comifar.task;

import it.swdes.decrypt.Decryptor;

import java.sql.Connection;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;

import fai.common.util.XmlUtil;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.base.task.AbstractDataCollector;
import fai.imp.comifar.ws.ComifarSoapWS;
import fai.imp.comifar.ws.ComifarSoapWSMethFormatter;

public class ComifarDataCollector extends AbstractDataCollector{
	static Logger logger = Logger.getLogger(ComifarDataCollector.class);
	private String ddtList = null;
	private String availibility = null;
	
	private ComifarSoapWS ws = null;

	public ComifarDataCollector(FaiImportConfig config, Connection conn) {
		super(config, conn);
	}

	@Override
	protected String doCollectData_executeAll() throws Exception {
		String error = null; 
		try {
			logger.info("recupero dati get ddt list ...");

			error = loadGetDDTList();
			if (error != null) return error;
		} catch (Throwable th) {
			String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "»";
			logger.error(msg, th);
			throw new Exception(msg, th);
		}
		return null;
	}

	@Override
	protected void doCollectData_prepare_specificSetup() throws Exception {
		logger.info("creazione proxy Web Service ...");
		String client = config.getServiceLogin();
		if (client != null && client.trim().equals("")) client = null;
		String primeLevelPassword = config.getServicePass();
		String secondLevelPassword = config.getServicePassSecondLevel();
		if (primeLevelPassword != null) {
			if (primeLevelPassword.trim().equals("")) { 
				primeLevelPassword = null;
			}
			else {
				if (Boolean.TRUE.equals(config.isServicePassEncr())) {
					logger.info("decrittazione primo password del WebService ...");
					primeLevelPassword = Decryptor.decrypt(primeLevelPassword);
				}
			}
		}
		if (secondLevelPassword != null) {
			if (secondLevelPassword.trim().equals("")) { 
				secondLevelPassword = null;
			}
			else {
				if (Boolean.TRUE.equals(config.isServicePassEncr())) {
					logger.info("decrittazione secondo password del WebService ...");
					secondLevelPassword = Decryptor.decrypt(secondLevelPassword);
				}
			}
		}

		ws = new ComifarSoapWS(client, "", "01/08/2021", "21/08/2021", primeLevelPassword, secondLevelPassword, getMinSan());
		ws.setWsUrl(config.getServiceQueryUrl());
	}

	@Override
	protected void doCollectData_prepare_resumePrevSession() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doCollectData_prepare_startNewSession() throws Exception {
		// TODO Auto-generated method stub

	}

	private String loadGetDDTList() throws Exception {
		String serviceID = ComifarSoapWSMethFormatter.formatGetDDTList();
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");
		ddtList = ws.getDDTList();
		logger.debug("DDTList Resposne : {}" + ddtList);
		if(ddtList != null){
			org.w3c.dom.Document xmlDoc = XmlUtil.asDocument(ddtList);
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			org.w3c.dom.NodeList nodes = (org.w3c.dom.NodeList)xPath.evaluate("risposta_ddtlist/esito/sigla",
			        xmlDoc, XPathConstants.NODESET);
			
			String status = "";
			
			if(nodes.getLength() > 0){
				status = nodes.item(0).getTextContent();
			}
			if (!"OK".equalsIgnoreCase(status)) throw new IllegalStateException("inammissibile, codice esito "+status+" NON ammesso in questo punto");
			
				
		}
		return "";
	}

	@Override
	protected String doCollectData_getAvailability() throws Exception {
		logger.info("recupero dati disponibilit\u00E0");
		String serviceID = ComifarSoapWSMethFormatter.formatGetAvailibility();
		logger.info("recupero dati "+serviceID+", invocazione WebService ...");
		availibility = ws.getAvailibility();
		logger.info("Disponibilit\u00E0 Resposne : {}" + availibility);
		if(availibility != null){
			org.w3c.dom.Document xmlDoc = XmlUtil.asDocument(availibility);
			XPath xPath = XPathFactory.newInstance().newXPath();
			org.w3c.dom.NodeList nodes = (org.w3c.dom.NodeList)xPath.evaluate("disponibilita/esito/sigla",
			        xmlDoc, XPathConstants.NODESET);
			String status = "";
			
			if(nodes.getLength() > 0){
				status = nodes.item(0).getTextContent();
			}
			if (!"OK".equalsIgnoreCase(status)) throw new IllegalStateException("inammissibile, codice esito "+status+" NON ammesso in questo punto");
			
		}
		return "";
	}
}

