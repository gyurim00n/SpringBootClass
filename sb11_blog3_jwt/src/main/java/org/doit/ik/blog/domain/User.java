package org.doit.ik.blog.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="users")
public class User implements UserDetails{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    //사용자의 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //사용자id(email) 반환
    @Override
    public String getUsername() {
        return email;
    }

    //패스워드 반환
    @Override
    public String getPassword() {
        return password;
    }
    
    //계정의 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    //계정의 잠금 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정의 사용가능여부
    @Override
    public boolean isEnabled() {
        return true;
    }
	
}
