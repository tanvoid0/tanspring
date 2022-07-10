package com.tanvoid0.tanspring.models.user.service;

import com.tanvoid0.tanspring.models.user.models.NewUserVO;
import com.tanvoid0.tanspring.models.user.models.UpdateUserVO;
import com.tanvoid0.tanspring.models.user.models.User;

public interface UserValidator {

  User validate(NewUserVO newVO);

  User validate(UpdateUserVO updateVO);

  User existsById(String id);
}
