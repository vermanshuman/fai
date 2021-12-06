package fai.imp.farmaclick.csv;

import fai.common.csv.CsvException;
import fai.common.util.CollectionsTool;

public class CsvRecordFarmaclickV extends CsvFarmaclickCommons {
  
  public static final String TIPO_RECORD = "V"; // Raggruppamenti Condizioni

  public static String DESTINAZIONE_ADDEBITO_DDT_O_FATTURA_ACCOMPAGNATORIA = "B";
  public static String DESTINAZIONE_ADDEBITO_FATTURA = "F";
  

  public CsvRecordFarmaclickV(int lineNumber, String line) {
    super(lineNumber, line);
  }

  @Override
  public String getExpectedTipoRecord() {
    return TIPO_RECORD;
  }

  public String getCodiceCliente() {
    return getString(true, 1, 8-2+1, true, "Codice cliente");
  }

  public String getCodiceAddebito() {
    return getString(true, 9, 11-10+1, true, "Codice addebito");
  }

  public String getDescrizioneAddebito() {
    return getString(true, 11, 41-12+1, true, "Descrizione addebito");
  }

  public String getDestinazioneAddebito() {
    String [] allowedValue = new String [] { DESTINAZIONE_ADDEBITO_DDT_O_FATTURA_ACCOMPAGNATORIA, DESTINAZIONE_ADDEBITO_FATTURA };
    String value = getString(true, 1, 1, true, "Azione");
    if (CollectionsTool.contains(value, allowedValue) == false) {
      throw new CsvException("trovata Destinazione addebito "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
    }
    return value;
  }

  public String getPeriodicitaFartturazione() {
    String [] allowedValue = new String [] { PERIODICITA_FATTURAZIONE_ACCOMPAGNATORIA, PERIODICITA_FATTURAZIONE_SETTIMANALE, PERIODICITA_FATTURAZIONE_QUINDICINALE, PERIODICITA_FATTURAZIONE_MENSILE };
    String value = getString(false, 42, 1);
    if (CollectionsTool.contains(value, allowedValue) == false) {
      throw new CsvException("trovato Periodicità Fartturazione "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
    }
    return value;
  }
  
  public Double getImportoMassimoDaAddebitare() {
    return getDouble(43, 53-44+1, nf4, true, "Importo massimo da addebitare. Oltre tale importo non viene più applicato l'addebito");
  }

  public Integer getNumeroMassimiAddebiti() {
    Integer value = getInteger(53, 57-54+1, false);
    String destinazioneAddebito = getDestinazioneAddebito();
    if (value == null && DESTINAZIONE_ADDEBITO_FATTURA.equals(destinazioneAddebito)) {
      throw new CsvException("trovata Numero massimi addebiti null non ammesso con Destinazione addebito "+destinazioneAddebito+" (riga: "+getLine()+")");
    }
    return value;
  }

  public Double getImportoDaAddebitare() {
    return getDouble(57, 68-58+1, nf4, true, "Importo da addebitare (Deve essere addebitato ad ogni documento)");
  }

  public Double getPercentualeDiAddebitoSuImponibile() {
    return getDouble(68, 72-69+1, nf2, true, "Percentuale di addebito su imponibile");
  }

  public String getFiller() {
    return getString(true, 72, 278-73+1, true, "Filler");
  }



  
  
}
  