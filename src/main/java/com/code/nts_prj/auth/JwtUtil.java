package com.code.nts_prj.auth;

import com.code.nts_prj.account.entity.AccountEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	public static final String SECRET_KEY = "12345678900987654321qwertyuioppoiuytrewq";

	public static final String PREFIX = "Bearer ";

	public static final String AUTHORIZATION = "Authorization";

	public static final String ROLE = "Role";

	// 1h
	private static final long JWT_EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 1 day

	public static String generateToken(AccountEntity accountEntity) {
		Claims claims = Jwts.claims().subject(accountEntity.getUserName()).build();
		/** Need to add Role into token. But claims is immutable. will handle it later */
		//		claims.put(ROLE, getUserAuthorities(accountEntity));
		return Jwts.builder()
				.claims(claims)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
				.signWith(Jwts.SIG.HS256.key().build())
				.compact();
	}

	public static String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public static Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public static Boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public static String parseToken(String token) {
		return token.substring(PREFIX.length());
	}

	private static List<SimpleGrantedAuthority> getUserAuthorities(AccountEntity accountEntity) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(accountEntity.getRole().name()));
		return authorities;
	}

	private static Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(Jwts.SIG.HS256.key().build())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	private static boolean isTokenExpired(String token) {
		return extractExpiration(token).before(Date.from(Instant.now()));
	}
}
