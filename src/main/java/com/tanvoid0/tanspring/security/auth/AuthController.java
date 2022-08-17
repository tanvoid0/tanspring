package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.common.exception.AuthException;
import com.tanvoid0.tanspring.security.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;
import com.tanvoid0.tanspring.security.jwt.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Api(value = "Auth controller exposes signin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @ApiOperation(value = "REST API to Signin or Login user to Blog app")
    @PostMapping("/signin")
    public JWTAuthResponseVO authenticateUser(@RequestBody LoginUserVO loginUserVO) {
        return userService.login(loginUserVO);
    }

    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signup")
    public UserVO registerUser(@RequestBody NewUserVO newUserVO) {
        return userService.register(newUserVO);
    }

    @ApiOperation(value = "REST API to authenticate user using token")
    @PostMapping("/authenticate")
    @PreAuthorize("hasRole('USER')")
    public UserVO authenticate() {
        return userService.getAuthUser();
    }
}
