package fai.imp.farmadati.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import fai.common.db.SqlUtilities;


public class SqlQueries {
	
	 public static Logger logger = Logger.getLogger(SqlQueries.class);

	  static final String SQL_RESOURCE_PATH = "/fai/imp/comifar/db/resources/sql/";
	  
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
}
