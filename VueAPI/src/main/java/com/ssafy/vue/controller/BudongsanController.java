package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.AptInfoDto;
import com.ssafy.vue.dto.Budongsan;
import com.ssafy.vue.dto.BudongsanMarketDto;
import com.ssafy.vue.dto.HouseDealInfoDto;
import com.ssafy.vue.dto.HouseDealParamDto;
import com.ssafy.vue.service.BudongsanService;

@RestController
@RequestMapping("/budongsans")
//@CrossOrigin("*")
public class BudongsanController {
	
	private final Logger logger = LoggerFactory.getLogger(BudongsanController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BudongsanService service;
	
//	내 부동산 목록 조회
	@GetMapping("/{userSeq}")
	public ResponseEntity<List<Budongsan>> getMyBudongsan(@PathVariable int userSeq){
		logger.debug("getMyBudongsan - 호출");
		HttpStatus status = HttpStatus.OK;
		
		List<Budongsan> list = service.getMyBudongsan(userSeq);
		if(list == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(list, status);
	}
		
	
//	부동산 매물 조회 (동별 조회 vs 아파트별 조회)
	@GetMapping("/market")
	public ResponseEntity<List<BudongsanMarketDto>> findMarket(@RequestParam(required=false) String dongCode, @RequestParam(required=false) Integer aptCode){
		logger.debug("findMarket - 호출");
		HttpStatus status = HttpStatus.OK;
		List<BudongsanMarketDto> list = null;
		System.out.println(dongCode);
		System.out.println(aptCode);
		
		if(dongCode != null)
			list = service.findMarketByDong(dongCode);
		else
			list = service.findMarketByApt(aptCode);
		
		if(list == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(list, status);
	}
	
//	부동산 마켓에 등록
	@PostMapping("/market")
	public ResponseEntity<String> addMarket(@RequestBody int bdsId){
		logger.debug("addMarket - 호출");
		HttpStatus status = HttpStatus.OK;
		String message = SUCCESS;
	
		if(!service.addMarket(bdsId)) {
			message = FAIL;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(message, status);
	}

	
	
	/**
	 * 아파트 정보 조회
	 * @param aptCode
	 * @return
	 */
	@GetMapping("/apt/{aptCode}")
	public ResponseEntity<AptInfoDto> findAptInfoById(@PathVariable int aptCode){
		logger.debug("findAptInfoById - 호출");
		HttpStatus status = HttpStatus.OK;
		
		AptInfoDto dto = service.findAptInfoById(aptCode);
		if(dto == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(dto, status);
		
	}
	

//	2. 현재 아파트 매물 조회
//	-> 부동산 매물 조회로 대체
	
	/**
	 * 현재 아파트 거래내역
	 * @param aptCode
	 * @return
	 */
	@GetMapping("/apt/deal/{aptCode}")
	public ResponseEntity<List<HouseDealInfoDto>> findHouseDealById(@PathVariable int aptCode){
		logger.debug("findHouseDealById - 호출");
		HttpStatus status = HttpStatus.OK;
		
		List<HouseDealInfoDto> list = service.findHouseDealById(aptCode);
		if(list == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(list, status);
	}
	
//	4. 현재 아파트 평당 최신 거래가 조회
	@GetMapping("/apt/latest-price/{aptCode}")
	public ResponseEntity<List<HouseDealInfoDto>> findLatestDealAmoutById(@PathVariable int aptCode){
		logger.debug("findLatestDealAmoutById - 호출");
		HttpStatus status = HttpStatus.OK;
		
		List<HouseDealInfoDto> list = service.findLatestDealAmoutById(aptCode);
		if(list == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(list, status);
	}
	/**
	 * 부동산 구매하기
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> buyBudongsan(@RequestBody HouseDealParamDto dto){
		logger.debug("buyBudongsan - 호출");
		HttpStatus status = HttpStatus.OK;
		String message = service.buyBudongsan(dto);
		return new ResponseEntity<>(message, status);
	}
	
//	부동산 매물 조회 (동별 조회 vs 아파트별 조회)
	@GetMapping("/market/apt")
	public ResponseEntity<List<BudongsanMarketDto>> findMarketByAptName(@RequestParam String aptName){
		System.out.println("aptName: " + aptName);
		logger.debug("findMarketByAptName - 호출");
		HttpStatus status = HttpStatus.OK;
		List<BudongsanMarketDto> list = null;
		

		list = service.findMarketByAptName(aptName);
		System.out.println(list.size());
		
		if(list == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(list, status);
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
