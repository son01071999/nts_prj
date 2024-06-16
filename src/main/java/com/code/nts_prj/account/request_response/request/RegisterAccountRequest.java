package com.code.nts_prj.account.request_response.request;

import com.code.nts_prj.validation.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RegisterAccountRequest {
	@JsonProperty("user_name")
	private String userName;

	private String password;
	private String role;

	@JsonProperty("phone_number")
	@PhoneNumber
	private String phoneNumber;

	private String name;
	private Integer age;
}
