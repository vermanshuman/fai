package fai.exp.oracloud.task;

import java.sql.Connection;
import java.util.Calendar;

import org.apache.log4j.Logger;

import fai.exp.oracloud.db.FaiTableExport;

public abstract class AbstractDataIO {
  
  
  static Logger logger = Logger.getLogger(AbstractDataIO.class);

  
  protected Connection conn;
  protected Calendar nowReference = null;
  
  public AbstractDataIO(Calendar nowReference, Connection conn) {
    super();
    this.nowReference = nowReference;
    this.conn = conn;
  }

  public abstract void doJob() throws Exception;
  

  protected boolean isImportExportEnabled(FaiTableExport table) {
    int currDayOfWeek = nowReference.get(Calendar.DAY_OF_WEEK);
    //
    String pattern = table.getSmtwtfsExportPattern();
    int [] weekday = new int [] { Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY };
    for (int i = 0; i < weekday.length; i++) {
      if (currDayOfWeek == weekday[i]) {
        String option = pattern.substring(i, i+1);
        logger.info("identificativo abilitato (X)/non abilitato (-) pattern settiamanale "+pattern+" per la tabella "+table.getTableNameExport()+": "+option);
        return "X".equals(option);
      }
    }
    throw new IllegalStateException("configurazione pattern settimanale errata per la tabella "+table.getTableNameExport()+": "+pattern);
  }
  

}
