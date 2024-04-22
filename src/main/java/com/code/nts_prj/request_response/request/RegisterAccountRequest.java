package com.code.nts_prj.request_response.request;

import com.code.nts_prj.validation.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RegisterAccountRequest {
	private String userName;
	private String password;
	private String role;
	@PhoneNumber private String phoneNumber;
	private String name;
	private Integer age;
}
