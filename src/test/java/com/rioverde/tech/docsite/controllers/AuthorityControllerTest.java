package com.rioverde.tech.docsite.controllers;

import com.rioverde.tech.docsite.commands.AuthorityCommand;
import com.rioverde.tech.docsite.domain.Authority;
import com.rioverde.tech.docsite.exceptions.NotFoundException;
import com.rioverde.tech.docsite.services.AuthorityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorityControllerTest {

    @Mock
    AuthorityService service;

    AuthorityController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new AuthorityController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAuthorityByIdTest() throws Exception {
        Long id = new Long(2L);
        Authority authority = new Authority();
        authority.setId(id);

        when(service.findById(anyLong())).thenReturn(authority);

        mockMvc.perform(get("/authority/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("authority/show"))
                .andExpect(model().attributeExists("authority"));

    }

    @Test
    public void getAllAuthoritiesTest() throws Exception {
        Long id = new Long(2L);
        Authority authority = new Authority();
        authority.setId(id);

        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(authority);

        when(service.getAuthorities()).thenReturn(authoritySet);

        mockMvc.perform(get("/authority"))
                .andExpect(status().isOk())
                .andExpect(view().name("authority/list"))
                .andExpect(model().attributeExists("authorities"));
    }

    @Test
    public void getAuthorityByIdWrongIdTest() throws Exception {
        // Given
        when(service.findById(anyLong())).thenThrow(new NotFoundException("Bad ID"));

        // Then
        mockMvc.perform(get("/authority/2/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"))
                .andExpect(model().attributeExists("exception"));
    }


    @Test
    public void getAuthorityByIdBadIdTest() throws Exception {
        // Given
        when(service.findById(anyLong())).thenThrow(new NotFoundException("Bad ID"));

        // Then
        mockMvc.perform(get("/authority/zzzz/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"))
                .andExpect(model().attributeExists("exception"));
    }

    @Test
    public void getNewAuthorityFormTest() throws Exception {
        mockMvc.perform(get("/authority/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("authority/authorityform"))
                .andExpect(model().attributeExists("authority"));
    }


    @Test
    public void postNewAuthorityFormTest() throws Exception {
        AuthorityCommand command = new AuthorityCommand();
        command.setId(2L);

        when(service.saveAuthorityCommand(any())).thenReturn(command);

        mockMvc.perform(post("/authority")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("city", "Anytown")
                .param("state", "GA")
                .param("county", "Bumpkin")
                .param("url", "http://some.site.gov")
                .param("agency", "foo"))
            .andExpect(status().is3xxRedirection())
            .andExpect(model().attributeExists("authority"))
            .andExpect(view().name("redirect:/authority/2/show"));
    }

    @Test
    public void postNewAuthorityFormFailReqFieldTest() throws Exception {
        AuthorityCommand command = new AuthorityCommand();
        command.setId(2L);

        when(service.saveAuthorityCommand(any())).thenReturn(command);

        mockMvc.perform(post("/authority")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("city", "Anytown")
                .param("state", "GA")
                .param("county", "Bumpkin")
                .param("agency", "foo"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("authority"))
                .andExpect(view().name("authority/authorityform"));
    }
}
