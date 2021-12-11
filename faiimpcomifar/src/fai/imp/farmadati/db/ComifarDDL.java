package fai.imp.farmadati.db;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class ComifarDDL {
  static Logger logger = Logger.getLogger(ComifarDDL.class);
  
 private Connection conn = null;
  
  public ComifarDDL(Connection conn) {
    super();
    this.conn = conn;
  }
  
}
