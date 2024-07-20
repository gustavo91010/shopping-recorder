package com.ajudaqui.recalldecompras.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import feign.FeignException;

public class ApiException {
	Logger logger = LoggerFactory.getLogger(ApiException.class);

	private String timestamp;
	private Integer status;
	private String error;
	private String message;

	public ResponseEntity<?> response(Throwable exception, HttpStatus status) {
		return new ResponseEntity<>(new ApiException(exception, status), status);
	}

	public ResponseEntity<?> response(FeignException exception, HttpStatus status) {
		return new ResponseEntity<>(new ApiException(exception, status), status);
	}

	public ResponseEntity<?> response(String message, HttpStatus status) {
		return new ResponseEntity<>(new ApiException(message, status), status);
	}

	public ApiException() {
	}

	public ApiException(Throwable exception, HttpStatus status) {
		this.timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).toString();
		this.status = status.value();
		this.error = status.toString();
		this.message = exception.getMessage();
	}

	public ApiException(FeignException exception, HttpStatus status) {
		this.timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).toString();
		this.status = status.value();
		this.error = status.toString();
		this.message = exception.contentUTF8();
	}

	public ApiException(String message, HttpStatus status) {
		this.timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).toString();
		this.status = status.value();
		this.error = status.toString();
		this.message = message;

	}

	@Override
	public String toString() {
		return "ApiException [logger=" + logger + ", timestamp=" + timestamp + ", status=" + status + ", error=" + error
				+ ", message=" + message + "]";
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
