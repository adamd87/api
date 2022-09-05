package pl.adamd.crm.api.config.employee.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.adamd.crm.api.config.employee.entity.Employee;
import pl.adamd.crm.api.config.employee.repository.EmployeeRepository;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String employeeName)
            throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmployeeName(employeeName)
                                              .orElseThrow(() -> new UsernameNotFoundException(
                                                      "Employee Not Found with username: " + employeeName));

        return EmployeeDetailsImpl.build(employee);
    }
}
