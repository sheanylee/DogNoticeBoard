package com.tech.spring.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tech.spring.dto.BoardDto;
import com.tech.spring.dto.UserDto;
import com.tech.spring.vopage.PageVO;
import com.tech.spring.vopage.PageVO_review;


@Repository
public class CustomBoardDaompl implements CustomBoardDao {

	@Inject SqlSession sql;
	
	private String namespace="com.tech.spring.dao.CustomBoardDao";	
	
	//게시판 글개수
	@Override
	public int countBoard(Map<String, String> map) {
		return sql.selectOne(namespace+".countBoard", map);
	}

	//게시판 목록
	@Override
	public List<BoardDto> board(PageVO pageInfo, Map<String, String> map) {
		//페이지 마다 개수대로 불러오기
		RowBounds rowBounds=new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		return sql.selectList(namespace+".board", map, rowBounds);
	}
	
	//게시판 등록
	@Override
	public int write(BoardDto dto) {
		return sql.insert(namespace+".write", dto);
	}

	//게시판 상세
	@Override
	public BoardDto detail(int board_seq) {
		return sql.selectOne(namespace+".detail", board_seq);
	}
	
	//게시판 삭제
	@Override
	public int remove(String board_seq) {
		return sql.update(namespace+".remove", board_seq);
	}

	//게시판 수정 페이지
	@Override
	public BoardDto editPage(int board_seq) {
		return sql.selectOne(namespace+".editPage", board_seq);
	}

	//게시판 수정
	@Override
	public int edit(BoardDto dto) {
		return sql.update(namespace+".edit", dto);
	}

	//게시판 리뷰 목록
	@Override
	public List<BoardDto> review(PageVO_review pageInfo, int board_seq) {
		//페이지 마다 개수대로 불러오기
		RowBounds rowBounds=new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		return sql.selectList(namespace+".review", board_seq, rowBounds);
	}

	//게시판 리뷰 등록
	@Override
	public int writeReview(BoardDto dto) {
		return sql.insert(namespace+".writeReview", dto);
	}

	//게시판 리뷰 삭제
	@Override
	public int deleteReview(int review_seq) {
		return sql.insert(namespace+".deleteReview", review_seq);
	}

	//게시판 리뷰 개수
	@Override
	public int countReview(int board_seq) {
		return sql.selectOne(namespace+".countReview", board_seq);
	}

}
