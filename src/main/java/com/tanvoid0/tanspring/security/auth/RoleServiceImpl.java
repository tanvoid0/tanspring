//package com.tanvoid0.tanspring.security.auth;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service("RoleService")
//public class RoleServiceImpl implements RoleService {
//
//  private RoleRepository roleRepository;
//
//  private ModelMapper mapper;
//
//  public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
//    this.roleRepository = roleRepository;
//    this.mapper = mapper;
//  }
//
//  @Override
//  public Role findOrCreateRole(String name) {
//    return getRole(name, roleRepository);
//  }
//
//  @Override
//  public Set<Role> findOrCreateRoles(Set<String> roles) {
//    return roles.stream().map(this::findOrCreateRole).collect(Collectors.toSet());
//  }
//}
