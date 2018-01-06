package com.rioverde.tech.docsite.services;

import com.rioverde.tech.docsite.domain.Document;
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
            return documents;
        }

        documentIterable.forEach(documents::add);
        log.debug("Got document qty: " + documents.size());

        return documents;
    }
}
