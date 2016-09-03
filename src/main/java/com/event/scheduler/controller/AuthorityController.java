package com.event.scheduler.controller;

import com.event.scheduler.service.AuthorityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "authorityController")
public class AuthorityController {
    private static final Logger LOG = Logger.getLogger(AuthorityController.class);

    @Autowired
    private AuthorityService authorityService;

    @RequestMapping(value = "/authorities", method = RequestMethod.GET)
    public String authorities(Model model) {
        try {
            model.addAttribute("authority", authorityService.getAll());
            return "/authorities";
        } catch (Exception e) {
            LOG.error(e);
            return "/authorities";
        }
    }
}
