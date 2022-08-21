package pl.adamd.crm.api.config.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String employeeName;

    private String email;

    private String password;

    @OneToMany
    private Set<Role> roles = new HashSet<>();

    public Employee(String employeeName, String email, String password) {
        super();
        this.employeeName = employeeName;
        this.email = email;
        this.password = password;
    }

}
