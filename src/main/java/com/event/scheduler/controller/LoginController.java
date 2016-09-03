package com.event.scheduler.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("login")
public class LoginController {
    private static final Logger LOG = Logger.getLogger(LoginController.class);

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "/login";
    }
}
