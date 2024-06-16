package com.code.nts_prj.account.exceptions.response;

import com.code.nts_prj.exception.BaseExceptionResponse;
import com.code.nts_prj.exception.ErrorConst;

public class UserNameNotFoundExceptionResponse extends BaseExceptionResponse {
	public UserNameNotFoundExceptionResponse() {
		super(ErrorConst.USERNAME_NOT_FOUND_ERROR_CODE, ErrorConst.USERNAME_NOT_FOUND_ERROR_MESSAGE);
	}
}
