package pl.adamd.crm.api.config.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportEmployees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String employeeName;

    private String token;

    private LocalDateTime loginTime;

    private LocalDateTime logoutTime;


}
