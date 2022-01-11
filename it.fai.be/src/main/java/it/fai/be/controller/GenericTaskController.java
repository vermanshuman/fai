package it.fai.be.controller;

import fai.broker.db.SqlQueries;
import fai.broker.models.Magazzino;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.fai.be.constant.ValueConstant;
import it.fai.be.controller.mapping.MappingConstants;
import it.fai.be.dto.CSVScheduleDTO;
import it.fai.be.dto.MagazzinoDto;
import it.fai.be.dto.ResponseDto;
import it.fai.be.service.GenericTaskService;
import it.fai.be.utils.DbUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping(MappingConstants.CONFIG_MAPPING)
public class GenericTaskController extends AbstractController{

    private final GenericTaskService service;

    @Autowired
    public GenericTaskController(GenericTaskService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Find Next CSV Import schedule", response = String.class)
    public ResponseEntity<CSVScheduleDTO> findNextCSVSchedule(@ApiParam(name = "acronym") String acronym) {

        Connection conn = null;
        try {
            conn = getConnection();
            return new ResponseEntity<>(service.getByAcronym(acronym, conn), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            CSVScheduleDTO csvScheduleDTO = new CSVScheduleDTO();
            csvScheduleDTO.setMessage(e.getMessage());
            return  new ResponseEntity<>(csvScheduleDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }


    }
}
