package fai.imp.farmadati.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.datacontract.schemas._2004._07.FDIWebServices.GetDataSet_Output;
import org.datacontract.schemas._2004._07.FDIWebServices.GetEnabledDataSet_Output;
import org.datacontract.schemas._2004._07.FDIWebServices.GetSchemaDataSet_Output;

import fai.common.db.SqlUtilities;
import fai.common.util.AxisSerializationUtil;
import fai.imp.farmadati.ws.FarmadatiSoapWS;
import fai.imp.farmadati.ws.FarmadatiSoapWSMethFormatter;

public class SqlQueries {
  
  
  
  public static Logger logger = Logger.getLogger(SqlQueries.class);

  static final String SQL_RESOURCE_PATH = "/fai/imp/farmadati/db/resources/sql/";
 
  public static void setAllFarmadatiDdlAsNoLongerExists(Connection conn) throws Exception {
    final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    excecuteNoParamsSql("setAllFarmadatiDdlAsNoLongerExists.sql", conn);
  }
  
  private static void excecuteNoParamsSql(String sqlResource, Connection conn) throws Exception {
    final String METH_NAME = new Object() {    }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, sqlResource);
      executeUpdate(sql, false, conn);
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
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

  public static void deleteAllFarmadatiWS(Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    executeUpdate("DELETE FROM FAI_FARMADATI_WS", true, conn);
  }
  
  public static void deleteAllFailedFarmadatiWS(Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    executeUpdate("DELETE FROM FAI_FARMADATI_WS WHERE RESULT_SUCCESS = 'N'", true, conn);
  }
  
  public static String getCreateTableByTableName(String tableName, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    String sqlCreate = null;
    try {
      Properties params = new Properties();
      params.setProperty("TABLE_NAME", SqlUtilities.getAsStringFieldValueWC(tableName));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getCreateTableByTableName.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        sqlCreate = SqlUtilities.getClobAsString(rs, "SQL_CREATE");
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
    return sqlCreate;

  }
   
  public static GetEnabledDataSet_Output searchGetEnabledDataSet(Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String xml = searchFarmadatiWS("GetEnabledDataSet", null, conn);
    return (GetEnabledDataSet_Output)AxisSerializationUtil.deserializeAxisObject(GetEnabledDataSet_Output.class, xml);
  }
  
  public static void insertGetEnabledDataSet(GetEnabledDataSet_Output object, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String xml = AxisSerializationUtil.serializeAxisObject(object);
    insertFarmadatiWS("GetEnabledDataSet", null, xml, "OK".equals(object.getCodEsito()), object.getDescEsito(), conn); 
  }
  
  
  public static GetSchemaDataSet_Output searchGetSchemaDataSet(String key, boolean schemaVar, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String xml = searchFarmadatiWS("GetSchemaDataSet", FarmadatiSoapWSMethFormatter.formatGetSchemaDataSet(key, schemaVar, true), conn);
    return (GetSchemaDataSet_Output)AxisSerializationUtil.deserializeAxisObject(GetSchemaDataSet_Output.class, xml);
  }
  
  public static void insertGetSchemaDataSet(GetSchemaDataSet_Output object, String key, boolean schemaVar, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String xml = AxisSerializationUtil.serializeAxisObject(object);
    insertFarmadatiWS("GetSchemaDataSet", FarmadatiSoapWSMethFormatter.formatGetSchemaDataSet(key, schemaVar, true),xml, "OK".equals(object.getCodEsito()), object.getDescEsito(), conn); 
  }
  
  public static GetDataSet_Output searchGetDataSet(String key, FarmadatiSoapWS.GetDataSetModalita modalita, int pag, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String xml = searchFarmadatiWS("GetDataSet", FarmadatiSoapWSMethFormatter.formatGetDataSet(key, modalita, pag, true), conn);
    return (GetDataSet_Output)AxisSerializationUtil.deserializeAxisObject(GetDataSet_Output.class, xml);
  }
  
  public static void insertGetDataSet(GetDataSet_Output object, String key, FarmadatiSoapWS.GetDataSetModalita modalita, int pag, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String xml = AxisSerializationUtil.serializeAxisObject(object);
    insertFarmadatiWS("GetDataSet", FarmadatiSoapWSMethFormatter.formatGetDataSet(key, modalita, pag, true),xml, "OK".equals(object.getCodEsito()), object.getDescEsito(), conn); 
  }
  

  public static Calendar getLastRunStartTs(Connection conn) throws Exception {
    final String METH_NAME = new Object() {
    }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    Calendar lastRunStartTs = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getFarmadatiWsStatus.sql");
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        lastRunStartTs = SqlUtilities.getCalendar(rs, "LAST_RUN_START_TS");
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
    return lastRunStartTs;

  }
  
  private static String searchFarmadatiWS(String serviceName, String serviceParams, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    String xml = null;
    try {
      Properties params = new Properties();
      params.setProperty("SERVICE_NAME", SqlUtilities.getAsStringFieldValueWC(serviceName));
      params.setProperty("SERVICE_PARAMS", SqlUtilities.getAsStringFieldValueWC(serviceParams));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "searchFarmadatiWS.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        xml = rs.getString("RESULT_XML");
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
    return xml;
  }
  
  
  private static void insertFarmadatiWS(String serviceName, String serviceParams, String xml, boolean resultSuccess, String resultDescr, Connection conn) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    //
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertFarmadatiWS.sql");
      pstmt = conn.prepareStatement(sql);
      // SERVICE_NAME
      pstmt.setString(1, serviceName);
      // SERVICE_PARAMS
      SqlUtilities.setString(pstmt, 2, serviceParams);
      // RESULT_XML
      SqlUtilities.setStringAsClob(pstmt, 3, xml);
      // RESULT_SUCCESS
      SqlUtilities.setString(pstmt, 4, resultSuccess ? "S" : "N");
      // RESULT_DESCR
      SqlUtilities.setString(pstmt, 5, resultDescr);
      //
      pstmt.executeUpdate();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(pstmt);
      SqlUtilities.closeWithNoException(rs);
    }
    
  }

  public static void insertFarmadatiDdl(String tableName, String sqlCreate, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      Properties params = new Properties();
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertFarmadatiDdl.sql");
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, tableName);
      SqlUtilities.setStringAsClob(pstmt, 2, sqlCreate);
      pstmt.executeUpdate();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(pstmt);
      SqlUtilities.closeWithNoException(rs);
    }

  }

  public static void updateFarmadatiDdl(String tableName, String sqlCreate, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      Properties params = new Properties();
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateFarmadatiDdlSqlCreate.sql");
      pstmt = conn.prepareStatement(sql);
      SqlUtilities.setStringAsClob(pstmt, 1, sqlCreate);
      pstmt.setString(2, tableName);
      pstmt.executeUpdate();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(pstmt);
      SqlUtilities.closeWithNoException(rs);
    }

  }

  public static Calendar getFarmadatiDdlAllDataLoadedTs(String tableName, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    Calendar ts = null;
    try {
      Properties params = new Properties();
      params.setProperty("TABLE_NAME", SqlUtilities.getAsStringFieldValueWC(tableName));
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "getFarmadatiDdlAllDataLoadedTsByTableName.sql", params);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        ts = SqlUtilities.getCalendar(rs, "ALL_DATA_LOADED_TS");
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
    return ts;

  }
  
  public static void updateFarmadatiDdlAllDataLoadedTs(String tableName, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      Properties params = new Properties();
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "updateFarmadatiDdlAllDataLoadedTs.sql");
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, tableName);
      pstmt.executeUpdate();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sql != null ? "; sql:" + System.getProperty("line.separator") + sql + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(pstmt);
      SqlUtilities.closeWithNoException(rs);
    }

  }

  public static void commentOnTable(String tableName, String comment, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    executeUpdate("COMMENT ON TABLE "+tableName+" IS "+SqlUtilities.getAsStringFieldValue(comment), true, conn);
  }
  

  public static void commentOnColumn(String tableName, String column, String comment, Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    executeUpdate("COMMENT ON COLUMN "+tableName+"."+column+" IS "+SqlUtilities.getAsStringFieldValue(comment), true, conn);
  }
  
  public static Set<String> searchAllFarmadatiTableToIgnore(Connection conn) throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    String sql = null;
    Statement stmt = null;
    ResultSet rs = null;
    Set<String> list = new HashSet<String>();
    try {
      Properties params = new Properties();
      sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "searchAllFarmadatiTableToIgnore.sql");
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        list.add(rs.getString("TABLE_NAME"));
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
}
