package com.code.nts_prj.auth.exceptions;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class TokenIsExpiredException extends AuthenticationException {
	private final String expiredToken;

	public TokenIsExpiredException(String expiredToken) {
		super(expiredToken);
		this.expiredToken = expiredToken;
	}
}
