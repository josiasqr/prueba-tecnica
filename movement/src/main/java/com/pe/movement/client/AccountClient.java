package com.pe.movement.client;

import com.pe.movement.client.Account.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "account", url = "http://${URI_ACCOUNT}:4001", path = "/accounts")
public interface AccountClient {
  @GetMapping(value = "/{numberAccount}")
  ResponseEntity<Account> getAccount(@PathVariable("numberAccount") Long numberAccount);

  @PutMapping("/{numberAccount}")
  public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account, @PathVariable("numberAccount") Long numberAccount);
}
