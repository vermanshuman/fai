package fai.imp.farmadati.tools;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.farmadati.task.FarmadatiDataCollector;

/**
 * Concepita per essere lanciata a riga di comando
 * 
 * @author Gianni Radice
 *
 */
public class FarmadatiDataCollectorApp {

  
  static Logger logger = null;

  static Connection conn = null;
  
  

  static void setLoggerProperty(java.util.Properties properties, String key, String defaultValue) {
    properties.setProperty(key, System.getProperty(key, defaultValue));
  }



	static {
		java.util.Properties properties = new java.util.Properties();
		setLoggerProperty(properties, "log4j.rootLogger", "INFO, ConsoleAppender, FileAppender");
		setLoggerProperty(properties, "log4j.appender.FileAppender", "org.apache.log4j.RollingFileAppender");
		setLoggerProperty(properties, "log4j.appender.FileAppender.File", "C:/log/TEST-" + (new java.text.SimpleDateFormat("yyMMdd-HHmmss")).format(java.util.Calendar.getInstance().getTime()) + ".log");
		setLoggerProperty(properties, "log4j.appender.FileAppender.MaxFileSize", "100MB");
		setLoggerProperty(properties, "log4j.appender.FileAppender.MaxBackupIndex", "10");
		setLoggerProperty(properties, "log4j.appender.FileAppender.layout", "org.apache.log4j.PatternLayout");
		setLoggerProperty(properties, "log4j.appender.FileAppender.layout.ConversionPattern", "%d{dd/MM/yyyy HH:mm:ss} [%-5p][%8.8t]  %L@%C - %m%n");
		setLoggerProperty(properties, "log4j.appender.ConsoleAppender", "org.apache.log4j.ConsoleAppender");
		setLoggerProperty(properties, "log4j.appender.ConsoleAppender.layout", "org.apache.log4j.PatternLayout");
		setLoggerProperty(properties, "log4j.appender.ConsoleAppender.layout.ConversionPattern", "%d{dd/MM/yyyy HH:mm:ss} [%-5p][%8.8t]  %L@%C - %m%n");
		setLoggerProperty(properties, "log4j.logger.org.apache.axis", "INFO");
		setLoggerProperty(properties, "log4j.logger.org.apache.hc", "INFO");
		setLoggerProperty(properties, "log4j.logger.org.apache.http.auth", "INFO");
		setLoggerProperty(properties, "log4j.logger.org.apache.commons.net.ftp", "INFO");
		org.apache.log4j.PropertyConfigurator.configure(properties);
		logger = org.apache.log4j.Logger.getRootLogger();
	}
  
  public static void main(String[] args) throws Exception {
//    if (args.length == 0) {
//      System.out.println("specificare il properties (deve contenere sia la configurazione JDBC che Log4j)");
//      return;
//    }
//    //
//    FileInputStream is = new FileInputStream(args[0]);
//    Properties props = new Properties();
//    props.load(is);
//    is.close();
    //
//    org.apache.log4j.PropertyConfigurator.configure(props);
//    logger = org.apache.log4j.Logger.getRootLogger();
//    //
//    Connection conn = null;
//    String jdbcDriver = props.getProperty("jdbcDriver", "oracle.jdbc.driver.OracleDriver");
//    String jdbcUrl = props.getProperty("jdbcUrl");
//    String jdbcUser = props.getProperty("jdbcUser");
//    String jdbcPass = props.getProperty("jdbcPass");
//    Class.forName(jdbcDriver);
//    conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
//    conn.setAutoCommit(false);
    //
	  

		Connection conn = null;
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String jdbcUser = "FAI";
		String jdbcPass = "india123";

		Class.forName(jdbcDriver);
		conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
		conn.setAutoCommit(false);
    FaiImportConfig config = fai.imp.base.db.SqlQueries.getFaiImportConfig("FARMADATI", conn); 
    FarmadatiDataCollector collectFarmadatiData = new FarmadatiDataCollector(config, conn);
    collectFarmadatiData.setDebugMode(false);
    collectFarmadatiData.doCollectData();
    //
    int WAIT_SEC = 20;
    logger.info("attesa di "+WAIT_SEC+" secondi prima di forzare il  garebage collector ...");
    Thread.sleep(WAIT_SEC * 1000);
    System.gc();
    logger.info("attesa di "+WAIT_SEC+" secondi prima di terminare l'applicazione ...");
    Thread.sleep(WAIT_SEC * 1000);
  }

}
