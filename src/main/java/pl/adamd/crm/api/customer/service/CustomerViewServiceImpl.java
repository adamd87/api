package pl.adamd.crm.api.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.adamd.crm.api.customer.dto.CustomerDto;
import pl.adamd.crm.api.customer.entity.Customer;
import pl.adamd.crm.api.customer.mapper.CustomerMapper;

import java.util.ArrayList;
import java.util.List;

import static pl.adamd.crm.api.common.Utils.setIfNotNull;


@Service
public class CustomerViewServiceImpl implements CustomerViewService {

    @Autowired
    CustomerService service;
    @Autowired
    CustomerMapper mapper;

    @Override
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<Customer> customers = service.findAll();

        return ResponseEntity.ok(mapper.mapCustomerListToDto(customers));
    }

    @Override
    public ResponseEntity<CustomerDto> getClientById(Long clientId) {
        Customer customer = service.findById(clientId);
        return ResponseEntity.ok(mapper.mapCustomerToDto(customer));
    }

    @Override
    public ResponseEntity<CustomerDto> addNewClient(CustomerDto request) {

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

        return ResponseEntity.ok(mapper.mapCustomerToDto(customer));
    }


    @Override
    public ResponseEntity<CustomerDto> updateClient(Long id, CustomerDto request) {
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

        return ResponseEntity.ok(mapper.mapCustomerToDto(customer));
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getListOfClientsWithAgreement() {
        List<Customer> getAllClients = service.findAll();
        List<Customer> result = new ArrayList<>();

        for (Customer customer : getAllClients) {
            if (customer.isAgreement()){
                result.add(customer);
            }
        }

        return ResponseEntity.ok(mapper.mapCustomerListToDto(result));
    }
}
