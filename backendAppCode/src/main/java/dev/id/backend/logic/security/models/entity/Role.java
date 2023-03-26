package dev.id.backend.logic.security.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

}
