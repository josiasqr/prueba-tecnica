package com.pe.customer.controller;

import com.pe.customer.domain.Customer;
import com.pe.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<Customer>> lists() {
    return ResponseEntity.ok(customerService.listCustomers());
  }

  @GetMapping("/report/{identification}")
  public ResponseEntity<List<Map<String, Object>>> reports(@PathVariable("identification") String identification,
                                                           @RequestParam(name = "dateStart") String dateStart,
                                                           @RequestParam(name = "dateEnd") String dateEnd) {
    if (dateStart == null || dateEnd == null || identification == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "identification, dateStart o dateEnd are required");
    }

    try {
      LocalDateTime.parse(dateStart);
      LocalDateTime.parse(dateEnd);
    } catch (DateTimeParseException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "dateStart o dateEnd invalid");
    }

    return ResponseEntity.ok(customerService.reports(identification, dateStart, dateEnd));
  }

  @GetMapping("/{identification}")
  public ResponseEntity<Customer> get(@PathVariable("identification") String identification) {
    Customer customer = customerService.getIdentification(identification);
    if (customer == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(customer);
  }

  @PostMapping
  public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer, BindingResult errors) {
    customer.setRegistrationDate(LocalDateTime.now());
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.CREATED)
      .body(customerService.createCustomer(customer));
  }

  @PutMapping("/{identification}")
  public ResponseEntity<Customer> update(@Valid @PathVariable("identification") String identification,
                                                 @RequestBody Customer customer, BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }

    Customer acc = customerService.getIdentification(identification);
    if (acc == null) {
      return ResponseEntity.notFound().build();
    }

    acc.setName(customer.getName());
    acc.setGender(customer.getGender());
    acc.setAge(customer.getAge());
    acc.setDirection(customer.getDirection());
    acc.setIdentification(customer.getIdentification());
    acc.setPhone(customer.getPhone());
    acc.setPassword(customer.getPassword());
    acc.setStatus(customer.getStatus());
    acc.setRegistrationDate(LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(customerService.updateCustomers(acc));
  }
}
