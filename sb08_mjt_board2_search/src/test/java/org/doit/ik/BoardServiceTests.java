package org.doit.ik;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTests {
	
	
	@Autowired
	private BoardService boardService;
	
	@Test
	void testRegister() {
		BoardDTO boardDTO = BoardDTO.builder()
									.title("단위 테스트 중...")
									.content("단위 테스트 중 컨텐트")
									.writerEmail("user55@aaa.com")
									.build();
		Long bno = this.boardService.register(boardDTO);
		System.out.println("------------" + bno);
	}
}
