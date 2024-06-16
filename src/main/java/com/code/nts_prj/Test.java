package com.code.nts_prj;

import com.code.nts_prj.auth.JwtUtil;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Test {
	public static void main(String[] args) throws Exception {
		//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String pass1 = "123456";
		String pass2 = "345678";
		String pass3 = "123456";
		String enPass1 = encode(pass1);
		String enPass2 = encode(pass2);
		String enPass3 = encode(pass3);
		System.out.println(verifyPassword(pass1, enPass1));
		System.out.println(verifyPassword(pass3, enPass1));
		System.out.println("enPass1 :  " + enPass1);
		System.out.println("enPass3 :  " + enPass3);
		System.out.println("enPass2 :  " + enPass2);
	}

	public static String encode(String pass) throws Exception {
		Mac sha256HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec = new SecretKeySpec(JwtUtil.SECRET_KEY.getBytes(), "HmacSHA256");
		sha256HMAC.init(secretKeySpec);
		byte[] hashedBytes = sha256HMAC.doFinal(pass.getBytes());
		return Base64.getEncoder().encodeToString(hashedBytes);
	}

	public static boolean verifyPassword(String password, String encodedPassword) throws Exception {
		String newEncodedPassword = encode(password);
		return newEncodedPassword.equals(encodedPassword);
	}
}
