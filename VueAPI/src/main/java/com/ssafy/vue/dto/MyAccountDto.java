package com.ssafy.vue.dto;

import lombok.Data;

@Data
public class MyAccountDto {
	private int userSeq;
	private String name;
	private int loan;
	private int deposit;
}