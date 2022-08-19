package com.tanvoid0.tanspring.common.template;

import java.util.List;

public interface CustomService<Entity, VO, NewVO, UpdateVO> {

    List<VO> get();

    VO get(long id);

    VO add(NewVO newVO);

    VO update(UpdateVO updateVO);

    boolean delete(long id);

    Entity findEntity(long id);

    VO convertEntityToVO(Entity entity);

    Entity convertVOToEntity(VO vo);
}
