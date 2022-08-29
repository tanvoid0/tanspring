package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.models.user.UpdateUserVO;
import com.tanvoid0.tanspring.models.user.UserService;
import com.tanvoid0.tanspring.models.user.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
}
