package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Favorite;

public interface FavoriteService {
	public List<Favorite> selectFavorite(int userSeq);
	public boolean insertFavorite(Favorite favorite);

}
