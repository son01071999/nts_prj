package com.code.nts_prj.service.impl;

import com.code.nts_prj.converter.AccountConverter;
import com.code.nts_prj.entity.AccountEntity;
import com.code.nts_prj.exception.ErrorMessage;
import com.code.nts_prj.exception.account.runtime.RegisterRequestException;
import com.code.nts_prj.repository.AccountRepo;
import com.code.nts_prj.request_response.request.RegisterAccountRequest;
import com.code.nts_prj.request_response.response.RegisterAccountResponse;
import com.code.nts_prj.service.AccountService;
import com.code.nts_prj.validation.RequestObjectValidation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
	private final AccountRepo accountRepo;
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
			if (RequestObjectValidation.checkAllFieldIsNotNull(request)) {
				throw new RegisterRequestException("400", ErrorMessage.REGISTER_ACCOUNT_REQUEST_INVALID);
			}

			// check if findByUserName is Empty -> save to DB
			accountRepo
					.findByUserName(request.getUserName())
					.ifPresent(
							entity -> {
								throw new RegisterRequestException("400", ErrorMessage.USERNAME_EXISTS);
							});
			accountEntity = accountRepo.save(AccountConverter.from(request));

		} catch (IllegalAccessException exception) {
			throw new RegisterRequestException("400", ErrorMessage.REGISTER_ACCOUNT_REQUEST_INVALID);
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
}
