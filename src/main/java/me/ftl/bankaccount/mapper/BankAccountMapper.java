package me.ftl.bankaccount.mapper;

import me.ftl.bankaccount.dto.BankAccountDto;
import me.ftl.bankaccount.entity.BankAccount;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BankAccountMapper {

    @Named(value = "toDto")
    public abstract BankAccountDto toDto(BankAccount bankAccount);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<BankAccountDto> toDtos(List<BankAccount> bankAccounts);

    public BankAccountDto toBalanceDto(BankAccount bankAccount) {
        return new BankAccountDto(bankAccount.getBalance());
    }

    public BankAccountDto toStatusDto(BankAccount bankAccount) {
        return new BankAccountDto(bankAccount.getStatus());
    }
}
