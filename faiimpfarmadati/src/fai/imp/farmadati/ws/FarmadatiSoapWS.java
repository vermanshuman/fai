package fai.imp.farmadati.ws;

import org.apache.log4j.Logger;
import org.slf4j.helpers.MarkerIgnoringBase;

import fai.common.util.ExceptionsTool;

public class FarmadatiSoapWS {

  
  public static Logger logger = Logger.getLogger(FarmadatiSoapWS.class);
  
  public static enum GetDataSetModalita { GETRECORDS, DATA_AGG, COUNT };
  
  private static String CODICE_ESITO_OK = "OK";
  
  private String user;
  private String pass;
  private String wsUrl = null;
  private boolean lastCallSucceed = false;
  
  
  

  public FarmadatiSoapWS() {
    super();
  }


  public FarmadatiSoapWS(String user, String pass) {
    super();
    this.user = user;
    this.pass = pass;
  }


  public String getUser() {
    return user;
  }


  public void setUser(String user) {
    this.user = user;
  }


  public String getPass() {
    return pass;
  }


  public void setPass(String pass) {
    this.pass = pass;
  }

  
  
  public String getWsUrl() {
    return wsUrl;
  }


  public void setWsUrl(String wsUrl) {
    this.wsUrl = wsUrl;
  }


  private org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub getBinding() {
    org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub binding = null;
    try {
        org.tempuri.FarmadatiItaliaWebServicesM2Locator locator = new org.tempuri.FarmadatiItaliaWebServicesM2Locator();
        if (wsUrl != null && !wsUrl.trim().equals("")) {
          locator.setBasicHttpBinding_FarmadatiItaliaWebServicesM2EndpointAddress(wsUrl);
        }
        logger.info("indirizzo WebService: "+locator.getBasicHttpBinding_FarmadatiItaliaWebServicesM2Address());
        binding = (org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub)locator.getBasicHttpBinding_FarmadatiItaliaWebServicesM2();
    }
    catch (javax.xml.rpc.ServiceException jre) {
      logger.error(ExceptionsTool.getExceptionDescription("binding non riuscito", jre));
        if(jre.getLinkedCause()!=null) {
          logger.error(ExceptionsTool.getExceptionDescription("binding non riuscito; trovata linked cause", jre.getLinkedCause()));
          logger.error("binding non riuscito; trovata linked cause", jre.getLinkedCause());
        }
        throw new IllegalStateException(ExceptionsTool.getExceptionDescription("binding non riuscito", jre), jre);
    }
    catch (Throwable th) {
      logger.error(ExceptionsTool.getExceptionDescription("binding non riuscito", th));
    }
    return binding;
  }


  public org.datacontract.schemas._2004._07.FDIWebServices.GetEnabledDataSet_Output getEnabledDataSet() throws Exception {
    lastCallSucceed = false;
    org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub binding = getBinding();
    org.datacontract.schemas._2004._07.FDIWebServices.GetEnabledDataSet_Output value = null;
    value = binding.getEnabledDataSet(user, pass);
    lastCallSucceed = CODICE_ESITO_OK.equals(value != null ? value.getCodEsito() : null);
    return value;
  }

  
  public org.datacontract.schemas._2004._07.FDIWebServices.GetSchemaDataSet_Output getSchemaDataSet(String codiceSetDati, boolean tracciatoVar) throws Exception {
    lastCallSucceed = false;
    org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub binding = getBinding();
    org.datacontract.schemas._2004._07.FDIWebServices.GetSchemaDataSet_Output value = null;
    value = binding.getSchemaDataSet(user, pass, codiceSetDati, tracciatoVar);
    lastCallSucceed = CODICE_ESITO_OK.equals(value != null ? value.getCodEsito() : null);
    return value;
  }

  public org.datacontract.schemas._2004._07.FDIWebServices.GetDataSet_Output getDataSet(String codiceSetDati, GetDataSetModalita modalita, int pageN) throws Exception {
    lastCallSucceed = false;
    org.tempuri.BasicHttpBinding_FarmadatiItaliaWebServicesM2Stub binding = getBinding();
    org.datacontract.schemas._2004._07.FDIWebServices.GetDataSet_Output value = null;
    value = binding.getDataSet(user, pass, codiceSetDati, ""+modalita, pageN);
    lastCallSucceed = CODICE_ESITO_OK.equals(value != null ? value.getCodEsito() : null);
    return value;
  }

  
  

  
  

  public boolean isLastCallSucceed() {
    return lastCallSucceed;
  }
  
  
  

}
