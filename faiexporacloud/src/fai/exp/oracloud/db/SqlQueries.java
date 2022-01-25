package fai.exp.oracloud.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import java.util.Properties;

import fai.common.db.SqlUtilities;
import fai.exp.oracloud.models.FaiExportConfig;

public class SqlQueries {

  
  static Logger logger = Logger.getLogger(SqlQueries.class);

  
  static final String SQL_RESOURCE_PATH = "/fai/exp/oracloud/db/resources/sql/";

  public static List<FaiGroupExport> findAllFaiGroupExport(Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<FaiGroupExport> list = new LinkedList<FaiGroupExport>();
    try {
      Properties params = new Properties();
      params.setProperty("chiave", "valore");
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "findAllFaiGroupExport.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      FaiGroupExport grp = null;
      Long grpOidBak = null;
      FaiTableExport tab = null;
      Long tabOidBak = null;
      FaiColumnExport col = null;
      while (rs.next()) {
        //
        // --- FAI_GROUP_EXPORT ---
        //
        long grpOid = rs.getLong("GRP_OID");
        if (grpOidBak == null || grpOidBak.longValue() != grpOid) {
          grp = new FaiGroupExport();
          list.add(grp);
          grp.setGroupIdx(rs.getInt("GRP_GROUP_IDX"));
          grp.setGroupNameExport(rs.getString("GRP_GROUP_NAME_EXPORT"));
        }
        grpOidBak = grpOid;
        //
        // --- FAI_TABLE_EXPORT ---
        //
        long tabOid = rs.getLong("TAB_OID");
        if (tabOidBak == null || tabOidBak.longValue() != tabOid) {
          tab = new FaiTableExport();
          grp.addTable(tab);
          tab.setOid(tabOid);
          tab.setTableIdx(rs.getInt("TAB_TABLE_IDX"));
          tab.setTableNameOracle(rs.getString("TAB_TABLE_NAME_ORACLE"));
          tab.setTableNameExport(rs.getString("TAB_TABLE_NAME_EXPORT"));
          tab.setSmtwtfsExportPattern(rs.getString("TAB_SMTWTFS_EXPORT_PATTERN"));
          tab.setSqlQueryIn(SqlUtilities.getClobAsString(rs, "TAB_SQL_QUERY_IN"));
          tab.setSqlQueryInFile(rs.getString("TAB_SQL_QUERY_IN_FILE"));
          tab.setSqlQueryOut(SqlUtilities.getClobAsString(rs, "TAB_SQL_QUERY_OUT"));
          tab.setSqlQueryOutFile(rs.getString("TAB_SQL_QUERY_OUT_FILE"));
          tab.setSqlDDL(SqlUtilities.getClobAsString(rs, "TAB_SQL_DDL"));
        }
        tabOidBak = tabOid;
        //
        // --- FAI_COLUMN_EXPORT ---
        //
        col = new FaiColumnExport();
        tab.addColumn(col);
        col.setOid(rs.getLong("COL_OID"));
        col.setColumnIdx(rs.getInt("COL_COLUMN_IDX"));
        col.setColumnNameOracle(rs.getString("COL_COLUMN_NAME_ORACLE"));
        col.setColumnNameExport(rs.getString("COL_COLUMN_NAME_EXPORT"));
        col.setRequired("S".equals(rs.getString("COL_REQUIRED")));
        col.setSqlType(rs.getString("COL_SQL_TYPE"));
        col.setSqlTypeLen(rs.getInt("COL_SQL_TYPE_LEN"));
        col.setSqlTypeScale(rs.getInt("COL_SQL_TYPE_SCALE"));
        col.setDateFormatPattern(rs.getString("COL_DATE_FORMAT_PATTERN"));
        col.setNumbFormatPattern(rs.getString("COL_NUMB_FORMAT_PATTERN"));
        col.setNumbFormatGrpCh(rs.getString("COL_NUMB_FORMAT_GRPCH"));
        col.setNumbFormatDecCh(rs.getString("COL_NUMB_FORMAT_DECCH"));
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
    return list;
  }
  
  public static void executeUpdate(String sql, Connection conn) throws Exception {
    executeUpdate(sql, true, conn);
  }

  private static void executeUpdate(String sql, boolean logStatement, Connection conn) throws Exception {
    final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    Statement stmt = null;
    ResultSet rs = null;
    try {
      logger.debug("Statement SQL (con parametri valorizzati):" + System.getProperty("line.separator") + sql);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
  }

  public static void updateFaiTableExportSqlDDL(long oid, String ddl, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    PreparedStatement stmt = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateFaiTableExportSqlDDL.sql");
      stmt = conn.prepareStatement(sql);
      SqlUtilities.setStringAsClob(stmt, 1, ddl);
      stmt.setLong(2, oid);
      stmt.executeUpdate();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
    }
    
  }
  
  public static String getDefaultTableExportSqlQueryIn(String groupNameExport, String tableNameExport) throws Exception {
    return getDefaultTableExportSqlQuery("In", groupNameExport, tableNameExport);
  }
  
  public static String getDefaultTableExportSqlQueryOut(String groupNameExport, String tableNameExport) throws Exception {
    return getDefaultTableExportSqlQuery("Out", groupNameExport, tableNameExport);
  }
  
  private static String getDefaultTableExportSqlQuery(String inOrOut, String groupNameExport, String tableNameExport) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String resourceName = "defaultSqlQuery"+inOrOut+"-"+groupNameExport+"-"+tableNameExport+".sql";
    final String LOG_PREFIX = METH_NAME + ": risorsa "+resourceName;
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, resourceName);
      logger.info(LOG_PREFIX + " trovata");
    }
    catch (Throwable th) {
      sql = null;
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.debug(LOG_PREFIX +msg);
      logger.info(LOG_PREFIX + " non trovata");
    }
    return sql;
  }
  
  public static Integer getLastVersionNumber(String tableName, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    Integer lastVersionNumber = null;
    try {
      sql = "SELECT MAX(VERSION_NUMBER) FROM "+tableName;
      logger.debug(METH_NAME+"Statement SQL (con parametri valorizzati):" + System.getProperty("line.separator") + sql);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        lastVersionNumber = rs.getInt(1);
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
    return lastVersionNumber;

  }
  
  public static Integer getPrevVersionNumber(String tableName, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    Integer prevVersionNumber = null;
    try {
      sql = "SELECT VERSION_NUMBER FROM "+tableName+" ORDER BY VERSION_NUMBER DESC";
      logger.debug(METH_NAME+"Statement SQL (con parametri valorizzati):" + System.getProperty("line.separator") + sql);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        if (rs.next()) {
          prevVersionNumber = rs.getInt(1);
        }
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
    return prevVersionNumber;

  }
  
  
  public static boolean tableExists(String tableName, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    boolean exists = false;
    try {
      Properties params = new Properties();
      params.setProperty("TABLE_NAME", SqlUtilities.getAsStringFieldValue(tableName));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "findTableByName.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        exists = true;
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
    return exists;
    
  }
  
  
  public static FaiExportConfig getFaiExportConfig(Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    FaiExportConfig config = null;
    try {
      Properties params = new Properties();
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getFaiExportConfig.sql");
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        config = new FaiExportConfig();
        config.setFtpProtocol(rs.getString("FTP_PROTOCOL"));
        config.setFtpHost(rs.getString("FTP_HOST"));
        config.setFtpPort(rs.getInt("FTP_PORT"));
        config.setFtpDir(rs.getString("FTP_DIR"));
        config.setFtpLogin(rs.getString("FTP_LOGIN"));
        config.setFtpPassword(rs.getString("FTP_PASSWORD"));
        config.setFtpPasswordEncr("S".equals(rs.getString("FTP_PASSWORD_ENCR")));
        config.setExportTime(SqlUtilities.getCalendar(rs, "EXPORT_TIME"));
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
    return config;

  }

  
}
