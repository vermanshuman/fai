package it.fai.be.service;

import it.fai.be.dto.OrderDTO;
import it.fai.be.dto.WarehouseDTO;

import java.sql.Connection;
import java.util.List;

public interface WarehouseService {

    List<WarehouseDTO> findAll(Connection conn) throws Exception;
}
