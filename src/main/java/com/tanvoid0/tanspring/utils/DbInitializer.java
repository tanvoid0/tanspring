//package com.tanvoid0.tanspring.utils;
//
//import com.tanvoid0.tanspring.security.auth.Role;
//import com.tanvoid0.tanspring.security.auth.RoleRepository;
//
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//public class DbInitializer {
//  private RoleRepository roleRepository;
//
//  public DbInitializer(RoleRepository roleRepository) {
//    this.roleRepository = roleRepository;
//  }
//
//  public Role findOrCreateRole(String name) {
//    return getRole(name, roleRepository);
//  }
//
//  public static Role getRole(String name, RoleRepository roleRepository) {
//    final Optional<Role> role = roleRepository.findByName(name);
//    if (!role.isPresent()) {
//      Role newRole = new Role();
//      newRole.setName(name);
//      roleRepository.save(newRole);
//      return newRole;
//    }
//    return role.get();
//  }
//
//  public Set<Role> findOrCreateRoles(Set<String> roles) {
//    return roles.stream().map(this::findOrCreateRole).collect(Collectors.toSet());
//  }
//}
