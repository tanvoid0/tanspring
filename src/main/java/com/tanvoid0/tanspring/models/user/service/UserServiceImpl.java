package com.tanvoid0.tanspring.models.user.service;

import com.tanvoid0.tanspring.models.user.exception.UserUpdateException;
import com.tanvoid0.tanspring.models.user.models.NewUserVO;
import com.tanvoid0.tanspring.models.user.models.UpdateUserVO;
import com.tanvoid0.tanspring.models.user.models.User;
import com.tanvoid0.tanspring.models.user.models.UserVO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

@Service("userService")
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository repository;

  @Autowired
  UserValidator validator;

  @NotNull
  ModelMapper mapper;

  @Override
  public List<UserVO> getAll() {
    final List<User> users = repository.findAll();
    return users.stream().map(item -> mapper.map(item, UserVO.class)).toList();
  }

  @Override
  public UserVO get(String id) {
    return mapper.map(validator.existsById(id), UserVO.class);
  }

  @Override
  public UserVO create(NewUserVO newVO) {
    final User entity = mapper.map(newVO, User.class);
    final User savedEntity = repository.save(entity);
    return mapper.map(savedEntity, UserVO.class);
  }

  @Override
  public UserVO update(String id, UpdateUserVO updateVO) {
    final User user = validator.existsById(id);

    final UserVO existingUserVO = mapper.map(user, UserVO.class);

    mapper.map(updateVO, user);
    try {
      final User savedUser = repository.save(user);
      return mapper.map(savedUser, UserVO.class);
    } catch (final Exception e) {
      throw new UserUpdateException(e.getMessage());
    }
  }

  @Override
  public String delete(String id) {
    validator.existsById(id);
    repository.deleteById(id);
    return id;
  }
}
