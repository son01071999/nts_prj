package com.code.nts_prj.auth;

import com.code.nts_prj.account.entity.AccountEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	@Value("${spring.security.jwt.secret-key}")
	private String jwtSecret;

	@Value("${spring.security.jwt.expiration-time}")
	private long jwtExpirationTime;

	public final String PREFIX = "Bearer ";

	public final String AUTHORIZATION = "Authorization";

	public final String ROLE = "role";

	public String generateToken(AccountEntity accountEntity) {
		Claims claims =
				Jwts.claims()
						.add(ROLE, this.getUserAuthorities(accountEntity))
						.subject(accountEntity.getUserName())
						.build();
		return Jwts.builder()
				.claims(claims)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
				.signWith(getSigningKey())
				.compact();
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	//	public Boolean validateToken(String token, UserDetails userDetails) {
	//		final String userName = extractUserName(token);
	//		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	//	}

	public String parseToken(String token) {
		return token.substring(PREFIX.length());
	}

	private List<SimpleGrantedAuthority> getUserAuthorities(AccountEntity accountEntity) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + accountEntity.getRole().name()));
		return authorities;
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	//	private static boolean isTokenExpired(String token) {
	//		return extractExpiration(token).before(Date.from(Instant.now()));
	//	}

	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
