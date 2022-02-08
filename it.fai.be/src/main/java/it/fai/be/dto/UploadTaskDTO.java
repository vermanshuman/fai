package it.fai.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UploadTaskDTO extends ResponseDto{

    private Long oid;
    private Date creationDate;
    private String csvFileName;
    private Integer orderCount;
    private Integer fulFilledOrderCount;
    private Integer processedOrderCount;
    private Integer missingOrderCount;
    private String orderStatus;
    private String orderLinesFulfilled;
    private String orderLinesProcessed;
    private String orderLinesMissed;
    private String executionStatus;
    private String executionStatusDescription;
    private String magazzinoAcronym;
    private Boolean buttonDisabled = Boolean.FALSE;
}
