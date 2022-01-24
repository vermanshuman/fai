package fai.exp.oracloud.task;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import fai.common.util.Filesystem;
import fai.exp.oracloud.db.FaiColumnExport;
import fai.exp.oracloud.db.FaiGroupExport;
import fai.exp.oracloud.db.FaiTableExport;
import fai.exp.oracloud.db.OracleSql;
import fai.exp.oracloud.db.SqlQueries;

public class RecordsDataImporter extends AbstractDataIO{
  
  static Logger logger = Logger.getLogger(RecordsDataImporter.class);
  
  
  private List<FaiGroupExport> groupExportList = null;

  
  
  
  public RecordsDataImporter(Calendar nowReference, Connection conn) {
    super(nowReference, conn);
  }

  private String getImportSqlStatement(FaiGroupExport group, FaiTableExport table) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + group.getGroupNameExport()+", "+table.getTableNameExport()+ ": ";
    String sql = null;
    String file = table.getSqlQueryInFile();
    if (file != null && !"".equals(file.trim())) {
      logger.info(LOG_PREFIX+" recupero dell'sql di import da file ..."); 
      sql = Filesystem.getContentAsText(new FileInputStream(file), true);
    }
    else if (table.getSqlQueryIn() != null) {
      logger.info(LOG_PREFIX+" recupero dell'sql di import da CLOB ..."); 
      sql = table.getSqlQueryIn();
    }
    else {
      logger.info(LOG_PREFIX+" recupero dell'sql di import da risorsa di default ..."); 
      sql = SqlQueries.getDefaultTableExportSqlQueryIn(group.getGroupNameExport(), table.getTableNameExport());
    }
    return sql;
  }
  
  
  public void doJob() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    groupExportList = SqlQueries.findAllFaiGroupExport(conn);
    OracleSql oracleSql = new OracleSql();
    for (FaiGroupExport group : groupExportList) {
      logger.info(LOG_PREFIX+group.getGroupNameExport()+", ..."); 
      List<FaiTableExport> tables = group.getTables();
      for (FaiTableExport table : tables) {
        //
        logger.info(LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+" ...");
        if (isImportExportEnabled(table) == false) {
          logger.info(LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+"; import/export non abilitato per questa tabella");
          continue;
        }
        //
        oracleSql.setTable(table);
        System.err.println(table.getTableNameOracle());
        for (int i = 0; i < table.getColumns().size(); i++) {
          System.err.println("    "+i+") "+table.getColumns().get(i).getColumnNameOracle());
        }
        
        //
        int colIdx = table.getMaxColumnIndex();
        FaiColumnExport colVersionNumber = new FaiColumnExport();
        colVersionNumber.setColumnIdx(++colIdx);
        colVersionNumber.setColumnNameExport(null);
        colVersionNumber.setColumnNameOracle("VERSION_NUMBER");
        colVersionNumber.setSqlType("INTEGER");
        table.addColumn(colVersionNumber);
        //
        FaiColumnExport colCreationTs = new FaiColumnExport();
        colCreationTs.setColumnIdx(++colIdx);
        colCreationTs.setColumnNameExport(null);
        colCreationTs.setColumnNameOracle("RECORD_CREATION_TS");
        colCreationTs.setSqlType("DATE");
        colCreationTs.setDefaultValue("SYSDATE");
        table.addColumn(colCreationTs);
        //
        String createTable = oracleSql.getCreateTable();
        if (!createTable.equals(table.getSqlDDL())) {
          logger.info(LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+"; drop e nuova creazione della tabella "+table.getTableNameOracle()+" perché inesistente o modificata...");
          logger.debug(LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+"; CREATE TABLE utilizzata per crare la tabella "+table.getTableNameOracle()+" al momento in banca dati: "+table.getSqlDDL());
          logger.debug(LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+"; CREATE TABLE richiesta per la creazione della tabella"+table.getTableNameOracle()+"                 : "+createTable);
          if (SqlQueries.tableExists(table.getTableNameOracle(), conn)) {
            SqlQueries.executeUpdate("DROP TABLE "+table.getTableNameOracle()+" CASCADE CONSTRAINTS", conn);
          }
          SqlQueries.executeUpdate(createTable, conn);
          SqlQueries.updateFaiTableExportSqlDDL(table.getOid(), createTable, conn);
          conn.commit();
          table.setSqlDDL(createTable);
        }
        //
        String insertIntoSelect = getImportSqlStatement(group, table);
        if (insertIntoSelect == null) {
          String msg = LOG_PREFIX+group.getGroupNameExport()+", "+table.getTableNameExport()+"; sql per import nella tabella "+table.getTableNameOracle()+" non trovato";
          logger.error(msg);
          throw new IllegalStateException(msg);
        }
        //
        table.removeColumnByOracleName(colVersionNumber.getColumnNameOracle());
        table.removeColumnByOracleName(colCreationTs.getColumnNameOracle());
        SqlQueries.executeUpdate(insertIntoSelect, conn);
        //
        Integer versionNumber = SqlQueries.getLastVersionNumber(table.getTableNameOracle(), conn);
        versionNumber = versionNumber == null ? 1 : versionNumber + 1;
        SqlQueries.executeUpdate("UPDATE "+table.getTableNameOracle()+" SET VERSION_NUMBER = "+versionNumber+" WHERE VERSION_NUMBER IS NULL", conn);
      }
    }
    conn.commit();
  }
  
  

}
