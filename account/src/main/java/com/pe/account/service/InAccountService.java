package com.pe.account.service;

import com.pe.account.client.Movement.Movement;
import com.pe.account.client.MovementClient;
import com.pe.account.domain.Account;
import com.pe.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pe.account.client.Movement.Type.*;

@Service
public class InAccountService implements AccountService {
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private MovementClient movementClient;

  @Override
  public List<Account> listAccounts() {
    return accountRepository.findAll();
  }

  @Override
  public List<Account> listIdentification(String identification) {
    return accountRepository.findByIdentificationClient(identification);
  }

  @Override
  public Account getNumberAccount(Long numberAccount) {
    return accountRepository.findByNumberAccount(numberAccount);
  }

  @Override
  public Account createAccount(Account account) {
    Account newAccount = accountRepository.save(account);

    if (account.validateBalance(account.getInitialBalance())) {
      Movement newMovement = new Movement();
      newMovement.setNumberAccount(newAccount.getNumberAccount());
      newMovement.setType(INITIAL);
      newMovement.setAmount(newAccount.getInitialBalance());
      movementClient.createMovement(newMovement);

      return newAccount;
    }

    return newAccount;
  }

  @Override
  public Account updateAccount(Account account) {
    return accountRepository.save(account);
  }

  @Override
  public void deleteAccount(Account account) {
    accountRepository.delete(account);
  }
}
