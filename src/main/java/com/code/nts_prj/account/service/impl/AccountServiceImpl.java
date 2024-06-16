package com.code.nts_prj.account.service.impl;

import com.code.nts_prj.account.converter.AccountConverter;
import com.code.nts_prj.account.entity.AccountEntity;
import com.code.nts_prj.account.exceptions.runtime.InvalidRequestException;
import com.code.nts_prj.account.exceptions.runtime.UserNameExistsException;
import com.code.nts_prj.account.exceptions.runtime.UserNameNotFoundException;
import com.code.nts_prj.account.request_response.request.LoginRequest;
import com.code.nts_prj.account.request_response.request.RegisterAccountRequest;
import com.code.nts_prj.account.request_response.response.LoginResponse;
import com.code.nts_prj.account.request_response.response.RegisterAccountResponse;
import com.code.nts_prj.account.service.AccountService;
import com.code.nts_prj.auth.JwtUtil;
import com.code.nts_prj.auth.exceptions.InvalidLoginRequestException;
import com.code.nts_prj.repository.AccountRepo;
import com.code.nts_prj.validation.RequestObjectValidation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
	private final AccountRepo accountRepo;
	private final AuthenticationManager authenticationManager;
	private final String SUCCESS = "SUCCESS";

	@Override
	public List<AccountEntity> getAll() {
		return accountRepo.findAll();
	}

	@Override
	public RegisterAccountResponse registerAccount(RegisterAccountRequest request) {
		AccountEntity accountEntity;

		// Check all fields in request are not null
		try {
			if (!RequestObjectValidation.checkAllFieldIsNotNull(request)) {
				throw new InvalidRequestException();
			}

			// check if findByUserName is Empty -> save to DB
			accountRepo
					.findByUserName(request.getUserName())
					.ifPresent(
							entity -> {
								throw new UserNameExistsException();
							});
			accountEntity = accountRepo.save(AccountConverter.from(request));

		} catch (IllegalAccessException exception) {
			throw new InvalidRequestException();
		}

		if (accountEntity.getId() != null) {
			return RegisterAccountResponse.builder()
					.code(200)
					.message(SUCCESS)
					.username(accountEntity.getUserName())
					.role(accountEntity.getRole().toString())
					.build();
		}
		return null;
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		if (!StringUtils.hasText(request.getUserName())
				|| !StringUtils.hasText(request.getPassword())) {
			throw new InvalidRequestException();
		}
		AccountEntity account =
				accountRepo
						.findByUserName(request.getUserName())
						.orElseThrow(UserNameNotFoundException::new);

		return this.doLogin(request, account);
	}

	private LoginResponse doLogin(LoginRequest request, AccountEntity account) {
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
		if (!isAuthenticated(authentication)) {
			throw new InvalidLoginRequestException();
		}
		String token = JwtUtil.generateToken(account);
		return new LoginResponse(200, SUCCESS, token);
	}

	private boolean isAuthenticated(Authentication authentication) {
		try {
			return this.authenticationManager.authenticate(authentication).isAuthenticated();
		} catch (BadCredentialsException e) {
			throw new InvalidLoginRequestException();
		}
	}
}
