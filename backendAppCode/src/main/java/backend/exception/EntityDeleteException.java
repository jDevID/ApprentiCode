package exceptions;

public class EntityDeleteException extends RuntimeException {
    public EntityDeleteException(String message) {
        super(message);
    }
}
