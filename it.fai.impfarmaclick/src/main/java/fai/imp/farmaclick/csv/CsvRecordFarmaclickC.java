package fai.imp.farmaclick.csv;

import java.util.Calendar;

import org.apache.log4j.Logger;

import fai.common.csv.CsvException;
import fai.common.util.CollectionsTool;

public class CsvRecordFarmaclickC extends CsvFarmaclickCommons {
  
  
  static Logger logger = Logger.getLogger(CsvRecordFarmaclickC.class);

  
  public static final String TIPO_RECORD = "C"; // Condizioni

  public CsvRecordFarmaclickC(int lineNumber, String line) {
    super(lineNumber, line);
  }

  

  @Override
  public String getExpectedTipoRecord() {
    return TIPO_RECORD;
  }
  
  public String getCodiceCliente() {
    return getString(true, 1, 8-2+1, true, "Codice cliente");
  }


  public String getCodiceProdotto() {
    return getString(true, 9, 16-10+1, true, "Codice prodotto");
  }

  public String getCodiceMinSan() {
    return getString(true, 16, 25-17+1, true, "Codice MinSan");
  }

  public String getCodiceEAN() {
    return on0LengthStringNull(getString(true, 25, 38-26+1, false, "Codice EAN"));
  }

  public String getDescrizioneProdotto() {
    return getString(true, 38, 78-39+1, true, "Descrizione prodotto");
  }

  public String getSequenza() {
    return getString(true, 78, 1, true, "Sequenza");
  }

  public Integer getSottosequenza() {
	try {
      return getInteger(79, 81-80+1, true, "Sottosequenza");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getSottosequenza");
	  return new Integer(0);
	}
  }

  public Integer getQuantitaMassima() {
    return getInteger(81, 86-82+1, true, "Quantità massima");
  }

  public Integer getQuantitaMinima() {
    return getInteger(86, 91-87+1, true, "Quantità minima");
  }

  public Integer getQuantitaAssegnata() {
    return getInteger(91, 96-92+1, true, "Quantità assegnata");
  }

  public Integer getQuantitaOmaggio() {
    return getInteger(96, 101-97+1, true, "Quantità omaggio");
  }

  public Calendar getDataInizioValidita() {
    return getCalendar(101, 109-102+1, sdfAAAAMMGG, true, "Data inizio validità");
  }

  public Calendar getDataFineValidita() {
    return getCalendar(109, 117-110+1, sdfAAAAMMGG, true, "Data fine validità");
  }

  public Double getPrezzoDiVenditaLordoSconti() {
	try {
      return getDouble(117, 128-118+1, nf4, true, "Prezzo di vendita lordo sconti");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getPrezzoDiVenditaLordoSconti");
	  return new Double(0);
	}
  }

  public String getFillerNonUtilizzato() {
    return getString(true, 128, 135-129+1, true, "Filler - Non utilizzato");
  }

  public String getCodiceProdottoOmaggio() {
    return on0LengthStringNull(getString(true, 135, 148-136+1, false, "Codice prodotto omaggio (Se MinSan allineato a sx)"));
  }

  public String getDescrizioneProdottoOmaggio() {
    return on0LengthStringNull(getString(true, 148, 188-149+1, false, "Descrizione prodotto omaggio"));
  }

  public Integer getQuantitaProdottoOmaggio() {
    return getInteger(188, 193-189+1, true, "Quantità prodotto omaggio");
  }

  public Integer getDilazionePagamentoInGiorni() {
	try {
      return getInteger(193, 196-194+1, true, "Dilazione pagamento in giorni");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getDilazionePagamentoInGiorni");
	  return new Integer(0);
	}
  }

  public Integer getGiorniAbbuonoPerCalcoloAddebito() {
	try {
      return getInteger(196, 199-197+1, true, "Giorni abbuono per il calcolo dell'addebito");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getGiorniAbbuonoPerCalcoloAddebito");
	  return new Integer(0);
	}
  }

  public String getCodiceAddebito() {
    return on0LengthStringNull(getString(true, 199, 202-200+1, false, "Codice addebito"));
  }

  public Double getPercentualeAddebitoAggiuntiva() {
	try {
      return getDouble(202, 206-203+1, nf2, true, "Percentuale addebito aggiuntiva");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getPercentualeAddebitoAggiuntiva");
	  return new Double(0);
	}
  }

  public Double getScontoCassa() {
	try {
      return getDouble(206, 210-207+1, nf2, true, "Sconto cassa");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getScontoCassa");
	  return new Double(0);
	}
  }
  
  public String getAllineamentoScadenza() {
    String [] allowedValue = new String [] { ALLINEAMENTO_SCADENZA_DATA_FATTURA, ALLINEAMENTO_SCADENZA_FINE_MESE, null };
    String value = on0LengthStringNull(getString(true, 210, 1));
    if (CollectionsTool.contains(value, allowedValue) == false) {
      //throw new CsvException("trovato Allineamento Scadenza "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
      logger.warn("trovato Allineamento Scadenza "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
    }
    return value;
  }
  
  public String getPeriodicitaFartturazione() {
    String [] allowedValue = new String [] { PERIODICITA_FATTURAZIONE_ACCOMPAGNATORIA, PERIODICITA_FATTURAZIONE_SETTIMANALE, PERIODICITA_FATTURAZIONE_QUINDICINALE, PERIODICITA_FATTURAZIONE_MENSILE };
    String value = getString(false, 211, 1);
    if (CollectionsTool.contains(value, allowedValue) == false) {
      //throw new CsvException("trovato Periodicità Fartturazione "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
      logger.warn("trovato Periodicità Fartturazione "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
    }
    return value;
  }
  

  public Double getPrezzoNettoUnitario() {
    return getDouble(212, 225-213+1, nf6, true, "Prezzo netto unitario");
  }

  
  
  public Double getPercScontoPrezzoPubblicoVsNettoUnitario() {
    
    try {
      return getDouble(225, 233-226+1, nf6, true, "Percentuale sconto tra prezzo al pubblico e il prezzo netto unitario");
    }
    catch (CsvException e) {
      logger.warn("Problema parsing getPercScontoPrezzoPubblicoVsNettoUnitario");
      return new Double(0);
    }

  }

  public Double getPercScontoPrezzoPubblicoDerivatoVsNettoUnitario() {
    try {
      return getDouble(233, 241-234+1, nf6, true, "Percentuale sconto tra prezzo al pubblico derivato e il prezzo netto unitario");
    }
    catch (CsvException e) {
      logger.warn("Problema parsing getPercScontoPrezzoPubblicoDerivatoVsNettoUnitario");
      return new Double(0);
    }
    
  }

  
  public String getCodiceRaggruppamentoCondizione() {
    return on0LengthStringNull(getString(true, 241, 244-242+1, false, "Codice raggruppamento condizione"));
  }

  public String getFiller() {
    return getString(true, 244, 278-245+1, true, "Filler");
  }
  
}
