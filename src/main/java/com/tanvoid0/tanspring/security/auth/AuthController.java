package com.tanvoid0.tanspring.security.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;
import com.tanvoid0.tanspring.common.vo.LoginUserVO;
import com.tanvoid0.tanspring.common.vo.SignUpUserVO;
import com.tanvoid0.tanspring.security.jwt.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(value = "Auth controller exposes signin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @ApiOperation(value = "REST API to Signin or Login user to Blog app")
  @PostMapping("/signin")
  public ResponseEntity<JWTAuthResponseVO> authenticateUser(@RequestBody LoginUserVO loginUserVO) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginUserVO.getUsernameOrEmail(), loginUserVO.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    // get token form tokenProvider
    String token = tokenProvider.generateToken(authentication);

    return ResponseEntity.ok(new JWTAuthResponseVO(token));
  }

  @ApiOperation(value = "REST API to Register or Signup user to Blog app")
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignUpUserVO signUpUserVO) {

    // add check for username exists in a DB
    if (userRepository.existsByUsername(signUpUserVO.getUsername())) {
      return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
    }

    // add check for email exists in DB
    if (userRepository.existsByEmail(signUpUserVO.getEmail())) {
      return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
    }

    // create user object
    User user = new User();
    user.setName(signUpUserVO.getName());
    user.setUsername(signUpUserVO.getUsername());
    user.setEmail(signUpUserVO.getEmail());
    user.setPassword(passwordEncoder.encode(signUpUserVO.getPassword()));

    Role roles = roleRepository.findByName("ROLE_USER").get();
    user.setRoles(Collections.singleton(roles));

    userRepository.save(user);

    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

  }
}
