package com.ssafy.vue.service;

import java.util.List;

//import org.apache.ibatis.annotations.Param;

import com.ssafy.vue.dto.AptInfoDto;
import com.ssafy.vue.dto.Budongsan;
import com.ssafy.vue.dto.BudongsanMarketDto;
import com.ssafy.vue.dto.HouseDealInfoDto;

public interface BudongsanService {

//	내 부동산 목록 조회
	public List<Budongsan> getMyBudongsan(int userSeq);
	
//	부동산 매물 조회
	public List<BudongsanMarketDto> findMarket(String dongCode, int aptCode);
	
//	부동산 마켓에 등록
	public boolean addMarket(int bdsId);

//	부동산 구매
//	public buyBudongsan();
	
	
//	1. 아파트 정보 조회
	public AptInfoDto findAptInfoById(int aptCode);

//	2. 현재 아파트 매물 조회
//	-> 부동산 매물 조회로 대체
	
//	3. 현재 아파트 거래내역 조회
	public List<HouseDealInfoDto> findHouseDealById(int aptCode);
	
//	4. 현재 아파트 평당 최신 거래가 조회
	public List<HouseDealInfoDto> findLatestDealAmoutById(int aptCode);
}

////	부동산 구매하기 - 주인 수정
//	public int updateOwner(int ownerId, int bdsId);
////	부동산 구매하기 - 거래 내역 등록
//	public int insertHouseDeal(HouseDealParamDto dto);