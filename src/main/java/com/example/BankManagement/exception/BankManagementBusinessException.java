package com.example.BankManagement.exception;

public class BankManagementBusinessException extends Exception{

    public BankManagementBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BankManagementBusinessException(String message) {
		super(message);
	}
    
}
