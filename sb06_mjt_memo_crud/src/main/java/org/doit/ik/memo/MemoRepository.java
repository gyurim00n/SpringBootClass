package org.doit.ik.memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Repository	JPArepostiory extends 햇으므로 자동으로 빈에 등록된다.
public interface MemoRepository extends JpaRepository<Memo, Long>{

	//[1]Query Method: 메서드 이름 자체가 SQL 문
	//FROM memo 
	//WHERE mno BETWEEN ? AND ?
	//ORDER BY mno DESC
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
	
	
	//[2]페이징 처리된 쿼리메서드
	//FROM memo
	//WHERE mno BETWEEN ? AND ?
	Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
	
	//[3] Query Method JQPL
	//DELETE FROM memo
	//WHERE mno > ?
	void deleteMemoByMnoLessThan(Long mno);
	
	
	//[4]@Query(JPQL)
	@Query("""
			SELECT m
			FROM Memo m
			ORDER BY m.mno DESC
			"""
			)
	List<Memo> getMemoListDesc();
	
	@Transactional
	// update, dml
	@Modifying//UPDATE DML
	@Query("""
			UPDATE Memo m
			SET m.memoText = ?2
			WHERE m.mno = ?1
			
			""")
	int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoTextm, @Param("memo") Memo memo);
	//1) mno 메모를 얻어오는 작업: findById(mno)
	//2) update ...
	//SET m.memoText = ?2 WHERE m.mno = ?1
	// *** SET m.memoTxt = :memoText WHERE m.mno = :mno
	// ***SET m.memoTxt = :#{memo.memoText} 	WHERE m.mno= :mno
	
	@Query(value="SELECT * FROM memo WHERE mno > 0", nativeQuery = true)
	List<Object []> getNativeResult();
	
}
