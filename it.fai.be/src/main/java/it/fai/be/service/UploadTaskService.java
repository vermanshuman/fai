package it.fai.be.service;

import it.fai.be.dto.CSVFileDTO;
import it.fai.be.dto.UploadTaskDTO;

import java.sql.Connection;
import java.util.List;

public interface UploadTaskService {

    CSVFileDTO uploadCSV(CSVFileDTO csvFileDTO, Connection conn) throws Exception;

    List<UploadTaskDTO> findAll(String startDate, String endDate);
}
