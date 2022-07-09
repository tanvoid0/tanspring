package com.tanvoid0.tanspring.models.user.service;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.tanvoid0.tanspring.models.user.models.NewUserVO;
import com.tanvoid0.tanspring.models.user.models.UpdateUserVO;
import com.tanvoid0.tanspring.models.user.models.UserVO;
import com.tanvoid0.tanspring.security.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @Autowired
  JwtUtils jwtUtils;

  @GetMapping("")
  public List<UserVO> getAll() {
    log.info("Return all users");
    return service.getAll();
  }

  @GetMapping("/{id}")
  public UserVO get(
      @PathVariable("id") final String id
  ) {
    log.info("Request to return user with id {}", id);
    return service.get(id);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public UserVO create(
      @Valid @RequestBody final NewUserVO newUserVO
  ) {
    log.info("Request to create a new user.");
    log.debug("Inbound payload is{}", newUserVO.toString());

    return service.create(newUserVO);
  }

  @PutMapping("/{id}")
  public UserVO update(@PathVariable("id") final String id, @Valid @RequestBody final UpdateUserVO updateVO) {
    log.info("Request to update a user with id {}", id);
    log.debug("Inbound payload is {}", updateVO.toString());
    return service.update(id, updateVO);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") final String id) {
    log.info("Request to delete a user with id {}", id);
    return service.delete(id);
  }

}
