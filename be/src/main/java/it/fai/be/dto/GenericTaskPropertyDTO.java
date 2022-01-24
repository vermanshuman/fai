package it.fai.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericTaskPropertyDTO {

    private Long oid;
    private String key;
    private String value;
}
