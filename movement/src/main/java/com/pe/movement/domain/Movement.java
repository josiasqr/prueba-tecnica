package com.pe.movement.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

  @Column(nullable = false)
  private Long numberAccount;
  private Type type;
  private Double amount;
  private LocalDateTime registrationDate;

  public String code() {
    Random r = new Random();
    Integer random = r.nextInt(999999);
    return random.toString();
  }
}
