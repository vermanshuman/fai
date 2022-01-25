package fai.broker.task.fabbcalc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fai.broker.task.AbstractGenericTask;
import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovToRiga;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.ItemStatus;
import fai.broker.models.OrdineInRigaDett;
import fai.broker.models.StatusInfo;
import fai.common.db.SqlUtilities;

public class FabbisognoCalculatorTask extends AbstractGenericTask {
  
  
  static Logger logger = Logger.getLogger(FabbisognoCalculatorTask.class);


  @Override
  protected void completeSetup() throws Exception {
    // nothing to do
  }

  @Override
  protected String doJobExecute() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    //
    String error = null;
    //
    // --- verifica che le tabelle di input al processo non siano in stato inconsistente ---
    //
    error = SqlQueries.isUnableToRunStatusError("FAI_ORDINE_IN_COLLECTION", "gruppo di Ordini da elaborare", conn);
    if (error != null) return error;
    //
    error = SqlQueries.isUnableToRunStatusError("FAI_ORDINE_IN", "Ordini", conn);
    if (error != null) return error;
    //
    // --- verifica che le tabelle di lavoro non siano in stato inconsistente ---
    //
    //error = SqlQueries.isUnableToRunStatusError("FAI_APPROVVIGIONAMENTO_FARMACO", "Fabbisogno/Approvvigionamento di Farmaci", conn);
    //if (error != null) return error;
    //
    // --- calcolo effettivoa dell'approvvigionamento ---
    //
    try {
      error = doJobExecute2();
      if (error == null) {
        conn.commit();
      }
      else {
        conn.rollback();
        error+="; le operazioni eseguite sono state annullate (rollback)";
        logger.error(error);
      }
    }
    catch (Throwable th) {
      SqlUtilities.rollbackWithNoException(conn);
      error = "Eccezione " + th.getClass().getName() + ", �" + th.getMessage() + "� nel calcolo del Fabbisogno/Approvvigionamento di Farmaci (FAI_APPROVVIGIONAMENTO_FARMACO); le operazioni sono state annullate (rollback)";
      logger.error(error, th);
    }
    //
    return error;
  }
  
  
  private String doJobExecute2() throws Exception {
    //
    // --- cancellazzzione FAI_APPROVVIGIONAMENTO_FARMACO delle sessioni ---
    //     precedenti e che non sono pi� considerabili validi 
    //     (es. disponibilit� richiesta al Fornitore, ma non confermata)
    //
    SqlQueries.deleteApprovvigionamentoToRecaulculate(conn);
    //
    // --- recupero delle righe per le quali calcolare il FAI_APPROVVIGIONAMENTO_FARMACO ---
    //     ordinate per CODICE_MINSAN/EAN 
    //
    List<OrdineInRigaDett> righe = SqlQueries.getAllOrdineInRigaDettWithoutFabbisogno(conn);
    //
    // --- determinazione dei record FAI_APPROVVIGIONAMENTO_FARMACO ---
    //
    String identificativoFarmacoBak = null;
    ApprovvigionamentoFarmaco currApprov = null;
    List<ApprovvigionamentoFarmaco> approvToInsert = new ArrayList<ApprovvigionamentoFarmaco>();
    for (OrdineInRigaDett riga : righe) {
      //
      // identificativo del farmaco secondo l'ORDER BY NVL(RIGA.CODICE_MINSAN, RIGA.CODICE_EAN);
      String identificativoFarmaco = riga.getCodiceMinSan();
      if (identificativoFarmaco == null || "".equals(identificativoFarmaco.trim())) {
        identificativoFarmaco = riga.getCodiceEan();
      }
      if (identificativoFarmaco == null || "".equals(identificativoFarmaco.trim())) {
        return "Identified Line (FAI_ORDINE_IN_RIGA_DETTAGLIO) of the incoming Order (FAI_ORDINE_IN) with both MinSan and EAN identifiers not assigned; the identifying OID of the Row is "+riga.getOid(); 
      }
      //
      if (!identificativoFarmaco.equals(identificativoFarmacoBak)) {
        currApprov = new ApprovvigionamentoFarmaco();
        currApprov.setCodiceMinSan(riga.getCodiceMinSan());
        currApprov.setCodiceEan(riga.getCodiceEan());
        currApprov.setMagazzinoAcronym(riga.getMagazzinoAcronym());
        currApprov.setQuantita(0);
        currApprov.setStatus(new StatusInfo());
        currApprov.getStatus().setStatus(ItemStatus.VALUE_TO_PROCESS);
        currApprov.getStatus().setStatusUpdatedTs(Calendar.getInstance());
        approvToInsert.add(currApprov);
        identificativoFarmacoBak = identificativoFarmaco;
      }
      //else if (!riga.getMagazzinoAcronym().equals(currApprov.getMagazzinoAcronym())) {
        //return "individuata Riga (FAI_ORDINE_IN_RIGA_DETTAGLIO) dell'Ordine in ingresso (FAI_ORDINE_IN) che, per il farmaco "+identificativoFarmaco+", indica "+riga.getMagazzinoAcronym()+" quale Magazzino di approvvigionamento, mentre almeno una delle righe precedenti indicava "+currApprov.getMagazzinoAcronym()+"; l'indicazione di Magazzini differenti, per lo stesso Farmaco, non � ammessa";
      //}
      //
      ApprovToRiga a2r = new ApprovToRiga();
      a2r.setRigaDett(riga);
      a2r.setQuantita(riga.getQuantita());
      currApprov.setQuantita(currApprov.getQuantita()+a2r.getQuantita());
      currApprov.addApprovRigaDett(a2r);
    }
    //
    // --- inserimento degli FAI_APPROVVIGIONAMENTO_FARMACO cos� determinati ---
    //
    SqlQueries.insertAllApprovvigionamentoFarmaco(approvToInsert, conn);
    //
    return null;
  }
  
  
}

