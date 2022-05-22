package com.ssafy.vue.dto;

import lombok.Data;

@Data
public class BudongsanMarketDto {
	private int marketId;
	private int bdsId;
	private int aptCode;
	private String aptName;
	private String address;
	private double area;
	private int floor;
	private int price;
	private int type;
	private int ownerId;
	private String dongCode;
}