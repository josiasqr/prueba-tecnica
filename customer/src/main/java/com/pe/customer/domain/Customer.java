package com.pe.customer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "id")
public class Customer extends Person {
  private String password;
  private Boolean status;
  private LocalDateTime registrationDate;

  public Double balance(Double balance, Double movement){
    if(movement < 0){
      return balance + (movement*-1);
    }
    return balance - movement;
  }
}
