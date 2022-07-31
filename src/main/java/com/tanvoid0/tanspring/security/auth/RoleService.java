package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.security.auth.Role;

import java.util.Set;

public interface RoleService {
  Role findOrCreateRole(String name);

  Set<Role> findOrCreateRoles(Set<String> roles);
}
