package com.event.scheduler.controller;

import com.event.scheduler.entity.EventPoint;
import com.event.scheduler.repository.EventPointRepository;
import com.event.scheduler.service.EventPointService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "eventPointController")
public class EventPointController {
    private static final Logger LOG = Logger.getLogger(EventPointController.class);

    @Autowired private EventPointService eventPointService;

    @RequestMapping(value = "/event_points", method = RequestMethod.GET)
    public String eventPoints(Model model) {
        try {
            model.addAttribute("event_point", eventPointService.getAll());
            return "/event_points";
        } catch (Exception e) {
            LOG.error(e);
            return "/event_points";
        }
    }

    @RequestMapping(value = "/event_points/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("event_point") EventPoint eventPoint, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "redirect:/login";
        }

        try {
            eventPointService.add(eventPoint);
            return "redirect:/event_points";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/event_points";
        }
    }

    @RequestMapping(value="/event_points/delete/{id}", method=RequestMethod.GET)
    public String inactive(@PathVariable Integer id, ModelMap modelMap) {

        try {
            eventPointService.delete(id);
            return "redirect:/event_points";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/event_points";
        }
    }
}
