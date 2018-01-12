package com.rioverde.tech.docsite.controllers;

import com.rioverde.tech.docsite.domain.Authority;
import com.rioverde.tech.docsite.domain.Document;
import com.rioverde.tech.docsite.exceptions.NotFoundException;
import com.rioverde.tech.docsite.services.DocumentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
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

    @Test
    public void getAllDocumentsForAuthorityTest() throws Exception {

        // Given
        Long documentId = new Long(2L);
        Long authorityId = new Long(3L);
        Authority authority = new Authority();
        authority.setId(authorityId);

        Document document = new Document();
        document.setId(documentId);
        document.setAuthority(authority);
        Set<Document> documents = new HashSet<>();
        documents.add(document);
        authority.addDocument(document);

        // When
        when(service.getDocumentsForAuthority(authorityId)).thenReturn(documents);

        // Then
        mockMvc.perform(get("/documents/authority/" + authorityId + "/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("documents/list"))
                .andExpect(model().attributeExists("documents"));

    }

    @Test
    public void getDocumentTest() throws Exception {
        // Given
        Long id = new Long(2L);
        Document document = new Document();
        document.setId(id);

        // When
        when(service.findById(id)).thenReturn(document);

        // Then
        mockMvc.perform(get("/documents/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("documents/show"))
                .andExpect(model().attributeExists("document"));

    }

    @Test
    public void getDocumentsIdNotFoundTest() throws Exception {

        // When
        when(service.findById(anyLong())).thenThrow(NotFoundException.class);

        // Then
        mockMvc.perform(get("/documents/9999/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"))
                .andExpect(model().attributeExists("exception"));
    }


    @Test
    public void getDocumentsInvalidFormatIdTest() throws Exception {

        // When
        when(service.findById(anyLong())).thenReturn(new Document());

        // Then
        mockMvc.perform(get("/documents/XXXXX/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"))
                .andExpect(model().attributeExists("exception"));
    }
}
