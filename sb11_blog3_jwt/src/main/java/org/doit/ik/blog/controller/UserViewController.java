package org.doit.ik.blog.controller;

import org.doit.ik.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserViewController {
	
	
	//		http://localhost/user	회원가입 [저장]
	@GetMapping("/login")
    public String login() {
//        Long id = userService.save(request);
        return "/member/login";
    }

	//		http://localhost/logout		[로그아웃]
    @GetMapping("/signup")
    public String signup() {
//        new SecurityContextLogoutHandler().logout(request, response,
//                SecurityContextHolder.getContext().getAuthentication());
        return "/member/signup";
    }
}
