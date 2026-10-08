package org.doit.ik;

import org.doit.ik.mreview.entity.Member;
import org.doit.ik.mreview.repository.MemberRepository;
import org.doit.ik.mreview.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import jakarta.transaction.Transactional;

@SpringBootTest
class MemberRepositoryTests {
	
	@Autowired
	private MemberRepository memberRepository;
	
	/*
	@Test
	void insertMembers() {
		IntStream.rangeClosed(1, 100)
		.forEach(i->{
			// 영화 저장
			Member member = Member.builder()
					.email("r"+i+"@doit.com")
					.pw("1111")
					.nickname("reviewer"+i)
					.build();
			this.memberRepository.save(member); 
		});
	}
	*/
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	// 회원 삭제
	@Transactional
	@Commit
	@Test
	void testDeleteMember() {
		Long mid = 2L;
		Member  member = Member.builder()
				.mid(mid)
				.build();
		// 1) 먼저 리뷰 삭제
		this.reviewRepository.deleteByMember(member);
		// 2) 회원 삭제
		this.memberRepository.deleteById(mid);
	}

}










