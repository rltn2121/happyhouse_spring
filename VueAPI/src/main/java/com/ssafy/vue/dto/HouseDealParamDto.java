package com.ssafy.vue.dto;

import lombok.Data;

@Data
public class HouseDealParamDto {
	private int marketId;
	private int bdsId;
	private int dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private int rentMoney;
	private int buyerId;
	private int sellerId;
}