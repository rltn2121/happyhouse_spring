package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.vue.dto.Favorite;
import com.ssafy.vue.mapper.FavoriteMapper;

public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Override
	public List<Favorite> selectFavorite(int userSeq) {
		return favoriteMapper.selectFavorite(userSeq);

	}

	@Override
	public boolean insertFavorite(Favorite favorite) {
		return favoriteMapper.insertFavorite(favorite) == 1;
	}

}
