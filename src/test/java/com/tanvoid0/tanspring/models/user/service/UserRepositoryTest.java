package com.tanvoid0.tanspring.models.user.service;

import static org.assertj.core.api.Assertions.*;

import com.tanvoid0.tanspring.models.user.models.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@DataMongoTest
class UserRepositoryTest {
  @Autowired
  private UserRepository repository;

//  @Autowired
//  private TestEntityManager entityManager;

  @BeforeEach
  void setUp() {
    username = "username";
    email = "email";
    password = "password";
    name = "name";

//    users = mockData();
  }

  @AfterEach
  void cleanUp() {
//    cleanData();
  }

  @Test
  void findAll() {
    final User user = User.builder()
        .username(username)
        .email(email)
        .password(password)
        .build();

    final User persistedUser = repository.save(user);
    assertThat(persistedUser.getUsername()).isEqualTo(username);
    repository.deleteById(persistedUser.getId());
  }

  @Test
  void findByUsername() {
  }

  @Test
  void existsByUsername() {
  }

  @Test
  void existsByEmail() {
  }

  private String username;
  private String password;
  private String email;
  private String name;
  private List<User> users;

  List<User> mockData() {
    final User user1 = User.builder()
        .username(username)
        .password(password)
        .email(email)
        .name(name)
        .build();

    final User user2 = User.builder()
        .username(username + "2")
        .password(password + "2")
        .email(email + "2")
        .name(name + "2")
        .build();

    users = repository.saveAll(List.of(user1, user2));
    return users;
  }

  void cleanData() {
    repository.deleteAllById(users.stream().map(User::getId).toList());
  }
}