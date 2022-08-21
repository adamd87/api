package pl.adamd.crm.api.customer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String info;
    private boolean agreement;
    private boolean business;
    private String nip;

    private String street;
    private String buildingNumber;
    private String apartmentNumber;
    private String postCode;
    private String city;
    private String country;

//    @OneToMany(mappedBy = "customer")
//    private List<Address> addresses;
}
