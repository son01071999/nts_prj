package com.code.nts_prj.account.controller;

import com.code.nts_prj.account.entity.AccountEntity;
import com.code.nts_prj.account.request_response.request.LoginRequest;
import com.code.nts_prj.account.request_response.request.RegisterAccountRequest;
import com.code.nts_prj.account.request_response.response.LoginResponse;
import com.code.nts_prj.account.request_response.response.RegisterAccountResponse;
import com.code.nts_prj.account.service.AccountService;
import com.code.nts_prj.redis.RedisService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
	private final AccountService accountService;
	private final RedisService redisService;

	@PostMapping("/user/register-account")
	public ResponseEntity<RegisterAccountResponse> registerAccount(
			@RequestBody RegisterAccountRequest request) {
		return ResponseEntity.ok().body(this.accountService.registerAccount(request));
	}

	@PostMapping("/user/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok().body(this.accountService.login(request));
	}

	@GetMapping("/admin/get-all")
	public ResponseEntity<List<AccountEntity>> getAll() {
		return ResponseEntity.ok().body(this.accountService.getAll());
	}
}
