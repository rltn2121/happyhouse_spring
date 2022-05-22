package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Favorite;

@Mapper
public interface FavoriteMapper {
	public List<Favorite> selectFavorite(int userSeq);
	public int insertFavorite(Favorite favorite);

}
