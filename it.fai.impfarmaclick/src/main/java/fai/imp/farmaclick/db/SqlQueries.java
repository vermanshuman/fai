package fai.imp.farmaclick.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import fai.common.db.SqlUtilities;
import fai.imp.farmaclick.csv.CsvFarmaclickCommons;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickA;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickC;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickD;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickL;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickR;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickV;
import fai.imp.farmaclick.csv.CsvRecordFarmaclickZ;
import fai.imp.farmaclick.models.Fornitore;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean;

public class SqlQueries {
  
  static Logger logger = Logger.getLogger(SqlQueries.class);

  static final String SQL_RESOURCE_PATH = "/fai/imp/farmaclick/db/resources/sql/";
  
  


  public static void setAllFornitoreNoLongerExists(Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setAllFornitoreNoLongerExists.sql");
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }

  }
  
  public static void deleteAlLRecordsFromNoLongerExiststingFornitore(Connection conn) throws Exception {
    List<String> tableNames = new ArrayList<String>();
    tableNames.add("FAI_RECORD_A_ADDEBITO_FINANZ");
    tableNames.add("FAI_RECORD_C_CONDIZIONI");
    tableNames.add("FAI_RECORD_D_RESET");
    tableNames.add("FAI_RECORD_L_LISTINO");
    tableNames.add("FAI_RECORD_R_RAGGR_COND");
    tableNames.add("FAI_RECORD_V_RAGGR_COND");
    tableNames.add("FAI_RECORD_Z_CHIUSURA");
    for (String tableName : tableNames) {
      deleteAlLRecordsFromNoLongerExiststingFornitore(tableName, conn);
    }
  }
  

  public static void deleteAlLRecordsFromNoLongerExiststingFornitore(String tableName, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    try {
      Properties props = new Properties();
      props.setProperty("TABLE_NAME", tableName);
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "deleteAlLRecordsFromNoLongerExiststingFornitore.sql", props);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }

  }
  
  
  
  
  public static void storeFornitore(FornitoreBean fb, boolean zippedContent, String urlDownload,
                                    String urlConferma, Long oidConfig, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      Properties props = new Properties();
      props.setProperty("CODICE", SqlUtilities.getAsStringFieldValue(fb.getCodice()));
      props.setProperty("CODICE_SITO_LOGISTICO", SqlUtilities.getAsStringFieldValue(fb.getCodiceSitoLogistico()));
      props.setProperty("DESCRIZIONE_BREVE", SqlUtilities.getAsStringFieldValue(fb.getDescrizioneBreve()));
      props.setProperty("LAST_CSV_DOWNLOAD_URL", SqlUtilities.getAsStringFieldValue(urlDownload));
      props.setProperty("LAST_CSV_CONFIRM_URL", SqlUtilities.getAsStringFieldValue(urlConferma));
      props.setProperty("LAST_CSV_ZIPPED", zippedContent ? "'S'" : "'N'");
      props.setProperty("OID_CONFIG", Long.toString(oidConfig));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "storeFornitore.sql", props);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }

  }
  
  public static Set<String> getAllFornitoreCodiceWithDataAlreadyStored(Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    Set<String> codici = new HashSet<String>();
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getAllFornitoreCodiceWithDataAlreadyStored.sql");
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        codici.add(rs.getString("CODICE"));
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
    return codici;

  }


  public static List<Fornitore> findAllFornitoreFromConfig(Connection conn, long configOid) throws Exception {
    return findAllFornitoreByCondition("WHERE OID_CONFIG=" + configOid, conn);
  }

  private static List<Fornitore> findAllFornitoreByCondition(String whereCondition, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<Fornitore> list = new LinkedList<Fornitore>();
    try {
      Properties params = new Properties();
      params.setProperty("whereCondition", whereCondition);
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "findAllFornitoreByCondition.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        Fornitore f = new Fornitore();
        f.setOid(rs.getLong("OID"));
        f.setCodice(rs.getString("CODICE"));
        f.setCodiceSitoLogistico(rs.getString("CODICE_SITO_LOGISTICO"));
        f.setDescrizioneBreve(rs.getString("DESCRIZIONE_BREVE"));
        f.setLastCsvFilename(rs.getString("LAST_CSV_FILENAME"));
        f.setLastCsvZipped("S".equals(rs.getString("LAST_CSV_ZIPPED")));
        f.setLastCsvDownloadUrl(rs.getString("LAST_CSV_DOWNLOAD_URL"));
        f.setLastCsvDownloadAtTs(SqlUtilities.getCalendar(rs, "LAST_CSV_DOWNLOAD_AT_TS"));
        f.setLastCsvConfirmUrl(rs.getString("LAST_CSV_CONFIRM_URL"));
        f.setLastCsvConfirmedAtTs(SqlUtilities.getCalendar(rs, "LAST_CSV_CONFIRMED_AT_TS"));
        f.setNoLongerExistsTs(SqlUtilities.getCalendar(rs, "NO_LONGER_EXISTS_TS"));
        f.setAllDataStoredTs(SqlUtilities.getCalendar(rs, "ALL_DATA_STORED_TS"));
        list.add(f);
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
    return list;

  }

  
  public static void setFornitoreCsvData(String codiceFornitore, InputStream data, boolean zippedData, Long oidConfig,  Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    PreparedStatement pstmt = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setFornitoreCsvData.sql");
      pstmt = conn.prepareStatement(sql);
      pstmt.setBlob(1, data);
      pstmt.setString(2, zippedData ? "S" : "N");
      pstmt.setLong(3,  oidConfig);
      pstmt.setString(4,  codiceFornitore);
      pstmt.executeUpdate();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(pstmt);
      try {
        data.close();
      }
      catch (Throwable th) {
        String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» tentando di chiudere l' "+InputStream.class.getName()+" nell'esecuzione del metodo " + METH_NAME;
        logger.warn(msg, th);
      }
    }
  }
  
  public static void setFornitoreCsvDownloadTimestamps(String codiceFornitore, Calendar downloadAt, Calendar downloadConfirmedAt, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("CODICE", SqlUtilities.getAsStringFieldValueWC(codiceFornitore));
      params.setProperty("LAST_CSV_DOWNLOAD_AT_TS", SqlUtilities.calendarToOracleToDate(downloadAt));
      params.setProperty("LAST_CSV_CONFIRMED_AT_TS", SqlUtilities.calendarToOracleToDate(downloadConfirmedAt));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setFornitoreCsvDownloadTimestamps.sql", params);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }
  
  public static InputStream getFornitoreCsvData(String codiceFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    InputStream is = null;
    try {
      Properties params = new Properties();
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getFornitoreCsvData.sql");
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, codiceFornitore);
      rs = stmt.executeQuery();
      if (rs.next()) {
        Blob blob = rs.getBlob(1);
        is = blob.getBinaryStream();
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
    return is;

  }
  
  
  public static void deleteAllCsvRecordFarmaclickD(long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID_FORNITORE", ""+oidFornitore);
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "deleteAllCsvRecordFarmaclickD.sql", params);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }
  
  public static void store(CsvRecordFarmaclickA record, long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.debug/* compare in un loop, lo metto a livello debug */(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID_FORNITORE", ""+oidFornitore);
      params.setProperty("AZIONE", SqlUtilities.getAsStringFieldValue(record.getAzione()));
      params.setProperty("CODICE_CLIENTE", SqlUtilities.getAsStringFieldValue(record.getCodiceCliente()));
      params.setProperty("CODICE_ADDEBITO_FINANZ", SqlUtilities.getAsStringFieldValue(record.getCodiceAddebitoFinanziario()));
      params.setProperty("DESCR_ADDEBITO_FINANZ", SqlUtilities.getAsStringFieldValue(record.getDescrizioneAddebitoFinanziario()));
      params.setProperty("PERC_ADDEBITO_FINANZ", ""+record.getPercentualeAddebitoFinanziario());
      params.setProperty("NUM_GIORNI_ANNO_COMM", ""+record.getNumeroGiorniAnnoCommerciale());
      switch (record.getAzione()) {
      	case CsvFarmaclickCommons.AZIONE_INSERIMENTO:
            sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertCsvRecordFarmaclickA.sql", params);
      		break;
      	default:
            sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateCsvRecordFarmaclickA.sql", params);
      		break;
      }
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      //throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }


  public static void store(CsvRecordFarmaclickC item, long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.debug/* compare in un loop, lo metto a livello debug */(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID_FORNITORE", ""+oidFornitore);
      params.setProperty("ALLINEAM_SCADENZA", SqlUtilities.getAsStringFieldValue(item.getAllineamentoScadenza()));
      params.setProperty("AZIONE", SqlUtilities.getAsStringFieldValue(item.getAzione()));
      params.setProperty("CODICE_ADDEBITO", SqlUtilities.getAsStringFieldValue(item.getCodiceAddebito()));
      params.setProperty("CODICE_CLIENTE", SqlUtilities.getAsStringFieldValue(item.getCodiceCliente()));
      params.setProperty("CODICE_EAN", SqlUtilities.getAsStringFieldValue(item.getCodiceEAN()));
      params.setProperty("CODICE_MINSAN", SqlUtilities.getAsStringFieldValue(item.getCodiceMinSan()));
      params.setProperty("CODICE_PRODOTTO", SqlUtilities.getAsStringFieldValue(item.getCodiceProdotto()));
      params.setProperty("CODICE_PRODOTTO_OMAGGIO", SqlUtilities.getAsStringFieldValue(item.getCodiceProdottoOmaggio()));
      params.setProperty("DATA_FINE_VALIDITA", SqlUtilities.calendarToOracleToDate(item.getDataFineValidita()));
      params.setProperty("DATA_INIZIO_VALIDITA", SqlUtilities.calendarToOracleToDate(item.getDataInizioValidita()));
      params.setProperty("DESCR_PRODOTTO", SqlUtilities.getAsStringFieldValue(item.getDescrizioneProdotto()));
      params.setProperty("DESCR_PRODOTTO_OMAGGIO", SqlUtilities.getAsStringFieldValue(item.getDescrizioneProdottoOmaggio()));
      params.setProperty("DILAZIONE_PAGAM_GIORNI", ""+item.getDilazionePagamentoInGiorni());
      params.setProperty("GIORNI_ABBU_CALC_ADD", ""+item.getGiorniAbbuonoPerCalcoloAddebito());
      params.setProperty("PERC_ADDEBITO_AGG", ""+item.getPercentualeAddebitoAggiuntiva());
      params.setProperty("PERC_SC_PRZPUBDR_PRZNETUN", ""+item.getPercScontoPrezzoPubblicoDerivatoVsNettoUnitario());
      params.setProperty("PERC_SC_PRZPUB_PRZNETUN", ""+item.getPercScontoPrezzoPubblicoVsNettoUnitario());
      params.setProperty("PERIODICITA_FATTURAZ", SqlUtilities.getAsStringFieldValue(item.getPeriodicitaFartturazione()));
      params.setProperty("PREZZO_NETTO_UNITARIO", ""+item.getPrezzoNettoUnitario());
      params.setProperty("PREZZO_VEND_LORDO_SCONTI", ""+item.getPrezzoDiVenditaLordoSconti());
      params.setProperty("QUANTITA_ASSEGNATA", ""+item.getQuantitaAssegnata());
      params.setProperty("QUANTITA_MASSIMA", ""+item.getQuantitaMassima());
      params.setProperty("QUANTITA_MINIMA", ""+item.getQuantitaMinima());
      params.setProperty("QUANTITA_OMAGGIO", ""+item.getQuantitaOmaggio());
      params.setProperty("QUANTITA_PRODOTTO_OMAGGIO", ""+item.getQuantitaOmaggio());
      params.setProperty("SCONTO_CASSA", ""+item.getScontoCassa());
      params.setProperty("SEQUENZA", SqlUtilities.getAsStringFieldValue(item.getSequenza()));
      params.setProperty("SOTTOSEQUENZA", ""+item.getSottosequenza());
      switch (item.getAzione()) {
    	case CsvFarmaclickCommons.AZIONE_INSERIMENTO:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertCsvRecordFarmaclickC.sql", params);
    		break;
    	default:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateCsvRecordFarmaclickC.sql", params);
    		break;
      }
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      //throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }

  public static void insert(CsvRecordFarmaclickD item, long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID_FORNITORE", ""+oidFornitore);
      params.setProperty("CODICE_CLIENTE", SqlUtilities.getAsStringFieldValue(item.getCodiceCliente()));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertCsvRecordFarmaclickD.sql", params);
      String lines[] = sql.split("\\r?\\n");
      stmt = conn.createStatement();
      //stmt.executeUpdate(sql);
      for (int j=0;j<lines.length;j++) {
        if (lines[j].length()>0)
    	  stmt.addBatch(lines[j]);
      }
      stmt.executeBatch();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }
  
  public static void store(CsvRecordFarmaclickL item, long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.debug/* compare in un loop, lo metto a livello debug */(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID_FORNITORE", ""+oidFornitore);
      params.setProperty("ALIQUOTA_IVA", ""+item.getAliquotaIVA());
      params.setProperty("ALLINEAM_SCADENZA", SqlUtilities.getAsStringFieldValue(item.getAllineamentoScadenza()));
      params.setProperty("AZIONE", SqlUtilities.getAsStringFieldValue(item.getAzione()));
      params.setProperty("CATEGORIA_TIPO_PRODOTTO", SqlUtilities.getAsStringFieldValue(item.getCategoriaTipoProdotto()));
      params.setProperty("CODICE_ADDEBITO", SqlUtilities.getAsStringFieldValue(item.getCodiceAddebito()));
      params.setProperty("CODICE_CLIENTE", SqlUtilities.getAsStringFieldValue(item.getCodiceCliente()));
      params.setProperty("CODICE_EAN", SqlUtilities.getAsStringFieldValue(item.getCodiceEAN()));
      params.setProperty("CODICE_MINSAN", SqlUtilities.getAsStringFieldValue(item.getCodiceMinSan()));
      params.setProperty("CODICE_PRODOTTO", SqlUtilities.getAsStringFieldValue(item.getCodiceProdotto()));
      params.setProperty("DESCR_PRODOTTO", SqlUtilities.getAsStringFieldValue(item.getDescrizioneProdotto()));
      params.setProperty("DILAZIONE_PAGAM_GIORNI", ""+item.getDilazionePagamentoInGiorni());
      params.setProperty("DILAZ_PAGAM_GIORNI", ""+item.getDilazionePagamentoInGiorni());
      params.setProperty("GIORNI_ABBU_CALC_ADD", ""+item.getGiorniAbbuonoPerCalcoloAddebito());
      params.setProperty("PERC_ADDEBITO_AGG", ""+item.getPercentualeAddebitoAggiuntiva());
      params.setProperty("PERC_SC_PRZPUBDR_PRZNETUN", ""+item.getPercScontoPrezzoPubblicoDerivatoVsNettoUnitario());
      params.setProperty("PERC_SC_PRZPUB_PRZNETUN", ""+item.getPercScontoPrezzoPubblicoVsNettoUnitario());
      params.setProperty("PERIODICITA_FATTURAZ", SqlUtilities.getAsStringFieldValue(item.getPeriodicitaFartturazione()));
      params.setProperty("PREZZO_AL_PUBBLICO", ""+item.getPrezzoAlPubblico());
      params.setProperty("PREZZO_NETTO_UNITARIO", ""+item.getPrezzoNettoUnitario());
      params.setProperty("PREZZO_PUBB_DERIVATO", ""+item.getPrezzoAlPubblicoDeivato());
      params.setProperty("PREZZO_VEND_CONSIG", ""+item.getPrezzoDiVenditaConsigliato());
      params.setProperty("PREZ_VEND_LIST_LORDO_SC", ""+item.getPrezzoDiVenditaAListinoLordoSconti());
      params.setProperty("QUANT_PER_COLLO_VEND", ""+item.getQuantitaPerColloDiVendita());
      params.setProperty("SCONTO_1", ""+item.getSconto1());
      params.setProperty("SCONTO_2", ""+item.getSconto2());
      params.setProperty("SCONTO_CASSA", ""+item.getScontoCassa());
      params.setProperty("SOMMA_SCONTO_1_2", ""+item.getSommaSconto1ESconto2());

      switch (item.getAzione()) {
    	case CsvFarmaclickCommons.AZIONE_INSERIMENTO:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertCsvRecordFarmaclickL.sql", params);
    		break;
    	default:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateCsvRecordFarmaclickL.sql", params);
    		break;
      }
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      //throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }
  

  public static void store(CsvRecordFarmaclickR item, long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.debug/* compare in un loop, lo metto a livello debug */(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID_FORNITORE", ""+oidFornitore);
      params.setProperty("AZIONE", SqlUtilities.getAsStringFieldValue(item.getAzione()));
      params.setProperty("CODICE_CLIENTE", SqlUtilities.getAsStringFieldValue(item.getCodiceCliente()));
      params.setProperty("CODICE_REGGRUPP_CONDIZ", SqlUtilities.getAsStringFieldValue(item.getCodiceRaggruppamentoCondizione()));
      params.setProperty("DESCR_REGGRUPP_CONDIZ", SqlUtilities.getAsStringFieldValue(item.getDescrizioneRaggruppamentoCondizione()));
      params.setProperty("RIGA", SqlUtilities.getAsStringFieldValue(item.getRiga()));
      switch (item.getAzione()) {
    	case CsvFarmaclickCommons.AZIONE_INSERIMENTO:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertCsvRecordFarmaclickR.sql", params);
    		break;
    	default:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateCsvRecordFarmaclickR.sql", params);
    		break;
      }
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      //throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }

  public static void store(CsvRecordFarmaclickV item, long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.debug/* compare in un loop, lo metto a livello debug */(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID_FORNITORE", ""+oidFornitore);
      params.setProperty("AZIONE", SqlUtilities.getAsStringFieldValue(item.getAzione()));
      params.setProperty("CODICE_ADDEBITO", SqlUtilities.getAsStringFieldValue(item.getCodiceAddebito()));
      params.setProperty("CODICE_CLIENTE", SqlUtilities.getAsStringFieldValue(item.getCodiceCliente()));
      params.setProperty("DESCR_ADDEBITO", SqlUtilities.getAsStringFieldValue(item.getDescrizioneAddebito()));
      params.setProperty("DESTINAZIONE_ADDEBITO", SqlUtilities.getAsStringFieldValue(item.getDestinazioneAddebito()));
      params.setProperty("IMORTO_MASSIMO_DA_ADDEB", ""+item.getImportoMassimoDaAddebitare());
      params.setProperty("IMPORTO_DA_ADDEB", ""+item.getImportoDaAddebitare());
      params.setProperty("NUMERO_MASSIMO_ADDEB", ""+item.getNumeroMassimiAddebiti());
      params.setProperty("PERC_ADDEB_SU_IMPONIBILE", ""+item.getPercentualeDiAddebitoSuImponibile());
      params.setProperty("PERIODICITA_FATTURAZ", item.getPeriodicitaFartturazione());
      switch (item.getAzione()) {
    	case CsvFarmaclickCommons.AZIONE_INSERIMENTO:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertCsvRecordFarmaclickV.sql", params);
    		break;
    	default:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateCsvRecordFarmaclickV.sql", params);
    		break;
      }
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      //throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }

  public static void store(CsvRecordFarmaclickZ item, long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.debug/* compare in un loop, lo metto a livello debug */(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID_FORNITORE", ""+oidFornitore);
      params.setProperty("AZIONE", SqlUtilities.getAsStringFieldValue(item.getAzione()));
      params.setProperty("CODICE_CLIENTE", SqlUtilities.getAsStringFieldValue(item.getCodiceCliente()));
      params.setProperty("DATA_ORA_GENERAZ", SqlUtilities.calendarToOracleToDate(item.getDataOraGenerazione()));
      switch (item.getAzione()) {
    	case CsvFarmaclickCommons.AZIONE_INSERIMENTO:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertCsvRecordFarmaclickZ.sql", params);
    		break;
    	default:
          sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateCsvRecordFarmaclickZ.sql", params);
    		break;
      }
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      //throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }
  
  public static void setFornitoreAllDataStoredTs(long oidFornitore, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("OID", ""+oidFornitore);
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setFornitoreAllDataStoredTs.sql", params);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
  }

  public static void setAllTablesRecordsActionFromNoLongerExistingFornitore(String actionValue, Connection conn) throws Exception {
    List<String> tableNames = new ArrayList<String>();
    tableNames.add("FAI_RECORD_A_ADDEBITO_FINANZ");
    tableNames.add("FAI_RECORD_C_CONDIZIONI");
    tableNames.add("FAI_RECORD_D_RESET");
    tableNames.add("FAI_RECORD_L_LISTINO");
    tableNames.add("FAI_RECORD_R_RAGGR_COND");
    tableNames.add("FAI_RECORD_V_RAGGR_COND");
    tableNames.add("FAI_RECORD_Z_CHIUSURA");
    for (String tableName : tableNames) {
      setAllTableRecordsActionFromNoLongerExistingFornitore(tableName, actionValue, conn);
    }
  }

  public static void setAllTableRecordsActionFromNoLongerExistingFornitore(String tableName, String actionValue, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("TABLE_NAME", tableName);
      params.setProperty("ACTION", SqlUtilities.getAsStringFieldValue(actionValue));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setAllTableRecordsActionFromNoLongerExistingFornitore.sql", params);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }

  }
  
  
  

}
