package fai.exp.oracloud.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;

public class RecordsDataImporter_TEST {
  
  static org.apache.log4j.Logger logger;
  static {
    java.util.Properties properties = new java.util.Properties();
    setLoggerProperty(properties, "log4j.rootLogger", "DEBUG, ConsoleAppender, FileAppender");
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

  static void setLoggerProperty(java.util.Properties properties, String key, String defaultValue) {
    properties.setProperty(key, System.getProperty(key, defaultValue));
  }

   public static void main(String[] args) throws Exception {
     
     
    Connection conn = null;
    String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
    String jdbcUrl = "jdbc:oracle:thin:@192.168.21.245:1521:odb";
    String jdbcUser = "FAI";
    String jdbcPass = "FAI";

    Class.forName(jdbcDriver);
    conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
    RecordsDataImporter dataImporter = new RecordsDataImporter(Calendar.getInstance(), conn);
    dataImporter.doJob();
  }

}
