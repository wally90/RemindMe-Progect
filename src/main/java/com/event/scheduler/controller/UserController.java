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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "userController")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired private UserService userService;
    @Autowired private AuthorityService authorityService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String registration(Model model) {
        try {
            model.addAttribute("user", userService.getAll());
            return "/users";
        } catch (Exception e) {
            LOG.error(e);
            return "/users";
        }
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("user") User user, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "redirect:/login";
        }

        try {
            userService.add(user);
            authorityService.add(new Authority(user.getUsername(), "ROLE_USER"));
            return "redirect:/users";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/users";
        }
    }

    @RequestMapping(value="/users/delete/{username}", method=RequestMethod.GET)
    public String inactive(@PathVariable String username, ModelMap modelMap) {

        try {
            authorityService.delete(username);
            userService.delete(username);
            return "redirect:/users";
        } catch (Exception e) {
            LOG.error(e);
            return "redirect:/users";
        }
    }
}
