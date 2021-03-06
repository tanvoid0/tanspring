package com.tanvoid0.tanspring.security;

import javax.validation.Valid;
import com.tanvoid0.tanspring.security.models.User;
import com.tanvoid0.tanspring.security.payload.request.SignupRequest;
import com.tanvoid0.tanspring.security.payload.response.JwtResponse;
import com.tanvoid0.tanspring.security.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {


  UserDetailsImpl getUser();

  // String getUserId();

  JwtResponse login(String username, String password);

  ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signUpRequest);
}