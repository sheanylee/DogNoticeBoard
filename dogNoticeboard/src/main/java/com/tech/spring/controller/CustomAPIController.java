package com.tech.spring.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.Response;
import com.tech.spring.dto.APIDto;
import com.tech.spring.service.CustomAPIService;

@Controller
public class CustomAPIController {

	@Autowired CustomAPIService service;
	
	//1월17일 api 페이지
	@RequestMapping("/api")
	public String api() {
		return "/api";
	}
	
	//1월18일 api
	@RequestMapping(value = "/chinese", produces = "application/text; charset=UTF-8")//2월15일 중문깨짐 해결
	@ResponseBody
	public String chinese(@RequestParam(value = "korean", defaultValue = "-")String korean) {		
		APIDto dto = new APIDto();
		dto.setKorean(korean);
		String result = service.getChinese(dto);
		System.out.println("컨트롤러-중문:"+result);
		return result;
	}
	
	//1월18일 api
	@RequestMapping(value = "/english", produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String english(@RequestParam(value = "korean", defaultValue = "-")String korean) {		
		APIDto dto = new APIDto();
		dto.setKorean(korean);
		String result = service.getEnglish(dto);
		System.out.println("컨트롤러-영문:"+result);
		return result;
	}
	
}