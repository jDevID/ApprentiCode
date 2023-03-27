package dev.id.backend.logic.dtos.specifics;

import dev.id.backend.logic.dtos.BaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import dev.id.backend.logic.security.models.entities.Role;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto implements Serializable {
    @NotBlank
    @Size(min = 2, max = 50)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastname;

    @Email
    @NotBlank
    private String email;

    private String password;

    private Role role;

    private List<ProjectDto> projects;
}