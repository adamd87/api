package pl.adamd.crm.api.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.adamd.crm.api.customer.entity.Customer;
import pl.adamd.crm.api.customer.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long clientId) {

        if (!customerRepository.findById(clientId)
                               .isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return customerRepository.findById(clientId)
                                 .get();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean existById(Long clientId) {
        return customerRepository.existsById(clientId);
    }

    @Override
    public List<Customer> findAllByFullName(String name) {
        List<Customer> customers = new ArrayList<>();

        Optional<List<Customer>> customerList = customerRepository.findAllByFullName(name);
        if (customerList.isPresent()) {
            customers = customerList.get();
        }
        return customers;
    }

}
