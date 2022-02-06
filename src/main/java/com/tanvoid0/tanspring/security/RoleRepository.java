package com.tanvoid0.tanspring.security;

import com.tanvoid0.tanspring.enums.ERole;
import com.tanvoid0.tanspring.security.models.Role;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}