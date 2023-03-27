package dev.id.backend.logic.security.models.dtos;

import lombok.Data;

@Data
public class PermissionDetailsDto {
    private String id;
    private String name;
    private String description;
}
