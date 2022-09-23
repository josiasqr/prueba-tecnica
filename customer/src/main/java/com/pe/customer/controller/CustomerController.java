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
import java.net.URI;
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
  public ResponseEntity<List<Customer>> listCustomers() {
    return ResponseEntity.ok(customerService.listCustomers());
  }

  @GetMapping("/report/{identification}")
  public ResponseEntity<List<Map<String, Object>>> reports(@PathVariable("identification") String identification,
                                                           @RequestParam(name = "dateStart") String dateStart,
                                                           @RequestParam(name = "dateEnd") String dateEnd) {

    if (dateStart == null || dateEnd == null || identification == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta identification, dateStart o dateEnd");
    }

    try {
      LocalDateTime start = LocalDateTime.parse(dateStart);
      LocalDateTime end = LocalDateTime.parse(dateEnd);
    } catch (DateTimeParseException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fechas no validas");
    }

    return ResponseEntity.ok(customerService.reports(identification, dateStart, dateEnd));
  }

  @GetMapping("/{identification}")
  public ResponseEntity<Customer> getCustomer(@PathVariable("identification") String identification) {
    Customer customer = customerService.getIdentification(identification);

    if (customer == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(customer);
  }

  @PostMapping
  public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }
    customer.setRegistrationDate(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
  }

  @PutMapping("/{identification}")
  public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable("identification") String identification,
                                                 @RequestBody Customer customer, BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }

    Customer findCustomer = customerService.getIdentification(identification);
    if (findCustomer == null) {
      return ResponseEntity.notFound().build();
    }

    findCustomer.setName(customer.getName());
    findCustomer.setGender(customer.getGender());
    findCustomer.setAge(customer.getAge());
    findCustomer.setDirection(customer.getDirection());
    findCustomer.setIdentification(customer.getIdentification());
    findCustomer.setPhone(customer.getPhone());
    findCustomer.setPassword(customer.getPassword());
    findCustomer.setStatus(customer.getStatus());
    findCustomer.setRegistrationDate(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomers(findCustomer));
  }
}
