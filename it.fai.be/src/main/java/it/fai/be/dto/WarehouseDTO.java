package it.fai.be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDTO extends ResponseDto{

    private Long oid;
    private String acronym;
    private String description;
    private String organisationCode;
}
