package fai.broker.supplier.driver.comifar;

import fai.broker.db.SqlQueries;
import fai.broker.models.*;
import fai.broker.supplier.AbstractSupplierService;
import fai.imp.base.bean.ProcessedOrderBean;
import fai.imp.base.bean.ProductBean;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.base.task.AbstractDataCollector;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComifarSupplierService extends AbstractSupplierService {

    static Logger logger = Logger.getLogger(ComifarSupplierService.class);

    @Override
    public RequestMode getRequestMode() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setupMagazzino() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected void setupFornitore() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected List<DisponibilitaTemp> getAvailabilityNoCache(List<DisponibilitaTemp> fabbisogno) throws Exception {
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

            DisponibilitaReqTemp[] req = fabb.getAllReq();

            List<ProductBean> products = getAvailability(magazzino.getOrganizationCode(),
                    Arrays.stream(req)
                            .map(r -> new ProductBean(r.getCodiceMinSan(), r.getQuantitaRichiesta()))
                            .collect(Collectors.toList()));

            for (int i = 0; i < req.length; i++) {
                int quantitaDisponibile = 0;
                final String codiceMinsan = req[i].getCodiceMinSan();
                Optional<ProductBean> matchedProduct = products
                        .stream()
                        .filter(p -> p.getProductCode().equals(codiceMinsan))
                        .findFirst();

                if (matchedProduct.isPresent()) {
                    if (matchedProduct.get().getAvailibility() != null &&
                            matchedProduct.get().getAvailibility()) {
                        quantitaDisponibile = matchedProduct.get().getQuantity();
                    }
                }
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
        }
        return fabbisogno;
    }

    @Override
    protected OrdineOut confirmExecute(List<ApprovvigionamentoFarmaco> approvvigionamento) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        List<ProcessedOrderBean> processedOrders = orderProducts(magazzino.getOrganizationCode(),
                approvvigionamento
                        .stream()
                        .map(appr -> new ProductBean(appr.getCodiceMinSan(), appr.getQuantita()))
                        .collect(Collectors.toList()));

        OrdineOut ordineOut = new OrdineOut();
        ordineOut.setStatus(ItemStatus.VALUE_PROCESSED);
        ordineOut.setIdOrdine(processedOrders != null && !processedOrders.isEmpty()
                ? processedOrders.get(0).getNumeroOrdineFornitore() : null);
        ordineOut.setFornitore(approvvigionamento != null && approvvigionamento.size() > 0 ? approvvigionamento.get(0).getFornitore() : null);


        for (ApprovvigionamentoFarmaco appr : approvvigionamento) {
            fai.common.db.SqlQueries.logInfo("inoltro richiesta d'ordine a " + asShortDescr() + " ...",
                    appr.toXml(), null, conn);
            conn.commit();

            final String codiceMinsan = appr.getCodiceMinSan();
            Optional<ProcessedOrderBean> matchedProduct = processedOrders
                    .stream()
                    .filter(p -> p.getProductCode().equals(codiceMinsan))
                    .findFirst();

            if (matchedProduct.isPresent()) {
                if (matchedProduct.get().getMissingQuantity() != null &&
                        matchedProduct.get().getMissingQuantity() > 0) {
                    ApprovvigionamentoFarmaco missingAppr = new ApprovvigionamentoFarmaco();
                    missingAppr.setQuantita(matchedProduct.get().getMissingQuantity());
                    missingAppr.setCodiceMinSan(appr.getCodiceMinSan());
                    missingAppr.setStatus(StatusInfo.newToProcessInstance(null, null));
                    SqlQueries.insertApprovvigionamentoFarmaco(missingAppr, conn);
                }

                if (matchedProduct.get().getOrderedQuantity() != null &&
                        matchedProduct.get().getMissingQuantity() > 0) {
                    appr.setQuantita(matchedProduct.get().getOrderedQuantity());
                    appr.setStatus(StatusInfo.newProcessedInstance(null, null));
                    SqlQueries.updateApprovvigionamentoFarmaco(appr, false, conn);
                }
            }
            //
            // --- tracciamento nel log della risposta ricevuta ---
            //
            fai.common.db.SqlQueries.logInfo(
                    "risposta ricevuta per la richiesta di disponibilità inoltrata " + asShortDescr(), appr.toXml(),
                    null, conn);
            conn.commit();

        }
        return ordineOut;
    }

    private List<ProductBean> getAvailability(String organizationCode, List<ProductBean> products) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        FaiImportConfig config = fai.imp.base.db.SqlQueries.getFaiImportConfig("COMIFAR", "WHERE SERVICE_LOGIN=" + organizationCode, conn);
        AbstractDataCollector dataCollector =
                new fai.imp.comifar.task.ComifarDataCollector(config, conn);
        return dataCollector.doGetAvailiblityData(products);
    }

    private List<ProcessedOrderBean> orderProducts(String organizationCode, List<ProductBean> products) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        FaiImportConfig config = fai.imp.base.db.SqlQueries.getFaiImportConfig("COMIFAR", "WHERE SERVICE_LOGIN=" + organizationCode, conn);
        AbstractDataCollector dataCollector =
                new fai.imp.comifar.task.ComifarDataCollector(config, conn);
        return dataCollector.doOrderProducts(products);
    }
}
