package com.pe.account.service;

import com.pe.account.domain.Account;

import java.util.List;

public interface AccountService {
  List<Account> listAccounts();
  List<Account> listIdentification(String identification);

  Account getNumberAccount(Long numberAccount);

  Account createAccount(Account account);

  Account updateAccount(Account account);

  void deleteAccount(Account account);
}
