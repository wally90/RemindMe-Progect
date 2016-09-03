package com.event.scheduler.service;

import com.event.scheduler.entity.Event;
import com.event.scheduler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service(value = "eventService")
public class EventService {
    @Autowired private EventRepository eventRepository;

    @Transactional
    public void add(final Event event, final String username) {
        event.setUsername(username);

        DateFormat formatter;
        formatter = new SimpleDateFormat("MM/dd/yyyy");

        String start= event.getStart();
        String end = event.getEnd();

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatter.parse(start);
            endDate = formatter.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timeStampStartDate = new Timestamp(startDate.getTime());
        Timestamp timeStampEndDate = new Timestamp(endDate.getTime());

        event.setStartDate(timeStampStartDate);
        event.setEndDate(timeStampEndDate);

        eventRepository.add(event);
    }

    @Transactional
    public void delete(final Integer key) {
        eventRepository.delete(key);
    }

    @Transactional
    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    @Transactional
    public List<Event> getAllByEventPointAndDate(final Integer eventPoint, final Timestamp startDate, final Timestamp endDate) {
        return eventRepository.getAllByEventPointAndDate(eventPoint, startDate, endDate);
    }
}
