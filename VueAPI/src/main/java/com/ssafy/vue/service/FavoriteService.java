package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.FavoriteDto;

public interface FavoriteService {
	public List<FavoriteDto> selectFavorite(int userSeq);
	public boolean toggleFavorite(int aptCode, int userSeq);
}
