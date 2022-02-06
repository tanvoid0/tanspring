package com.tanvoid0.tanspring.security;

import java.util.Optional;

import com.tanvoid0.tanspring.security.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}