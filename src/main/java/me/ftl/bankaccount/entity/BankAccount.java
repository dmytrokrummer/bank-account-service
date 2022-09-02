package me.ftl.bankaccount.entity;

import lombok.*;
import me.ftl.bankaccount.entity.enums.BankAccountStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    private Long accountNumber;
    private Integer currency;
    private Long balance;
    @Enumerated(EnumType.STRING)
    private BankAccountStatus status;
    @Version
    private int version;
}
