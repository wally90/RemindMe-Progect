package com.event.scheduler.exception.repository;

public class NullEntityException extends DatabaseException {

    public NullEntityException(String message) {
        super(message);
    }
}
