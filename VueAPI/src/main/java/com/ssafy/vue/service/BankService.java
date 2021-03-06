package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.Bank;
import com.ssafy.vue.dto.MyAccountDto;
import com.ssafy.vue.dto.MyAssetDto;

public interface BankService {

	// 모든 은행 조회
	public List<Bank> getBankAll();
	// 은행 계좌 개설
	public int createBankAccount(int userSeq, int bankId) throws SQLException;
	// 내 모든 계좌 조회
	public List<MyAccountDto> getMyAllBankAccount(int userSeq);
	// 대출/상환
	public String loanOrRepayment( int price,  int bankId, int userSeq);
	// 예금/출금
	public String depositOrWithdraw( int price, int bankId,  int userSeq);
	// 내 자산 조회
	public MyAssetDto getMyAsset(int userSeq);
	
	public boolean updateUserAsset(int userSeq);
}
