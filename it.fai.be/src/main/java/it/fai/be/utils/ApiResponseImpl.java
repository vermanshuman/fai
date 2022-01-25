package it.fai.be.utils;

import org.springframework.stereotype.Component;

import it.fai.be.dto.ResponseDto;

@Component
public class ApiResponseImpl implements ApiResponse {
    @Override
    public <T> ResponseDto<?> response(String message, boolean success, T response) {
        return ResponseDto.<T>builder()
                .response(response)
                .message(message)
                .success(success)
                .build();
    }
}
