package org.doit.ik.dept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// @Repository X(필요없음 자동으로 올라가져서 Bean -> ㅇㅇㅇjpa.DeptRepository.fragments#0
// <엔티티, 프라이머리 컬럼의 타입>
public interface DeptRepository extends JpaRepository<Dept, Integer>{
	// 방법1) 기본 제공 메서드

	// 방법2) 쿼리메서드 
	// 예 ) 부서명으로 dept 조회
	//			findBy + 엔티티의 필ㄹ드명
	List<Dept> findByDname(String dname);
	List<Dept> findByDnameAndLoc(String keyword);	//WHERE dname = ?  AND loc = ?
	List<Dept> findByDnameOrLoc(String keyword);	//WHERE dname = ?  OR loc = ?

	// 방법3) @Query 어노테이션 사용 + JPQL 또는 Native SQL
	// Spring Data JPA에서 Repository 메서드에 직접 쿼리를 작성할때 사용하는 어노테이션
	@Query("SELECT d FROM Dept d")
	List<Dept> getAllDepts();


	//Native SQL 사용 예시. 이전에 쓴 SQL과 같다. 
	@Query(
			value = "SELECT * FROM DEPT WHERE DNAME = :dname",
			nativeQuery = true
			)
	List<Dept> findByDname2(@Param("dname") String dname);


	@Modifying
	@Transactional
	@Query("UPDATE Dept d SET d.dname = :dname, d.loc = :loc WHERE d.deptno = :deptno")
	int updateDept(
			@Param("deptno") Integer deptno,
			@Param("dname") String dname,
			@Param("loc") String loc);    

}

/*
복잡한 쿼리 사용해야할 경우
1)쿼리 메서드
2) JPQL 사용
3) @Query
4) QueryDsl 사용 *** : 동적 쿼리, 복잡한 쿼리

 */
/*
키워드   의미   SQL 개념
GreaterThan   초과   >
GreaterThanEqual   이상   >=
LessThan   미만   <
LessThanEqual   이하   <=
Between   범위   BETWEEN
Equal   같음   =
Not   다름   <>
 */