package com.pe.account.service;

import com.pe.account.client.CustomerClient;
import com.pe.account.client.Movement.Movement;
import com.pe.account.client.MovementClient;
import com.pe.account.client.customer.Customer;
import com.pe.account.domain.Account;
import com.pe.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.pe.account.client.Movement.Type.*;

@Service
public class InAccountService implements AccountService {
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private MovementClient movementClient;
  @Autowired
  private CustomerClient customerClient;

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
    // Search customer in microservice 'customer'
    customerClient.get(account.getIdentificationClient()).getBody();

    // create account
    Account newAccount = accountRepository.save(account);

    // create movement in microservice 'movement'
    if (account.getInitialBalance() > 0) {
      Movement newMovement = new Movement();
      newMovement.setNumberAccount(newAccount.getNumberAccount());
      newMovement.setType(INITIAL);
      newMovement.setAmount(newAccount.getInitialBalance());

      movementClient.createMovement(newMovement);
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
