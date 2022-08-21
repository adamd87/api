package pl.adamd.crm.api.customer.service;


import org.springframework.http.ResponseEntity;
import pl.adamd.crm.api.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerViewService {
    ResponseEntity<List<CustomerDto>> getAll();

    ResponseEntity<CustomerDto> getClientById(Long clientId);

    ResponseEntity<CustomerDto> addNewClient(CustomerDto request);

    ResponseEntity<CustomerDto> updateClient(Long id, CustomerDto request);

    ResponseEntity<List<CustomerDto>> getListOfClientsWithAgreement();
}
