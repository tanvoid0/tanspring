package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;
import com.tanvoid0.tanspring.models.user.UpdateUserVO;
import com.tanvoid0.tanspring.models.user.UserService;
import com.tanvoid0.tanspring.models.user.UserVO;
import com.tanvoid0.tanspring.models.user.career.CareerService;
import com.tanvoid0.tanspring.models.user.career.CareerVO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(maxAge = 3600)
@Api(value = "Auth controller exposes signin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @Autowired
  private CareerService careerService;

  @Autowired
  private HttpServletRequest httpServletRequest;


  @ApiOperation(value = "REST API to Signin or Login user to Blog app")
  @PostMapping("/signin")
  public JWTAuthResponseVO authenticateUser(@RequestBody LoginUserVO loginUserVO) {
    return userService.login(loginUserVO);
  }

  @ApiOperation(value = "REST API to Register or Signup user to Blog app")
  @PostMapping("/signup")
  public UserVO registerUser(@Valid @RequestBody NewUserVO newUserVO) {
    return userService.register(newUserVO);
  }

  @ApiOperation("Get ALl users")
  @GetMapping
  public List<UserVO> getAll() {
    log.info("Find all users");
    return userService.getAll();
  }

  @ApiOperation("Get user")
  @GetMapping("/{id}")
  public UserVO get(@PathVariable("id") final long id) {
    log.info("Find user with id {}", id);
    return userService.get(id);
  }

  @ApiOperation("Get User career")
  @GetMapping("/career")
  public CareerVO getCareer() {
    log.info("Find career for authenticated user");
    return careerService.getCareer();
  }

  @ApiOperation(value = "Update User")
  @PutMapping("/{id}")
  public UserVO updateUser(@PathVariable("id") final long id, @RequestBody UpdateUserVO updateVO) {
    log.info("Updating user {}", id);
    updateVO.setId(id);
    return userService.update(updateVO);
  }

  @ApiOperation(value = "Delete User")
  @DeleteMapping("/{id}")
  public boolean deleteUser(@PathVariable("id") final long id) {
    log.info("Deleting user with id {}", id);
    return userService.delete(id);
  }

  @ApiOperation(value = "REST API to authenticate user using token")
  @PostMapping("/authenticate")
  @PreAuthorize("hasRole('USER')")
  public UserVO authenticate() {
    return userService.getAuthUserVO();
  }

  @ApiOperation(value = "REST API to fetch portfolio data")
  @GetMapping("/portfolio/{username}")
  public UserVO getPortfolioByUsername(@PathVariable("username") String username) {
    log.info("Find Portfolio details for username {}", username);
    return userService.getPortfolio(username);
  }

  @GetMapping("/career/{username}")
  public CareerVO getCareerByUsername(
      @PathVariable("username") String username
  ) {
    return careerService.getByUsername(username);
  }
}
