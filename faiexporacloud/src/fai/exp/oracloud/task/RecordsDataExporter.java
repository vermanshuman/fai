package fai.exp.oracloud.task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.attribute.GroupPrincipal;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import fai.common.ftp.Ftp;
import fai.common.ftp.FtpFactory;
import fai.common.util.Filesystem;
import fai.exp.oracloud.db.FaiGroupExport;
import fai.exp.oracloud.db.FaiTableExport;
import fai.exp.oracloud.db.OracleSql;
import fai.exp.oracloud.db.SqlQueries;
import fai.exp.oracloud.models.FaiExportConfig;

public class RecordsDataExporter extends AbstractDataIO {
  
  
  static Logger logger = Logger.getLogger(RecordsDataExporter.class);
  
  
  private List<FaiGroupExport> groupExportList = null;
  private FaiExportConfig config = null;


  public RecordsDataExporter(Calendar nowReference, FaiExportConfig config, Connection conn) {
    super(nowReference, conn);
    this.config = config;
  }


  private String getExportSqlStatement(FaiGroupExport group, FaiTableExport table) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + group.getGroupNameExport()+", "+table.getTableNameExport()+ ": ";
    String sql = null;
    String file = table.getSqlQueryOutFile();
    if (file != null && !"".equals(file.trim())) {
      logger.info(LOG_PREFIX+" recupero dell'sql di export da file ..."); 
      sql = Filesystem.getContentAsText(new FileInputStream(file), true);
    }
    else if (table.getSqlQueryOut() != null) {
      logger.info(LOG_PREFIX+" recupero dell'sql di export da CLOB ..."); 
      sql = table.getSqlQueryIn();
    }
    else {
      logger.info(LOG_PREFIX+" recupero dell'sql di export da risorsa di default ..."); 
      sql = SqlQueries.getDefaultTableExportSqlQueryOut(group.getGroupNameExport(), table.getTableNameExport());
    }
    return sql;
  }
  
  
  @Override
  public void doJob() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    groupExportList = SqlQueries.findAllFaiGroupExport(conn);
    Ftp ftp = null;
    ZipOutputStream zipOutputStream = null;
    String zipOutputStreamFilename = null;
    try {
      for (FaiGroupExport group : groupExportList) {
        logger.info(LOG_PREFIX+group.getGroupNameExport()+", ..."); 
        ftp = FtpFactory.newFtp(config);
        zipOutputStreamFilename = group.getGroupNameExport()+".zip";
        OutputStream ftpOutputStream = ftp.getOutputStream(zipOutputStreamFilename);
        zipOutputStream = new ZipOutputStream(ftpOutputStream);
        List<FaiTableExport> tables = group.getTables();
        for (FaiTableExport table : tables) {
          //
          logger.info(LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+" ...");
          if (isImportExportEnabled(table) == false) {
            logger.info(LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+"; import/export non abilitato per questa tabella");
            continue;
          }
          ZipEntry zipEntry = new ZipEntry(table.getTableNameExport()+".csv");
          zipOutputStream.putNextEntry(zipEntry);
          //
          Integer lastVersion = SqlQueries.getLastVersionNumber(table.getTableNameOracle(), conn);
          Integer prevVersion = SqlQueries.getPrevVersionNumber(table.getTableNameOracle(), conn);
          //
          String sql = getExportSqlStatement(group, table);
          if (sql == null) {
            String msg = LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+"; sql per import nella tabella "+table.getTableNameOracle()+" non trovato";
            logger.error(msg);
            throw new IllegalStateException(msg);
          }
          //
          CsvExporter csvExporter = new CsvExporter(sql, table, lastVersion, prevVersion, conn);
          csvExporter.doExport(zipOutputStream);
        }
        zipOutputStream.close();
        ftp.disconnect();
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME ;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      Filesystem.closeWithoutThrowingException(zipOutputStream, zipOutputStreamFilename);
      disconnectWithoutThrowingException(ftp);
    }
  }
  
  
  public static void disconnectWithoutThrowingException(Ftp ftp) {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    if (ftp == null) return;
    try {
      ftp.disconnect();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME+"; disconnessione dell'istanza di "+ftp.getClass().getName()+" non riuscita";
      logger.error(msg);
    }
  }

  


}

