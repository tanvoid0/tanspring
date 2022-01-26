package com.tanvoid0.tanspring.repository;

import com.tanvoid0.tanspring.models.ERole;
import com.tanvoid0.tanspring.models.Role;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}