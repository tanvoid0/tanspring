package com.tanvoid0.tanspring.models.user.service;

import com.tanvoid0.tanspring.models.user.exception.UserNotFoundException;
import com.tanvoid0.tanspring.models.user.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userValidator")
public class UserValidatorImpl implements UserValidator {
  @Autowired
  UserRepository repository;

  @Override
  public User existsById(String id) {
    return repository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id));
  }
}
