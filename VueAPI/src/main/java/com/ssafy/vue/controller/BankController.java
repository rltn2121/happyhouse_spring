package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Bank;
import com.ssafy.vue.dto.BankTransaction;
import com.ssafy.vue.dto.MyAccountDto;
import com.ssafy.vue.dto.MyAssetDto;
import com.ssafy.vue.exception.custom.BankAccountDuplicatedException;
import com.ssafy.vue.service.BankService;

@RestController
@RequestMapping("/banks")
//@CrossOrigin("*")
public class BankController {
	
	private final Logger logger = LoggerFactory.getLogger(BankController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BankService service;
	
	/**
	 * 모든 은행 조회
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<Bank>> getBankAll(){
		logger.debug("getBankAll - 호출");
		HttpStatus status = HttpStatus.OK;
		List<Bank> list = service.getBankAll();
		if(list == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(list, status);
	}

	/**
	 * 은행 계좌 개설
	 * @param bankTransaction
	 * @return
	 * @throws BankAccountDuplicatedException
	 */
	@PostMapping("/account")
	public ResponseEntity<String> createBankAccount(@RequestBody BankTransaction bankTransaction) throws BankAccountDuplicatedException   {
		logger.debug("getBankAll - 호출");
		HttpStatus status = HttpStatus.OK;
		String message = SUCCESS;
		
		int userSeq = bankTransaction.getUserSeq();
		int bankId = bankTransaction.getBankId();
		System.out.println(userSeq);
		System.out.println(bankId);
		
		try {
			if(!service.createBankAccount(userSeq, bankId)) {
				message = FAIL;
				status = HttpStatus.BAD_REQUEST;
			}
			return new ResponseEntity<>(message, status);
		}catch(BankAccountDuplicatedException e) {
			System.out.println("EXCEPTION 발생");
			e.printStackTrace();
			throw new BankAccountDuplicatedException("account duplicated");
		
		}
	
	}

	/**
	 * 내 모든 계좌 조회
	 * @param userSeq
	 * @return
	 */
	@GetMapping("/account/{user_seq}")
	public ResponseEntity<List<MyAccountDto>> getMyAllBankAccount(@PathVariable("user_seq") int userSeq){
		logger.debug("getMyAllBankAccount - 호출");
		HttpStatus status = HttpStatus.OK;
		List<MyAccountDto> list = service.getMyAllBankAccount(userSeq);
		if(list == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(list, status);
	}
	
	/**
	 * 내 자산 조회
	 * @param userSeq
	 * @return
	 */
	@GetMapping("/assets/{user_seq}")
	public ResponseEntity<MyAssetDto> getMyAsset(@PathVariable("user_seq") int userSeq){
		logger.debug("getMyAsset - 호출");
		HttpStatus status = HttpStatus.OK;
		MyAssetDto dto = service.getMyAsset(userSeq);
		if(dto == null)
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<>(dto, status);
	}
	
	/**
	 * 대출/상환
	 * @param bankTransaction
	 * @return
	 */
	@PutMapping("/loan")
	public ResponseEntity<String> loanOrRepayment(@RequestBody BankTransaction bankTransaction) {
		logger.debug("loanOrRepayment - 호출");
		HttpStatus status = HttpStatus.OK;
		String message = SUCCESS;
		
		int price = bankTransaction.getLoan();
		int bankId = bankTransaction.getBankId();
		int userSeq = bankTransaction.getUserSeq();
		System.out.println(price);
		if(!service.loanOrRepayment(price, bankId, userSeq)) {
			message = FAIL;
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(message, status);
	}
	
	/**
	 * 예금/출금
	 * @param bankTransaction
	 * @return
	 */
	@PutMapping("/deposit")
	public ResponseEntity<String> depositOrWithdraw(@RequestBody BankTransaction bankTransaction) {
		logger.debug("depositOrWithdraw - 호출");
		HttpStatus status = HttpStatus.OK;
		String message = SUCCESS;
		
		int price = bankTransaction.getDeposit();
		int bankId = bankTransaction.getBankId();
		int userSeq = bankTransaction.getUserSeq();
		if(service.depositOrWithdraw(price, bankId, userSeq)) {
			message = FAIL;
			status = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>(message, status);
	}
	
}
