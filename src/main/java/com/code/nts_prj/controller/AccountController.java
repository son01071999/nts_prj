package com.code.nts_prj.controller;

import com.code.nts_prj.entity.AccountEntity;
import com.code.nts_prj.request_response.request.RegisterAccountRequest;
import com.code.nts_prj.request_response.response.RegisterAccountResponse;
import com.code.nts_prj.service.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
	private final AccountService accountService;

	@PostMapping("/register-account")
	public ResponseEntity<RegisterAccountResponse> registerAccount(
			@RequestBody RegisterAccountRequest request) {
		return ResponseEntity.ok().body(accountService.registerAccount(request));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<AccountEntity>> getAll() {
		return ResponseEntity.ok().body(accountService.getAll());
	}
}
