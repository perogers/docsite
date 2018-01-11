package com.rioverde.tech.docsite.controllers;

import com.rioverde.tech.docsite.commands.AuthorityCommand;
import com.rioverde.tech.docsite.exceptions.NotFoundException;
import com.rioverde.tech.docsite.services.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.validation.Valid;

@Slf4j
@Controller
public class AuthorityController {

    private static final String AUTHORITY_FORM_URL = "authority/authorityform";
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

    @RequestMapping({"/authority/new"})
    public String newAuthority( Model model) {
        log.debug("Requesting authority form");
        model.addAttribute("authority", new AuthorityCommand());

        return AUTHORITY_FORM_URL;
    }


    @GetMapping({"/authority"})
    public String getAuthorities(Model model) {
        log.debug("Get all");

        model.addAttribute("authorities", authorityService.getAuthorities());

        return "authority/list";
    }

    @PostMapping({"/authority"})
    public String saveOrUpdateAuthority(@Valid @ModelAttribute("authority") AuthorityCommand command,
                                        BindingResult bindingResult) {
        log.debug("Adding / Updating authority");
        if(bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return AUTHORITY_FORM_URL;
        }

        AuthorityCommand savedCommand = authorityService.saveAuthorityCommand(command);

        return "redirect:/authority/" + savedCommand.getId() + "/show";
    }



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);


        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception){

        log.error("Handling Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("500error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
