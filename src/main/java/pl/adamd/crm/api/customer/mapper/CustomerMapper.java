package pl.adamd.crm.api.customer.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pl.adamd.crm.api.customer.dto.CustomerDto;
import pl.adamd.crm.api.customer.entity.Customer;


import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    CustomerDto mapCustomerToDto(Customer customer);
    List<CustomerDto> mapCustomerListToDto(List<Customer> addresses);

}
