package fai.exp.oracloud.task;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import fai.common.csv.CsvOutputStream;
import fai.common.db.SqlUtilities;
import fai.common.util.NumberFormatFactory;
import fai.exp.oracloud.db.FaiColumnExport;
import fai.exp.oracloud.db.FaiTableExport;

public class CsvExporter {
  
  
  static Logger logger = Logger.getLogger(CsvExporter.class);

  private String sql = null;
  private FaiTableExport table = null;
  private Integer lastVersion = null;
  private Integer prevVersion = null;
  private Connection conn = null;
  private Hashtable<String, NumberFormat> numberFormattersByPattern = new Hashtable<String, NumberFormat>(); 
  private Hashtable<String, SimpleDateFormat> simpleDateFormatByPattern = new Hashtable<String, SimpleDateFormat>(); 
  
  
  
  public CsvExporter(String sql, FaiTableExport table, Integer lastVersion, Integer prevVersion, Connection conn) {
    super();
    this.sql = sql;
    this.table = table;
    this.lastVersion = lastVersion;
    this.prevVersion = prevVersion;
    this.conn = conn;
  }



  private Object getFormatter(FaiColumnExport col) {
    Object formatter = null;
    if (col.getNumbFormatPattern() != null) {
      String nfpattern = col.getNumbFormatPattern();
      char decSep = col.getNumbFormatDecCh() != null ? col.getNumbFormatDecCh().charAt(0) : '.'; 
      char grpSep = col.getNumbFormatGrpCh() != null ? col.getNumbFormatGrpCh().charAt(0) : '\'';
      String key = nfpattern+"::"+decSep+"::"+grpSep;
      NumberFormat nf = numberFormattersByPattern.get(key);
      if (nf == null) {
        nf = NumberFormatFactory.newNumberFormat(nfpattern, decSep, grpSep);
        numberFormattersByPattern.put(key, nf);
      }
      formatter = nf;
    }
    else if (col.getDateFormatPattern() != null) {
      String key = col.getDateFormatPattern();
      SimpleDateFormat sdf = simpleDateFormatByPattern.get(key);
      if (sdf == null) {
        sdf = new SimpleDateFormat(key);
        simpleDateFormatByPattern.put(key, sdf);
      }
      formatter = sdf;
    }
    return formatter;
  }



  public void doExport(OutputStream os) throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    //
    // --- configurazione CsvOutputStream --- 
    //
    CsvOutputStream csv = new CsvOutputStream();
    csv.setFieldSeparator(","); // SPOSTARE COME PARAMETRO CONFIGURABILE IN BANCA DATI
    csv.setIncludeHeader(false); // SPOSTARE COME PARAMETRO CONFIGURABILE IN BANCA DATI
    csv.setCrlf("\r\n"); // SPOSTARE COME PARAMETRO CONFIGURABILE IN BANCA DATI
    List<FaiColumnExport> columns = table.getColumns();
    for (FaiColumnExport col : columns) {
      String header = col.getColumnNameExport();
      boolean required = col.isRequired();
      Object formatter = getFormatter(col);
      csv.addHeader(header, null, formatter, required);
    }
    csv.addHeader("END", "END", null, true); // terminatore: tutte le righe terminano, lato Oracle Cloud, col valore "END"
    csv.setOutputStream(os);
    //
    // --- interrogazione ed export --- 
    //
    Statement stmt = null;
    ResultSet rs = null;
    String sqlWithParams = sql;
    try {
      if (sql.indexOf("?LAST_VERSION?") >= 0) {
        sqlWithParams = sqlWithParams.replace("?LAST_VERSION?", ""+(lastVersion == null ? 0 : lastVersion));
      }
      if (sql.indexOf("?PREV_VERSION?") >= 0) {
        sqlWithParams = sqlWithParams.replace("?PREV_VERSION?", ""+(prevVersion == null ? 0 : prevVersion));
      }
      logger.debug("sql con parametri valorizzati: "+sqlWithParams); 
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sqlWithParams);
      int recordCount = 0;
      while (rs.next()) {
        csv.newRecord();
        for (FaiColumnExport col : columns) {
          String header = col.getColumnNameExport();
          if ("VARCHAR2".equals(col.getSqlType())) {
            csv.setCurrentRecord(header, rs.getString(col.getColumnNameOracle()));
          }
          else if ("NUMBER".equals(col.getSqlType()) && col.getSqlTypeScale() == 0) {
            Long value = rs.getLong(col.getColumnNameOracle());
            if (rs.wasNull()) value = null;
            csv.setCurrentRecord(header, value);
          }
          else if ("NUMBER".equals(col.getSqlType()) && col.getSqlTypeScale() != 0) {
            Double value = rs.getDouble(col.getColumnNameOracle());
            if (rs.wasNull()) value = null;
            csv.setCurrentRecord(header, value);
          }
          else if ("DATE".equals(col.getSqlType())) {
            Calendar value = SqlUtilities.getCalendar(rs, col.getColumnNameOracle());
            csv.setCurrentRecord(header, value);
          }
          else {
            throw new IllegalStateException("tipo non gestito: "+col.getSqlType());
          }
        }
        csv.flushCurrentRecord();
        recordCount++;
      }
      os.flush();
      logger.info(""+recordCount+" record letti ed esportati");
    }
    catch (Throwable th) {
      String msg = "Eccezione " + th.getClass().getName() + ", «" + th.getMessage() + "» nell'esecuzione del metodo " + METH_NAME + (sqlWithParams != null ? "; sql:" + System.getProperty("line.separator") + sqlWithParams + System.getProperty("line.separator") : "");
      logger.error(msg, th);
      throw new Exception(msg, th);
    }
    finally {
      SqlUtilities.closeWithNoException(stmt);
      SqlUtilities.closeWithNoException(rs);
    }
  }

}
