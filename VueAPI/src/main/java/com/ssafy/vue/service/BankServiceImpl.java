package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.Bank;
import com.ssafy.vue.dto.MyAccountDto;
import com.ssafy.vue.dto.MyAssetDto;
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
	
	public int createBankAccount(int userSeq, int bankId) {
		try {
			int ret = mapper.createBankAccount(userSeq, bankId);
			System.out.println(ret);
			return 1;
		} catch (Exception e) {
			System.out.println("catch");
			return 0;
		}
	}

	@Override
	public List<MyAccountDto> getMyAllBankAccount(int userSeq){
		return mapper.getMyAllBankAccount(userSeq);
	}

	/**
	 * 대출 / 상환
	 * 해야할 것
	 * 1. 대출 시 (은행에 돈 빌림)
	 *   - 대출하기 전에 대출 한도 확인 (10억?) 1,000,000,000 원 -> 100,000 만원
	 *   - 대출 한도 확인 후에 
	 *     (1) 은행에서 대출금 더하기
	 *     (2) 유저에서 현금 더하기
	 * 2. 상환 시 (은행에 돈 갚음)
	 *   - 상환하기 전에 유저 현금 확인 (현금 >= 상환할 액수)
	 *   - 상환해야 할 금액 확인 (대출금 >= 상환할 액수)
	 *   - 위 두 가지 확인 후에
	 *     (1) 은행에서 대출금 빼기
	 *     (2) 유저에서 현금 빼기
	 */
	@Transactional
	@Override
	public String loanOrRepayment(int price, int bankId, int userSeq) throws NullPointerException{
		final int LOAN_LIMIT = 100000;
		MyAccountDto account = mapper.getMyBankAccount(bankId, userSeq);
		int loan = account.getLoan();
		
		// 대출
		if(price > 0) {
			int limit = price + loan;
			
			// 대출 한도 초과
			if(limit > LOAN_LIMIT) 
				return "ERR01"; 
			
			// 대출 가능
			mapper.loanOrRepayment(price, bankId, userSeq);
			mapper.updateUserCash(price, userSeq);
			return "success";
		}
		
		// 상환 (price가 음수로 들어옴)
		else {
			int cash = mapper.getMyCash(userSeq);
			
			// 현금 부족
			if(cash < -price)
				return "ERR02";
			
			// 대출금보다 더 많은 금액을 상환하려고 함
			if(loan < -price)
				return "ERR03";
			
			// 상환 가능
			mapper.loanOrRepayment(price, bankId, userSeq);
			mapper.updateUserCash(price, userSeq);
			return "success";
		}
	}

	
	/**
	 * 예금 / 출금
	 * 해야할 것
	 * 1. 예금 시
	 *   - 넣으려는 금액만큼 유저가 현금을 가지고 있는지 확인 (현금 >= 넣을 금액)
	 *   - 현금 확인 후에
	 *     (1) 은행에서 예금 더하기
	 *     (2) 유저에서 현금 빼기
	 * 2. 출금 시
	 *   - 출금하려는 금액만큼 예금이 있는지 확인 (예금 >= 출금할 금액)
	 *   - 예금 확인 후에
	 *     (1) 은행에서 예금 빼기
	 *     (2) 유저에서 현금 더하기
	 */
	@Transactional
	@Override
	public String depositOrWithdraw(int price, int bankId, int userSeq){
		MyAccountDto account = mapper.getMyBankAccount(bankId, userSeq);
		int deposit = account.getDeposit();
		
		// 예금
		if(price > 0) {
			int cash = mapper.getMyCash(userSeq);
			// 현금 부족
			if(cash < price)
				return "ERR02";
			
			// 예금 가능
			mapper.depositOrWithdraw(price, bankId, userSeq);
			mapper.updateUserCash(-price, userSeq);
			return "success";
		}
		
		// 출금 (price가 음수로 들어옴)
		else {
			// 출금할 금액 부족
			if(deposit < -price)
				return "ERR04";
			
			// 출금 가능
			mapper.depositOrWithdraw(price, bankId, userSeq);
			mapper.updateUserCash(-price, userSeq);
			return "success";
		}
	}

	@Override
	public MyAssetDto getMyAsset(int userSeq) {
		return mapper.getMyAsset(userSeq);
	}

	@Override
	public boolean updateUserAsset(int userSeq) {
		MyAssetDto myAsset = mapper.getMyAsset(userSeq);
		System.out.println("달: " + myAsset.getDays());
		
		// 1. 월급 넣어주기 + 달 증가
		System.out.println("월급 넣어주기: " + mapper.getSalary(userSeq));
		
		// 2. 부동산 시세 갱신
		System.out.println("부동산 시세 갱신: " + mapper.updateBudongsanPrice());
		
		// 3. 12개월마다 은행 이자 반영
		if(myAsset.getDays()>0 && myAsset.getDays() % 12 == 0)
			System.out.println("12개월마다 은행 이자 반영: " +mapper.updateBankTransactionInterest(userSeq));
		return false;
	}
	
	
}
