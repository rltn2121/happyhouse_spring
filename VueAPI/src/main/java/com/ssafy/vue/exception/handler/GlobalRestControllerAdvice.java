package com.ssafy.vue.exception.handler;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestControllerAdvice
@EnableWebMvc
public class GlobalRestControllerAdvice {
//
//	@ExceptionHandler(SQLException.class)
//	public ResponseEntity<String> bankAccountDuplicatedExceptionHandler(SQLException e){
//		String message = "you have already opened an account with this bank.";
//		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//	}
	
}
