package com.ssafy.vue.dto;

import lombok.Data;

@Data
public class FavoriteDto {
	private int userSeq;
	
	private int bdsId; 
	private int aptCode;
    private String aptName;
    private String address;
    private int price;
    private double area;
    private int floor; 
    private int type; 
    private int status;
    private int ownerId;
}
