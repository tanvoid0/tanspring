package com.tanvoid0.tanspring.models.user.service;

import com.tanvoid0.tanspring.core.exception.NotFoundException;
import com.tanvoid0.tanspring.models.user.exception.UserNotFoundException;
import com.tanvoid0.tanspring.models.user.models.NewUserVO;
import com.tanvoid0.tanspring.models.user.models.UpdateUserVO;
import com.tanvoid0.tanspring.models.user.models.User;
import com.tanvoid0.tanspring.security.role.ERole;
import com.tanvoid0.tanspring.security.role.Role;
import com.tanvoid0.tanspring.security.role.RoleRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userValidator")
public class UserValidatorImpl implements UserValidator {
  @Autowired
  UserRepository repository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  ModelMapper mapper;

  @Override
  public User validate(NewUserVO newVO) {

    final User entity = mapper.map(newVO, User.class);
    entity.setRoles(roleNameValidator(newVO.getRoles()));
    return entity;
  }

  @Override
  public User validate(UpdateUserVO updateVO) {
    final User user = this.existsById(updateVO.getId());
//    roleValidator(updateVO.getRoles());
    return user;
  }

  @Override
  public User existsById(String id) {
    return repository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id));
  }

  Set<Role> roleNameValidator(final Set<ERole> roles) {
    Set<Role> existingRoles = roleRepository.findByNameIn(roles);
    if (existingRoles.isEmpty()) {
      throw new NotFoundException("User Role", "roles", roles.toString());
    }
    return existingRoles;
  }

  Set<Role> roleValidator(final Set<Role> roles) {
    return roleNameValidator(roles.stream().map(Role::getName).collect(Collectors.toSet()));
  }
}
