package com.tech.spring.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.tech.spring.vopage.PageVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	private int board_seq; /*게시판 시퀀스*/
	private String board_title;  /*게시판 제목*/
	private String board_text; /*게시판 내용*/
	private String board_writer; /* 게시판 작성자 */
	private Timestamp board_reg_date; /* 게시판 작성시간 */
	private Timestamp board_mod_date; /* 게시판 수정시간 */
	private String board_img_path; /* 게시판 이미지*/
	private String board_del_yn; /*게시판 삭제여부*/
	
	//1월10일
	private int no; /* 게시글 최신 순번 */
	
	//1월16일 파일 업로드
	private MultipartFile img;
	
	//2월27일 리뷰
	private int review_seq; /*리뷰 시퀀스*/
	private String review_writer;  /*리뷰 작성자*/
	private Timestamp review_reg_date; /*리뷰 작성시간*/
	private Timestamp review_mod_date; /* 리뷰 수정시간 */
	private String review_text; /* 리뷰 내용 */
	private String review_del_yn; /*리뷰 삭제여부*/
	private int review_count; /*리뷰 개수*/
	
	private int recommend; //추천
	
}

