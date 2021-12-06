package fai.imp.farmadati.tools;

import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.junit.Test;

import fai.imp.base.db.SqlQueries;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.farmadati.task.FarmadatiDataCollector;

import java.util.Properties;



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


  
  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.out.println("specificare il properties (deve contenere sia la configurazione JDBC che Log4j)");
      return;
    }
    //
    FileInputStream is = new FileInputStream(args[0]);
    Properties props = new Properties();
    props.load(is);
    is.close();
    //
    org.apache.log4j.PropertyConfigurator.configure(props);
    logger = org.apache.log4j.Logger.getRootLogger();
    //
    Connection conn = null;
    String jdbcDriver = props.getProperty("jdbcDriver", "oracle.jdbc.driver.OracleDriver");
    String jdbcUrl = props.getProperty("jdbcUrl");
    String jdbcUser = props.getProperty("jdbcUser");
    String jdbcPass = props.getProperty("jdbcPass");
    Class.forName(jdbcDriver);
    conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
    conn.setAutoCommit(false);
    //
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
