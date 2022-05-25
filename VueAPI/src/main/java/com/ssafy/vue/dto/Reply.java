package com.ssafy.vue.dto;

import lombok.Data;

@Data
public class Reply {
	private int aptId;
	private int userSeq;
	private String username;
	private String content;
	private int score;
	private int replySeq;
}
