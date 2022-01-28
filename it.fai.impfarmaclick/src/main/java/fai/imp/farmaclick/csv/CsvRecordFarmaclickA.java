package fai.imp.farmaclick.csv;

import org.apache.log4j.Logger;

import fai.common.util.CollectionsTool;

public class CsvRecordFarmaclickA extends CsvFarmaclickCommons {
  static Logger logger = Logger.getLogger(CsvRecordFarmaclickA.class);
  public static final String TIPO_RECORD = "A"; // Addebito Finanziario
  

  public CsvRecordFarmaclickA(int lineNumber, String line) {
    super(lineNumber, line);
  }

  
  @Override
  public String getExpectedTipoRecord() {
    return TIPO_RECORD;
  }
  public String getCodiceCliente() {
    return getString(true, 1, 8-2+1, true, "Codice cliente");
  }


  public String getCodiceAddebitoFinanziario() {
    return getString(true, 9, 12-10+1, true, "Codice addebito finanziario");
  }

  public String getDescrizioneAddebitoFinanziario() {
    return getString(true, 12, 37-13+1, true, "Descrizione addebito finanziario");
  }

  public Double getPercentualeAddebitoFinanziario() {
	try {
	  return getDouble(37, 41-38+1, nf2, true, "Percentuale addebito finanziario");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getPercentualeAddebitoFinanziario");
	  return new Double(0);
	}
  }

  public Integer getNumeroGiorniAnnoCommerciale() {
	try {
      return getInteger(41, 44-42+1, true, "Numero giorni anno commerciale");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getNumeroGiorniAnnoCommerciale");
	  return new Integer(0);
	}
  }

  public String getFiller() {
    return getString(true, 44, 278-45+1, true, "Filler");
  }


}
  