package pl.adamd.crm.api.config.employee.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.adamd.crm.api.config.employee.entity.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EmployeeDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    private boolean active;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static EmployeeDetailsImpl build(Employee user) {
        List<GrantedAuthority> authorities = user.getRoles()
                                                 .stream()
                                                 .map(role -> new SimpleGrantedAuthority(role.getName()
                                                                                             .name()))
                                                 .collect(Collectors.toList());

        return new EmployeeDetailsImpl(user.getId(), user.getEmployeeName(), user.getEmail(), user.getActive(),
                user.getPassword(),
                                       authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeDetailsImpl user = (EmployeeDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
