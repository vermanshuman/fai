package it.fai.be.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.fai.be.controller.mapping.MappingConstants;
import it.fai.be.dto.*;
import it.fai.be.service.SupplierService;
import it.fai.be.utils.DbUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(MappingConstants.SUPPLIER_MAPPING)
public class SupplierController extends AbstractController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    /**
     * Find all suppliers.
     */
    @GetMapping
    @ApiOperation(value = "Find all suppliers", response = SupplierResponseDTO.class)
    public ResponseEntity<SupplierResponseDTO> findAll() {
        Connection conn = null;
        SupplierResponseDTO response = new SupplierResponseDTO();
        try {
            conn = getConnection();
            List<SupplierDTO> suppliers = service.findAll(conn);
            response.setSuppliers(suppliers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }

    @ApiOperation(value = "Configure Supplier Calendar")
    @PostMapping(MappingConstants.SUPPLIER_CALENDAR_MAPPING)
    public ResponseEntity<ResponseDto> configureSupplierCalendar(
            @ApiParam(name = "supplierCalendarDTO") @RequestBody List<SupplierCalendarDTO> supplierCalendarDTO) {
        Connection conn = null;
        ResponseDto response = new ResponseDto();
        try {
            conn = getConnection();
            service.configureCalendar(supplierCalendarDTO, conn);
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
