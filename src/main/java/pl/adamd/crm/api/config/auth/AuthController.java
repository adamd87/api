package pl.adamd.crm.api.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.adamd.crm.api.config.auth.request.LoginRequest;
import pl.adamd.crm.api.config.auth.request.SignupRequest;
import pl.adamd.crm.api.config.auth.response.JwtResponse;
import pl.adamd.crm.api.config.auth.response.MessageResponse;
import pl.adamd.crm.api.config.employee.details.EmployeeDetailsImpl;
import pl.adamd.crm.api.config.employee.entity.ERole;
import pl.adamd.crm.api.config.employee.entity.Employee;
import pl.adamd.crm.api.config.employee.entity.Role;
import pl.adamd.crm.api.config.employee.repository.EmployeeRepository;
import pl.adamd.crm.api.config.employee.repository.RoleRepository;
import pl.adamd.crm.api.config.jwt.JwtUtils;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateEmployee
            (@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (loginRequest.getEmployeeName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        EmployeeDetailsImpl employeeDetails =
                (EmployeeDetailsImpl) authentication.getPrincipal();

        List<String> roles = employeeDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                employeeDetails.getId(),
                employeeDetails.getUsername(),
                employeeDetails.getEmail(), roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser
            (@RequestBody SignupRequest signUpRequest) {

        if (employeeRepository.existsByEmployeeName
                (signUpRequest.getEmployeeName())) {

            return ResponseEntity.badRequest()
                    .body(new MessageResponse
                            ("Error: EmployeeName is already taken!"));
        }

        if (employeeRepository
                .existsByEmail(signUpRequest.getEmail())) {

            return ResponseEntity.badRequest()
                    .body(new MessageResponse
                            ("Error: Email is already in use!"));
        }

        // Create new employee account
        Employee employee = new Employee(signUpRequest
                .getEmployeeName(), signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role employeeRole = roleRepository
                    .findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException
                            ("Error: Role is not found."));
            roles.add(employeeRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException
                                        ("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "fitter":
                        Role fitterRole = roleRepository
                                .findByName(ERole.ROLE_FITTER)
                                .orElseThrow(() -> new RuntimeException
                                        ("Error: Role is not found."));
                        roles.add(fitterRole);

                        break;
                    default:
                        Role defaultRole = roleRepository
                                .findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException
                                        ("Error: Role is not found."));
                        roles.add(defaultRole);
                }
            });
        }

        employee.setRoles(roles);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new MessageResponse
                ("Employee registered successfully!"));
    }

}
