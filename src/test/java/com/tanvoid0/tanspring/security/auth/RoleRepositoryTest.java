//package com.tanvoid0.tanspring.security.auth;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//@DataJpaTest
//class RoleRepositoryTest {
//
//  @Autowired
//  private RoleRepository repository;
//
//  @AfterEach
//  void tearDown() {
//    repository.deleteAll();
//  }
//
//  @Test
//  void findByName() {
//    // given
////    Role role = Role.builder().id(-1L).name("ROLE_USER").build();
////    repository.save(role);
////
////    // when
////    Optional<Role> result = repository.findByName("ROLE_USER");
////
////    // then
////    assertThat(result).isEqualTo("ROLE_USER");
//  }
//
//  @Test
//  void existsByName() {
//  }
//}