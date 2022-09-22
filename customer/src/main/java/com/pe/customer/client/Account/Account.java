package com.pe.customer.client.Account;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
  private Integer id;
  private String identificationClient;
  private Long numberAccount;
  private Type type;
  private Double initialBalance;
  private Boolean status;
  private LocalDateTime registrationDate;
}
