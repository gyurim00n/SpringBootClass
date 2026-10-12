package org.doit.ik.club.security.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.doit.ik.club.entity.ClubMember;
import org.doit.ik.club.entity.ClubMemberRole;
import org.doit.ik.club.repository.ClubMemberRepository;
import org.doit.ik.club.security.dto.ClubAuthMemberDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService{
	
	private final ClubMemberRepository clubMemberRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("ClubUserDetailsService.loadUserByUsername()...username = " + username );
		Optional<ClubMember> result =  this.clubMemberRepository.findByEmail(username, false);
		
		if(!result.isPresent()) {
			throw new UsernameNotFoundException("Check Email or Social");	
		}
		
		ClubMember clubMember = result.get();
		log.info("-".repeat(40));
		log.info(clubMember);
		
		ClubAuthMemberDTO authMemberDTO = new ClubAuthMemberDTO(
				clubMember.getEmail(),
				clubMember.getPassword(),
				clubMember.isFromSocial(),
				//private Set<ClubMemberRole> roleSet = new HashSet<>();
				clubMember.getRoleSet().stream().map(role -> 
								new SimpleGrantedAuthority("ROLE_" + role.name()))
								.collect(Collectors.toSet())
				);
		
		return authMemberDTO;
	}
}
