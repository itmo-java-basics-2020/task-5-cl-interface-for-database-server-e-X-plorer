package ru.andrey.kvstorage.exception;

public class DatabaseCommandArgumentException extends DatabaseException {
    public DatabaseCommandArgumentException(String message) {
        super(message);
    }
}
