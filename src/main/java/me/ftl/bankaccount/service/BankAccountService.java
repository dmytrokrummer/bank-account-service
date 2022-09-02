package me.ftl.bankaccount.service;

import lombok.RequiredArgsConstructor;
import me.ftl.bankaccount.entity.BankAccount;
import me.ftl.bankaccount.entity.enums.BankAccountStatus;
import me.ftl.bankaccount.entity.enums.OperationSign;
import me.ftl.bankaccount.exception.BankAccountException;
import me.ftl.bankaccount.repository.BankAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository repository;

    public List<BankAccount> getAll() {
        return repository.findAll();
    }

    public BankAccount get(Long accountNumber) {
        return getBankAccount(accountNumber);
    }

    @Transactional
    public void transfer(Long accountNumber, Integer currency, Long amount, OperationSign sign) {
        BankAccount account = getBankAccount(accountNumber);

        checkAccountStatus(account.getStatus());
        checkCurrenciesIsSame(currency, account.getCurrency());

        if (OperationSign.CREDIT == sign) {
            checkBalanceForCredit(amount, account.getBalance());
        }

        Long newBalance = OperationSign.CREDIT == sign ? Math.subtractExact(account.getBalance(), amount) :
                Long.sum(account.getBalance(), amount);

        account.setBalance(newBalance);

        repository.save(account);
    }

    private BankAccount getBankAccount(Long accountNumber) {
        return repository.findById(accountNumber)
                .orElseThrow(() -> new BankAccountException(HttpStatus.NOT_FOUND, BankAccountException.NOT_FOUND));
    }

    private void checkAccountStatus(BankAccountStatus status) {
        if (BankAccountStatus.OPEN != status) {
            throw new BankAccountException(HttpStatus.FORBIDDEN, BankAccountException.FORBIDDEN_ACCOUNT_STATUS);
        }
    }

    private void checkCurrenciesIsSame(Integer currency, Integer accountCurrency) {
        if (currency.compareTo(accountCurrency) != 0) {
            throw new BankAccountException(HttpStatus.FORBIDDEN, BankAccountException.DIFFERENT_CURRENCIES);
        }
    }

    private void checkBalanceForCredit(Long creditAmount, Long balance) {
        if (creditAmount.compareTo(balance) > 0) {
            throw new BankAccountException(HttpStatus.FORBIDDEN, BankAccountException.BALANCE_NOT_SUFFICIENT);
        }
    }
}
