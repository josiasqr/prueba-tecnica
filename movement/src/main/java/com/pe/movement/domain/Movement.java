package com.pe.movement.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@NoArgsConstructor
@Entity
@Table
public class Movement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String code;

  @NotNull(message = "numberAccount not be empty")
  @Column(nullable = false)
  private Long numberAccount;

  @Enumerated(EnumType.STRING)
  private Type type;

  @NotNull(message = "amount not be empty")
  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false)
  private Double balance;

  private LocalDateTime registrationDate;

  public String code() {
    Random r = new Random();
    Integer random = r.nextInt(999999);
    return random.toString();
  }
}
