package it.fai.be.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {
    private String message;
    private Integer errorCode;
    private String errorDescription;
    boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;
}
