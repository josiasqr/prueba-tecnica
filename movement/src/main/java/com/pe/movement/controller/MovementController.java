package com.pe.movement.controller;

import com.pe.movement.domain.Movement;
import com.pe.movement.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {
  @Autowired
  private MovementService movementService;

  @GetMapping
  public ResponseEntity<List<Movement>> listMovements() {
    return ResponseEntity.ok(movementService.listMovements());
  }

  @GetMapping("/{code}")
  public ResponseEntity<Movement> getMovement(@PathVariable("code") String code) {
    Movement movement = movementService.getMovement(code);

    if (movement == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(movement);
  }

  @GetMapping("/numberAccount/{numberAccount}")
  public ResponseEntity<List<Movement>> listNumberAccount(@PathVariable("numberAccount") Long numberAccount,
                                                          @RequestParam(name = "dateStart", required = false) String dateStart,
                                                          @RequestParam(name = "dateEnd", required = false) String dateEnd) {
    try {
      if (dateStart == null || dateEnd == null) {
        return ResponseEntity.ok(movementService.listNumberAccount(numberAccount));
      }
      LocalDateTime start = LocalDateTime.parse(dateStart);
      LocalDateTime end = LocalDateTime.parse(dateEnd);

      return ResponseEntity.ok(movementService.listNumberAccountAndDate(numberAccount, start, end));
    } catch (DateTimeParseException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fechas no validas");
    }
  }

  @PostMapping
  public ResponseEntity<Movement> createMovement(@Valid @RequestBody Movement movement, BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }
    movement.setCode(movement.code());
    movement.setRegistrationDate(LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CREATED).body(movementService.createMovement(movement));
  }
}
