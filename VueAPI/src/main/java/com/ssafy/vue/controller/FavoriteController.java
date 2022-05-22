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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Favorite;
import com.ssafy.vue.service.FavoriteService;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

//	@Autowired
//	private FavoriteService favoriteService;
//	
//	private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";
//	
//	@GetMapping("{userSeq}")
//	public ResponseEntity<List<Favorite>> selectFavorite(@PathVariable int userSeq){
//		logger.debug("selectFavorite - 호출");
//		
//		return new ResponseEntity<List<Favorite>>(favoriteService.selectFavorite(userSeq), HttpStatus.OK);
//	}
}
