package fai.imp.farmadati.task;

import java.sql.Statement;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import fai.common.csv.CsvOutputStream;
import fai.common.db.SqlUtilities;
import fai.common.util.Filesystem;
import fai.common.util.Timeout;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.farmadati.csv.FarmadatiCsvOutputStreamFactory;

/**
 * @deprecated
 * @author Gianni Radice
 *
 */
public class StoreFarmadatiData {

  
  static Logger logger = Logger.getLogger(StoreFarmadatiData.class);
  
  private FaiImportConfig config = null;
  private Connection conn = null;

  /**
   * @deprecated
   * @param config
   * @param conn
   */
  public StoreFarmadatiData(FaiImportConfig config, Connection conn) {
    super();
    this.config = config;
    this.conn = conn;
  }

  /**
   * @deprecated
   * @return
   * @throws Exception
   */
  private String getSelectToCsvFile() throws Exception {
    String sql = null;
    throw new NoSuchMethodError("deve restituire l'SQL che recuperare i campi per il CSV");
  }
  
  public void doStoreData(OutputStream os) throws Exception {
    ResultSet rs = null;
    Statement stmt = null;
    final String METH_NAME = new Object() {}.getClass().getEnclosingMethod().getName();
    logger.debug("method: " + METH_NAME);
    Timeout timeout = new Timeout(10000, false);
    int recordCount = 0;
    try {
      CsvOutputStream csv = FarmadatiCsvOutputStreamFactory.newInstance();
      csv.setOutputStream(os);
      String sql = getSelectToCsvFile();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        csv.setCurrentRecord("Item Number", rs.getString("ITEM_NUM"));
        csv.setCurrentRecord("Description", rs.getString("DESCR"));
        csv.setCurrentRecord("Primary Unit of Measure", rs.getString("PRIMARY_UOM"));
        csv.setCurrentRecord("Pack Type", rs.getString("PACKAGE_TYPE"));
        csv.setCurrentRecord("Long Description", rs.getString("LONG_DESCR"));
        csv.setCurrentRecord("Formatted Description", rs.getString("FORMATTED_DESCR"));
        csv.setCurrentRecord("Input Tax Classification Code", rs.getString("INPUT_TAX_CLASS_CODE"));
        csv.setCurrentRecord("List Price", rs.getDouble("LIST_PRICE"));
        csv.setCurrentRecord("ATTRIBUTE_CATEGORY", rs.getString("ATTR_CAT"));
        csv.setCurrentRecord("ATTRIBUTE1", rs.getString("ATTR1"));
        csv.setCurrentRecord("ATTRIBUTE2", rs.getString("ATTR2"));
        csv.setCurrentRecord("ATTRIBUTE3", rs.getString("ATTR3"));
        csv.flushCurrentRecord();
        recordCount++;
        if (timeout.isExpired()) {
          logger.info(recordCount+" record salvati sul file CSV di output ...");
        }
      }
      logger.info(recordCount+" record complessivamente salvati sul file CSV di output");
          
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME;
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
  }
 
}
