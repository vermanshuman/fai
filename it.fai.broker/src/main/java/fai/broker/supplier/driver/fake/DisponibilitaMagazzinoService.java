package fai.broker.supplier.driver.fake;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.DisponibilitaReqTemp;
import fai.broker.models.DisponibilitaResTemp;
import fai.broker.models.DisponibilitaTemp;
import fai.broker.models.ItemStatus;
import fai.broker.models.OrdineOut;
import fai.broker.models.StatusInfo;
import fai.broker.supplier.AbstractSupplierService;
import fai.broker.supplier.ApprovFarmaco2DispTempConverter;
import fai.broker.supplier.SupplierService.ManagedRequest;
import fai.broker.util.Constants;
import fai.common.util.ExceptionsTool;
import fai.orainvmgr.jsonbean.GetInventoryQuantitiesRes;
import fai.orainvmgr.service.OraInvMgrService;

public class DisponibilitaMagazzinoService extends AbstractSupplierService {

    static Logger logger = Logger.getLogger(DisponibilitaMagazzinoService.class);

    @Override
    public List<DisponibilitaTemp> getAvailabilityNoCache(List<DisponibilitaTemp> fabbisogno) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        //
        // A seconda che il "RequestMode" restituito sia "OneProductOneRequest"
        // o "MoreProductOneRequest" la List ricevuta in input sarà caratterizzata
        // da 1 o N elementi (con N pari al numero di farmaci/prodotti per i quali
        // richiedere la disponibilità)
        //
        for (DisponibilitaTemp fabb : fabbisogno) {
            //
            // --- tracciamento nel log della richiesta che sta per essere inoltrata ---
            //
            fai.common.db.SqlQueries.logInfo("inoltro richiesta di disponibilità al " + asShortDescr() + " ...",
                    fabb.toXml(), null, conn);
            conn.commit();
            //
            // --- aggiornamento delle FAI_DISPONIBILITA_TEMP/FAI_DISPONIBILITA_REQ_TEMP ---
            //
            fabb.setStatus(StatusInfo.newProcessingInstance("richieta fittizia", fabb.toXml()));
            SqlQueries.storeRequestDisponibilitaTemp(fabb, conn);
            conn.commit();
            //
            DisponibilitaReqTemp[] req = fabb.getAllReq();
            for (int i = 0; i < req.length; i++) {
                int quantitaDisponibile = getAvailabilityOraService(magazzino.getOrganizationCode(),
                        req[i].getCodiceMinSan());
                DisponibilitaResTemp res = new DisponibilitaResTemp();
                res.setQuantitaDisponibile(quantitaDisponibile);
                req[i].addResp(res);
            }
            //
            // --- tracciamento nel log della risposta ricevuta ---
            //
            fai.common.db.SqlQueries.logInfo(
                    "risposta ricevuta per la richiesta di disponibilità inoltrata " + asShortDescr(), fabb.toXml(),
                    null, conn);
            conn.commit();
            //
            // --- aggiornamento delle FAI_DISPONIBILITA_TEMP/FAI_DISPONIBILITA_RES_TEMP ---
            //
            fabb.setStatus(StatusInfo.newProcessedInstance("risposta fittizia", fabb.toXml()));
            SqlQueries.storeResponseDisponibilitaTemp(fabb, conn);
            conn.commit();
            //
        }
        return fabbisogno;
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
                        throw new IllegalStateException("inammissibile, " + cached.size()
                                + " risposte in cache per un fabbisogno di " + fabbisogno.size() + " elementi");
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
            // --- conversion of demand from type ApprovvigionamentoFarmaco to
            // DisponibilitaTemp ---
            //
            setMagazzinoOrFornitore(fabbisogno);
            ApprovFarmaco2DispTempConverter converter = new ApprovFarmaco2DispTempConverter();
            converter.setup(magazzino, fornitore);
            converter.setFabbisognoRequest(fabbisogno, getRequestMode());
            List<DisponibilitaTemp> fabbisognoAsDispTemp = converter.getDisponibilitaTempListBeforeRequest();
            //
            // --- aggiornamento timestamp richiesta fabbisogno ApprovvigionamentoFarmaco
            // ---
            //
            setRichiestaDisponibilitaReq(fabbisogno, ItemStatus.VALUE_PROCESSING, null, null);
            //
            // --- inoltro della richiesta effettiva ---
            //
            List<DisponibilitaTemp> disponibilitaAsDispTemp = getAvailabilityNoCache(fabbisognoAsDispTemp);
            responseReceived = true;
            //
            // --- aggiornamento timestamp rispsota fabbisogno ApprovvigionamentoFarmaco ---
            // NOTA:
            // aggiorna solo il timestamp per registrare il fatto che è stata ottenuta
            // risposta, ma non la quantità, gestita al di fuori di questa classe
            //
            setRichiestaDisponibilitaRes(fabbisogno, ItemStatus.VALUE_PROCESSING, null, null);
            //
            // --- elaborazione della risposta ---
            //
            converter.setDisponibilitaTempListAfterResponse(disponibilitaAsDispTemp);
            responseList = converter.getManagedRequests();
            if (lastErrorOccurred == null) {
                if (responseList.size() != fabbisogno.size())
                    throw new IllegalStateException("inammissibile, " + responseList.size()
                            + " risposte ottenute per un fabbisogno di " + fabbisogno.size() + " elementi");
            }
            if (cache != null && lastErrorOccurred == null) {
                List<List<ApprovvigionamentoFarmaco>> toCache = new ArrayList<List<ApprovvigionamentoFarmaco>>();
                for (ManagedRequest mr : responseList) {
                    toCache.add(clone(mr.getAvailable()));
                }
                String key = asAvailabilityKey(fabbisogno);
                cache.put(key, toCache);
            }
            return responseList;

        } catch (Throwable th) {
            String error = ExceptionsTool.getExceptionDescription("eccezione inattesa tentando di interrogare il "
                    + asShortDescr() + " per la determinazione della disponibilità", th);
            if (responseReceived == false) {
                this.setRichiestaDisponibilitaReq(fabbisogno, ItemStatus.VALUE_ERROR, error,
                        ExceptionsTool.getStackTraceAsString(th));
            } else {
                this.setRichiestaDisponibilitaRes(fabbisogno, ItemStatus.VALUE_ERROR, error,
                        ExceptionsTool.getStackTraceAsString(th));
            }
            throw new Exception(error, th);
        }

    }

    @Override
    protected void setupMagazzino() throws Exception {
        // nothing to do ... è un fake ...
    }

    @Override
    protected void setupFornitore() throws Exception {
        // nothing to do ... è un fake ...
    }

    public RequestMode getRequestMode() throws Exception {
        //
        // random... così le provo entrambe!
        return Math.random() < 0.5 ? RequestMode.MoreProductOneRequest : RequestMode.OneProductOneRequest;
    }

    @Override
    protected OrdineOut confirmExecute(List<ApprovvigionamentoFarmaco> approvvigionamento) throws Exception {
        throw new NoSuchMethodError("non ancora implementato");
    }

    private Integer getAvailabilityOraService(String organizationCode, String prodottoCode) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        OraInvMgrService os = new OraInvMgrService(Constants.ORA_INV_SERVICE_USERNAME,
                Constants.ORA_INV_SERVICE_PASSWORD, Constants.ORA_INV_SERVICE_SERVICE_URL);
        GetInventoryQuantitiesRes response = os.getInventoryQuantities(organizationCode, prodottoCode);
        if (response.getReturnStatus().equals("SUCCESS")) {
            logger.info("availability :: " + response.getAvailableToTransact());
            return response.getAvailableToTransact();
        }
        return 0;
    }

}
