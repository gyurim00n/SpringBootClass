package org.doit.ik;

import java.util.Optional;
import java.util.stream.IntStream;

import org.doit.ik.memo.Memo;
import org.doit.ik.memo.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoRepositoryTests {
	@Autowired
	private MemoRepository memoRepository;
	
	//[1] memoReposioty  객체 확인
	@Test
	void contextLoads() {
		System.out.println("😒😒" + this.memoRepository.getClass().getName());
	}

	//[2] 더미데이터 추가 256개 
	@Test
	void testInsertDummies() {
		IntStream
			.rangeClosed(1, 256)
			.forEach( i -> {
				Memo entity = Memo.builder()
						.memoText("Sample Memo..." + i)
						.build();
				this.memoRepository.save(entity);
				
			});
	}
	
	//[3] mno =256L 메모 내용을 조회 SELECT

	@Test
	void testSelectOne() {
		//this.memoRepository.getOne(256L);
		Optional<Memo> opMemo = this.memoRepository.findById(256L);
		opMemo.ifPresent(memo -> {
			
			System.out.println("~~~~~~~~~ 메모테스트" + memo.getMemoText());
		});
	}
	
	//[4] mno =256L 메모 내용을 조회 SELECT
	
	@Test
	void testDeleteOne() {
		this.memoRepository.deleteById(256L);
	}
	
	//[5] mno=255L 메모 내용 "수정" UPDAte@Test
	void testUpdateOne() {
		//Entity
		//Memo memo = this.memoRepository.getOne(256L);
		Memo memo = this.memoRepository.findById(256L).orElseThrow();
		memo.setMemoText("UPDATE");;
		Memo uMemo = this.memoRepository.save(memo);
		System.out.println("😒😒" + uMemo.getMemoText());
	}
	
	
}
