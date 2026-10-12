package org.doit.ik;

import java.util.Optional;
import java.util.stream.IntStream;

import org.doit.ik.club.entity.ClubMember;
import org.doit.ik.club.entity.ClubMemberRole;
import org.doit.ik.club.repository.ClubMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ClubMemberTests {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClubMemberRepository clubMemberRepository;
	/*
	@Test
	void InsertDummies() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			
			ClubMember clubMember = ClubMember.builder()
									.email("user"+ i + "@doit.org")
									.name("user" + i)
									.fromSocial(false)
									.password(this.passwordEncoder.encode("1234"))
									.build();
			
			
			//DB에 Save 전 권한 부여
			clubMember.addMemberRole(ClubMemberRole.USER);
			if(i>80) clubMember.addMemberRole(ClubMemberRole.MANAGER);
			if(i>90) clubMember.addMemberRole(ClubMemberRole.ADMIN);
			
			
			
			this.clubMemberRepository.save(clubMember);
		});
	}
	*/
	
	@Test
	public void  testFindByEmail(){
		String email = "user95@doit.org";
		boolean social = false;
		ClubMember clubMember= this.clubMemberRepository.findByEmail(email, social).get();
		
		System.out.println("😑😑 " + clubMember);
		//😑😑 ClubMember(email=user95@doit.org, password=$2a$10$EnN7MVzM5eNllJxnQKOLPeYiyqWGk1kjCz8coMZakqjSE0Ug..l0G, 
		//name=user95, fromSocial=false, roleSet=[USER, MANAGER, ADMIN])
		
	};
}
