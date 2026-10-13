package org.doit.ik.blog.config;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security의 웹 보안 기능을 활성화하기 위해 사용하는 어노테이션
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	// ① 스프링 시큐리티 기능 비활성화
	@Bean 
	WebSecurityCustomizer configure() {
		return (web) -> web.ignoring()
				.requestMatchers(PathRequest.toH2Console())
				.requestMatchers("/static/**");		
	}
	
	// ② 특정 HTTP 요청에 대한 웹 기반 보안 구성
	@Bean 
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
				.authorizeHttpRequests(auth ->	// 인증, 인가 설정
				auth
					.requestMatchers(
							 "/login"
							, "/signup"
							, "/user"
							, "/"
							, "/index"
							).permitAll()
					.anyRequest().authenticated()
				)
//				.formLogin(Customizer.withDefaults())
				.formLogin(formLogin -> formLogin	// 폼 기반 로그인 설정
						.loginPage("/login")
						.defaultSuccessUrl("/blog/articles")
						)
				.logout(logout -> logout			// 로그아웃 설정
						.logoutSuccessUrl("/login")
						.invalidateHttpSession(true)
						)
				.csrf(AbstractHttpConfigurer::disable)	// CSRF 비활성화
				.build();
	}
	
	// ③ 패스워드 인코더로 사용할 빈 등록
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

} // class




