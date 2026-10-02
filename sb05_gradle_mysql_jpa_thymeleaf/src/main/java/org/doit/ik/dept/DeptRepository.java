package org.doit.ik.dept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface DeptRepository extends JpaRepository<Dept, Integer>{
	//Query Method
	List<Dept> findByDname(String dname);
	List<Dept> findByDnameAndLoc(String keyword, String loc);	
	List<Dept> findByDnameOrLoc(String keyword, String loc);	

	@Query("SELECT d FROM Dept d")
	List<Dept> getAllDepts();



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