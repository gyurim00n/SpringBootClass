package org.doit.ik.board.repository;

import org.doit.ik.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

	
}
