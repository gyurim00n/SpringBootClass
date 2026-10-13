package org.doit.ik.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

	//          http://localhost/login 요청
	@GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    //  http://localhost/signup 요청
	@GetMapping("/signup")
    public String signup() {
        return "/member/signup";
    }

}
