package it.fai.be.service.impl;

import fai.broker.db.SqlQueries;
import fai.broker.models.Fornitore;
import it.fai.be.dto.SupplierCalendarDTO;
import it.fai.be.dto.SupplierDTO;
import it.fai.be.service.SupplierService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class SupplierServiceImpl implements SupplierService {

    @Override
    public List<SupplierDTO> findAll(Connection conn) throws Exception {
        log.debug("Find All Suppliers");
        List<Fornitore> fornitores = SqlQueries.getAllFornitore(conn);
        return fornitores
                .stream()
                .map(fornitore -> setSupplier(fornitore))
                .collect(Collectors.toList());
    }

    @Override
    public void configureCalendar(List<SupplierCalendarDTO> calendarDTOS, Connection conn) throws Exception {
        log.debug("Configure supplier calendar");

    }

    private SupplierDTO setSupplier(Fornitore fornitore) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setOid(fornitore.getOid());
        supplierDTO.setCodice(fornitore.getCodice());
        return supplierDTO;
    }
}
