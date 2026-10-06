package org.doit.ik.guestbook.controller;

import org.doit.ik.guestbook.dto.GuestbookDTO;
import org.doit.ik.guestbook.dto.PageRequestDTO;
import org.doit.ik.guestbook.dto.PageResultDTO;
import org.doit.ik.guestbook.entity.Guestbook;
import org.doit.ik.guestbook.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// [2]	 방명록 등록 컨트롤러 메서드 
	// http://localhost/guestbook/register
	@GetMapping("/register")
	public void regiser() {
		log.info("~~~~~~~~~~~~~~~GuestbookController.register()~~ + GET");
	}
	
	// [2-2]	 방명록 등록 컨트롤러 메서드 
	// http://localhost/guestbook/register + POST
	@PostMapping("/register")
	public String regiser(GuestbookDTO guestbookDTO, RedirectAttributes rttr) {
		log.info("~~~~~~~~~~~~~~~GuestbookController.register()~~ + POST");
		
		Long gno= this.guestbookService.register(guestbookDTO);
		rttr.addFlashAttribute("msg", gno);
		return "redirect:/guestbook/list";
	}
	
	// [3]	 방명록 상세보기 컨트롤러 메서드 
	// http://localhost/guestbook/read/gno=300&page=??&size=10&검색조건,검색어..~
	@GetMapping(value = {"/read", "modify"})
	public void read(@RequestParam("gno") long gno
			, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO
			, Model model) {
		log.info("~~~~~~~~~~~~~~~GuestbookController.read()~~ + GET");
		GuestbookDTO guestbookDTO = this.guestbookService.read(gno);
		model.addAttribute("dto", guestbookDTO);
	}
	
	// [4]	 방명록 수정 컨트롤러 메서드 
	// http://localhost/guestbook/modify/gno=300&page=??&size=10&검색조건,검색어..~
	@PostMapping("/modify")
	public String modify(GuestbookDTO guestbookDTO
			, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO
			, RedirectAttributes rttr) {
		log.info("~~~~~~~~~~~~~~~GuestbookController.modify()~~ + POST");
		this.guestbookService.modify(guestbookDTO);
		
		//1. input value 문자열(String) -> GuestbookDTO.LocalDateTime 변환 x
		
		
		//?gno=10 
		rttr.addAttribute("gno", guestbookDTO.getGno());
//		rttr.addFlashAttribute("page", pageRequestDTO.getPage());
//		rttr.addFlashAttribute("size", pageRequestDTO.getSize());
//		rttr.addFlashAttribute("typ", guestbookDTO.getGno());
//		rttr.addFlashAttribute("gno", guestbookDTO.getGno());
		return "redirect:/guestbook/read";
		
	}
	
	// [5]	 방명록 삭제 컨트롤러 메서드 
	// http://localhost/guestbook/remove/gno=300&page=??&size=10&검색조건,검색어..~
	@PostMapping("/remove")
	public String remove(@RequestParam("gno") long gno
			, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO
			, RedirectAttributes rttr) {
		log.info("~~~~~~~~~~~~~~~GuestbookController.remove()~~ + POST");
		this.guestbookService.remove(gno);
		
		//1. input value 문자열(String) -> GuestbookDTO.LocalDateTime 변환 x
		
		rttr.addFlashAttribute("msg", gno);
		return "redirect:/guestbook/list";
		
	}
}
