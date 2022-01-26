package fai.imp.base.db;

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
import fai.imp.base.models.FaiImportConfig;

public class SqlQueries {
  
  
  static Logger logger = Logger.getLogger(SqlQueries.class);

  static final String SQL_RESOURCE_PATH = "/fai/imp/base/db/resources/sql/";
  
  
  
  private static Boolean asBoolean(String s) {
    if (s == null) return null;
    //
    if ("S".equals(s)) {
      return true;
    }
    else if ("N".equals(s)) {
      return false;
    }
    else {
      throw new IllegalStateException("inammissibile, impossibile ricondurre la stringa \""+s+"\" ad un valore booleano");
    }
  }
  

  
  public static void updateStatusSessionCompleted(long oidConfig, String lastRunType, boolean success, String lastRunDescr, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      Properties params = new Properties();
      params.setProperty("LAST_RUN_TYPE", SqlUtilities.getAsStringFieldValue(lastRunType));
      params.setProperty("LAST_RUN_DONE", success ? "'S'" : "'N'");
      params.setProperty("LAST_RUN_DESCR", SqlUtilities.getAsStringFieldValue(lastRunDescr));
      params.setProperty("OID", ""+oidConfig);
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateStatusSessionCompleted.sql", params);
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

  public static FaiImportConfig getFaiImportConfig(String acronym, Connection conn) throws Exception {
    List<FaiImportConfig> list = findFaiImportConfig(acronym, null, conn);
    if (list.size() == 0)
      return null;
    else if (list.size() == 1)
      return list.get(0);
    else
      throw new IllegalStateException("inammisisbile, trovati "+FaiImportConfig.class.getName()+" per ACRONYM "+acronym+"; attesi: 0 oppure 1");
  }

  public static FaiImportConfig getFaiImportConfig(long oidConfig, Connection conn) throws Exception {
    List<FaiImportConfig> list = findFaiImportConfig(null, oidConfig, conn);
    if (list.size() == 0)
      return null;
    else if (list.size() == 1)
      return list.get(0);
    else
      throw new IllegalStateException("inammisisbile, trovati "+FaiImportConfig.class.getName()+" per OID "+oidConfig+"; attesi: 0 oppure 1");
  }

  public static FaiImportConfig getFaiImportConfig(String acronym, String customQuery, Connection conn) throws Exception {
    List<FaiImportConfig> list = findFaiImportConfig(acronym, null, customQuery, conn);
    if (list.size() == 0)
      return null;
    else if (list.size() == 1)
      return list.get(0);
    else
      throw new IllegalStateException("inammisisbile, trovati "+FaiImportConfig.class.getName()+" per ACRONYM "+acronym+"; attesi: 0 oppure 1");
  }

  public static List<FaiImportConfig> findAllFaiImportConfig(Connection conn) throws Exception {
    return findFaiImportConfig(null, null, conn);
  }

  private static List<FaiImportConfig> findFaiImportConfig(String acronym, Long oidConfig, Connection conn) throws Exception {
    return findFaiImportConfig(acronym, oidConfig, null, conn);
  }

  private static List<FaiImportConfig> findFaiImportConfig(String acronym, Long oidConfig,
                                                           String customQuery, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<FaiImportConfig> list = new ArrayList<FaiImportConfig>();
    try {
      String wc = "";
      if (acronym != null || oidConfig != null) {
        wc = "WHERE 1=1";
        if (acronym != null) wc += " AND ACRONYM = "+SqlUtilities.getAsStringFieldValue(acronym);
        if (oidConfig != null) wc += " AND OID = "+oidConfig;
        if(customQuery != null) wc +=  customQuery;
      }
      Properties params = new Properties();
      params.setProperty("whereCondition", wc);
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "findFaiImportConfig.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        FaiImportConfig cfg = new FaiImportConfig();
        cfg.setOid(rs.getLong("OID"));
        cfg.setAcronym(rs.getString("ACRONYM"));
        cfg.setDescr(rs.getString("DESCR"));
        cfg.setImplClassName(rs.getString("CLASS_NAME"));
        cfg.setServiceLogin(rs.getString("SERVICE_LOGIN"));
        cfg.setServicePass(rs.getString("SERVICE_PASS"));
        cfg.setServicePassEncr("S".equals(rs.getString("SERVICE_PASS_ENCR")));
        cfg.setServiceQueryTime(SqlUtilities.getCalendar(rs, "SERVICE_QUERY_TIME"));
        cfg.setServiceQuerySMTWTFS(rs.getString("SERVICE_QUERY_SMTWTFS"));
        cfg.setServiceQueryUrl(rs.getString("SERVICE_QUERY_URL"));
        cfg.setServiceQureyZippedContent("S".equals(rs.getString("SERVICE_QUERY_ZIPPED_CONTENT")));
        cfg.setServiceResumePrevSession(rs.getString("SERVICE_RESUME_PREV_SESS"));
        cfg.setLastRunStartTs(SqlUtilities.getCalendar(rs, "LAST_RUN_START_TS"));
        cfg.setLastRunEndTs(SqlUtilities.getCalendar(rs, "LAST_RUN_END_TS"));
        cfg.setLastRunType(rs.getString("LAST_RUN_TYPE"));
        cfg.setLastRunDone("S".equals(rs.getString("LAST_RUN_DONE")));
        cfg.setLastRunDescr(rs.getString("LAST_RUN_DESCR"));
        cfg.setServicePassSecondLevel(rs.getString("SERVICE_PASS_SECOND_LEVEL"));
        cfg.setServiceApiLevel(rs.getString("SERVICE_API_LEVEL"));
        list.add(cfg);
      }
    }
    catch (Throwable th) {
      list = new ArrayList<FaiImportConfig>();
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

  public static void updateStatusNewSession(long oidConfig, String lastRunType, Connection conn) throws Exception {
    final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String sql;
    Statement stmt = null;
    try {
      Properties params = new Properties();
      params.setProperty("LAST_RUN_TYPE", SqlUtilities.getAsStringFieldValue(lastRunType));
      params.setProperty("LAST_RUN_START_TS", SqlUtilities.calendarToOracleToDate(Calendar.getInstance()));
      params.setProperty("OID", ""+oidConfig);
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateStatusNewSession.sql", params);
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
