package fai.broker.models;

import it.swdes.models.*;

public class OrdineIn extends AbstractModel {


  public static int OID;
  public static int ID_VENDITA;
  public static int ID_UTENTE;
  public static int NOME_COMPLETO_ACQUIRENTE;
  public static int NUMERO_TELEFONO_ACQUIRENTE;
  public static int EMAIL_ACQUIRENTE;
  public static int INDIRIZZO_ACQUIRENTE1;
  public static int INDIRIZZO_ACQUIRENTE2;
  public static int CITTA_ACQUIRENTE;
  public static int STATO_ACQUIRENTE;
  public static int CAP_ACQUIRENTE;
  public static int PAESE_ACQUIRENTE;
  public static int QUANTITA;
  public static int SPEDIZIONE_EIMBALLAGGIO;
  public static int ASSICURAZIONE;
  public static int TARIFFA_PER_CONTRASSEGNO;
  public static int PREZZO_TOTALE;
  public static int METODO_DI_PAGAMENTO;
  public static int DATA_DI_VENDITA;
  public static int DATA_DEL_MODULO_DI_PAGAMENTO;
  public static int DATA_PAGAMENTO;
  public static int DATA_SPEDIZIONE;
  public static int DATA_FATTURA;
  public static int NUMERO_FATTURA;
  public static int FEEDBACK_LASCIATO;
  public static int FEEDBACK_RICEVUTO;
  public static int MEMO_PER_USO_PERSONALE;
  public static int CAMPO_PRIVATO;
  public static int ID_TRANSAZIONE_PAYPAL;
  public static int SERVIZIO_DI_SPEDIZIONE;
  public static int OPZIONE_CONTRASSEGNO;
  public static int ID_TRANSAZIONE;
  public static int ID_ORDINE;
  public static int ALIQUOTA_IVA_INCLUSA;
  public static int DETTAGLI_VARIAZIONE;
  public static int AZIENDA_ACQUIRENTE;
  public static int NOME_COMPLETO_FATTURAZIONE;
  public static int AZIENDA_FATTURAZIONE;
  public static int CF_FATTURAZIONE;
  public static int PIVA_FATTURAZIONE;
  public static int INDIRIZZO_FATTURAZIONE;
  public static int CITTA_FATTURAZIONE;
  public static int STATO_FATTURAZIONE;
  public static int CAP_FATTURAZIONE;
  public static int PAESE_FATTURAZIONE;
  public static int CODICEFE;
  public static int PEC;
  public static int NOTE;
  public static int RIGA_DETT;
  public static int STATUS;

  public static final Attribute[] attributes = buildAttributes(OrdineIn.class, new Attribute[] { //@formatter:off
    new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
    new Attribute("idVendita", null, Long.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("idUtente", null, Long.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("nomeCompletoAcquirente", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("numeroTelefonoAcquirente", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("emailAcquirente", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("indirizzoAcquirente1", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("indirizzoAcquirente2", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("cittaAcquirente", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("statoAcquirente", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("capAcquirente", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("paeseAcquirente", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("quantita", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("spedizioneEImballaggio", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("assicurazione", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("tariffaPerContrassegno", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("prezzoTotale", null, Double.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("metodoDiPagamento", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("dataDiVendita", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("dataDelModuloDiPagamento", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("dataPagamento", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("dataSpedizione", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("dataFattura", null, java.util.Calendar.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("numeroFattura", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("feedbackLasciato", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("feedbackRicevuto", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("memoPerUsoPersonale", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("campoPrivato", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("idTransazionePaypal", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("servizioDiSpedizione", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("opzioneContrassegno", null, Boolean.class, Boolean.FALSE, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("idTransazione", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("idOrdine", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("aliquotaIvaInclusa", null, Integer.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("dettagliVariazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("aziendaAcquirente", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("nomeCompletoFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("aziendaFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("cfFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("pivaFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("indirizzoFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("cittaFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("statoFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("capFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("paeseFatturazione", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("codicefe", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("pec", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("note", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    new Attribute("rigaDett", null, OrdineInRigaDett[].class, null, false, Attribute.LOOKUP, true, Attribute.NO_OPTION),
    new Attribute("status", null, StatusInfo.class, null, false, Attribute.DETAIL, false, Attribute.NO_OPTION),
  }); //@formatter:on


  /** CONSTRUCTOR **/
  public OrdineIn() {
  }


  /** OID **/
  public boolean setOid(Long oid) {
    return setLong(OID, oid);
  }

  public Long getOid() {
    return getLong(OID);
  }


  /** ID_VENDITA **/
  public boolean setIdVendita(Long idVendita) {
    return setLong(ID_VENDITA, idVendita);
  }

  public Long getIdVendita() {
    return getLong(ID_VENDITA);
  }


  /** ID_UTENTE **/
  public boolean setIdUtente(Long idUtente) {
    return setLong(ID_UTENTE, idUtente);
  }

  public Long getIdUtente() {
    return getLong(ID_UTENTE);
  }


  /** NOME_COMPLETO_ACQUIRENTE **/
  public boolean setNomeCompletoAcquirente(String nomeCompletoAcquirente) {
    return setString(NOME_COMPLETO_ACQUIRENTE, nomeCompletoAcquirente);
  }

  public String getNomeCompletoAcquirente() {
    return getString(NOME_COMPLETO_ACQUIRENTE);
  }


  /** NUMERO_TELEFONO_ACQUIRENTE **/
  public boolean setNumeroTelefonoAcquirente(String numeroTelefonoAcquirente) {
    return setString(NUMERO_TELEFONO_ACQUIRENTE, numeroTelefonoAcquirente);
  }

  public String getNumeroTelefonoAcquirente() {
    return getString(NUMERO_TELEFONO_ACQUIRENTE);
  }


  /** EMAIL_ACQUIRENTE **/
  public boolean setEmailAcquirente(String emailAcquirente) {
    return setString(EMAIL_ACQUIRENTE, emailAcquirente);
  }

  public String getEmailAcquirente() {
    return getString(EMAIL_ACQUIRENTE);
  }


  /** INDIRIZZO_ACQUIRENTE1 **/
  public boolean setIndirizzoAcquirente1(String indirizzoAcquirente1) {
    return setString(INDIRIZZO_ACQUIRENTE1, indirizzoAcquirente1);
  }

  public String getIndirizzoAcquirente1() {
    return getString(INDIRIZZO_ACQUIRENTE1);
  }


  /** INDIRIZZO_ACQUIRENTE2 **/
  public boolean setIndirizzoAcquirente2(String indirizzoAcquirente2) {
    return setString(INDIRIZZO_ACQUIRENTE2, indirizzoAcquirente2);
  }

  public String getIndirizzoAcquirente2() {
    return getString(INDIRIZZO_ACQUIRENTE2);
  }


  /** CITTA_ACQUIRENTE **/
  public boolean setCittaAcquirente(String cittaAcquirente) {
    return setString(CITTA_ACQUIRENTE, cittaAcquirente);
  }

  public String getCittaAcquirente() {
    return getString(CITTA_ACQUIRENTE);
  }


  /** STATO_ACQUIRENTE **/
  public boolean setStatoAcquirente(String statoAcquirente) {
    return setString(STATO_ACQUIRENTE, statoAcquirente);
  }

  public String getStatoAcquirente() {
    return getString(STATO_ACQUIRENTE);
  }


  /** CAP_ACQUIRENTE **/
  public boolean setCapAcquirente(String capAcquirente) {
    return setString(CAP_ACQUIRENTE, capAcquirente);
  }

  public String getCapAcquirente() {
    return getString(CAP_ACQUIRENTE);
  }


  /** PAESE_ACQUIRENTE **/
  public boolean setPaeseAcquirente(String paeseAcquirente) {
    return setString(PAESE_ACQUIRENTE, paeseAcquirente);
  }

  public String getPaeseAcquirente() {
    return getString(PAESE_ACQUIRENTE);
  }


  /** QUANTITA **/
  public boolean setQuantita(Integer quantita) {
    return setInt(QUANTITA, quantita);
  }

  public Integer getQuantita() {
    return getInt(QUANTITA);
  }


  /** SPEDIZIONE_EIMBALLAGGIO **/
  public boolean setSpedizioneEImballaggio(Double spedizioneEImballaggio) {
    return setDouble(SPEDIZIONE_EIMBALLAGGIO, spedizioneEImballaggio);
  }

  public Double getSpedizioneEImballaggio() {
    return getDouble(SPEDIZIONE_EIMBALLAGGIO);
  }


  /** ASSICURAZIONE **/
  public boolean setAssicurazione(Double assicurazione) {
    return setDouble(ASSICURAZIONE, assicurazione);
  }

  public Double getAssicurazione() {
    return getDouble(ASSICURAZIONE);
  }


  /** TARIFFA_PER_CONTRASSEGNO **/
  public boolean setTariffaPerContrassegno(Double tariffaPerContrassegno) {
    return setDouble(TARIFFA_PER_CONTRASSEGNO, tariffaPerContrassegno);
  }

  public Double getTariffaPerContrassegno() {
    return getDouble(TARIFFA_PER_CONTRASSEGNO);
  }


  /** PREZZO_TOTALE **/
  public boolean setPrezzoTotale(Double prezzoTotale) {
    return setDouble(PREZZO_TOTALE, prezzoTotale);
  }

  public Double getPrezzoTotale() {
    return getDouble(PREZZO_TOTALE);
  }


  /** METODO_DI_PAGAMENTO **/
  public boolean setMetodoDiPagamento(Integer metodoDiPagamento) {
    return setInt(METODO_DI_PAGAMENTO, metodoDiPagamento);
  }

  public Integer getMetodoDiPagamento() {
    return getInt(METODO_DI_PAGAMENTO);
  }


  /** DATA_DI_VENDITA **/
  public boolean setDataDiVendita(java.util.Calendar dataDiVendita) {
    return setCalendar(DATA_DI_VENDITA, dataDiVendita);
  }

  public java.util.Calendar getDataDiVendita() {
    return getCalendar(DATA_DI_VENDITA);
  }


  /** DATA_DEL_MODULO_DI_PAGAMENTO **/
  public boolean setDataDelModuloDiPagamento(java.util.Calendar dataDelModuloDiPagamento) {
    return setCalendar(DATA_DEL_MODULO_DI_PAGAMENTO, dataDelModuloDiPagamento);
  }

  public java.util.Calendar getDataDelModuloDiPagamento() {
    return getCalendar(DATA_DEL_MODULO_DI_PAGAMENTO);
  }


  /** DATA_PAGAMENTO **/
  public boolean setDataPagamento(java.util.Calendar dataPagamento) {
    return setCalendar(DATA_PAGAMENTO, dataPagamento);
  }

  public java.util.Calendar getDataPagamento() {
    return getCalendar(DATA_PAGAMENTO);
  }


  /** DATA_SPEDIZIONE **/
  public boolean setDataSpedizione(java.util.Calendar dataSpedizione) {
    return setCalendar(DATA_SPEDIZIONE, dataSpedizione);
  }

  public java.util.Calendar getDataSpedizione() {
    return getCalendar(DATA_SPEDIZIONE);
  }


  /** DATA_FATTURA **/
  public boolean setDataFattura(java.util.Calendar dataFattura) {
    return setCalendar(DATA_FATTURA, dataFattura);
  }

  public java.util.Calendar getDataFattura() {
    return getCalendar(DATA_FATTURA);
  }


  /** NUMERO_FATTURA **/
  public boolean setNumeroFattura(String numeroFattura) {
    return setString(NUMERO_FATTURA, numeroFattura);
  }

  public String getNumeroFattura() {
    return getString(NUMERO_FATTURA);
  }


  /** FEEDBACK_LASCIATO **/
  public boolean setFeedbackLasciato(Boolean feedbackLasciato) {
    return setBoolean(FEEDBACK_LASCIATO, feedbackLasciato);
  }

  public Boolean getFeedbackLasciato() {
    return getBoolean(FEEDBACK_LASCIATO);
  }

  public boolean isFeedbackLasciato() {
    return isBoolean(FEEDBACK_LASCIATO);
  }


  /** FEEDBACK_RICEVUTO **/
  public boolean setFeedbackRicevuto(Boolean feedbackRicevuto) {
    return setBoolean(FEEDBACK_RICEVUTO, feedbackRicevuto);
  }

  public Boolean getFeedbackRicevuto() {
    return getBoolean(FEEDBACK_RICEVUTO);
  }

  public boolean isFeedbackRicevuto() {
    return isBoolean(FEEDBACK_RICEVUTO);
  }


  /** MEMO_PER_USO_PERSONALE **/
  public boolean setMemoPerUsoPersonale(String memoPerUsoPersonale) {
    return setString(MEMO_PER_USO_PERSONALE, memoPerUsoPersonale);
  }

  public String getMemoPerUsoPersonale() {
    return getString(MEMO_PER_USO_PERSONALE);
  }


  /** CAMPO_PRIVATO **/
  public boolean setCampoPrivato(Integer campoPrivato) {
    return setInt(CAMPO_PRIVATO, campoPrivato);
  }

  public Integer getCampoPrivato() {
    return getInt(CAMPO_PRIVATO);
  }


  /** ID_TRANSAZIONE_PAYPAL **/
  public boolean setIdTransazionePaypal(String idTransazionePaypal) {
    return setString(ID_TRANSAZIONE_PAYPAL, idTransazionePaypal);
  }

  public String getIdTransazionePaypal() {
    return getString(ID_TRANSAZIONE_PAYPAL);
  }


  /** SERVIZIO_DI_SPEDIZIONE **/
  public boolean setServizioDiSpedizione(Integer servizioDiSpedizione) {
    return setInt(SERVIZIO_DI_SPEDIZIONE, servizioDiSpedizione);
  }

  public Integer getServizioDiSpedizione() {
    return getInt(SERVIZIO_DI_SPEDIZIONE);
  }


  /** OPZIONE_CONTRASSEGNO **/
  public boolean setOpzioneContrassegno(Boolean opzioneContrassegno) {
    return setBoolean(OPZIONE_CONTRASSEGNO, opzioneContrassegno);
  }

  public Boolean getOpzioneContrassegno() {
    return getBoolean(OPZIONE_CONTRASSEGNO);
  }

  public boolean isOpzioneContrassegno() {
    return isBoolean(OPZIONE_CONTRASSEGNO);
  }


  /** ID_TRANSAZIONE **/
  public boolean setIdTransazione(Integer idTransazione) {
    return setInt(ID_TRANSAZIONE, idTransazione);
  }

  public Integer getIdTransazione() {
    return getInt(ID_TRANSAZIONE);
  }


  /** ID_ORDINE **/
  public boolean setIdOrdine(Integer idOrdine) {
    return setInt(ID_ORDINE, idOrdine);
  }

  public Integer getIdOrdine() {
    return getInt(ID_ORDINE);
  }


  /** ALIQUOTA_IVA_INCLUSA **/
  public boolean setAliquotaIvaInclusa(Integer aliquotaIvaInclusa) {
    return setInt(ALIQUOTA_IVA_INCLUSA, aliquotaIvaInclusa);
  }

  public Integer getAliquotaIvaInclusa() {
    return getInt(ALIQUOTA_IVA_INCLUSA);
  }


  /** DETTAGLI_VARIAZIONE **/
  public boolean setDettagliVariazione(String dettagliVariazione) {
    return setString(DETTAGLI_VARIAZIONE, dettagliVariazione);
  }

  public String getDettagliVariazione() {
    return getString(DETTAGLI_VARIAZIONE);
  }


  /** AZIENDA_ACQUIRENTE **/
  public boolean setAziendaAcquirente(String aziendaAcquirente) {
    return setString(AZIENDA_ACQUIRENTE, aziendaAcquirente);
  }

  public String getAziendaAcquirente() {
    return getString(AZIENDA_ACQUIRENTE);
  }


  /** NOME_COMPLETO_FATTURAZIONE **/
  public boolean setNomeCompletoFatturazione(String nomeCompletoFatturazione) {
    return setString(NOME_COMPLETO_FATTURAZIONE, nomeCompletoFatturazione);
  }

  public String getNomeCompletoFatturazione() {
    return getString(NOME_COMPLETO_FATTURAZIONE);
  }


  /** AZIENDA_FATTURAZIONE **/
  public boolean setAziendaFatturazione(String aziendaFatturazione) {
    return setString(AZIENDA_FATTURAZIONE, aziendaFatturazione);
  }

  public String getAziendaFatturazione() {
    return getString(AZIENDA_FATTURAZIONE);
  }


  /** CF_FATTURAZIONE **/
  public boolean setCfFatturazione(String cfFatturazione) {
    return setString(CF_FATTURAZIONE, cfFatturazione);
  }

  public String getCfFatturazione() {
    return getString(CF_FATTURAZIONE);
  }


  /** PIVA_FATTURAZIONE **/
  public boolean setPivaFatturazione(String pivaFatturazione) {
    return setString(PIVA_FATTURAZIONE, pivaFatturazione);
  }

  public String getPivaFatturazione() {
    return getString(PIVA_FATTURAZIONE);
  }


  /** INDIRIZZO_FATTURAZIONE **/
  public boolean setIndirizzoFatturazione(String indirizzoFatturazione) {
    return setString(INDIRIZZO_FATTURAZIONE, indirizzoFatturazione);
  }

  public String getIndirizzoFatturazione() {
    return getString(INDIRIZZO_FATTURAZIONE);
  }


  /** CITTA_FATTURAZIONE **/
  public boolean setCittaFatturazione(String cittaFatturazione) {
    return setString(CITTA_FATTURAZIONE, cittaFatturazione);
  }

  public String getCittaFatturazione() {
    return getString(CITTA_FATTURAZIONE);
  }


  /** STATO_FATTURAZIONE **/
  public boolean setStatoFatturazione(String statoFatturazione) {
    return setString(STATO_FATTURAZIONE, statoFatturazione);
  }

  public String getStatoFatturazione() {
    return getString(STATO_FATTURAZIONE);
  }


  /** CAP_FATTURAZIONE **/
  public boolean setCapFatturazione(String capFatturazione) {
    return setString(CAP_FATTURAZIONE, capFatturazione);
  }

  public String getCapFatturazione() {
    return getString(CAP_FATTURAZIONE);
  }


  /** PAESE_FATTURAZIONE **/
  public boolean setPaeseFatturazione(String paeseFatturazione) {
    return setString(PAESE_FATTURAZIONE, paeseFatturazione);
  }

  public String getPaeseFatturazione() {
    return getString(PAESE_FATTURAZIONE);
  }


  /** CODICEFE **/
  public boolean setCodicefe(String codicefe) {
    return setString(CODICEFE, codicefe);
  }

  public String getCodicefe() {
    return getString(CODICEFE);
  }


  /** PEC **/
  public boolean setPec(String pec) {
    return setString(PEC, pec);
  }

  public String getPec() {
    return getString(PEC);
  }


  /** NOTE **/
  public boolean setNote(String note) {
    return setString(NOTE, note);
  }

  public String getNote() {
    return getString(NOTE);
  }


  /** RIGA_DETT **/
  public boolean setRigaDett(AbstractModel rigaDett) {
    return setObject(RIGA_DETT, rigaDett);
  }

  public AbstractModel getRigaDett() {
    return getObject(RIGA_DETT);
  }

  public void setAllRigaDett(OrdineInRigaDett[] rigaDett) {
    setArray(RIGA_DETT, rigaDett);
  }

  public OrdineInRigaDett[] getAllRigaDett() {
    return (OrdineInRigaDett[])getArray(RIGA_DETT);
  }

  public void addRigaDett(OrdineInRigaDett rigaDett) {
    addArrayElement(RIGA_DETT, rigaDett);
  }

  public void addRigaDett(int index, OrdineInRigaDett rigaDett) {
    addArrayElement(RIGA_DETT, index, rigaDett);
  }

  public boolean removeRigaDett(OrdineInRigaDett rigaDett) {
    return removeArrayElement(RIGA_DETT, rigaDett);
  }

  public OrdineInRigaDett removeRigaDett(int index) {
    return (OrdineInRigaDett)removeArrayElement(RIGA_DETT, index);
  }

  public OrdineInRigaDett getRigaDettAt(int index) {
    return (OrdineInRigaDett)getArrayElement(RIGA_DETT, index);
  }

  public void replaceRigaDettAt(int index, OrdineInRigaDett rigaDett) {
    copyAt(RIGA_DETT, rigaDett, index);
  }

  public int getRigaDettCount() {
    return getArrayCount(RIGA_DETT);
  }


  /** STATUS **/
  public boolean setStatus(StatusInfo status) {
    return setObject(STATUS, status);
  }

  public StatusInfo getStatus() {
    return (StatusInfo)getObject(STATUS);
  }

 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on


 //@formatter:on



}
