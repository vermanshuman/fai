package fai.common.http;

import java.io.File;
import java.io.InputStream;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.log4j.Logger;

import fai.common.util.Filesystem;

public class Http {
  
  
  static Logger logger = Logger.getLogger(Http.class);

  
  private CloseableHttpClient httpClient = null;
  private int lastHttpResult = -1;

  
  private CloseableHttpClient httpClient() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    if (httpClient != null) return httpClient;
    //
    logger.info(LOG_PREFIX + " creazione nuova istanza di "+CloseableHttpClient.class.getName()+" ...");
    httpClient = HttpClients.createDefault();
    return httpClient;
  }
  
  
  public InputStream getInputStreamGET(String url) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.debug(LOG_PREFIX + url + "...");
    lastHttpResult = -1;
    try {
      HttpGet httpGet = new HttpGet(url); 
      CloseableHttpResponse response = httpClient().execute(httpGet);
      HttpEntity entity = null;
      lastHttpResult = response.getCode();
      logger.debug(LOG_PREFIX + "esito "+lastHttpResult+" per "+url);
      if (lastHttpResult == 200) {
        entity =  response.getEntity();
      }
      return entity == null ? null : entity.getContent();
    }
    catch (Throwable th) {
      httpClient = null;
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
    }
  }


  public int getLastHttpResult() {
    return lastHttpResult;
  }
  
  
  
  // 
  // public static void main(String[] args) throws Exception {
  //   Http http = new Http();
  //   InputStream is = http.getInputStreamGET("https://images.photowall.com/products/60869/azores-mountain-landscape-1.jpg?h=699&q=85");
  //   Filesystem.store(is, new File("C:\\_delme\\2021\\210513b\\imgx.jpg"));
  //       
  // }
  // 

}
