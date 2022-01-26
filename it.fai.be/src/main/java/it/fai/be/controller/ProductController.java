package it.fai.be.controller;

import io.swagger.annotations.ApiOperation;
import it.fai.be.controller.mapping.MappingConstants;
import it.fai.be.dto.ProductDTO;
import it.fai.be.dto.ProductResponseDTO;
import it.fai.be.service.ProductService;
import it.fai.be.utils.DbUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(MappingConstants.PRODUCT_MAPPING)
public class ProductController extends AbstractController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Find all products.
     */
    @GetMapping
    @ApiOperation(value = "Find all products", response = ProductResponseDTO.class)
    public ResponseEntity<ProductResponseDTO> findAll() {
        Connection conn = null;
        ProductResponseDTO response = new ProductResponseDTO();
        try {
            conn = getConnection();
            List<ProductDTO> products = service.findAll(conn);
            response.setProducts(products);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }

    /**
     * Find all products for order.
     */
    @GetMapping(MappingConstants.PRODUCT_ORDER_MAPPING)
    @ApiOperation(value = "Find all products for order", response = ProductResponseDTO.class)
    public ResponseEntity<ProductResponseDTO> findProductsByOrder(@PathVariable Long orderId) {
        Connection conn = null;
        ProductResponseDTO response = new ProductResponseDTO();
        try {
            conn = getConnection();
            List<ProductDTO> products = service.findProductsByOrder(orderId, conn);
            response.setProducts(products);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }
}
