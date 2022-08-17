package com.tanvoid0.tanspring.models.user.hobby;

import java.util.List;

public interface HobbyService {
    HobbyVO add(NewHobbyVO newVO);

    List<HobbyVO> get();

    HobbyVO get(long id);

    HobbyVO update(UpdateHobbyVO updateVO);

    boolean delete(long id);
}
