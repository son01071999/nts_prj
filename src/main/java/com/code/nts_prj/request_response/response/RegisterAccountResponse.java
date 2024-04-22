package com.code.nts_prj.request_response.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAccountResponse extends BaseResponse {
	private String username;
	private String role;

	@Builder
	public RegisterAccountResponse(int code, String message, String username, String role) {
		super(code, message);
		this.username = username;
		this.role = role;
	}
}
