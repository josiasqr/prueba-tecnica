package com.pe.customer.service;

import com.pe.customer.domain.Customer;
import com.pe.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class InCustomerServiceTest {
  @Mock
  CustomerRepository customerRepository;

  @InjectMocks
  InCustomerService inClientService;

  @Autowired
  private Customer customer;

  @BeforeEach
  void init(){
    Customer mockCustomer = new Customer();
    mockCustomer.setId(1);
    mockCustomer.setName("Lucas Barrios");
    mockCustomer.setGender("Masculino");
    mockCustomer.setAge((byte) 25);
    mockCustomer.setDirection("Lima Peru");
    mockCustomer.setIdentification("89767463");
    mockCustomer.setPhone("987654321");
    mockCustomer.setPassword("123456");
    mockCustomer.setStatus(true);
    mockCustomer.setRegistrationDate(LocalDateTime.parse("2022-09-21T19:08:00"));

    customer = mockCustomer;
  }
  @Test
  void findByIdentification() {
    Mockito.when(customerRepository.findByIdentification("89767463")).thenReturn(customer);
    Customer obj = inClientService.getIdentification("89767463");
    assertEquals(customer, obj);
  }
}
