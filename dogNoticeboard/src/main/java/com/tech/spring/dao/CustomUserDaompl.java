package com.tech.spring.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tech.spring.dto.UserDto;


@Repository
public class CustomUserDaompl implements CustomUserDao {

	@Inject SqlSession sql;
	
	private String namespace="com.tech.spring.dao.CustomUserDao";
	
	//회원가입
	@Override
	public int register(Map<String, Object> map) {
		return sql.insert(namespace+".register", map);
	}
	
	//아이디중복체크
	@Override
	public int idcheck(String custom_user_nick) {
		System.out.println("daompl:"+custom_user_nick);
		int result=sql.selectOne(namespace+".idcheck", custom_user_nick);
		System.out.println("checkId:"+result);
		return result;
	}
	
	//로그인
	@Override
	public UserDto login(UserDto inputDto) {
		UserDto getDto=sql.selectOne(namespace+".login", inputDto);
		System.out.println("login:"+getDto);
		return getDto;
	}

	//비밀번호찾기
	@Override
	public UserDto forgetPswd(UserDto inputDto) {
		UserDto getDto=sql.selectOne(namespace+".forgetPswd", inputDto);
		System.out.println("forgetPswd:"+getDto);
		return getDto;
	}

	//비밀번호 임시변경
	@Override
	public int randomPswd(UserDto inputDto) {
		int result=sql.update(namespace+".randomPswd", inputDto);
		return result;
	}

	//유저 목록
	@Override
	public List<UserDto> user() {
		return sql.selectList(namespace+".user");
	}	
}
