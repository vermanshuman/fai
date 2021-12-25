package fai.common.ftp;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import fai.common.models.FtpConfig;

import java.util.Properties;

import it.swdes.decrypt.Decryptor;

class Sftp implements Ftp {

  
  static Logger logger = Logger.getLogger(Sftp.class);


  private FtpConfig cfg = null;
  private String error = null;
  private JSch jsch = null;
  private Session session = null;
  private Channel channel = null;
  private ChannelSftp sftp = null;
  private Exception lastDisconnectException = null;
  
  
  public Sftp(FtpConfig cfg) {
    super();
    this.cfg = cfg;
  }
  

  @Override
  public void connect() throws Exception {
    error = null;
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    try {
      jsch = new JSch();
      session = null;
      String host= cfg.getFtpHost();
      int port = cfg.getFtpPort();
      String user = cfg.getFtpLogin();
      if ("".equals(user.trim())) user = null;
      String pass = cfg.getFtpPassword();
      if ("".equals(pass.trim())) pass = null;
      if (user != null) {
        if (Boolean.TRUE.equals(cfg.getFtpPasswordEncr() && pass != null)) {
            logger.info("decrittazione password FTP...");
            pass = Decryptor.decrypt(pass);
        }
      }
      logger.info("creazione della sessione verso "+host+":"+port+(user != null ? " per l'utente "+user : "")+" ...");
      session = jsch.getSession(user, host, port);
      Properties sftpConfig = new Properties();
      sftpConfig.setProperty("StrictHostKeyChecking", "no");
      session.setConfig(sftpConfig);
      session.setPassword(pass);
      session.connect(30000);
      logger.info("creazione del canale ...");
      channel = session.openChannel( "sftp" );
      sftp = ( ChannelSftp ) channel;
      sftp.connect(30000);
      logger.info("connessione stabilita");
    }
    catch (Throwable th) {
      disconnect();
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      if (error == null) error = msg; 
      logger.error(msg, th);
      throw new Exception(msg, th);
      
    }
    //
  }

  @Override
  public OutputStream getOutputStream(String targetFilename) throws Exception {
    error = null;
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    try {
      if (sftp == null) {
        connect();
        if (error != null) return null;
      }
      //
      String dir = cfg.getFtpDir(); 
      logger.info("cd "+dir);
      sftp.cd(dir);
      String filename = targetFilename; 
      logger.info("apertura "+OutputStream.class.getName()+" verso "+filename+" ...");
      OutputStream os = sftp.put(filename);
      logger.info(OutputStream.class.getName()+" verso "+filename+" aperto");
      return os;
    }
    catch (Throwable th) {
      lastDisconnectException = null;
      disconnectExecute();
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      if (error == null) error = msg; 
      logger.error(msg, th);
      throw new Exception(msg, th);
      
    }
  }


  @Override
  public InputStream getInputStream(String sourceFilename) throws Exception {
    error = null;
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    try {
      if (sftp == null) {
        connect();
        if (error != null) return null;
      }
      //
      String dir = cfg.getFtpDir(); 
      logger.info("cd "+dir);
      sftp.cd(dir);
      String filename = sourceFilename; 
      logger.info("apertura "+OutputStream.class.getName()+" verso "+filename+" ...");
      InputStream is = sftp.get(filename);
      logger.info(InputStream.class.getName()+" verso "+filename+" aperto");
      return is;
    }
    catch (Throwable th) {
      lastDisconnectException = null;
      disconnectExecute();
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      if (error == null) error = msg; 
      logger.error(msg, th);
      throw new Exception(msg, th);
      
    }
  }

  @Override
  public void completePendingCommand() throws Exception {
    // nothing todo
  }

  @Override
  public void  disconnect() throws Exception {
    error = null;
    lastDisconnectException = null;
    logger.info("disconnessione ...");
    disconnectExecute();
    if (lastDisconnectException != null) {
      throw lastDisconnectException;
    }
    logger.info("disconnessione completata"+(error != null ? " con errore: "+error : ""));
  }
  
  

  private void disconnectExecute() throws Exception {
    if (jsch == null && sftp == null && session == null) return;
    //
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    channelExit();
    sessionDisconnect();
    jsch = null;

  }
  
  
  private void channelExit()  {
    if (sftp == null) return;
    //
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    try {
      logger.info("chiusura del canale ...");
      sftp.exit();
      sftp = null;
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» chiusura del canale non riuscita nel metodo " + METH_NAME;
      if (error == null) error = msg; 
      logger.error(msg, th);
      lastDisconnectException = new IllegalStateException(msg, th);
    }
    finally {
    }
    
  }

  private void sessionDisconnect() {
    if (session == null) return;
    //
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    try {
      logger.info("chiusura della sessione ...");
      session.disconnect();
      session = null;
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» chiusura della sessione non riuscita nel metodo " + METH_NAME;
      if (error == null) error = msg; 
      logger.error(msg, th);
      lastDisconnectException = new IllegalStateException(msg, th);
    }
    finally {
    }
    
  }


  @Override
  public String getError() {
    return error;
  }
  
  
  
  
  

}

