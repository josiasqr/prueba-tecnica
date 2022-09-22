package com.pe.account.client;

import com.pe.account.client.Movement.Movement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "movement", url = "http://${URI_MOVEMENT}:4002", path = "/movements")
public interface MovementClient {
  @PostMapping
  ResponseEntity<Movement> createMovement(@RequestBody Movement movement);
}
