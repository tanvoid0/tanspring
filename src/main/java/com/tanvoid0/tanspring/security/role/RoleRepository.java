package com.tanvoid0.tanspring.security.role;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);

  Set<Role> findByNameIn(Set<ERole> name);
}