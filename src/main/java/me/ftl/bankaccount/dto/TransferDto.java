package me.ftl.bankaccount.dto;

import me.ftl.bankaccount.entity.enums.OperationSign;

public record TransferDto(Long accountNumber, Integer currency, Long amount, OperationSign sign) {
}
