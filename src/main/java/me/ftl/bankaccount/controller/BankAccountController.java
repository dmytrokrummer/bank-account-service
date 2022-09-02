package me.ftl.bankaccount.controller;

import lombok.RequiredArgsConstructor;
import me.ftl.bankaccount.dto.BankAccountDto;
import me.ftl.bankaccount.dto.TransferDto;
import me.ftl.bankaccount.entity.BankAccount;
import me.ftl.bankaccount.mapper.BankAccountMapper;
import me.ftl.bankaccount.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/bank-account")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService service;
    private final BankAccountMapper mapper;

    @GetMapping
    public ResponseEntity<List> getAll() {
        List<BankAccount> bankAccounts = service.getAll();

        return ResponseEntity.ok(mapper.toDtos(bankAccounts));
    }

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<BankAccountDto> getBalance(@PathVariable Long accountNumber) {
        BankAccount bankAccount = service.get(accountNumber);

        return ResponseEntity.ok(mapper.toBalanceDto(bankAccount));
    }

    @GetMapping("/{accountNumber}/status")
    public ResponseEntity<BankAccountDto> getStatus(@PathVariable Long accountNumber) {
        BankAccount bankAccount = service.get(accountNumber);

        return ResponseEntity.ok(mapper.toStatusDto(bankAccount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferDto transfer) {
        service.transfer(transfer.accountNumber(), transfer.currency(), transfer.amount(), transfer.sign());

        return ResponseEntity.noContent().build();
    }
}
