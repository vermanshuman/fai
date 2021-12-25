package fai.imp.farmaclick.ws.bean;

import java.util.ArrayList;
import java.util.List;

public class OrdineInputBean {

  //
  // a meno che non sia diversamente indicato, gli attributi
  // sono supportati dall'api_2005_001 e dall'api_2010_001
  //
  private java.lang.String IDSessione;
  private java.lang.String codiceFornitore;
  private List<ArticoloInputBean> articoli = new ArrayList<ArticoloInputBean>();         
  private boolean descrizioneArticoli;
  private boolean descrizioneMotivazioneMancanza;
  private boolean indicazioneDepositoAllestimento;  // solo api_2005_001
  private java.lang.String codiceFatturazione;      // solo api_2005_001
  private java.lang.String codiceTipoOrdine;
  private boolean indicazioneDatiConsegna;          // solo api_2005_001
  private java.lang.String noteOrdine;
  private java.lang.String riferimentoOrdineFarmacia;
  private int trattamentoRisposta;     
  private java.lang.String noteCorriere;            // solo api_2010_001            
  public java.lang.String getIDSessione() {
    return IDSessione;
  }
  public void setIDSessione(java.lang.String iDSessione) {
    IDSessione = iDSessione;
  }
  public java.lang.String getCodiceFornitore() {
    return codiceFornitore;
  }
  public void setCodiceFornitore(java.lang.String codiceFornitore) {
    this.codiceFornitore = codiceFornitore;
  }
  public List<ArticoloInputBean> getArticoli() {
    return articoli;
  }
  public void setArticoli(List<ArticoloInputBean> articoli) {
    this.articoli = articoli;
  }
  public boolean isDescrizioneArticoli() {
    return descrizioneArticoli;
  }
  public void setDescrizioneArticoli(boolean descrizioneArticoli) {
    this.descrizioneArticoli = descrizioneArticoli;
  }
  public boolean isDescrizioneMotivazioneMancanza() {
    return descrizioneMotivazioneMancanza;
  }
  public void setDescrizioneMotivazioneMancanza(boolean descrizioneMotivazioneMancanza) {
    this.descrizioneMotivazioneMancanza = descrizioneMotivazioneMancanza;
  }
  
  /**
   * ATTENZIONE! SOLO API 2005_001!!<br>
   * <br>
   * se true  nel bean di output si otterrà l'indicazione del deposito che allestirà 
   * fisicamente la merce nel caso il fornitore abbia attivo un sistema di deviazioni 
   * interne
   * 
   * @return
   */
  public boolean isIndicazioneDepositoAllestimento() {
    return indicazioneDepositoAllestimento;
  }
  public void setIndicazioneDepositoAllestimento(boolean indicazioneDepositoAllestimento) {
    this.indicazioneDepositoAllestimento = indicazioneDepositoAllestimento;
  }
  public java.lang.String getCodiceFatturazione() {
    return codiceFatturazione;
  }
  /**
   * ATTENZIONE! SOLO API 2005_001!!<br>
   * <br>
   * permette di specificare un altro codice cliente a cui la merce andrebbe fatturata; 
   * se null, viene fatturato al codice cliente implicito nell'ID di Sessione
   * 
   * @param codiceFatturazione
   */
  public void setCodiceFatturazione(java.lang.String codiceFatturazione) {
    this.codiceFatturazione = codiceFatturazione;
  }
  public java.lang.String getCodiceTipoOrdine() {
    return codiceTipoOrdine;
  }
  public void setCodiceTipoOrdine(java.lang.String codiceTipoOrdine) {
    this.codiceTipoOrdine = codiceTipoOrdine;
  }
  public boolean isIndicazioneDatiConsegna() {
    return indicazioneDatiConsegna;
  }
  
  /**
   * ATTENZIONE! SOLO API 2005_001!!<br>
   * <br>
   * se true, nella risposta si otterrà anche l'informazione circa _quale_ corriere 
   * effettuerà la consegna e a quale _ora_
   * 
   * @param indicazioneDatiConsegna
   */
  public void setIndicazioneDatiConsegna(boolean indicazioneDatiConsegna) {
    this.indicazioneDatiConsegna = indicazioneDatiConsegna;
  }
  public java.lang.String getNoteOrdine() {
    return noteOrdine;
  }
  public void setNoteOrdine(java.lang.String noteOrdine) {
    this.noteOrdine = noteOrdine;
  }
  public java.lang.String getRiferimentoOrdineFarmacia() {
    return riferimentoOrdineFarmacia;
  }
  public void setRiferimentoOrdineFarmacia(java.lang.String riferimentoOrdineFarmacia) {
    this.riferimentoOrdineFarmacia = riferimentoOrdineFarmacia;
  }
  public int getTrattamentoRisposta() {
    return trattamentoRisposta;
  }
  public void setTrattamentoRisposta(int trattamentoRisposta) {
    this.trattamentoRisposta = trattamentoRisposta;
  }
  public java.lang.String getNoteCorriere() {
    return noteCorriere;
  }
  
  /**
   * ATTENZIONE! SOLO API 2010_001!!<br>
   * <br>
   * "Note da inviare al corriere che effettuerà la consegna della merce in farmacia.<br>
   * Il testo specificato in questo campo verrà stampato sul DDT e, se il grossista è 
   * dotato di un sistema per la tracciabilità delle fasi consegna, l'informazione verrà 
   * visualizzata sul palmare del corriere."
   * @param noteCorriere
   */
  public void setNoteCorriere(java.lang.String noteCorriere) {
    this.noteCorriere = noteCorriere;
  }
  
  

}
