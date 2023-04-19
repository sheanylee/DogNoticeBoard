package com.tech.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tech.spring.dto.UserDto;
import com.tech.spring.service.CustomUserService;

@Controller
@RequestMapping("/user")
public class CustomUserController {
	
	@Autowired CustomUserService service;

	//4월10일 카카오회원가입
	@RequestMapping(value="/kakaoRegister", method=RequestMethod.POST)
	@ResponseBody
	public String kakaoRegister(@RequestParam Map<String, Object> map) {
		System.out.println("카카오회원가입");
		System.out.println(map);
		int success=service.kakaoRegister(map);
		System.out.println("성공:"+success);
		return null;
	}	
	
	//4월12일 카카오로그인
	@RequestMapping(value="/kakaoLogin", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView kakaoLogin(@RequestParam Map<String, Object> map,
							  @ModelAttribute("inputDto") UserDto inputDto,
							  HttpSession session,
							  HttpServletRequest request,
							  HttpServletResponse response,
							  RedirectAttributes redirect) throws IOException {
		System.out.println("로그인");
		System.out.println(map.get("custom_user_nick")+"/"+map.get("custom_user_pswd"));
		
		inputDto.setCustom_user_nick((String) map.get("custom_user_nick"));
		inputDto.setCustom_user_pswd((String) map.get("custom_user_pswd"));
		
		ModelAndView mav=new ModelAndView();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		UserDto getDto=service.login(inputDto);
		if(getDto != null) {
			System.out.println("받아오는 이름:"+getDto.getCustom_user_name());
			session = request.getSession();
			session.setAttribute("getDto", getDto);
			session.setAttribute("login", true);
			mav.setViewName("redirect:/main");
		}
		else {
			redirect.addAttribute("result", "loginFail");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('회원정보가 없습니다.');");
			writer.println("</script>");
			writer.flush();
			mav.setViewName("/user/login");
		}
		return mav;
	}
	
	//1월2일 회원가입 페이지
	@RequestMapping("/register")
	public String register() {
		return "/user/register";
	}
	
	//1월3일 회원가입
	@RequestMapping(value="/registerToLogin", method=RequestMethod.POST)
	@ResponseBody
	public String register(@RequestParam Map<String, Object> map) {
		System.out.println("회원가입");
		System.out.println(map);
		int success=service.register(map);
		System.out.println("성공:"+success);
		return null;
	}
	
	//1월4일 아이디 중복체크
	@RequestMapping(value="/idcheck", method=RequestMethod.POST)
	@ResponseBody
	public int idcheck(@RequestBody String custom_user_nick) {
		System.out.println("controller:"+custom_user_nick);
	    int cnt = service.idcheck(custom_user_nick);
	    System.out.println("아이디중복이면1:"+cnt);
	    return cnt;
	}
	
	//1월5일 로그인 페이지
	@RequestMapping("/login")
	public String login() {
		return "/user/login";
	}

	//1월8일 로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("inputDto") UserDto inputDto,
							  HttpSession session,
							  HttpServletRequest request,
							  HttpServletResponse response,
							  RedirectAttributes redirect) throws IOException {
		System.out.println("로그인");
		System.out.println("입력한 아이디와 비밀번호:"+inputDto.getCustom_user_nick()+"/"+inputDto.getCustom_user_pswd());
		
		ModelAndView mav=new ModelAndView();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		UserDto getDto=service.login(inputDto);
		if(getDto != null) {
			System.out.println("받아오는 이름:"+getDto.getCustom_user_name());
			session = request.getSession();
			session.setAttribute("getDto", getDto);
			session.setAttribute("login", true);
			mav.setViewName("redirect:/main");
		}
		else {
			redirect.addAttribute("result", "loginFail");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('회원정보가 없습니다.');");
			writer.println("</script>");
			writer.flush();
			mav.setViewName("/user/login");
		}
		return mav;
	}
	
	//1월8일 로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session,
							   HttpServletRequest request) {
		System.out.println("로그아웃");
		
		ModelAndView mav=new ModelAndView();
		session = request.getSession();
		session.removeAttribute("getDto");
		session.removeAttribute("login");
		mav.setViewName("redirect:/main");
		return mav;
	}
	
	//1월15일 비밀번호찾기 페이지
	@RequestMapping(value="/forgetPswd", method=RequestMethod.GET)
	public String forgetPswd() {
		return "/user/forgetPswd";
	}
	
	//1월15일 비밀번호 임시발급
	@RequestMapping(value="/forgetPswd", method=RequestMethod.POST)
	public String forgetPswd(@ModelAttribute("inputDto") @Valid UserDto inputDto,
							 HttpServletResponse response) throws IOException {
		System.out.println("입력한 아이디와 전화번호:"+inputDto.getCustom_user_nick()+"/"+inputDto.getCustom_user_phone());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		UserDto getDto = service.forgetPswd(inputDto);
		if(getDto == null) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('회원정보가 없습니다.');");
			writer.println("location.href='forgetPswd'");
			writer.println("</script>");
			writer.flush();
		}
		else {
			//임시비밀번호 생성
			String randomPswd = "";
			for (int i = 0; i < 12; i++) {
				randomPswd += (char) ((Math.random() * 26) + 97);
			}
			getDto.setCustom_user_pswd(randomPswd);
			//임시비밀번호 변경
			int result = service.randomPswd(getDto);
			System.out.println("임시비밀번호변경성공시1:"+result);
			//임시비밀번호 메일발송
			sendEmail(getDto, "findpw");
			writer.println("<script type='text/javascript'>");
			writer.print("alert('이메일로 임시비밀번호가 발송되었습니다.');");
			writer.println("location.href='login'");
			writer.println("</script>");
			writer.flush();
		}
		return "/user/login";
	}
	//임시비밀번호 메일발송
	private void sendEmail(UserDto getDto, String div) {
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "sihyunjava@gmail.com";
		String hostSMTPpwd = "bpynsfsbqbmkhliv";
		
		String fromEmail = "customedu@gmail.com";
		String fromName = "customedu";
		String subject = "";
		String msg = "";
		
		if(div.equals("findpw")) {
			subject = "[customedu] 임시 비밀번호 안내";
			msg += "<div align='center'>";
			msg += "<h3>"+getDto.getCustom_user_nick()+"님의 임시 비밀번호입니다.</h3>";
			msg += "임시 비밀번호 : "+getDto.getCustom_user_pswd();
			msg += "</div>";
		}
		System.out.println("가입시입력한메일:"+getDto.getCustom_user_email());
		String mail = getDto.getCustom_user_email();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSLOnConnect(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);
			
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.addTo(mail, getDto.getCustom_user_email());
			email.setFrom(fromEmail, fromName);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
		
	}	
}