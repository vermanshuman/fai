package it.fai.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDTO extends ResponseDto{

    private Long idVendita;
    private String userName;
    private String email;
    private Integer numberOfLines;
    private Integer fulfilledCount;
    private Integer missingCount;
    private Double amount;
    private String  status;
}
