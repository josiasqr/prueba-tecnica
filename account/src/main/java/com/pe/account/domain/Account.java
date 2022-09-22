package com.pe.account.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@NoArgsConstructor
@Entity
@Table
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty(message = "identificationClient not be empty")
  @Column(nullable = false)
  private String identificationClient;

  @Column(unique = true, nullable = false)
  private Long numberAccount;
  private Type type;
  private Double initialBalance;
  private Boolean status;
  private LocalDateTime registrationDate;

  public Boolean validateBalance(Double initialBalance) {
    if (initialBalance > 0) {
      return true;
    }
    return false;
  }

  public Long number() {
    Random random = new Random();
    Long numberRandom = random.nextLong();

    if (numberRandom > 0) {
      return numberRandom;
    }
    return numberRandom * -1;
  }
}
