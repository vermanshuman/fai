package it.fai.be.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.fai.be.controller.mapping.MappingConstants;
import it.fai.be.dto.CSVFileDTO;
import it.fai.be.dto.UploadTaskDTO;
import it.fai.be.dto.UploadTasksDTO;
import it.fai.be.service.UploadTaskService;
import it.fai.be.utils.DbUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping(MappingConstants.UPLOAD_MAPPING)
public class UploadTaskController extends AbstractController {

    private final UploadTaskService service;

    public UploadTaskController(UploadTaskService service) {
        this.service = service;
    }

    @PutMapping(MappingConstants.UPLOAD_GENERIC_TASK_CSV)
    @ApiOperation(value = "Update CSV File", response = CSVFileDTO.class)
    public ResponseEntity<CSVFileDTO> uploadCSV(@ApiParam(name = "csvFileDTO") @RequestBody CSVFileDTO csvFileDTO) {
        Connection conn = null;
        try {
            conn = getConnection();
            return new ResponseEntity<>(service.uploadCSV(csvFileDTO, conn), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            CSVFileDTO response = new CSVFileDTO();
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }

    /**
     * Find all manual csv tasks.
     * @param startDate
     * @param endDate
     */
    @GetMapping
    @ApiOperation(value = "Find all manual tasks", response = UploadTaskDTO.class, responseContainer = "List")
    public ResponseEntity<UploadTasksDTO> findAll(@ApiParam(name = "dd-MM-yyyy startDate") String startDate,
                                                  @ApiParam(name = "dd-MM-yyyy endDate") String endDate) {
        Connection conn = null;
        UploadTasksDTO uploadTasksDTO = new UploadTasksDTO();
        try {
            conn = getConnection();
            List<UploadTaskDTO> uploadTasks = service.findAll(startDate, endDate, conn);
            uploadTasksDTO.setUploadTasks(uploadTasks);
            return new ResponseEntity<>(uploadTasksDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            uploadTasksDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(uploadTasksDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }

    }
    @GetMapping(MappingConstants.EXECUTE_IMPORT_TASK)
    @ApiOperation(value = "import Ordini CSV", response = UploadTaskDTO.class)
    public ResponseEntity<UploadTaskDTO> executeImportTask(@PathVariable Long taskOID) {
        Connection conn = null;
        try {
            conn = getConnection();
            return new ResponseEntity<>(service.executeImportTask(taskOID, conn), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            UploadTaskDTO uploadTaskDTO = new UploadTaskDTO();
            uploadTaskDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(uploadTaskDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            DbUtils.closeSilent(conn);
        }
    }
}
