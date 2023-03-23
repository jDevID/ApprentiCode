package dev.id.backend.data.validation;

import java.io.Serializable;

public interface EntityValidator<D extends Serializable> {
    void validate(D dto);
}
