package com.event.scheduler.exception.repository;

public abstract class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }
}
