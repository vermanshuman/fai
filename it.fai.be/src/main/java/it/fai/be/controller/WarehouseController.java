package it.fai.be.controller;

import io.swagger.annotations.ApiOperation;
import it.fai.be.controller.mapping.MappingConstants;
import it.fai.be.dto.OrderDTO;
import it.fai.be.dto.WarehouseDTO;
import it.fai.be.dto.WarehousesResponseDTO;
import it.fai.be.service.WarehouseService;
import it.fai.be.utils.DbUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(MappingConstants.WAREHOUSE_MAPPING)
public class WarehouseController extends AbstractController {

    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    /**
     * Find all magazzino.
     */
    @GetMapping
    @ApiOperation(value = "Find all warehouses", response = OrderDTO.class)
    public ResponseEntity<WarehousesResponseDTO> findAll() {
        Connection conn = null;
        WarehousesResponseDTO response = new WarehousesResponseDTO();
        try {
            conn = getConnection();
            List<WarehouseDTO> warehouses = service.findAll(conn);
            response.setWarehouses(warehouses);
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
