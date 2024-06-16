package com.code.nts_prj.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	private final HandlerExceptionResolver resolver;

	public AuthenticationEntryPointImpl(
			@Qualifier("handlerExceptionResolver") final HandlerExceptionResolver resolver) {
		this.resolver = resolver;
	}

	@Override
	public void commence(
			final HttpServletRequest request,
			final HttpServletResponse response,
			final AuthenticationException authException) {
		this.resolver.resolveException(request, response, null, authException);
	}
}
