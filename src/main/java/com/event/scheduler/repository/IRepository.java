package com.event.scheduler.repository;

import com.event.scheduler.exception.repository.DatabaseException;

import java.util.List;

public interface IRepository <Entity, Key> {
    void add(final Entity entity) throws DatabaseException;
    void delete(final Key key) throws  DatabaseException;

    List<Entity> getAll() throws DatabaseException;
}
