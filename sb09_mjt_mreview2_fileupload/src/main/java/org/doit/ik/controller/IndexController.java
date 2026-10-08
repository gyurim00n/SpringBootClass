package org.doit.ik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2

public class IndexController {
	@GetMapping(value= {"/index", "/"})
	private String index() {
		log.info("----------IndexController.index »£√‚--------------");
		return "index";
	}
}
