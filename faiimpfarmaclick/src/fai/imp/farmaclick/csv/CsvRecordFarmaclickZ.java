package fai.imp.farmaclick.csv;

import java.util.Calendar;

import fai.common.util.CalendarWrapper;
import fai.common.util.CollectionsTool;

public class CsvRecordFarmaclickZ extends CsvFarmaclickCommons {
  
  public static final String TIPO_RECORD = "Z"; // Record di Chiusura o Fine Listino
  
  public CsvRecordFarmaclickZ(int lineNumber, String line) {
    super(lineNumber, line);
  }

  @Override
  public String getExpectedTipoRecord() {
    return TIPO_RECORD;
  }

  public String getCodiceCliente() {
    return getString(true, 1, 8-2+1, true, "Codice cliente");
  }

  public Calendar getDataOraGenerazione() {
    String yyyyMMdd = getString(true, 9, 17-10+1, true, "Data di generazione");
    String HHmmss = getString(true, 17, 23-18+1, true, "Ora di generazione");
    return CalendarWrapper.getInstance(CalendarWrapper.getInstance(yyyyMMdd, "yyyyMMdd"), CalendarWrapper.getInstance(HHmmss, "HHmmss")).getCalendar();
  }

  

}
  