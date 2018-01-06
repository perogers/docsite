package com.rioverde.tech.docsite.docsite.repositories;

import com.rioverde.tech.docsite.docsite.domain.Authority;
import com.rioverde.tech.docsite.docsite.domain.State;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

public interface AuthorityPagingRepository extends PagingAndSortingRepository<Authority, Long> {

    Optional<Authority> findAuthorityByStateAndCounty(State state, String county);
    //Set<Authority> findAll(PageRequest pageRequest);
    //Set<Authority> findAll();
}
