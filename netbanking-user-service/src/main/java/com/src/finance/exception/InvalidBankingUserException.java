package com.src.finance.exception;


public class InvalidBankingUserException extends SimpleBankingGlobalException {
    public InvalidBankingUserException(String message, String code) {
        super(message, code);
    }
}
