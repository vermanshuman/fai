package fai.common.ftp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import fai.common.models.FtpConfig;
import fai.common.util.Filesystem;

class LocalFilesystem implements Ftp {
  
  
  static Logger logger = Logger.getLogger(LocalFilesystem.class);

  
  private FtpConfig cfg = null;
  private String error = null;
  private boolean loggedIn = false;
  
  
  public LocalFilesystem(FtpConfig cfg) {
    this.cfg = cfg;
  }
  
  
  @Override
  public void connect() throws Exception {
    // nothing todo
        
  }
  
  
  @Override
  public OutputStream getOutputStream(String targetFilename) throws Exception {
    error = null;
    //
    String fullpath = Filesystem.getFullPath(cfg.getFtpDir(), targetFilename);
    logger.info("apertura "+OutputStream.class.getName()+" verso "+fullpath+" ...");
    OutputStream os = new FileOutputStream(fullpath);
    return os;
  }
  


  @Override
  public InputStream getInputStream(String sourceFilename) throws Exception {
    error = null;
    //
    String fullpath = Filesystem.getFullPath(cfg.getFtpDir(), sourceFilename);
    logger.info("apertura "+InputStream.class.getName()+" verso "+fullpath+" ...");
    InputStream os = new FileInputStream(fullpath);
    return os;
  }  
  
  
  @Override
  public void completePendingCommand() throws Exception {
    // nothing todo
  }
  

  @Override
  public void  disconnect() throws Exception {
    error = null;
    disconnectExecute();
  }
  
  

  private void disconnectExecute() throws Exception {
    // nothing todo
  }


  @Override
  public String getError() {
    return error;
  }

  
  
  
  
  

}

