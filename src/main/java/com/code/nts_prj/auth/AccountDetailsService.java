package com.code.nts_prj.auth;

import com.code.nts_prj.account.entity.AccountEntity;
import com.code.nts_prj.account.exceptions.runtime.UserNameNotFoundException;
import com.code.nts_prj.repository.AccountRepo;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {
	private final AccountRepo accountRepo;

	public AccountDetailsService(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		AccountEntity account =
				accountRepo.findByUserName(username).orElseThrow(UserNameNotFoundException::new);
		List<GrantedAuthority> authorityList =
				List.of(new SimpleGrantedAuthority(account.getRole().name()));

		return new User(account.getUserName(), account.getPassword(), authorityList);
	}
}
