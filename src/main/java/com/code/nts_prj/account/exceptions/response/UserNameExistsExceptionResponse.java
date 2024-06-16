package com.code.nts_prj.account.exceptions.response;

import com.code.nts_prj.exception.BaseExceptionResponse;
import com.code.nts_prj.exception.ErrorConst;

public class UserNameExistsExceptionResponse extends BaseExceptionResponse {

	public UserNameExistsExceptionResponse() {
		super(ErrorConst.USERNAME_EXISTS_ERROR_CODE, ErrorConst.USERNAME_EXISTS_ERROR_MESSAGE);
	}
}
