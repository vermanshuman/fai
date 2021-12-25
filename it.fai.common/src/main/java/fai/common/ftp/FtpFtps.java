package fai.common.ftp;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.log4j.Logger;

import fai.common.models.FtpConfig;
import it.swdes.decrypt.Decryptor;

class FtpFtps implements Ftp {
  
  
  static Logger logger = Logger.getLogger(FtpFtps.class);

  
  private FtpConfig cfg = null;
  private FTPClient ftpClient = null;
  private String error = null;
  private boolean loggedIn = false;
  
  
  public FtpFtps(FtpConfig cfg) {
    super();
    this.cfg = cfg;
  }
  
  
  @Override
  public void connect() throws Exception {
    loggedIn = false;
    error = null;
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    try {
      if (ftpClient != null) {
        ftpClient.disconnect();
        if (error != null) return;
      }
      //
      String protocol = cfg.getFtpProtocol();
      if ("FTP".equalsIgnoreCase(protocol)) {
        ftpClient = new FTPClient();
      }
      else if ("FTPS".equalsIgnoreCase(protocol)) {
        ftpClient = new FTPSClient();
      }
      else {
        throw new IllegalArgumentException("protocollo non gestito: "+protocol);
      }
      //
      ftpClient.setConnectTimeout(30000);
      ftpClient.setDataTimeout(30000);
      ftpClient.setDefaultPort(cfg.getFtpPort());
      //
      String server = cfg.getFtpHost();
      logger.info("connessione a "+server+" ...");
      ftpClient.connect(server);
      int reply = ftpClient.getReplyCode();
      if (!FTPReply.isPositiveCompletion(reply)) {
        error = "connessione non riuscita, codice "+reply;
        logger.error(error);
        disconnectExecute();
        return;
      }
      //
      String user = cfg.getFtpLogin();
      if ("".equals(user.trim())) user = null;
      String pass = cfg.getFtpPassword();
      if ("".equals(pass.trim())) pass = null;
      if (user != null) {
        if (Boolean.TRUE.equals(cfg.getFtpPasswordEncr() && pass != null)) {
            logger.info("decrittazione password FTP...");
            pass = Decryptor.decrypt(pass);
        }
        logger.info("login al server "+server+" ...");
        if (ftpClient.login(user, pass) == false) {
          error = "login non riuscita";
          logger.error(error);
          disconnectExecute();
          return;
        }
        loggedIn = true;
      }
      //
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }
    catch (Throwable th) {
      disconnect();
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      if (error == null) error = msg; 
      logger.error(msg, th);
      throw new Exception(msg, th);
      
    }
    finally {
    }
        
  }
  
  
  @Override
  public OutputStream getOutputStream(String targetFilename) throws Exception {
    error = null;
    if (ftpClient == null) {
      connect();
      if (error != null) {
        return null;
      }
    }
    //
    String fullpath = getFullPath(cfg.getFtpDir(), targetFilename);
    logger.info("apertura "+OutputStream.class.getName()+" verso "+fullpath+" ...");
    OutputStream os = ftpClient.storeFileStream(fullpath);
    if (os == null) {
      error = OutputStream.class.getName()+" verso "+fullpath+" non ottenuto";
      logger.error(error);
      disconnectExecute();
    }
    return os;
  }



  @Override
  public InputStream getInputStream(String sourceFilename) throws Exception {
    error = null;
    if (ftpClient == null) {
      connect();
      if (error != null) {
        return null;
      }
    }
    //
    String fullpath = getFullPath(cfg.getFtpDir(), sourceFilename);
    logger.info("apertura "+InputStream.class.getName()+" verso "+fullpath+" ...");
    InputStream is = ftpClient. retrieveFileStream(fullpath);
    if (is == null) {
      error = InputStream.class.getName()+" verso "+fullpath+" non ottenuto";
      logger.error(error);
      disconnectExecute();
    }
    return is;
  }
  
  
  
  
  private String getFullPath(String path, String filename) throws Exception {
    path = path.trim();
    filename = filename.trim();
    //
    if (path.endsWith("/")) path = path.substring(0, path.length()-1);
    if (filename.startsWith("/")) filename = filename.substring(1, filename.length());
    //
    return path+"/"+filename;
  }
  
  @Override
  public void completePendingCommand() throws Exception {
    error = null;
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    try {
      if (ftpClient == null) {
        error = ""+FTPClient.class.getName()+" non istanziato";
        logger.error(error);
        return;
      }
      if (ftpClient.completePendingCommand() == false) {
        error = "chisura delle operazioni pending non riuscita";
        logger.error(error);
        disconnectExecute();
      }
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      if (error == null) error = msg;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
    }

  }
  

  @Override
  public void  disconnect() throws Exception {
    error = null;
    disconnectExecute();
  }
  
  

  private void disconnectExecute() throws Exception {
    if (ftpClient == null) return;
    //
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    try {
      if (loggedIn) {
        logger.info("logout ...");
        ftpClient.logout();
      }
      logger.info("disconnessione ...");
      ftpClient.disconnect();
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» disconnessione non riuscita nel metodo " + METH_NAME;
      if (error == null) error = msg; 
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      ftpClient = null;
    }

  }


  @Override
  public String getError() {
    return error;
  }

  
  

}

