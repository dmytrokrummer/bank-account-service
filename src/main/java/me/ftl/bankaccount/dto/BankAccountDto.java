package me.ftl.bankaccount.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.ftl.bankaccount.entity.enums.BankAccountStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BankAccountDto(Long accountNumber, Integer currency, Long balance, BankAccountStatus status) {

    public BankAccountDto(Long balance) {
        this(null, null, balance, null);
    }
    public BankAccountDto(BankAccountStatus status) {
        this(null, null, null, status);
    }
}
