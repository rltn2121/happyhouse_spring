package com.ssafy.vue.dto;

import java.util.List;

import lombok.Data;

@Data
public class AptDetailDto {
	// 아파트 정보
	private AptInfoDto aptInfoDto;
	// 아파트 매물 정보
	private List<BudongsanMarketDto> budongsanMarketList;
	// 거래내역
	private List<HouseDealInfoDto> dealInfoList;
	// 평당 최신 거래내역
	private List<HouseDealInfoDto> latestDealInfoList;
	// 댓글
	private List<Reply> replyList;
}
