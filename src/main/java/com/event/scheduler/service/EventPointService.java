package com.event.scheduler.service;

import com.event.scheduler.entity.EventPoint;
import com.event.scheduler.repository.EventPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "eventPointService")
public class EventPointService {
    @Autowired private EventPointRepository eventPointRepository;

    @Transactional
    public void add(final EventPoint eventPoint) {
        eventPointRepository.add(eventPoint);
    }

    @Transactional
    public void delete(final Integer key) {
        eventPointRepository.delete(key);
    }

    @Transactional
    public List<EventPoint> getAll() {
        return eventPointRepository.getAll();
    }
}
