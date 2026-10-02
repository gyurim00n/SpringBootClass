package org.doit.ik.memo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/memo")
//@Log4j2 : org.apache.logging.log4j.Logger 현재 SB에서는 사용권장!!
//@Log4j : org.apache.log4j.logger 거의 사용X 
@RequiredArgsConstructor
public class MemoController {
	private final MemoRepository memoRepository;
	
	//		/memo/list/{페이지번호} 컨트롤러 메서드 구현 
	@GetMapping("/list/{pageNumber}")
	private String memoList(@PathVariable("pageNumber") int pageNumber, Model model, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
	      log.info("😍 MemoController.memoList... pageNumber : " + pageNumber);

	      Sort sort = Sort.by("mno").descending();
	      Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
	      
	      Page<Memo> mPage = this.memoRepository.findAll(pageable);
	      
	      long totalElements = mPage.getTotalElements(); 
	       
	      boolean next = mPage.hasNext();
	      boolean prev = mPage.hasPrevious();
	      
	      // 1) 
	      // model.addAttribute("mPage",mPage);
	      List<Memo> mList = mPage.getContent();
	      model.addAttribute("mList",mList);
	      model.addAttribute("mPage",mPage);
	      model.addAttribute("totalElements", totalElements);
	      
	      model.addAttribute("next", next); // boolean 다음 페이지 유무
	      model.addAttribute("prev", prev); // boolean 이전 페이지 유무
	      
	      // 2) 페이징 처리
	      int pageBlock = 10; 
	      int currentBlock = (pageNumber - 1 ) / pageBlock; // 1 ~ 10 페이지 0블럭 - 11 ~ 20 페이지 1블럭 ~~
	      long totalPages = mPage.getTotalPages();
	      
	      int startPage = currentBlock * pageBlock + 1;
	      int endPage = startPage + pageBlock - 1;
	      if (endPage > mPage.getTotalPages()) endPage = mPage.getTotalPages();
	      // 이전, 다음
	      boolean hasPreviousBlock = startPage > 1;
	      boolean hasNextBlock = endPage < totalPages;
	      
	      model.addAttribute("currentPage", pageNumber);
	      model.addAttribute("totalPages", totalPages);
	      model.addAttribute("startPage", startPage);
	      model.addAttribute("endPage", endPage);
	      model.addAttribute("hasPreviousBlock", hasPreviousBlock);
	      model.addAttribute("hasNextBlock", hasNextBlock);
	      
	      return "/memo/list";

	}
		
		
}
