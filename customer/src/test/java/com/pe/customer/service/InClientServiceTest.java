package com.pe.customer.service;

import com.pe.customer.domain.Client;
import com.pe.customer.repository.ClientRepository;
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
public class InClientServiceTest {
  @Mock
  ClientRepository clientRepository;

  @InjectMocks
  InClientService inClientService;

  @Autowired
  private Client client;

  @BeforeEach
  void init(){
    Client mockClient = new Client();
    mockClient.setId(1);
    mockClient.setName("Lucas Barrios");
    mockClient.setGender("Masculino");
    mockClient.setAge((byte) 25);
    mockClient.setDirection("Lima Peru");
    mockClient.setIdentification("89767463");
    mockClient.setPhone("987654321");
    mockClient.setPassword("123456");
    mockClient.setStatus(true);
    mockClient.setRegistrationDate(LocalDateTime.parse("2022-09-21T19:08:00"));

    client = mockClient;
  }
  @Test
  void findByIdentification() {
    Mockito.when(clientRepository.findByIdentification("89767463")).thenReturn(client);
    Client obj = inClientService.getIdentification("89767463");
    assertEquals(client, obj);
  }
}
