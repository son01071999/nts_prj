package com.code.nts_prj.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	private final AccountDetailsService accountDetailsService;

	private final AuthenticationEntryPoint authenticationEntryPoint;

	private final JwtUtil jwtUtil;

	public SecurityConfig(
			final AccountDetailsService accountDetailsService,
			final AuthenticationEntryPoint authenticationEntryPoint,
			JwtUtil jwtUtil) {
		this.accountDetailsService = accountDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.jwtUtil = jwtUtil;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.logout(Customizer.withDefaults());
		httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.addFilterBefore(
						new JwtRequestFilter(accountDetailsService, authenticationEntryPoint, jwtUtil),
						BasicAuthenticationFilter.class)
				.authorizeHttpRequests(
						authorizeHttpRequests ->
								authorizeHttpRequests
										.requestMatchers("/account/user/**")
										.permitAll()
										.requestMatchers("/account/admin/**")
										.hasRole("ADMIN")
										.anyRequest()
										.authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(
						httpSecuritySessionManagementConfigurer ->
								httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
										SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	@AliasFor("bCryptPasswordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
