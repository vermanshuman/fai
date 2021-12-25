package fai.imp.farmaclick.ws;

import java.lang.reflect.Method;
import java.util.Hashtable;

class EsitoServizioUtil {

  
  private static Hashtable<Integer, String> esitoServizioDescr = new Hashtable<Integer, String>();
  static {
    esitoServizioDescr.put(0, "Nessun errore");
    esitoServizioDescr.put(1, "Servizio non svolto");
    esitoServizioDescr.put(2, "Livello di API Farmaclick non supportato");
    esitoServizioDescr.put(3, "Servizio non supportato dal fornitore: i grossisti che aderiscono a Farmaclick possono attivare un insieme ridotto di servizi; questo errore si verifica quando viene richiesto un servizio che il fornitore ha deciso di non supportare o che ha limitato in determinate fasce orarie");
    esitoServizioDescr.put(4, "Chiave di sessione non valida o scaduta");
    esitoServizioDescr.put(5, "Collegamento con fornitore non attivo: si è verificato un errore di comunicazione tra il server Farmaclick e l'elaboratore del grossista");
    esitoServizioDescr.put(6, "L'elaboratore del grossista sta eseguendo le operazioni di chiusura annuali e non può rispondere alle richieste delle farmacie");
    esitoServizioDescr.put(7, "L'elaboratore del grossista sta facendo delle operazioni di manutenzione programmata e non può rispondere");
    esitoServizioDescr.put(8, "Servizio non disponibile in questo momento (1) . Riprovare in un secondo tempo.");
    esitoServizioDescr.put(9, "Formato dei dati non corretto (2)");
    esitoServizioDescr.put(10, "Errore interno del server Farmaclick");
    esitoServizioDescr.put(11, "Credenziali di accesso (ID Sessione o User e Password) errate");
    esitoServizioDescr.put(12, "ID Sessione scaduto");
    esitoServizioDescr.put(13, "Farmacia non abilitata");
    esitoServizioDescr.put(14, "Farmacia attiva su Farmaclick, ma non riconosciuta dal fornitore");
    esitoServizioDescr.put(15, "Timeout durante il collegamento con il fornitore (3)");
    esitoServizioDescr.put(18, "Codice fornitore non valido");
    esitoServizioDescr.put(19, "Errore durante la compressione del file");
    esitoServizioDescr.put(20, "Errore di collegamento al Public Server");
    esitoServizioDescr.put(21, "Numero eccessivo di connessioni sul fornitore");
    esitoServizioDescr.put(22, "Codice Corriere non valido");
    esitoServizioDescr.put(101, "User e/o password corrette, ma farmacia non attiva su Farmaclick");
    esitoServizioDescr.put(102, "User e password corrette, ma l'abilitazione su Farmaclick è scaduta");
    esitoServizioDescr.put(103, "L' Ordine è stato ricevuto, ma l'elaboratore centrale del Distributore Intermedio è momentaneamente off-line. Non è possibile restituire i mancanti alla farmacia, ma l'ordine è stato comunque acquisito dal server Farmaclick");
    esitoServizioDescr.put(104, "Non utilizzato");
    esitoServizioDescr.put(105, "CodiceTipoOrdine non valido o non più attivo");
    esitoServizioDescr.put(106, "L'ordine non può essere accettato perché non rispetta i parametri indicati dal campo codiceTipoOrdine o perché non raggiunge l'importo minimo richiesto dal fornitore");
    esitoServizioDescr.put(107, "Superati limiti su singolo ordine");
    esitoServizioDescr.put(108, "E' stato superato il limite massimo di prodotti di cui richiedere informazioni in una singola chiamata");
    esitoServizioDescr.put(109, "Programma di gestione contabile non interfacciato con Farmaclick");
    esitoServizioDescr.put(110, "L'ordine non contiene nessuna riga valida");
    esitoServizioDescr.put(111, "Codice di raggruppamento offerta non trovato");
    esitoServizioDescr.put(112, "Documento origine non trovato");
    esitoServizioDescr.put(113, "Array con prodotti non valido");
    esitoServizioDescr.put(114, "Riferimento ordine non trovato");
    esitoServizioDescr.put(115, "Ordine Doppio, non rielaborato dal fornitore");
    esitoServizioDescr.put(116, "Ordine con aggiunte. L'elaborazione del fornitore si è limitata alle righe aggiunte");
    esitoServizioDescr.put(119, "Listino non presente");
    esitoServizioDescr.put(123, "Listino non abilitato");
    esitoServizioDescr.put(124, "Riferimento ordine farmacia non valido");
    esitoServizioDescr.put(125, "Errore durante il reperimento del file allegato da spedire");
    esitoServizioDescr.put(126, "Non è possibile cancellare un ordine in fase di consolidamento");
    esitoServizioDescr.put(127, "Ordine in fase di consolidamento");
    esitoServizioDescr.put(128, "Tipo documento non riconosciuto");
    esitoServizioDescr.put(129, "Codice Accorpatore condizione non riconosciuto");
    esitoServizioDescr.put(130, "Numero RMA non valido.");
    esitoServizioDescr.put(131, "Numero RMA scaduto");
    esitoServizioDescr.put(132, "Codice motivazione reso non valido");
    esitoServizioDescr.put(133, "Riferimento Reso Farmacia non valido");
    esitoServizioDescr.put(134, "L'elaboratore del grossista non è in grado di evadere l'ordine. L'ordine deve essere considerato come annullato");
    esitoServizioDescr.put(135, "Ricezione ordini sospesa, ordine non accettato dal fornitore");

  }
  
  public static synchronized Integer getCodice(Object o) throws Exception {
    Integer esitoServizioCodice = null;
    if (o != null) { 
      Class c = o.getClass();
      Method [] method = c.getMethods();
      for (int i = 0; i < method.length; i++) {
        Method m = method[i];
        String methodName = m.getName();
        if ("getEsitoServizio".equals(methodName)) {
          esitoServizioCodice = (Integer)m.invoke(o);
        }
      }
    }
    return esitoServizioCodice;
  }
      

  public static synchronized String getDescrizione(Object o) throws Exception {
    String descr = null;
    Integer esitoServizioCodice = null;
    if (o == null) { 
      descr = "esito non disponibile";
    }
    else {
      Class c = o.getClass();
      Method [] method = c.getMethods();
      for (int i = 0; i < method.length; i++) {
        Method m = method[i];
        String methodName = m.getName();
        if ("getEsitoServizio".equals(methodName)) {
          esitoServizioCodice = (Integer)m.invoke(o);
          descr = getDescrizione(esitoServizioCodice);
        }
      }
    }
    return descr;
  }
  
  public static synchronized String getDescrizione(Integer esitoServizio) throws Exception {
    String descr = esitoServizio == null ? null : esitoServizioDescr.get(esitoServizio);
    if (descr == null) {
      descr = "non documentato";
    }
    else {
      descr = "\""+descr+"\"";
    }
    return descr;
 
  }
  

}
