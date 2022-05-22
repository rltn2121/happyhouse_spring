package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.vue.dto.Bank;
import com.ssafy.vue.dto.MyAccountDto;
import com.ssafy.vue.exception.custom.BankAccountDuplicatedException;
@Mapper
public interface BankMapper {
	// 모든 은행 조회
	public List<Bank> getBankAll();
	// 은행 계좌 개설
	public int createBankAccount(int userSeq, int bankId) throws BankAccountDuplicatedException;
	// 내 모든 계좌 조회
	public List<MyAccountDto> getMyAllBankAccount(int userSeq);
	// 대출/상환
	public int loanOrRepayment(int price, int bankId, int userSeq);
	// 예금/출금
	public int depositOrWithdraw(int price, int bankId, int userSeq);
}