package pl.adamd.crm.api.config.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamd.crm.api.config.employee.entity.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeName(String employeeName);

    Optional<Employee> findByEmail(String email);

    Boolean existsByEmployeeName(String employeeName);

    Boolean existsByEmail(String email);
}
