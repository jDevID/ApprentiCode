package domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexityDto extends BaseDto implements Serializable {
    @Size(min = 3, max = 50, message = "min = 3, max = 50 characters")
    @NotBlank(message = "Name must not be blank")
    private String name;
    private List<ResourceDto> resources;

    @NotNull(message = "There must be a project associated")
    private ProjectDto project;
}
