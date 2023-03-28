package backend.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;
import java.util.List;

@ToString@Entity
@Getter@Setter
@RequiredArgsConstructor
@Table(name = "project")
public class Project extends BaseEntity {
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Complexity> complexities;

    @NotBlank(message = "Project name must not be blank")
    @Pattern(regexp = "^[A-Z]\\w*$", message = "Project name must start with an uppercase letter")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
