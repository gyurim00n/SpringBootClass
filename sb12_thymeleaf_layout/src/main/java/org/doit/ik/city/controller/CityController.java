package org.doit.ik.city.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovy.util.logging.Log4j2;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@lombok.extern.log4j.Log4j2
@RequestMapping("/city")
public class CityController {
   
   @GetMapping("/london")
    public void london(HttpServletRequest request, Model model) {
      model.addAttribute("requestURI", request.getRequestURI());
    }

    @GetMapping("/paris")
    public void paris(HttpServletRequest request, Model model) {    
       model.addAttribute("requestURI", request.getRequestURI());
    }

    @GetMapping("/seoul")
    public void seoul(HttpServletRequest request, Model model) {
       model.addAttribute("requestURI", request.getRequestURI());
    }

}          