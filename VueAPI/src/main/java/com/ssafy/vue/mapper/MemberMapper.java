package com.ssafy.vue.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(int userSeq) throws SQLException;

	public int userRegister(MemberDto memberDto);
	public MemberDto findById(int userSeq);
	public int deleteById(int userSeq);
	public int update(MemberDto memberDto);
	
	
}