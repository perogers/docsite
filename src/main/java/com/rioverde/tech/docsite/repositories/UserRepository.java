package com.rioverde.tech.docsite.repositories;


import com.rioverde.tech.docsite.domain.User;
import com.rioverde.tech.docsite.domain.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {


    Optional<User> findByUserIdAndAndUserStatus(String userId, UserStatus userStatus);
}
