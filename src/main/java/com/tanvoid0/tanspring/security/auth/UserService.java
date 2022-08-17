package com.tanvoid0.tanspring.security.auth;

import com.tanvoid0.tanspring.common.vo.JWTAuthResponseVO;

import java.util.List;

public interface UserService {
    List<UserVO> getAll();

    UserVO get(long id);

    UserVO create(NewUserVO newVO);

    UserVO update(UpdateUserVO updateVO);

    boolean delete(long id);

    UserVO register(NewUserVO newVO);

    JWTAuthResponseVO login(LoginUserVO loginVO);

    long getAuthUserId();

    User getAuthUser();

    UserVO getAuthUserVO();
}
