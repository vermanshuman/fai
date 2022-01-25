package it.fai.be.service.impl;

import fai.broker.db.SqlQueries;
import fai.broker.models.Magazzino;
import it.fai.be.constant.ValueConstant;
import it.fai.be.dto.WarehouseDTO;
import it.fai.be.service.WarehouseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Override
    public List<WarehouseDTO> findAll(Connection conn) throws Exception {
        log.debug("Find All Warehouses");
        List<Magazzino> magazzini = SqlQueries.getAllMagazzino(ValueConstant.MAGAZZINO_CONTEXT, conn);
        return magazzini
                .stream()
                .map(warehouse -> setWarehouse(warehouse))
                .collect(Collectors.toList());
    }

    private WarehouseDTO setWarehouse(Magazzino magazzino) {
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setOid(magazzino.getOid());
        warehouseDTO.setAcronym(magazzino.getAcronym());
        warehouseDTO.setDescription(magazzino.getDescr());
        warehouseDTO.setOrganisationCode(magazzino.getOrganizationCode());
        return warehouseDTO;
    }
}
