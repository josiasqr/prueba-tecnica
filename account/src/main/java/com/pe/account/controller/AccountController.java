package com.pe.account.controller;

import com.pe.account.domain.Account;
import com.pe.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
  @Autowired
  private AccountService accountService;

  @GetMapping
  public ResponseEntity<List<Account>> listAccounts() {
    return ResponseEntity.ok(accountService.listAccounts());
  }

  @GetMapping("/identification/{identification}")
  public ResponseEntity<List<Account>> getListIdentification(@PathVariable("identification") String identification) {
    List<Account> accounts = accountService.listIdentification(identification);

    return ResponseEntity.ok().body(accounts);
  }

  @GetMapping("/{numberAccount}")
  public ResponseEntity<Account> getNumberAccount(@PathVariable("numberAccount") Long numberAccount) {
    Account account = accountService.getNumberAccount(numberAccount);

    if (account == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(account);
  }

  @PostMapping
  public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account, BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }
    account.setNumberAccount(account.number());
    account.setRegistrationDate(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
  }

  @PutMapping("/{numberAccount}")
  public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account, @PathVariable("numberAccount") Long numberAccount, BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }

    Account acc = accountService.getNumberAccount(numberAccount);
    if (acc == null) {
      return ResponseEntity.notFound().build();
    }

    acc.setNumberAccount(account.getNumberAccount());
    acc.setType(account.getType());
    acc.setInitialBalance(account.getInitialBalance());
    acc.setStatus(account.getStatus());
    acc.setRegistrationDate(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CREATED).body(accountService.updateAccount(acc));
  }

  @DeleteMapping("/{numberAccount}")
  public ResponseEntity<Void> deleteAccount(@PathVariable("numberAccount") Long numberAccount) {
    Account account = accountService.getNumberAccount(numberAccount);

    if (account == null) {
      return ResponseEntity.notFound().build();
    }
    accountService.deleteAccount(account);

    return ResponseEntity.ok().build();
  }
}
