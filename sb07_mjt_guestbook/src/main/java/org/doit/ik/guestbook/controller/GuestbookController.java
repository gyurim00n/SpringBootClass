package org.doit.ik.guestbook.controller;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
	private final GuestbookService guestbookService;
	
	// [1]	 방명록 목록 컨트롤러 메서드 
	// http://localhost/guestbook/list?page=1&size=10&검색조건,검색어
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("~~~~~~~~~~~~~~~GuestbookController.list()~~");
		
		PageResultDTO<GuestbookDTO, Guestbook> result = this.guestbookService.getList(pageRequestDTO);
		
		model.addAttribute("result",result);
		
	}
	
	
}
