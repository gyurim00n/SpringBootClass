package org.doit.ik.board.repository;

import org.doit.ik.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
	//글번호(bno)에 해당하는 댓글 모든 댓글 삭제하는 메서드
	@Transactional //실무에서 보통 Transaction 처리한다. 해당 글번호를 가진 reply가 존재하는지 확인위함.
	@Modifying 	//Spring DATA JPA에게 insert/update/delete 처리되는 쿼리입니다를 알림.
				//붙이지 않을시 InvalidDataAccessApiUsageException 예외 발생.
	@Query("""
			DELETE FROM Reply r
			WHERE r.board.bno = :bno
			""")
	void deleteByBno(@Param("bno") Long bno);
	
	
}
