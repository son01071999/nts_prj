package com.code.nts_prj.auth;

import com.code.nts_prj.auth.exceptions.TokenIsExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	private final AccountDetailsService accountDetailsService;

	private final AuthenticationEntryPoint authenticationEntryPoint;

	public JwtRequestFilter(
			AccountDetailsService accountDetailsService,
			AuthenticationEntryPoint authenticationEntryPoint) {
		this.accountDetailsService = accountDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			@NotNull HttpServletResponse response,
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {
		final String token = request.getHeader(JwtUtil.AUTHORIZATION);
		if (StringUtils.hasText(token) && token.startsWith(JwtUtil.PREFIX)) {
			String jwt = JwtUtil.parseToken(token);
			try {
				String userName = JwtUtil.extractUserName(jwt);
				if (StringUtils.hasText(userName)
						&& Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
					UserDetails userDetails = accountDetailsService.loadUserByUsername(userName);
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
							new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} catch (ExpiredJwtException e) {
				this.authenticationEntryPoint.commence(
						request, response, new TokenIsExpiredException(token));
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
