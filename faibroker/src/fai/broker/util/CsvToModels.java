package fai.broker.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fai.broker.models.OrdineIn;
import fai.broker.models.OrdineInRigaDett;
import fai.common.csv.CsvRecord;
import fai.common.util.ExceptionsTool;

public class CsvToModels {
  

  static Logger logger = Logger.getLogger(CsvToModels.class);
  
  
  public enum LineType { NONE, HEADER, ORDINE_IN, ORDINE_IN_RIGA_DETT, ORDINE_IN_PIU_RIGA_DETT };
  
  private List<OrdineIn> ordini = new ArrayList<OrdineIn>();
  
  private BufferedReader reader = null;
  private String line = null;
  private boolean waitingForHeader = true;
  private CsvRecord csv = null;
  private LineType currentLineType = LineType.NONE;
  private OrdineIn currLineOrdineIn = null;
  private OrdineInRigaDett currLineOrdineRigaDett = null;
  private int lineNumber = 0;
  
  
  public void setInputStream(InputStream is) throws Exception {
    reader = new BufferedReader(new InputStreamReader(is));
    csv = new CsvRecord();
    csv.setFieldSeparator(";");
    csv.setStringFormat('"', '"');
    csv.setDoubleNumberFormat(fai.common.util.NumberFormatFactory.newNumberFormat("####.##", ',', '\''));
    csv.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
    waitingForHeader = true;
    currentLineType = LineType.NONE;
  }
  
  public boolean moveNextLine() throws Exception {
    line = reader.readLine();
    lineNumber++;
    if (line != null) {
      parseLine();
      return true;
    }
    else {
      return false;
    }
  }
  
  public int getLineNumber() {
    return lineNumber;
  }
  
  private Boolean asBoolean(CsvRecord csv, String fieldName, String trueValue, String falseValue) throws Exception {
    String value = csv.getString(fieldName, true, false);
    if (value == null) return null;
    //
    value = value.trim();
    if (trueValue == null && falseValue != null) {
      return new Boolean(!falseValue.equals(value));
    }
    else if (trueValue != null && falseValue == null) {
      return new Boolean(trueValue.equals(value));
    }
    else if (trueValue != null && falseValue != null) {
      if (trueValue.equals(value)) {
        return true;
      }
      else if (falseValue.equals(value)) {
        return false;
      } 
      else {
        throw new IllegalArgumentException("trovato valore non gestito per la determinazione del valore booleano del campo \""+fieldName+"\": "+value);
      }
    }
    else {
      throw new IllegalArgumentException("condizione non ammessa per la determinazione del valore booleano del campo \""+fieldName+"\": almeno uno dei due valori indicanti la condizione true o la condizione false deve essere specificato");
    }
  }
  
  private void parseLine() throws Exception {
    try {
      currentLineType = LineType.NONE;
      currLineOrdineIn = null;
      currLineOrdineRigaDett = null;
      if (line == null || "".equals(line.trim())) {
        currentLineType = LineType.NONE;
        return;
      }
      if (waitingForHeader) {
        csv.parseHeaders(line);
        currentLineType = LineType.HEADER;
        waitingForHeader = false;
        return;
      }
      //
      csv.parseValues(line);
      String nomeCompletoAcquirente = csv.getString("Nome completo dell'acquirente", true, false);
      boolean parseAsRigaTestata = (nomeCompletoAcquirente != null && !"".equals(nomeCompletoAcquirente.trim()));
      Double prezzoFinale = csv.getDouble("Prezzo finale", true, false);
      boolean parseAsRigaDettaglio = prezzoFinale != null; 
      //
      if (parseAsRigaTestata) {
        currentLineType = LineType.ORDINE_IN;
        OrdineIn ordine = new OrdineIn();
        ordine.setNomeCompletoAcquirente(nomeCompletoAcquirente);
        ordine.setIdVendita(csv.getLong("ID vendita", true, true));
        ordine.setIdUtente(csv.getLong("ID utente", true, true));
        ordine.setNumeroTelefonoAcquirente(csv.getString("Numero di telefono dell'acquirente", true, false));
        ordine.setEmailAcquirente(csv.getString("Email dell'acquirente", true, false));
        ordine.setIndirizzoAcquirente1(csv.getString("Indirizzo dell'acquirente 1", true, true));
        ordine.setIndirizzoAcquirente2(csv.getString("Indirizzo dell'acquirente 2", true, false));
        ordine.setCittaAcquirente(csv.getString("Città dell'acquirente", true, true));
        ordine.setStatoAcquirente(csv.getString("Stato dell'acquirente", true, true));
        ordine.setCapAcquirente(csv.getString("CAP dell'acquirente", true, true));
        ordine.setPaeseAcquirente(csv.getString("Paese dell'acquirente", true, true));
        ordine.setQuantita(csv.getInteger("Quantità", true, true));
        ordine.setSpedizioneEImballaggio(csv.getDouble("Spedizione e imballaggio", true, true));
        ordine.setAssicurazione(csv.getDouble("Assicurazione", true, true));
        ordine.setTariffaPerContrassegno(csv.getDouble("Tariffa per contrassegno", true, true));
        ordine.setPrezzoTotale(csv.getDouble("Prezzo totale", true, true));
        ordine.setMetodoDiPagamento(csv.getInteger("Metodo di pagamento", true, true));
        ordine.setDataDiVendita(csv.getDate("Data di vendita", true, true));
        ordine.setDataDelModuloDiPagamento(csv.getDate("Data del Modulo di pagamento", true, true));
        ordine.setDataPagamento(csv.getDate("Data pagamento", true, true));
        ordine.setDataSpedizione(csv.getDate("Data spedizione", true, false));
        ordine.setDataFattura(csv.getDate("Data della fattura", true, false));
        ordine.setNumeroFattura(csv.getString("Numero della fattura", true, true));
        ordine.setFeedbackLasciato(asBoolean(csv,"Feedback lasciato", null, "No"));
        ordine.setFeedbackRicevuto(asBoolean(csv,"Feedback ricevuto", null, "No"));
        ordine.setMemoPerUsoPersonale(csv.getString("Memo per uso personale", true, false));
        ordine.setCampoPrivato(csv.getInteger("Campo privato", true, false));
        ordine.setIdTransazionePaypal(csv.getString("ID transazione PayPal", true, false));
        ordine.setServizioDiSpedizione(csv.getInteger("Servizio di spedizione", true, false));
        ordine.setOpzioneContrassegno(asBoolean(csv,"Opzione Contrassegno", null, "No"));
        ordine.setIdTransazione(csv.getInteger("ID transazione", true, false));
        ordine.setIdOrdine(csv.getInteger("ID ordine", true, true));
        ordine.setAliquotaIvaInclusa(csv.getInteger("Aliquota IVA inclusa", true, false));
        ordine.setDettagliVariazione(csv.getString("Dettagli variazione", true, false));
        ordine.setAziendaAcquirente(csv.getString("Azienda acquirente", true, false));
        ordine.setNomeCompletoFatturazione(csv.getString("Nome completo fatturazione", true, true));
        ordine.setAziendaFatturazione(csv.getString("Azienda fatturazione", true, false));
        ordine.setCfFatturazione(csv.getString("CF fatturazione", true, true));
        ordine.setPivaFatturazione(csv.getString("PIva fatturazione", true, false));
        ordine.setIndirizzoFatturazione(csv.getString("Indirizzo fatturazione", true, true));
        ordine.setCittaFatturazione(csv.getString("Città fatturazione", true, true));
        ordine.setStatoFatturazione(csv.getString("Stato fatturazione", true, true));
        ordine.setCapFatturazione(csv.getString("CAP fatturazione", true, true));
        ordine.setPaeseFatturazione(csv.getString("Paese fatturazione", true, true));
        ordine.setCodicefe(csv.getString("codiceFE", true, false));
        ordine.setPec(csv.getString("PEC", true, false));
        ordine.setNote(csv.getString("Note", true, false));
        ordine.setQuantita(csv.getInteger("Quantità", true, true));
        ordine.setAliquotaIvaInclusa(csv.getInteger("Aliquota IVA inclusa", true, false));
        ordine.setDettagliVariazione(csv.getString("Dettagli variazione", true, false));
        ordini.add(ordine);
        currLineOrdineIn = ordine;
      }
      if (parseAsRigaDettaglio) {
        currentLineType = LineType.ORDINE_IN_RIGA_DETT;
        OrdineInRigaDett riga = new OrdineInRigaDett();
        //riga.setMagazzinoAcronym(csv.getString("Magazzino", true, true));
        riga.setNumeroOggetto(csv.getString("Numero oggetto", true, true));
        riga.setTitoloInserzione(csv.getString("Titolo inserzione", true, false));
        riga.setEtichettaPersonalizzata(csv.getString("Etichetta personalizzata", true, true));
        riga.setQuantita(csv.getInteger("Quantità", true, true));
        riga.setPrezzoFinale(prezzoFinale);
        riga.setAliquotaIvaInclusa(csv.getInteger("Aliquota IVA inclusa", true, false));
        riga.setIdProdottoUnivoco(csv.getString("ID prodotto univoco", true, false));
        riga.setProductidtype(csv.getString("ProductIDType", true, false));
        riga.setProductidvalue2(csv.getDouble("ProductIDValue-2", true, false));
        riga.setProductidvalue(csv.getDouble("ProductIDValue", true, false));
        riga.setDettagliVariazione(csv.getString("Dettagli variazione", true, false));
        riga.setCoupon("Coupon".equals(riga.getTitoloInserzione()));
        ordini.get(ordini.size()-1).addRigaDett(riga);
        currLineOrdineRigaDett = riga;
      }
      if (parseAsRigaTestata && parseAsRigaDettaglio) {
        currentLineType = LineType.ORDINE_IN_PIU_RIGA_DETT;
      }
    }
    catch (Throwable th) {
      String msg = ExceptionsTool.getExceptionDescription("errore di parsing alla riga "+lineNumber+", interpretata come "+currentLineType, th);
      logger.error(msg, th);
      throw new IllegalStateException(msg, th);
    }
  }
  
  public LineType getLineType() {
    return currentLineType;
  }
  
  
  
  public OrdineIn getCurrLineOrdineIn() {
    return currLineOrdineIn;
  }

  public OrdineInRigaDett getCurrLineOrdineRigaDett() {
    return currLineOrdineRigaDett;
  }

  public List<OrdineIn> getOrdini() {
    return ordini;
  }
  
  
  

}
