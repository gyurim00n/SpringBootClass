package org.doit.ik;

import java.util.Arrays;
import java.util.List;

import org.doit.ik.mreview.repository.MovieImageRepository;
import org.doit.ik.mreview.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class MovieRepositoryTests {
   
   @Autowired
   private MovieRepository movieRepository;
   
   @Autowired
   private MovieImageRepository movieImageRepository;
   
   /*
   @Test
   void insertMovies() {
      IntStream.rangeClosed(1, 100)
      .forEach(i->{
         // 영화 저장
         Movie movie = Movie.builder()
               .title("move..." + i)
               .build();
         this.movieRepository.save(movie);
         
         // 랜덤하게 영화이미지를 저장  1<=  <5
         int count = (int)(Math.random()*5)+ 1;
         for (int j = 0; j < count; j++) {
            MovieImage movieImage = MovieImage.builder()
                  .uuid( UUID.randomUUID().toString()  )
                  .movie(movie)
                  .imgName("test"+i+".jpg")
                  .build();
            
            this.movieImageRepository.save(movieImage);
         } // for
         
      });
   }
	*/
   /*
   @Test
   void testGetListPage() {
	   Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
	   Page<Object[]> result = this.movieRepository.getListPage(pageable);
	   
	   for(Object[] objects: result.getContent()) {
		   System.out.println("" + Arrays.toString(objects));
	   }
	   
   }
   */
   
  
}










