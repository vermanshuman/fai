package fai.imp.farmaclick.csv;

import fai.common.util.CollectionsTool;

public class CsvRecordFarmaclickR extends CsvFarmaclickCommons {  
  
  public static final String TIPO_RECORD = "R"; // Raggruppamenti Condizioni
  
  public CsvRecordFarmaclickR(int lineNumber, String line) {
    super(lineNumber, line);
  }

  @Override
  public String getExpectedTipoRecord() {
    return TIPO_RECORD;
  }

  
  public String getCodiceCliente() {
    return getString(true, 1, 8-2+1, true, "Codice cliente");
  }

  public String getCodiceRaggruppamentoCondizione() {
    return getString(true, 9, 12-10+1, true, "Codice raggruppamento condizione");
  }

  public String getRiga() {
    return getString(true, 12, 1, true, "Riga");
  }

  public String getDescrizioneRaggruppamentoCondizione() {
    return getString(true, 13, 91-14+1, true, "Descrizione raggruppamento condizione");
  }



  public String getFiller() {
    return getString(true, 91, 278-92+1, true, "Filler");
  }
  

    
  
}
  