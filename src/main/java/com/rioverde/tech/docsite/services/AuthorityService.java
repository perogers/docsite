package com.rioverde.tech.docsite.services;

import com.rioverde.tech.docsite.commands.AuthorityCommand;
import com.rioverde.tech.docsite.domain.Authority;

import java.util.Set;

public interface AuthorityService {
    Set<Authority> getAuthorities();
    Authority findById(Long id);
    AuthorityCommand saveAuthorityCommand(AuthorityCommand command);
}
