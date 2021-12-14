package fai.imp.comifar.ws;

import org.apache.log4j.Logger;
import org.soapcomifar.SoapcomifarLocator;
import org.soapcomifar.SoapcomifarStub;

import fai.common.util.ExceptionsTool;

public class ComifarSoapWS{
	
	public static enum GetDataSetModalita { GETRECORDS};

	static Logger logger = Logger.getLogger(ComifarSoapWS.class);

	private String cliente;
	private String rifinterno;
	private String minSan;
	private String startDate;
	private String endDate;
	private String primeLevelPassword;
	private String secondLevelPassword;
	private String sessionQueryType;
	private String args;
	private String wsUrl = null;
	
	private boolean lastCallSucceed = false;

	public ComifarSoapWS() {
		super();
	}

	public ComifarSoapWS(String cliente, String rifinterno, String startDate, String endDate,
			String primeLevelPassword, String secondLevelPassword) {
		super();
		this.cliente = cliente;
		this.rifinterno = rifinterno;
		this.startDate = startDate;
		this.endDate = endDate;
		this.primeLevelPassword = primeLevelPassword;
		this.secondLevelPassword = secondLevelPassword;
	}
	
	public ComifarSoapWS(String cliente, String rifinterno, String startDate, String endDate,
			String primeLevelPassword, String secondLevelPassword, String minSan, String sessionQueryType) {
		this(cliente, rifinterno, startDate, endDate, primeLevelPassword, secondLevelPassword);
		this.minSan = minSan;
		this.sessionQueryType = sessionQueryType;
	}

	private SoapcomifarStub getBinding() {
		SoapcomifarStub binding = null;
		try {
			SoapcomifarLocator locator = new SoapcomifarLocator();
			if (wsUrl != null && !wsUrl.trim().equals("")) {
				locator.setsoapcomifarEndpointAddress(wsUrl);
			}
			logger.info("indirizzo WebService: "+locator.getsoapcomifarAddress());

			binding = (SoapcomifarStub)locator.getsoapcomifar();
		}
		catch (javax.xml.rpc.ServiceException jre) {
			logger.error(ExceptionsTool.getExceptionDescription("binding non riuscito", jre));
			if(jre.getLinkedCause()!=null) {
				logger.error(ExceptionsTool.getExceptionDescription("binding non riuscito; trovata linked cause", jre.getLinkedCause()));
				logger.error("binding non riuscito; trovata linked cause", jre.getLinkedCause());
			}
			throw new IllegalStateException(ExceptionsTool.getExceptionDescription("binding non riuscito", jre), jre);
		}
		return binding;
	}

	public String getDDTList() throws Exception {
		lastCallSucceed = false;
		SoapcomifarStub binding = getBinding();
		String value = binding.ddtlist(cliente, rifinterno, args, startDate, endDate, primeLevelPassword, secondLevelPassword);
		return value;
	}
	
	public String getAvailibility() throws Exception {
		lastCallSucceed = false;
		SoapcomifarStub binding = getBinding();
		String value = binding.disponibilita(cliente, minSan, args, primeLevelPassword, secondLevelPassword);
		return value;
	}
	
	public String getProductList() throws Exception {
		lastCallSucceed = false;
		SoapcomifarStub binding = getBinding();
		String value = binding.listino(cliente, primeLevelPassword, secondLevelPassword, args, sessionQueryType);
		return value;
	}
	
	public String getProductList(Integer args) throws Exception {
		lastCallSucceed = false;
		SoapcomifarStub binding = getBinding();
		String value = binding.listino(cliente, primeLevelPassword, secondLevelPassword, args.toString(), sessionQueryType);
		return value;
	}

	public String getRifinterno() {
		return rifinterno;
	}

	public void setRifinterno(String rifinterno) {
		this.rifinterno = rifinterno;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPrimeLevelPassword() {
		return primeLevelPassword;
	}

	public void setPrimeLevelPassword(String primeLevelPassword) {
		this.primeLevelPassword = primeLevelPassword;
	}

	public String getSecondLevelPassword() {
		return secondLevelPassword;
	}

	public void setSecondLevelPassword(String secondLevelPassword) {
		this.secondLevelPassword = secondLevelPassword;
	}

	public String getWsUrl() {
		return wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	public boolean isLastCallSucceed() {
		return lastCallSucceed;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getSessionQueryType() {
		return sessionQueryType;
	}

	public void setSessionQueryType(String sessionQueryType) {
		this.sessionQueryType = sessionQueryType;
	}
	
	public void setArgs(String args) {
		this.args = args;
	}
}
