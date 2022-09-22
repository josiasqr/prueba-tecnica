package com.pe.customer.client.Movement;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Movement {
  private Integer id;
  private String code;
  private Long numberAccount;
  private Type type;
  private Double amount;
  private Double balance;
  private LocalDateTime registrationDate;
}
