package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.FavoriteDto;
import com.ssafy.vue.mapper.FavoriteMapper;
@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Override
	public List<FavoriteDto> selectFavorite(int userSeq) {
		return favoriteMapper.selectFavorite(userSeq);

	}

	@Override
	public boolean insertFavorite(FavoriteDto favorite) {
		return favoriteMapper.insertFavorite(favorite) == 1;
	}

}
