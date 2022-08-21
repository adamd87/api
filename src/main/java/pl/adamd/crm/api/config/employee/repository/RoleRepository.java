package pl.adamd.crm.api.config.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamd.crm.api.config.employee.entity.ERole;
import pl.adamd.crm.api.config.employee.entity.Role;


import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
