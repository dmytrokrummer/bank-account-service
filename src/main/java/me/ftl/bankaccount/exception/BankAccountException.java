package me.ftl.bankaccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BankAccountException extends ResponseStatusException {

    public static final String NOT_FOUND = "Entity not found";
    public static final String FORBIDDEN_ACCOUNT_STATUS = "Only OPEN accounts can make transfers";
    public static final String BALANCE_NOT_SUFFICIENT = "Insufficient funds for transfer";
    public static final String DIFFERENT_CURRENCIES = "Currencies must be the same";

    public BankAccountException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
