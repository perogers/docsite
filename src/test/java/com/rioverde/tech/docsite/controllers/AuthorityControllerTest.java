package com.rioverde.tech.docsite.controllers;

import com.rioverde.tech.docsite.domain.Authority;
import com.rioverde.tech.docsite.services.AuthorityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorityControllerTest {

    @Mock
    AuthorityService service;

    @Mock
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
}
