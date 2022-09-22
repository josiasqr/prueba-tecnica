package com.pe.customer.controller;

import com.pe.customer.domain.Client;
import com.pe.customer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {
  @Autowired
  private ClientService clientService;

  @GetMapping
  public ResponseEntity<List<Client>> listClients() {
    return ResponseEntity.ok(clientService.listClients());
  }

  @GetMapping("/report")
  public ResponseEntity<List<Map<String, Object>>> reports() {

    return ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(clientService.reports("78907341", "", ""));
  }

  @GetMapping("/{identification}")
  public ResponseEntity<Client> getClient(@PathVariable("identification") String identification) {
    Client client = clientService.getIdentification(identification);

    if (client == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(client);
  }

  @PostMapping
  public ResponseEntity<Client> createClient(@Valid @RequestBody Client client, BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }
    client.setRegistrationDate(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(client));
  }

  @PutMapping("/{identification}")
  public ResponseEntity<Client> updateClient(@Valid @PathVariable("identification") String identification,
                                             @RequestBody Client client, BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }

    Client findClient = clientService.getIdentification(identification);
    if (findClient == null) {
      return ResponseEntity.notFound().build();
    }

    findClient.setName(client.getName());
    findClient.setGender(client.getGender());
    findClient.setAge(client.getAge());
    findClient.setDirection(client.getDirection());
    findClient.setIdentification(client.getIdentification());
    findClient.setPhone(client.getPhone());
    findClient.setPassword(client.getPassword());
    findClient.setStatus(client.getStatus());
    findClient.setRegistrationDate(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CREATED).body(clientService.updateClient(findClient));
  }
}
