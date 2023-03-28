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
public class EntryDto extends BaseDto implements Serializable {
    @Size(min = 3, max = 100)
    @NotBlank(message = "Command must not be blank")
    @NotNull
    private String command;

    @Size(min = 3, max = 50, message = "min = 3, max = 100 characters")
    private String response;
    @Size(min = 3, max = 50, message = "min = 3, max = 100 characters")
    private String hint;
    @NotNull(message = "Complexity must not be null")
    private ComplexityDto complexity;
    @NotNull(message = "Tag must not be null")
    private TagDto tag;
}
