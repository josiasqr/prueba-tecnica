package com.pe.customer.service;

import com.pe.customer.domain.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
  List<Customer> listCustomers();

  List<Map<String, Object>> reports(String identification, String dateStart, String dateEnd);

  Customer getIdentification(String identification);

  Customer createCustomer(Customer customer);

  Customer updateCustomers(Customer customer);

  void deleteClient(Customer customer);
}
