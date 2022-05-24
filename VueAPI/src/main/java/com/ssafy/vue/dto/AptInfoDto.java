package com.ssafy.vue.dto;

import java.util.List;

import lombok.Data;

@Data
public class AptInfoDto {

	private int aptCode;
	private String aptName;
	private String address;
	private int buildYear;
}
