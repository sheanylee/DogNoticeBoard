package com.tech.spring.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tech.spring.dto.BoardDto;
import com.tech.spring.dto.RecommendDto;
import com.tech.spring.dto.UserDto;
import com.tech.spring.vopage.PageVO;
import com.tech.spring.vopage.PageVO_review;

public interface CustomBoardDao {

	//게시판 글개수
	int countBoard(Map<String, String> map);

	//게시판 목록
	List<BoardDto> board(PageVO pageInfo, Map<String, String> map);	
	
	//게시판 등록
	int write(BoardDto dto);

	//게시판 상세
	BoardDto detail(int board_seq);
	
	//게시판 삭제
	int remove(String board_seq);

	//게시판 수정 페이지
	BoardDto editPage(int board_seq);

	//게시판 수정
	int edit(BoardDto dto);

	//게시판 리뷰 목록
	List<BoardDto> review(PageVO_review pageInfo, int board_seq);

	//게시판 리뷰 등록
	int writeReview(BoardDto dto);

	//게시판 리뷰 삭제
	int deleteReview(int review_seq);

	//게시판 리뷰 개수
	int countReview(int board_seq);

	//게시판 추천
	int recommend(RecommendDto dto);

	//게시판 추천 확인
	int countRecommend(RecommendDto dto);

}
