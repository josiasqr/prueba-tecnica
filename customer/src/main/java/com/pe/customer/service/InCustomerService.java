package com.pe.customer.service;

import com.pe.customer.client.Account.Account;
import com.pe.customer.client.AccountClient;
import com.pe.customer.client.Movement.Movement;
import com.pe.customer.client.MovementClient;
import com.pe.customer.domain.Customer;
import com.pe.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InCustomerService implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private AccountClient accountClient;
  @Autowired
  private MovementClient movementClient;

  public InCustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<Customer> listCustomers() {
    return this.customerRepository.findAll();
  }

  @Override
  public List<Map<String, Object>> reports(String identification, String dateStart, String dateEnd) {
    Customer customer = getIdentification(identification);
    if (customer == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found with identification = " + identification);
    }

    List<Account> accounts = accountClient.getListIdentification(identification).getBody();
    List<Map<String, Object>> newMap = new ArrayList<>();

    for (Account acc : accounts) {
      List<Movement> movements = movementClient.listNumberAccount(acc.getNumberAccount(), dateStart, dateEnd).getBody();

      int cont = 0;
      for (Movement mov : movements) {
        Map<String, Object> report = new HashMap<>();
        report.put("Fecha", mov.getRegistrationDate());
        report.put("Cliente", customer.getName());
        report.put("Numero Cuenta", acc.getNumberAccount());
        report.put("Saldo Inicial", customer.balance(mov.getBalance(), mov.getAmount()));
        report.put("Estado", acc.getStatus());
        report.put("Movimiento", mov.getAmount());
        report.put("Saldo Disponible", mov.getBalance());

        newMap.add(cont, report);
        cont++;
      }
    }

    return newMap;
  }

  @Override
  public Customer getIdentification(String identification) {
    return this.customerRepository.findByIdentification(identification);
  }

  @Override
  public Customer createCustomer(Customer customer) {
    return this.customerRepository.save(customer);
  }

  @Override
  public Customer updateCustomers(Customer customer) {
    return this.customerRepository.save(customer);
  }

  @Override
  public void deleteClient(Customer customer) {
    this.customerRepository.delete(customer);
  }
}
