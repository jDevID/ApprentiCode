package dev.id.backend.logic.security.models.entities;

import dev.id.backend.logic.dtos.BaseDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Permission extends BaseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;

}
