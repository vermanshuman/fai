package fai.broker.supplier.driver.farmaclick;

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

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FarmaclickSupplierService extends AbstractSupplierService {

    static Logger logger = Logger.getLogger(FarmaclickSupplierService.class);

    private FaiImportConfig importConfig;
    
    @Override
    public RequestMode getRequestMode() throws Exception {
        return RequestMode.MoreProductOneRequest;
        //return RequestMode.OneProductOneRequest;
    }

    @Override
    protected void setupMagazzino() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected void setupFornitore() throws Exception {
    	if(fornitore != null){
            try {
                List<fai.imp.farmaclick.models.Fornitore> fornitores
                        = SqlQueries.findAllFornitoreByCondition("WHERE CODICE = '" + fornitore.getCodice() + "'" , conn);
                if(fornitores != null && !fornitores.isEmpty()){
                    List<FaiImportConfig> faiImportConfigs =
                            fai.imp.base.db.SqlQueries.findFaiImportConfig(null, fornitores.get(0).getOidConfig(), conn);
                    if(faiImportConfigs != null && faiImportConfigs.size() > 0){
                        setImportConfig(faiImportConfigs.get(0));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("Error in fetching fortinore");
            }
        }


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
        ordineOut.setRequestXml(processedOrdersBean.getRequestXML());
        ordineOut.setResponseXml(processedOrdersBean.getResponseXMl());
        ordineOut.setStatus(ItemStatus.VALUE_PROCESSED);
        if(approvvigionamento != null && approvvigionamento.size() > 0)
            ordineOut.setFornitore(approvvigionamento.get(0).getFornitore());
        if(processedOrders != null && !processedOrders.isEmpty()){
            ordineOut.setIdOrdine(processedOrders.get(0).getNumeroOrdineFornitore());
            ordineOut.setIdRicevuta(processedOrders.get(0).getIdVendita());
        }

        ordineOut.setFornitore(approvvigionamento != null && approvvigionamento.size() > 0 ? approvvigionamento.get(0).getFornitore() : null);
        ordineOut = SqlQueries.insertOrdineOut(ordineOut, conn);


        for (ApprovvigionamentoFarmaco appr : approvvigionamento) {
            fai.common.db.SqlQueries.logInfo("inoltro richiesta d'ordine a " + asShortDescr() + " ...",
                    appr.toXml(), null, conn);
            conn.commit();

            final String codiceMinsan = appr.getCodiceMinSan();
            Optional<ProcessedOrderBean> matchedProduct = processedOrders
                    .stream()
                    .filter(p ->
                    (StringUtils.isNotBlank(p.getSusbituteMinsan())
                    && !p.getSusbituteMinsan().equalsIgnoreCase("0") && p.getSusbituteMinsan().equals(codiceMinsan)) ||
                            ((StringUtils.isBlank(p.getSusbituteMinsan()) || p.getSusbituteMinsan().equalsIgnoreCase("0") &&
                                    p.getProductCode().equals(codiceMinsan))))
                    .findFirst();

            if (matchedProduct.isPresent()) {
                if (matchedProduct.get().getMissingQuantity() != null &&
                        matchedProduct.get().getMissingQuantity() > 0) {

                    if(matchedProduct.get().getMissingQuantity().equals(appr.getQuantita())){
                        SqlQueries.updateApprovvigionamentoFarmacoMissing(appr, false, conn);
                    }else {
                        ApprovvigionamentoFarmaco approvvigionamentoFarmaco
                                = SqlQueries.checkMissingApprovvigionamentoFarmaco(codiceMinsan, conn);
                        if(approvvigionamentoFarmaco == null){
                            approvvigionamentoFarmaco = new ApprovvigionamentoFarmaco();
                            approvvigionamentoFarmaco.setQuantita(matchedProduct.get().getMissingQuantity());
                            approvvigionamentoFarmaco.setStatus(StatusInfo.newToProcessInstance(null, null));
                            approvvigionamentoFarmaco.setMagazzinoAcronym(appr.getMagazzinoAcronym());
                            approvvigionamentoFarmaco.setCodiceMinSan(appr.getCodiceMinSan());
                            Long approvOID = SqlQueries.insertApprovvigionamentoFarmaco(approvvigionamentoFarmaco, conn);

                            List<OrdineInRigaDett> ordineInRigaDettList =
                                    SqlQueries.findOrdineInRigaDettaglioByCondition(
                                            approvvigionamentoFarmaco.getCodiceMinSan(), conn);
                            if (ordineInRigaDettList != null && ordineInRigaDettList.size() > 0) {
                                List<ApprovToRiga> approvToRigas =
                                        SqlQueries.findOrdineInRigaByCondition(" WHERE OID_APPROVFARMACO=" + appr.getOid()
                                                + " AND OID_ORDINEINRIGA=" + ordineInRigaDettList.get(0).getOid(), conn);

                                ApprovToRiga approvToRiga = approvToRigas.get(0);
                                Integer currentQuantity = approvToRiga.getQuantita();
                                if(matchedProduct.get().getMissingQuantity() > currentQuantity){
                                    approvToRiga.setQuantita(currentQuantity-matchedProduct.get().getMissingQuantity());
                                    SqlQueries.updateOrdineInRigaQuantityByCondition(approvToRiga,appr.getOid(), conn);
                                    approvToRiga = new ApprovToRiga();
                                    approvToRiga.setQuantita(matchedProduct.get().getMissingQuantity());
                                    approvToRiga.setRigaDett(ordineInRigaDettList.get(0));
                                    SqlQueries.insertApprovToRiga(approvOID, approvToRiga, conn);
                                }else {
                                    SqlQueries.updateOrdineInRigaQuantityByCondition(approvToRiga,approvOID, conn);
                                }
                            }
                        }
                        else {
                            approvvigionamentoFarmaco.setFornitore(appr.getFornitore());
                            Integer updatedQuantity = approvvigionamentoFarmaco.getQuantita() + matchedProduct.get().getMissingQuantity();
                            approvvigionamentoFarmaco.setQuantita(updatedQuantity);
                            approvvigionamentoFarmaco.setCodiceEan(matchedProduct.get().getProductCode());
                            approvvigionamentoFarmaco.setCodiceMinSan(matchedProduct.get().getProductCode());
                            SqlQueries.updateApprovvigionamentoFarmaco(approvvigionamentoFarmaco, false, conn);
                            SqlQueries.updateApprovToRigaByApprovvigionamentoFarmaco(
                                    approvvigionamentoFarmaco.getOid(), approvvigionamentoFarmaco.getQuantita(), conn);
                        }
                        Integer updatedQuantity = appr.getQuantita() - matchedProduct.get().getMissingQuantity();
                        appr.setQuantita(updatedQuantity);
                        SqlQueries.updateApprovvigionamentoFarmacoOrdine(appr, conn);
                    }
                }

                if (matchedProduct.get().getOrderedQuantity() != null &&
                        matchedProduct.get().getOrderedQuantity() > 0) {
                    appr.setQuantita(matchedProduct.get().getOrderedQuantity());
                    appr.setStatus(StatusInfo.newProcessedInstance(null, null));
                    appr.setDisponibilitaConfermataReq(Calendar.getInstance());
                    if(StringUtils.isNotBlank(matchedProduct.get().getSusbituteMinsan()) &&
                            !matchedProduct.get().getSusbituteMinsan().equalsIgnoreCase("0"))
                        appr.setCodiceSostituitoMinsan(matchedProduct.get().getProductCode());
                    appr.setOrdineOut(ordineOut);
                    SqlQueries.updateApprovvigionamentoFarmacoOrdine(appr, conn);
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

    private List<ProductBean> getProductAvailability(List<ProductBean> products) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        AbstractDataCollector dataCollector =
                new fai.imp.farmaclick.task.FarmaclickDataCollector(this.importConfig, conn);
        return dataCollector.doGetAvailiblityData(products);
    }

    private ProcessedOrdersBean orderProducts(List<ProductBean> products) throws Exception {
        final String METH_NAME = new Object() {
        }.getClass().getEnclosingMethod().getName();
        final String LOG_PREFIX = METH_NAME + "(" + asShortDescr() + ")" + ": ";
        logger.info(LOG_PREFIX + "...");
        AbstractDataCollector dataCollector =
                new fai.imp.farmaclick.task.FarmaclickDataCollector(this.importConfig, conn);
        return dataCollector.doOrderProducts(products);
    }
    
    public void setImportConfig(FaiImportConfig importConfig) {
        this.importConfig = importConfig;
    }
}
