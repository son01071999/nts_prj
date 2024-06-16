package com.code.nts_prj.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
	public int code;
	public String message;

	public BaseResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
