package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Bank;
import com.ssafy.vue.dto.MyAccountDto;
import com.ssafy.vue.dto.MyAssetDto;
@Mapper
public interface BankMapper {
	// 모든 은행 조회
	public List<Bank> getBankAll();
	// 은행 계좌 개설
	public int createBankAccount(int userSeq, int bankId) throws SQLException;
	// 내 모든 계좌 조회
	public List<MyAccountDto> getMyAllBankAccount(int userSeq);
	// 대출/상환
	public int loanOrRepayment(int price, int bankId, int userSeq);
	// 예금/출금
	public int depositOrWithdraw(int price, int bankId, int userSeq);
	
	// 돈 이체하기
	public int updateUserCash(int price, int userSeq);
	
	// 내 자산 조회
	public MyAssetDto getMyAsset(int userSeq);
	
	
	// 내 현금 조회
	public int getMyCash(int userSeq);
	
	// 특정 은행 계좌 조회
	public MyAccountDto getMyBankAccount(int bankId, int userSeq);
	
	public int getSalary(int userSeq);
	public int updateBudongsanPrice();
	public int updateBankTransactionInterest(int userSeq);
}