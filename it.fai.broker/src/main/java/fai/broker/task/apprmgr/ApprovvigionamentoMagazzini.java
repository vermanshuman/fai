package fai.broker.task.apprmgr;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.ItemStatus;
import fai.broker.models.Magazzino;
import fai.broker.models.TipoFarmaco;
import fai.broker.supplier.SupplierService;
import fai.broker.supplier.SupplierService.ManagedRequest;
import fai.broker.supplier.SupplierServiceFactory;

class ApprovvigionamentoMagazzini extends ApprovvigionamentoMagazziniOrFornitori {
  
  static Logger logger = Logger.getLogger(ApprovvigionamentoMagazzini.class);
  
  /**
   * Criterio per determinare se un Magazzino � in grado o no di fornire un certo Farmaco.<br/>
   * Questo criterio � cambiato rispetto a quanto inzialmente previsto.<br/>
   * Piuttosto che eliminare il codice gi� scritto, si � preferito preservarlo, consentendo
   * di decidere il funzionamento al momento della compilazione per mezzo di apposita 
   * variabile statica.<br/>
   * 
   */
  private static enum SelezionaFarmaco {
    /**
     * Criterio inizialmente previsto: il Magazzino viene scelto perch� supporta quel tipo di Farmaco
     */
    PercheSupportatoDalMagazzino, 
    /**
     * Nuovo criterio: il Magazzino viene scelto perch� esplicitamente indicato in fase di Ordine ("web_order.csv")
     */
    PercheMagazzinoIndicatoNellOrdine 
    };
  
  /**
   * Consente di scegliere, al momento della compilazione, il criterio da applicacre per decidere se un 
   * dato Magazzino � in grado oppure no di fornire un determinato Farmaco<br/>
   */
  private static SelezionaFarmaco CRITERIO_SELEZIONE_FARMACO = SelezionaFarmaco.PercheSupportatoDalMagazzino;
  
  public ApprovvigionamentoMagazzini() {
    super();
  }
  
  @Override
  protected String processExecute() throws Exception {
    //
    /*List<Magazzino> magazzini = env.getMagazzini();
    for (Magazzino magazzino : magazzini) {
      String error = process(magazzino);
      if (error != null) return error;
    }*/
	String error = process();
    if (error != null) return error;
    
    //
    return null;
  }

  Magazzino magazzino = null;
  
  protected String process() throws Exception {
    //
    // --- selezione dei soli ApprovvigionamentoFarmaco supportati dal Magazzino ---
    //
    List<ApprovvigionamentoFarmaco> supportedApprovToProcess = new ArrayList<ApprovvigionamentoFarmaco>();
    logger.info("approvvigionamentoToProcess size :: "+approvvigionamentoToProcess.size());
    for (ApprovvigionamentoFarmaco approv : approvvigionamentoToProcess) {
      if(approv.getMagazzinoAcronym() != null) {
    	  magazzino = SqlQueries.getSelectedMagazzino(null, approv.getMagazzinoAcronym(), conn);
      }
      if (CRITERIO_SELEZIONE_FARMACO == SelezionaFarmaco.PercheSupportatoDalMagazzino) {
        //env.getAnagrafica().setProdotto(approv.getCodiceMinSan(), approv.getCodiceEan(), true);
        if (env.getAnagrafica().isParafarmaco() == true && magazzino.isTipoFarmacoSuppported(TipoFarmaco.VALUE_PARAFARMACO)) {
          supportedApprovToProcess.add(approv);
        }
        else if (env.getAnagrafica().isParafarmaco() == false && magazzino.isTipoFarmacoSuppported(TipoFarmaco.VALUE_FARMACO)) {
          supportedApprovToProcess.add(approv);
        }
      //}
      //else if (CRITERIO_SELEZIONE_FARMACO == SelezionaFarmaco.PercheMagazzinoIndicatoNellOrdine) {
        //if (approv.getMagazzinoAcronym().equals(magazzino.getAcronym())) {
          //supportedApprovToProcess.add(approv);
        //}
      }
      else {
        return "riscontrato problema algoritmico (\"bug\"): criterio non gestito: "+CRITERIO_SELEZIONE_FARMACO;
      }
    }
    
    SupplierService service = SupplierServiceFactory.getSupplierService(magazzino, conn);
    env.addMagazzinoSupplierService(magazzino.getAcronym(), service);
    //
    // --- recupero del servizio di accesso al Magazzino ---
    //
   // SupplierService service = env.getMagazzinoSupplierService(magazzino.getAcronym());
    //
    // --- interrogazione Magazzino ---
    //
    List<ManagedRequest> managedRequests = service.getAvailability(supportedApprovToProcess);
    if (service.getError() != null) return service.getError();
    //
    // --- la richiesta della disponibilit� DEVE aver impostato alcuni campi... ---
    //     ==> verifica
    //
    String error = areApprovvigionamentoFarmacoFieldsSetAsExpected(supportedApprovToProcess, ApprovvigionamentoFarmaco.MAGAZZINO, magazzino.getOid(), magazzino.getAcronym(), new int [] { ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_REQ,  ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_RES }, new int [] { ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_REQ,  ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_RES  });
    if (error != null) return error;
    //
    // --- gestione delle risposte/aggiornamento banca dati ---
    //
    ManagedRequestsUpdater mru = new ManagedRequestsUpdater(conn);
    boolean isMagazzino = true;
    boolean bestMatchCanBeMoreThanRequested = isMagazzino; 
    mru.process("disponibilit� Magazzino", managedRequests, bestMatchCanBeMoreThanRequested, isMagazzino);
    //
    // --- ricaricamento dei FAI_APPROVVIGIONAMENTO_FARMACO "TO_PROCESS" ---
    //
    approvvigionamentoToProcess = SqlQueries.getAllApprovvigionamentoFarmaco(ItemStatus.VALUE_TO_PROCESS, conn);
    //
    return null;
  }

}
