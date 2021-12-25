package fai.common.task;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

import fai.common.db.SqlQueries;
import fai.common.models.GenericTaskConfig;
import fai.common.util.ExceptionsTool;
import fai.common.util.RichProperties;

/**
 * Il metodo {@link #setProperty(String, String)} ha direttamente 
 * effetto sulla tabella FAI_GENERIC_TASK_PROPERTY sottostante 
 *
 */
public class RichPropertiesDB extends RichProperties {
  
  
  static Logger logger = Logger.getLogger(RichPropertiesDB.class);

  
  private String genericTaskAcronym = null;
  private Connection conn = null;
  
  public static RichPropertiesDB newRichPropertiesDB(Properties properties, String genericTaskAcronym, Connection conn) throws Exception {
    RichPropertiesDB rp = new RichPropertiesDB();
    rp.conn = conn;
    rp.genericTaskAcronym = genericTaskAcronym;
    Enumeration enumer = properties.keys();
    while (enumer.hasMoreElements()) {
      String key = (String)enumer.nextElement();
      String value = properties.getProperty(key);
      rp.put(key, value);
    }
    return rp;
  }

  
  public static RichPropertiesDB newRichPropertiesDB(String genericTaskAcronym, Connection conn) throws Exception {
    GenericTaskConfig config = SqlQueries.getGenericTaskConfig(genericTaskAcronym, conn);
    return  newRichPropertiesDB(config.getRichProperties(), genericTaskAcronym, conn);
    
  }

  public synchronized Object setProperty(String key, String value) {
    try {
      SqlQueries.setGenericTaskConfigProperty(genericTaskAcronym, key, value, conn);
    }
    catch (Throwable th) {
      String error = ExceptionsTool.getExceptionDescription("eccezione inattesa tentando di assegnare la proprietà "+value+" alla chiave "+key+" per il FAI_GENERIC_TASK "+genericTaskAcronym, th);
      logger.error(error, th);
      throw new IllegalStateException(error, th);
    }
    return super.put(key, value);
}

  

}
