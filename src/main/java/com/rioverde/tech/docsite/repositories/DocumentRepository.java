package com.rioverde.tech.docsite.repositories;

import com.rioverde.tech.docsite.domain.Document;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentRepository extends PagingAndSortingRepository<Document, Long> {

    Iterable<Document> findAllByAuthorityIdEquals(Long authorityId);
}
