package com.tanvoid0.tanspring.common.template;

import java.util.List;

public interface CustomController<VO, NewVO, UpdateVO> {
  List<VO> get();

  VO get(long id);

  VO add(NewVO newVO);

  List<VO> add(List<NewVO> newVOList);

  VO update(long id, UpdateVO updateVO);

  boolean delete(long id);
}
