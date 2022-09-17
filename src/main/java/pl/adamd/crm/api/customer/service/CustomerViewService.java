package pl.adamd.crm.api.customer.service;


import org.springframework.http.ResponseEntity;
import pl.adamd.crm.api.customer.dto.CustomerDto;
import pl.adamd.crm.api.customer.dto.CustomerDtoList;
import pl.adamd.crm.api.customer.entity.Customer;

import java.util.List;

public interface CustomerViewService {
    ResponseEntity<List<CustomerDtoList>> getAll();

    ResponseEntity<Customer> getClientById(Long clientId);

    ResponseEntity<Customer> addNewClient(CustomerDto request);

    ResponseEntity<Customer> updateClient(Long id, CustomerDto request);

    ResponseEntity<List<Customer>> getListOfClientsWithAgreement();

    ResponseEntity<List<CustomerDtoList>> getByName(String name);
}
