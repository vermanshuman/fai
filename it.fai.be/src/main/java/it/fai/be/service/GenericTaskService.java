package it.fai.be.service;

import it.fai.be.dto.CSVScheduleDTO;
import it.fai.be.dto.GenericTaskDTO;

import java.sql.Connection;

public interface GenericTaskService {

    CSVScheduleDTO getByAcronym(String acronym, Connection conn);

    GenericTaskDTO create(GenericTaskDTO genericTaskDTO);

    GenericTaskDTO update(GenericTaskDTO genericTaskDTO, Connection conn);

    GenericTaskDTO findTaskByAcronym(String acronym, Connection conn);
}
