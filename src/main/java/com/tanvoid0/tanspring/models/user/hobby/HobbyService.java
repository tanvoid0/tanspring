package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.common.vo.SwapOrderSequence;

import java.util.List;

public interface HobbyService {
  HobbyVO add(NewHobbyVO newVO);

  HobbyVO add(String username, NewHobbyVO newVO);

  List<HobbyVO> add(List<NewHobbyVO> newHobbyVOS);

  List<HobbyVO> get();

  HobbyVO get(long id);

  HobbyVO update(UpdateHobbyVO updateVO);

  boolean delete(long id);

  Hobby findEntity(long id);

  List<HobbyVO> findByUsername(String username);

  List<HobbyVO> swap(SwapOrderSequence swapOrderSequence);
}
