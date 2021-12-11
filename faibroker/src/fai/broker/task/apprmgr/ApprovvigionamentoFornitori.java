package fai.broker.task.apprmgr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.Fornitore;
import fai.broker.models.ItemStatus;
import fai.broker.supplier.SupplierService;
import fai.broker.supplier.SupplierService.ManagedRequest;

class ApprovvigionamentoFornitori extends ApprovvigionamentoMagazziniOrFornitori {

  protected class Offer {
    Fornitore fornitore;
    String codice;
    boolean isMinSan;
    double prezzo;
  }
  
  protected class ApprovByFornitore {
    Fornitore fornitore;
    List<ApprovvigionamentoFarmaco> approvList = new ArrayList<ApprovvigionamentoFarmaco>();
  }
  
  protected Hashtable<String, Offer> offersByMinSan = null; 
  protected Hashtable<String, Offer> offersByEan = null; 
  
  
  protected void loadBestOffers() throws Exception {
    offersByMinSan = new Hashtable<String, Offer>(); 
    offersByEan = new Hashtable<String, Offer>(); 
    List<Object[]> bestOffers = SqlQueries.getAllBestOffersFornitori(conn);
    for (Object[] o : bestOffers) {
      Offer offer = new Offer();
      offer.fornitore = env.getFornitoreByOid(((Long)o[0]), true);
      offer.codice = (String)o[2];
      offer.prezzo = (Double)o[3];
      String tipoCodice = (String)o[1];
      if ("MINSAN".equals(tipoCodice)) {
        offer.isMinSan = true;
        offersByMinSan.put(offer.codice, offer);
      }
      else if ("EAN".equals(tipoCodice)) {
        offer.isMinSan = false;
        offersByEan.put(offer.codice, offer);
      }
      else {
        throw new IllegalStateException("inammissibile, tipo codice non gestito: "+tipoCodice);
      }
      
    }
  }
  
  protected List<ApprovByFornitore> groupByFornitore(List<ApprovvigionamentoFarmaco> approvList) {
    Hashtable<Long, ApprovByFornitore> ht = new Hashtable<Long, ApprovByFornitore>();
    List<ApprovByFornitore> list = new ArrayList<ApprovByFornitore>();
    for (ApprovvigionamentoFarmaco approv : approvList) {
      Offer offer = offersByMinSan.get(approv.getCodiceMinSan());
      if (offer == null) offer = offersByEan.get(approv.getCodiceEan());
//      if (offer == null)  throw new IllegalStateException("inammissibile, nessuna offerta trovata, n� per il MinSan \""+approv.getCodiceMinSan()+"\", n� per l'EAN \""+approv.getCodiceEan()+"\"");
      if (offer == null) {
    	  System.out.println("product code " + approv.getCodiceMinSan() + approv.getCodiceEan() + " is not existed in supplier");
    	  continue;
      }
      //
      ApprovByFornitore abf = ht.get(offer.fornitore.getOid());
      if (abf == null) {
        abf = new ApprovByFornitore();
        abf.fornitore = offer.fornitore;
        ht.put(abf.fornitore.getOid(), abf);
        list.add(abf);
      }
      abf.approvList.add(approv);
    }
    return list;
  }
  


  @Override
  protected String processExecute() throws Exception {
    int listiniDispTempCount = 0;
    int listiniDispTempCountBak = Integer.MIN_VALUE;
    while((listiniDispTempCount = SqlQueries.countListiniDisponibilitaTemp(conn)) > 0 && this.approvvigionamentoToProcess.size() > 0) {
      if (listiniDispTempCount == listiniDispTempCountBak) {
        throw new IllegalStateException("inammissibile, l'insieme dei Listini/Fornitori (FAI_LISTINI_DISPONIBILITA_TEMP) da consultare risulta invariato nonostante le richieste di disponibilit� gi� inoltrate");
      }
      listiniDispTempCountBak = listiniDispTempCount;
      //
      //
      // --- caricamento delle offerte migliori per ogni MinSan/EAN dalla FAI_LISTINI_DISPONIBILITA_TEMP ---
      // 
      loadBestOffers();
      //
      // --- raggruppamento degli approvvigionamenti da soddisfare per Fornitore con la migliore offerta ---
      // 
      List<ApprovByFornitore> approvByFornitoreList = groupByFornitore(approvvigionamentoToProcess);
      //
      // --- interrogazione dei Fornitori ---
      // 
      for (ApprovByFornitore abf : approvByFornitoreList) {
        //
        // --- recupero del servizio di accesso al Fornitore ---
        //
        SupplierService service = env.getFornitoreSupplierService(abf.fornitore.getCodice());
        //
        // --- interrogazione Fornitore ---
        //
        List<ManagedRequest> managedRequests = service.getAvailability(abf.approvList);
        if (service.getError() != null) return service.getError();
        //
        // --- la richiesta della disponibilit� DEVE aver impostato alcuni campi... ---
        //     ==> verifica
        //
        //String error = areApprovvigionamentoFarmacoFieldsSetAsExpected(abf.approvList, ApprovvigionamentoFarmaco.FORNITORE, abf.fornitore.getOid(), abf.fornitore.getCodice(), new int [] { ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_REQ,  ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_RES }, new int [] { ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_REQ,  ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_RES  });
        //if (error != null) return error;
        //
        // --- aggiornamento della FAI_LISTINI_DISPONIBILITA_TEMP in funzione delle risposte ottenute  ---
        //
        for (ManagedRequest mr : managedRequests) {
          ApprovvigionamentoFarmaco richiesto = mr.getRequested();
          ApprovvigionamentoFarmaco disponibile = mr.getAvailableBestMatch(false);
          if (disponibile.getQuantita() == richiesto.getQuantita()) {
            SqlQueries.deleteListiniDisponibilitaTemp(disponibile.getCodiceMinSan(), disponibile.getCodiceEan(), null, conn);
          }
          else if (disponibile.getQuantita() < richiesto.getQuantita()) {
            SqlQueries.deleteListiniDisponibilitaTemp(disponibile.getCodiceMinSan(), disponibile.getCodiceEan(), abf.fornitore.getOid(), conn);
          }
          else {
//            throw new IllegalStateException("inammissibile, richieste "+richiesto.getQuantita()+" unit� di MinSan \""+richiesto.getCodiceMinSan()+"\"/EAN \""+richiesto.getCodiceEan()+"\", ma ottenuta dispponibilit� per "+disponibile.getQuantita()+" unit�");
          }
        }
        //
        // --- gestione delle risposte/aggiornamento banca dati ---
        //
        ManagedRequestsUpdater mru = new ManagedRequestsUpdater(conn);
        boolean isMagazzino = false;
        boolean bestMatchCanBeMoreThanRequested = isMagazzino; 
        mru.process("disponibilit� Fornitore", managedRequests, bestMatchCanBeMoreThanRequested, isMagazzino);
      }
      //
      // --- ricaricamento dei FAI_APPROVVIGIONAMENTO_FARMACO "TO_PROCESS" ---
      //
      this.approvvigionamentoToProcess = SqlQueries.getAllApprovvigionamentoFarmaco(ItemStatus.VALUE_TO_PROCESS, conn);
      //
    }
    return null;
  }
    

}
