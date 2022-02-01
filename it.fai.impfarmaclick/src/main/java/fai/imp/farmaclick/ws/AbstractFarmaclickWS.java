package fai.imp.farmaclick.ws;

import org.apache.log4j.Logger;

import fai.common.util.AxisSerializationUtil;

public abstract class AbstractFarmaclickWS {
  
  
  static Logger logger = Logger.getLogger(AbstractFarmaclickWS.class);

  protected String endpointAddress  = null;

  protected String serviceAPILevel  = null;
  

  private boolean logRequestAsXml = false; 
  private boolean logReponsesAsXml = false; 

  private String requestXml = null;
  private String responseXml = null;

  public String getEndpointAddress() {
    return endpointAddress;
  }

  public void setEndpointAddress(String endpointAddress) {
    this.endpointAddress = endpointAddress;
  }

  public String getServiceAPILevel() {
    return serviceAPILevel;
  }

  public void setServiceAPILevel(String serviceAPILevel) {
    this.serviceAPILevel = serviceAPILevel;
  }

  protected void logEsitoServizio(String logPrefix, Object o) throws Exception {
    Integer esitoServizioCodice = EsitoServizioUtil.getCodice(o);
    String descr = EsitoServizioUtil.getDescrizione(esitoServizioCodice);
    if (logPrefix == null) logPrefix="";
    //
    String msg = logPrefix+"esito servizio "+esitoServizioCodice+", "+descr;
    //
    if (esitoServizioCodice == 0) {
      logger.debug(msg);
    }
    else {
      logger.error(msg);
    }
  }
  
  
  public boolean isLogRequestAsXml() {
    return logRequestAsXml;
  }

  public void setLogRequestAsXml(boolean logRequestAsXml) {
    this.logRequestAsXml = logRequestAsXml;
  }

  public boolean isLogReponsesAsXml() {
    return logReponsesAsXml;
  }

  public void setLogReponsesAsXml(boolean logReponsesAsXml) {
    this.logReponsesAsXml = logReponsesAsXml;
  }

  protected void logRequestAsXml(String logPrefix, Object o) throws Exception {
    if (logRequestAsXml == false) return;
    if (logPrefix == null) logPrefix="";
    //
    setRequestXml(AxisSerializationUtil.serializeAxisObject(o));
    logger.info(logPrefix+"richiesta, "+getRequestXml());

  }

  protected void logResponseAsXml(String logPrefix, Object o) throws Exception {
    if (logReponsesAsXml == false) return;
    if (logPrefix == null) logPrefix="";
    //
    setResponseXml(AxisSerializationUtil.serializeAxisObject(o));
    logger.info(logPrefix+"risposta, "+AxisSerializationUtil.serializeAxisObject(o));
  }

  public String getRequestXml() {
    return requestXml;
  }

  public void setRequestXml(String requestXml) {
    this.requestXml = requestXml;
  }

  public String getResponseXml() {
    return responseXml;
  }

  public void setResponseXml(String responseXml) {
    this.responseXml = responseXml;
  }
}
