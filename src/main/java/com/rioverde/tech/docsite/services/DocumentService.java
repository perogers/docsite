package com.rioverde.tech.docsite.services;

import com.rioverde.tech.docsite.domain.Document;

import java.util.Set;

public interface DocumentService {
    Set<Document> getDocuments();
}
