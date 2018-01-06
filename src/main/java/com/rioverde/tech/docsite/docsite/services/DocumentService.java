package com.rioverde.tech.docsite.docsite.services;

import com.rioverde.tech.docsite.docsite.domain.Document;

import java.util.Set;

public interface DocumentService {
    Set<Document> getDocuments();
}
