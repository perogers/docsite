package com.rioverde.tech.docsite.repositories;


import com.rioverde.tech.docsite.domain.User;
import com.rioverde.tech.docsite.domain.UserStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{


    Optional<User> findByUserIdAndAndUserStatus(String userId, UserStatus userStatus);
}
