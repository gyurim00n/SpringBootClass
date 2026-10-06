package org.doit.ik;


import java.util.stream.IntStream;

import org.doit.ik.board.entity.Member;
import org.doit.ik.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTests {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	void insertMembers() {
		
		IntStream.rangeClosed(1, 100).forEach(i -> {
			//entity;
			Member entity = Member.builder().email("user"+i+"@aaa.com").password("1111").name("user"+i).build();
			this.memberRepository.save(entity);
			
		});
	}
	
	
}
