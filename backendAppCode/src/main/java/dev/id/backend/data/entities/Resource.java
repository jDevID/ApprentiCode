package dev.id.backend.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "resource")
public class Resource extends BaseEntity {
    @NotBlank(message = "Title can't be blank")
    @Size(min = 3, max = 50, message = "min = 3, max = 50 characters")
    @Column(name = "title", nullable = false)
    private String title;
    @NotNull(message = "url can't be null")
    @NotBlank(message = "url can't be blank")
    @Column(name = "link")
    private String url;
    @Column(name = "type")
    @Size(min = 3, max = 25, message = "min = 3, max = 25 characters")
    private String resourceType;

    @ManyToOne
    @JoinColumn(name = "complexity_id")
    private Complexity complexity;

}
