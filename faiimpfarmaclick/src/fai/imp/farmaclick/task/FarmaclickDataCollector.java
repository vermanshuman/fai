package fai.imp.farmaclick.task;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import fai.common.csv.CsvException;
import fai.common.http.Http;
import fai.common.util.ExceptionsTool;
import fai.common.util.Filesystem;
import fai.common.util.Timeout;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.base.task.AbstractDataCollector;
import fai.imp.farmaclick.csv.CsvRecordFarmaclick;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickA;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickC;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickD;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickFactory;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickL;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickR;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickV;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickZ;
import fai.imp.farmaclick.csv.CsvRecordFieldsTester;
import fai.imp.farmaclick.db.SqlQueries;
import fai.imp.farmaclick.models.Fornitore;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean;
import fai.imp.farmaclick.ws.FarmaclickWS;
import fai.imp.farmaclick.ws.bean.DownloadListinoOutputBean;
import it.swdes.decrypt.Decryptor;

public class FarmaclickDataCollector extends AbstractDataCollector {
  
  
  static Logger logger = Logger.getLogger(FarmaclickDataCollector.class);
  
  static final boolean DEBUG_DO_NOT_CONFIRM_DOWNLOAD = true; // perché sennò otterrò sempre zero differenze se scarico le variazioni 
  
  private Http http = null;
  private FarmaclickWS ws = null;

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

    //
    // --- login al WebService ---
    //
    String login = config.getServiceLogin();
    if (login != null && login.trim().equals("")) login = null;
    String pass = config.getServicePass();
    if (pass != null) {
      if (pass.trim().equals("")) { 
        pass = null;
      }
      else {
        if (config.isServicePassEncr()) {
          logger.info("decrittazione password del WebService ...");
          pass = Decryptor.decrypt(pass);
        }
      }
    } 
    //
    // --- Fornitori: download e registrazione in banca dati  ---
    //
    ws.login(login, pass, "SERVER", "");
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
          SqlQueries.storeFornitore(fornitoreBean, false, cleanUrl(dlob.getUrlDownload()), cleanUrl(dlob.getUrlConfermaDownload()), conn);
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
    List<Fornitore> fornitori = SqlQueries.findAllFornitoreWithoutDataAlreadyStored(conn);
    for (Fornitore fornitore : fornitori) {
      try {
        logger.info(LOG_PREFIX + " "+fornitore.getCodice()+" download e registrazione dati in banca dati ...");
        List<CsvException> errors = doCollect_fornitore(fornitore);
        if (errors.size() == 0)
          logger.info(LOG_PREFIX + " "+fornitore.getCodice()+" download e registrazione dati in banca dati completata");
        else
          logger.error(LOG_PREFIX + " "+fornitore.getCodice()+" download e registrazione dati in banca dati non riuscita");
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
      SqlQueries.setFornitoreCsvData(fornitore.getCodice(), is, config.isServiceQureyZippedContent(), conn);
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
        if (http.getLastHttpResult() != 200) throw new IllegalStateException(LOG_PREFIX+"download non riuscito, errore HTTP "+http.getLastHttpResult());
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
      logger.error(LOG_PREFIX + errors.size()+" errori di parsing del CSV riscontrati per questo fornitore; nessuna variazione alla banca dati");
      return errors;
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
      // --- se non in modalità parsing/verifica, cancellazione del Tipo Record "D"  ---
      //     (Reset Campagne e Listino, "Se il grossista vuole effettuare un "reset" del listino, invia un tipo record "D" [...]") 
      //
      if (preparseOnly == false) {
        SqlQueries.deleteAllCsvRecordFarmaclickD(fornitore.getOid(), conn);        
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
            throw e;
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
        else {
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
          else
            throw new IllegalStateException(LOG_PREFIX + "tipo di record non gestito " + record.getClass().getName());
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
  protected String doCollectData_getAvailability() throws Exception {
  	return null;
  }
}
