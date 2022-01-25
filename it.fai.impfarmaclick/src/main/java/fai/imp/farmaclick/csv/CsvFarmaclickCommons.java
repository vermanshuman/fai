package fai.imp.farmaclick.csv;

import fai.common.csv.CsvException;
import fai.common.util.CollectionsTool;

public abstract class CsvFarmaclickCommons extends CsvRecordFarmaclick {

  public static final String AZIONE_INSERIMENTO = "I";
  public static final String AZIONE_VARIAZIONE = "V";
  public static final String AZIONE_CANCELLAZIONE = "C";

  public static final String ALLINEAMENTO_SCADENZA_DATA_FATTURA = "D";
  public static final String ALLINEAMENTO_SCADENZA_FINE_MESE = "F";

  public static final String PERIODICITA_FATTURAZIONE_ACCOMPAGNATORIA = "A";
  public static final String PERIODICITA_FATTURAZIONE_SETTIMANALE = "S";
  public static final String PERIODICITA_FATTURAZIONE_QUINDICINALE = "Q";
  public static final String PERIODICITA_FATTURAZIONE_MENSILE = "M";

  public CsvFarmaclickCommons(int lineNumber, String line) {
    super(lineNumber, line);
  }

  protected String onMatchNull(String value, String valueToMatch) {
    if (value == null) return null;
    return value.equals(valueToMatch) ? null : value;
  }

  protected String on0LengthStringNull(String value) {
    return onMatchNull(value, "");
  }

  public String getAzione() {
    String [] allowedValue = new String [] { AZIONE_INSERIMENTO, AZIONE_VARIAZIONE, AZIONE_CANCELLAZIONE };
    String value = getString(true, 0, 1, true, "Azione");
    if (CollectionsTool.contains(value, allowedValue) == false) {
      throw new CsvException("trovata Azione "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
    }
    return value;
  }

  

}
