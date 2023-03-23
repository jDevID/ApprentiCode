package dev.id.backend.data.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
@SuppressWarnings("unused") // TODO: SECURITY ALONG ANGULAR
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;
    @Email
    @Column(name = "mail")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Project> projects;
}
