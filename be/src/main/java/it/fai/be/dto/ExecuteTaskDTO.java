package it.fai.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExecuteTaskDTO {

    private Long oid;
    private Date creationDate;
    private String csvFileName;
    private Integer orderCount;
    private String orderStatus;
    private String orderLinesFulfilled;
    private String orderLinesProcessed;
    private String orderLinesMissed;
    private String executionStatus;
}
