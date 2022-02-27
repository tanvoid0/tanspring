package com.tanvoid0.tanspring.models.user.service;

import lombok.extern.slf4j.Slf4j;

import com.tanvoid0.tanspring.models.user.models.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  UserServiceImpl service;

  @GetMapping("/")
  public List<UserVO> get() {
    log.info("Return all users");
    return service.get();
  }

}
