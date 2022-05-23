package com.ssafy.vue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.dto.MemberResultDto;
import com.ssafy.vue.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		
//		return memberMapper.login(memberDto); 
		MemberDto result = memberMapper.login(memberDto);
		return result;
	}

	@Override
	public MemberDto userInfo(int userSeq) throws Exception {
		return memberMapper.userInfo(userSeq);
	}

	@Override
	public MemberResultDto userRegister(MemberDto memberDto) {
		MemberResultDto membeResultDto = new MemberResultDto();
		if( memberMapper.userRegister(memberDto) == 1 ) {
			membeResultDto.setResult(SUCCESS);
		}else {
			membeResultDto.setResult(FAIL);
		}
		return membeResultDto;
	}

	@Override
	public MemberResultDto findById(int userSeq) {
		MemberResultDto result = new MemberResultDto();
		MemberDto dto = memberMapper.findById(userSeq);
		if(dto != null) {
			result.setResult(SUCCESS);
			result.setDto(dto);
		}else
			result.setResult(FAIL);
		return result;
	}

	@Override
	public MemberResultDto deleteById(int userSeq) {		
		MemberResultDto result = new MemberResultDto();
		if(memberMapper.deleteById(userSeq) == 1) {
			result.setResult(SUCCESS);
		}else
			result.setResult(FAIL);
		return result;
	}

	@Override
	public MemberResultDto update(MemberDto memberDto) {
		MemberResultDto result = new MemberResultDto();
		if(memberMapper.update(memberDto) == 1) {
			System.out.println("서비스 -> 수정 성공");
			result.setResult(SUCCESS);
		}
		else
			result.setResult(FAIL);
		return result;
	}

}
