package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.models.user.UserVO;
import com.tanvoid0.tanspring.models.user.career.CareerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin(maxAge = 3600)
@Api(value = "Auth controller exposes signin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService service;

  private final CareerService careerService;

  @PostMapping("/login")
  public UserVO login(@RequestBody LoginUserVO loginUserVO) {
    return service.login(loginUserVO);
  }

  @PostMapping("/register")
  public UserVO registerUser(@Valid @RequestBody NewUserVO newUserVO) {
    return service.register(newUserVO);
  }

  @PostMapping("/authenticate")
  public UserVO authenticate(HttpServletRequest request) {
    return service.authenticate(request);
  }

//  @ApiOperation("Get ALl users")
//  @GetMapping
//  public List<UserVO> getAll() {
//    log.info("Find all users");
//    return userService.getAll();
//  }
//
//  @ApiOperation("Get user")
//  @GetMapping("/{id}")
//  public UserVO get(@PathVariable("id") final long id) {
//    log.info("Find user with id {}", id);
//    return userService.getUserVOByUsername(id);
//  }
//
//  @ApiOperation("Get User career")
//  @GetMapping("/career")
//  public CareerVO getCareer() {
//    log.info("Find career for authenticated user");
//    return careerService.getCareer();
//  }
//
//  @ApiOperation(value = "Update User")
//  @PutMapping("/{id}")
//  public UserVO updateUser(@PathVariable("id") final long id, @RequestBody UpdateUserVO updateVO) {
//    log.info("Updating user {}", id);
//    updateVO.setId(id);
//    return userService.update(updateVO);
//  }
//
//  @ApiOperation(value = "Delete User")
//  @DeleteMapping("/{id}")
//  public boolean deleteUser(@PathVariable("id") final long id) {
//    log.info("Deleting user with id {}", id);
//    return userService.delete(id);
//  }
//
//  @ApiOperation(value = "REST API to fetch portfolio data")
//  @GetMapping("/portfolio/{username}")
//  public UserVO getPortfolioByUsername(@PathVariable("username") String username) {
//    log.info("Find Portfolio details for username {}", username);
//    return userService.getPortfolio(username);
//  }
//
//  @GetMapping("/career/{username}")
//  public CareerVO getCareerByUsername(
//      @PathVariable("username") String username
//  ) {
//    return careerService.getByUsername(username);
//  }
}
