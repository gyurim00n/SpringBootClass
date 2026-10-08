package org.doit.ik.mreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
public class UploadTestController {
	
	 @GetMapping("/uploadEx")
	 public void uploadEx() { 
	 }
	 

}
