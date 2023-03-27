package dev.id.backend.logic.security.models.dtos;

import lombok.Data;

@Data
public class PermissionCreateDto {
    private String name;
    private String description;
}
