package fai.broker.task.apprmgr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fai.broker.models.*;
import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.supplier.SupplierService;
import fai.broker.supplier.SupplierService.ManagedRequest;
import fai.broker.supplier.SupplierServiceFactory;

class ApprovvigionamentoMagazzini extends ApprovvigionamentoMagazziniOrFornitori {

  static Logger logger = Logger.getLogger(ApprovvigionamentoMagazzini.class);

  /**
   * Criterio per determinare se un Magazzino è in grado o no di fornire un certo Farmaco.<br/>
   * Questo criterio è cambiato rispetto a quanto inzialmente previsto.<br/>
   * Piuttosto che eliminare il codice già scritto, si è preferito preservarlo, consentendo
   * di decidere il funzionamento al momento della compilazione per mezzo di apposita 
   * variabile statica.<br/>
   *
   */
  private static enum SelezionaFarmaco {
    /**
     * Criterio inizialmente previsto: il Magazzino viene scelto perché supporta quel tipo di Farmaco
     */
    PercheSupportatoDalMagazzino,
    /**
     * Nuovo criterio: il Magazzino viene scelto perché esplicitamente indicato in fase di Ordine ("web_order.csv")
     */
    PercheMagazzinoIndicatoNellOrdine
  };

  /**
   * Consente di scegliere, al momento della compilazione, il criterio da applicacre per decidere se un 
   * dato Magazzino è in grado oppure no di fornire un determinato Farmaco<br/>
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
      supportedApprovToProcess.add(approv);
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
    if(uploadTaskConfig != null)
      SqlQueries.seUploadTaskExecutionStatus(uploadTaskConfig.getOid(), ExecutionStatus.APPROVAL_WAREHOUSE_AVAILIBILITY.getAcronym(),
              ExecutionStatus.APPROVAL_WAREHOUSE_AVAILIBILITY.getDescr(), conn);
    List<ManagedRequest> managedRequests = service.getAvailability(supportedApprovToProcess);
    if (service.getError() != null) return service.getError();
    //
    // --- la richiesta della disponibilità DEVE aver impostato alcuni campi... ---
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
    mru.process("disponibilità Magazzino", managedRequests, bestMatchCanBeMoreThanRequested, isMagazzino);
    conn.commit();

    List<ApprovvigionamentoFarmaco> approvvigionamentoProcessing = SqlQueries.getAllApprovvigionamentoFarmaco(ItemStatus.VALUE_PROCESSING, conn);
    for(ApprovvigionamentoFarmaco approvvigionamentoFarmaco : approvvigionamentoProcessing) {
      approvvigionamentoFarmaco.setStatus(StatusInfo.newProcessedInstance(null, null));
      approvvigionamentoFarmaco.setDisponibilitaConfermataReq(Calendar.getInstance());
      SqlQueries.updateApprovvigionamentoFarmaco(approvvigionamentoFarmaco, false, conn);
    }
    conn.commit();

    //
    // --- ricaricamento dei FAI_APPROVVIGIONAMENTO_FARMACO "TO_PROCESS" ---
    //
    approvvigionamentoToProcess = SqlQueries.getAllApprovvigionamentoFarmaco(ItemStatus.VALUE_TO_PROCESS, conn);
    //
    return null;
  }

}
