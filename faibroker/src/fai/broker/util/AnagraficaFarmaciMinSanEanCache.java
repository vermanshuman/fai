package fai.broker.util;

/**
 * Utilizza internamente {@link AnagraficaFarmaciCache} per gestire 
 * esplicitametne MinSan ed EAN.<br/>
 * 
 * @author Gianni Radice
 *
 */
public class AnagraficaFarmaciMinSanEanCache {
  
  private AnagraficaFarmaciCache minSanCache = new AnagraficaFarmaciCache();  
  private AnagraficaFarmaciCache eanCache = new AnagraficaFarmaciCache();  
  private AnagraficaFarmaciCache currentTarget = null;  

  
  public AnagraficaFarmaciMinSanEanCache addToCache(String minSan, String ean, String descrizione, String tipoDescrizione, String tipoDescrizioneEstesa) {
    //
    if (minSan != null) {
      minSanCache.addToCache(minSan, descrizione, tipoDescrizione, tipoDescrizioneEstesa);
    }
    if (ean != null) {
      eanCache.addToCache(ean, descrizione, tipoDescrizione, tipoDescrizioneEstesa);
    }
    //
    return this;
  }
  
  public AnagraficaFarmaciMinSanEanCache unsetProdotto() {
    minSanCache.unsetProdotto();
    eanCache.unsetProdotto();
    currentTarget = null;
    return this;
  }


  public AnagraficaFarmaciMinSanEanCache setProdotto(String unknowCode, boolean mustExists) throws Exception {
    return setProdotto(unknowCode, unknowCode, mustExists);
  }

  public AnagraficaFarmaciMinSanEanCache setProdotto(String minSan, String ean, boolean mustExists) throws Exception {
    unsetProdotto();
    if (minSan != null) {
      minSanCache.setProdotto(minSan, true, false);
      if (minSanCache.exists()) currentTarget = minSanCache;
    }
    if (ean != null) {
      eanCache.setProdotto(ean, false, false);
      if (eanCache.exists()) currentTarget = eanCache;
    }
    if (mustExists && minSanCache.exists() == false && eanCache.exists() == false) {
      throw new IllegalArgumentException("inammissibile, nessun riscontro per MinSan "+minSan+" oppure EAN "+ean); 
    }
    return this;
  }
  
  
  public boolean exists() {
    return currentTarget == null ? false : currentTarget.exists();  
  }
  
  public String getDescrizione() {
    return currentTarget == null ? null : currentTarget.getDescrizione();
  }

  public String getDescrizioneTipo() {
    return currentTarget == null ? null : currentTarget.getDescrizioneTipo();
  }
  
  public String getDescrizioneEstesaTipo() {
    return currentTarget == null ? null : currentTarget.getDescrizioneEstesaTipo();
  }
  
  public boolean isParafarmaco() {
    return currentTarget == null ? false : currentTarget.isParafarmaco();  
  }

  public boolean isMinSan() {
    return currentTarget == null ? false : currentTarget.equals(minSanCache);
  }

  public boolean isEan() {
    return currentTarget == null ? false : currentTarget.equals(eanCache);
  }

}
