package org.doit.ik.club.repository;

import java.util.Optional;

import org.doit.ik.club.entity.ClubMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {
	
	
	//[1] 쿼리 메서드 추가
	//email(pk) + 일반/소셜 로그인 사용자 -> 회원정보 조회
	@EntityGraph(attributePaths = {"roleSet"}, type = EntityGraphType.LOAD)
	@Query("""
			SELECT m
			FROM ClubMember m
			WHERE m.fromSocial = :social AND m.email = :email
			""")
	Optional<ClubMember> findByEmail(
						  @Param("email") String email
						, @Param("social") boolean social
						);
}
