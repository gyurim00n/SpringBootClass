package org.doit.ik.blog.repository;

import java.util.Optional;

import org.doit.ik.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	// 이메일을 사용해서 사용자 정보를 반환
	Optional<User> findByEmail(String email);

}
