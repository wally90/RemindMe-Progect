package com.event.scheduler.service;

import com.event.scheduler.entity.Authority;
import com.event.scheduler.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "authorityService")
public class AuthorityService {
    @Autowired private AuthorityRepository authorityRepository;

    @Transactional
    public void add(final Authority authority) {
        authorityRepository.add(authority);
    }

    @Transactional
    public void delete(final String key) {
        authorityRepository.delete(key);
    }

    @Transactional
    public List<Authority> getAll() {
        return authorityRepository.getAll();
    }
}
