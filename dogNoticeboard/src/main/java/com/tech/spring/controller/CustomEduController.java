package com.tech.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class CustomEduController {
	
	
	@RequestMapping("/main")
	public String main() {
		
		return "/main";
		
	}
	
}