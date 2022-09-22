package com.pe.movement.repository;

import com.pe.movement.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {
  Movement findByCode(String code);
  List<Movement> findByNumberAccount(Long numberAccount);
  List<Movement> findAllByNumberAccountAndRegistrationDateBetween(Long number, LocalDateTime dateStart, LocalDateTime dateEnd);
}
