package com.code.nts_prj.account.request_response.response;

import com.code.nts_prj.response.BaseResponse;
import lombok.Getter;

@Getter
public class LoginResponse extends BaseResponse {
	private final String jwtToken;

	public LoginResponse(int code, String message, String jwtToken) {
		super(code, message);
		this.jwtToken = jwtToken;
	}
}
