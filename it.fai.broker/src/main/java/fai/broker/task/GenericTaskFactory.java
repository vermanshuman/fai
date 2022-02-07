package fai.broker.task;

import fai.common.db.SqlQueries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;

public class GenericTaskFactory {

	static org.apache.log4j.Logger logger;
	  static {
	    java.util.Properties properties = new java.util.Properties();
	    setLoggerProperty(properties, "log4j.rootLogger", "INFO, ConsoleAppender, FileAppender");
	    setLoggerProperty(properties, "log4j.appender.FileAppender", "org.apache.log4j.RollingFileAppender");
	    setLoggerProperty(properties, "log4j.appender.FileAppender.File", "D:/log/GenericTaskFactory-" + (new java.text.SimpleDateFormat("yyMMdd-HHmmss")).format(java.util.Calendar.getInstance().getTime()) + ".log");
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
	  
	public static GenericTask newGenericTask(String acronym, Calendar nowReference, Connection conn) throws Exception {
		String className = SqlQueries.getGenericTaskConfigClassName(acronym, conn);
		if (className == null || "".equals(className))
			throw new IllegalArgumentException(
					"inammissibile, nessun " + GenericTask.class.getName() + " per l'ACRONYM " + acronym);
		//
		GenericTask genericTask = (GenericTask) Class.forName(className).newInstance();
		genericTask.setup(acronym, nowReference, conn);
		return genericTask;
	}

	public static class Test {

		public static void main(String args[]) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "FAI", "india123");
				con.setAutoCommit(false);

				// step 1
				GenericTask genericTask = GenericTaskFactory.newGenericTask("MAGAZZINO_UPS", Calendar.getInstance(), con);
				String error = genericTask.doJob();

				// step 2
				if(error == null) {
					genericTask = GenericTaskFactory.newGenericTask("FABBISOGNO_CALCULATOR", Calendar.getInstance(), con);
					error = genericTask.doJob();
				}
				// setp 3
				if(error == null) {
					genericTask = GenericTaskFactory.newGenericTask("APPROVVIGIONAMENTO_MGR", Calendar.getInstance(), con);
					error = genericTask.doJob();
				}

//				GenericTask genericTask = GenericTaskFactory.newGenericTask("FABBISOGNO_CALCULATOR", Calendar.getInstance(), con);
//					String error = genericTask.doJob();
//					genericTask = GenericTaskFactory.newGenericTask("APPROVVIGIONAMENTO_MGR", Calendar.getInstance(), con);
//					error = genericTask.doJob();
				con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
