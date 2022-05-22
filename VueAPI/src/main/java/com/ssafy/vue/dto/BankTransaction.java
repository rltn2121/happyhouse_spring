package com.ssafy.vue.dto;

import lombok.Data;

@Data
public class BankTransaction {
	private int bankId;
	private int userSeq;
	private int loan;
	private int deposit;
}