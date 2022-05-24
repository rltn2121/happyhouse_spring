package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.FavoriteDto;
import com.ssafy.vue.dto.FavoriteParamDto;
import com.ssafy.vue.service.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;
	
	private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@GetMapping("/users/{userSeq}")
	public ResponseEntity<List<FavoriteDto>> selectFavorite(@PathVariable int userSeq){
		logger.debug("selectFavorite - 호출");
		
		return new ResponseEntity<List<FavoriteDto>>(favoriteService.selectFavorite(userSeq), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> toggleFavorite(@RequestBody FavoriteParamDto dto){
		logger.debug("toggleFavorite - 호출");
		HttpStatus status = HttpStatus.OK;
		String message = SUCCESS;
		
		int aptCode = dto.getAptCode();
		int userSeq = dto.getUserSeq();
		if(!favoriteService.toggleFavorite(aptCode, userSeq)) {
			message = FAIL;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(message, status);
	}
}
