package com.code.nts_prj.exception.account.response;

import com.code.nts_prj.exception.BaseExceptionResponse;
import lombok.Getter;

@Getter
public class RegisterRequestExceptionResponse extends BaseExceptionResponse {

	private final String id;

	public RegisterRequestExceptionResponse(String id, int status, String message) {
		super(status, message);
		this.id = id;
	}
}
