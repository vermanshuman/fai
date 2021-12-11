package fai.imp.farmaclick.ws;

import java.util.List;

import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.FornitoreBean;
import fai.imp.farmaclick.soap.api_2010_001.FCKLogin.LoginOutputBean;
import fai.imp.farmaclick.ws.bean.DownloadListinoOutputBean;

public class FarmaclickWS_TEST {
  
  
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
    org.apache.log4j.PropertyConfigurator.configure(properties);
    logger = org.apache.log4j.Logger.getRootLogger();
  }

  static void setLoggerProperty(java.util.Properties properties, String key, String defaultValue) {
    properties.setProperty(key, System.getProperty(key, defaultValue));
  }

  
  
  public void doTest() throws Exception {
    FarmaclickWS ws = new FarmaclickWS();
    ws.setEndpointAddress("https://secure.infarmaclick.com/public_server_pro/Farmaclick2010001FCKLogin");
    ws.setLogRequestAsXml(true);
    ws.setLogReponsesAsXml(true);
    ws.login("FCK00042794", "FXEJ1BBZ3Y", "SERVER", "");
    List<FornitoreBean> fornitori = ws.getFornitoreBeanList();
    for (FornitoreBean fornitore : fornitori) {
      logger.info("");
      logger.info("-------------------- DOWNLOAD LISTINO "+fornitore.getCodice()+" --------------------");
      logger.info("");
      boolean [] soloVariazioni = new boolean [] { false, true };
      boolean [] outputCompresso = new boolean [] { true, false };
      for (int i = 0; i < soloVariazioni.length; i++) {
        for (int j = 0; j < outputCompresso.length; j++) {
          logger.info("---- solo variazioni:  "+soloVariazioni[i]);
          logger.info("---- output compresso: "+outputCompresso[j]);
          DownloadListinoOutputBean listino = ws.callDownloadListino(fornitore.getCodice(), soloVariazioni[i], outputCompresso[j]);
          if (listino != null) {
            logger.info("url download: "+listino.getUrlDownload());
            logger.info("url conferma: "+listino.getUrlConfermaDownload());
            System.exit(0);
          }
        }
        }
      }
      
  }

  
  public static void main(String[] args) throws Exception {
    FarmaclickWS_TEST test = new FarmaclickWS_TEST();
    test.doTest();
    
  }
}
