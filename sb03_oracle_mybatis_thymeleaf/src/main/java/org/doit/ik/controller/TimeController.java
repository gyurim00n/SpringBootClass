package org.doit.ik.controller;

import org.doit.ik.persistence.TimeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/server")
@RequiredArgsConstructor
public class TimeController {
	
	private final TimeMapper timeMapper;
	
	@GetMapping("/time")
	public void serverTime(Model model) {
		log.info("🎶🎶🎶 TimeController.index()...");
		
		String serverTime = this.timeMapper.getTime();
		model.addAttribute("serverTime",serverTime);
	}
	
}
