package backend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "complexity")
public class Complexity extends BaseEntity {

    @Size(min = 3, max = 50, message = "min = 3, max = 50 characters")
    @NotBlank(message = "name must not be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "complexity", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Resource> resources;

    @OneToMany(mappedBy = "complexity")
    @ToString.Exclude
    private List<Entry> entries;
    @NotNull(message = "There must be a project associated")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @ToString.Exclude
    private Project project;

}
