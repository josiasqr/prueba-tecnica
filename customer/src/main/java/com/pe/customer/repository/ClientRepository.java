package com.pe.customer.repository;

import com.pe.customer.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
  Client findByIdentification(String identification);
}
