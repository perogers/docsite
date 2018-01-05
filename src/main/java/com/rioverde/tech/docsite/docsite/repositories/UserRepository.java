package com.rioverde.tech.docsite.docsite.repositories;


import com.rioverde.tech.docsite.docsite.domain.User;
import com.rioverde.tech.docsite.docsite.domain.UserStatus;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{


    Optional<User> findByUserIdAndAndUserStatus(String userId, UserStatus userStatus);
}
