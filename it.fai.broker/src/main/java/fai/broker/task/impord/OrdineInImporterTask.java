package fai.broker.task.impord;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import fai.broker.task.AbstractGenericTask;
import fai.common.ftp.FtpFactory;
import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.models.ItemStatus;
import fai.broker.models.OrdineIn;
import fai.broker.models.OrdineInCollection;
import fai.broker.models.OrdineInRigaDett;
import fai.broker.models.StatusInfo;
import fai.broker.util.CsvToModels;
import fai.common.ftp.Ftp;
import fai.common.models.FtpConfig;
import fai.common.util.Filesystem;
import fai.common.util.MD5;
import fai.common.util.Timeout;

/**
 * Importa il csv degli ordini (al momento della redazione della presente 
 * documentazione - 2021.06.18 -,  "web_order.csv") nelle tabelle
 * {@code FAI_ORDINE_IN_COLLECTION}, {@code FAI_ORDINE_IN}, 
 * {@code FAI_ORDINE_IN_RIGA_DETTAGLIO} e determina il fabbisogno di ogni 
 * singolo farmaco che salva nella tabella {@code FAI_FABBISOGNO_FARMACO}.<br/>
 * <br/>
 * 
 * 
 * @author Gianni Radice
 *
 */
public class OrdineInImporterTask extends AbstractGenericTask {
  
  
  static Logger logger = Logger.getLogger(OrdineInImporterTask.class);
  


  protected FtpConfig ftpConfig = null;
  protected String csvInFileName = null; 
  protected String csvInFileNameUID = null;
  protected String csvDirectory = null;
  
  
  @Override
  protected void completeSetup() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    //
    ftpConfig = new FtpConfig();
    String protocol = params.getProperty("protocol", true);
    boolean protocolIsLocal = "LOCAL".equals(protocol);
    csvDirectory = params.getProperty("dir", true);

    ftpConfig.setFtpProtocol(protocol);
    ftpConfig.setFtpHost(params.getProperty("host", protocolIsLocal ? false : true));
    ftpConfig.setFtpPort(params.getInteger("port", false));
    ftpConfig.setFtpDir(csvDirectory);
    ftpConfig.setFtpLogin(params.getProperty("login", false));
    ftpConfig.setFtpPassword(params.getProperty("password", false));
    ftpConfig.setFtpPasswordEncr(params.getBoolean("passwordEncr", false));
    csvInFileName = params.getProperty("csvInFileName", true); // suppongo, al momento (2021.06.21), che il nome del file sia sempre lo stesso

  }
  
  /**
   * Restituisce un identificativo univoco del file (l'MD5
   * dell'intero contenuto, il semplice nome del file, 
   * ecc...).<br/>
   * <br/>
   * Implementato come metodo dedicato perché, al momento
   * (2021.06.21), non è stato ancora chiarito quale 
   * caratteristica del file permette di distinguere un 
   * file già scaricato e salvato da un nuovo file.
   * 
   * @return
   * @throws Exception
   */
  protected String getInputCsvUID() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    Ftp ftp = null;
    InputStream is = null;
    try {
      if ("LOCAL".equals(params.getProperty("protocol", true))) {
        is = new FileInputStream(csvDirectory + FileSystems.getDefault().getSeparator() + csvInFileName); //ftp.getInputStream(csvInFileName);
      }else {
        ftp = FtpFactory.newFtp(ftpConfig);
        is = ftp.getInputStream(csvInFileName);
      }
      //ftp = FtpFactory.newFtp(ftpConfig);

      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String md5 = null;
      String line = null;
      int lineCount = 0;
      Timeout timeout = new Timeout(5000, false);
      while((line = reader.readLine())!=null) {
        md5 = md5 == null ? MD5.getMD5(line) : MD5.getMD5(md5 + line);
        // ^^ si, lo so, non è l "MD5 del file", ma, dal momento
        //    che non posso prevedere quale sarà la dimensione del file
        //    mi assicuro di non allocare mai dati che ecceedano
        //    la lunghezza di un MD5 più il contenuto di una riga
        lineCount++;
        if (timeout.isExpired()) {
          logger.info(LOG_PREFIX+" "+lineCount+" righe processate ...");
        }
      }
      return md5;
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      Filesystem.closeWithoutThrowingException(is, InputStream.class.getName()+" verso il file "+csvInFileName);
      if (ftp != null) {
        ftp.completePendingCommand();
        
      }
    }
        
  }
  

  public String doActualParseAndInsert() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    //
    Ftp ftp = null;
    InputStream is = null;
    String magazzinoAcronym = params.getProperty("magazzino_acronym", true);
    //
    // --- creazione del nuovo ORDINE_IN_COLLECTION ---
    //
    OrdineInCollection oic = new OrdineInCollection();
    oic.setOid(null);
    oic.setUniqueID(csvInFileNameUID);
    oic.setBatchId(Long.parseLong((new SimpleDateFormat("yyMMddHHmmss")).format(Calendar.getInstance().getTime())));
    oic.setInputResource(csvInFileName.substring(0, csvInFileName.lastIndexOf(".csv")) + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()) + ".csv");
    String fullpath = null;
    if ("LOCAL".equals(params.getProperty("protocol", true))) {
      fullpath = Filesystem.getFullPath(params.getProperty("dir", true), csvInFileName); 
    }
    else {
      fullpath = params.getProperty("protocol", true)+"://"+params.getProperty("host", true)+":"+params.getProperty("port", "(default port)")+"/"+params.getProperty("dir", true)+"/"+csvInFileName; 
    }
    oic.setInputResourceFullPath(fullpath);
    oic.setStatus(StatusInfo.newProcessingInstance(null, null)); // "processing", perché il parsing è in corso
    oic.setMagazzinoAcronym(magazzinoAcronym);
    SqlQueries.insertOrdineInCollection(oic, conn);
    conn.commit();
    //
    // --- inizio del parsing vero e proprio ---
    //
    logger.info(LOG_PREFIX+"parsing file "+csvInFileName+" ...");
    CsvToModels csvToModels = null;
    int lineCount = 0;
    try {

      if ("LOCAL".equals(params.getProperty("protocol", true))) {
        is = new FileInputStream(csvDirectory + FileSystems.getDefault().getSeparator() + csvInFileName); //ftp.getInputStream(csvInFileName);
      }else {
        ftp = FtpFactory.newFtp(ftpConfig);
        is = ftp.getInputStream(csvInFileName);
      }
      //ftp = FtpFactory.newFtp(ftpConfig);
      //is = new FileInputStream("D:\\FAI\\docs\\web_order.csv"); //ftp.getInputStream(csvInFileName);;
      csvToModels = new CsvToModels();
      csvToModels.setInputStream(is);
      Timeout timeout = new Timeout(5000, false);
      while (csvToModels.moveNextLine()) {
        lineCount++;
        if (timeout.isExpired()) {
          logger.info(LOG_PREFIX+" "+lineCount+" righe processate ...");
        }
      }
      logger.info(LOG_PREFIX+" "+lineCount+" righe complessivamente processate");
    }
    catch (Throwable th) {
      String lineParsingInf = lineCount == 0 ? "nessuna riga processata" : "parsing riuscito fino alla riga n."+lineCount+" inclusa";
      String humanReadableError = "parsing del file "+csvInFileName+" non riuscito ("+th.getMessage()+"); "+lineParsingInf;
      String techError = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME+"; "+lineParsingInf;
      logger.error(LOG_PREFIX+techError, th);
      oic.setStatus(SqlQueries.setOrdineInCollectionStatus(oic.getOid(), ItemStatus.VALUE_ERROR.getOid(), humanReadableError, techError, conn));
      conn.commit();
      return humanReadableError;
    }
    finally {
      Filesystem.closeWithoutThrowingException(is, InputStream.class.getName()+" verso il file "+csvInFileName);
      if (ftp != null) {
        ftp.completePendingCommand();
        
      }
    }
    List<OrdineIn> ordini = csvToModels.getOrdini();
    logger.info(LOG_PREFIX+ordini.size()+" ordini caricati ...");
    //
    // --- attribuzione dei CODICE_MINSAN o CODICE_EAN alle righe degli ordini ---
    //
    logger.info(LOG_PREFIX+"attribuzione dei codici MinSan/EAN ...");
    //AnagraficaFarmaciMinSanEanCache anagrafica = new AnagraficaFarmaciMinSanEanCache();// SqlQueries.getAnagraficaFarmaciMinSanEanCache(conn);
    for (int i = 0; i < ordini.size(); i++) {
      OrdineIn ordine = ordini.get(i);
      //int IdOrdine = ordine.getIdOrdine();
      OrdineInRigaDett [] riga = ordine.getAllRigaDett();
      for (int j = 0; j < riga.length; j++) {
        if ("Coupon".equals(riga[j].getTitoloInserzione())) continue;
        String numeroOggetto = riga[j].getNumeroOggetto();
        //anagrafica.setProdotto(numeroOggetto, false);
        riga[j].setCodiceMinSan(numeroOggetto);
        
        /*
        if (anagrafica.exists()) {
          if (anagrafica.isMinSan()) {
            riga[j].setCodiceMinSan(numeroOggetto);
          }
          else if (anagrafica.isEan()) {
            riga[j].setCodiceEan(numeroOggetto);
          }
          else {
            String error = "inammissibile, trovato riscontro per Numero Oggetto "+numeroOggetto+" caratterizzante una delle righe dell'Ordine identificato dall'Id Ordine "+numeroOggetto+", tuttavia il Numero Oggetto non risulta essere né un codicde MinSan, né un codice EAN"; 
            oic.setStatus(SqlQueries.setOrdineInCollectionStatus(oic.getOid(), ItemStatus.VALUE_ERROR.getOid(), error, error, conn));
            conn.commit();
            throw new IllegalStateException(error);
          }
        }
        else {
          String error = "nessun riscontro per Numero Oggetto "+numeroOggetto+" caratterizzante una delle righe dell'Ordine identificato dall'Id Ordine "+numeroOggetto+"; il Numero Oggetto non corrisponde nessun codice MinSan ed a nessun codice EAN tra quelli noti in anagrafica"; 
          if (numeroOggetto.startsWith("P")) error+="; inoltre, data l'iniziale, il Numero Oggetto dovrebbe indicare un Coupon mentre, nel Titolo Inserizione, è indicato "+riga[j].getTitoloInserzione();
          oic.setStatus(SqlQueries.setOrdineInCollectionStatus(oic.getOid(), ItemStatus.VALUE_ERROR.getOid(), error, error, conn));
          conn.commit();
          return error;
        }*/
      }
    }    
    //
    // --- analisi eventuali incongruenze degli ordini caricati ---
    //
    logger.info(LOG_PREFIX+"analisi eventuali icongurenze negli ordini caricati ...");
    String error = (new OrdineInAnalyzer()).analyze(ordini);
    if (error != null) {
      oic.setStatus(SqlQueries.setOrdineInCollectionStatus(oic.getOid(), ItemStatus.VALUE_ERROR.getOid(), error, error, conn));
      conn.commit();
      return error;
    }
    //
    // --- registrazione in banca dati ---
    //
    logger.info(LOG_PREFIX+"registrazione dei "+ordini.size()+" caricati in banca dati ...");
    for (int i = 0; i < ordini.size(); i++) {
      logger.info(LOG_PREFIX+"inserimento Ordine #"+(i+1)+"/"+ordini.size()+" ...");
      OrdineIn ordine = ordini.get(i);
      ordine.setStatus(StatusInfo.newProcessedInstance(null, null)); // "processed" (alla commit)   
      SqlQueries.insertOrdineIn(oic.getOid(), ordine, conn);
      OrdineInRigaDett [] riga = ordine.getAllRigaDett();
      for (int j = 0; j < riga.length; j++) {
    	riga[j].setMagazzinoAcronym(magazzinoAcronym);
    	logger.info("magazzinoAcronym ::"+magazzinoAcronym);
        SqlQueries.insertOrdineInRigaDettaglio(ordine.getOid(), riga[j], conn);
      }
    }
    //
    //
    // --- impostazione a "processed" della collezione di ordini, quindi commit ---
    //
    SqlQueries.setOrdineInCollectionStatus(oic.getOid(), ItemStatus.VALUE_PROCESSED.getOid(), null, null, conn);
    conn.commit();
    //
    return null;
  }
    
  
  

  @Override
  protected String doJobExecute() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    //
    String error = null;
    //
    // --- verifica che le tabelle di lavoro non siano in stato inconsistente ---
    //
    error = SqlQueries.isUnableToRunStatusError("FAI_ORDINE_IN_COLLECTION", "gruppo di Ordini da elaborare", conn);
    if (error != null) return error;
    //
    error = SqlQueries.isUnableToRunStatusError("FAI_ORDINE_IN", "Ordini", conn);
    if (error != null) return error;
    //
    // --- nota/warning circa gli rodini sospesi ---
    //
    String warn = SqlQueries.isUnableToRunStatusError("FAI_ORDINE_IN_COLLECTION", "Ordini", new ItemStatus [] { ItemStatus.VALUE_VOIDED }, conn);
    if (warn != null) {
      logger.warn(LOG_PREFIX+"trovati uno o più record FAI_ORDINE_IN_COLLECTION in stato "+ItemStatus.VALUE_VOIDED.getAcronym()+"; tutti gli FAI_ORDINE_IN della stessa FAI_ORDINE_IN_COLLECTION saranno considerati "+ItemStatus.VALUE_VOIDED.getAcronym()+" e, quindi, ignorati");
    }
    warn = SqlQueries.isUnableToRunStatusError("FAI_ORDINE_IN", "Ordini", new ItemStatus [] { ItemStatus.VALUE_VOIDED }, conn);
    if (warn != null) {
      logger.warn(LOG_PREFIX+"trovati uno o più record FAI_ORDINE_IN in stato "+ItemStatus.VALUE_VOIDED.getAcronym());
    }
    //
    // --- verifica che il file oggetto di import non sia già stato elaborato ---
    //
    csvInFileNameUID = getInputCsvUID();
    if (SqlQueries.isOrdineInCollectionAlreadyCreated(csvInFileNameUID, conn)) {
      logger.info(LOG_PREFIX+"l'ordine identificato da "+csvInFileNameUID+" risulta già essere stato processato");
      return null;
    }
    //
    // --- avvio dell'import vero e proprio ---
    //
    return doActualParseAndInsert();
    
    
  }
  
  

}

