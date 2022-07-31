//package com.tanvoid0.tanspring.models.user.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import com.tanvoid0.tanspring.models.user.models.User;
//import com.tanvoid0.tanspring.models.user.models.UserVO;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//@SpringBootTest
//@Rollback
//@ExtendWith(SpringExtension.class)
//class UserServiceTest {
//  @Inject
//  private UserService userService;
//
//  @Inject
//  private UserValidator userValidator;
//
//  @Inject
//  private UserRepository repository;
//
//  @Test
//  void getAll() {
////    List<User> users = UserTestUtilities.users;
////
////    when(repository.findAll()).thenReturn(users);
////    List<UserVO> testUsers = userService.getAll();
////
////    assertThat(testUsers.size()).isEqualTo(users.size());
//  }
//
//  @Test
//  void test() {
//  }
//
//  @Test
//  void create() {
//  }
//
//  @Test
//  void update() {
//  }
//
//  @Test
//  void delete() {
//  }
//}