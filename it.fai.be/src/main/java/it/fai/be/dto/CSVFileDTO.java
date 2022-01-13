package it.fai.be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CSVFileDTO extends ResponseDto{
    private String csvFileContent;
    private String csvFileName;
    private String magazzinoAcronym;
}
