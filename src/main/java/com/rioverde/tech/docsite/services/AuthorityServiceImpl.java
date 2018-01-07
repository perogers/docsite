package com.rioverde.tech.docsite.services;

import com.rioverde.tech.docsite.domain.Authority;
import com.rioverde.tech.docsite.exceptions.NotFoundException;
import com.rioverde.tech.docsite.repositories.AuthorityPagingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityPagingRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityPagingRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Set<Authority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        Iterable<Authority> authorityIterable = authorityRepository.findAll(new Sort(Sort.DEFAULT_DIRECTION, "state", "county", "city")) ;
        if( authorityIterable == null ) {
            log.error("No authorities found");
            return  authorities;
        }
        authorityIterable.forEach(authorities::add);

        return authorities;
    }

    @Override
    public Authority findById(Long id) {
        Authority authority = authorityRepository.findOne(id);
        if( authority == null) {
            log.error("No authority for ID: " + id);
            throw new NotFoundException("No authority for ID: " + id);
        }
        return authority;
    }
}
