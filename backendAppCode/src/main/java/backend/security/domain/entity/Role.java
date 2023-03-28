package backend.security.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;


    public static final Role USER = new Role(null, "USER", "User role description", new HashSet<>());
    public static final Role ADMIN = new Role(null, "ADMIN", "Admin role description", new HashSet<>());


    public static void initializeRoles(Set<Permission> userPermissions, Set<Permission> adminPermissions) {
        USER.setPermissions(userPermissions);
        ADMIN.setPermissions(adminPermissions);
    }

    // Add a constructor that doesn't require permissions
    public Role(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Create a method to create a new Role instance with the given permissions
    public Role withPermissions(Set<Permission> permissions) {
        return new Role(this.id, this.name, this.description, permissions);
    }
}
