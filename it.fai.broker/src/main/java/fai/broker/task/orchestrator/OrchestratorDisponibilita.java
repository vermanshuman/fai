package fai.broker.task.orchestrator;

import fai.broker.task.AbstractGenericTask;
import org.apache.log4j.Logger;

import fai.broker.task.apprmgr.ApprovvigionamentoMgr;
import fai.broker.task.fabbcalc.FabbisognoCalculatorTask;
import fai.broker.task.impord.OrdineInImporterTask;

public class OrchestratorDisponibilita extends AbstractGenericTask {
  
  static Logger logger = Logger.getLogger(OrchestratorDisponibilita.class);
  

  @Override
  protected void completeSetup() throws Exception {
  }

  @Override
  public String doJobExecute() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    //
    String error = null;
    //
    //  --- importa gli Ordini (es., "web_order.csv") ---
    //
    OrdineInImporterTask ordineInImporterTask = new OrdineInImporterTask();
    ordineInImporterTask.setup("ORDINE_IN_IMPORTER", nowReference, conn);
    error = ordineInImporterTask.doJob();
    if (error != null) return error;
    //
    //  --- calcola il Fabbisogno ---
    //
    FabbisognoCalculatorTask fabbisognoCalculatorTask = new FabbisognoCalculatorTask();
    fabbisognoCalculatorTask.setup("FABBISOGNO_CALCULATOR", nowReference, conn);
    error = fabbisognoCalculatorTask.doJob();
    if (error != null) return error;
    //
    //  --- getstisce l'Approvvigionamento vero e proprio ---
    //
    ApprovvigionamentoMgr approvvigionamentoMgr = new ApprovvigionamentoMgr();
    approvvigionamentoMgr.setup("APPROVVIGIONAMENTO_MGR", nowReference, conn);
    error = approvvigionamentoMgr.doJob();
    if (error != null) return error;
    //
    return null;
  }

}
