package fai.broker.util;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.apache.log4j.Logger;

public class AnagraficaFarmaciCache {
  
  
  static Logger logger = Logger.getLogger(AnagraficaFarmaciCache.class);

  
  private String currentCodiceProdotto;
  private String currentDescrProdotto;
  private String currentDescrTipoProdotto;
  private String currentDescrEstesaTipoProdotto;
  private boolean currentIsParafarmaco;

  private Hashtable<String, String> descrProdottoByMinSan = new Hashtable<String, String>(); 
  private Hashtable<String, String> descrTipoProdottoByMinSan = new Hashtable<String, String>(); 
  private Hashtable<String, String> descrEstesasTipoProdottoByMinSan = new Hashtable<String, String>();
  private Hashtable<String, String> descrTipoProdottoSelf = new Hashtable<String, String>(); // serve per evitare di sprecare RAM per String tutte uguali tra loro 
  private Hashtable<String, String> descrEstesasTipoProdottoSelf = new Hashtable<String, String>(); // serve per evitare di sprecare RAM per String tutte uguali tra loro
  
  public AnagraficaFarmaciCache addToCache(String codice, String descrizione, String tipoDescrizione, String tipoDescrizioneEstesa) {
    //
    String tipoDescr = descrTipoProdottoSelf.get(tipoDescrizione);
    if (tipoDescr == null) {
      descrTipoProdottoSelf.put(tipoDescrizione, tipoDescrizione);
      tipoDescr = tipoDescrizione;
    }
    String tipoDescrEstesa = descrEstesasTipoProdottoSelf.get(tipoDescrizioneEstesa);
    if (tipoDescrEstesa == null) {
      descrEstesasTipoProdottoSelf.put(tipoDescrizioneEstesa, tipoDescrizioneEstesa);
      tipoDescrEstesa = tipoDescrizioneEstesa;
    }
    //
    descrProdottoByMinSan.put(codice, descrizione);
    descrTipoProdottoByMinSan.put(codice, tipoDescr);
    descrEstesasTipoProdottoByMinSan.put(codice, tipoDescrEstesa);
    //
    return this;
  }
  
  public AnagraficaFarmaciCache unsetProdotto() {
    currentCodiceProdotto = null;
    currentDescrProdotto = null;
    currentDescrTipoProdotto = null;
    currentDescrEstesaTipoProdotto = null;
    currentIsParafarmaco = false;
    return this;
  }
  
  
  public AnagraficaFarmaciCache setProdotto(String codice, boolean mustBeMinSan, boolean mustExists) throws Exception {
    unsetProdotto();
    //
    if (mustBeMinSan && ( codice == null && codice.length() != 9)) throw new IllegalArgumentException("inammissibile, non è un codice MinSan: "+codice);
    String descr = descrProdottoByMinSan.get(codice);
    if (mustExists && descr == null) throw new IllegalArgumentException("inammissibile, nessun riscontro per il codice: "+codice);
    //
    currentCodiceProdotto = codice;
    //
    if (descr == null) {
      logger.info("Farmaco "+codice+": nessun riscontro");
      return null;
    }
    //
    currentDescrProdotto = descr;
    //
    currentDescrTipoProdotto = descrTipoProdottoByMinSan.get(currentCodiceProdotto);
    if (currentDescrTipoProdotto == null) throw new IllegalStateException("inammissibile, nessuna descrizione della tipologia di prodotto trovata per il codice: "+currentCodiceProdotto);
    //
    currentDescrEstesaTipoProdotto = descrEstesasTipoProdottoByMinSan.get(currentCodiceProdotto);
    if (currentDescrEstesaTipoProdotto == null) throw new IllegalStateException("inammissibile, nessuna descrizione estesa della tipologia di prodotto trovata per il codice: "+currentCodiceProdotto);
    //
    currentIsParafarmaco = currentDescrTipoProdotto.toUpperCase().indexOf("PARAFARMAC") >= 0;
    //
    logger.info("Farmaco "+codice+": «"+currentDescrProdotto+"», "+(isParafarmaco() ? "parafarmaco" : "NON parafarmaco")+" in base alla descrizione «"+currentDescrTipoProdotto+"»"+(currentDescrTipoProdotto.equals(currentDescrEstesaTipoProdotto) ? "" : " «"+currentDescrEstesaTipoProdotto+"»"));
    //
    return this;
  }
  
  public boolean exists() {
    return currentDescrProdotto != null;
  }
  
  public String getDescrizione() {
    return currentDescrProdotto;
  }

  public String getDescrizioneTipo() {
    return currentDescrTipoProdotto;
  }
  
  public String getDescrizioneEstesaTipo() {
    return currentDescrEstesaTipoProdotto;
  }
  
  public boolean isParafarmaco() {
    return currentIsParafarmaco;
  }

}
