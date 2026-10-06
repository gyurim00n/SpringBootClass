package org.doit.ik;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Member;
import org.doit.ik.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BoardRepositoryTests {
	
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	
	void insertBoards() {
		
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member writer = Member.builder().email("user"+i+"@aaa.com").build();
			
			//entity;
			Board entity = Board.builder()
								.title("title..." + i)
								.content("content... " + i)
								.writer(writer)
								.build();
			this.boardRepository.save(entity);
			
		});
	}
	
	@Test
	@Transactional
	void testRead1() {
		//100번 게시글 정보를 상세보기(조회)
		Long bno =100L;
		Optional<Board> o_board= this.boardRepository.findById(bno);
		Board board = o_board.get();
		System.out.println("😑😑😑" + board);
		
		//작성자정보
		Member writer = board.getWriter();
		System.out.println("😑😑😑" + writer);
	}
	
	@Test
	void testGetBoardWithWriter() {
		Long bno =100L;
		Object result = this.boardRepository.getBoardWithWriter(bno);
		Object [] arr = (Object[])result;
		System.out.println("--------------------------");
		System.out.println(Arrays.toString(arr));
		System.out.println("--------------------------");
		
	}


}
