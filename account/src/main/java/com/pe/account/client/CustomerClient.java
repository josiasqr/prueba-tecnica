package com.pe.account.client;

import com.pe.account.client.customer.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer", url = "http://${URI_CUSTOMER}:4000", path = "/clients")
public interface CustomerClient {
  @GetMapping("/{identification}")
  public ResponseEntity<Customer> get(@PathVariable("identification") String identification);
}
