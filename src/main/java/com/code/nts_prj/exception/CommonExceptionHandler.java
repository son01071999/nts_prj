package com.code.nts_prj.exception;

import com.code.nts_prj.exception.account.response.RegisterRequestExceptionResponse;
import com.code.nts_prj.exception.account.runtime.RegisterRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order
@RestControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(RegisterRequestException.class)
	public ResponseEntity<RegisterRequestExceptionResponse> handleRegisterRequestExceptionResponse(
			final RegisterRequestException exception) {
		log.error("RegisterRequestException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(
						new RegisterRequestExceptionResponse(
								exception.getCode(), HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
	}
}
