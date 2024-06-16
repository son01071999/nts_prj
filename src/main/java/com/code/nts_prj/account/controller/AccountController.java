package com.code.nts_prj.account.controller;

import com.code.nts_prj.account.entity.AccountEntity;
import com.code.nts_prj.account.request_response.request.LoginRequest;
import com.code.nts_prj.account.request_response.request.RegisterAccountRequest;
import com.code.nts_prj.account.request_response.response.LoginResponse;
import com.code.nts_prj.account.request_response.response.RegisterAccountResponse;
import com.code.nts_prj.account.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired private AccountService accountService;

	@PostMapping("/user/register-account")
	public ResponseEntity<RegisterAccountResponse> registerAccount(
			@RequestBody RegisterAccountRequest request) {
		return ResponseEntity.ok().body(accountService.registerAccount(request));
	}

	@PostMapping("/user/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok().body(accountService.login(request));
	}

	@GetMapping("/admin/getAll")
	public ResponseEntity<List<AccountEntity>> getAll() {
		return ResponseEntity.ok().body(accountService.getAll());
	}
}
