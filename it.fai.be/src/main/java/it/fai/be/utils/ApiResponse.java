package it.fai.be.utils;

import it.fai.be.dto.ResponseDto;


public interface ApiResponse {
    @SuppressWarnings("rawtypes")
	public <T> ResponseDto response(String message, boolean success, T response);
}
