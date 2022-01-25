package it.fai.be.service;

import it.fai.be.dto.SupplierDTO;

import java.sql.Connection;
import java.util.List;

public interface SupplierService {

    List<SupplierDTO> findAll(Connection conn) throws Exception;
}
