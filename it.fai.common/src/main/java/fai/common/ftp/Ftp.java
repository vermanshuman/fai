package fai.common.ftp;

import java.io.InputStream;
import java.io.OutputStream;

public interface Ftp {
  
  void connect() throws Exception;

  OutputStream getOutputStream(String targetFilename) throws Exception;

  InputStream getInputStream(String sourceFilename) throws Exception;
  
  void completePendingCommand() throws Exception;

  void disconnect() throws Exception;

  String getError();

}