package pl.adamd.crm.api.config.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employee;

}
