package com.ssafy.vue.service;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.dto.MemberResultDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(int userSeq) throws Exception;
	
	public MemberResultDto userRegister(MemberDto memberDto);
	public MemberResultDto findById(int userSeq);
	public MemberResultDto deleteById(int userSeq);
	public MemberResultDto update(MemberDto memberDto);
}
