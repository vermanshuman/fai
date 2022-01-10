package fai.broker.task.apprmgr;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovToRiga;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.Fornitore;
import fai.broker.models.ItemStatus;
import fai.broker.models.Magazzino;
import fai.broker.models.OrdineIn;

class SospensioneOrdini {
  
  
  static Logger logger = Logger.getLogger(SospensioneOrdini.class);


  protected Connection conn = null;
  protected ApprovvigionamentoEnv env = null;

  public void setup(ApprovvigionamentoEnv env, Connection conn) throws Exception {
    this.env = env;
    this.conn = conn;
  }
  
  private ApprovvigionamentoFarmaco [] getApprovsToProcess(ApprovvigionamentoFarmaco reference, boolean fkToFornitore) throws Exception {
    Long oidStToProcess = ItemStatus.VALUE_TO_PROCESS.getOid();
    Long oidStProcessing = ItemStatus.VALUE_PROCESSING.getOid();
    //
    // --- caricamento e controllo dei record da trattare ---
    //
    // verifica coerenza e integrità dell'input
    if (oidStProcessing.equals(reference.getStatus().getStatus().getOid()) == false) throw new IllegalArgumentException("inammissibile, atteso "+ApprovvigionamentoFarmaco.class.getName()+" in stato OID "+oidStProcessing+", ricevuta istanza in stato "+reference.getStatus().getStatus().getOid());
    if (reference.getFornitore() == null && fkToFornitore == true)  throw new IllegalStateException("inammissibile, l'istanza ricevuta in input non referenzia un "+Fornitore.class.getName());
    if (reference.getMagazzino() == null && fkToFornitore == false) throw new IllegalStateException("inammissibile, l'istanza ricevuta in input non referenzia un "+Magazzino.class.getName());
    //
    // caricamento dell'insieme di FAI_APPROVVIGIONAMENTO_FARMACO t.c. l'insieme stesso contenga l'istanza "PROCESSING" passata più l'eventuale "TO PROCESS" con stesso MinSan/EAN   
    List<ApprovvigionamentoFarmaco> approvList = SqlQueries.getAllApprovvigionamentoFarmacoByStatusAndCodice(reference.getCodiceMinSan(), reference.getCodiceEan(), new Long [] { oidStToProcess, oidStProcessing }, conn);
    if (approvList.size() != 1 && approvList.size() != 2) throw new IllegalStateException("inammissibile, la SELECT ha restituito "+approvList.size()+" record FAI_APPROVVIGIONAMENTO_FARMACO; attesi: 1 o 2");
    //
    ApprovvigionamentoFarmaco apprToProcess = null;
    ApprovvigionamentoFarmaco apprProcessing = null;
    //
    for (ApprovvigionamentoFarmaco appr : approvList) {
      if (appr.getOid().equals(reference.getOid())) {
        if (oidStProcessing.equals(appr.getStatus().getStatus().getOid()) == false) throw new IllegalArgumentException("inammissibile, atteso "+ApprovvigionamentoFarmaco.class.getName()+" corrispondente all'istanza di riferimento (identificata dall'OID "+reference.getOid()+") in stato OID "+oidStProcessing+", ma la SELECT ha recuperato un'istanza in stato "+appr.getStatus().getStatus().getOid());
        apprProcessing = appr;
      }
      else {
        if (oidStToProcess.equals(appr.getStatus().getStatus().getOid()) == false) throw new IllegalArgumentException("inammissibile, atteso "+ApprovvigionamentoFarmaco.class.getName()+" in stato OID "+oidStToProcess+", ma la SELECT ha recuperato un'istanza in stato "+appr.getStatus().getStatus().getOid());
        apprToProcess = appr;
      }
    }
    if (apprProcessing == null) throw new IllegalStateException("inammissibile, nessun riscontro in banca dati per il record FAI_APPROVVIGIONAMENTO_FARMACO corrispondente all'istanza di riferimento identificata dall'OID "+reference.getOid());
    //
    return new ApprovvigionamentoFarmaco[] { apprProcessing, apprToProcess };
  }
  
  private void splitAndMergeApprovMagazzino(ApprovvigionamentoFarmaco reference) throws Exception {
    //
    // --- caricamento e controllo dei record da trattare ---
    //
    ApprovvigionamentoFarmaco [] af = getApprovsToProcess(reference, false);
    ApprovvigionamentoFarmaco apprToProcess = af[0];
    ApprovvigionamentoFarmaco apprProcessing = af[1];
    //
    // --- individuazione della quota-parte della quantità ---
    //
    if (apprProcessing != null && apprToProcess == null) {
      //
      // -- caso semplice, basta cambiare lo stato da "PROCESSING" a "TO PROCESS" --
      //
      apprProcessing.setStatus(SqlQueries.setApprovvigionamentoFarmacoStatus(apprProcessing.getOid(), ItemStatus.VALUE_TO_PROCESS.getOid(), "impostato come da processare ("+ItemStatus.VALUE_TO_PROCESS.getAcronym()+") perché referenziante un FAI_ORDINE_IN sospeso", null, conn));
      reference.setStatus(apprProcessing.getStatus()); // allineamenteo anche del clone fornito come riferimento
    }
    else if (apprProcessing != null && apprToProcess != null) {
      //
      // -- caso complesso, al "PROCESSING" va tolta la quota parte che non può soddisfare --
      //
      // calcolo della QUOTA PARTE della QUANTITA dell'APPROV "PROCESSING" referenziante ORDINI_IN "SUSPENDEND"
      ItemStatus statusSuspended = ItemStatus.VALUE_SUSPENDED;
      int qtOrineInSuspended = apprProcessing.getQuantitaByOrdineInStatus(statusSuspended);
      if (apprProcessing.getQuantita().intValue() < qtOrineInSuspended)  throw new IllegalStateException("inammissibile, la quantità dell'istanza "+ApprovvigionamentoFarmaco.class.getName()+", identificata dall'OID "+apprProcessing.getOid()+", è inferiore alla somma delle quantità di tutti gli "+OrdineIn.class.getName()+" in stato "+statusSuspended.getAcronym());
      //
      // decurtazione dalla QUANTITA dell'APPROV della QUOTA PARTE 
      apprProcessing.setQuantita(apprProcessing.getQuantita() - qtOrineInSuspended);
      //
      // spostamento dell'associazione degli A2R con la QUOTA PARTE dall'APPROV "PROCESSING" all'APPROV "TO PROCESS"
      List<ApprovToRiga> a2rList = apprProcessing.getAllApprToRigaByOrdineInStatus(statusSuspended);
      for (ApprovToRiga a2r: a2rList) {
        SqlQueries.updateApprovToRiga(a2r.getOid(), apprToProcess.getOid(), conn);
      }
      //
      // l'APPROV "PROCESSING" referenzia ancora degli A2R verso ORDINI_IN "PROCESSED"?
      if (/* basta controllare la QUANTITA */ apprProcessing.getQuantita() == 0) {
        // NO
        // --> il record può essere rimosso
        SqlQueries.deleteApprovvigionamentoFarmacoByOid(apprProcessing.getOid(), conn);
      }
      else {
        // SI
        // --> è sufficiente aggiornare la QUANTITA che è stata decurtata della QUOTA PARTE
        SqlQueries.updateApprovToRiga(apprProcessing.getOid(), apprProcessing.getQuantita(), conn);
      }
      //
      // aggiornamento del riferimento passato
      reference.setQuantita(apprToProcess.getQuantita());
    }
    else {
      throw new IllegalStateException("condizione non gestita");
    }
    
    
    
  }
  

  
  private void mergeAndSuspendApprovFornitore(ApprovvigionamentoFarmaco reference) throws Exception {
    //
    // --- caricamento e controllo dei record da trattare ---
    //
    ApprovvigionamentoFarmaco [] af = getApprovsToProcess(reference, true);
    ApprovvigionamentoFarmaco apprToProcess = af[0];
    ApprovvigionamentoFarmaco apprProcessing = af[1];
    //
    // --- aggiornamento dei record caricati ---
    //
    if (apprProcessing != null && apprToProcess == null) {
      //
      // -- caso semplice, basta cambiare lo stato da "PROCESSING" a "TO PROCESS" --
      //
      apprProcessing.setStatus(SqlQueries.setApprovvigionamentoFarmacoStatus(apprProcessing.getOid(), ItemStatus.VALUE_TO_PROCESS.getOid(), "impostato come da processare ("+ItemStatus.VALUE_TO_PROCESS.getAcronym()+") perché referenziante un FAI_ORDINE_IN sospeso", null, conn));
      reference.setStatus(apprProcessing.getStatus()); // allineamenteo anche del clone fornito come riferimento
    }
    else if (apprProcessing != null && apprToProcess != null) {
      //
      // -- caso complesso: i due vanno fusi in uno solo e aggiornata la quantità -- 
      //
      // rimuovo quello che aveva ottenuto risposte dal Fornitore 
      SqlQueries.deleteApprovvigionamentoFarmacoByOid(apprProcessing.getOid(), conn);
      //
      // aggiorno quello che era già "PROCESSING"
      apprToProcess.setQuantita(apprToProcess.getQuantita()+apprProcessing.getQuantita());
      SqlQueries.updateApprovvigionamentoFarmaco(apprToProcess, true, conn);
      
    }
    else {
      throw new IllegalStateException("condizione non gestita");
    }
  }
  

  /**
   * potrei mettere l'SqlQueries direttamente nel while nel quale uso questo
   * metodo, ma ho necessità di loggare ...
   * 
   * @return
   * @throws Exception
   */
  private int setOrdineInSuspendedIfApprovToProcess() throws Exception {
    logger.info("sospensione ("+ItemStatus.VALUE_SUSPENDED.getAcronym()+") degli Ordini in ingresso (FAI_ORDINE_IN) per i quali non è stato possibile soddisfare il fabbisogno (FAI_APPROVVIGIONAMENTO_FARMACO "+ItemStatus.VALUE_TO_PROCESS.getAcronym()+") ...");
    int updatedRecordsCount = SqlQueries.setOrdineInSuspendedIfApprovToProcess(conn);
    if (updatedRecordsCount == 0) {
      logger.info("nessun Ordini in ingresso (FAI_ORDINE_IN) sospeso ("+ItemStatus.VALUE_SUSPENDED.getAcronym()+"), nessun fabbisogno risulta privo di copertura");
    }
    else {
      logger.info(updatedRecordsCount +" Ordini in ingresso (FAI_ORDINE_IN) sospesi ("+ItemStatus.VALUE_SUSPENDED.getAcronym()+") per impossibilità di soddisfarne il fabbisogno per almeno un farmaco (FAI_APPROVVIGIONAMENTO_FARMACO "+ItemStatus.VALUE_TO_PROCESS.getAcronym()+")");
    }
    return updatedRecordsCount;
  }
  
  
  public String doJob() throws Exception {
    //     
    // --- convenzioni per i commenti --
    //     
    //     o) ORDINE:    FAI_ORDINE_IN
    //     o) RIGA:      FAI_ORDINE_IN_RIGA_DETTAGLIO    
    //     o) A2R:       associativa FAI_APPROVVIGFARMACO_ORDINRIGA
    //     o) APPROV:    FAI_APPROVVIGIONAMENTO_FARMACO
    //     o) FORNITORE: FAI_FORNITORE
    //     
    //
    //  --- SOSPENSIONE di tutti gli ORDINI con almeno una RIGA referenziata, via ---
    //      A2R, da un APPROV in stato "TO PROCESS"
    //
    while (setOrdineInSuspendedIfApprovToProcess()>0) {
      //
      // TODO *** QUI SERVE UN WATCH DOG PER SCONGIURARE IL LOOP INFINITO A CAUSA DI ERRORI ***
      //
      //
      //  --- SOSPENSIONE di tutti gli ORDINI soddisfatti per mezzo APPROV "PROCESSING" --- 
      //      coperti da FORNITORI che, tuttavia, per mezzo di altri A2R referenziano
      //      altri ORDINI (nel frattempo) posti a "SUSPENDED". 
      //      Inoltre,
      //      tutti gli APPROV "PROCESSING" dell'Ordine vanno posti nuovamente a "TO PROCESS"
      //      perché non è detto che il FORNITORE con una disponibilità di 10 Aspirine,
      //      delle quali 6 all'ORDINE appena "SUSPENDED" e 4 ad un altro ORDINE, sia disposto
      //      a fornire solo queste ultime 4.
      //      L'APPROV va eventualmente "fuso" con APPROV esistenti con stesso MINSAN/EAN e 
      //      in stato "TO_PROCESS" prima di esser posto a "TO PROCESS"
      //
      //      Graficamente, esplicitando le relazioni tra le tabelle/record, fissato  
      //      un particolare MINSAN/EAN (che, quindi, si omette dal "disegno"), e supponendo 
      //      che le quantità siano garantite da FORNITORI, dato lo scenario:
      //      
      //                
      //                                    +--- A2R QTA 4 --- RIGA:b --- ORDINE:B "PROCESSED"
      //                                    |      
      //      APPROV:1 "PROCESSING" QTA=6 --+ 
      //                                    |
      //                                    +--- A2R QTA 2 --- RIGA:a --- ORDINE:A "SUSPENDED"
      //               
      //      APPROV:2 "TO PROCESS" QTA=3 -------A2R QTA 3 --- RIGA:c --- ORDINE:C "SUSPENDED"
      //      
      //      
      //      
      //      APPROV:1 non può più essere tentuo nello stato "PROCESSING", perché referenzia
      //      una riga di ORDINE:A che è "SUSPENDED" e non è detto che il FORNITORE sia dispoosto
      //      a fornire 4 invece di 6 unità, pertanto torna "TO PROCESS" ed anche ORDINE:B,
      //      non più soddisfatto, diventa "SUSPENDED":
      //      
      //                
      //                                    +--- A2R QTA 4 --- RIGA:b --- ORDINE:B "SUSPENDED"
      //                                    |      
      //      APPROV:1 "TO PROCESS" QTA=6 --+ 
      //                                    |
      //                                    +--- A2R QTA 2 --- RIGA:a --- ORDINE:A "SUSPENDED"
      //               
      //      APPROV:2 "TO PROCESS" QTA=3 -------A2R QTA 3 --- RIGA:c --- ORDINE:C "SUSPENDED"
      //      
      //                
      //      Inoltre, poiché APPROV:1 e APPROV:2 fanno riferimento allo stesso MINSAN/EAN, i due                
      //      record andranno "fusi":         
      //                 
      //                 
      //                                    +--- A2R QTA 4 --- RIGA:b --- ORDINE:B "SUSPENDED"
      //                                    |      
      //      APPROV:1* "TO PROCESS" QTA=9 -+ 
      //                                    |
      //                                    +--- A2R QTA 2 --- RIGA:a --- ORDINE:A "SUSPENDED"
      //                                    | 
      //                                    +----A2R QTA 3 --- RIGA:c --- ORDINE:C "SUSPENDED"
      //                
      //                
      //      Inutile dire che ORDINE:B passato da "PROCESSED" a "SUSPENDED" magari includeva
      //      anche altre RIGHE, servite da APPROV di FORNITORI: il processo di SOSPENSIONE
      //      va quindi re-iterato finché il passaggio di un ORDINE da "PROCESSED" a 
      //      "SUSPENDED" non impatta su APPROV - di FORNITORI - in stato "PROCESSING" 
      // 
      //
      logger.info("sospensione ("+ItemStatus.VALUE_SUSPENDED.getAcronym()+") degli Ordini in ingresso (FAI_ORDINE_IN) il cui fabbisogno risulta soddisfatto da un approvvigionamento (FAI_APPROVVIGIONAMENTO_FARMACO), richiesto ad un Fornitore (FAI_FORNITORE), in comune con Ordini già sospesi ...");
      //
      // carico per "ApprovvigionamentoFarmaco" perché - al momento - è l'unico modello che riesce
      // a "connettere" i due estremi della "catena" di oggetti
      List<ApprovvigionamentoFarmaco> approvList = SqlQueries.getAllOrdineInDataLinkedToSuspendedOnes(true, false, conn);
      //
      Set<Long> ordiniSospesi = new HashSet<Long>();
      for (ApprovvigionamentoFarmaco approv : approvList) {
        //
        // loop sugli ApprovvigionamentoFarmaco ...
        for (int i = 0; i < approv.getApprovRigaDettCount(); i++) {
          if (approv.getStatus().getStatus().getAcronym().equals(ItemStatus.VALUE_PROCESSING.getAcronym()) == false) throw new IllegalStateException("inammissibile, il FAI_APPROVVIGIONAMENTO_FARMACO di OID "+approv.getOid()+" recuperato dalla SELECT è in stato "+approv.getStatus().getStatus().getAcronym()+"; atteso: "+ItemStatus.VALUE_PROCESSING.getAcronym());
          //
          // ... e sospensione degli OrdineIn da sospendere
          OrdineIn ordineDaSospendere = approv.getApprovRigaDettAt(i).getRigaDett().getOrdineIn();
          if (ordiniSospesi.contains(ordineDaSospendere.getOid()) == false) { // <-- lo stesso OrdineIn potrebbe essere referenziato da più Approv --> sospensione solo la prima volta che viene incontrato
            if (ordineDaSospendere.getStatus().getStatus().getAcronym().equals(ItemStatus.VALUE_PROCESSED.getAcronym()) == false) throw new IllegalStateException("inammissibile, il FAI_ORDINE_IN di OID "+ordineDaSospendere.getOid()+" recuperato dalla SELECT è in stato "+ordineDaSospendere.getStatus().getStatus().getAcronym()+"; atteso: "+ItemStatus.VALUE_PROCESSED.getAcronym());
            //
            SqlQueries.setOrdineInCollectionStatus(ordineDaSospendere.getOid(), ItemStatus.VALUE_SUSPENDED.getOid(), "sospeso perché referenziato FAI_APPROVVIGIONAMENTO_FARMACO in comune con altro FAI_ORDINE_IN già sospeso" , null, conn);
            ordiniSospesi.add(ordineDaSospendere.getOid());
          }
        }
        //
        // eventuale accorpamento dell'FAI_APPROVVIGIONAMENTO_FARMACO "PROCESSED"
        // agli eventuali "TO PROCESS" con stesso MINSAN/EAN
        mergeAndSuspendApprovFornitore(approv);
      }
      logger.info(ordiniSospesi.size()+" Ordini in ingresso (FAI_ORDINE_IN) sospesi ("+ItemStatus.VALUE_SUSPENDED.getAcronym()+") il cui fabbisogno risultava soddisfatto da un approvvigionamento (FAI_APPROVVIGIONAMENTO_FARMACO), richiesto ad un Fornitore (FAI_FORNITORE), in comune con Ordini già sospesi");
    }
    //
    //  --- GESTIONE di tutti gli ORDINI soddisfatti per mezzo APPROV "PROCESSING" --- 
    //      coperti da MAGAZZINI che, tuttavia, per mezzo di altri A2R referenziano
    //      altri ORDINI (nel frattempo) posti a "SUSPENDED".
    //      
    //      A differenza dei FORNITORI, si ammette che un qualsiasi MAGAZZINO capace 
    //      di rifornire una QUANTITA X di un certo prodotto, sia anche capace di fornire
    //      una qualsiasi quantità minore di X.
    //
    //      Con notazione grafica, partendo dallo stessissimo scenario visto per i FORNITORI,
    //      ma attribuendo gli APPROV ai MAGAZZINI:
    //                
    //                
    //                                    +--- A2R QTA 4 --- RIGA:b --- ORDINE:B "PROCESSED"
    //                                    |      
    //      APPROV:1 "PROCESSING" QTA=6 --+ 
    //                                    |
    //                                    +--- A2R QTA 2 --- RIGA:a --- ORDINE:A "SUSPENDED"
    //               
    //      APPROV:2 "TO PROCESS" QTA=3 -------A2R QTA 3 --- RIGA:c --- ORDINE:C "SUSPENDED"
    //      
    //                
    //      si avrebbe, semplicemente, che l'APPROV:1 andrebbe considerato "PROCESSING"
    //      per le sole 4 unità che è possibile soddisfare, assegnando le altre 2 
    //      all'APPROV:2 "TO PROCESS" (se APPROV:2 non esistesse, per esempio perché non esiste
    //      l'ORDINE:C, allora un APPROV:2 andrebbe creato):
    //                
    //                
    //      APPROV:1 "PROCESSING" QTA=4 ------ A2R QTA 4 --- RIGA:b --- ORDINE:B "PROCESSED"
    //                                    
    //                                    +--- A2R QTA 2 --- RIGA:a --- ORDINE:A "SUSPENDED"
    //                                    |
    //      APPROV:2 "TO PROCESS" QTA=5 --+----A2R QTA 3 --- RIGA:c --- ORDINE:C "SUSPENDED"
    //      
    //
    logger.info("assegnazione dello stato "+ItemStatus.VALUE_TO_PROCESS.getAcronym()+" per quota parte di ogni fabbisogno soddisfatto per mezzo di un approvvigionamento (FAI_APPROVVIGIONAMENTO_FARMACO) richiesto ad un Magazzino (FAI_MAGAZZINO) ma in comune con Ordini sospesi ...");
    //
    // carico per "ApprovvigionamentoFarmaco" perché - al momento - è l'unico modello che riesce
    // a "connettere" i due estremi della "catena" di oggetti
    List<ApprovvigionamentoFarmaco> approvList = SqlQueries.getAllOrdineInDataLinkedToSuspendedOnes(false, true, conn);
    //
    for (ApprovvigionamentoFarmaco approv : approvList) {
      //
      // loop sugli ApprovvigionamentoFarmaco ...
      for (int i = 0; i < approv.getApprovRigaDettCount(); i++) {
        if (approv.getStatus().getStatus().getAcronym().equals(ItemStatus.VALUE_PROCESSING.getAcronym()) == false) throw new IllegalStateException("inammissibile, il FAI_APPROVVIGIONAMENTO_FARMACO di OID "+approv.getOid()+" recuperato dalla SELECT è in stato "+approv.getStatus().getStatus().getAcronym()+"; atteso: "+ItemStatus.VALUE_PROCESSING.getAcronym());
        //
        splitAndMergeApprovMagazzino(approv);
      }
    }
    return null;
  }
  
  
  

}
