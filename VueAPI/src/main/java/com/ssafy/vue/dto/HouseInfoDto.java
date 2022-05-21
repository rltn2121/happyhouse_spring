package com.ssafy.vue.dto;

import lombok.Data;

@Data
public class HouseInfoDto {
	private int aptCode;
	private String aptName;
	private String dongCode;
	private String dongName;
	private String sidoName;
	private String gugunName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String recentPrice;
	private double dist;
}
