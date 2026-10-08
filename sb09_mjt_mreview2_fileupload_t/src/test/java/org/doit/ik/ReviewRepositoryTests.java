package org.doit.ik;

import java.util.UUID;
import java.util.stream.IntStream;

import org.doit.ik.mreview.entity.Member;
import org.doit.ik.mreview.entity.Movie;
import org.doit.ik.mreview.entity.MovieImage;
import org.doit.ik.mreview.entity.Review;
import org.doit.ik.mreview.repository.MemberRepository;
import org.doit.ik.mreview.repository.MovieImageRepository;
import org.doit.ik.mreview.repository.MovieRepository;
import org.doit.ik.mreview.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewRepositoryTests {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Test
	void insertMovieReviews() {
		
		IntStream.rangeClosed(1, 200)
		.forEach(i->{
			Long mid = (long)(Math.random()*100)+1;			
			Member member= Member.builder()
					.mid(mid)
					.build();
			
			Long mno = (long)(Math.random()*100)+1;
			Movie movie = Movie.builder()
					.mno(mno)
					.build();
			
			int grade = (int)(Math.random()*5)+1;
			Review review = Review.builder()
					.member(member)
					.movie(movie)
					.grade(grade)
					.text("이 영화에 대한 느낌..." + i)
					.build();
		
			this.reviewRepository.save(review);
			
		});
	}

}










