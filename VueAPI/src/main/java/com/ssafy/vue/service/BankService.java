package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Bank;
import com.ssafy.vue.dto.MyAccountDto;
import com.ssafy.vue.dto.MyAssetDto;
import com.ssafy.vue.exception.custom.BankAccountDuplicatedException;

public interface BankService {

	// 모든 은행 조회
	public List<Bank> getBankAll();
	// 은행 계좌 개설
	public boolean createBankAccount(int userSeq, int bankId) throws BankAccountDuplicatedException;
	// 내 모든 계좌 조회
	public List<MyAccountDto> getMyAllBankAccount(int userSeq);
	// 대출/상환
	public boolean loanOrRepayment( int price,  int bankId, int userSeq);
	// 예금/출금
	public boolean depositOrWithdraw( int price, int bankId,  int userSeq);
	// 내 자산 조회
	public MyAssetDto getMyAsset(int userSeq);
}
