package pl.adamd.crm.api.customer.service;


import pl.adamd.crm.api.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(Long clientId);

    void save(Customer customer);

    boolean existById(Long clientId);

    List<Customer> findAllByFullName(String name);
}
