package fai.imp.comifar.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fai.common.ftp.Ftp;
import fai.common.ftp.FtpFactory;
import fai.common.models.FtpConfig;
import fai.imp.base.bean.ProcessedOrderBean;
import fai.imp.base.bean.ProductBean;
import fai.imp.base.db.SqlQueries;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.comifar.task.ComifarDataCollector;

public class ComifarDataCollectorApp {

	static org.apache.log4j.Logger logger;
	static {
		java.util.Properties properties = new java.util.Properties();
		setLoggerProperty(properties, "log4j.rootLogger", "INFO, ConsoleAppender, FileAppender");
		setLoggerProperty(properties, "log4j.appender.FileAppender", "org.apache.log4j.RollingFileAppender");
		setLoggerProperty(properties, "log4j.appender.FileAppender.File", "E:/log/TEST-" + (new java.text.SimpleDateFormat("yyMMdd-HHmmss")).format(java.util.Calendar.getInstance().getTime()) + ".log");
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

//		FtpConfig ftpConfig = new FtpConfig();
//		ftpConfig.setFtpProtocol("LOCAL");
//		ftpConfig.setFtpHost("localhost");
//		ftpConfig.setFtpLogin("anshuman");
//		ftpConfig.setFtpPassword("india123");
//		Ftp ftp = FtpFactory.newFtp(ftpConfig);
//		ftp.getInputStream("/web_order.csv");
		Connection conn = null;
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String jdbcUser = "FAI";
		String jdbcPass = "india123";

		Class.forName(jdbcDriver);
		conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
		conn.setAutoCommit(false);
		FaiImportConfig config = SqlQueries.getFaiImportConfig("COMIFAR", conn);
		ComifarDataCollector dataCollector = new ComifarDataCollector(config, conn);
		
		// Start Collecting data
		dataCollector.doCollectData();
		// End Collecting data
		
		// Check availibility
//		ProductBean productBean = new ProductBean();
//		productBean.setProductCode("900266077");
//		productBean.setQuantity(1);
//		
//		List<ProductBean> products = dataCollector.doGetAvailiblityData(Stream.of(productBean).collect(Collectors.toList()));
//
//		products.stream()
//		.forEach(p -> {
//			System.out.println(p.getProductCode() + ">>>>>" + p.getQuantity() + "::: " + p.getAvailibility());
//		});
		// End Check availibility
		
		// Start Order Products
//		ProductOrderRequestBean productOrderRequestOne = new ProductOrderRequestBean();
//		productOrderRequestOne.setProductCode("28725125");
//		productOrderRequestOne.setQuantity(1);
//	    
//		ProductOrderRequestBean productOrderRequestTwo = new ProductOrderRequestBean();
//		productOrderRequestTwo.setProductCode("28740013");
//		productOrderRequestTwo.setQuantity(5);
//	    List<ProcessedOrderBean> processedOrderBeans = dataCollector.doOrderProducts(Stream.of(productOrderRequestOne, productOrderRequestTwo).collect(Collectors.toList()));
//	    
//	    processedOrderBeans
//	    .stream()
//	    .forEach(p -> {
//	    	System.out.println("Order status for product: " + p.getProductCode() + " is " + p.getOrderFailed());
//	    	if(p.getOrderFailed()) {
//	    		System.out.println("reason for order failure " + p.getErrorCode() + " : " + p.getErrorDescription());
//	    	}
//	    });
		// End Order Products
	}
}
