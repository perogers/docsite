package com.rioverde.tech.docsite.services;

import com.rioverde.tech.docsite.domain.User;
import com.rioverde.tech.docsite.exceptions.NotFoundException;
import com.rioverde.tech.docsite.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<User> getAll() {
        log.debug("Get all users");
        Iterable<User> users = repository.findAll(new Sort(Sort.Direction.ASC, "lastName", "firstName"));

        if( users == null) {
            log.error("Error for all users");
            throw new NotFoundException("Error getting all users");
        }

        Set<User> userSet = new HashSet<>();
        users.forEach(userSet::add);

        return userSet;
    }

    @Override
    public User getUserById(Long id) {
        log.debug("Getting user for ID: " +id);
        User user = repository.findOne(id);
        if( user == null ) {
            String msg = String.format("No user found for ID: %d", id);
            log.error(msg);
            throw new NotFoundException(msg);
        }
        return user;
    }
}
