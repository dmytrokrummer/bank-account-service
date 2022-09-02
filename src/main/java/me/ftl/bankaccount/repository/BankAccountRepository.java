package me.ftl.bankaccount.repository;

import me.ftl.bankaccount.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
