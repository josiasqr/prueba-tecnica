package com.pe.customer.service;

import com.pe.customer.client.Account.Account;
import com.pe.customer.client.AccountClient;
import com.pe.customer.client.MovementClient;
import com.pe.customer.domain.Client;
import com.pe.customer.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InClientService implements ClientService {
  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private AccountClient accountClient;

  @Autowired
  private MovementClient movementClient;

  public InClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public List<Client> listClients() {
    return this.clientRepository.findAll();
  }

  @Override
  public List<Map<String, Object>> reports(String identification, String dateStart, String dateEnd) {
    Client client = getIdentification(identification);
    List<Account> accounts = accountClient.getListIdentification(identification).getBody();

    Map<String, Object> report = new HashMap<>();
    report.put("Customer", client);
    report.put("Account", accounts);

    List<Map<String, Object>> newMap = List.of(report);

    return newMap;
  }

  @Override
  public Client getIdentification(String identification) {
    return this.clientRepository.findByIdentification(identification);
  }

  @Override
  public Client createClient(Client client) {
    return this.clientRepository.save(client);
  }

  @Override
  public Client updateClient(Client client) {
    return this.clientRepository.save(client);
  }

  @Override
  public void deleteClient(Client client) {
    this.clientRepository.delete(client);
  }
}
