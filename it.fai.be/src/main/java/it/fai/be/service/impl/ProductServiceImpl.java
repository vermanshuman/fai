package it.fai.be.service.impl;

import fai.broker.db.SqlQueries;
import fai.broker.models.ApprovToRiga;
import fai.broker.models.ApprovvigionamentoFarmaco;
import fai.broker.models.OrdineIn;
import fai.broker.models.OrdineInCollection;
import it.fai.be.dto.OrderDTO;
import it.fai.be.dto.ProductDTO;
import it.fai.be.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDTO> findAll(Connection conn) throws Exception {
        log.debug("Find All products");
        List<ApprovvigionamentoFarmaco> approvvigionamentoFarmacos =
                SqlQueries.getAllProdottiByOrder(null, "ORDER BY ORDINE.OID", conn);
        return approvvigionamentoFarmacos
                .stream()
                .map(approvvigionamentoFarmaco -> setProduct(approvvigionamentoFarmaco,conn))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductsByOrder(Long idOrder, Connection conn) throws Exception {
        log.debug("Find All Products for order");
        if (idOrder != null) {
            List<ApprovvigionamentoFarmaco> approvvigionamentoFarmacos =
                    SqlQueries.getAllProdottiByOrder(idOrder, null, conn);
            return approvvigionamentoFarmacos
                    .stream()
                    .map(approvvigionamentoFarmaco -> setProduct(approvvigionamentoFarmaco, null))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private ProductDTO setProduct(ApprovvigionamentoFarmaco approvvigionamentoFarmaco, Connection conn) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setIdVendita(approvvigionamentoFarmaco.getIdVendita());
        productDTO.setMinsan(approvvigionamentoFarmaco.getCodiceMinSan());
        ApprovToRiga[] approvToRiga = approvvigionamentoFarmaco.getAllApprovRigaDett();
        int qt = 0;
        for (int i = 0; i < approvToRiga.length; i++) {
            qt += approvToRiga[i].getQuantita();
        }
        if(approvvigionamentoFarmaco.getMagazzino() != null){
            productDTO.setWarehouseQuantity(approvvigionamentoFarmaco.getQuantita());
        }else if(approvvigionamentoFarmaco.getFornitore() != null){
            productDTO.setSupplierQuantity(approvvigionamentoFarmaco.getQuantita());
        }else {
            productDTO.setMissingQuantity(approvvigionamentoFarmaco.getQuantita());
        }
        if(approvvigionamentoFarmaco.getIdOrdine() != null && conn != null){
            try {
                List<OrdineInCollection> ordineInCollections =
                        SqlQueries.findOrdineInCollectionByOrder(
                                Long.parseLong(approvvigionamentoFarmaco.getIdOrdine()), conn);
                if(ordineInCollections != null && !ordineInCollections.isEmpty()){
                    productDTO.setCsvFileName(ordineInCollections.get(0).getInputResource());
                }
            } catch (Exception e) {
            }
        }
        productDTO.setTotalQuantity(qt);
        return productDTO;
    }
}
