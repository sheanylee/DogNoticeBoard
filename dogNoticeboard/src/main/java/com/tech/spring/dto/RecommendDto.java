package com.tech.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendDto {
	private int recommend_seq; /*추천시퀀스*/
	private String board_seq;  /*추천글*/
	private String custom_user_nick; /*추천인*/
	
}

