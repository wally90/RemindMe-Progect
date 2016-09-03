package com.event.scheduler.repository;

import com.event.scheduler.entity.EventPoint;
import com.event.scheduler.entity.User;
import com.event.scheduler.exception.repository.DatabaseException;
import com.event.scheduler.exception.repository.ErrorQueryException;
import com.event.scheduler.exception.repository.NullEntityException;
import com.event.scheduler.exception.repository.NullKeyException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository(value = "eventPointRepository")
public class EventPointRepository implements IRepository<EventPoint, Integer> {
    private static final Logger LOG = Logger.getLogger(EventPointRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(EventPoint eventPoint) throws DatabaseException {
        if (eventPoint == null) {
            new NullEntityException("Event point entity is null");
        }

        try {
            this.jdbcTemplate.update("INSERT INTO event_points (event_point_name) VALUES (?);", new Object[]{
                    eventPoint.getEventPointName()
            });
        } catch (Exception e) {
            LOG.error(e);
            throw new ErrorQueryException("Failed to execute the request for to add a new event point entity into database!");
        }
    }

    @Override
    public void delete(Integer key) throws DatabaseException {
        if (key == null) {
            new NullKeyException("Key for delete event point from database is null!");
        }

        try {
            this.jdbcTemplate.update("DELETE FROM event_points WHERE id=?;", new Object[]{ key });
        } catch (Exception e) {
            LOG.error(e);
            throw new ErrorQueryException("Failed to execute the request on delete event point entity from database!");
        }
    }

    @Override
    public List<EventPoint> getAll() throws DatabaseException {
        try {
            return this.jdbcTemplate.query("SELECT id AS id, event_point_name AS eventPointName FROM event_points;", new BeanPropertyRowMapper<EventPoint>(EventPoint.class));
        } catch (Exception e) {
            LOG.error("Failed to execute the request for getting all event points from database!");
            return Collections.<EventPoint>emptyList();
        }
    }
}
