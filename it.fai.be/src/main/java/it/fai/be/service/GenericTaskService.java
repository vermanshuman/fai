package it.fai.be.service;

import it.fai.be.dto.CSVScheduleDTO;
import it.fai.be.dto.GenericTaskDTO;

import java.sql.Connection;

public interface GenericTaskService {

    CSVScheduleDTO getByAcronym(String acronym, Connection conn);
}
