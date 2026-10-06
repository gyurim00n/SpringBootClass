package org.doit.ik;

import java.util.stream.IntStream;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.repository.GuestbookRepository;
import org.doit.ik.guestbook.service.GuestbookService;
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
	
	@Autowired
	private GuestbookService guestbookService;
	
	@Test
	void querydslTest() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
														.page(1)
														.size(10)
														.type("t")
														.keyword("15")
														.build();
		
		//this.guestbookService.getList(pageRequestDTO);
		PageResultDTO<GuestbookDTO, Guestbook> result = this.guestbookService.getList(pageRequestDTO);
		for(GuestbookDTO dto : result.getDtoList()) {
			System.out.println("😍😍" + dto);
		}
		System.out.println("=".repeat(50));
		if(result.isPrev()) System.out.println("<<");
		result.getPageList().forEach(i -> System.out.println(i+" "));
		if(result.isNext()) System.out.println(">>");
		System.out.println("=".repeat(50));
		
	}
}
