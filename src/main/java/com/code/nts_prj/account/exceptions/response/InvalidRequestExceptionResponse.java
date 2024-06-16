package com.code.nts_prj.account.exceptions.response;

import com.code.nts_prj.exception.BaseExceptionResponse;
import com.code.nts_prj.exception.ErrorConst;
import lombok.Getter;

@Getter
public class InvalidRequestExceptionResponse extends BaseExceptionResponse {
	public InvalidRequestExceptionResponse() {
		super(ErrorConst.REQUEST_INVALID_ERROR_CODE, ErrorConst.REQUEST_INVALID_ERROR_MESSAGE);
	}
}
