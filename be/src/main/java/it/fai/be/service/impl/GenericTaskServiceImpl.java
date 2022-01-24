package it.fai.be.service.impl;

import fai.common.db.SqlQueries;
import fai.common.models.GenericTaskConfig;
import fai.common.util.RichProperties;
import it.fai.be.constant.ValueConstant;
import it.fai.be.dto.CSVFileDTO;
import it.fai.be.dto.CSVScheduleDTO;
import it.fai.be.dto.GenericTaskDTO;
import it.fai.be.service.GenericTaskService;
import it.fai.be.utils.DateUtil;
import it.fai.be.utils.FilesUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class GenericTaskServiceImpl implements GenericTaskService {

    @Override
    public CSVScheduleDTO getByAcronym(String acronym, Connection conn) {
        CSVScheduleDTO csvScheduleDTO = new CSVScheduleDTO();
        try {
            GenericTaskConfig taskConfig = SqlQueries.getGenericTaskConfig(acronym, conn);
            if(taskConfig != null && StringUtils.isNotBlank(taskConfig.getScheduledTimes())){
                List<String> items = Arrays.asList(taskConfig.getScheduledTimes().split(","));
                csvScheduleDTO.setCsvSchedule(getNextScheduleTime(items));
            }
        } catch (Exception e) {
            log.error("Exception in getByAcronym" , e);
        }
        return csvScheduleDTO;
    }

    @Override
    public GenericTaskDTO create(GenericTaskDTO genericTaskDTO) {
        log.debug("Creating Generic Task Config");
        return null;
    }

    @Override
    public GenericTaskDTO update(GenericTaskDTO genericTaskDTO, Connection conn) {
        try {
            SqlQueries.setGenericTaskConfig(ValueConstant.IMPORT_ACRONYM, genericTaskDTO.getScheduledDays(), genericTaskDTO.getScheduleTimes(), conn);

            if(genericTaskDTO.getRichProperties() != null){
                SqlQueries.setGenericTaskConfigProperty(ValueConstant.IMPORT_ACRONYM,
                        ValueConstant.LOGIN_LEY, genericTaskDTO.getRichProperties().getProperty("login"), conn);
                SqlQueries.setGenericTaskConfigProperty(ValueConstant.IMPORT_ACRONYM,
                        ValueConstant.HOST_KEY, genericTaskDTO.getRichProperties().getProperty("host"), conn);
                SqlQueries.setGenericTaskConfigProperty(ValueConstant.IMPORT_ACRONYM,
                        ValueConstant.CSV_FILE_NAME, genericTaskDTO.getRichProperties().getProperty("csvInFileName"), conn);
                SqlQueries.setGenericTaskConfigProperty(ValueConstant.IMPORT_ACRONYM,
                        ValueConstant.PASSWORD_KEY, genericTaskDTO.getRichProperties().getProperty("password"), conn);
                SqlQueries.setGenericTaskConfigProperty(ValueConstant.IMPORT_ACRONYM,
                        ValueConstant.MAGAZZINO_ACRONYM, genericTaskDTO.getRichProperties().getProperty("magazzino_acronym"), conn);
            }
            GenericTaskConfig genericTaskConfig = SqlQueries.getGenericTaskConfig(ValueConstant.IMPORT_ACRONYM, conn);
            RichProperties rp = genericTaskConfig.getRichProperties();
            if(rp != null){

            }

        } catch (Exception e) {
            log.error("Exception in update" , e);
        }
        return genericTaskDTO;
    }

    @Override
    public GenericTaskDTO findTaskByAcronym(String acronym, Connection conn) {
        GenericTaskDTO genericTaskDTO = null;
        try {
            GenericTaskConfig genericTaskConfig = SqlQueries.getGenericTaskConfig(acronym, conn);
            if(genericTaskConfig != null){
                genericTaskDTO = new GenericTaskDTO();;
                genericTaskDTO.setOid(genericTaskConfig.getOid());
                genericTaskDTO.setAcronym(genericTaskConfig.getAcronym());
                genericTaskDTO.setScheduleTimes(genericTaskConfig.getScheduledTimes());
                genericTaskDTO.setScheduledDays(genericTaskConfig.getScheduledSmtwtfs());
                genericTaskDTO.setRichProperties(genericTaskConfig.getRichProperties());
            }
        } catch (Exception e) {
            log.error("Exception in findTaskByAcronym" , e);
        }

        return genericTaskDTO;
    }

    private String getNextScheduleTime(List<String> items){
        String nextScheduleTime = null;
        Optional<Date> schedule = items.stream()
                .filter(item -> StringUtils.isNotBlank(item))
                .map(item -> DateUtil.getCurrentDay(DateUtil.fromString(item,
                        DateUtil.getTimePattern())))
                .filter(date -> date.equals(DateUtil.getNow()) || date.after(DateUtil.getNow()))
                .findFirst();

        if(schedule.isPresent()){
            nextScheduleTime = DateUtil.toFormattedString(schedule.get(), DateUtil.getDatePatternWithMinutes(), null);
        }
        return nextScheduleTime;
    }
}
