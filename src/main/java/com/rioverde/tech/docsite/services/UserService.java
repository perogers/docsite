package com.rioverde.tech.docsite.services;

import com.rioverde.tech.docsite.domain.User;

import java.util.Set;

public interface UserService {

    public Set<User> getAll();

    public User getUserById(Long id);

}
