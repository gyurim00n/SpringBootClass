package org.doit.ik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

//이코드는 이 파일을 xml 취급하게 만들어준다. 
@Configuration
@Log4j2
public class SecurityConfig {

	// 1. 반드시 Spring Boot 2.0부터 PasswordEncoder 설정.
	@Bean PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	// 2. autehntication-manager 설정: SS에서 인증처리 핵심.
	//	ㄴauthentication-provider : 인증처리를 하는 구현 객체 (클래스) 중에 인 메모리상의 인증 처리.

	@Bean InMemoryUserDetailsManager inMemoryUserDetailsManager() {

		UserDetails user = User.builder()
				.username("moon")
				.password(this.passwordEncoder().encode("1234"))
				.roles("USER")
				.build();

		UserDetails admin = User.builder()
				.username("admin")
				.password(this.passwordEncoder().encode("1234"))
				.roles("Admin")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);

	}
	
	@Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		log.info(" SecurityConfig.securityFilterChain....");
		//인가(Authorization)가 필요한 리소스 설정 방법: 2가지 
		//1) 어노테이션을 사용...
//		<security:intercept-url pattern="/customer/noticeReg.htm"  access="isAuthenticated()" />
//	      <security:intercept-url pattern="/customer/noticeDel.htm"  access="hasRole('ROLE_ADMIN')" />
//	      <security:intercept-url pattern="/**"  access="permitAll" />    
		http.authorizeHttpRequests((auth) -> {
			auth
			.requestMatchers("/sample/admin").hasRole("ADMIN")
			.requestMatchers("/sample/admin").hasRole("USER")
			.anyRequest().permitAll();
		});
		
		 //<security:form-login
		http.formLogin( Customizer.withDefaults() ); 	//login 요청 시 스프링 시큐리티에서 만든 기본 로그인 페이지를 사용하겠다는 뜻
		http.formLogin((form) -> {
			form.defaultSuccessUrl("/", true).permitAll();
			
		});
		
		//csrf 설정, Spring Security 7에서 API 거 변경...
		http.csrf(csrf -> csrf.disable());
		
		//<security:logout/>
		http.logout(logout -> logout.logoutSuccessUrl("/").permitAll());
		
		return http.build();
	}
}
