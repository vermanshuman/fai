package it.fai.be.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.fai.be.controller.mapping.MappingConstants;
import it.fai.be.dto.CSVScheduleDTO;
import it.fai.be.dto.GenericTaskDTO;
import it.fai.be.dto.ResponseDto;
import it.fai.be.service.GenericTaskService;
import it.fai.be.utils.DbUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

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
            return new ResponseEntity<>(csvScheduleDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }

    @PostMapping
    @ApiOperation(value = "Create new schedule", response = GenericTaskDTO.class)
    public ResponseEntity<GenericTaskDTO> save(@ApiParam(name = "genericTaskDTO")
                                        @RequestBody GenericTaskDTO genericTaskDTO) {
        return new ResponseEntity<>(service.create(genericTaskDTO), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Update schedule", response = GenericTaskDTO.class)
    public ResponseEntity<GenericTaskDTO> update(@ApiParam(name = "genericTaskDTO")
                                          @RequestBody GenericTaskDTO genericTaskDTO) {
        Connection conn = null;
        try {
            conn = getConnection();
            return new ResponseEntity<>(service.update(genericTaskDTO, conn), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            GenericTaskDTO response = new GenericTaskDTO();
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }


    @GetMapping(MappingConstants.FIND_BY_ACRONYM)
    @ApiOperation(value = "Find config by acronym")
    public ResponseEntity<GenericTaskDTO> findTaskByAcronym(@ApiParam(name = "acronym") String acronym) {
        Connection conn = null;
        try {
            conn = getConnection();
            return new ResponseEntity<>(service.findTaskByAcronym(acronym, conn), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            GenericTaskDTO response = new GenericTaskDTO();
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }
}
