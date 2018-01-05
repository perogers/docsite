package com.rioverde.tech.docsite.docsite.repositories;

import com.rioverde.tech.docsite.docsite.domain.Authority;
import com.rioverde.tech.docsite.docsite.domain.State;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Long>{

    Optional<Authority> findAuthorityByStateAndCounty(State state, String county);

}
