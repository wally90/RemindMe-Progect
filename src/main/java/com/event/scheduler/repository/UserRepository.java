package com.event.scheduler.repository;

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

@Repository(value = "userRepository")
public class UserRepository implements IRepository<User, String> {
    private static final Logger LOG = Logger.getLogger(UserRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User user) throws DatabaseException {
        if (user == null) {
            new NullEntityException("User entity is null");
        }

        try {
            this.jdbcTemplate.update("INSERT INTO users (username, password, enabled, first_name, second_name) VALUES (?, ?, ?, ?, ?);", new Object[]{
                    user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    user.getFirstName(),
                    user.getSecondName()
            });
        } catch (Exception e) {
            LOG.error(e);
            throw new ErrorQueryException("Failed to execute the request for to add a new user entity into database!");
        }
    }

    @Override
    public void delete(String key) throws DatabaseException {
        if (key == null) {
            new NullKeyException("Key for delete user from database is null!");
        }

        try {
            this.jdbcTemplate.update("DELETE FROM users WHERE username=?;", new Object[]{ key });
        } catch (Exception e) {
            LOG.error(e);
            throw new ErrorQueryException("Failed to execute the request on delete user entity from database!");
        }
    }

    @Override
    public List<User> getAll() throws DatabaseException {
        try {
            return this.jdbcTemplate.query("SELECT username AS username, password AS password, enabled AS enabled, first_name AS firstName, second_name AS secondName FROM users;",
                    new BeanPropertyRowMapper<User>(User.class));
        } catch (Exception e) {
            LOG.error("Failed to execute the request for getting all users from database!");
            return Collections.<User>emptyList();
        }
    }
}
