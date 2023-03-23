package dev.id.backend.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "tags")
public class Tag extends BaseEntity {
    @Column(name = "name")
    @NotBlank(message = "Tag name cannot be blank")
    @NotNull(message = "Tag name cannot be null")
    private String name;
    @NotBlank(message = "Operating system must not be blank")
    private String os;
    @Column(name = "description")
    @NotBlank(message = "Tag description cannot be blank")
    @Size(min = 3, max = 75, message = "min = 3, max = 75 characters")
    private String description;
    @ManyToMany(mappedBy = "tags")
    @ToString.Exclude
    private List<Entry> entries;
}
