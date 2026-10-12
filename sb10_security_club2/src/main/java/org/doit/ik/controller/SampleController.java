package org.doit.ik.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/sample")
//@PreAuthorize("isAuthenticated()") //이 안의 컨텐츠는 모두 로그인이 필요하다!
public class SampleController {

	@GetMapping(value = {"/all"})
	public void exAll() {
		log.info("~~~SampleController.exAll()");
		 
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = {"/member"})
	public void exMember() {
		log.info("~~~SampleController.exMember()");
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = {"/admin"})
	public void exAdmin() {
		log.info("~~~SampleController.exAdmin()");
		
	}
	
}
