package com.rioverde.tech.docsite.docsite.controllers;

import com.rioverde.tech.docsite.docsite.domain.Document;
import com.rioverde.tech.docsite.docsite.services.DocumentService;
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

public class DocumentControllerTest {

    @Mock
    DocumentService service;

    @Mock
    DocumentController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new DocumentController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllDocumentsTest() throws Exception {

        // Given
        Long id = new Long(2L);
        Document document = new Document();
        document.setId(id);
        Set<Document> documents = new HashSet<>();
        documents.add(document);

        // When
        when(service.getDocuments()).thenReturn(documents);

        // Then
        mockMvc.perform(get("/documents"))
                .andExpect(status().isOk())
                .andExpect(view().name("documents/list"))
                .andExpect(model().attributeExists("documents"));

    }
}
