package com.pe.movement.service;

import com.pe.movement.client.Account.Account;
import com.pe.movement.client.AccountClient;
import com.pe.movement.domain.Movement;
import com.pe.movement.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static com.pe.movement.domain.Type.*;

@Service
public class InMovementService implements MovementService {
  @Autowired
  private MovementRepository movementRepository;
  @Autowired
  private AccountClient accountClient;

  @Override
  public List<Movement> listMovements() {
    return movementRepository.findAll();
  }

  @Override
  public List<Movement> listNumberAccount(Long numberAccount) {
    return movementRepository.findByNumberAccount(numberAccount);
  }

  @Override
  public List<Movement> listNumberAccountAndDate(Long number, LocalDateTime dateStart, LocalDateTime dateEnd) {
    return movementRepository.findAllByNumberAccountAndRegistrationDateBetween(number, dateStart, dateEnd);
  }

  @Override
  public Movement getMovement(String code) {
    return movementRepository.findByCode(code);
  }

  @Override
  public Movement createMovement(Movement movement) {
    // Search account in microservice 'account'
    Account account = accountClient.getAccount(movement.getNumberAccount()).getBody();

    if (movement.getType().equals(INITIAL)) {
      movement.setBalance(account.getInitialBalance());

    } else if (movement.getType().equals(DEPOSITO)) {
      // Update setInitialBalance en 'microservice account'
      account.setInitialBalance(account.getInitialBalance() + movement.getAmount());
      accountClient.updateAccount(account, movement.getNumberAccount());

      movement.setBalance(account.getInitialBalance());
    } else {
      if (account.getInitialBalance() < movement.getAmount()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "insufficient balance");
      }
      // Update setInitialBalance en 'microservice account'
      account.setInitialBalance(account.getInitialBalance() - movement.getAmount());
      accountClient.updateAccount(account, movement.getNumberAccount());

      movement.setBalance(account.getInitialBalance());
      movement.setAmount(movement.getAmount() * -1);
    }

    return movementRepository.save(movement);
  }
}
