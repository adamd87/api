package pl.adamd.crm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.adamd.crm.api.config.employee.entity.ERole;
import pl.adamd.crm.api.config.employee.entity.Role;
import pl.adamd.crm.api.config.employee.repository.RoleRepository;

@SpringBootApplication
public class ApiApplication
        implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args)
            throws InterruptedException {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args)
            throws Exception {
        try {
            if (roleRepository.findAll()
                              .isEmpty()) {
                Role role = new Role();
                role.setName(ERole.ROLE_EMPLOYEE);
                roleRepository.save(role);
                Role role2 = new Role();
                role2.setName(ERole.ROLE_ADMIN);
                roleRepository.save(role2);
                Role role3 = new Role();
                role2.setName(ERole.ROLE_FITTER);
                roleRepository.save(role3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
