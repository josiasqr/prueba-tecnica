package com.pe.customer.client;

import com.pe.customer.client.Account.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account", url = "http://${URI_ACCOUNT}:4001", path = "/accounts")
public interface AccountClient {
  @GetMapping("/identification/{identification}")
  public ResponseEntity<List<Account>> getListIdentification(@PathVariable("identification") String identification);

}
