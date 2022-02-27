package com.tanvoid0.tanspring.models.user.service;

import com.tanvoid0.tanspring.models.user.models.User;

public interface UserValidator {

  User existsById(final String id);
}
