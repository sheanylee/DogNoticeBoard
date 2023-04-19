package com.tech.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.spring.dao.CustomBoardDao;
import com.tech.spring.dao.CustomUserDao;
import com.tech.spring.dto.BoardDto;
import com.tech.spring.dto.RecommendDto;
import com.tech.spring.dto.UserDto;
import com.tech.spring.vopage.PageVO;
import com.tech.spring.vopage.PageVO_review;

@Service
public class CustomBoardServicempl implements CustomBoardService{

	@Autowired private CustomBoardDao dao;
	
	//게시판 글개수
	@Override
	public int countBoard(Map<String, String> map) {
		return dao.countBoard(map);
	}

	//게시판 목록
	@Override
	public List<BoardDto> board(PageVO pageInfo, Map<String, String> map) {
		return dao.board(pageInfo, map);
	}
	
	//게시판 등록
	@Override
	public int write(BoardDto dto) {
		return dao.write(dto);
	}

	//게시판 상세
	@Override
	public BoardDto detail(int board_seq) {
		return dao.detail(board_seq);
	}
	
	//게시판 삭제
	@Override
	public int remove(String board_seq) {
		return dao.remove(board_seq);
	}

	//게시판 수정 페이지
	@Override
	public BoardDto editPage(int board_seq) {
		return dao.editPage(board_seq);
	}

	//게시판 수정
	@Override
	public int edit(BoardDto dto) {
		return dao.edit(dto);
	}

	//게시판 리뷰 목록
	@Override
	public List<BoardDto> review(PageVO_review pageInfo, int board_seq) {
		return dao.review(pageInfo, board_seq);
	}

	//게시판 리뷰 등록
	@Override
	public int writeReview(BoardDto dto) {
		return dao.writeReview(dto);
	}

	
	//게시판 리뷰 삭제
	@Override
	public int deleteReview(int review_seq) {
		return dao.deleteReview(review_seq);
	}

	//게시판 리뷰 개수
	@Override
	public int countReview(int board_seq) {
		return dao.countReview(board_seq);
	}

	//게시판 추천
	@Override
	public int recommend(RecommendDto dto) {
		return dao.recommend(dto);
	}

	//게시판 추천 확인
	@Override
	public int countRecommend(RecommendDto dto) {
		return dao.countRecommend(dto);
	}
	
}
