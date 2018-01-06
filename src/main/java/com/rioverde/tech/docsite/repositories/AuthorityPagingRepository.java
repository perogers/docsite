package com.rioverde.tech.docsite.repositories;

import com.rioverde.tech.docsite.domain.Authority;
import com.rioverde.tech.docsite.domain.State;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AuthorityPagingRepository extends PagingAndSortingRepository<Authority, Long> {

    Optional<Authority> findAuthorityByStateAndCounty(State state, String county);
    //Set<Authority> findAll(PageRequest pageRequest);
    //Set<Authority> findAll();
}
