package pl.adamd.crm.api.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.adamd.crm.api.customer.dto.CustomerDto;
import pl.adamd.crm.api.customer.entity.Customer;

import java.util.ArrayList;
import java.util.List;

import static pl.adamd.crm.api.common.Utils.setIfNotNull;


@Service
public class CustomerViewServiceImpl implements CustomerViewService {

    @Autowired
    CustomerService service;

    @Override
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = service.findAll();

        return ResponseEntity.ok(customers);
    }

    @Override
    public ResponseEntity<Customer> getClientById(Long clientId) {
        Customer customer = service.findById(clientId);
        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<Customer> addNewClient(CustomerDto request) {

        Customer customer = Customer.builder()
                                    .fullName(request.getFullName())
                                    .phone(request.getPhone())
                                    .email(request.getEmail())
                                    .info(request.getInfo())
                                    .agreement(request.isAgreement())
                                    .business(request.isBusiness())
                                    .nip(request.getNip())
                                    .street(request.getStreet())
                                    .buildingNumber(request.getBuildingNumber())
                                    .apartmentNumber(request.getApartmentNumber())
                                    .postCode(request.getPostCode())
                                    .city(request.getCity())
                                    .country(request.getCountry())
                                    .build();

        service.save(customer);

        return ResponseEntity.ok(customer);
    }


    @Override
    public ResponseEntity<Customer> updateClient(Long id, CustomerDto request) {
        Customer customer = service.findById(id);

        setIfNotNull(request.getFullName(), customer::setFullName);
        setIfNotNull(request.getPhone(), customer::setPhone);
        setIfNotNull(request.getEmail(), customer::setEmail);
        setIfNotNull(request.getInfo(), customer::setInfo);
        setIfNotNull(request.isAgreement(), customer::setAgreement);
        setIfNotNull(request.isBusiness(), customer::setBusiness);
        setIfNotNull(request.getNip(), customer::setNip);
        setIfNotNull(request.getStreet(), customer::setStreet);
        setIfNotNull(request.getBuildingNumber(), customer::setBuildingNumber);
        setIfNotNull(request.getApartmentNumber(), customer::setApartmentNumber);
        setIfNotNull(request.getPostCode(), customer::setPostCode);
        setIfNotNull(request.getCity(), customer::setCity);
        setIfNotNull(request.getCountry(), customer::setCountry);

        service.save(customer);

        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<List<Customer>> getListOfClientsWithAgreement() {
        List<Customer> getAllClients = service.findAll();
        List<Customer> result = new ArrayList<>();

        for (Customer customer : getAllClients) {
            if (customer.isAgreement()) {
                result.add(customer);
            }
        }

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Customer>> getByName(String name) {
        List<Customer> customerList = service.findAllByFullName(name);

        ResponseEntity<List<Customer>> listResponseEntity;

        listResponseEntity = ResponseEntity.ok(customerList);


        return listResponseEntity;
    }
}
