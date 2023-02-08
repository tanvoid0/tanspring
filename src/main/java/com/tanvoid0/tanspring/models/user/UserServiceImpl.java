package com.tanvoid0.tanspring.models.user;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.common.exception.auth.AuthException;
import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguage;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguageRepository;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguageVO;
import com.tanvoid0.tanspring.models.util_entities.ValidatorUtil;
import com.tanvoid0.tanspring.security.auth.LoginUserVO;
import com.tanvoid0.tanspring.security.auth.NewUserVO;
import com.tanvoid0.tanspring.security.auth.Role;
import com.tanvoid0.tanspring.security.auth.RoleRepository;
import com.tanvoid0.tanspring.security.jwt.JwtTokenProvider;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private SkillLanguageRepository skillLanguageRepository;

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
  public User findByUsername(final String username) {
    return repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
  }

  @Override
  public UserVO getUserVOByUsername(final long id) {
    final User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    return mapper.map(user, UserVO.class);
  }

  @Override
  public UserVO getUserVOByUsername(String username) {
    final User user = this.findByUsername(username);
    final UserVO userVO = convertEntityToVo(user);
    return userVO;
  }

  @Override
  public UserVO getPortfolio(String username) {
    final UserVO user = this.getUserVOByUsername(username);
    final List<SkillLanguage> languages = skillLanguageRepository.findAll();
    if (languages.isEmpty()) {
      return user;
    }

    final List<SkillLanguageVO> languageVOs = languages.stream().map(item -> mapper.map(item, SkillLanguageVO.class)).toList();
    user.getSkill().setLanguages(languageVOs);
    return user;
  }

  @Override
  public UserVO create(NewUserVO newVO) {
    return null;
  }

  @Override
  public UserVO update(final UpdateUserVO updateVO) {
    final User entity = findById(updateVO.getId());

    mapper.map(updateVO, entity);

    if (!updateVO.getPassword().isEmpty()) {
      entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    }

    final User savedUser = repository.save(entity);
    return mapper.map(savedUser, UserVO.class);
  }

  @SneakyThrows
  @Override
  public UserVO updateInfo(final UpdateUserInfoVO updateUserInfoVO) {
//    final User entity = getAuthUser(); // TODO: fix when token validation works
    final User entity = findByUsername("", updateUserInfoVO.getEmail());
    mapper.map(updateUserInfoVO, entity);

    ValidatorUtil.staleVersionValidator(entity.getVersion(), updateUserInfoVO.getVersion());

    final User savedUser = repository.save(entity);
    return convertEntityToVo(savedUser);
  }

  @Override
  public boolean delete(long id) {
    final User user = findById(id);
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
    try {
      Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginVO.getUsernameOrEmail(), loginVO.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      // get token form tokenProvider
      String token = tokenProvider.generateToken(authentication);

      return new JWTAuthResponseVO(token);
    } catch (final BadCredentialsException ex) {
      log.debug("Exception: {}", ex.toString());
      throw new AuthException(HttpStatus.BAD_REQUEST, ex.getMessage());
    } catch (final Exception ex) {
      throw new AuthException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
  }

  @Override
  public long getAuthUserId() {
    return this.getAuthUser().getId();
  }


  @Override
  public User getAuthUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return null;
    }
    String username = auth.getName();
    return repository.findByUsernameOrEmail(username, username).orElseThrow(() -> new ResourceNotFoundException("user", "usernameOrEmail", username));
  }

  @Override
  public UserVO getAuthUserVO() {
    return mapper.map(this.getAuthUser(), UserVO.class);
  }

  private UserVO convertEntityToVo(final User user) {
    return mapper.map(user, UserVO.class);
  }

  @Override
  public User findById(final long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
  }

  public User findByUsername(final String username, final String email) {
    return repository.findByUsernameOrEmail(username, email)
        .orElseThrow(() ->
            new ResourceNotFoundException("User", "usernameOrEmail", String.format("username: %s, email: %s", username, email))
        );
  }

}
