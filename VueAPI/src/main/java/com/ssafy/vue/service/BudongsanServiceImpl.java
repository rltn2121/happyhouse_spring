package com.ssafy.vue.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.AptInfoDto;
import com.ssafy.vue.dto.Budongsan;
import com.ssafy.vue.dto.BudongsanMarketDto;
import com.ssafy.vue.dto.HouseDealInfoDto;
import com.ssafy.vue.dto.HouseDealParamDto;
import com.ssafy.vue.mapper.BankMapper;
import com.ssafy.vue.mapper.BudongsanMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudongsanServiceImpl implements BudongsanService {
	
	private final BudongsanMapper mapper;
	private final BankMapper bank;
	
	@Override
	public List<BudongsanMarketDto> getMyBudongsan(int userSeq) {
		return mapper.getMyBudongsan(userSeq);
	}

	@Override
	public List<BudongsanMarketDto> findMarketByDong(String dongCode) {
		return mapper.findMarketByDong(dongCode);
	}
	
	@Override
	public List<BudongsanMarketDto> findMarketByApt(int aptCode) {
		return mapper.findMarketByApt(aptCode);
	}

	@Override
	public boolean addMarket(int bdsId) {
		return mapper.addMarket(bdsId) == 1;
	}

	@Override
	public AptInfoDto findAptInfoById(int aptCode) {
		return mapper.findAptInfoById(aptCode);
	}

	@Override
	public List<HouseDealInfoDto> findHouseDealById(int aptCode) {
		return mapper.findHouseDealById(aptCode);
	}

	@Override
	public List<HouseDealInfoDto> findLatestDealAmoutById(int aptCode) {
		return mapper.findLatestDealAmoutById(aptCode);
	}
	
	@Transactional
	@Override
	public String buyBudongsan(HouseDealParamDto dto) {
		int marketId = dto.getMarketId();
		int bdsId = dto.getBdsId();
		int sellerId = dto.getSellerId();
		int buyerId = dto.getBuyerId();
		int price = dto.getDealAmount();
		LocalDate now = LocalDate.now();
		dto.setDealYear(now.getYear());
		dto.setDealMonth(now.getMonthValue());
		dto.setDealDay(now.getDayOfMonth());
		
		int buyerCash = bank.getMyCash(buyerId);
		if(buyerCash < price)
			return "ERR02";
			
		// 1. 부동산 주인을 구매자로 변경
		mapper.updateOwner(buyerId, bdsId);
		
		// 2. 거래 내역 등록
		mapper.insertHouseDeal(dto);
		
		// 3. 마켓에서 삭제
		mapper.deleteMarket(marketId);
		
		// 4. 구매자 계좌에서 출금
		bank.updateUserCash(-price, buyerId);
		
		// 5. 판매자 계좌에 입금
		bank.updateUserCash(price, sellerId);
		
		return "success";
	}

	@Override
	public List<BudongsanMarketDto> findMarketByAptName(String aptName) {
		System.out.println("Service Impl, aptName: " + aptName);
		List<BudongsanMarketDto> list = mapper.findMarketByAptName(aptName);
		System.out.println("Service Impl, list.size(): " + list.size());
		return list;
	}

	@Override
	public boolean deleteMarket(int marketId) {
		return mapper.deleteMarket(marketId)==1;
	}
	
}
