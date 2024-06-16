package com.code.nts_prj.auth.exceptions;

import com.code.nts_prj.exception.BaseExceptionResponse;
import com.code.nts_prj.exception.ErrorConst;
import lombok.Getter;

@Getter
public class TokenIsExpiredExceptionResponse extends BaseExceptionResponse {
	private final String expiredToken;

	public TokenIsExpiredExceptionResponse(String expiredToken) {
		super(ErrorConst.TOKEN_IS_EXPIRED_ERROR_CODE, ErrorConst.TOKEN_IS_EXPIRED_ERROR_MESSAGE);
		this.expiredToken = expiredToken;
	}
}
