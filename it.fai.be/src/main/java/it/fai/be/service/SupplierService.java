package it.fai.be.service;

import it.fai.be.dto.SupplierCalendarDTO;
import it.fai.be.dto.SupplierDTO;

import java.sql.Connection;
import java.util.List;

public interface SupplierService {

    List<SupplierDTO> findAll(Connection conn) throws Exception;

    void configureCalendar(List<SupplierCalendarDTO> calendarDTOS, Connection conn) throws Exception;
}
