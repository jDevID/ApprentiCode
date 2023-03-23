package dev.id.backend.logic.exceptions;

public class EntityDeleteException extends RuntimeException {
    public EntityDeleteException(String message) {
        super(message);
    }
}
