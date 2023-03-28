package exceptions;

public class EntityOperationException extends RuntimeException {
    private final OperationType operationType;

    public EntityOperationException(String message, OperationType operationType) {
        super(message);
        this.operationType = operationType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public enum OperationType {
        DELETE,
        UPDATE
    }
}
