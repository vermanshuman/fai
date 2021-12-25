package fai.imp.farmaclick.ws.bean;

import java.util.Calendar;

import fai.imp.farmaclick.ws.FarmaclickWS;

/**
 * Questo oggetto è simile - ma non uguale - tra le varie versioni di API.<br/>
 * <br/>
 * Poiché le variazioni constano, essenzialmente, di campi via via aggiunti, 
 * questa versione includerà sempre tutti i campi della più recente versione,
 * in modo da rendere indipendente l'interfaccia esposta da {@link FarmaclickWS}
 * dall'API effettivamente utilizzata.<br/>
 * <br/>
 * 
 * 
 * @author Gianni Radice
 *
 */
public class DownloadListinoOutputBean {
  
  private java.util.Calendar dataOraGenerazioneListino;

  private int delayProsRichiesta;

  private int esitoServizio;

  private java.lang.String tipoRisposta;

  private java.lang.String urlConfermaDownload;

  private java.lang.String urlDownload;

  public DownloadListinoOutputBean() {
    
  }

  public DownloadListinoOutputBean(String tipoRisposta, int esitoServizio, Calendar dataOraGenerazioneListino, int delayProsRichiesta, String urlDownload, String urlConfermaDownload) {
    super();
    this.tipoRisposta = tipoRisposta;
    this.esitoServizio = esitoServizio;
    this.dataOraGenerazioneListino = dataOraGenerazioneListino;
    this.delayProsRichiesta = delayProsRichiesta;
    this.urlDownload = urlDownload;
    this.urlConfermaDownload = urlConfermaDownload;
  }

  public java.util.Calendar getDataOraGenerazioneListino() {
    return dataOraGenerazioneListino;
  }

  public void setDataOraGenerazioneListino(java.util.Calendar dataOraGenerazioneListino) {
    this.dataOraGenerazioneListino = dataOraGenerazioneListino;
  }

  public int getDelayProsRichiesta() {
    return delayProsRichiesta;
  }

  public void setDelayProsRichiesta(int delayProsRichiesta) {
    this.delayProsRichiesta = delayProsRichiesta;
  }

  public int getEsitoServizio() {
    return esitoServizio;
  }

  public void setEsitoServizio(int esitoServizio) {
    this.esitoServizio = esitoServizio;
  }

  public java.lang.String getTipoRisposta() {
    return tipoRisposta;
  }

  public void setTipoRisposta(java.lang.String tipoRisposta) {
    this.tipoRisposta = tipoRisposta;
  }

  public java.lang.String getUrlConfermaDownload() {
    return urlConfermaDownload;
  }

  public void setUrlConfermaDownload(java.lang.String urlConfermaDownload) {
    this.urlConfermaDownload = urlConfermaDownload;
  }

  public java.lang.String getUrlDownload() {
    return urlDownload;
  }

  public void setUrlDownload(java.lang.String urlDownload) {
    this.urlDownload = urlDownload;
  }


  
  
  
  
}
