package dev.id.backend.logic.dtos.specifics;

import dev.id.backend.logic.dtos.BaseDto;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused") // TODO: SECURITY ALONG ANGULAR
public class UserDto extends BaseDto implements Serializable {
    private String username;
    @Email
    private String email;
    private String password;
    private String role;
    private List<ProjectDto> projects;
}
