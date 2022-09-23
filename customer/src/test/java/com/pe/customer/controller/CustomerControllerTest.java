package com.pe.customer.controller;

import com.pe.customer.domain.Customer;
import com.pe.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;

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
  public void getCustomerCode200() throws Exception {
    when(customerService.getIdentification("89767463")).thenReturn(customer);

    mockMvc.perform(get("/clients/89767463").contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(APPLICATION_JSON))
      .andExpect(jsonPath("$.name").value("Lucas Barrios"))
      .andExpect(jsonPath("$.gender").value("Masculino"));

    //verify(customerService).getIdentification("89767463");
  }

  @Test
  void listCustomerCode404() throws Exception {
    when(customerService.getIdentification("897674631")).thenReturn(customer);

    mockMvc.perform(get("/clients/89767463").contentType(APPLICATION_JSON))
      .andExpect(status().is4xxClientError());

    //verify(customerService).getIdentification("89767463");
  }

  @Test
  void createCustomer() throws Exception {
    mockMvc.perform(post("/clients")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"josias \", \"gender\":\"femenino\",\"age\":32,\"direction\":\"Lima\",\"identification\":\"78905312\",\"phone\":\"98052436\", \"password\":\"1234\",\"status\":\"true\"}")
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated());
  }
}
