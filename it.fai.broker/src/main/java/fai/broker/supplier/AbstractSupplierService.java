package fai.broker.supplier;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.DisponibilitaTemp;
import fai.broker.models.Fornitore;
import fai.broker.models.ItemStatus;
import fai.broker.models.Magazzino;
import fai.broker.models.OrdineOut;
import fai.common.util.ExceptionsTool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public abstract class AbstractSupplierService implements SupplierService {

    static Logger logger = Logger.getLogger(AbstractSupplierService.class);

    protected Magazzino magazzino = null;
    protected Fornitore fornitore = null;
    protected Connection conn = null;
    protected Hashtable<String, List<List<ApprovvigionamentoFarmaco>>> cache = null;
    protected int cacheHit = 0;
    protected int cacheMissing = 0;
    protected String lastErrorOccurred = null;


    @Override
    public void setup(Magazzino magazzino, Connection conn) throws Exception {
        this.magazzino = magazzino;
        this.conn = conn;
        setupMagazzino();

    }

    @Override
    public void setup(Fornitore fornitore, Connection conn) throws Exception {
        this.fornitore = fornitore;
        this.conn = conn;
        setupFornitore();
    }


    protected abstract void setupMagazzino() throws Exception;

    protected abstract void setupFornitore() throws Exception;

    @Override
    public boolean isWarehouse() throws Exception {
        return magazzino != null;
    }

    protected String asAvailabilityKey(List<ApprovvigionamentoFarmaco> fabbisogno) throws Exception {
        StringBuffer sb = new StringBuffer("");
        for (ApprovvigionamentoFarmaco af : fabbisogno) {
            sb.append(af.getCodiceMinSan() + "//" + af.getCodiceEan() + "//" + af.getQuantita() + ";");
        }
        String key = fai.common.util.MD5.getMD5(sb.toString());
        return key;
    }


    /**
     * Il metodo per la "clonazione" serve scongiurare il pericolo che le operazioni sui riferimenti
     * alle istanze in cache alterino il contenuto di quest'ultima.
     *
     * @param source
     * @return
     * @throws Exception
     */
    protected List<ApprovvigionamentoFarmaco> clone(List<ApprovvigionamentoFarmaco> masters) throws Exception {
        List<ApprovvigionamentoFarmaco> clones = new ArrayList<ApprovvigionamentoFarmaco>();
        for (ApprovvigionamentoFarmaco master : masters) {
            ApprovvigionamentoFarmaco clone = new ApprovvigionamentoFarmaco();
            clone.copyFrom(master);
            clones.add(clone);
        }
        return clones;
    }


    protected String asShortDescr() {
        if (magazzino != null) {
            return "magazzino " + magazzino.getAcronym();
        } else if (fornitore != null) {
            return "fornitore " + fornitore.getCodice();
        } else {
            return null;
        }
    }

    private boolean equals(Magazzino m1, Magazzino m2) throws Exception {
        if (m1 == null && m2 == null)
            return true;
        else if (m1 == null && m2 != null)
            return false;
        else if (m1 != null && m2 == null)
            return false;
        //
        if (m1.getAcronym() != null && m2.getAcronym() != null) return m1.getAcronym().equals(m2.getAcronym());
        if (m1.getOid() != null && m2.getOid() != null) return m1.getOid().equals(m2.getOid());
        //
        throw new IllegalStateException("inammissibile, elementi insufficienti per confrontare i due Magazzini:\r\nMagazzino 1: " + m1.toXml() + "\r\nMagazzino 2: " + m2.toXml());
    }

    private boolean equals(Fornitore f1, Fornitore f2) throws Exception {
        if (f1 == null && f2 == null)
            return true;
        else if (f1 == null && f2 != null)
            return false;
        else if (f1 != null && f2 == null)
            return false;
        //
        if (f1.getCodice() != null && f2.getCodice() != null) return f1.getCodice().equals(f2.getCodice());
        if (f1.getOid() != null && f2.getOid() != null) return f1.getOid().equals(f2.getOid());
        //
        throw new IllegalStateException("inammissibile, elementi insufficienti per confrontare i due Magazzini:\r\nFornitore 1: " + f1.toXml() + "\r\nFornitore 2: " + f2.toXml());
    }


    protected void setMagazzinoOrFornitore(List<ApprovvigionamentoFarmaco> approvList) throws Exception {
        for (ApprovvigionamentoFarmaco approv : approvList) {
            Magazzino currMagazzino = approv.getMagazzino();
            Fornitore currFornitore = approv.getFornitore();
            if (currMagazzino != null && currFornitore != null)
                throw new IllegalStateException("inammissibile, Magazzino e Fornitore entrambi valorizzati per l'istanza " + ApprovvigionamentoFarmaco.class.getName() + " identificata dall'OID " + approv.getOid() + " con, rispettivamente,\r\n " + (currMagazzino == null ? null : currMagazzino.toXml()) + "\r\n e \r\n" + (currFornitore == null ? null : currFornitore.toXml()));
            if (currMagazzino != null && equals(currMagazzino, magazzino) == false)
                throw new IllegalStateException("inammissibile, all'istanza " + ApprovvigionamentoFarmaco.class.getName() + " identificata dall'OID " + approv.getOid() + " risulta assegnato un Magazzino differente dal " + asShortDescr() + " per il quale opera la presente implementazione di " + SupplierService.class.getName() + "; dettagli circa l'istanza di Magazzino inattesa:\r\n " + (currMagazzino == null ? null : currMagazzino.toXml()));
            if (currFornitore != null && equals(currFornitore, fornitore) == false)
                throw new IllegalStateException("inammissibile, all'istanza " + ApprovvigionamentoFarmaco.class.getName() + " identificata dall'OID " + approv.getOid() + " risulta assegnato un Fornitore differente dal " + asShortDescr() + " per il quale opera la presente implementazione di " + SupplierService.class.getName() + "; dettagli circa l'istanza di Fornitore inattesa:\r\n " + (currFornitore == null ? null : currFornitore.toXml()));
            approv.setMagazzino(magazzino);
            approv.setFornitore(fornitore);
        }
    }


    @Override
    public List<ManagedRequest> getAvailability(List<ApprovvigionamentoFarmaco> fabbisogno) throws Exception {
        boolean responseReceived = false;
        try {
            final String METH_NAME = new Object() {
            }.getClass().getEnclosingMethod().getName();
            final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
            logger.info(LOG_PREFIX + "...");
            lastErrorOccurred = null;
            //
            // === RICORSO ALLA CACHE, SE CONSENTITO ===
            //
            List<ManagedRequest> responseList = new ArrayList<ManagedRequest>();
            if (cache != null) {
                String key = asAvailabilityKey(fabbisogno);
                List<List<ApprovvigionamentoFarmaco>> cached = cache.get(key);
                if (cached != null) {
                    if (cached.size() != fabbisogno.size())
                        throw new IllegalStateException("inammissibile, " + cached.size() + " risposte in cache per un fabbisogno di " + fabbisogno.size() + " elementi");
                    for (int i = 0; i < fabbisogno.size(); i++) {
                        ManagedRequest mr = new ManagedRequest(fabbisogno.get(i), clone(cached.get(i)));
                        responseList.add(mr);
                    }
                    cacheHit++;
                    logger.info("cache hit *" + cacheHit + "*, cache missing " + cacheMissing);
                    return responseList;
                } else {
                    cacheMissing++;
                    logger.info("cache hit " + cacheHit + ", cache missing *" + cacheMissing + "*");
                }
            }
            //
            // === INTERROGAZIONE VERA E PROPRIA ===
            //
            //
            // --- conversion of demand from type ApprovvigionamentoFarmaco to DisponibilitaTemp ---
            //
            setMagazzinoOrFornitore(fabbisogno);
            ApprovFarmaco2DispTempConverter converter = new ApprovFarmaco2DispTempConverter();
            converter.setup(magazzino, fornitore);
            converter.setFabbisognoRequest(fabbisogno, getRequestMode());
            List<DisponibilitaTemp> fabbisognoAsDispTemp = converter.getDisponibilitaTempListBeforeRequest();
            //
            // --- aggiornamento timestamp richiesta fabbisogno ApprovvigionamentoFarmaco ---
            //
            setRichiestaDisponibilitaReq(fabbisogno, ItemStatus.VALUE_PROCESSING, null, null);
            //
            // --- inoltro della richiesta effettiva ---
            //
            List<DisponibilitaTemp> disponibilitaAsDispTemp = getAvailabilityNoCache(fabbisognoAsDispTemp);
            responseReceived = true;
            //
            // --- aggiornamento timestamp rispsota fabbisogno ApprovvigionamentoFarmaco ---
            //     NOTA:
            //     aggiorna solo il timestamp per registrare il fatto che � stata ottenuta
            //     risposta, ma non la quantit�, gestita al di fuori di questa classe
            //
            setRichiestaDisponibilitaRes(fabbisogno, ItemStatus.VALUE_PROCESSING, null, null);
            //
            // --- elaborazione della risposta  ---
            //
            converter.setDisponibilitaTempListAfterResponse(disponibilitaAsDispTemp);
            responseList = converter.getManagedRequests();
            if (lastErrorOccurred == null) {
                if (responseList.size() != fabbisogno.size())
                    throw new IllegalStateException("inammissibile, " + responseList.size() + " risposte ottenute per un fabbisogno di " + fabbisogno.size() + " elementi");
            }
            //
            if (cache != null && lastErrorOccurred == null) {
                List<List<ApprovvigionamentoFarmaco>> toCache = new ArrayList<List<ApprovvigionamentoFarmaco>>();
                for (ManagedRequest mr : responseList) {
                    toCache.add(clone(mr.getAvailable()));
                }
                String key = asAvailabilityKey(fabbisogno);
                cache.put(key, toCache);
            }
            //
            return responseList;
        } catch (Throwable th) {
            String error = ExceptionsTool.getExceptionDescription("eccezione inattesa tentando di interrogare il " + asShortDescr() + " per la determinazione della disponibilit�", th);
            if (responseReceived == false) {
                this.setRichiestaDisponibilitaReq(fabbisogno, ItemStatus.VALUE_ERROR, error, ExceptionsTool.getStackTraceAsString(th));
            } else {
                this.setRichiestaDisponibilitaRes(fabbisogno, ItemStatus.VALUE_ERROR, error, ExceptionsTool.getStackTraceAsString(th));
            }
            throw new Exception(error, th);
        }
    }

    @Override
    public OrdineOut confirm(List<ApprovvigionamentoFarmaco> approvvigionamento) throws Exception {
        lastErrorOccurred = null;
        return confirmExecute(approvvigionamento);
    }


    protected abstract List<DisponibilitaTemp> getAvailabilityNoCache(List<DisponibilitaTemp> fabbisogno) throws Exception;

    protected abstract OrdineOut confirmExecute(List<ApprovvigionamentoFarmaco> approvvigionamento) throws Exception;

    @Override
    public void setCacheEnabled(boolean value) throws Exception {
        if (value == true) {
            cache = new Hashtable<String, List<List<ApprovvigionamentoFarmaco>>>();
        } else {
            cache = null;
        }

    }

    @Override
    public String getError() throws Exception {
        return lastErrorOccurred;
    }


    /**
     * Fare riferimento a quanto documentato per {@link #setRichiestaDisponibilitaReq(List, ItemStatus, String, String)}
     *
     * @param afList
     * @param status
     * @param statusDescr
     * @param statusTechDescr
     * @throws Exception
     */
    private void setRichiestaDisponibilitaReq(ApprovvigionamentoFarmaco af, ItemStatus status, String statusDescr, String statusTechDescr) throws Exception {
        setRichiestaDisponibilitaReq(Arrays.asList(new ApprovvigionamentoFarmaco[]{af}), status, statusDescr, statusTechDescr);
    }


    /**
     * Fare riferimento a quanto documentato per {@link #setRichiestaDisponibilitaRes(List, ItemStatus, String, String)}
     *
     * @param afList
     * @param status
     * @param statusDescr
     * @param statusTechDescr
     * @throws Exception
     */
    private void setRichiestaDisponibilitaRes(ApprovvigionamentoFarmaco af, ItemStatus status, String statusDescr, String statusTechDescr) throws Exception {
        setRichiestaDisponibilitaRes(Arrays.asList(new ApprovvigionamentoFarmaco[]{af}), status, statusDescr, statusTechDescr);
    }


    /**
     * Fare riferimento a quanto documentato per {@link #setConfermaDisponibilitaReq(List, ItemStatus, String, String)}
     *
     * @param afList
     * @param status
     * @param statusDescr
     * @param statusTechDescr
     * @throws Exception
     */
    private void setConfermaDisponibilitaReq(ApprovvigionamentoFarmaco af, ItemStatus status, String statusDescr, String statusTechDescr) throws Exception {
        setConfermaDisponibilitaReq(Arrays.asList(new ApprovvigionamentoFarmaco[]{af}), status, statusDescr, statusTechDescr);
    }


    /**
     * Fare riferimento a quanto documentato per {@link #setConfermaDisponibilitaRes(List, ItemStatus, String, String)}
     *
     * @param afList
     * @param status
     * @param statusDescr
     * @param statusTechDescr
     * @throws Exception
     */
    private void setConfermaDisponibilitaRes(ApprovvigionamentoFarmaco af, ItemStatus status, String statusDescr, String statusTechDescr) throws Exception {
        setConfermaDisponibilitaRes(Arrays.asList(new ApprovvigionamentoFarmaco[]{af}), status, statusDescr, statusTechDescr);
    }


    /**
     * Metodo di utilit� per l'assegnazione della data/ora corrente al campo
     * DISPONIBILITA_RICHIESTA_REQ e gli attributi di status passati al
     * set di record FAI_APPROVVIGIONAMENTO_FARMACO corrispondenti alla
     * {@link List} di istanzanze ricevute in input.<br/>
     * <br/>
     * Se i parametri di status sono null, assegna i valori di default.<br/>
     * <br/>
     * Immediatamente dopo gli statement di UPDATE esegue COMMIT.<br/>
     * <br/>
     * Da utilizzare per quei servizi, esposti da Magazzini o Fornitori, che
     * accettano richieste di disponibilit� per pi� farmaci (quelli della
     * {@link List}) nella stessa transazione/richiesta.<br/>
     * <br/>
     * Se il servizio opera su una sola richiesta di disponibilit� per volta,
     * ricorrere invece alla {@link #setRichiestaDisponibilitaReq(ApprovvigionamentoFarmaco, ItemStatus, String, String)}
     *
     * @param afList
     * @param status
     * @param statusDescr
     * @param statusTechDescr
     * @throws Exception
     */
    private void setRichiestaDisponibilitaReq(List<ApprovvigionamentoFarmaco> afList, ItemStatus status, String statusDescr, String statusTechDescr) throws Exception {
        if (status == null) status = ItemStatus.VALUE_PROCESSING;
        if (statusDescr == null) statusDescr = "inoltro richiesta di disponibilit� a " + asShortDescr() + " ...";
        //
        SqlQueries.setApprovvigionamentoFarmacoToNow(afList, ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_REQ, conn);
        SqlQueries.setApprovvigionamentoFarmacoStatus(afList, status.getOid(), statusDescr, statusTechDescr, conn);
        conn.commit();
    }

    /**
     * Vale quanto documentato per {@link #setRichiestaDisponibilitaReq(List, ItemStatus, String, String)},
     * ma applicato alla risposta ricevuta per la richiesta di disponibilit�,
     * con la conseguenza che il campo oggetto dell'aggiornamento (oltre quelli di status) sar�
     * DISPONIBILITA_RICHIESTA_RES (anzich� DISPONIBILITA_RICHIESTA_REQ)
     *
     * @param afList
     * @param status
     * @param statusDescr
     * @param statusTechDescr
     * @throws Exception
     */
    private void setRichiestaDisponibilitaRes(List<ApprovvigionamentoFarmaco> afList, ItemStatus status, String statusDescr, String statusTechDescr) throws Exception {
        if (status == null) status = ItemStatus.VALUE_PROCESSING;
        if (statusDescr == null)
            statusDescr = "inoltro richiesta di disponibilit� a " + asShortDescr() + " completato (risposta ricevuta)";
        //
        SqlQueries.setApprovvigionamentoFarmacoToNow(afList, ApprovvigionamentoFarmaco.DISPONIBILITA_RICHIESTA_RES, conn);
        SqlQueries.setApprovvigionamentoFarmacoStatus(afList, status.getOid(), statusDescr, statusTechDescr, conn);
        conn.commit();
    }

    /**
     * Vale quanto documentato per {@link #setRichiestaDisponibilitaReq(List, ItemStatus, String, String)},
     * ma applicato alla richiesta di conferma della disponibilit�,
     * con la conseguenza che il campo oggetto dell'aggiornamento (oltre quelli di status) sar�
     * DISPONIBILITA_CONFERMA_REQ (anzich� DISPONIBILITA_RICHIESTA_REQ)
     *
     * @param afList
     * @param status
     * @param statusDescr
     * @param statusTechDescr
     * @throws Exception
     */
    private void setConfermaDisponibilitaReq(List<ApprovvigionamentoFarmaco> afList, ItemStatus status, String statusDescr, String statusTechDescr) throws Exception {
        if (status == null) status = ItemStatus.VALUE_PROCESSING;
        if (statusDescr == null)
            statusDescr = "inoltro richiesta di conferma della disponibilit� a " + asShortDescr() + " ...";
        //
        SqlQueries.setApprovvigionamentoFarmacoToNow(afList, ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_REQ, conn);
        SqlQueries.setApprovvigionamentoFarmacoStatus(afList, status.getOid(), statusDescr, statusTechDescr, conn);
        conn.commit();
    }

    /**
     * Vale quanto documentato per {@link #setRichiestaDisponibilitaReq(List, ItemStatus, String, String)},
     * ma applicato alla risposta ottenuta per la richiesta di conferma della disponibilit�,
     * con la conseguenza che il campo oggetto dell'aggiornamento (oltre quelli di status) sar�
     * DISPONIBILITA_CONFERMA_RES (anzich� DISPONIBILITA_RICHIESTA_REQ)
     *
     * @param afList
     * @param status
     * @param statusDescr
     * @param statusTechDescr
     * @throws Exception
     */
    private void setConfermaDisponibilitaRes(List<ApprovvigionamentoFarmaco> afList, ItemStatus status, String statusDescr, String statusTechDescr) throws Exception {
        if (status == null) status = ItemStatus.VALUE_PROCESSING;
        if (statusDescr == null)
            statusDescr = "inoltro richiesta di conferma della disponibilit� a " + asShortDescr() + " completata (risposta ricevuta)";
        //
        SqlQueries.setApprovvigionamentoFarmacoToNow(afList, ApprovvigionamentoFarmaco.DISPONIBILITA_CONFERMATA_RES, conn);
        SqlQueries.setApprovvigionamentoFarmacoStatus(afList, status.getOid(), statusDescr, statusTechDescr, conn);
        conn.commit();
    }


}
