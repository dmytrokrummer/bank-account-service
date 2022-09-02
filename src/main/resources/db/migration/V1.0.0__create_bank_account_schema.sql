create table bank_accounts
(
    account_number  bigint      PRIMARY KEY,
    currency        smallint    not null,
    balance         BIGINT      not null,
    status          varchar(255),
    version         int         default 0
);

create index bank_accounts_account_number_idx on bank_accounts (account_number);
