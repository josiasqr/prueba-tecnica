package com.pe.account.controller;

import com.pe.account.domain.Account;
import com.pe.account.domain.Type;
import com.pe.account.service.AccountService;
import com.pe.account.service.InAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AccountService accountService;

  private Account account;
  @BeforeEach
  void init(){
    Account acc=new Account();
    acc.setId(1);
    acc.setIdentificationClient("78907341");
    acc.setNumberAccount(8367996542534765180L);
    acc.setType(Type.CORRIENTE);
    acc.setInitialBalance(200.0);
    acc.setStatus(true);
    acc.setRegistrationDate(LocalDateTime.parse("2022-09-22T20:50:14.926998"));

    account = acc;
  }

  @Test
  void getAccountNumberAccount() throws Exception {
    when(accountService.getNumberAccount(8367996542534765180L)).thenReturn(account);

    mockMvc.perform(get("/accounts/8367996542534765180").contentType(APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(APPLICATION_JSON))
      .andExpect(jsonPath("$.identificationClient").value("78907341"))
      .andExpect(jsonPath("$.type").value("CORRIENTE"));
  }

  @Test
  void createAccount() throws Exception {
    mockMvc.perform(post("/accounts")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"type\":\"CORRIENTE \", \"identificationClient\":\"78907341\",\"initialBalance\":2000.0,\"status\":true}")
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated());
  }
}
