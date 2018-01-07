package com.rioverde.tech.docsite.controllers;

import com.rioverde.tech.docsite.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping({"/users"})
    public String getUsers(Model model) {
        log.debug("Getting all users");

        model.addAttribute("users", service.getAll());

        return "users/list";
    }
}
