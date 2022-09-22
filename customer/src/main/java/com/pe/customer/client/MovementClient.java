package com.pe.customer.client;

import com.pe.customer.client.Movement.Movement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "movement", url = "http://${URI_MOVEMENT}:4002", path = "/movements")
public interface MovementClient {
  @GetMapping("/numberAccount/{numberAccount}")
  ResponseEntity<List<Movement>> listNumberAccount(@PathVariable("numberAccount") Long numberAccount,
                                                          @RequestParam(name = "dateStart", required = false) String dateStart,
                                                          @RequestParam(name = "dateEnd", required = false) String dateEnd);
}
