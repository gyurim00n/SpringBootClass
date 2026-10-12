package org.doit.ik.club.security.dto;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@ToString
@Getter
@Setter
@Log4j2
public class ClubAuthMemberDTO extends User {

	//인증받은 후 저장할 로그인 회원 정보...
	private String email;
	private String name;
	private boolean fromSocial;
	
	public ClubAuthMemberDTO(String username, 
			@Nullable String password,
			boolean fromSocial,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password,authorities);

		this.email = username;
		this.fromSocial = fromSocial;
	}
	
}
