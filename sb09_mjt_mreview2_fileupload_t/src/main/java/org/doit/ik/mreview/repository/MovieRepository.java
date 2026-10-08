package org.doit.ik.mreview.repository;

import java.util.List;

import org.doit.ik.mreview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	// [1]영화 이미지는 대표 이미지 1개 가져올 예정
	// 번호/제목/리뷰수/평점평균/등록일 -  영화목록 페이지 + 페이징 처리. JPQL
	@Query("""
			SELECT m, mi
			     , avg(coalesce(r.grade,0)), count(distinct r)
			FROM Movie m LEFT JOIN MovieImage mi ON mi.movie = m
			             LEFT JOIN Review r ON r.movie = m
			GROUP BY m, mi             
			""")
	Page<Object []> getListPage(Pageable pageable);
	
	// [2] 특정 영화의 정보를 상세보기(조회) 
	@Query("""
			SELECT m, mi
			FROM Movie m LEFT JOIN MovieImage mi ON mi.movie = m			
			WHERE m.mno = :mno
			""")
	List<Object []> getMovieWithAll(@Param("mno")Long mno); 

}




