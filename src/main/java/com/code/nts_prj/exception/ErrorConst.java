package com.code.nts_prj.exception;

public interface ErrorConst {
	long UNKNOWN_EXCEPTION_ERROR_CODE = 100000L;
	String UNKNOWN_EXCEPTION_ERROR_MESSAGE = "Anything is wrong in runtime.";
	long REQUEST_INVALID_ERROR_CODE = 100001L;
	String REQUEST_INVALID_ERROR_MESSAGE = "Request is invalid, please check it again!";

	long USERNAME_EXISTS_ERROR_CODE = 100002L;
	String USERNAME_EXISTS_ERROR_MESSAGE = "Username exists";

	long TOKEN_IS_EXPIRED_ERROR_CODE = 100003L;
	String TOKEN_IS_EXPIRED_ERROR_MESSAGE = "Token is expired";

	long INVALID_LOGIN_REQUEST_ERROR_CODE = 100004L;
	String INVALID_LOGIN_REQUEST_ERROR_MESSAGE = "Username or password is wrong.";

	long USERNAME_NOT_FOUND_ERROR_CODE = 100005L;
	String USERNAME_NOT_FOUND_ERROR_MESSAGE = "Username not found";

	String NOT_FOUND_API_KEY_ERROR_MESSAGE = "API key is not found";
	String INVALID_API_KEY_ERROR_MESSAGE = "API key is invalid";
}
