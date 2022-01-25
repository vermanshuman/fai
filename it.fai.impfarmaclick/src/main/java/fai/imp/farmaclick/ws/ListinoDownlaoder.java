package fai.imp.farmaclick.ws;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean;
import fai.imp.farmaclick.ws.bean.DownloadListinoOutputBean;

public class ListinoDownlaoder {
  
  
  static Logger logger = Logger.getLogger(ListinoDownlaoder.class);
  
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss/SSS");

  private FornitoreBean fornitore = null;
  private DownloadListinoOutputBean downloadListino = null;
  
  private Hashtable<String, Calendar> lastDownloadTsByCodiceFornitore = new Hashtable<String, Calendar>(); 
  private Hashtable<String, Integer> delaySecondsByCodiceFornitore = new Hashtable<String, Integer>(); 
  private Hashtable<String, FornitoreBean> fornitoreByCodice = new Hashtable<String, FornitoreBean>(); 

  public void setDownloadListino(FornitoreBean fornitore, DownloadListinoOutputBean downloadListino) {
    this.fornitore = fornitore;
    this.downloadListino = downloadListino;
    delaySecondsByCodiceFornitore.put(fornitore.getCodice(), this.downloadListino.getDelayProsRichiesta());
    fornitoreByCodice.put(fornitore.getCodice(), fornitore);
  }
  
  
  public InputStream download() throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX  = METH_NAME+" fornitore "+fornitore.getCodice()+": ";
    logger.debug(LOG_PREFIX +"...");
    InputStream is = null;
    boolean callPerformed = false;
    try {
      Calendar lastDownload = lastDownloadTsByCodiceFornitore.get(fornitore.getCodice());
      if (lastDownload != null) {
        Calendar now = Calendar.getInstance();
        Calendar nextDownload = (Calendar)lastDownload.clone();
        int delayMinutes = delaySecondsByCodiceFornitore.get(fornitore.getCodice());
        nextDownload.add(Calendar.MINUTE, delayMinutes);
        long timeToWait = nextDownload.getTimeInMillis() - now.getTimeInMillis();
        if (timeToWait > 0) {
          logger.info("impossibile procedere al download prima di "+df.format(nextDownload.getTime()+"; operazioni sospese fino a tale ora"));
          Thread.currentThread().sleep(timeToWait + /* pure un po' di tolleranza... */ 1000 );
        }
      }
      //
      //
      //
      logger.info("**** IMPLEMENTARE ****");
      //
      //
      //
      callPerformed = true; // immediatamente prima di contattare il server 
      //
      //
      //
      return is;
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      if (callPerformed) {
        lastDownloadTsByCodiceFornitore.put(fornitore.getCodice(), Calendar.getInstance());
      }
    }
  }
  
  
  

}
