package fai.broker.task.apprmgr;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovToRiga;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.ItemStatus;
import fai.broker.models.StatusInfo;
import fai.broker.supplier.SupplierService.ManagedRequest;
import fai.common.util.SqlCliClient;

/**
 * Classe di utilità impiegata tanto per i Magazzini 
 * che per i Fornitori
 * 
 * @author Gianni Radice
 *
 */
class ManagedRequestsUpdater {
  

  
  static Logger logger = Logger.getLogger(ManagedRequestsUpdater.class);
  
  private Connection conn = null;

  private List<ApprovvigionamentoFarmaco> approvSoddisfatto = new ArrayList<ApprovvigionamentoFarmaco>();
  private List<ApprovvigionamentoFarmaco> approvDaSoddisfare = new ArrayList<ApprovvigionamentoFarmaco>();
  
  public ManagedRequestsUpdater(Connection conn) {
    super();
    this.conn = conn;
  }
  
  private void mustBeEquals(String req, String res, String fieldDescription, String operationName) throws Exception {
    if (req == null && res == null) return;
    if (req != null && res != null && req.equals(res)) return;
    //
    throw new IllegalStateException("l'operazione di "+operationName+" avente per oggetto "+fieldDescription+" "+req+" ha ottenuto risposta per  "+fieldDescription+" "+res);
  }
  
  public void process(String operationName, List<ManagedRequest> managedRequests, boolean bestMatchCanBeMoreThanRequested, boolean isMagazzino) throws Exception {
    approvSoddisfatto = new ArrayList<ApprovvigionamentoFarmaco>();
    approvDaSoddisfare = new ArrayList<ApprovvigionamentoFarmaco>();
    if (bestMatchCanBeMoreThanRequested == true && isMagazzino == false) throw new IllegalArgumentException("combinazione di parametri non consentita: nel caso del Magazzino una disponibilita di 100 elementi, se ne sono stati richiesti 70, sarà registrata come pari a 70, mentre per i Fornitori l'acquisto di 100 unità non può essere registrato come 70 unità acquistate");
    for (ManagedRequest mr : managedRequests) {
      ApprovvigionamentoFarmaco quantitaRichiesta = mr.getRequested();
      if (quantitaRichiesta.getOid() == null) throw new IllegalStateException("identificativo (OID) per l'Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) non assegnato per l'operazione di "+operationName+"; ciò non è ammissibile perché la quantità richiesta deve essere già registrata in banca dati (e quindi provvista di OID)");
      ApprovvigionamentoFarmaco quantitaDisponibile = mr.getAvailableBestMatch(bestMatchCanBeMoreThanRequested);
      if (quantitaDisponibile.getOid() != null) throw new IllegalStateException("identificativo (OID) per l'Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) risulta già assegnato ("+quantitaDisponibile.getOid()+") per la risposta all'operazione di "+operationName+"; ciò non è ammissibile perché la risposta non può già essere stata registrata in banca dati (e quindi non può essere provvista di OID)");
      mustBeEquals(quantitaRichiesta.getCodiceMinSan(), quantitaDisponibile.getCodiceMinSan(), "Codice MinSan", operationName);
      mustBeEquals(quantitaRichiesta.getCodiceEan(), quantitaDisponibile.getCodiceEan(), "Codice EAN", operationName);
      //
      String LOG_PREFIX = "quantità "+quantitaRichiesta.getCodiceDescr()+" richiesta "+quantitaRichiesta.getQuantita()+", disponibile "+quantitaDisponibile.getQuantita()+" presso "+(isMagazzino ? "Magazzino" : "Fornitore")+": ";
      logger.info(LOG_PREFIX+"...");
      if (quantitaDisponibile.getQuantita() >= quantitaRichiesta.getQuantita()) {
        // La quantità disponibile è sovrabbondante rispetto a quella richiesta. 
        // Dal momento che il record relativo alla quantità richiesta sarà 
        // aggiornato mantenendo la "quantità richiesta", la (maggiore) 
        // "quantità disponibile", come informazione, andrà persa.
        // Ovviamente ciò è accettabile se e solo se si tratta di un Magazzino:
        // nel caso di Fornitori, se il Fornitore da disponibilità per 100 pezzi
        // non è possibile conservare la richiesta di 70, perché l'ordine 
        // sucessivo per 70 verrebbe poi rigettato
        //if (isMagazzino == false)  throw new IllegalStateException("nel caso dei Fornitori, l'operazione di "+operationName+" avente per oggetto l'Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) non può accettare una disponibilità di "+quantitaDisponibile.getQuantita() +" elementi per una richiesta di "+quantitaRichiesta.getQuantita()); 
    	if (quantitaDisponibile.getQuantita() > quantitaRichiesta.getQuantita())  throw new IllegalStateException("nel caso dei Fornitori, l'operazione di "+operationName+" avente per oggetto l'Approvvigionamento Farmaci per Gruppo di Ordini (FAI_APPROVVIGIONAMENTO_FARMACO) non può accettare una disponibilità di "+quantitaDisponibile.getQuantita() +" elementi per una richiesta di "+quantitaRichiesta.getQuantita()); 
          
    	  //
        storeApprovvigionamentoFarmaco(quantitaRichiesta, ItemStatus.VALUE_PROCESSING, false);
        approvSoddisfatto.add(quantitaRichiesta);
        logger.info(LOG_PREFIX+"soddisfatto");
      }
      else { // quantitaDisponibile.getQuantita() < quantitaRichiesta.getQuantita()
        // la quantità disponibile - sia QD - è inferiore a quella richiesta - QR -.
        //
        // Qui se cose si complicano.
        //
        // Utilizzando, per brevità, gli acronimi:
        //
        // - APPROV per FAI_APPROVVIGIONAMENTO_FARMACO
        // - APPR2RIGA per FAI_APPROVVIGFARMACO_ORDINRIGA
        //
        // Abbiamo quanto segue.
        //
        // Ad APPROV quantitaRichiesta, già presente in banca dati con la fk verso
        // il Magazzino o il Fornitore, si assegna QUANTITA = QD.
        // Questo record diventa quindi "quantitaSoddisfatta" (invece che 
        // quantitaRichiesta)
        //
        // Va quindi creato un nuovo record APPROV, chiamiamolo "quantitaDaRichiedere",
        // che avrà tutte le proprietà di quantitaDisponibile, a meno di 
        // QUANTITA, cui sarà assegnata la differenza QR - QD
        //
        // Tuttavia, non basta: vanno riviste tutte le associazioni APPR2RIGA
        // 
        // Può essere, per esempio, che, a fronte di una richiesta di 
        // 6 unità da equidistribuire su 3 Righe di Ordine - 2 per riga -
        // La dispobilità di 4 elementi porti dalla situazione:
        //
        //  quantitaRichiesta 6 --> APPR2RIGA 2 --> Riga A
        //                      --> APPR2RIGA 2 --> Riga B
        //                      --> APPR2RIGA 2 --> Riga C
        //
        // alla situazione:
        //
        //  quantitaSoddisfatta 4  --> APPR2RIGA 2 --> Riga A
        //                         --> APPR2RIGA 2 --> Riga B
        //  quantitaDaRichiedere 2 --> APPR2RIGA 2 --> Riga C
        //  
        // Se, invece, la quantità disponibile fosse pari a 
        // sole 3 unità, si avrebe:
        //  
        //  quantitaSoddisfatta 3  --> APPR2RIGA 2 --> Riga A
        //                         --> APPR2RIGA 1 --> Riga B
        //  quantitaDaRichiedere 3 --> APPR2RIGA 1 --> Riga B
        //                         --> APPR2RIGA 2 --> Riga C
        //  
        // Nel primo caso le associazionei APPR2RIGA restano
        // invariate, ma per uno di essi l'APPROV referenziato
        // cambia da quantitaSoddisfatta a quantitaDaRichiedere.
        //
        // Nel secondo caso c'è comunque un record APPR2RIGA
        // che cambia APPROV, ma quello referenziante Riga B
        // viene diviso in 2, di cui uno continua a referenziare
        // quantitaSoddisfatta, ma con la quantità ridotta
        // da 2 a 1 e l'altro quantitaDaRichiedere 
        //
        // ---
        //
        // --- organizzazione degli APPR2RIGA per priorità ---
        //     (qualora già non lo fossero)
        //
        ApprovToRiga [] approvToRiga = getSortedByPriority(quantitaRichiesta.getAllApprovRigaDett());
        //
        // --- ri-assegnazione delle istanze a nuove variabili il cui nome  ---
        //     riflette il modo in cui saranno impiegate
        //
        ApprovvigionamentoFarmaco quantitaSoddisfatta = quantitaRichiesta;
        ApprovvigionamentoFarmaco quantitaDaRichiedere = quantitaDisponibile;
        //
        int qtRichiesta = quantitaRichiesta.getQuantita();
        int qtDisponibile = quantitaDisponibile.getQuantita();
        int qtSoddisfatta = qtDisponibile;
        int qtDaRichiedere = qtRichiesta - qtSoddisfatta; 
        quantitaSoddisfatta.setQuantita(qtSoddisfatta);
        quantitaSoddisfatta.setAllApprovRigaDett(new ApprovToRiga[0]);
        quantitaDaRichiedere.setQuantita(qtDaRichiedere);
        quantitaDaRichiedere.setAllApprovRigaDett(new ApprovToRiga[0]);
        //
        int qt = 0;
        for (int i = 0; i < approvToRiga.length; i++) {
          qt += approvToRiga[i].getQuantita(); // <-- quota parte dell'asssociazione (approvToRiga[i].getQuantita()), NON intero fabbisogno della riga (approvToRiga[i].getRigaDett().getQuantita(approvToRiga[i].getRigaDett().getQuantita())
          if (qt <= qtSoddisfatta) {
            quantitaSoddisfatta.addApprovRigaDett(approvToRiga[i]);
            System.err.println("-->> line " + new Throwable().getStackTrace()[0].getLineNumber()+": "+quantitaSoddisfatta.getApprovRigaDettCount()+" / " + quantitaDaRichiedere.getApprovRigaDettCount());
          }
          else if (qt - approvToRiga[i].getQuantita() < qtSoddisfatta && qtSoddisfatta < qt) {
            //
            int quotaParteSoddisfatta = quantitaSoddisfatta.getQuantita() - (qt - approvToRiga[i].getQuantita()); 
            int quotaParteDarichiedere = approvToRiga[i].getQuantita() - quotaParteSoddisfatta; 
            //
            approvToRiga[i].setQuantita(quotaParteSoddisfatta);
            quantitaSoddisfatta.addApprovRigaDett(approvToRiga[i]);
            //
            ApprovToRiga newApprovToRiga = new ApprovToRiga();
            newApprovToRiga.setRigaDett(approvToRiga[i].getRigaDett());
            newApprovToRiga.setQuantita(quotaParteDarichiedere);
            quantitaDaRichiedere.addApprovRigaDett(newApprovToRiga);
            System.err.println("-->> line " + new Throwable().getStackTrace()[0].getLineNumber()+": "+quantitaSoddisfatta.getApprovRigaDettCount()+" / " + quantitaDaRichiedere.getApprovRigaDettCount());
          }
          else if (qtSoddisfatta <= qt) {
            quantitaDaRichiedere.addApprovRigaDett(approvToRiga[i]);
            System.err.println("-->> line " + new Throwable().getStackTrace()[0].getLineNumber()+": "+quantitaSoddisfatta.getApprovRigaDettCount()+" / " + quantitaDaRichiedere.getApprovRigaDettCount());
          }
          else {
            throw new IllegalStateException("inammissibile, condizione sconoscitua e non gestita");
          }
        
        }
        //
        SqlCliClient.getSingleton(conn).execute("select * from FAI_APPROVVIGFARMACO_ORDINRIGA");
        storeApprovvigionamentoFarmaco(quantitaSoddisfatta, ItemStatus.VALUE_PROCESSING, true);
        logger.info(LOG_PREFIX+"quantità soddisfatta "+quantitaSoddisfatta.getQuantita()+", status "+quantitaSoddisfatta.getStatus().getStatus().getAcronym());
        storeApprovvigionamentoFarmaco(quantitaDaRichiedere, ItemStatus.VALUE_TO_PROCESS, true);
        logger.info(LOG_PREFIX+"quantità ancora da richiedere "+quantitaDaRichiedere.getQuantita()+", status "+quantitaDaRichiedere.getStatus().getStatus().getAcronym());
        approvSoddisfatto.add(quantitaSoddisfatta);
        approvDaSoddisfare.add(quantitaDaRichiedere);
        // //
        // logger.info("quantitaSoddisfatta = "+quantitaSoddisfatta.toXml());
        // logger.info("quantitaDaRichiedere = "+quantitaDaRichiedere.toXml());
        // //
        // SqlCliClient.getSingleton(conn).execute("select * from FAI_APPROVVIGIONAMENTO_FARMACO");
        // SqlCliClient.getSingleton(conn).execute("select * from FAI_APPROVVIGFARMACO_ORDINRIGA");
        // SqlCliClient.getSingleton(conn).execute("select * from FAI_ORDINE_IN_RIGA_DETTAGLIO");
        // SqlCliClient.getSingleton(conn).execute("select * from FAI_ORDINE_IN");
        // SqlCliClient.getSingleton(conn).execute("select * from FAI_ORDINE_IN_COLLECTION");
        // System.exit(0);
      }
    }
  }
  
  
  private void storeApprovvigionamentoFarmaco(ApprovvigionamentoFarmaco approv, ItemStatus status, boolean updateChildrenToo) throws Exception {
    Long oid = approv.getOid();
    if (oid == null) {
      approv.setStatus(new StatusInfo());
      approv.getStatus().setStatus(status); 
      approv.getStatus().setStatusUpdatedTs(Calendar.getInstance());
      SqlQueries.insertApprovvigionamentoFarmaco(approv, conn);
    }
    else {
      approv.setStatus(SqlQueries.setApprovvigionamentoFarmacoQuantitaStauts(oid, approv.getQuantita(), status.getOid(), null, null, conn));
    }
    if (updateChildrenToo == false) return;
    //
    SqlQueries.deleteApprovToRigaByApprovOid(approv.getOid(), conn);
    ApprovToRiga [] a2r = approv.getAllApprovRigaDett();
    for (int i = 0; i < a2r.length; i++) {
      SqlQueries.insertApprovToRiga(approv.getOid(), a2r[i], conn);
      // if (a2r[i].getOid() == null) {
      //   SqlQueries.insertApprovToRiga(approv.getOid(), a2r[i], conn); 
      // }
      // else {
      //   SqlQueries.updateApprovToRiga(a2r[i].getOid(), a2r[i].getQuantita(), conn);
      // }
    }
  }
  
  private ApprovToRiga [] getSortedByPriority(ApprovToRiga [] approvToRiga) {
    Arrays.sort(approvToRiga, new Comparator<ApprovToRiga>() {
      @Override
      public int compare(ApprovToRiga a1, ApprovToRiga a2) {
        // assumo che l'OID crescente sia sufficiente s
        // rappresetare l'età del record "riga"
        return a1.getRigaDett().getOid().compareTo(a2.getRigaDett().getOid());
      }
    } );
    return approvToRiga;
  }

  public List<ApprovvigionamentoFarmaco> getApprovvigionamentoSoddisfatto() {
    return approvSoddisfatto; 
  }

  public List<ApprovvigionamentoFarmaco> getApprovvigionamentoDaSoddisfare() {
    return approvDaSoddisfare; 
  }

  
  
}
