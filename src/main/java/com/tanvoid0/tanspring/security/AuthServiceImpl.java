package com.tanvoid0.tanspring.security;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tanvoid0.tanspring.enums.ERole;
import com.tanvoid0.tanspring.models.user.service.UserRepository;
import com.tanvoid0.tanspring.security.jwt.JwtUtils;
import com.tanvoid0.tanspring.models.user.models.Role;
import com.tanvoid0.tanspring.models.user.models.User;
import com.tanvoid0.tanspring.security.payload.request.SignupRequest;
import com.tanvoid0.tanspring.security.payload.response.JwtResponse;
import com.tanvoid0.tanspring.security.payload.response.MessageResponse;
import com.tanvoid0.tanspring.security.services.UserDetailsImpl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service("authService")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {
  @NonNull
  AuthenticationManager authenticationManager;

  @NonNull
  UserRepository userRepository;

  @NonNull
  RoleRepository roleRepository;

  @NonNull
  PasswordEncoder encoder;

  @NonNull
  JwtUtils jwtUtils;


  // @Override
  // public boolean authenticate() {
  //   return false;
  // }

  public static String getId() {
    UserDetailsImpl details = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return details.getId();
  }

  // @Override
  // public String getUserId() {
  //   UserDetailsImpl details = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  //   return details.getId();
  // }

  @Override
  public UserDetailsImpl getUser() {
    return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  @Override
  public JwtResponse login(String username, String password) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password)
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      List<String> roles = userDetails.getAuthorities().stream()
          .map(item -> item.getAuthority())
          .collect(Collectors.toList());

      return new JwtResponse(jwt,
          userDetails.getId(),
          userDetails.getUsername(),
          userDetails.getEmail(),
          roles);
    } catch (final Exception ex) {
      log.error("Error Message {}", ex.getMessage());
      return JwtResponse.builder()
          .error(ex)
          .build();
    }
  }

  @Override
  public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signUpRequest) {
    return null;
//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Email is already in use!"));
//    }
//
//    // Create new user's account
//    User user = new User(signUpRequest.getUsername(),
//        signUpRequest.getEmail(),
//        encoder.encode(signUpRequest.getPassword()));
//
//    Set<String> strRoles = signUpRequest.getRoles();
//    Set<Role> roles = new HashSet<>();
//
//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//          case "admin":
//            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(adminRole);
//
//            break;
//          case "mod":
//            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(modRole);
//
//            break;
//          default:
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        }
//      });
//    }
//
//    user.setRoles(roles);
//    userRepository.save(user);
//
//    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}