package it.fai.be.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
	
	public static Connection getDbConnection(String dbDriver, String dbUrl, String dbUser, String dbPass) throws ClassNotFoundException, SQLException {
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		con.setAutoCommit(false);
		return con;
	}
	
	public static void closeSilent (Connection conn) {
		if (conn != null) {
			try {conn.close();} catch (SQLException e) {}
		}
	}

}
