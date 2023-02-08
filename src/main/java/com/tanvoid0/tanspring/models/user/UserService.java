package com.tanvoid0.tanspring.models.user;

import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;
import com.tanvoid0.tanspring.security.auth.LoginUserVO;
import com.tanvoid0.tanspring.security.auth.NewUserVO;

import java.util.List;

public interface UserService {
  List<UserVO> getAll();

  User findByUsername(String username);

  UserVO getUserVOByUsername(long id);

  UserVO getUserVOByUsername(String username);

  UserVO getPortfolio(String username);


  UserVO create(NewUserVO newVO);

  UserVO update(UpdateUserVO updateVO);

  UserVO updateInfo(UpdateUserInfoVO updateUserInfoVO);

  boolean delete(long id);

  UserVO register(NewUserVO newVO);

  JWTAuthResponseVO login(LoginUserVO loginVO);

  long getAuthUserId();

  User getAuthUser();

  UserVO getAuthUserVO();

  User findById(long id);
}
