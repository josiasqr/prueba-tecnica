package com.pe.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty(message = "name not be empty")
  @Column(nullable = false)
  private String name;

  @NotEmpty(message = "gender not be empty")
  @Column(nullable = false)
  private String gender;

  @Min(1)
  @Max(127)
  @Column(nullable = false)
  private Byte age;

  @NotEmpty(message = "direction not be empty")
  @Column(nullable = false)
  private String direction;

  @NotEmpty(message = "identification not be empty")
  @Column(nullable = false, unique = true)
  private String identification;

  @NotEmpty(message = "phone not be empty")
  @Size(min = 6, max = 12, message = "phone invalid")
  @Column(nullable = false)
  private String phone;
}
