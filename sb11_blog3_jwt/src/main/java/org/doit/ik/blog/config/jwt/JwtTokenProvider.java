package org.doit.ik.blog.config.jwt;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.crypto.SecretKey;

import org.doit.ik.blog.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
	private final JwtProperties jwtProperties;
	// 1. Access Token 생성: generateAccessToken 
	   public String generateAccessToken(User user, Duration expiredAt) {

	      Date now = new Date();

	      return Jwts.builder()
	            .issuer(jwtProperties.getIssuer())
	            .issuedAt(now)
	            .expiration(new Date(now.getTime() + expiredAt.toMillis()))
	            .subject(user.getEmail())
	            .claim("id", user.getId())
	            .claim("type", "access")      // ***
	            .signWith(getSecretKey())
	            .compact();
	   }

	   // 2. Refresh Token 생성: generateRefreshToken 
	   public String generateRefreshToken(User user, Duration expiredAt) {

	      Date now = new Date();

	      return Jwts.builder()
	            .issuer(jwtProperties.getIssuer())
	            .issuedAt(now)
	            .expiration(new Date(now.getTime() + expiredAt.toMillis()))
	            .subject(user.getEmail())
	            .claim("id", user.getId())
	            .claim("type", "refresh")      // ***
	            .signWith(getSecretKey())
	            .compact();
	   }

	   // 3. Refresh Token을 이용해 Access Token 생성[새로 추가]: generateAccessTokenFromRefreshToken 
	   public String generateAccessTokenFromRefreshToken(
	         String refreshToken,
	         Duration expiredAt) {

	      if (!validToken(refreshToken)) {
	         throw new IllegalArgumentException(
	               "유효하지 않은 Refresh Token입니다."
	               );
	      }

	      if (!isRefreshToken(refreshToken)) {
	         throw new IllegalArgumentException(
	               "Refresh Token이 아닙니다."
	               );
	      }

	      Claims claims = getClaims(refreshToken);      

	      //System.out.println( claims.get("id", Long.class) + " / " + claims.getSubject()); 

	      Date now = new Date();

	      return Jwts.builder()
	            .issuer(jwtProperties.getIssuer())
	            .issuedAt(now)
	            .expiration(new Date(now.getTime() + expiredAt.toMillis()))
	            .subject(claims.getSubject())
	            .claim("id", claims.get("id", Long.class))
	            .claim("type", "access")
	            .signWith(getSecretKey())
	            .compact();
	   }

	   // 3-2. 기존 Refresh Token을 이용해 새로운   Refresh Token 생성
	   public String generateRefreshTokenFromRefreshToken(
	         String refreshToken,
	         Duration expiredAt) {

	      if (!validToken(refreshToken)) {
	         throw new IllegalArgumentException(
	               "유효하지 않은 Refresh Token입니다."
	               );
	      }

	      if (!isRefreshToken(refreshToken)) {
	         throw new IllegalArgumentException(
	               "Refresh Token이 아닙니다."
	               );
	      }

	      Claims claims = getClaims(refreshToken);

	      Date now = new Date();

	      return Jwts.builder()
	            .issuer(jwtProperties.getIssuer())
	            .issuedAt(now)
	            .expiration(new Date(now.getTime() + expiredAt.toMillis()))
	            .subject(claims.getSubject())
	            .claim("id", claims.get("id", Long.class))
	            .claim("type", "refresh")
	            .signWith(getSecretKey())
	            .compact();
	   }

	   // 4. Secret Key 생성: getSecretKey 
	   private SecretKey getSecretKey() {

	      return Keys.hmacShaKeyFor(
	            Decoders.BASE64.decode(
	                  jwtProperties.getSecretKey()
	                  )
	            );
	   }

	   // 5. JWT 유효성 검증: validToken 
	   public boolean validToken(String token) {

	      try {
	         Jwts.parser()
	         .verifyWith(getSecretKey())
	         .build()
	         .parseSignedClaims(token);

	         return true;

	      } catch (Exception e) {

	         return false;
	      }
	   }

	   //  6. JWT에서 Claims 가져오기: getClaims 
	   private Claims getClaims(String token) {

	      return Jwts.parser()
	            .verifyWith(getSecretKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();
	   }

	   /// 7. Access Token인지 확인: isAccessToken 
	   public boolean isAccessToken(String token) {

	      Claims claims = getClaims(token);

	      return "access".equals(claims.get("type", String.class));
	   }

	   // 8. Refresh Token인지 확인: isRefreshToken 
	   public boolean isRefreshToken(String token) {

	      Claims claims = getClaims(token);

	      return "refresh".equals(claims.get("type", String.class));
	   }

	   //  9. JWT → Authentication 
	   public Authentication getAuthentication(String token) {

	      Claims claims = getClaims(token);

	      Set<SimpleGrantedAuthority> authorities =
	            Collections.singleton(
	                  new SimpleGrantedAuthority("ROLE_USER")
	                  );

	      org.springframework.security.core.userdetails.User principal =
	            new org.springframework.security.core.userdetails.User(
	                  claims.getSubject(),
	                  "",
	                  authorities
	                  );

	      return new UsernamePasswordAuthenticationToken(
	            principal,
	            token,
	            authorities
	            );
	   }

	   //  10. JWT에서 User ID 가져오기: getUserId 
	   public Long getUserId(String token) {

	      Claims claims = getClaims(token);
	      return claims.get("id", Long.class);
	   }
	
}
