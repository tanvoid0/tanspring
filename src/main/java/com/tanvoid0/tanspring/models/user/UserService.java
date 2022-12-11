package com.tanvoid0.tanspring.models.user;

import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;
import com.tanvoid0.tanspring.security.auth.LoginUserVO;
import com.tanvoid0.tanspring.security.auth.NewUserVO;

import java.util.List;

public interface UserService {
  List<UserVO> getAll();

  UserVO get(long id);

  UserVO get(String username);

  UserVO getPortfolio(String username);


  UserVO create(NewUserVO newVO);

  UserVO update(UpdateUserVO updateVO);

  boolean delete(long id);

  UserVO register(NewUserVO newVO);

  JWTAuthResponseVO login(LoginUserVO loginVO);

  long getAuthUserId();

  User getAuthUser();

  UserVO getAuthUserVO();

}
