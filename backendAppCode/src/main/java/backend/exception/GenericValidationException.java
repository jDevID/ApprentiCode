package exceptions;

import java.util.Map;

public class GenericValidationException extends RuntimeException {
    private final Map<String, String> errors;

    public GenericValidationException(Map<String, String> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        return String.format("%s: %s", super.getMessage(), errors);
    }
}
