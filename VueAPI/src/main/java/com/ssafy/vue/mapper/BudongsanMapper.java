package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.AptInfoDto;
import com.ssafy.vue.dto.Budongsan;
import com.ssafy.vue.dto.BudongsanMarketDto;
import com.ssafy.vue.dto.HouseDealInfoDto;
import com.ssafy.vue.dto.HouseDealParamDto;
@Mapper
public interface BudongsanMapper {
//	내 부동산 목록 조회
	public List<BudongsanMarketDto> getMyBudongsan(int userSeq);
//	부동산 매물 조회
	public List<BudongsanMarketDto> findMarketByDong(String dongCode);
	public List<BudongsanMarketDto> findMarketByApt(int aptCode);
//	부동산 마켓에 등록
	public int addMarket(int bdsId);
	
//	부동산 구매하기 - 주인 수정
	public int updateOwner(int ownerId, int bdsId);
//	부동산 구매하기 - 거래 내역 등록
	public int insertHouseDeal(HouseDealParamDto dto);
	
//	판매자 돈 입금
//	구매자 돈 출금
	
//	1. 아파트 정보 조회
	public AptInfoDto findAptInfoById(int aptCode);

//	2. 현재 아파트 매물 조회
//	-> 부동산 매물 조회로 대체
	
//	3. 현재 아파트 거래내역 조회
	public List<HouseDealInfoDto> findHouseDealById(int aptCode);
	
//	4. 현재 아파트 평당 최신 거래가 조회
	public List<HouseDealInfoDto> findLatestDealAmoutById(int aptCode);
	
	// 마켓에서 부동산 제거
	public int deleteMarket(int marketId);
	
	// 아파트 이름으로 부동산 찾기
	public List<BudongsanMarketDto> findMarketByAptName(String aptName);
}