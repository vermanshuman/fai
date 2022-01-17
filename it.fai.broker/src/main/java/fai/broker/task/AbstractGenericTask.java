package fai.broker.task;

import fai.broker.models.UploadCSVStatus;
import fai.broker.models.UploadTaskConfig;
import fai.common.db.SqlQueries;
import fai.common.models.GenericTaskConfig;
import fai.common.task.RichPropertiesDB;
import fai.common.util.ExceptionsTool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Calendar;

public abstract class AbstractGenericTask implements GenericTask {
  

  static Logger logger;

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
    logger = Logger.getLogger(AbstractGenericTask.class);
  }

  static void setLoggerProperty(java.util.Properties properties, String key, String defaultValue) {
    properties.setProperty(key, System.getProperty(key, defaultValue));
  }


  protected String acronym;
  protected Calendar nowReference;
  protected Connection conn;
  protected GenericTaskConfig taskConfig; 
  protected RichPropertiesDB params = null;
  protected UploadTaskConfig uploadTaskConfig;
  
  @Override
  public void setup(String acronym, Calendar nowReference, Connection conn) throws Exception {
    String[] tokens = acronym.split("\\@");
    this.acronym = tokens[0];
    this.nowReference = nowReference;
    this.conn = conn;
    if(tokens.length > 1){
      uploadTaskConfig = fai.broker.db.SqlQueries.findUploadTask(Long.parseLong(tokens[1]), conn);
    }else {
      taskConfig = SqlQueries.getGenericTaskConfig(acronym, conn);
      params = RichPropertiesDB.newRichPropertiesDB(taskConfig.getRichProperties(), acronym, conn);
    }
    completeSetup();
  }
  
  protected abstract void completeSetup() throws Exception;
  
  @Override
  public String doJob() throws Exception {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = acronym+", "+METH_NAME+": ";
    logger.info(SqlQueries.logInfo(LOG_PREFIX + "...", null, null, conn));
    conn.commit();
    if (uploadTaskConfig == null && isThisWeekdayExecutionAllowed() == false) {
      logger.info(SqlQueries.logInfo(LOG_PREFIX + "nessuna operazione da svolgere in base al pattern settimanale", null, null, conn));
      conn.commit();
      return null;
    }
    //
    String error;
    if(uploadTaskConfig != null){
      fai.broker.db.SqlQueries.setUploadTaskNewSession(uploadTaskConfig.getOid(), conn);
    }else {
      SqlQueries.setGenericTaskNewSession(taskConfig.getOid(), conn);
    }
    try {
      error = doJobExecute();
      if (error == null) {

        if(uploadTaskConfig != null){
          fai.broker.db.SqlQueries.setUploadTaskSessionCompleted(uploadTaskConfig.getOid(), true,
                  UploadCSVStatus.VALUE_PROCESSING.getOid(), UploadCSVStatus.VALUE_PROCESSING.getDescr(), null, conn);
        }
        else
          SqlQueries.setGenericTaskSessionCompleted(taskConfig.getOid(), true, null, conn);
        logger.info(SqlQueries.logInfo(LOG_PREFIX + "task completato senza errori", null, null, conn));
      }
      else {
        conn.rollback(); // eventuale rollaback per qualsisi cosa non sia stato commitatto
        logger.error(SqlQueries.logError(LOG_PREFIX+"il task non sarà portato a termine per il seguente motivo: "+error, null, null, conn));
        if(uploadTaskConfig != null){
          fai.broker.db.SqlQueries.setUploadTaskSessionCompleted(uploadTaskConfig.getOid(), false,
                  UploadCSVStatus.VALUE_ERROR.getOid(), UploadCSVStatus.VALUE_ERROR.getDescr(), error, conn);
        }else {
          SqlQueries.setGenericTaskSessionCompleted(taskConfig.getOid(), false, error, conn);
        }
      }
      conn.commit();
    }
    catch (Throwable th) {
		th.printStackTrace();
      error = LOG_PREFIX+ExceptionsTool.getExceptionDescription("task interrotto causa eccezione intattesa", th);
      logger.error(SqlQueries.logError(error, null, th, conn), th);
      if(uploadTaskConfig != null){
        fai.broker.db.SqlQueries.setUploadTaskSessionCompleted(uploadTaskConfig.getOid(), false,
                UploadCSVStatus.VALUE_ERROR.getOid(), UploadCSVStatus.VALUE_ERROR.getDescr(), error, conn);
      }else {
        SqlQueries.setGenericTaskSessionCompleted(taskConfig.getOid(), false, error, conn);
      }
      conn.commit();
    }
    return error;
  }
  
  protected abstract String doJobExecute() throws Exception;

  
  protected boolean isThisWeekdayExecutionAllowed() {
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    String smtwtfs = taskConfig.getScheduledSmtwtfs();
    if (smtwtfs == null || "".equals(smtwtfs.trim())) {
      logger.info(LOG_PREFIX+"nessun filtro pattern settimanale e orario impostato per l'esecuzione del task: il task sarà eseguito");
      return true;
    }
    //
    int [] weekday = new int [] { Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY };
    int currDayOfWeek = nowReference.get(Calendar.DAY_OF_WEEK);
    for (int i = 0; i < weekday.length; i++) {
      if (currDayOfWeek == weekday[i]) {
        String type = smtwtfs.substring(i, i+1);
        boolean mustBeExecuted = "X".equals(type);
        logger.info(LOG_PREFIX+"flag di esecuzione estratto dal pattern settimanale "+smtwtfs+": "+type+"; il task "+(mustBeExecuted == false ? "non sarà" : "sarà")+" eseguito");
        return mustBeExecuted;
      }
    }
    throw new IllegalStateException(LOG_PREFIX+"configurazione pattern settimanale errata: "+taskConfig.getScheduledSmtwtfs());
  }

  
  
  

}
