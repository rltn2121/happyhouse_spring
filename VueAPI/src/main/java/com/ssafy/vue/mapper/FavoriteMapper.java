package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.FavoriteDto;

@Mapper
public interface FavoriteMapper {
	public List<FavoriteDto> selectFavorite(int userSeq);
	public int insertFavorite(int bdsId, int userSeq);
	public int toggleFavorite(int bdsId, int userSeq);
}
