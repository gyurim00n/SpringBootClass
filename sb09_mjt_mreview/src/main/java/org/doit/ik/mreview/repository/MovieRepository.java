package org.doit.ik.mreview.repository;

import java.util.List;

import org.doit.ik.mreview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	//[1]영화 이미지는 대표 이미지 1개만 가져올 예정
	//번호/제목/리뷰수/평점평균/등록일-영화목록페이지+페이징처리, JPQL:
	@Query("""
			Select m, mi, avg(coalesce(r.grade,0)), count(distinct r)
			FROM Movie m LEFT OUTER JOIN MovieImage mi ON mi.movie = m
						 LEFT OUTER JOIN Review r ON r.movie = m
			GROUP BY m, mi
			""")
	Page<Object[]> getListPage(Pageable pageable);
	
	//[2]특정 영화의 정보를 상세보기(조회) -> mno 필요
	@Query("""
			Select m, mi
			FROM Movie m 
			LEFT OUTER JOIN MovieImage mi ON mi.movie = m
			
			WHERE m.mno = :mno
			GROUP BY m, mi
			
			""")
	List<Object[]> getMovieWithAll(@Param("mno") Long mno);
	

}
