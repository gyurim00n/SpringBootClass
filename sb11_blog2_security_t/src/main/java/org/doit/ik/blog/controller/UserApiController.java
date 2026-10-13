package org.doit.ik.blog.controller;

import org.doit.ik.blog.dto.AddUserRequest;
import org.doit.ik.blog.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserApiController {
	
	private final UserService userService;
	
	//       http://localhost/user   회원가입  [저장]
	@PostMapping("/user")
    public String signup(AddUserRequest request) {
        Long id = userService.save(request);
        return "redirect:/login";
    }

    //  http://localhost/logout   [로그아웃]
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}
