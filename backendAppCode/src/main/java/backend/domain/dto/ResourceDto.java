package domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto extends BaseDto implements Serializable {
    @NotBlank(message = "Title can't be blank")
    @Size(min = 3, max = 50, message = "min = 3, max = 50 characters")
    private String title;
    @NotNull(message = "url can't be null")
    @NotBlank(message = "url can't be blank")
    private String url;
    @Size(min = 3, max = 25, message = "min = 3, max = 25 characters")
    private String resourceType;
    @NotNull(message = "Complexity can't be null")
    private ComplexityDto complexity;
}
