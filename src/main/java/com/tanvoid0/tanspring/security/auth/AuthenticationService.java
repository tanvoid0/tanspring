package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.AppUser;
import com.tanvoid0.tanspring.models.user.UserRepository;
import com.tanvoid0.tanspring.models.user.UserService;
import com.tanvoid0.tanspring.models.user.UserVO;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final ModelMapper mapper;

  public AuthenticationResponseVO register(NewUserVO request) {

//    request.setPassword(passwordEncoder.encode(request.getPassword()));
    var user = userService.register(request);

    var jwtToken = jwtService.generateToken(user);
    final AuthenticationResponseVO auth = mapper.map(user, AuthenticationResponseVO.class);
    auth.setToken(jwtToken);
    return auth;
  }

  public AuthenticationResponseVO login(final LoginUserVO request) {
//    authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(
//            request.getEmail(),
//            passwordEncoder.request.getPassword()
//        )
//    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow(() -> new BadCredentialsException("Invalid Credentials"));
    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return authResponseFromUser(user);
    } else {
      throw new BadCredentialsException("Invalid Credentials");
    }
  }

  public UserVO authenticate(final HttpServletRequest request) {
    final String email = jwtService.extractUsernameFromAuthorizationHeader(request);
    final AppUser user = repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    return authResponseFromUser(user);
  }

  private AuthenticationResponseVO authResponseFromUser(final AppUser user) {
    final var jwtToken = jwtService.generateToken(user);
    final AuthenticationResponseVO authUser = mapper.map(user, AuthenticationResponseVO.class);
    authUser.setToken(jwtToken);
    authUser.setExpiresIn(jwtService.expiresIn());
    return authUser;
  }

  public AppUser getAuthUser() {
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return null;
    }
    final String username = auth.getName();
    return repository.findByUsernameOrEmail(username, username).orElseThrow(() -> new ResourceNotFoundException("user", "usernameOrEmail", username));
  }

  public UserVO getPortfolio(final String username) {
    return userService.getPortfolio(username);
  }
}
