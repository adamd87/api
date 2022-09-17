package pl.adamd.crm.api.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoList {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String info;
    private boolean agreement;
    private boolean business;
    private String nip;
    private String address;
}
