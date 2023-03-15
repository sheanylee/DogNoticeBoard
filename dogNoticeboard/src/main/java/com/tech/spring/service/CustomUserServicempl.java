package com.tech.spring.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.spring.dao.CustomUserDao;
import com.tech.spring.dto.UserDto;

@Service
public class CustomUserServicempl implements CustomUserService{

	@Autowired private CustomUserDao dao;
	
	//회원가입
	@Override
	public int register(Map<String, Object> map) {
		return dao.register(map);
	}

	//아이디중복체크
	@Override
	public int idcheck(String custom_user_nick) {
		return dao.idcheck(custom_user_nick);
	}

	//로그인
	@Override
	public UserDto login(UserDto inputDto) {
		return dao.login(inputDto);
	}

	//비밀번호찾기
	@Override
	public UserDto forgetPswd(UserDto inputDto) {
		return dao.forgetPswd(inputDto);
	}

	//비밀번호 임시변경
	@Override
	public int randomPswd(UserDto inputDto) {
		return dao.randomPswd(inputDto);
	}

	//유저 목록
	@Override
	public List<UserDto> user() {
		return dao.user();
	}	
}
