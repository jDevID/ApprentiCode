package domain.validation;

import java.io.Serializable;

public interface DtoValidation<D extends Serializable> {
    void validate(D dto);
}
