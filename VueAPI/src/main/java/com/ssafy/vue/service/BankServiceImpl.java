package com.ssafy.vue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.Bank;
import com.ssafy.vue.dto.MyAccountDto;
import com.ssafy.vue.exception.custom.BankAccountDuplicatedException;
import com.ssafy.vue.mapper.BankMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

	// @RequiredArgsConstructor를 이용한
	// 생성자 주입 방식
	private final BankMapper mapper;
	
	@Override
	public List<Bank> getBankAll(){
		return mapper.getBankAll();
	}

	@Override
	public boolean createBankAccount(int userSeq, int bankId) throws BankAccountDuplicatedException{
		return mapper.createBankAccount(userSeq, bankId) == 1;
	}

	@Override
	public List<MyAccountDto> getMyAllBankAccount(int userSeq){
		return mapper.getMyAllBankAccount(userSeq);
	}

	@Override
	public boolean loanOrRepayment(int price, int bankId, int userSeq){
		return mapper.loanOrRepayment(price, bankId, userSeq) == 1;
	}

	@Override
	public boolean depositOrWithdraw(int price, int bankId, int userSeq){
		return mapper.depositOrWithdraw(price, bankId, userSeq) == 1;
	}
	
	
}
