package com.tanvoid0.tanspring.models.user.service;

import com.tanvoid0.tanspring.models.user.models.NewUserVO;
import com.tanvoid0.tanspring.models.user.models.UpdateUserVO;
import com.tanvoid0.tanspring.models.user.models.UserVO;

import java.util.List;

public interface UserService {
  List<UserVO> getAll();

  UserVO get(String id);

  UserVO create(NewUserVO vo);

  UserVO update(String id, UpdateUserVO vo);

  String delete(String id);
}
