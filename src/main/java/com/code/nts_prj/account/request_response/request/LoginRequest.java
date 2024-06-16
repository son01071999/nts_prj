package com.code.nts_prj.account.request_response.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {
	private String userName;
	private String password;
}
