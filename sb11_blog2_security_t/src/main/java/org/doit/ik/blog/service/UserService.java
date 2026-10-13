package org.doit.ik.blog.service;

import org.doit.ik.blog.domain.User;
import org.doit.ik.blog.dto.AddUserRequest;
import org.doit.ik.blog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	// 회원 가입
	public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
					                .email(dto.getEmail())
					                .password(passwordEncoder.encode(dto.getPassword()))
					                .build()
					               ).getId();
    }

	// 사용자ID로 사용자 객체 반환
    public User findById(Long userId) {      
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
    
    // 로그인
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

}







