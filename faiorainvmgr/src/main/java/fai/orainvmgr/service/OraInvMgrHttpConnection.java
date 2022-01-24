package fai.orainvmgr.service;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

import org.apache.log4j.Logger;

class OraInvMgrHttpConnection {

  public static Logger log = Logger.getLogger(OraInvMgrHttpConnection.class);
  
  private static int AUTHENTICATION_MAX_RETRY = 2;
  
  private String username = null;
  private String password = null;
  private String serverUrl = null;
  
  private int responseCode;
  
  private String responseMessage;
  
  private String responseAsText;
  
  private boolean responseCodeIsError;
  
  
  public OraInvMgrHttpConnection(String username, String password, String serverUrl) {
    super();
    this.username = username;
    this.password = password;
    this.serverUrl = serverUrl;
  }


  public String getTargetUrl() {
    return serverUrl;
  }


  public int getResponseCode() {
    return responseCode;
  }
  
  public boolean isResponseCodeError() {
    return responseCodeIsError;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  

  public String getResponseAsText() {
    return responseAsText;
  }

  private String getFullUrl(String methodUrl) throws Exception {
    if (methodUrl == null) return serverUrl;
    //
    if (methodUrl.startsWith("/") == false) methodUrl = "/"+methodUrl;
    //
    String url = serverUrl;
    if (url.endsWith("/"))  url.substring(0, url.length()-1);
    //
    return url + methodUrl;
  }
  
  public boolean sendData(String data) throws Exception {
    return sendData(null, data); 
  }

  public boolean sendData(String methodUrl, String data) throws Exception {
    this.responseMessage = null;
    this.responseAsText = null;
    String targetUrl = getFullUrl(methodUrl);
    //
    final String LOG_PREFIX = String.format("inoltro di %s all'url %s", data, targetUrl);
    //
    log.debug(String.format("%s ...", LOG_PREFIX));
    //
    HttpURLConnection connection;
    URL url = new URL(targetUrl);
    if (username != null && !"".equals(username.trim())) {
      Authenticator.setDefault(new Authenticator() {
        private int authenticationRetry = -1;
        protected PasswordAuthentication getPasswordAuthentication() {
          authenticationRetry++;
          if (authenticationRetry > AUTHENTICATION_MAX_RETRY) throw new IllegalStateException(LOG_PREFIX+" accesso con autenticazione non riuscito; massimo numero di retry raggiunto");
          log.debug(LOG_PREFIX+" accesso con autenticazione "+(authenticationRetry > 0 ? ", retry #"+authenticationRetry+"/"+AUTHENTICATION_MAX_RETRY+" " : "")+"...");
          return new PasswordAuthentication (username, password != null && !"".equals(password.trim()) ? password.toCharArray() : null);
      }
      });
    }
    else {
      log.debug(String.format("%s accesso senza autenticazione ...", LOG_PREFIX));
    }
    connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/vnd.oracle.adf.resourceitem+json");
    connection.setUseCaches(false);
    connection.setDoOutput(true);
    //
    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
    wr.writeBytes(data);
    wr.flush();
    wr.close();
    //
    responseCode = connection.getResponseCode();
    responseMessage = connection.getResponseMessage();
    OraInvMgrStatusCodes statusCode = new OraInvMgrStatusCodes(responseCode);
    if (statusCode.isError() == false) {
      InputStream is = connection.getInputStream();
      StringBuffer rispostaBuffer = new StringBuffer();
      while (is.available() > 0) {
        int c = is.read();
        rispostaBuffer.append((char)c);
      }
      responseAsText = rispostaBuffer.toString();
      log.debug(String.format("%s, esito HTTP %d (%s): %s%s", LOG_PREFIX, responseCode, responseMessage,  System.getProperty("line.separator"), responseAsText));
    }
    else {
      log.error(String.format("%s, errore HTTP %d (%s); dettagli errore: \"%s\"", LOG_PREFIX, responseCode, responseMessage, statusCode.getDetails()));
      responseAsText = null; 
    }
    //
    responseCodeIsError = statusCode.isError();
    return !responseCodeIsError;
  }

}
