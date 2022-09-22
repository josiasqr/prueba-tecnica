package com.pe.movement.service;

import com.pe.movement.domain.Movement;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementService {
  List<Movement> listMovements();
  List<Movement> listNumberAccount(Long numberAccount);
  List<Movement> listNumberAccountAndDate(Long number, LocalDateTime dateStart, LocalDateTime dateEnd);

  Movement getMovement(String code);

  Movement createMovement(Movement movement);
}
