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
        final List<User> users = repository.findAll();
        return users.stream().map(user -> mapper.map(user, UserVO.class)).toList();
    }

    @Override
    public UserVO get(final long id) {
        final User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return mapper.map(user, UserVO.class);
    }

    @Override
    public UserVO create(NewUserVO newVO) {
        return null;
    }

    @Override
    public UserVO update(UpdateUserVO updateVO) {
        final User entity = repository.findById(updateVO.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", updateVO.getId()));

        mapper.map(updateVO, entity);

        if (!updateVO.getPassword().isEmpty()) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }

        final User savedUser = repository.save(entity);
        return mapper.map(savedUser, UserVO.class);
    }

    @Override
    public boolean delete(long id) {
        final User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.getRoles().removeAll(user.getRoles());
        repository.deleteById(id);
        return true;
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
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return repository.findByUsernameOrEmail(username, username).orElseThrow(() -> new ResourceNotFoundException("user", "usernameOrEmail", username));
    }

    @Override
    public UserVO getAuthUserVO() {
        return mapper.map(this.getAuthUser(), UserVO.class);
    }
}
