package com.pe.account.client.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class Customer {
  private Integer id;
  private String name;
  private String gender;
  private Byte age;
  private String direction;
  private String identification;
  private String phone;
  private String password;
  private Boolean status;
  private LocalDateTime registrationDate;
}
