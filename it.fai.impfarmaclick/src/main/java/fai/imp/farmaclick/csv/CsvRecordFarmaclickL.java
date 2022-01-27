package fai.imp.farmaclick.csv;

import org.apache.log4j.Logger;

import fai.common.csv.CsvException;
import fai.common.util.CollectionsTool;

public class CsvRecordFarmaclickL extends CsvFarmaclickCommons {
  
  static Logger logger = Logger.getLogger(CsvRecordFarmaclickL.class);
  
  public static final String TIPO_RECORD = "L"; // Listino
  

  public CsvRecordFarmaclickL(int lineNumber, String line) {
    super(lineNumber, line);
  }
  
  @Override
  public String getExpectedTipoRecord() {
    return TIPO_RECORD;
  }

  public String getAzione() {
    String [] allowedValue = new String [] { AZIONE_INSERIMENTO, AZIONE_VARIAZIONE, AZIONE_CANCELLAZIONE };
    String value = getString(true, 0, 1, true, "Azione");
    if (CollectionsTool.contains(value, allowedValue) == false) {
      throw new CsvException("trovata Azione "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
    }
    return value;
  }
  
  public String getCodiceCliente() {
    return getString(true, 1, 8-2+1, true, "Codice cliente");
  }

  public String getCodiceProdotto() {
    return getString(true, 9, 16-10+1, true, "Codice Prodotto");
  }

  public String getCodiceMinSan() {
    return on0LengthStringNull(getString(true, 16, 25-17+1, false, "Codice MinSan"));
  }

  public String getCodiceEAN() {
    return on0LengthStringNull(getString(true, 25, 38-26+1, false, "Codice EAN"));
  }

  public String getDescrizioneProdotto() {
    return getString(true, 38, 78-39+1, true, "Descrizione prodotto");
  }

  public String getCategoriaTipoProdotto() {
    return getString(true, 78, 80-79+1, true, "Categoria tipo prodotto");
  }

  public Double getPrezzoAlPubblico() {
    return getDouble(80, 91-81+1, nf4, true, "Prezzo al pubblico");
  }

  public Double getAliquotaIVA() {
    return getDouble(91, 94-92+1, nf1, true, "Aliquota IVA");
  }

  public Double getPrezzoDiVenditaAListinoLordoSconti() {
    return getDouble(94, 105-95+1, nf4, true, "Prezzo di vendita a listino lordo sconti");
  }

  public String getFillerNonUtilizzato() {
    String s = getString(false, 105, 1, false, "Filler - Non utilizzato");
    final String EXPECTED_VALUE = " ";
    if (!EXPECTED_VALUE.equals(s)) {
      throw new CsvException("trovato Fine Record "+s+"; valori ammessi: "+EXPECTED_VALUE.length()+" caratteri spazio (riga: "+getLine()+")");
    }
    return s;
  }

  public Double getSconto1() {
    return getDouble(106, 110-107+1, nf2, true, "Sconto 1");
  }

  public Double getSconto2() {
    return getDouble(110, 114-111+1, nf2, true, "Sconto 2");
  }

  public Integer getDilazionePagamentoInGiorni() {
	try {
      return getInteger(114, 117-115+1, true, "Dilazione pagamento in giorni");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getDilazionePagamentoInGiorni");
	  return new Integer(0);
	}
  }

  public Integer getGiorniAbbuonoPerCalcoloAddebito() {
	try {
      return getInteger(117, 120-118+1, true, "Giorni abbuono per il calcolo dell'addebito");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getGiorniAbbuonoPerCalcoloAddebito");
	  return new Integer(0);
	}
  }

  public String getCodiceAddebito() {
    return on0LengthStringNull(getString(true, 120, 123-121+1, false, "Codice addebito"));
  }

  public Double getPercentualeAddebitoAggiuntiva() {
	try {
      return getDouble(123, 127-124+1, nf2, true, "Percentuale addebito aggiuntiva");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getPercentualeAddebitoAggiuntiva");
	  return new Double(0);
	}
  }

  public Double getScontoCassa() {
	try {
      return getDouble(127, 131-128+1, nf2, true, "Sconto cassa");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getScontoCassa");
	  return new Double(0);
	}
  }
  
  public String getAllineamentoScadenza() {
    String [] allowedValue = new String [] { ALLINEAMENTO_SCADENZA_DATA_FATTURA, ALLINEAMENTO_SCADENZA_FINE_MESE };
    String value = getString(false, 131, 1);
    if (CollectionsTool.contains(value, allowedValue) == false) {
      //throw new CsvException("trovato Allineamento Scadenza "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
      logger.warn("trovato Allineamento Scadenza "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
    }
    return value;
  }
  
  public String getPeriodicitaFartturazione() {
    String [] allowedValue = new String [] { PERIODICITA_FATTURAZIONE_ACCOMPAGNATORIA, PERIODICITA_FATTURAZIONE_SETTIMANALE, PERIODICITA_FATTURAZIONE_QUINDICINALE, PERIODICITA_FATTURAZIONE_MENSILE };
    String value = getString(false, 132, 1);
    if (CollectionsTool.contains(value, allowedValue) == false) {
      //throw new CsvException("trovato Periodicità Fartturazione "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
      logger.warn("trovato Periodicità Fartturazione "+value+"; valori ammessi: "+CollectionsTool.asJson(allowedValue)+" (riga: "+getLine()+")");
    }
    return value;
  }

  public Integer getQuantitaPerColloDiVendita() {
	try {
      return getInteger(133, 138-134+1, true, "Quantità per collo di vendita");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getQuantitaPerColloDiVendita");
	  return new Integer(0);
	}
  }

  public Double getPrezzoDiVenditaConsigliato() {
	try {
      return getDouble(138, 149-139+1, nf4, true, "Prezzo di vendita consigliato");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getPrezzoDiVenditaConsigliato");
	  return new Double(0);
	}
  }

  public Double getPrezzoAlPubblicoDeivato() {
	try {
      return getDouble(149, 160-150+1, nf4, true, "Prezzo al pubblico deivato");
	}catch(Exception ex) {
	  logger.warn("problema con parsing getPrezzoAlPubblicoDeivato");
	  return new Double(0);
	}
  }

  public Double getPrezzoNettoUnitario() {
    return getDouble(160, 173-161+1, nf6, true, "Prezzo netto unitario");
  }

  
  public Double getPercScontoPrezzoPubblicoVsNettoUnitario() {
    try {
      return getDouble(173, 181-174+1, nf6, true, "Percentuale sconto tra prezzo al pubblico e il prezzo netto unitario");
    }
    catch (CsvException e) {
      logger.warn("problema con parsing getPercScontoPrezzoPubblicoVsNettoUnitario");
      return new Double(0);
    }
  }

  public Double getPercScontoPrezzoPubblicoDerivatoVsNettoUnitario() {
    try {
      return getDouble(181, 189-182+1, nf6, true, "Percentuale sconto tra prezzo al pubblico derivato e il prezzo netto unitario");
    }
    catch (CsvException e) {
      logger.warn("problema con parsing getPercScontoPrezzoPubblicoDerivatoVsNettoUnitario");
      return new Double(0);
    }
    
  }

  
  public Double getSommaSconto1ESconto2() {
    return getDouble(189, 193-190+1, nf2, true, "Somma sconto 1 e sconto 2");
  }

  public String getFiller() {
    return getString(true, 193, 278-194+1, true, "Filler");
  }

}
