package fai.imp.farmaclick.csv;

import fai.common.csv.CsvException;

public class CsvRecordFarmaclickD extends CsvRecordFarmaclick {
  
  
  public static final String TIPO_RECORD = "D"; // Reset Campagne e Listino


  public CsvRecordFarmaclickD(int lineNumber, String line) {
    super(lineNumber, line);
  }
  
  @Override
  public String getExpectedTipoRecord() {
    return TIPO_RECORD;
  }
  
  public String getCodiceCliente() {
    return getString(true, 1, 8-2+1, true, "Codice Cliente");
  }
  
  public String getTipoRecord2() {
    String s = getString(true, 0, 1, true, "Tipo Record (occorrenza specifica)");
    if (!TIPO_RECORD.equals(s)) {
      throw new CsvException("trovato Tipo Record (occorrenza specifica) "+s+"; valori ammessi: "+TIPO_RECORD+" (riga: "+getLine()+")");
    }
    return s;
    
  }
  
  public String getFiller() {
    return getString(true, 9, 278-10+1, true, "Filler");
  }


}
