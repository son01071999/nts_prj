package com.code.nts_prj.auth.exceptions;

import com.code.nts_prj.exception.BaseExceptionResponse;
import com.code.nts_prj.exception.ErrorConst;

public class InvalidLoginRequestExceptionResponse extends BaseExceptionResponse {
	public InvalidLoginRequestExceptionResponse() {
		super(
				ErrorConst.INVALID_LOGIN_REQUEST_ERROR_CODE,
				ErrorConst.INVALID_LOGIN_REQUEST_ERROR_MESSAGE);
	}
}
