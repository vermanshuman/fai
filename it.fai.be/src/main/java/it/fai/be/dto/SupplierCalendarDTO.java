package it.fai.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SupplierCalendarDTO {
    private Long oid;
    private Long oidSupplier;
    private List<Integer> dayOfWeek;
    private String hourStart;
    private String hourEnd;


}
