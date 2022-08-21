package pl.adamd.crm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.adamd.crm.api.config.employee.entity.ERole;
import pl.adamd.crm.api.config.employee.entity.Role;
import pl.adamd.crm.api.config.employee.repository.RoleRepository;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			if (roleRepository.findAll().isEmpty()) {
				Role employeeRole = new Role();
				employeeRole.setName(ERole.ROLE_EMPLOYEE);
				roleRepository.save(employeeRole);
				Role adminRole = new Role();
				adminRole.setName(ERole.ROLE_ADMIN);
				roleRepository.save(adminRole);
				Role fitterRole = new Role();
				fitterRole.setName(ERole.ROLE_FITTER);
				roleRepository.save(fitterRole);
			}
		} catch (Exception ignored) {

		}

	}

}
