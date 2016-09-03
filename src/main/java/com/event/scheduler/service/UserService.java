package com.event.scheduler.service;

import com.event.scheduler.entity.User;
import com.event.scheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "userService")
public class UserService {
    @Autowired private UserRepository userRepository;

    @Transactional
    public void add(final User user) {
        userRepository.add(user);
    }

    @Transactional
    public void delete(final String key) {
        userRepository.delete(key);
    }

    @Transactional
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
