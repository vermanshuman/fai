package fai.common.ftp;

import fai.common.models.FtpConfig;

public class FtpFactory {
  
  public static Ftp newFtp(FtpConfig config) throws Exception {
    String protocol = config.getFtpProtocol();
    if ("FTP".equalsIgnoreCase(protocol) || "FTPS".equalsIgnoreCase(protocol)) {
      return new FtpFtps(config);
    }
    else if ("SFTP".equalsIgnoreCase(config.getFtpProtocol())) { 
      return new Sftp(config);
    }
    else if ("LOCAL".equalsIgnoreCase(config.getFtpProtocol())) { 
      return new LocalFilesystem(config);
    }
    else {
      throw new IllegalArgumentException("protocollo non gestito: "+protocol);
    }
  }
  

}
