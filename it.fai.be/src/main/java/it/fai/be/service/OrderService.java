package it.fai.be.service;

import it.fai.be.dto.OrderDTO;
import java.sql.Connection;
import java.util.List;

public interface OrderService {

    List<OrderDTO> findAll(Connection conn) throws Exception;

    List<OrderDTO> findOrdersByTask(Long taskOID, Connection conn) throws Exception;
}
