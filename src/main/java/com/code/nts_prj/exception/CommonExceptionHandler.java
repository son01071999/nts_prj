package com.code.nts_prj.exception;

import com.code.nts_prj.account.exceptions.response.InvalidRequestExceptionResponse;
import com.code.nts_prj.account.exceptions.response.UserNameExistsExceptionResponse;
import com.code.nts_prj.account.exceptions.response.UserNameNotFoundExceptionResponse;
import com.code.nts_prj.account.exceptions.runtime.InvalidRequestException;
import com.code.nts_prj.account.exceptions.runtime.UserNameExistsException;
import com.code.nts_prj.account.exceptions.runtime.UserNameNotFoundException;
import com.code.nts_prj.auth.exceptions.InvalidLoginRequestException;
import com.code.nts_prj.auth.exceptions.InvalidLoginRequestExceptionResponse;
import com.code.nts_prj.auth.exceptions.TokenIsExpiredException;
import com.code.nts_prj.auth.exceptions.TokenIsExpiredExceptionResponse;
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

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<BaseExceptionResponse> handleRegisterRequestExceptionResponse(
			final BaseException exception) {
		log.error("BaseException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(
						new BaseExceptionResponse(
								ErrorConst.UNKNOWN_EXCEPTION_ERROR_CODE,
								ErrorConst.UNKNOWN_EXCEPTION_ERROR_MESSAGE));
	}

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<InvalidRequestExceptionResponse> handleRegisterRequestExceptionResponse(
			final InvalidRequestException exception) {
		log.error("InvalidRequestException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new InvalidRequestExceptionResponse());
	}

	@ExceptionHandler(UserNameExistsException.class)
	public ResponseEntity<UserNameExistsExceptionResponse> handleUserNameExistsExceptionResponse(
			final UserNameExistsException exception) {
		log.error("UserNameExistsException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new UserNameExistsExceptionResponse());
	}

	@ExceptionHandler(TokenIsExpiredException.class)
	public ResponseEntity<TokenIsExpiredExceptionResponse> handleUserNameExistsExceptionResponse(
			final TokenIsExpiredException exception) {
		log.error("TokenIsExpiredException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new TokenIsExpiredExceptionResponse(exception.getExpiredToken()));
	}

	@ExceptionHandler(InvalidLoginRequestException.class)
	public ResponseEntity<InvalidLoginRequestExceptionResponse> handleUserNameExistsExceptionResponse(
			final InvalidLoginRequestException exception) {
		log.error("InvalidLoginRequestException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new InvalidLoginRequestExceptionResponse());
	}

	@ExceptionHandler(UserNameNotFoundException.class)
	public ResponseEntity<UserNameNotFoundExceptionResponse> handleUserNameExistsExceptionResponse(
			final UserNameNotFoundException exception) {
		log.error("UserNameNotFoundException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new UserNameNotFoundExceptionResponse());
	}
}
