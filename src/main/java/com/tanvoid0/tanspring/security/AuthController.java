package com.tanvoid0.tanspring.security;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import com.tanvoid0.tanspring.models.user.service.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanvoid0.tanspring.security.payload.request.LoginRequest;
import com.tanvoid0.tanspring.security.payload.request.SignupRequest;
import com.tanvoid0.tanspring.security.payload.response.AuthException;
import com.tanvoid0.tanspring.security.payload.response.JwtResponse;
import com.tanvoid0.tanspring.security.jwt.JwtUtils;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  AuthService authService;

  @PostMapping("/signin")
  public <T extends ResponseEntity> T authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    log.info(loginRequest.toString());
    JwtResponse data = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    log.info("Authentication data");
    if (data.getError() == null) {
      return (T) ResponseEntity.ok(data);
    } else {
      final AuthException ex = new AuthException(data.getError().getMessage());
      return (T) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toMap());
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return authService.signup(signUpRequest);
  }
}