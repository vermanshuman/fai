package fai.imp.farmadati.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import fai.common.db.SqlUtilities;
import fai.imp.comifar.dto.Listino;


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

	public static void deleteAllComifarWS(Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		executeUpdate("DELETE FROM FAI_COMIFAR_WS", true, conn);
	}

	public static void deleteAllFailedComifarWS(Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		executeUpdate("DELETE FROM FAI_COMIFAR_WS WHERE RESULT_SUCCESS = 'N'", true, conn);
	}

	public static void insertGetProductList(String xml, Listino listino, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		insertComifarWS("Listino",listino.getInfo().getProgressive().toString(),xml, listino.getInfo().getOutcome().contains("OK."), listino.getInfo().getOutcomeDescription(), conn); 
	}

	private static void insertComifarWS(String serviceName, String serviceParams, String xml, boolean resultSuccess, String resultDescr, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		//
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "insertComifarWS.sql");
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

	public static String searchGetDataSet(Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		//
		return searchComifarWS("Listino", conn);
	}

	private static String searchComifarWS(String serviceName, Connection conn) throws Exception {
		final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
		logger.debug("method: " + METH_NAME);
		String sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		String xml = null;
		try {
			Properties params = new Properties();
			params.setProperty("SERVICE_NAME", SqlUtilities.getAsStringFieldValueWC(serviceName));
			sql = SqlUtilities.getSql(SQL_RESOURCE_PATH, "searchComifarWS.sql", params);
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
}
