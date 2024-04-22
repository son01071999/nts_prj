package com.code.nts_prj.exception.account.runtime;

import com.code.nts_prj.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequestException extends BaseException {
	private String code;
	private String message;
}
