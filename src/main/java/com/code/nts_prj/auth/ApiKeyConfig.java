package com.code.nts_prj.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyConfig {
	@Value("${api.key}")
	private String apiKey;

	public String getApiKey() {
		return this.apiKey;
	}
}
