package com.tanvoid0.tanspring.models.user.service;

import java.util.List;
import java.util.Optional;

import com.tanvoid0.tanspring.models.user.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  List<User> findAll();

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}