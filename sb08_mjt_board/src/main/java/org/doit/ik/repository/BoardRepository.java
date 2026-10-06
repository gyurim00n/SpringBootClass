package org.doit.ik.repository;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	//[1] JPQL 쿼리, LEFT JOIN 을 사용
	//Board 게시ㅣ글 정보를 조회할 때 Writer 작성자 정보도 조인해서 같이 SELECT
	@Query("""
			SELECT b, w
			FROM Board b LEFT JOIN b.writer w 
			WHERE b.bno = :bno
			""")
	Object getBoardWithWriter(@Param("bno") Long bno);
	
}
