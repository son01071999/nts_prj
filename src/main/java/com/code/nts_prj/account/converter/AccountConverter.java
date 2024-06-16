package com.code.nts_prj.account.converter;

import com.code.nts_prj.account.entity.AccountEntity;
import com.code.nts_prj.account.enums.Role;
import com.code.nts_prj.account.request_response.request.RegisterAccountRequest;
import com.code.nts_prj.converter.ConvertEnumFromString;
import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
public class AccountConverter {
	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public static AccountEntity from(final RegisterAccountRequest request) {
		if (request == null) {
			return null;
		}
		return AccountEntity.builder()
				.userName(request.getUserName())
				.password(encodePassword(request.getPassword()))
				.role(ConvertEnumFromString.validateEnumFromString(Role.class, request.getRole()))
				.phoneNumber(request.getPhoneNumber())
				.name(request.getName())
				.age(request.getAge())
				.build();
	}

	public static String encodePassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

	/** Using for update password method ( TO DO ) */
	public static boolean checkPassword(String inputPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(inputPassword, encodedPassword);
	}

	private AccountConverter() {}
}
