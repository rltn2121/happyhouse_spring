package com.ssafy.vue.exception.custom;

import java.sql.SQLException;

public class BankAccountDuplicatedException extends SQLException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4004561730982429914L;

	public BankAccountDuplicatedException(String message) {
		super(message);
	}
	
}
