package com.rioverde.tech.docsite.docsite.controllers;

import com.rioverde.tech.docsite.docsite.services.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @RequestMapping({"/authority/{id}/show"})
    public String getAuthorityById(@PathVariable String id, Model model) {
        log.debug("Find by ID: " + id);
        model.addAttribute("authority", authorityService.findById(Long.valueOf(id)));

        return "authority/show";
    }

    @RequestMapping({"/authority"})
    public String getAuthorities(Model model) {
        log.debug("Get all");

        model.addAttribute("authorities", authorityService.getAuthorities());

        return "authority/list";
    }

}
