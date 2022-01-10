package fai.common.db;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;

import fai.common.models.GenericTaskConfig;
import fai.common.models.Property;
import fai.common.util.ExceptionsTool;
import it.swdes.database.SqlStatementManager;


public class SqlQueries {
  
  static Logger logger = Logger.getLogger(SqlQueries.class);

  static final String SQL_RESOURCE_PATH = "/fai/common/db/resources/sql/";
  
  public static long getOidNextVal(Connection conn) throws Exception {    
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    long retval = 0;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getOidNextVal.sql");
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      rs.next();
      retval = rs.getLong(1);
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
    return retval;
  }


  public static long getNextFaiNoGapSequence(String acronym, Connection conn) throws Exception {
    incrementFaiNoGapSequence(acronym, conn);
    return getLastFaiNoGapSequence(acronym, conn);
  }

  
  public static Long getLastFaiNoGapSequence(String acronym, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    Long noGapSeqValue = null;
    try {
      Properties params = new Properties();
      params.setProperty("ACRONYM", SqlUtilities.getAsStringFieldValue(acronym));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getLastFaiNoGapSequence.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        noGapSeqValue = rs.getLong("LAST_VALUE");
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
    return noGapSeqValue;
  }
  
  protected static void  incrementFaiNoGapSequence(String acronym, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("ACRONYM", SqlUtilities.getAsStringFieldValue(acronym));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "incrementFaiNoGapSequence.sql", params);
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
    }

  }

  public static void deleteGenericTaskConfigProperty(String acronym, String propertyName, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("KEY", SqlUtilities.getAsStringFieldValueWC(propertyName));
      params.setProperty("ACRONYM", SqlUtilities.getAsStringFieldValueWC(acronym));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "deleteGenericTaskConfigProperty.sql", params);
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
    }
  }


  public static void setGenericTaskConfigProperty(String acronym, String propertyName, String propertyValue, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    if (propertyValue == null) {
      deleteGenericTaskConfigProperty(acronym, propertyName, conn);
      return;
    }
    String sql = null;
    PreparedStatement stmt = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setGenericTaskConfigProperty.sql");
      stmt = conn.prepareStatement(sql);
      int col = 0;
      stmt.setString(++col, propertyName);
      SqlUtilities.setStringAsClob(stmt, ++col, propertyValue);
      stmt.setString(++col, acronym);
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

  public static String getGenericTaskConfigClassName(String acronym, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    GenericTaskConfig cfg  =getGenericTaskConfig(acronym, conn);
    return cfg == null ? null : cfg.getImplClassName();
    
  }

  public static GenericTaskConfig getGenericTaskConfig(String acronym, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    GenericTaskConfig cfg = null;
    try {
      Properties params = new Properties();
      params.setProperty("ACRONYM", SqlUtilities.getAsStringFieldValueWC(acronym));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getGenericTaskConfig.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        if (cfg == null) {
          cfg = new GenericTaskConfig();
          cfg.setOid(rs.getLong("GENTASK_OID"));
          cfg.setAcronym(rs.getString("GENTASK_ACRONYM"));
          cfg.setDescr(rs.getString("GENTASK_DESCR"));
          cfg.setImplClassName(rs.getString("GENTASK_CLASS_NAME"));
          cfg.setScheduledTimes(rs.getString("GENTASK_SCHEDULED_TIMES"));
          cfg.setScheduledSmtwtfs(rs.getString("GENTASK_SCHEDULED_SMTWTFS"));
          cfg.setLastRunStartTs(SqlUtilities.getCalendar(rs, "GENTASK_LAST_RUN_START_TS"));
          cfg.setLastRunEndTs(SqlUtilities.getCalendar(rs, "GENTASK_LAST_RUN_END_TS"));
          cfg.setLastRunDone(SqlUtilities.asBoolean(rs.getString("GENTASK_LAST_RUN_DONE")));
          cfg.setLastRunDescr(rs.getString("GENTASK_LAST_RUN_DESCR"));
          cfg.setEnabled(SqlUtilities.asBoolean(rs.getString("GENTASK_ENABLED")));
        }
        long propertyOid = rs.getLong("PROP_OID");
        if (!rs.wasNull() ) {
          Property p = new Property();
          p.setOid(propertyOid);
          p.setKey(rs.getString("PROP_KEY"));
          p.setValue(rs.getString("PROP_VALUE"));
          cfg.addProperty(p);
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
    return cfg;
  }

  
  public static void setGenericTaskSessionCompleted(long oidConfig, boolean success, String lastRunDescr, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setGenericTaskSessionCompleted.sql");
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, success ? "S" : "N");
      SqlUtilities.setStringAsClob(stmt, 2, lastRunDescr);
      stmt.setLong(3, oidConfig);
      stmt.executeUpdate();
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



  public static void setGenericTaskNewSession(long oidConfig, Connection conn) throws Exception {
    final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String sql = null;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("LAST_RUN_START_TS", SqlUtilities.calendarToOracleToDate(Calendar.getInstance()));
      params.setProperty("OID", ""+oidConfig);
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "setGenericTaskNewSession.sql", params);
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
    }
  }  


  public static void executeUpdate(String sql, Connection conn) throws Exception {
    executeUpdate(sql, new Properties(), conn);
  }

  public static void executeUpdate(String sql, Properties params, Connection conn) throws Exception {
    final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    Statement stmt = null;
    try {
      sql = SqlStatementManager.getSqlStatement(new ByteArrayInputStream(sql.getBytes(StandardCharsets.UTF_8)), params, "stringa runtime");
      logger.debug("Risorsa runtime, statement SQL (con parametri valorizzati):" + System.getProperty("line.separator") + sql);
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
    }
  }
  
  public static int executeCount(String sql, Connection conn) throws Exception {
    return executeCount(sql, new Properties(), conn);
  }

  public static int executeCount(String sql, Properties params, Connection conn) throws Exception {
    final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    int count = 0;
    ResultSet rs = null;
    Statement stmt = null;
    try {
      sql = SqlStatementManager.getSqlStatement(new ByteArrayInputStream(sql.getBytes(StandardCharsets.UTF_8)), params, "stringa runtime");
      logger.debug("Risorsa runtime, statement SQL (con parametri valorizzati):" + System.getProperty("line.separator") + sql);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      rs.next();
      count = rs.getInt(1);
      return count;
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

  public static void logTrace(String message, String techMessage, Throwable throwable, Connection conn) throws Exception {
    log("T", message, techMessage, throwable, conn);
  }

  public static void logDebug(String message, String techMessage, Throwable throwable, Connection conn) throws Exception {
    log("D", message, techMessage, throwable, conn);
  }

  public static String logInfo(String message, String techMessage, Throwable throwable, Connection conn) throws Exception {
    return log("I", message, techMessage, throwable, conn);
  }
  
  public static String logWarn(String message, String techMessage, Throwable throwable, Connection conn) throws Exception {
    return log("W", message, techMessage, throwable, conn);
  }

  public static String logError(String message, String techMessage, Throwable throwable, Connection conn) throws Exception {
    return log("E", message, techMessage, throwable, conn);
  }
  
  public static String logFatal(String message, String techMessage, Throwable throwable, Connection conn) throws Exception {
    return log("F", message, techMessage, throwable, conn);
  }  
  
  public static String log(String level, String message, String techMessage, Throwable throwable, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String sql = null;
    PreparedStatement stmt = null;
    try {
      if (level.length() > 20) level = level.substring(level.length()-20, level.length());
      //
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertLog.sql");
      stmt = conn.prepareStatement(sql);
      int col = 0;
      SqlUtilities.setString(stmt, ++col, level);
      SqlUtilities.setString(stmt, ++col, Thread.currentThread().getName());
      SqlUtilities.setStringAsClob(stmt, ++col, message);
      SqlUtilities.setStringAsClob(stmt, ++col, techMessage);
      SqlUtilities.setStringAsClob(stmt, ++col, throwable == null ? null : ExceptionsTool.getStackTraceAsString(throwable));
      stmt.executeUpdate();
      return message;
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
  
  


}
