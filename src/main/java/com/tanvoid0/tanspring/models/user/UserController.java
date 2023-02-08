package com.tanvoid0.tanspring.models.user;

import com.tanvoid0.tanspring.models.user.hobby.HobbyService;
import com.tanvoid0.tanspring.models.user.hobby.HobbyVO;
import com.tanvoid0.tanspring.models.user.hobby.NewHobbyVO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  UserService userService;

  @Autowired
  HobbyService hobbyService;

  @GetMapping("/hobby/{username}")
  public List<HobbyVO> getHobbies(
      @PathVariable("username") final String username
  ) {
    return hobbyService.findByUsername(username);
  }

  /// Create Methods
  @PostMapping("/hobby/{username}")
  public HobbyVO addHobby(
      @PathVariable("username") final String username,
      @RequestBody final NewHobbyVO newVO) {
    return hobbyService.add(username, newVO);
  }
}
