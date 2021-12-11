package fai.broker.task.impord;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fai.broker.models.OrdineIn;
import fai.broker.models.OrdineInRigaDett;

class OrdineInAnalyzer {


    static Logger logger = Logger.getLogger(OrdineInAnalyzer.class);


    static final double TOLLERANZA_TOTALI_PREZZI_SINGOLO_ORDINE = 0.07; // verificato 0.06
    static final double TOLLERANZA_TOTALI_PREZZI_SINGOLO_ORDINE_PER_INTERO_CSV = 0.007; // verificato 0.006, ossia 0.12 cumulativo su 18 ordini


    private String analyze(OrdineIn ordine) throws Exception {
        String descrOrdine = "ID Vendita " + ordine.getIdVendita() + ", ID utente " + ordine.getIdUtente();
        assertTrue("l'ordine " + descrOrdine + " non contiene righe", ordine.getRigaDettCount() > 0);
        //
        int quantitaRighe = 0;
        double prezzoRighe = 0;
        for (int i = 0; i < ordine.getRigaDettCount(); i++) {
            OrdineInRigaDett riga = ordine.getRigaDettAt(i);
            Integer quantita = riga.getQuantita();
            if (quantita == null || quantita <= 0) {
                return "Ordine " + descrOrdine + ": Quantita Riga n. " + (i + 1) + " non valida; atteso valore maggiore di zero, trovato: " + quantita;
            }
            quantitaRighe += "Coupon".equals(riga.getTitoloInserzione()) ? 0 : quantita;
            prezzoRighe += riga.getPrezzoFinale() * riga.getQuantita();
        }
        Integer quantitaOrdine = ordine.getQuantita();
        if (quantitaOrdine == null || quantitaOrdine <= 0) {
            return "Ordine " + descrOrdine + ": Quantita Totale non valida; atteso valore maggiore di zero, trovato: " + quantitaOrdine;
        }
        if (!(new Integer(quantitaRighe)).equals(quantitaOrdine)) {
            return "Ordine " + descrOrdine + ": Quantit� Totale dell'Ordine (" + quantitaOrdine + ") non coincidente con la Somma delle Quantit� delle righe del medesimo Ordine (" + quantitaRighe + ")";
        }
        double prezzoTotale = ordine.getPrezzoTotale() - ordine.getSpedizioneEImballaggio() - ordine.getAssicurazione() - ordine.getTariffaPerContrassegno();
        if (Math.abs(prezzoTotale - prezzoRighe) > TOLLERANZA_TOTALI_PREZZI_SINGOLO_ORDINE) {
            return "Ordine " + descrOrdine + ": Prezzo Totale dell'Ordine al netto di imballaggio, assicurazione, tariffa per contrassegno (" + prezzoTotale + ") non coincidente con la Somma dei Prezzi delle righe del medesimo Ordine (" + prezzoRighe + ") con un errore superiore alla tolleranza ammessa " + TOLLERANZA_TOTALI_PREZZI_SINGOLO_ORDINE;
        }
        return null;
    }

    private String asFarmacoDescr(OrdineInRigaDett riga) throws Exception {
        List<String> list = new ArrayList<String>();
        String ean = riga.getCodiceEan();
        if (ean != null && "".equals(ean.trim()) == false) list.add("EAN " + ean);
        String minSan = riga.getCodiceMinSan();
        if (minSan != null && "".equals(ean.trim()) == false) list.add("MinSan " + minSan);
        return fai.common.util.CollectionsTool.asCSV(list, ", ");
    }

    public String analyze(List<OrdineIn> ordini) throws Exception {
        int quantitaOrdini = 0;
        int quantitaRighe = 0;
        double prezzoOrdini = 0;
        double prezzoRighe = 0;
        for (int i = 0; i < ordini.size(); i++) {
            String PREFIX = "analisi Ordine #" + (i + 1) + "/" + ordini.size() + ": ";
            logger.info(PREFIX + "...");
            OrdineIn ordine = ordini.get(i);
            quantitaOrdini += ordine.getQuantita();
            prezzoOrdini += ordine.getPrezzoTotale() - ordine.getSpedizioneEImballaggio() - ordine.getAssicurazione() - ordine.getTariffaPerContrassegno();
            OrdineInRigaDett[] riga = ordine.getAllRigaDett();
            for (int j = 0; j < riga.length; j++) {
                //String magazzinoAcronym = riga[j].getMagazzinoAcronym();
                //if (magazzinoAcronym == null || "".equals(magazzinoAcronym.trim())) {
                //return "individua riga dell'Ordine con Id Ordine "+ordine.getIdOrdine()+", per la quale non risulata essere indicato il Magazzino al quale fare riferimento per il Farmaco "+asFarmacoDescr(riga[j]);
                //}
                boolean isCoupon = Boolean.TRUE.equals(riga[j].isCoupon());
                if (isCoupon == true && "Coupon".equals(riga[j].getTitoloInserzione()) == false
                        || isCoupon == false && "Coupon".equals(riga[j].getTitoloInserzione()) == true
                        || isCoupon == true && riga[j].getNumeroOggetto().startsWith("P") == false
                        || isCoupon == false && riga[j].getNumeroOggetto().startsWith("P") == true
                ) {
                    return "individua riga dell'Ordine con Id Ordine " + ordine.getIdOrdine() + ", contrassegnata come " + (isCoupon ? "Coupon" : "Prodtto (ossia non-Coupon)") + ", i cui campi Titolo Inserzione (" + riga[j].getTitoloInserzione() + "), Numero Oggetto (" + riga[j].getNumeroOggetto() + ") appaiono inconsistenti rispetto al vincoli che consentono discriminare le righe Coupon dalle righe Prodotto;";
                }
                if (isCoupon == false && "".equals(riga[j].getCodiceMinSan()) && "".equals(riga[j].getCodiceEan())) {
                    return "individua riga dell'Ordine con Id Ordine " + ordine.getIdOrdine() + ", per la quale non risulata essere stato determinato n� il codice MinSan n� il codice EAN corrispondente al Numero Oggetto " + riga[j].getNumeroOggetto();
                }
                //
                quantitaRighe += isCoupon ? 0 : riga[j].getQuantita();
                prezzoRighe += riga[j].getPrezzoFinale() * riga[j].getQuantita();
            }
            String error = analyze(ordine);
            if (error != null) return error;
        }
        if (quantitaOrdini != quantitaRighe) {
            return "Somma dei Totali Quantit� degli Ordini (" + quantitaOrdini + ") non coincidente con la Somma delle Quantit� delle Righe dei medesimi Ordini (" + quantitaRighe + ")";
        }
        double toll = TOLLERANZA_TOTALI_PREZZI_SINGOLO_ORDINE_PER_INTERO_CSV * ordini.size();
        if (Math.abs(prezzoOrdini - prezzoRighe) > toll) {
            return "Somma degli Importi Totali degli Ordini (" + prezzoOrdini + ") non coincidente con la Somma degli Importi delle Righe dei medesimi Ordini (" + prezzoRighe + ") con un errore superiore alla tolleranza ammessa " + toll;
        }
        return null;
    }


}
