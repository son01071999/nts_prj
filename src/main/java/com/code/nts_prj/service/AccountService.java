package com.code.nts_prj.service;

import com.code.nts_prj.entity.AccountEntity;
import com.code.nts_prj.request_response.request.RegisterAccountRequest;
import com.code.nts_prj.request_response.response.RegisterAccountResponse;
import java.util.List;

public interface AccountService {

	List<AccountEntity> getAll();

	RegisterAccountResponse registerAccount(RegisterAccountRequest request);
}
