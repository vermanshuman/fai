package fai.broker.supplier.driver.comifar;

import fai.broker.db.SqlQueries;
import fai.broker.models.*;
import fai.broker.supplier.AbstractSupplierService;
import fai.imp.base.bean.ProcessedOrderBean;
import fai.imp.base.bean.ProcessedOrdersBean;
import fai.imp.base.bean.ProductBean;
import fai.imp.base.models.FaiImportConfig;
import fai.imp.base.task.AbstractDataCollector;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComifarSupplierService extends AbstractSupplierService {

    static Logger logger = Logger.getLogger(ComifarSupplierService.class);

    @Override
    public RequestMode getRequestMode() throws Exception {
        //return RequestMode.MoreProductOneRequest;
        return RequestMode.OneProductOneRequest;
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

            List<ProductBean> products = getProductAvailability(Arrays.stream(req)
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

        ProcessedOrdersBean processedOrdersBean =  orderProducts(approvvigionamento
                .stream()
                .map(appr -> new ProductBean(appr.getCodiceMinSan(), appr.getQuantita()))
                .collect(Collectors.toList()));

        List<ProcessedOrderBean> processedOrders = processedOrdersBean.getProcessedOrders();
        OrdineOut ordineOut = new OrdineOut();

        ordineOut.setStatus(ItemStatus.VALUE_PROCESSED);
        if(approvvigionamento != null && approvvigionamento.size() > 0)
            ordineOut.setFornitore(approvvigionamento.get(0).getFornitore());
        if(processedOrders != null && !processedOrders.isEmpty()){
            ordineOut.setIdOrdine(processedOrders.get(0).getNumeroOrdineFornitore());
            ordineOut.setIdRicevuta(processedOrders.get(0).getIdVendita());
        }
        ordineOut.setRequestXml(processedOrdersBean.getRequestXML());
        ordineOut.setResponseXml(processedOrdersBean.getResponseXMl());

        ordineOut.setFornitore(approvvigionamento != null && approvvigionamento.size() > 0 ? approvvigionamento.get(0).getFornitore() : null);

        ordineOut = SqlQueries.insertOrdineOut(ordineOut, conn);

        for (ApprovvigionamentoFarmaco appr : approvvigionamento) {
            fai.common.db.SqlQueries.logInfo("inoltro richiesta d'ordine a " + asShortDescr() + " ...",
                    appr.toXml(), null, conn);
            conn.commit();

            final String codiceMinsan = appr.getCodiceMinSan();
            Optional<ProcessedOrderBean> matchedProduct = processedOrders
                    .stream()
                    .filter(p -> p.getProductCode().equals(codiceMinsan))
                    .findFirst();

            if(matchedProduct == null){
                matchedProduct = processedOrders
                        .stream()
                        .filter(p -> StringUtils.isNotBlank(p.getSusbituteMinsan())
                                        && (!p.getSusbituteMinsan().equalsIgnoreCase("0")
                                && p.getSusbituteMinsan().equals(codiceMinsan)))
                        .findFirst();
            }
            if (matchedProduct.isPresent()) {
                manageOrderResponse(matchedProduct.get(), appr, codiceMinsan, ordineOut);
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

    private List<ProductBean> getProductAvailability(List<ProductBean> products) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        FaiImportConfig config = fai.imp.base.db.SqlQueries.getFaiImportConfig("COMIFAR", null, conn);
        AbstractDataCollector dataCollector =
                new fai.imp.comifar.task.ComifarDataCollector(config, conn);
        return dataCollector.doGetAvailiblityData(products);
    }

    private ProcessedOrdersBean orderProducts(List<ProductBean> products) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        FaiImportConfig config = fai.imp.base.db.SqlQueries.getFaiImportConfig("COMIFAR",  conn);
        AbstractDataCollector dataCollector =
                new fai.imp.comifar.task.ComifarDataCollector(config, conn);
        return dataCollector.doOrderProducts(products);
    }

}
