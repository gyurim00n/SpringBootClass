package org.doit.ik.controller;

import java.lang.ProcessBuilder.Redirect;

import org.doit.ik.exception.BusinessException;
import org.doit.ik.persistence.DeptMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class DeptController {
	
	private final DeptMapper deptMapper;
	/*//[1]
	@GetMapping("/dept/list")
	public void list(Model model){
		log.info("🎶🎶🎶 DeptController.index()...");
		try {
			model.addAttribute("list",this.deptMapper.getDeptList());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}*/
	//[2]
	@GetMapping("/dept/list")
	public void list(Model model) throws Exception{
		log.info("🎶🎶🎶 DeptController.index()...");
		model.addAttribute("list",this.deptMapper.getDeptList());
		
		//강제로 예외 발생 가정
		//throw new RuntimeException("😒😒😒강제로 발생시킨 RuntimeException입니다.");
		throw new BusinessException("😒😒😒강제로 발생시킨 BusinessException입니다.");
		// :
		// :
	}
	
}
