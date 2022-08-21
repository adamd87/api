package pl.adamd.crm.api.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamd.crm.api.customer.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
