package fai.broker.task.apprmgr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import fai.broker.task.AbstractGenericTask;
import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.Fornitore;
import fai.broker.models.FornitoreCalendar;
import fai.broker.models.ItemStatus;
import fai.broker.models.OrdineOut;
import fai.broker.supplier.SupplierService;
import fai.broker.supplier.SupplierServiceFactory;
import fai.broker.util.AnagraficaFarmaciMinSanEanCache;
import fai.common.util.CollectionsTool;

public class ApprovvigionamentoMgr extends AbstractGenericTask {
  
  
  static Logger logger = Logger.getLogger(ApprovvigionamentoMgr.class);
  
  static final String MAGAZZINO_CONTEXT = "FAI_ORDINE_IN*";
  
  private ApprovvigionamentoEnv env = null;


  @Override
  protected void completeSetup() throws Exception {
    env = new ApprovvigionamentoEnv();
    //
    // --- loading of the registry of all known drugs ---
    //
    AnagraficaFarmaciMinSanEanCache anagrafica = new AnagraficaFarmaciMinSanEanCache();//SqlQueries.getAnagraficaFarmaciMinSanEanCache(conn);
    env.setAnagrafica(anagrafica);
    //check FAI_FORNITORE_CALENDAR
  /*  Calendar current = Calendar.getInstance();
    int dateOfWeek = current.get(Calendar.DAY_OF_WEEK);
    List<FornitoreCalendar> fornitoriCalendars = SqlQueries.getAllFornitoreCalendarByDateOfWeek(conn, dateOfWeek);
    Set<Long> selectedFornitori =  new HashSet<Long>();
    for(FornitoreCalendar c : fornitoriCalendars) {
      Calendar start = c.getHourStart();
      Calendar end = c.getHourEnd();

      start.set(Calendar.YEAR, current.get(Calendar.YEAR));
      start.set(Calendar.MONTH, current.get(Calendar.MONTH));
      start.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));

      end.set(Calendar.YEAR, current.get(Calendar.YEAR));
      end.set(Calendar.MONTH, current.get(Calendar.MONTH));
      end.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));

      if(start.compareTo(current) <= 0 && end.compareTo(current) >= 0) {
        selectedFornitori.add(c.getOidFornitore());
      }
    }
    env.setSelectedFornitori(selectedFornitori);*/

    //
    // --- caricamento dell'Anagrafica di tutti i Fornitori ---
    //
   /* List<Fornitore> allFornitori = SqlQueries.getAllFornitore(conn);
    List<Fornitore> fornitori = new ArrayList<>();
    for(Fornitore f : allFornitori) {
      if(selectedFornitori.contains(f.getOid())) {
        fornitori.add(f);
      }
    }
    env.setFornitori(fornitori);

    //
    // --- caricamento dei SupplierService per i Fornitori ---
    //
    for (Fornitore fornitore : fornitori) {
      try {
        SupplierService ss = SupplierServiceFactory.getSupplierService(fornitore, conn);
        env.addFornitoreSupplierService(fornitore.getCodice(), ss);
      } catch (ClassNotFoundException e) {
      }
    }*/
    //
    // --- caricamento dell'Anagrafica di tutti i Magazzini ---
    //
    //List<Magazzino> magazzini = SqlQueries.getAllMagazzino(MAGAZZINO_CONTEXT, conn);
    //env.setMagazzini(magazzini);
    //
    // --- caricamento dei SupplierService per i Magazzini ---
    //
   /* for (Magazzino magazzino : magazzini) {
      SupplierService ss = SupplierServiceFactory.getSupplierService(magazzino, conn);
      env.addMagazzinoSupplierService(magazzino.getAcronym(), ss);
    }*/

    
  }

  
  
  @Override
  protected String doJobExecute() throws Exception {
    final String METH_NAME = new Object() { }.getClass().getEnclosingMethod().getName();
    final String LOG_PREFIX = METH_NAME + ": ";
    logger.info(LOG_PREFIX + "...");
    //
    String error = null;
    //
    // --- verifica che le tabelle di lavoro non siano in stato inconsistente ---
    //
    error = SqlQueries.isUnableToRunStatusError("FAI_APPROVVIGIONAMENTO_FARMACO", "Approvvigionamento Farmaci per Gruppo di Ordini", conn);
    if (error != null) return error;
    //
    // --- caricamento dei FAI_APPROVVIGIONAMENTO_FARMACO "to process" ---
    //
    List<ApprovvigionamentoFarmaco> approvvigionamentoToProcess = SqlQueries.getAllApprovvigionamentoFarmaco(ItemStatus.VALUE_TO_PROCESS, conn);
    error = analyze(approvvigionamentoToProcess);
    if (error != null) return error;
    //
    // --- abilitazione della cache per tutti i SupplierService (Magazzini e Fornitori) ---
    //
    env.setAllSupplierServiceCacheEnabled(true);
    //
    // --- predisposizione della FAI_LISTINI_DISPONIBILITA_TEMP ---
    //
    SqlQueries.deleteAllListiniDisponibilitaTemp(conn);
    SqlQueries.insertListiniDisponibilitaTempByApprovvFarmaco(ItemStatus.VALUE_TO_PROCESS.getOid(), conn);
    //
    // --- cancellazione della tabella FAI_DISPONIBILITA_TEMP (e tabelle dipendenti) ---
    //     NOTA:
    //     nel momento in cui dovesse presentarsi l'esigenza, i criteri di 
    //     cancellazione del contenuto potrebbero essere resi pi� articolati 
    //     rispetto a quelli attuali, ed il contenuo della tabella (almeno
    //     inizialmente - 2021.07.27 - conservato solo a scopo di tracciamento 
    //     dettagliato delle richieste e delle risposte, ma mai successivamente 
    //     recuperato per essere utilizzato negli algoritmi) utilizzato per  
    //     ottimizzare l'algoritmo di determinazione della fornitura migliore
    //     (al momento, 2021.07.27, neppure il prezzo ottenuto in risposta
    //     dai fornitori, viene conservato nella FAI_DISPONIBILITA_RES_TEMP)
    //
    SqlQueries.deleteAllDisponibilitaTemp(conn);
    //
    // --- determination of availability for FAI_APPROVIGIONAMENTO_FARMACO in Warehouses and at Suppliers ---
    //
    ApprovvigionamentoMagazzini magazzini = new ApprovvigionamentoMagazzini();
    magazzini.setup(env, conn);
    error = magazzini.process(approvvigionamentoToProcess);
    if (error != null) return error;
    approvvigionamentoToProcess = magazzini.getApprovvigionamentoToProcess();
    
  /*  ApprovvigionamentoFornitori fornitori = new ApprovvigionamentoFornitori();
    fornitori.setup(env, conn);
    error = fornitori.process(approvvigionamentoToProcess);
    if (error != null) return error;
    approvvigionamentoToProcess = fornitori.getApprovvigionamentoToProcess();
    //
    // --- management of ORDERS_IN to be suspended ---
    //
//    SospensioneOrdini sospensioneOrdini = new SospensioneOrdini();
//    sospensioneOrdini.setup(env, conn);
//    error = sospensioneOrdini.doJob();
//    if (error != null) return error;
    
    SqlQueries.deleteApprovvigionamentoWithQuantitaZero(conn);
    conn.commit();
    
    for (Fornitore fornitore : env.getFornitori()) {
    	List<ApprovvigionamentoFarmaco> approvvigionamentoToOrder =
                SqlQueries.getAllApprovvigionamentoFarmacoByFornitore(ItemStatus.VALUE_PROCESSING, fornitore, conn);
        logger.info("approvvigionamentoToOrder size :: "+approvvigionamentoToOrder.size());
        if(approvvigionamentoToOrder.size() > 0){
          SupplierService service = env.getFornitoreSupplierService(fornitore.getCodice());
          OrdineOut ordineOut = service.confirm(approvvigionamentoToOrder);
          logger.info(ordineOut.getIdOrdine());
        }
    } */
    //   
    Calendar current = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");  
    String currentTime = dateFormat.format(current.getTime());
    int dateOfWeek = current.get(Calendar.DAY_OF_WEEK);
    List<FornitoreCalendar> fornitoriCalendars = SqlQueries.getFornitoresCalendarByDateOfWeek(conn, dateOfWeek, currentTime);
    Integer maxAttemptNumber = SqlQueries.getMaxAttemptNumber(conn, dateOfWeek, currentTime);
    LinkedHashMap<Integer, Set<Long>> map = new LinkedHashMap<>();
    Integer counter = 1;
    for(int i=0; i < maxAttemptNumber; i++) {
    	Set<Long> fornitoreDb = new HashSet<Long>();
    	for(FornitoreCalendar fornitoreCalendar : fornitoriCalendars) {
    		if(counter.intValue() == fornitoreCalendar.getAttemptNumber().intValue()) {
    			fornitoreDb.add(fornitoreCalendar.getOidFornitore());
    		}
    	}
    	map.put(counter, fornitoreDb);
		counter++;
    }
    
    Set<Integer> keys = map.keySet();
    for (Integer key : keys) {
        Set<Long> selectedFornitori = map.get(key);
        env.setSelectedFornitori(selectedFornitori);
        
        List<Fornitore> allFornitori = SqlQueries.getAllFornitore(conn);
        List<Fornitore> fornitori = new ArrayList<>();
        for(Fornitore f : allFornitori) {
          if(selectedFornitori.contains(f.getOid())) {
            fornitori.add(f);
          }
        }
        env.setFornitori(fornitori);

        //
        // --- caricamento dei SupplierService per i Fornitori ---
        //
        for (Fornitore fornitore : fornitori) {
          try {
            SupplierService ss = SupplierServiceFactory.getSupplierService(fornitore, conn);
            env.addFornitoreSupplierService(fornitore.getCodice(), ss);
          } catch (ClassNotFoundException e) {
          }
        }
        
        error = processSupplierAvailabilityAndOrders(approvvigionamentoToProcess);
        if(error != null)
        	return error;
        approvvigionamentoToProcess = SqlQueries.getAllApprovvigionamentoFarmaco(ItemStatus.VALUE_TO_PROCESS, conn);
        if(approvvigionamentoToProcess == null)
        	break;
    }
    return null;
  }
  
  private String processSupplierAvailabilityAndOrders(List<ApprovvigionamentoFarmaco> approvvigionamentoToProcess) throws Exception {
	  String error = null;
	  ApprovvigionamentoFornitori fornitori = new ApprovvigionamentoFornitori();
	    fornitori.setup(env, conn);
	    error = fornitori.process(approvvigionamentoToProcess);
	    if (error != null) return error;
	    approvvigionamentoToProcess = fornitori.getApprovvigionamentoToProcess();
	    //
	    // --- management of ORDERS_IN to be suspended ---
	    //
//	    SospensioneOrdini sospensioneOrdini = new SospensioneOrdini();
//	    sospensioneOrdini.setup(env, conn);
//	    error = sospensioneOrdini.doJob();
//	    if (error != null) return error;
	    
	    SqlQueries.deleteApprovvigionamentoWithQuantitaZero(conn);
	    conn.commit();
	    
	    for (Fornitore fornitore : env.getFornitori()) {
	    	List<ApprovvigionamentoFarmaco> approvvigionamentoToOrder =
	                SqlQueries.getAllApprovvigionamentoFarmacoByFornitore(ItemStatus.VALUE_PROCESSING, fornitore, conn);
	        logger.info("approvvigionamentoToOrder size :: "+approvvigionamentoToOrder.size());
	        if(approvvigionamentoToOrder.size() > 0){
	          SupplierService service = env.getFornitoreSupplierService(fornitore.getCodice());
	          OrdineOut ordineOut = service.confirm(approvvigionamentoToOrder);
	          logger.info(ordineOut.getIdOrdine());
	        }
	    }
	    conn.commit();
	  return error;
  }
  
  private String analyze(List<ApprovvigionamentoFarmaco> approvvigionamentoToProcess) throws Exception {
    int recordsWithErrors = 0;
    for (ApprovvigionamentoFarmaco a : approvvigionamentoToProcess) {
      boolean thisRecordError = false;
      List<String> errors = new ArrayList<String>();
      List<String> techErrors = new ArrayList<String>();
      if (!a.getStatus().getStatus().getOid().equals(ItemStatus.VALUE_TO_PROCESS.getOid())) {
        errors.add(""+(errors.size()+1)+") stato "+a.getStatus().getStatus().getAcronym()+" non ammesso");
        techErrors.add(""+(techErrors.size()+1)+") stato "+a.getStatus().getStatus().getAcronym()+" NON AMMESSO in questo punto (BUG)");
        thisRecordError = true;
      }
      if (a.getOrdineOut() != null) {
        errors.add(""+(errors.size()+1)+") � presente il rifermento ad un Ordine");
        techErrors.add(""+(techErrors.size()+1)+") referenzia FAI_ORDINE_OUT di OID "+a.getOrdineOut().getOid());
        thisRecordError = true;
      }
      if (a.getDisponibilitaConfermataReq() != null) {
        errors.add(""+(errors.size()+1)+") � gi� stata inoltrata la RICHIESTA DI CONFERMA della disponibilit�");
        techErrors.add(""+(errors.size()+1)+") � gi� stata inoltrata la RICHIESTA DI CONFERMA della disponibilit�");
        thisRecordError = true;
      }
      if (a.getDisponibilitaConfermataRes() != null) {
        errors.add(""+(errors.size()+1)+") � gi� stata OTTENUTA CONFERMA della disponibilit�");
        techErrors.add(""+(errors.size()+1)+") � gi� stata OTTENUTA CONFERMA della disponibilit�");
        thisRecordError = true;
      }
      if (a.getFornitore() != null) {
        errors.add(""+(errors.size()+1)+") � gi� presente l'indicazione di un Fornitore per l'approvvigionamento");
        techErrors.add(""+(techErrors.size()+1)+") referenzia FAI_FORNITORE di OID "+a.getFornitore().getOid()+" ("+a.getFornitore().getCodice()+")");
        thisRecordError = true;
      }
      if (a.getMagazzino() != null) {
        errors.add(""+(errors.size()+1)+") � gi� presente l'indicazione di un Magazzino per l'approvvigionamento");
        techErrors.add(""+(techErrors.size()+1)+") referenzia FAI_MAGAZZINO di OID "+a.getMagazzino().getOid()+" ("+a.getMagazzino().getAcronym()+")");
        thisRecordError = true;
      }
      if (thisRecordError) {
        long oidStatusToAssign = ItemStatus.VALUE_ERROR.getOid();
        String statusDescr = "condizioni non ammesse per un approvvigionamento da elaborare: "+CollectionsTool.asCSV(errors, ", ");
        String statusTechDescr = "condizioni non ammesse per un FAI_APPROVVIGIONAMENTO_FARMACO in stato "+ItemStatus.VALUE_TO_PROCESS+": "+CollectionsTool.asCSV(techErrors, ", ");
        a.setStatus(SqlQueries.setApprovvigionamentoFarmacoStatus(a.getOid(), oidStatusToAssign, statusDescr, statusTechDescr, conn));
        recordsWithErrors++;       
      }
    }
    if (recordsWithErrors > 0) {
      conn.commit();
      return recordsWithErrors+" record di Approvvigionamento Farmaci per Gruppo di Ordini su un totale di "+approvvigionamentoToProcess.size()+" record da elaborare ("+ItemStatus.VALUE_TO_PROCESS+") risultano in stato inconsistente e sono stati contrassegnati come in errore ("+ItemStatus.VALUE_ERROR+")";
    }
    else {
      return null;
    }
    
  }
  
  
  

}
