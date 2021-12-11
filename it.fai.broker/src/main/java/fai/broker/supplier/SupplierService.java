 package fai.broker.supplier;

import java.sql.Connection;
import java.util.List;

import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.DisponibilitaTemp;
import fai.broker.models.Fornitore;
import fai.broker.models.Magazzino;
import fai.broker.models.OrdineOut;

public interface SupplierService {
  
  public enum RequestMode { MoreProductOneRequest, OneProductOneRequest }
  
  public class ManagedRequest {
    private ApprovvigionamentoFarmaco requested;
    private List<ApprovvigionamentoFarmaco> available;
    
    public ManagedRequest(ApprovvigionamentoFarmaco requested, List<ApprovvigionamentoFarmaco> available) {
      super();
      this.requested = requested;
      this.available = available;
      for (ApprovvigionamentoFarmaco avail : this.available) {
        if (avail.getOid() != null) throw new IllegalStateException("identificativo (OID) per l'Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) risulta gi� assegnato ("+avail.getOid()+") per la risposta; ci� non � ammissibile perch� la risposta non pu� gi� essere stata registrata in banca dati (e quindi non pu� essere provvista di OID)");        
      }
    }

    /**
     * Restituisce l'istanza {@link ApprovvigionamentoFarmaco} rappresentante 
     * il fabbisogno richiesto 
     * 
     * @return
     */
    public ApprovvigionamentoFarmaco getRequested() {
      return requested;
    }

    /**
     * Restituisce una @{@link List} dei possibili {@link ApprovvigionamentoFarmaco}  
     * in risposta al fabbisogno richiesto {@link #getRequested()}.<br/>
     * E' una lista, in quanto il servizio potrebbe rispondere
     * che non sono disponibil "8 unit� di prodotto", ma pu� fornire
     * "5 oppure 10".
     * 
     * @return
     */
    public List<ApprovvigionamentoFarmaco> getAvailable() {
      return available;
    }
    
    
    /**
     * Restituisce, dell'elenco {@link #getAvailable()}, l'opzione che
     * minimizza lo scarto rispetto alla {@link #getAvailable()} e,
     * se {@code canBeMoreThanRequested} == <tt>true</true>, permette
     * anche di eccedere la quantit� richiesta.<br/>
     * <br/>
     * Per esempio, se sono state richieste "18 unit� di prodotto", 
     * ma il servizio pu� renderne disponibili "5 o 10 o 15 o 20",
     * se {@code canBeMoreThanRequested} == <tt>false</true> sar�
     * restituita l'opzione "15 unit�" (3 unit� in meno delle 18 richieste),
     * mentre se {@code canBeMoreThanRequested} == <tt>false</true>,
     * allora sar� restituita l'opzione "20 unit�" (2 in pi�, ma
     * con uno scarto pi� contentuto rispetto a 3) 
     * 
     * 
     * 
     * @param canBeMoreThanRequested
     * @return
     */
    public ApprovvigionamentoFarmaco getAvailableBestMatch(boolean canBeMoreThanRequested) {
      if (available == null || available.size() == 0) return null;
      //
      int delta = Integer.MIN_VALUE;
      ApprovvigionamentoFarmaco selected = null;
      for (int i = 0; i < available.size(); i++) {
        ApprovvigionamentoFarmaco avail = available.get(i);
        int tempDelta = requested.getQuantita() - avail.getQuantita();
        if (tempDelta < 0 && canBeMoreThanRequested == false) continue;
        //
        boolean select = false;
        if (i == 0) {
          select = true;
        }
        if (canBeMoreThanRequested == false && tempDelta < delta) {
          select = true;
        }
        if (canBeMoreThanRequested == true && ((Math.abs(tempDelta) < Math.abs(delta)) || (Math.abs(tempDelta) == Math.abs(delta) && tempDelta > 0 && delta < 0))) {
          select = true;
        }
        if (select) {
          selected = avail;
          delta = tempDelta;
        }
      }
      return selected;
    }
    
    
  }
  
  public void setup(Magazzino magazzino, Connection conn) throws Exception;

  public void setup(Fornitore magazzino, Connection conn) throws Exception;

  /**
   * La classe implementante dovr� restituire {@link RequestMode#MoreProductOneRequest}
   * se il servizio che andr� ad interrgogare consente pu� essere interrogato
   * circa la disponibilit� di pi� farmaci/prodotti con un'unica richiesta, e 
   * dovr� restituire {@link RequestMode#OneProductOneRequest} nel caso in cui
   * sia necessario una richiesta per ogni singolo farmaco/prodotto.<br>
   * <br/>
   * Nel primo caso, l'implementazione astratta {@link AbstractSupplierService}
   * predisporr�, in favore delle implementazioni concrete, gli elementi gerarchici
   * della struttura dati {@link DisponibilitaTemp} --&gt; {@link DisponibilitaReqTemp}
   * nel rapporto 1:N, nel secondo la stessa struttura sar� predisposta nel
   * rapporto N:N 
   * 
   * @return
   * @throws Exception
   */
  public RequestMode getRequestMode() throws Exception;
  
  /**
   * restituisce <tt>true</tt> se l'istanza di questa interfaccia
   * rappresenta un Magazzino invece che un Fornitore vero e 
   * proprio
   * 
   * @return
   * @throws Exception
   */
  public boolean isWarehouse() throws Exception;

  /**
   * Restituisce la disponibilit� di quanto richiesto con la {@link #List} di 
   * {@link ApprovvigionamentoFarmaco} fornita come input, nella {@link #List}  di 
   * {@link ManagedRequest}
   * 
   * @param fabbisogno
   * @return
   * @throws Exception
   */
  public List<ManagedRequest> getAvailability(List<ApprovvigionamentoFarmaco> fabbisogno) throws Exception;
  
  /**
   * Conferma la richiesta dei farmaci nella {@link #List} di 
   * {@link ApprovvigionamentoFarmaco} e restituisce l'{@link OrdineOut}
   * corrispondente.
   * 
   * @param approvvigionamento
   * @return
   * @throws Exception
   */
  public OrdineOut confirm(List<ApprovvigionamentoFarmaco> approvvigionamento) throws Exception;
  
  
  /**
   * Ha effetto sulla {@link #getAvailability(List)}:
   * <ul>
   * <li>se viene reiterata una richiesta gi� fatta precedentemente, 
   *     l'interrogazione vera e propria non avr� luogo, e sar� restituita 
   *     la riposta ottenuta in precedenza
   * </li>
   * <li>se il servizio rappresenta un Magazzino ({@link #isWarehouse()} == <tt>true</tt>), 
   *     non avr� effettivamente luogo anche qualsiasi richiesta per quantit� inferiori 
   *     a quelle che risultava possibile soddisafare in base a precedenti interrogazioni
   * </li>
   * </ul>
   * 
   * Passare <tt>false</tt> come parametro, non solo significa non utilizzare 
   * la cache, ma anche cancellare il contenuto attuale della medesima.<br/>
   * 
   * @param value
   * @throws Exception
   */
  public void setCacheEnabled(boolean value) throws Exception;


  /**
   * Restituisce una decrizione d'errore, nel caso in cui la {@link #getAvailability(List)}
   * oppure la {@link #confirm(List)} non fosse andata a buon fine, altrimenti
   * <tt>null</tt> 
   * 
   * 
   * @return
   * @throws Exception
   */
  public String getError() throws Exception;
  


}
