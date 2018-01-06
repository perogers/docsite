package com.rioverde.tech.docsite.services;

import com.rioverde.tech.docsite.domain.Document;
import com.rioverde.tech.docsite.repositories.DocumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class DocumentServiceTests {

    DocumentServiceImpl documentService;

    @Mock
    DocumentRepository documentRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        documentService = new DocumentServiceImpl(documentRepository);
    }

    @Test
    public void getDocumentsTest() {
        // Given
        Long id = new Long(2L);
        Document document = new Document();
        document.setId(id);
        Set<Document> documents = new HashSet<>();
        documents.add(document);
        when(documentRepository.findAll()).thenReturn(documents);

        // When
        Set<Document> documentSet = documentService.getDocuments();

        // Then
        assertNotNull(documentSet);
        verify(documentRepository, times(1)).findAll();
    }

}
