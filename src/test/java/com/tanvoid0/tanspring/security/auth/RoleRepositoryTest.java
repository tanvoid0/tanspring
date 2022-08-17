package com.tanvoid0.tanspring.security.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {RoleRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.tanvoid0.tanspring.security.auth"})
@DataJpaTest
class RoleRepositoryTest {
  @Autowired
  private RoleRepository roleRepository;

  /**
   * Method under test: {@link RoleRepository#findByName(String)}
   */
  @Test
  void testFindByName() {
    Role role = new Role();
    role.setName("Name");

    Role role1 = new Role();
    role1.setName("Name");
    roleRepository.save(role);
    roleRepository.save(role1);
    assertFalse(roleRepository.findByName("foo").isPresent());
  }

  /**
   * Method under test: {@link RoleRepository#existsByName(String)}
   */
  @Test
  void testExistsByName() {
    Role role = new Role();
    role.setName("Name");

    Role role1 = new Role();
    role1.setName("Name");
    roleRepository.save(role);
    roleRepository.save(role1);
    assertFalse(roleRepository.existsByName("foo"));
  }

  /**
   * Method under test: {@link RoleRepository#existsByName(String)}
   */
  @Test
  void testExistsByName2() {
    Role role = new Role();
    role.setName("42");

    Role role1 = new Role();
    role1.setName("Name");
    roleRepository.save(role);
    roleRepository.save(role1);
    assertTrue(roleRepository.existsByName("42"));
  }
}