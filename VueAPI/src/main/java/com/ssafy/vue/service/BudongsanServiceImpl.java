package com.ssafy.vue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.AptInfoDto;
import com.ssafy.vue.dto.Budongsan;
import com.ssafy.vue.dto.BudongsanMarketDto;
import com.ssafy.vue.dto.HouseDealInfoDto;
import com.ssafy.vue.mapper.BudongsanMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudongsanServiceImpl implements BudongsanService {
	
	private final BudongsanMapper mapper;
	
	@Override
	public List<Budongsan> getMyBudongsan(int userSeq) {
		return mapper.getMyBudongsan(userSeq);
	}

	@Override
	public List<BudongsanMarketDto> findMarket(String dongCode, int aptCode) {
		return mapper.findMarket(dongCode, aptCode);
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

	
	
	
}
