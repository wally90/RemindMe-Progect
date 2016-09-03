package com.event.scheduler.controller;

import com.event.scheduler.entity.Event;
import com.event.scheduler.entity.EventPoint;
import com.event.scheduler.service.EventPointService;
import com.event.scheduler.service.EventService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller(value = "eventController")
public class EventController {
    private static final Logger LOG = Logger.getLogger(EventController.class);

    @Autowired private EventService eventService;
    @Autowired private EventPointService eventPointService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String registration(Model model) {
        try {
            List<EventPoint> eventPoint = eventPointService.getAll();
            model.addAttribute("eventPoint", eventPoint);

            List<Event> event = eventService.getAll();
            for (int i = 0; i < event.size(); i++) {
                for (int j = 0; j < eventPoint.size(); j++) {
                    if (event.get(i).getEventPointId() == eventPoint.get(j).getId()) {
                        event.get(i).setEventPoint(eventPoint.get(j).getEventPointName());
                        break;
                    }
                }
            }

            model.addAttribute("event", event);
            return "/events";
        } catch (Exception e) {
            LOG.error(e);
            return "/events";
        }
    }

    @RequestMapping(value = "/events/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("event") Event event, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "redirect:/events";
        }

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            eventService.add(event, username);
            return "redirect:/events";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/events";
        }
    }

    @RequestMapping(value="/events/delete/{id}", method=RequestMethod.GET)
    public String inactive(@PathVariable Integer id, ModelMap modelMap) {

        try {
            eventService.delete(id);
            return "redirect:/events";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/events";
        }
    }
}
