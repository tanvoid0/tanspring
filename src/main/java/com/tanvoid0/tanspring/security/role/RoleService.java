package com.tanvoid0.tanspring.security.role;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
  Role findByName(ERole name);

  Set<Role> findByNameIn(Set<ERole> name);
}