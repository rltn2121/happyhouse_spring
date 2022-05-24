package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.AptDetailDto;
import com.ssafy.vue.dto.AptInfoDto;
import com.ssafy.vue.dto.BudongsanMarketDto;
import com.ssafy.vue.dto.HouseDealInfoDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.Reply;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.service.BudongsanService;
import com.ssafy.vue.service.HouseMapService;
import com.ssafy.vue.service.ReplyService;

@RestController
@RequestMapping("/map")
//@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	@Autowired
	private BudongsanService service;
	@Autowired
	private ReplyService replyService; 
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
//		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
//		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(
			@RequestParam("dong") String dong, 
			@RequestParam("myLng") String myLng,
			@RequestParam("myLat") String myLat
			) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong, Double.parseDouble(myLat), Double.parseDouble(myLng)), HttpStatus.OK);
	}
//	@GetMapping("/apts/{aptCode}")
//	public ResponseEntity<List<HouseDealInfoDto>> aptDetail(@PathVariable int aptCode) throws SQLException {
//		List<HouseDealInfoDto> list = haHouseMapService.findById(aptCode);
//		if(list != null)
//			return new ResponseEntity<List<HouseDealInfoDto>>(list, HttpStatus.OK);
//		else
//			return new ResponseEntity<List<HouseDealInfoDto>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@GetMapping("/apt/{aptCode}")
	public ResponseEntity<AptDetailDto> getAptDetail(@PathVariable int aptCode){
		AptInfoDto aptInfoDto = service.findAptInfoById(aptCode);
		List<BudongsanMarketDto> budongsanMarketList = service.findMarketByApt(aptCode);
		List<HouseDealInfoDto> dealInfoList = service.findHouseDealById(aptCode);
		System.out.println(dealInfoList.size());
		List<HouseDealInfoDto> latestDealInfoList = service.findLatestDealAmoutById(aptCode);
		List<Reply> replyList = replyService.selectReply(aptCode);

		AptDetailDto dto = new AptDetailDto();
		dto.setAptInfoDto(aptInfoDto);
		dto.setBudongsanMarketList(budongsanMarketList);
		dto.setDealInfoList(dealInfoList);
		dto.setLatestDealInfoList(latestDealInfoList);
		dto.setReplyList(replyList);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
}
