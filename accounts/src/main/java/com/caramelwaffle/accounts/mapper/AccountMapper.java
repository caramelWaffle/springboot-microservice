package com.caramelwaffle.accounts.mapper;

import com.caramelwaffle.accounts.model.AccountData;
import com.caramelwaffle.accounts.entity.AccountEntity;

public class AccountMapper {

    public static AccountData mapToAccountsData(AccountEntity accountEntity, AccountData accountsDto) {
        accountsDto.setAccountNumber(accountEntity.getAccountNumber());
        accountsDto.setAccountType(accountEntity.getAccountType());
        accountsDto.setBranchAddress(accountEntity.getBranchAddress());
        return accountsDto;
    }

    public static AccountEntity mapToAccounts(AccountData accountsDto, AccountEntity accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

}