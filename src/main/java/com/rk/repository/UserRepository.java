package com.rk.repository;

import com.rk.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {
    boolean existsByUsername(String username);

    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);
}
