package org.doit.ik.blog.config.jwt;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties("spring.jwt") //app.pro에서 읽어옴..
public class JwtProperties {

	private String issuer;
	//spring.jwt.issuer=moon@doit.org

	private String secretKey; //secret_key
	//spring.jwt.secret_key=sjJbCjxEpeKsIuEaZHh3gQdw5gP+wmPLtjLkfkqB61s=
	
	
	// Access Token 만료시간
	private Duration accessTokenExpiration;
	
	
	// Refresh Token 만료시간
	private Duration refreshTokenExpiration;

}
