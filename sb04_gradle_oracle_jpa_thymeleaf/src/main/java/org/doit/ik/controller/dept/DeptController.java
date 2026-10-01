package org.doit.ik.controller.dept;

import java.util.List;

import org.doit.ik.dept.Dept;
import org.doit.ik.dept.DeptService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {
	private final DeptService deptService;
	
	//[1] 부서정보 조회
	/*
	@GetMapping("/list")
	public void deptList(Model model){
		log.info("~~~~~~DeptController.deptList()~~~~~~~");
		List<Dept> dList = this.deptService.getDepts();
		model.addAttribute("dList", dList);
		
	}
	*/
	
	//[1-2] 부서정보 조회 + 페이징 처리 (Oracle 12C 부터 사용가능...
		
		@GetMapping("/list")
		public void deptList(Model model){
			log.info("~~~~~~DeptController.deptList()~~~~~~~");
			int pageNumber = 1;
			int pageSize = 10;
			Page<Dept> dPage = this.deptService.getDeptsByPage(pageNumber, pageSize);
			
			List<Dept> dList = dPage.getContent();
			long totalElements = dPage.getTotalElements();//전체 레코드 수를 가져온다.
			int totalPages = dPage.getTotalPages(); //전체 페이지 수
			//int number = dPage.getNumber() + 1; //0부터 시작한다...현제 페이지 수
			//int pageSize = dPage.getSize(); // 페이지 당 몇개?
			boolean next = dPage.hasNext();
			boolean prev = dPage.hasPrevious();
			
			model.addAttribute("dList", dList);
			model.addAttribute("totalElements", totalElements);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("prev", prev);
			model.addAttribute("next", next);
			// Page의 주요 메서드 정리...
			
			
			
		}
		//		/dept/insert?dname=QC&loc=SEOUL
		//						DeptDTO	-> Dept 변환 ->	save(dept 엔터티 객체)
		@GetMapping("/insert")
		public String deptInsert(Dept dept, RedirectAttributes rttr) { //엔티티에 담음!
			log.info("~~~~~~DeptController.deptInsert()~~~~~~~");
			Dept saveDept = this.deptService.saveDept(dept); //saveDept 는 dB에 insert 된 ID까지 존재.
			int deptno = saveDept.getDeptno(); //저장된 부서번호
			rttr.addFlashAttribute("deptno", deptno);
			return "redirect:/dept/list";
		}
		
		//deptno=51,dname='MARKETING',loc='POHANG'
		@GetMapping("/update")
		public String deptUpdate(Dept dept, RedirectAttributes rttr) { //엔티티에 담음!
			log.info("~~~~~~DeptController.deptUpdate()~~~~~~~");
			Dept updateDept = this.deptService.updateDept(dept); //saveDept 는 dB에 insert 된 ID까지 존재.
			int deptno = updateDept.getDeptno(); //저장된 부서번호
			rttr.addFlashAttribute("deptno", deptno);
			return "redirect:/dept/list";
		}
		
		@GetMapping("/delete")
		public String deptDelete(Integer deptno, RedirectAttributes rttr) {
			log.info("~~~~~~DeptController.deptDelete()~~~~~~~");
			this.deptService.deleteDept(deptno); //saveDept 는 dB에 insert 된 ID까지 존재.
		
			rttr.addFlashAttribute("deptno", deptno);
			return "redirect:/dept/list";
		}
		
}
