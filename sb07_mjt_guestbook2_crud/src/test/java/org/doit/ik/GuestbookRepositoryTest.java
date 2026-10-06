package org.doit.ik;

import java.util.stream.IntStream;

import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuestbookRepositoryTest {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	
	@Test
	void insertDummies() {
		
		IntStream.rangeClosed(1, 315).forEach(i -> {
			Guestbook guestbook = Guestbook.builder()
					.title("title..." + i)
					.content("Content!!" + i)
					.writer("user"+ (i%10))
					.build();
			this.guestbookRepository.save(guestbook);
			
		});
		System.out.println("~~~~ 더미 데이터 저장 완료");
		
	}

}
