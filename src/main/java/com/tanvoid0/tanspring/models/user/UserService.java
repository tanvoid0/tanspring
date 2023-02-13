package com.tanvoid0.tanspring.models.user;

import com.tanvoid0.tanspring.security.auth.AuthenticationResponseVO;
import com.tanvoid0.tanspring.security.auth.LoginUserVO;
import com.tanvoid0.tanspring.security.auth.NewUserVO;

import java.util.List;

public interface UserService {
  List<UserVO> getAll();

  AppUser findByUsername(String username);

  UserVO getUserVOByUsername(long id);

  UserVO getUserVOByUsername(String username);

  UserVO getPortfolio(String username);


  UserVO create(NewUserVO newVO);

  UserVO update(UpdateUserVO updateVO);

  UserVO updateInfo(UpdateUserInfoVO updateUserInfoVO);

  boolean delete(long id);

  AppUser register(NewUserVO newVO);

  AuthenticationResponseVO login(LoginUserVO loginVO);

  long getAuthUserId();

  AppUser getAuthUser();

  UserVO getAuthUserVO();

  AppUser findById(long id);
}
