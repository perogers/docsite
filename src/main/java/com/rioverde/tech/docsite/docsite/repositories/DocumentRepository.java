package com.rioverde.tech.docsite.docsite.repositories;

import com.rioverde.tech.docsite.docsite.domain.Document;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentRepository extends PagingAndSortingRepository<Document, Long> {


}
