package com.ssafy.vue.dto;

import lombok.Data;

@Data
public class Bank {
	private int bankId;
	private String name;
	private double loanInterest;
	private double depositInterest;
}