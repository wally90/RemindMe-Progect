package com.event.scheduler.controller;

import com.event.scheduler.entity.Authority;
import com.event.scheduler.entity.User;
import com.event.scheduler.entity.UserRole;
import com.event.scheduler.repository.AuthorityRepository;
import com.event.scheduler.service.AuthorityService;
import com.event.scheduler.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "registration")
public class RegistrationController {
    private static final Logger LOG = Logger.getLogger(RegistrationController.class);

    @Autowired private UserService userService;
    @Autowired private AuthorityService authorityService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        return "/registration";
    }

    @RequestMapping(value = "/registration/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("user") User user, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "redirect:/login";
        }

        try {
            userService.add(user);
            authorityService.add(new Authority(user.getUsername(), "ROLE_USER"));
            return "redirect:/login";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/login";
        }
    }
}
