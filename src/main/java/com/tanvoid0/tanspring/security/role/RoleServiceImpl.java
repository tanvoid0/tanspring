package com.tanvoid0.tanspring.security.role;

import com.tanvoid0.tanspring.core.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

  @Autowired
  RoleRepository repository;

  @Override
  public Role findByName(ERole name) {
    return repository.findByName(name).orElseThrow(() -> new NotFoundException("Role", "name", name.toString()));
  }

  @Override
  public Set<Role> findByNameIn(Set<ERole> name) {
    return repository.findByNameIn(name);
  }


}
