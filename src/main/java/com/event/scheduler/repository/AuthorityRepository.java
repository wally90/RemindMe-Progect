package com.event.scheduler.repository;

import com.event.scheduler.entity.Authority;
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

@Repository(value = "authorityRepository")
public class AuthorityRepository implements IRepository<Authority, String> {
    private static final Logger LOG = Logger.getLogger(AuthorityRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Authority authority) throws DatabaseException {
        if (authority == null) {
            new NullEntityException("Authority entity is null");
        }

        try {
            this.jdbcTemplate.update("INSERT INTO authorities (username, authority) VALUES (?, ?);", new Object[]{
                    authority.getUsername(),
                    authority.getAuthority()
            });
        } catch (Exception e) {
            LOG.error(e);
            throw new ErrorQueryException("Failed to execute the request for to add a new authority entity into database!");
        }
    }

    @Override
    public void delete(String key) throws DatabaseException {
        if (key == null) {
            new NullKeyException("Key for delete authority from database is null!");
        }

        try {
            this.jdbcTemplate.update("DELETE FROM authorities WHERE username=?;", new Object[]{ key });
        } catch (Exception e) {
            LOG.error(e);
            throw new ErrorQueryException("Failed to execute the request on delete authority entity from database!");
        }
    }

    @Override
    public List<Authority> getAll() throws DatabaseException {
        try {
            return this.jdbcTemplate.query("SELECT username AS username, authority AS authority FROM authorities;", new BeanPropertyRowMapper<Authority>(Authority.class));
        } catch (Exception e) {
            LOG.error("Failed to execute the request for getting all authorities from database!");
            return Collections.<Authority>emptyList();
        }
    }
}
