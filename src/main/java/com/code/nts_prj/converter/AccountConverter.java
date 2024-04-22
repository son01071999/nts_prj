package com.code.nts_prj.converter;

import com.code.nts_prj.entity.AccountEntity;
import com.code.nts_prj.enums.Role;
import com.code.nts_prj.request_response.request.RegisterAccountRequest;
import lombok.Builder;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

@Getter
@Builder
public class AccountConverter {
	public static AccountEntity from(final RegisterAccountRequest request) {
		if (request == null) {
			return null;
		}
		return AccountEntity.builder()
				.userName(request.getUserName())
				.password(hashPassword(request.getPassword()))
				.role(ConvertEnumFromString.validateEnumFromString(Role.class, request.getRole()))
				.phoneNumber(request.getPhoneNumber())
				.name(request.getName())
				.age(request.getAge())
				.build();
	}

	private static String hashPassword(String password) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(password, salt);
	}

	public static boolean checkPassword(String inputPassword, String hashedPassword) {
		return BCrypt.checkpw(inputPassword, hashedPassword);
	}
}
