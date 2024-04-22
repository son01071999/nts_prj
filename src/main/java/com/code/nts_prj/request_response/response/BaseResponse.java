package com.code.nts_prj.request_response.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
	public int code;
	public String message;

//	@Builder
	public BaseResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
