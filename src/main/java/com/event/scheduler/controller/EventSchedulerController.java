package com.event.scheduler.controller;

import com.event.scheduler.entity.Event;
import com.event.scheduler.entity.EventPoint;
import com.event.scheduler.service.EventPointService;
import com.event.scheduler.service.EventService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller(value = "eventSchedulerController")
public class EventSchedulerController {
    private static final Logger LOG = Logger.getLogger(EventSchedulerController.class);

    @Autowired private EventService eventService;
    @Autowired private EventPointService eventPointService;

    @RequestMapping(value = "/scheduler", method = RequestMethod.GET)
    public String eventPoints(Model model) {
        List<EventPoint> eventPoint = eventPointService.getAll();

        model.addAttribute("eventPoint", eventPoint);
        model.addAttribute("scheduler", eventService.getAll());

        return "/scheduler";
    }

    @RequestMapping(value = "/scheduler/find", method = RequestMethod.POST)
    public String add(@ModelAttribute("event") Event event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/scheduler";
        }

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            event.setUsername(username);

            // formatted date
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

            List<EventPoint> eventPoint = eventPointService.getAll();
            model.addAttribute("eventPoint", eventPoint);
            model.addAttribute("scheduler", eventService.getAllByEventPointAndDate(event.getEventPointId(), event.getStartDate(), event.getEndDate()));

            return "/scheduler";
        } catch (Exception e) {
            LOG.error(e);
            return "/scheduler";
        }
    }
}
