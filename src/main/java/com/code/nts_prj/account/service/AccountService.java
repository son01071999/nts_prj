package com.code.nts_prj.account.service;

import com.code.nts_prj.account.entity.AccountEntity;
import com.code.nts_prj.account.request_response.request.LoginRequest;
import com.code.nts_prj.account.request_response.request.RegisterAccountRequest;
import com.code.nts_prj.account.request_response.response.LoginResponse;
import com.code.nts_prj.account.request_response.response.RegisterAccountResponse;
import java.util.List;

public interface AccountService {

	List<AccountEntity> getAll();

	RegisterAccountResponse registerAccount(RegisterAccountRequest request);

	LoginResponse login(LoginRequest request);
}
