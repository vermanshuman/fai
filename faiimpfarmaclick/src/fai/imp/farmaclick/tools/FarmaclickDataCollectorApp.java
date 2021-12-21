package fai.imp.farmaclick.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fai.imp.base.bean.ProductAvailibilityBean;
import fai.imp.base.bean.ProductOrderRequestBean;
import fai.imp.base.db.SqlQueries;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.farmaclick.task.FarmaclickDataCollector;

public class FarmaclickDataCollectorApp {
  
  static org.apache.log4j.Logger logger;
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

  static void setLoggerProperty(java.util.Properties properties, String key, String defaultValue) {
    properties.setProperty(key, System.getProperty(key, defaultValue));
  }

  
  
  public static void main(String[] args) throws Exception {
    
    
    Connection conn = null;
    String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	String jdbcUser = "FAI";
	String jdbcPass = "india123";
    Class.forName(jdbcDriver);
    conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
    conn.setAutoCommit(false);
    FaiImportConfig config = SqlQueries.getFaiImportConfig("FARMACLICK", conn);
    FarmaclickDataCollector dataCollector = new FarmaclickDataCollector(config, conn);
    //dataCollector.doCollectData();
    //List<ProductAvailibilityBean> productAvailibilityBeans = dataCollector.doGetAvailiblityData(Stream.of("012745028", "029401027").collect(Collectors.toList()));
    
    ProductOrderRequestBean productOrderRequestBean = new ProductOrderRequestBean();
    productOrderRequestBean.setProductCode("902980616");
    productOrderRequestBean.setQuantity(1);
    dataCollector.doOrderProducts(Stream.of(productOrderRequestBean).collect(Collectors.toList()));
  }

}
