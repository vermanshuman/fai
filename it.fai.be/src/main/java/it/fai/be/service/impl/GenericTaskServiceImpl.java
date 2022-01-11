package it.fai.be.service.impl;

import fai.common.db.SqlQueries;
import fai.common.models.GenericTaskConfig;
import it.fai.be.dto.CSVScheduleDTO;
import it.fai.be.service.GenericTaskService;
import it.fai.be.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
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
