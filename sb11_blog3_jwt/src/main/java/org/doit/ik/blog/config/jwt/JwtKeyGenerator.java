package org.doit.ik.blog.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

public class JwtKeyGenerator {
	//spring.jwt.secret_key=pcUfukvfGkEt/4tVNhU+4LIGl2JexZxxU0WfeKc4z94
	public static void main(String[] args) {
		
		String secret_key = Encoders.BASE64.encode(Jwts.SIG.HS256.key().build().getEncoded());
		System.out.println("Secretkey is...." +secret_key);
		
	}
}
