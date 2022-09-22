package com.pe.customer.service;

import com.pe.customer.domain.Client;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ClientService {
  List<Client> listClients();
  List<Map<String, Object>> reports(String identification, String dateStart, String dateEnd);
  Client getIdentification(String identification);

  Client createClient(Client client);

  Client updateClient(Client client);

  void deleteClient(Client client);
}
