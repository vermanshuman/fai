package it.fai.be.dto;

import fai.common.models.GenericTaskConfig;
import fai.common.util.RichProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericTaskDTO extends ResponseDto{

    private Long oid;
    private String acronym;
    private String scheduleTimes;
    private String scheduledDays;
    private RichProperties richProperties;
}
