package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.common.exception.AuthException;
import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;
import com.tanvoid0.tanspring.security.jwt.JwtTokenProvider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UserVO> getAll() {
        return null;
    }

    @Override
    public UserVO get(long id) {
        return null;
    }

    @Override
    public UserVO create(NewUserVO newVO) {
        return null;
    }

    @Override
    public UserVO update(UpdateUserVO updateVO) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public UserVO register(NewUserVO newVO) {
        // add check for username exists in a DB
        if (repository.existsByUsername(newVO.getUsername())) {
            throw new AuthException("Username is already taken!");
        }

        // add check for email exists in DB
        if (repository.existsByEmail(newVO.getEmail())) {
            throw new AuthException("Email already taken!");
        }

        // create user object
        User user = mapper.map(newVO, User.class);
        user.setPassword(passwordEncoder.encode(newVO.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));

        User savedUser = repository.save(user);
        return mapper.map(savedUser, UserVO.class);
    }

    @Override
    public JWTAuthResponseVO login(LoginUserVO loginVO) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginVO.getUsernameOrEmail(), loginVO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return new JWTAuthResponseVO(token);
    }

    @Override
    public long getAuthUserId() {
        return this.getAuthUser().getId();
    }

    @Override
    public UserVO getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = repository.findByUsernameOrEmail(username, username).orElseThrow(() -> new ResourceNotFoundException("user", "usernameOrEmail", username));
        return mapper.map(user, UserVO.class);
    }
}
