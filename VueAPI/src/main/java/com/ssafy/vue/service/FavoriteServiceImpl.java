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
	public boolean toggleFavorite(int aptCode, int userSeq) {
		// 찜 목록에 없는 값은
		int result = favoriteMapper.toggleFavorite(aptCode, userSeq);
		// 찜 목록에 추가하기
		if( result == 0)
			result = favoriteMapper.insertFavorite(aptCode, userSeq);
		return result == 1;
	}

}
