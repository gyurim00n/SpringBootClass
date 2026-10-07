package org.doit.ik.board.controller;

import org.doit.ik.board.dto.BoardDTO;
import org.doit.ik.board.dto.PageRequestDTO;
import org.doit.ik.board.dto.PageResultDTO;
import org.doit.ik.board.repository.BoardRepository;
import org.doit.ik.board.service.BoardService;
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
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardRepository boardRepository;
	private final BoardService boardService;


	//[1]게시글 목록
	//http://localhost/board/list?page=1&size=10&type=t&keyword=검색어
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		
		PageResultDTO<BoardDTO, Object[]> result =  this.boardService.getList(pageRequestDTO);
		log.info("BoardController.list()..." + pageRequestDTO);
		
		model.addAttribute("result", result);
	}
	
	
	//[2]게시글 등록
	@GetMapping("/register")
	public void register() {
		log.info("BoardController.register()...GET");
	}
	
	//[2-2]게시글 등록
	@PostMapping("/register")
	public String register(BoardDTO boardDTO, RedirectAttributes rttr) {
		log.info("BoardController.register()...POST");
		
		Long bno = this.boardService.register(boardDTO);
		rttr.addFlashAttribute("msg", bno);
		return "redirect:/board/list";
	}
	
	//[3] 게시글 상세보기
	// /board/read(bno=
	// /board/modify(bno
	@GetMapping(value = {"/read", "modify"})
	public void readAndModify(@RequestParam("bno") Long bno
					, Model model
					, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO) {
		log.info("BoardController.read()...");
		BoardDTO boardDTO = this.boardService.get(bno);
		model.addAttribute("dto", boardDTO);
		
	}
	
	//[4] 게시글 수정
	@PostMapping(value = {"/modify"})
	public String modify(BoardDTO boardDTO, RedirectAttributes rttr, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO) {
		log.info("BoardController.modify()...POST");
		this.boardService.modify(boardDTO);
		
		rttr.addAttribute("page", pageRequestDTO.getPage());
		rttr.addAttribute("type", pageRequestDTO.getType());
		rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
		rttr.addAttribute("bno", boardDTO.getBno());
		
		
		//1회성 전달
		rttr.addFlashAttribute("msg", boardDTO.getBno());

		return "redirect:/board/read";
		
	}
	
	//[5] 게시글 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("BoardController.delete ()...POST");
		
		this.boardService.removeWithReplies(bno);
		
		//1회성 전달
		rttr.addFlashAttribute("msg", bno);
		
		return "redirect:/board/list";
	}
}
