//package com.tanvoid0.tanspring.security.auth;
//
//import com.tanvoid0.tanspring.security.auth.Role;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface RoleRepository extends JpaRepository<Role, Long> {
//  Optional<Role> findByName(String name);
//
//  boolean existsByName(String name);
//}
