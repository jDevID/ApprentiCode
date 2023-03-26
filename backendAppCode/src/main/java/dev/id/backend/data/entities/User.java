package dev.id.backend.data.entities;


import dev.id.backend.logic.security.models.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "_user") //postgres already allocates a User Table
public class User extends BaseEntity implements UserDetails {
    private String lastname;
    private String firstname;
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING) //ordinal by default
    private Role role;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Project> projects;

    @Override // returns a list of roles
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name())); //take one
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; //
    }

    @Override
    public boolean isAccountNonExpired() { // false (expired) by default
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // f
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // f
        return true;
    }

    @Override
    public boolean isEnabled() { // f
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
