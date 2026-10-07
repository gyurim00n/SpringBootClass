package org.doit.ik;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.doit.ik.board.entity.Board;
import org.doit.ik.board.entity.Member;
import org.doit.ik.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	//[4]
	@Test
	void testGetBoardWithReply() {
		Long bno =50L;
		List<Object[]> result = this.boardRepository.getBoardWithReply(bno);
		System.out.println("--------------------------");
		for(Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println("--------------------------");
	}
	
	//[5]
	@Test
	void testGetBoardWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());//1번 페이지, 10개
		Page<Object[]> result = this.boardRepository.getBoardWithReplyCount(pageable);
		result.get().forEach(row -> {
			Object [] arr = (Object[]) row;
			System.out.println("😑😑"+ Arrays.toString(arr));
			
		});
//		System.out.println("--------------------------");
//		for(Object[] arr : result) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println("--------------------------");
//		Hibernate: 
//		    select
//		        b1_0.bno,
//		        b1_0.content,
//		        b1_0.moddate,
//		        b1_0.regdate,
//		        b1_0.title,
//		        b1_0.email,
//		        w1_0.email,
//		        w1_0.moddate,
//		        w1_0.name,
//		        w1_0.password,
//		        w1_0.regdate,
//		        count(r1_0.rno) 
//		    from
//		        Board b1_0 
//		    left join
//		        Member w1_0 
//		            on w1_0.email=b1_0.email 
//		    left join
//		        Reply r1_0 
//		            on r1_0.board_bno=b1_0.bno 
//		    group by
//		        b1_0.bno 
//		    order by
//		        b1_0.bno desc 
//		    limit
//		        ?
//		Hibernate: 
//		    select
//		        count(*) 
//		    from
//		        Board b1_0
	}
	//[6]
	@Test
	void testGetBoardByBno() {
		Object result = this.boardRepository.getBoardByBno(100L);
		
			Object [] arr = (Object[]) result;
			System.out.println("😑😑"+ Arrays.toString(arr));
	}
	

}
