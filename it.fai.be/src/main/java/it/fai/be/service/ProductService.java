package it.fai.be.service;

import it.fai.be.dto.ProductDTO;

import java.sql.Connection;
import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll(Connection conn) throws Exception;

    List<ProductDTO> findProductsByOrder(Long orderId, Connection conn) throws Exception;
}
