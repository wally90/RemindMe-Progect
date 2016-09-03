package com.event.scheduler.exception.repository;

public class NullKeyException extends DatabaseException {

    public NullKeyException(String message) {
        super(message);
    }
}
