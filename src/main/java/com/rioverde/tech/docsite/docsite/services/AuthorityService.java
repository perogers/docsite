package com.rioverde.tech.docsite.docsite.services;

import com.rioverde.tech.docsite.docsite.domain.Authority;

import java.util.Set;

public interface AuthorityService {
    public Set<Authority> getAuthorities();
    public Authority findById(Long id);
}
