package com.event.scheduler.repository;

import com.event.scheduler.entity.Event;
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
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Repository(value = "eventRepository")
public class EventRepository implements IRepository<Event, Integer> {
    private static final Logger LOG = Logger.getLogger(EventRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Event event) throws DatabaseException {
        if (event == null) {
            new NullEntityException("Event entity is null");
        }

        try {
            this.jdbcTemplate.update("INSERT INTO events (username, event_points_id, description, start_date, end_date) VALUES (?, ?, ?, ?, ?);", new Object[]{
                    event.getUsername(),
                    event.getEventPointId(),
                    event.getDescription(),
                    event.getStartDate(),
                    event.getEndDate()
            });
        } catch (Exception e) {
            LOG.error(e);
            throw new ErrorQueryException("Failed to execute the request for to add a new event entity into database!");
        }
    }

    @Override
    public void delete(Integer key) throws DatabaseException {
        if (key == null) {
            new NullKeyException("Key for delete event from database is null!");
        }

        try {
            this.jdbcTemplate.update("DELETE FROM events WHERE id=?;", new Object[]{ key });
        } catch (Exception e) {
            LOG.error(e);
            throw new ErrorQueryException("Failed to execute the request on delete event entity from database!");
        }
    }

    @Override
    public List<Event> getAll() throws DatabaseException {
        try {
            return this.jdbcTemplate.query("SELECT id AS id, username AS username, event_points_id AS eventPointId, description AS description, start_date AS startDate, end_date AS endDate FROM events;", new BeanPropertyRowMapper<Event>(Event.class));
        } catch (Exception e) {
            LOG.error("Failed to execute the request for getting all events from database!");
            return Collections.<Event>emptyList();
        }
    }

    public List<Event> getAllByEventPointAndDate(final Integer eventPoint, final Timestamp startDate, final Timestamp endDate) throws DatabaseException {
        try {
            return this.jdbcTemplate.query("SELECT id AS id, username AS username, event_points_id AS eventPointId, description AS description, start_date AS startDate, end_date AS endDate FROM events WHERE event_points_id = ? AND start_date >= ? AND end_date <= ?;", new BeanPropertyRowMapper<Event>(Event.class), new Object[]{eventPoint, startDate, endDate});
        } catch (Exception e) {
            LOG.error("Failed to execute the request for getting all events from database!");
            return Collections.<Event>emptyList();
        }
    }
}
