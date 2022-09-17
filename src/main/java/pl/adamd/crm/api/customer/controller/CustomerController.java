package pl.adamd.crm.api.customer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.adamd.crm.api.customer.dto.CustomerDto;
import pl.adamd.crm.api.customer.dto.CustomerDtoList;
import pl.adamd.crm.api.customer.entity.Customer;
import pl.adamd.crm.api.customer.service.CustomerViewService;

import java.util.List;

@Controller
@RequestMapping("/api/auth/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerViewService viewService;


    @GetMapping("/all")
    public ResponseEntity<List<CustomerDtoList>> getAllClients() {
        return viewService.getAll();
    }

    @GetMapping("/by-id/{id}")
    ResponseEntity<Customer> getClientById(@PathVariable Long id) {
        return viewService.getClientById(id);
    }

    @GetMapping("/by-name")
    ResponseEntity<List<CustomerDtoList>> getClientByName(@RequestParam("name") String name) {
        return viewService.getByName(name);
    }

    @GetMapping("/list/users-with-agreement")
    ResponseEntity<List<Customer>> getAgreementClientList() {
        return viewService.getListOfClientsWithAgreement();
    }

    @PutMapping("/add")
    public ResponseEntity<Customer> createNewClient(@RequestBody CustomerDto customer) {
        return viewService.addNewClient(customer);
    }

    @PatchMapping("/update/{id}")
    ResponseEntity<Customer> updateClient(@PathVariable Long id, @RequestBody CustomerDto updateClientDetails) {
        return viewService.updateClient(id, updateClientDetails);
    }


}
