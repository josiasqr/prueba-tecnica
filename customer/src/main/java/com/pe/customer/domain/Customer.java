package com.pe.customer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "id")
public class Customer extends Person {
  private String password;

  @NotNull(message = "status not be empty")
  @Column(nullable = false)
  private Boolean status;

  private LocalDateTime registrationDate;

  public Double balance(Double balance, Double movement) {
    if (movement < 0) {
      return balance + (movement * -1);
    }
    return balance - movement;
  }
}
