package com.code.nts_prj.auth;

import com.code.nts_prj.exception.ErrorConst;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyFilter extends HttpFilter {
	private final ApiKeyConfig apiKeyConfig;

	public ApiKeyFilter(ApiKeyConfig apiKeyConfig) {
		this.apiKeyConfig = apiKeyConfig;
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String apiKey = request.getHeader("API-KEY");
		if(apiKey != null) {
			if (apiKeyConfig.getApiKey().equals(apiKey)) {
				chain.doFilter(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write(ErrorConst.INVALID_API_KEY_ERROR_MESSAGE);
			}
		}else{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(ErrorConst.NOT_FOUND_API_KEY_ERROR_MESSAGE);
		}
	}
}
