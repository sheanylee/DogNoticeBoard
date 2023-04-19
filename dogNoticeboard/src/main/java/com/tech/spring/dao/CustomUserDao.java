package com.tech.spring.dao;

import java.util.List;
import java.util.Map;

import com.tech.spring.dto.UserDto;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface CustomUserDao {

	//회원가입
	int register(Map<String, Object> map);
	
	//아이디중복체크
	int idcheck(String custom_user_nick);

	//로그인
	UserDto login(UserDto inputDto);

	//비밀번호찾기
	UserDto forgetPswd(UserDto inputDto);

	//비밀번호 임시변경
	int randomPswd(UserDto inputDto);

	//유저 목록
	List<UserDto> user();

	//카카오 회원가입
	int kakaoRegister(Map<String, Object> map);
}
