package org.doit.ik.mreview.repository;

import org.doit.ik.mreview.entity.Member;
import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	// 특정 영화의 모든 리뷰 정보 + 회원의 닉네임	
	// attributePaths 속성: 조회할때 함께 가져올 연관 관계 필드이름을 지정하는 속성
	@EntityGraph( attributePaths = {"member"}, type = EntityGraphType.FETCH)
	List<Review> findByMovie(Movie movie);
	
	// i/u/d 
	//  회원 삭제하는 쿼리 메서드가 아니라...
	// [ member 객체와 연관된 모든 리뷰를 삭제하는 쿼리 메서드 ]
	@Modifying
	@Query("""
			DELETE FROM Review r
			WHERE r.member = :member
			""")
    void deleteByMember(@Param("member") Member member);

}




