package com.tanvoid0.tanspring.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tanvoid0.tanspring.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanvoid0.tanspring.enums.ERole;
import com.tanvoid0.tanspring.security.models.Role;
import com.tanvoid0.tanspring.security.payload.request.LoginRequest;
import com.tanvoid0.tanspring.security.payload.request.SignupRequest;
import com.tanvoid0.tanspring.security.payload.response.JwtResponse;
import com.tanvoid0.tanspring.security.payload.response.MessageResponse;
import com.tanvoid0.tanspring.security.jwt.JwtUtils;
import com.tanvoid0.tanspring.security.services.UserDetailsImpl;

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
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    JwtResponse data = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    return ResponseEntity.ok(data);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return authService.signup(signUpRequest);
  }
}