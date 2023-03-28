package domain.validation;

import exceptions.GenericValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DtoValidationImpl<D extends Serializable> implements DtoValidation<D> {

    private final Validator validator;

    public DtoValidationImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public DtoValidationImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validate(D dto) {
        Set<ConstraintViolation<D>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            for (ConstraintViolation<D> violation : violations) {
                String field = violation.getPropertyPath().toString();

                String message = violation.getMessage();
                errors.put(field, message);
            }
            throw new GenericValidationException(errors);
        }
    }
}
