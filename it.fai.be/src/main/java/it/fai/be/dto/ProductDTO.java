package it.fai.be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends ResponseDto{

    private Long idVendita;
    private String minsan;
    private String productDescription;
    private Integer totalQuantity;
    private Integer warehouseQuantity;
    private Integer supplierQuantity;
    private Integer fulFilledQuantity;
    private Integer fulFilledPurchaseQuantity;
    private Integer missingQuantity;
    private String  status;
}
