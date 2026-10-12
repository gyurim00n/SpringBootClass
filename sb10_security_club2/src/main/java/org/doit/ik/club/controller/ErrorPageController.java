package org.doit.ik.club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/error")
public class ErrorPageController {
	
	
	@GetMapping("403")
	public void exAll() {
		log.info(" ///   403          ====");
		
	}
}
