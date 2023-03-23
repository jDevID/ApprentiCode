package dev.id.backend.logic.exceptions;

public class EntityUpdateException extends RuntimeException {
    public EntityUpdateException(String message) {
        super(message);
    }
}