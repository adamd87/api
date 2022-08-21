package pl.adamd.crm.api.customer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.adamd.crm.api.customer.dto.CustomerDto;
import pl.adamd.crm.api.customer.service.CustomerViewService;


import java.util.List;

@Controller
@RequestMapping("/api/auth/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerViewService viewService;


    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllClients() {
       return viewService.getAll();
    }

    @GetMapping("/by-id/{id}")
    ResponseEntity<CustomerDto> getClientById(@PathVariable Long id) {
        return viewService.getClientById(id);
    }

    @GetMapping("/list/users-with-agreement")
    @PreAuthorize("hasRole('FITTER')")
    ResponseEntity<List<CustomerDto>> getAgreementClientList(){
        return viewService.getListOfClientsWithAgreement();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CustomerDto> createNewClient(@RequestBody CustomerDto customer) {
        return viewService.addNewClient(customer);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<CustomerDto> updateClient(@PathVariable Long id, @RequestBody CustomerDto updateClientDetails) {
        return viewService.updateClient(id, updateClientDetails);
    }



}
