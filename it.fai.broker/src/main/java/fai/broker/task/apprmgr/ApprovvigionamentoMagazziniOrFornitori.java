package fai.broker.task.apprmgr;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.common.util.SqlCliClient;

abstract class ApprovvigionamentoMagazziniOrFornitori {
  
  static Logger logger = Logger.getLogger(ApprovvigionamentoMagazziniOrFornitori.class);

  protected Connection conn = null;

  protected ApprovvigionamentoEnv env = null;
  
  protected List<ApprovvigionamentoFarmaco> approvvigionamentoToProcess = null;
  
  public ApprovvigionamentoMagazziniOrFornitori() {
    super();
  }
  
  public void setup(ApprovvigionamentoEnv env, Connection conn) throws Exception {
    this.env = env;
    this.conn = conn;
  }
  
  public List<ApprovvigionamentoFarmaco> getApprovvigionamentoToProcess() {
    return approvvigionamentoToProcess;
  }

  public String process(List<ApprovvigionamentoFarmaco> approvvigionamentoToProcess) throws Exception {
    this.approvvigionamentoToProcess = approvvigionamentoToProcess;
    if (approvvigionamentoToProcess.size() == 0) {
      logger.info("nessun Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) da soddisfare");
      return null;
    }
    //
    return processExecute();

  }
  
  protected abstract String processExecute() throws Exception;

  
  protected String areApprovvigionamentoFarmacoFieldsSetAsExpected(List<ApprovvigionamentoFarmaco> approvList, int amMagazzinoOrFornitoreIdx, long oidMagazzinoOrFornitore, String acrCodeMagazzinoOrFornitore, int [] amNotNullField, int [] amNullField) throws Exception {
    boolean isMagazzino = false;
    if (amMagazzinoOrFornitoreIdx == ApprovvigionamentoFarmaco.MAGAZZINO)
      isMagazzino = true;
    else if (amMagazzinoOrFornitoreIdx == ApprovvigionamentoFarmaco.FORNITORE)
      isMagazzino = false;
    else
      throw new IllegalArgumentException("inammissibile, atteso indice identificativo del Magazzino o del Fornitore; trovato: "+amMagazzinoOrFornitoreIdx);
    //
    for (ApprovvigionamentoFarmaco approvAvailReqSent : approvList) {
      int count = SqlQueries.countApprovvigionamentoFarmaco(approvAvailReqSent.getOid(), ApprovvigionamentoFarmaco.MAGAZZINO , oidMagazzinoOrFornitore, amNotNullField, amNullField, conn);
      if (count != 1) {
        String error = "a valle della richiesta di disponibilità al "+(isMagazzino ? "Magazzino" : "Fornitore")+" "+acrCodeMagazzinoOrFornitore+" (OID "+oidMagazzinoOrFornitore+"), lo stato del record di Fabbisogno/Approvvigionamento di Farmaci (FAI_APPROVVIGIONAMENTO_FARMACO) identificato dall'OID "+approvAvailReqSent.getOid();
        logger.error(error);
        SqlCliClient.getSingleton(conn).execute("SELECT * FROM FAI_APPROVVIGIONAMENTO_FARMACO WHERE OID = "+approvAvailReqSent.getOid(), org.apache.log4j.Level.ERROR);
        return error+"; ulteriori informazioni disponibili nei log di sistema";
      }
    }
    return null;
    
  }
  
  

  

}
