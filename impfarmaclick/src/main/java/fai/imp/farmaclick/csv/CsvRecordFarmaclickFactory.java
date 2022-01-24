package fai.imp.farmaclick.csv;

import java.lang.reflect.Constructor;

import fai.common.csv.CsvException;

public class CsvRecordFarmaclickFactory {
  
  public static final String TIPO_RECORD_C = "C"; // Condizioni
  public static final String TIPO_RECORD_A = "A"; // Addebito Finanziario
  public static final String TIPO_RECORD_R = "R"; // Raggruppamenti Condizioni
  public static final String TIPO_RECORD_V = "V"; // Raggruppamenti Condizioni
  public static final String TIPO_RECORD_Z = "Z"; // Record di Chiusura


  public static CsvRecordFarmaclick asCsvRecordFarmaclick(int lineNumber, String line) throws CsvException {
    CsvRecordFarmaclick record = null;
    int typeIdx = 8;
    if (line.length() < typeIdx + 1) {
      throw new CsvException("lunghezza della riga n."+lineNumber+", pari a "+line.length()+" caratteri, insufficiente a contenere il tipo; riga: "+line);
    }
    try {
      String type = line.substring(typeIdx, typeIdx+1);
      Class cl = Class.forName(CsvRecordFarmaclick.class.getName()+type);
      Constructor<CsvRecordFarmaclick> c = cl.getConstructor(int.class, String.class);
      record = c.newInstance(lineNumber, line);
      return record;
    }
    catch (Throwable e) {
      throw new CsvException("creazione dell'istanza di "+CsvRecordFarmaclick.class.getName()+" non riuscita; riga n."+lineNumber+" processata: "+line, e);
    }
    
  }


}
