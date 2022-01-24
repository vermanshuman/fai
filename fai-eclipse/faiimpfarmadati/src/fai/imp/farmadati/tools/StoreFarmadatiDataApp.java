package fai.imp.farmadati.tools;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

import fai.common.ftp.Ftp;
import fai.common.ftp.FtpFactory;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.farmadati.db.SqlQueries;
import fai.imp.farmadati.task.FarmadatiDataCollector;
import fai.imp.farmadati.task.StoreFarmadatiData;

/**
 * Concepita per esser lanciata a riga di comando
 * 
 * @deprecated
 * @author Gianni Radice
 *
 */
public class StoreFarmadatiDataApp {

  
  static Logger logger = null;

  private Connection conn = null;
  private FaiImportConfig config = null;
  
  /**
   * 
   * @deprecated
   * @param conn
   */
  public StoreFarmadatiDataApp(Connection conn) {
    this.conn = conn;
  }

  /**
   * @deprecated
   * @throws Exception
   */
  public void doJob() throws Exception {
    config = fai.imp.base.db.SqlQueries.getFaiImportConfig("FARMADATI", conn);

    //
    // --- APERTURA DELLA CONNESSIONE FTP VERSO IL SERVER DI DESTINAZIONE ---
    //
    Ftp ftp = null; // FtpFactory.newFtp(config);
    OutputStream os = ftp.getOutputStream("esempio.zip");
    String error = ftp.getError();
    if (error != null) {
      logger.error("RECUPERO DELLO STREAMING DI OUTPUT VERSO L'FTP NON RIUSCITO: "+error);
      return;
    }
    //
    // --- RECUPERO DATI DA BANCA DATI E STORE SU FTP TARGET  ---
    //
    StoreFarmadatiData store = new StoreFarmadatiData(config, conn);
    store.doStoreData(os);
    //
    // --- RILASCIO DELLE RISORSE ---
    //
    os.flush();
    os.close();
    ftp.completePendingCommand();
    error = ftp.getError();
    if (error != null) {
      logger.error("COMPLETAMENTO OPERAZIONI PENDING FTP NON RIUSCITO: "+error);
      return;
    }
    ftp.disconnect();;
    error = ftp.getError();
    if (error != null) {
      logger.error("DISCONNESSIONE FTP NON RIUSCITA: "+error);
      return;
    }
  }
  
  /**
   * @deprecated
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.out.println("specificare il properties");
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
    String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
    String jdbcUrl = props.getProperty("jdbcUrl");
    String jdbcUser = props.getProperty("jdbcUser");
    String jdbcPass = props.getProperty("jdbcPass");
    Class.forName(jdbcDriver);
    conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
    conn.setAutoCommit(false);
    //
    StoreFarmadatiDataApp app = new StoreFarmadatiDataApp(conn);
    app.doJob();
    
    //
  }


}
