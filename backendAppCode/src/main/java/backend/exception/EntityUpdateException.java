package exceptions;

public class EntityUpdateException extends RuntimeException {
    public EntityUpdateException(String message) {
        super(message);
    }
}
