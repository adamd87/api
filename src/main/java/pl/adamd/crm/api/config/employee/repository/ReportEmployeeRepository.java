package pl.adamd.crm.api.config.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamd.crm.api.config.employee.entity.ReportEmployee;

import java.util.Optional;

@Repository
public interface ReportEmployeeRepository extends JpaRepository<ReportEmployee, Long> {
    Optional<ReportEmployee> findByToken(String token);
}
