package com.rioverde.tech.docsite.services;

import com.rioverde.tech.docsite.domain.Document;
import com.rioverde.tech.docsite.exceptions.NotFoundException;
import com.rioverde.tech.docsite.repositories.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;


    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Set<Document> getDocuments() {
        Iterable<Document> documentIterable = documentRepository.findAll();
        Set<Document> documents = new HashSet<>();

        if(documentIterable == null ) {
            log.error("Failure returning documents from repository");
            throw new NotFoundException("No documents found");
        }

        documentIterable.forEach(documents::add);
        log.debug("Got document qty: " + documents.size());

        return documents;
    }

    @Override
    public Document findById(Long id) {
        log.debug("Find by ID: " + id);
        Document document = documentRepository.findOne(id);
        if(document == null) {
            log.error("Document not found for ID: " + id);
            throw new NotFoundException("Document not found for ID: " + id);
        }
        return document;
    }
}
