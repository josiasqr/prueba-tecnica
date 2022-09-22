package com.pe.account.repository;

import com.pe.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
  Account findByNumberAccount(Long numberAccount);
  List<Account> findByIdentificationClient(String identification);
}
