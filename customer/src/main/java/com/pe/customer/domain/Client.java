package com.pe.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "id")
public class Client extends Person {
  private String password;
  private Boolean status;
  private LocalDateTime registrationDate;
}
